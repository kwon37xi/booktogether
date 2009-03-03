package com.google.code.booktogether.dao;

import java.util.List;

public interface BestSellersDao {

	public int insertBestSellers(Integer bookIdNum);

	public List<Integer> getListBestSellers();

}
