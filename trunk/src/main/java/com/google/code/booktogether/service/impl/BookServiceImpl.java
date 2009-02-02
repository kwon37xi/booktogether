package com.google.code.booktogether.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.google.code.booktogether.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	public Date getCurrentDate(){
		return new Date();
	}

}
