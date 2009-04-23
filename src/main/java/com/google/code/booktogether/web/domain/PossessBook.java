package com.google.code.booktogether.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 소유책 도메인
 * 
 * @author ParkHaeCheol
 * 
 */
@Entity
@Table(name="possessbook")
public class PossessBook extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 소유책 일련번호
	 */
	@Column(name="idNum")
	private Integer idNum;

	/**
	 * 구입 날짜
	 */
	@Column(name="purchase_date")
	private Date purchaseDate;

	/**
	 * 구입 가격
	 */
	@Column(name="purchase_price")
	private Integer purchasePrice;

	/**
	 * 독서 시작일
	 */
	@Column(name="begin_read")
	private Date beginRead;

	/**
	 * 독서 종료일
	 */
	@Column(name="end_read")
	private Date endRead;

	/**
	 * 책 품질
	 */
	@Column(name="quality")
	private Integer quality;

	/**
	 * 책 상태
	 */
	@Column(name="state")
	private Integer state;
	
	/**
	 * 소유한 책 정보
	 */
	private Book book;

	/**
	 * 소유자 정보
	 */
	private User user;
	

	@Override
	public boolean equals(Object o) {

		if (o instanceof PossessBook == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		PossessBook rhs = (PossessBook) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(purchaseDate, rhs.getPurchaseDate());
		equb.append(purchasePrice, rhs.getPurchasePrice());
		equb.append(beginRead, rhs.getBeginRead());
		equb.append(endRead, rhs.getEndRead());
		equb.append(quality, rhs.getQuality());
		equb.append(state, rhs.getState());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(2, 17);
		hashcode.append(idNum);
		hashcode.append(purchaseDate);
		hashcode.append(purchasePrice);
		hashcode.append(beginRead);
		hashcode.append(endRead);
		hashcode.append(quality);
		hashcode.append(state);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("purchaseDate", purchaseDate);
		tob.append("purchasePrice", purchasePrice);
		tob.append("beginRead", beginRead);
		tob.append("endRead", endRead);
		tob.append("quality", quality);
		tob.append("state", state);
		tob.append("BOOK : ", book.toString());
		tob.append("USER : ", user.toString());

		return tob.toString();

	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getBeginRead() {
		return beginRead;
	}

	public void setBeginRead(Date beginRead) {
		this.beginRead = beginRead;
	}

	public Date getEndRead() {
		return endRead;
	}

	public void setEndRead(Date endRead) {
		this.endRead = endRead;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

}
