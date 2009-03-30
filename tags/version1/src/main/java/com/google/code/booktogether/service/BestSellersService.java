package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Book;

public interface BestSellersService {

	public boolean insertBestSeller(Integer bookIdNum);
	
	public List<Book> getListBestSellers();
	
}
