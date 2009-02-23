package com.google.code.booktogether.web.openapi.header;

import java.util.Map;

/**
 * 책헤더 도메인(다음)
 * @author ParkHaeCheol
 *
 */
public class BookOpenApiHeader{
	
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
	
	/**
	 * 각기 다른 API의 차별된 정보 공간
	 */
	private Map<String, String> etcInfo;
	
	
	public Map<String, String> getEtcInfo() {
		return etcInfo;
	}

	public void setEtcInfo(Map<String, String> etcInfo) {
		this.etcInfo = etcInfo;
	}

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
