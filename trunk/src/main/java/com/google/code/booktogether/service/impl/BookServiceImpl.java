package com.google.code.booktogether.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookJdbcDao;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;

@Service("bookService")
public class BookServiceImpl implements BookService {

	//책 JDBC DAO DI
	@Resource(name="bookJdbcDao")
	private BookJdbcDao bookJdbcDao;
	

	@Override
	public List<Book> getListBook(PageBean pageBean) {

		//페이지를 위해서 전체 목록 가지고 오기
		int dbcount=bookJdbcDao.getDbcount();

		//페이지 정보에 저장
		pageBean.setDbcount(dbcount);

		//목록 가지고 오기
		List<Book> booklist = bookJdbcDao.getListBook(pageBean.getStartPage()-1, pageBean.getPagesize());

		//책 목록 에서 지은이 정보는 빠진상태
		//한 책당 여러명의 지은이 정보를 가지고 와야 함
		for(int i=0;i<booklist.size();i++){
			
			//책에 관련된 지은이정보
			List<Author> authorlist=bookJdbcDao.getAuthor(booklist.get(i));
			
			//List<Author> -> Array로 바꾸기
			if(authorlist!=null){

				Author[] authors=new Author[authorlist.size()];

				int j=0;

				for(Author author : authorlist){
					authors[j++]=author;
				}

				booklist.get(i).setAuthors(authors);
			}
		}

		return booklist;
	}

	//책 등록
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertBook(Book book) {

		boolean result=false;
		
		//책정보 등록
		int count=bookJdbcDao.insertBook(book);
		
		//지은이 정보등록
		int count1=bookJdbcDao.insertAuthor(book.getAuthors());

		//이건 간단히 검증 단계
		result=(count==0 || count1==0) ? false : true;

		return result;
	}

	//책 수정
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean modifyBook(Book book) {
		
		boolean result=false;

		//책 수정
		int count=bookJdbcDao.modifyBook(book);
		
		//지은이 정보 수정
		for(Author author:book.getAuthors()){
			bookJdbcDao.modifyAuthor(author);
		}

		result=(count==0) ? false : true;
		
		return result;
	}

	//책 삭제
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteBook(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	//책 조회
	@Override
	public Book getBook(int id) {
		
		//책 정보 가지고 오기
		Book book=bookJdbcDao.getBook(id);
		
		//해당 책관련 지은이 가지고오기
		List<Author> authorlist=bookJdbcDao.getAuthor(book);
		
		//List<Author> -> array로 변환
		if(authorlist!=null){

			Author[] authors=new Author[authorlist.size()];

			int j=0;

			for(Author author : authorlist){
				authors[j++]=author;
			}

			book.setAuthors(authors);
		}
		
		return book;
		
	}


}
