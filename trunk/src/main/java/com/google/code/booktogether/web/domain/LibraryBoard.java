package com.google.code.booktogether.web.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 서재 방명록
 */
public class LibraryBoard extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
	 */
	private Integer idNum;

	/**
	 * 서재 일련번호
	 */
	private Integer libraryIdNum;

	/**
	 * 작성자 일련번호
	 */
	private Integer writer;

	/**
	 * 작성자 아이디
	 */
	private String writerUserId;

	/**
	 * 작성자 이름
	 */
	private String writerName;

	/**
	 * 내용
	 */
	private String content;

	/**
	 * 입력 날짜(시간)
	 */
	private Date inputDate;

	@Override
	public boolean equals(Object o) {

		if (o instanceof LibraryBoard == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		LibraryBoard rhs = (LibraryBoard) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(libraryIdNum, rhs.getLibraryIdNum());
		equb.append(writer, rhs.getWriter());
		equb.append(writerUserId, rhs.getWriterUserId());
		equb.append(writerName, rhs.getWriterName());
		equb.append(content, rhs.getContent());
		equb.append(inputDate, rhs.getInputDate());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(19, 37);
		hashcode.append(idNum);
		hashcode.append(libraryIdNum);
		hashcode.append(writer);
		hashcode.append(writerUserId);
		hashcode.append(writerName);
		hashcode.append(content);
		hashcode.append(inputDate);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("libraryIdNum", libraryIdNum);
		tob.append("writer", writer);
		tob.append("writerUserId", writerUserId);
		tob.append("writerName", writerName);
		tob.append("content", content);
		tob.append("inputDate", inputDate);

		return tob.toString();

	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public Integer getLibraryIdNum() {
		return libraryIdNum;
	}

	public void setLibraryIdNum(Integer libraryIdNum) {
		this.libraryIdNum = libraryIdNum;
	}

	public Integer getWriter() {
		return writer;
	}

	public void setWriter(Integer writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getWriterUserId() {
		return writerUserId;
	}

	public void setWriterUserId(String writerUserId) {
		this.writerUserId = writerUserId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

}
