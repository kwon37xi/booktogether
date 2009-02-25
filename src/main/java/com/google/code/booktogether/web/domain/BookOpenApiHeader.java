package com.google.code.booktogether.web.domain;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 책헤더 도메인(다음)
 * 
 * @author ParkHaeCheol
 * 
 */
public class BookOpenApiHeader extends BaseObject{

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

	@Override
	public boolean equals(Object o) {

		if (o instanceof BookOpenApiHeader == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		BookOpenApiHeader rhs = (BookOpenApiHeader) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(title, rhs.getTitle());
		equb.append(totalCount, rhs.getTotalCount());
		equb.append(result, rhs.getResult());
		equb.append(sort, rhs.getSort());
		equb.append(pageNo, rhs.getPageNo());
		equb.append(etcInfo, rhs.getEtcInfo());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(7, 37);
		hashcode.append(title);
		hashcode.append(totalCount);
		hashcode.append(result);
		hashcode.append(sort);
		hashcode.append(pageNo);
		hashcode.append(etcInfo);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("title", title);
		tob.append("totalCount", totalCount);
		tob.append("result", result);
		tob.append("sort", sort);
		tob.append("pageNo", pageNo);
		tob.append("etcInfo", etcInfo);

		return tob.toString();

	}

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
