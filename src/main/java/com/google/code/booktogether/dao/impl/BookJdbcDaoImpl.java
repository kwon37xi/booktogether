package com.google.code.booktogether.dao.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.BookJdbcDao;
import com.google.code.booktogether.dao.rowmapper.AuthorRowMapper;
import com.google.code.booktogether.dao.rowmapper.BookRowMapper;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

@Repository("bookJdbcDao")
public class BookJdbcDaoImpl extends SimpleJdbcDaoSupport implements BookJdbcDao{
	
	private Logger log=Logger.getLogger(this.getClass());
	private static final String INSERT_BOOK_SQL="INSERT INTO BOOK(name,page, isbn10,isbn13, publish_comp,publish_date, price, size, corver, category, content) VALUES(?, ?, ?, ?, ?, ?, ?, ? ,? , ?, ?)";
	private static final String MODIFY_BOOK_SQL="UPDATE BOOK SET name=?, page=?, isbn10=?, isbn13=?, publish_comp=?, publish_date=?, price=?, size=?, corver=?, category=?, content=? WHERE id=?";
	private static final String MODIFY_AUTHOR_SQL="UPDATE AUTHOR SET name=?, author_div=? WHERE id=?";
	private static final String DBCOUNT_SQL="SELECT count(*) FROM BOOK";
	private static final String LIST_BOOK_SQL="SELECT * FROM BOOK LIMIT ?,?";
	private static final String GET_AUTHORS_SQL="SELECT * FROM AUTHOR WHERE BOOK_REF=?";
	private static final String GET_BOOK_SQL="SELECT * FROM BOOK WHERE ID=?";
	private static final String INSERT_AUTHOR_SQL="INSERT INTO AUTHOR(name,author_div,book_ref) VALUES(?, ?, LAST_INSERT_ID())";

	@Resource(name="dataSource")
	public void setJdbcDao(DataSource dataSource){
		setDataSource(dataSource);
	}
	
	@Resource(name="bookRowMapper")
	BookRowMapper bookRowMapper;
	
	@Resource(name="authorRowMapper")
	AuthorRowMapper authorRowMapper;
	
	@Override
	public int deleteBook(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Book getBook(int id) {
		
		Book book=getSimpleJdbcTemplate().queryForObject(GET_BOOK_SQL, bookRowMapper, new Object[]{id});
		
		return book;
	}

	@Override
	public List<Book> getListBook(int startRow, int pageSize) {
		
		List<Book> booklist=getSimpleJdbcTemplate().query(LIST_BOOK_SQL, bookRowMapper, new Object[]{startRow-1, pageSize});
		
		
		
		return booklist;
		
	}

	@Override
	public int insertBook(Book book) {
		
		if(log.isInfoEnabled()){
			log.info("시작");
		}
		
		int count=getSimpleJdbcTemplate().update(
					INSERT_BOOK_SQL,
					new Object[]{
						book.getName()
						,book.getPage()
						,book.getISBN10()
						,book.getISBN13()
						,book.getPublish_comp()
						,book.getPublish_date()
						,book.getPrice()
						,book.getSize()
						,book.getCorver()
						,book.getCategory()
						,book.getContent()
		});
		
		if(log.isInfoEnabled()){
			log.info("종료");
		}
		
		return count;
	}

	@Override
	public int modifyBook(Book book) {
		int count=getSimpleJdbcTemplate().update(
				MODIFY_BOOK_SQL,
					new Object[]{
						book.getName()
						,book.getPage()
						,book.getISBN10()
						,book.getISBN13()
						,book.getPublish_comp()
						,book.getPublish_date()
						,book.getPrice()
						,book.getSize()
						,book.getCorver()
						,book.getCategory()
						,book.getContent()
						,book.getId()
				});
		return count;
	}

	@Override
	public int getDbcount() {
		
		int count=getSimpleJdbcTemplate().queryForInt(DBCOUNT_SQL);
		
		return count;
	}

	@Override
	public int insertAuthor(Author[] authors) {
		
		int count=0;
		
		for(Author author :authors){
			count=getSimpleJdbcTemplate().update(INSERT_AUTHOR_SQL, new Object[]{author.getName(), author.getAuthor_div()});
		}
		
		return count;
	}

	@Override
	public List<Author> getAuthor(Book book) {
		List<Author> authorlist=getSimpleJdbcTemplate().query(GET_AUTHORS_SQL, authorRowMapper, new Object[]{book.getId()});
		return authorlist;
	}

	@Override
	public int modifyAuthor(Author author) {
		int count=getSimpleJdbcTemplate().update(MODIFY_AUTHOR_SQL, new Object[]{author.getName(), author.getAuthor_div(),author.getId()});
		return count;
	}

}
