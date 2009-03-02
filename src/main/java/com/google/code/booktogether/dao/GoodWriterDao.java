package com.google.code.booktogether.dao;

import com.google.code.booktogether.web.domain.GoodWriter;


public interface GoodWriterDao {

	public int insertGoodWriter(GoodWriter goodwriter);
	
	public int deleteGoodWriter(GoodWriter goodwriter);
	
	public GoodWriter getGoodWriter();
	
}
