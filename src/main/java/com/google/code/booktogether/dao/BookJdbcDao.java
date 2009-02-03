package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

public interface BookJdbcDao {

	public int insertBook(Book book);

	public int modifyBook(Book book);

	public int deleteBook(Book book);

	public Book getBook(Book book);

	public List<Book> getListBook(int startRow, int pageSize);
	
	public int getDbcount();
	
	public int insertAuthor(Author[] authors);
}
