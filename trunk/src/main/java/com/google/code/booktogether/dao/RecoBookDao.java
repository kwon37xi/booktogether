package com.google.code.booktogether.dao;

import java.util.List;

public interface RecoBookDao {

	public int insertRecoBook(Integer bookIdNum);

	public List<Integer> getListRecoBook();

}
