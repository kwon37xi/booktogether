package com.google.code.booktogether.web.page;

/**
 * 페이징 기법을 위한 클래스.
 */
public class PageBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 리스트 뿌릴때 번호출력
	 */
	private Integer numList;

	/**
	 * 선택된 페이지
	 */
	private Integer pageNo = 1;

	/**
	 * 시작 페이지
	 */
	private Integer startRow;

	/**
	 * 마지막 페이지
	 */
	private Integer endRow;

	/**
	 * 데이터베이스에 있는 모든 회원수
	 */
	private Integer dbCount;

	/**
	 * 보여줄 페이지 갯수
	 */
	private Integer pageSize = 20;

	/**
	 * 총번호 수
	 */
	private Integer pageCount;

	/**
	 * 보여질 페이지 숫자 갯수
	 */
	private Integer limit = 3;

	/**
	 * 시작 페이지
	 */
	private Integer startPage = 1;

	/**
	 * 끝페이지
	 */
	private Integer endPage;

	/**
	 * 이전 페이지 유무확인
	 */
	private boolean prePage;

	/**
	 * 다음 페이지 유무확인
	 */
	private boolean nextPage;
	
	
	

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setDbCount(Integer dbCount) {
		this.dbCount = dbCount;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getStartRow() {
		startRow = (pageNo - 1) * pageSize + 1;
		return startRow;
	}

	public Integer getEndRow() {
		endRow = startRow + pageSize - 1;
		if (endRow > dbCount) {
			endRow = dbCount;
		}
		return endRow;
	}

	public Integer getDbCount() {
		return dbCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getPageCount() {
		if (dbCount % pageSize == 0)
			pageCount = dbCount / pageSize;
		else
			pageCount = dbCount / pageSize + 1;
		return pageCount;
	}

	public Integer getStartPage() {
		startPage = pageNo - ((pageNo - 1) % limit);
		return startPage;
	}

	public Integer getEndPage() {
		getPageCount();
		endPage = startPage + limit - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		if (endPage == 0) {
			endPage = 1;
		}
		return endPage;
	}

	public boolean isNextPage() {
		if (getStartPage() + getLimit() - 1 < getPageCount()) {
			nextPage = true;
		} else
			nextPage = false;
		return nextPage;
	}

	public boolean isPrePage() {
		if (getStartPage() - getLimit() > 0) {
			prePage = true;
		} else
			prePage = false;
		return prePage;
	}

	public Integer getNumList() {
		numList = dbCount - ((getPageNo() - 1) * getPageSize());
		return numList;
	}

	public void setNumList(Integer numList) {
		this.numList = numList;
	}

}
