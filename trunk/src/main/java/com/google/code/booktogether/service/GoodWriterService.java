package com.google.code.booktogether.service;

import com.google.code.booktogether.web.domain.GoodWriter;

public interface GoodWriterService {

	public boolean insertGoodWriter(GoodWriter goodWriter);
	
	public GoodWriter getGoodWriter();
	
}
