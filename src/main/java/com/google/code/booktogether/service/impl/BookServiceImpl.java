package com.google.code.booktogether.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookDao;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.interceptor.UseApiDaumBook;

@Service("bookService")
public class BookServiceImpl implements BookService {

	//책 JDBC DAO DI
	@Resource(name="bookJdbcDao")
	private BookDao bookJdbcDao;


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
			booklist.get(i).setAuthors(listToArray(authorlist));
		}

		return booklist;
	}

	//책 등록
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insertBook(Book book) {

		boolean result=false;

		try{

			//책정보 등록
			int count=bookJdbcDao.insertBook(book);

			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}

			int id=bookJdbcDao.getLastNumIncrement();

			//지은이 정보등록
			count=bookJdbcDao.insertAuthor(book.getAuthors(),id);

			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}else{
				result=true;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return result;
	}

	//책 수정
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean modifyBook(Book book) {

		boolean result=false;

		try{

			//책 수정
			int count=bookJdbcDao.modifyBook(book);

			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}

			//지은이 정보 수정
			for(Author author:book.getAuthors()){
				count=bookJdbcDao.modifyAuthor(author);
			}

			//책 수정
			count=bookJdbcDao.modifyBook(book);

			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return result;
	}

	//책 삭제
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean deleteBook(int id) {

		boolean result=false;
		
		try{

			int count=bookJdbcDao.deleteBook(id);
			
			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}
			
			count=bookJdbcDao.deleteAuthor(id);
			
			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}else{
				result=true;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return result;

	}

	//책 조회
	@Override
	public Book getBook(int id) {

		//책 정보 가지고 오기
		Book book=bookJdbcDao.getBook(id);

		if(book!=null){

			//해당 책관련 지은이 가지고오기
			List<Author> authorlist=bookJdbcDao.getAuthor(book);

			//List<Author> -> array로 변환
			book.setAuthors(listToArray(authorlist));
		}

		return book;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public Book checkBook(String isbn) {

		Book book=bookJdbcDao.getBook(isbn);

		if(book==null){

			//OPEN API로 Book값 세팅
			book=new UseApiDaumBook().viewBook(isbn);

			//DB에 넣기
			this.insertBook(book);

			//ISBN으로 값가지고 오기
			book=bookJdbcDao.getBook(isbn);

		}

		//지은이 정보 가지고 오기
		List<Author> authorlist=bookJdbcDao.getAuthor(book);

		book.setAuthors(listToArray(authorlist));

		return book;
	}

	//List -> Array로 변환
	@Override
	public Author[] listToArray(List<Author> authorlist) {

		Author[] authors=null;

		if(authorlist!=null){

			authors=new Author[authorlist.size()];

			int j=0;

			for(Author author : authorlist){
				authors[j++]=author;
			}
		}

		return authors;

	}

	@Override
	public List<Book> searchBook(String query, String searchType, PageBean pageBean) {
		
		UseApiDaumBook uadb=new UseApiDaumBook();
		
		List<Book> booklist=uadb.searchBook(query, searchType, pageBean.getPageNo());
		
		pageBean.setDbcount(uadb.getTotalCount());
		
		return booklist;
	}
}
