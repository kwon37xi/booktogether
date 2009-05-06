package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 책 도메인
 * 
 * @author ParkHaeCheol
 * 
 */
public class Book extends BaseObject {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer idNum;

	/**
	 * 책이름
	 */
	private String name;

	/**
	 * 책지은이들
	 */
	private Author[] authors=null;

	/**
	 * ISBN 10자리
	 */
	private String ISBN10;

	/**
	 * ISBN 13자리
	 */
	private String ISBN13;

	/**
	 * 출판사
	 */
	private String publishComp;

	/**
	 * 출판일
	 */
	private String publishDate;

	/**
	 * 책가격
	 */
	private Integer price;

	/**
	 * 분류
	 */
	private String category;

	/**
	 * 책표지이미지
	 */
	private String bookCover;

	/**
	 * 책 설명
	 */
	private String description;

	@Override
	public boolean equals(Object o) {

		if (o instanceof Book == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		Book rhs = (Book) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(name, rhs.getName());
		equb.append(ISBN10, rhs.getISBN10());
		equb.append(ISBN13, rhs.getISBN13());
		equb.append(publishComp, rhs.getPublishComp());
		equb.append(publishDate, rhs.getPublishDate());
		equb.append(price, rhs.getPrice());
		equb.append(category, rhs.getCategory());
		equb.append(bookCover, rhs.getBookCover());
		equb.append(description, rhs.getDescription());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(59, 113);
		hashcode.append(idNum);
		hashcode.append(name);
		hashcode.append(ISBN10);
		hashcode.append(ISBN13);
		hashcode.append(publishComp);
		hashcode.append(publishDate);
		hashcode.append(price);
		hashcode.append(category);
		hashcode.append(bookCover);
		hashcode.append(description);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("name", name);
		tob.append("ISBN10", ISBN10);
		tob.append("ISBN13", ISBN13);
		tob.append("publishComp", publishComp);
		tob.append("publishDate", publishDate);
		tob.append("price", price);
		tob.append("category", category);
		tob.append("bookCover", bookCover);
		tob.append("description", description);

		if (authors != null) {

			for (Author author : authors) {
				tob.append("AUTHOR", author.toString());
			}

		}

		return tob.toString();

	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author[] getAuthors() {
		return authors;
	}

	public void setAuthors(Author[] authors) {
		this.authors = authors;
	}

	public String getISBN10() {
		return ISBN10;
	}

	public void setISBN10(String isbn10) {
		ISBN10 = isbn10;
	}

	public String getISBN13() {
		return ISBN13;
	}

	public void setISBN13(String isbn13) {
		ISBN13 = isbn13;
	}

	public String getPublishComp() {
		return publishComp;
	}

	public void setPublishComp(String publishComp) {
		this.publishComp = publishComp;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
