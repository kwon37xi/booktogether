package com.google.code.booktogether.web.openapi.header;

import com.google.code.booktogether.web.domain.base.BaseObject;


/**
 * 책헤더 도메인(다음)
 * @author ParkHaeCheol
 *
 */
public class BookOpenApiDaumHeader extends BaseObject{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 헤더 제목
	 */
	private String title;
	
	/**
	 * 총 검색 갯수
	 */
	private String totalCount;
	
	/**
	 * 보여질 갯수
	 */
	private String result;
	
	/**
	 * 정렬
	 */
	private String sort;
	
	/**
	 * 페이지
	 */
	private String pageNo;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	
	
	

}
