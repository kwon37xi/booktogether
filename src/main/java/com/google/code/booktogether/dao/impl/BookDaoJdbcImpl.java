package com.google.code.booktogether.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.code.booktogether.dao.BookDao;
import com.google.code.booktogether.dao.rowmapper.AuthorRowMapper;
import com.google.code.booktogether.dao.rowmapper.BookRowMapper;
import com.google.code.booktogether.service.util.XmlUtil;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

@Repository("bookJdbcDao")
public class BookDaoJdbcImpl extends SimpleJdbcDaoSupport implements BookDao{

	@Resource(name="dataSource")
	public void setJdbcDao(DataSource dataSource){
		setDataSource(dataSource);
	}

	//책 목록에 쓰일 RowMapper
	@Resource(name="bookRowMapper")
	BookRowMapper bookRowMapper;

	//지은이 목록에 쓰일 RowMapper
	@Resource(name="authorRowMapper")
	AuthorRowMapper authorRowMapper;


	//책 삭제
	@Override
	public int deleteBook(int id) {

		String sql=XmlUtil.getInstance().getSQL("book","DELETE_BOOK_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{id});

		return count;
	}

	//책 관련 지은이 삭제
	@Override
	public int deleteAuthorBook(int book_ref) {

		String sql=XmlUtil.getInstance().getSQL("book","DELETE_AUTHORBOOK_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{book_ref});

		return count;
	}

	//지은이 삭제
	@Override
	public int deleteAuthor(int id) {

		String sql=XmlUtil.getInstance().getSQL("book","DELETE_AUTHOR_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{id});

		return count;
	}

	//책 조회
	@Override
	public Book getBook(int id) {

		String sql=XmlUtil.getInstance().getSQL("book","GET_BOOK_ID_SQL");

		Book book=(Book)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, bookRowMapper, new Object[]{id}));

		return book;
	}

	//책 목록
	@Override
	public List<Book> getListBook(int startpage, int pageSize) {

		String sql=XmlUtil.getInstance().getSQL("book","LIST_BOOK_SQL");

		List<Book> booklist=getSimpleJdbcTemplate().query(sql, bookRowMapper, new Object[]{startpage, pageSize});

		return booklist;

	}

	//책 등록
	@Override
	public int insertBook(Book book) {
		
		String sql=XmlUtil.getInstance().getSQL("book","INSERT_BOOK_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql,
				new Object[]{
						book.getName()
						,book.getISBN10()
						,book.getISBN13()
						,book.getPublish_comp()
						,book.getPublish_date()
						,book.getPrice()
						,book.getCorver()
						,book.getCategory()
						,book.getDescription()
				});

		return count;
	}

	//책 수정
	@Override
	public int modifyBook(Book book) {

		String sql=XmlUtil.getInstance().getSQL("book","MODIFY_BOOK_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql,
				new Object[]{
						book.getName()
						,book.getISBN10()
						,book.getISBN13()
						,book.getPublish_comp()
						,book.getPublish_date()
						,book.getPrice()
						,book.getCorver()
						,book.getCategory()
						,book.getDescription()
						,book.getId()
				});
		return count;
	}

	//책 전체 갯수
	@Override
	public int getDbcount() {

		String sql=XmlUtil.getInstance().getSQL("book","DBCOUNT_SQL");

		int count=getSimpleJdbcTemplate().queryForInt(sql);

		return count;
	}

	//지은이 등록
	@Override
	public int insertAuthor(Author[] authors,int id) {

		String sql=XmlUtil.getInstance().getSQL("book","INSERT_AUTHOR_SQL");

		int count=0;

		for(Author author :authors){
			count=getSimpleJdbcTemplate().update(sql, new Object[]{author.getName(), author.getAuthor_div(),id});
		}

		return count;
	}

	
	
	//해당책에 관련된 지은이 목록
	@Override
	public List<Author> getAuthor(Book book) {

		String sql=XmlUtil.getInstance().getSQL("book","GET_AUTHORS_SQL");

		List<Author> authorlist=getSimpleJdbcTemplate().query(sql, authorRowMapper, new Object[]{book.getId()});
		
		return authorlist;
	}

	
	
	//지은이 수정
	@Override
	public int modifyAuthor(Author author) {

		String sql=XmlUtil.getInstance().getSQL("book","MODIFY_AUTHOR_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{author.getName(), author.getAuthor_div(),author.getId()});
		return count;
	}

	
	
	//책조회 isbn
	@Override
	public Book getBook(String isbn) {

		String sql=XmlUtil.getInstance().getSQL("book","GET_BOOK_ISBN_SQL");
		
		Book book=(Book)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql,bookRowMapper,new Object[]{isbn,isbn}));
		
		return book;
	}

	
	
	@Override
	public Author getAuthor(int id) {

		String sql=XmlUtil.getInstance().getSQL("book","GET_AUTHOR_SQL");

		Author author=(Author)DataAccessUtils.singleResult(
									getSimpleJdbcTemplate().query(sql, authorRowMapper, new Object[]{id})
							  );
		return author;
		
	}

	@Override
	public int getLastNumIncrement() {
		
		String sql=XmlUtil.getInstance().getSQL("book","GET_LAST_NUM");

		int last_num=getSimpleJdbcTemplate().queryForInt(sql);
							 
		return last_num;
	}

}
