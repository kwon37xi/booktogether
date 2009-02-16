package com.google.code.booktogether.web.openapi.header;

import com.google.code.booktogether.web.domain.BaseObject;


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
	private String totalcount;
	
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
	private String pageno;
	
	
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
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

	public String getPageno() {
		return pageno;
	}

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

	

}
