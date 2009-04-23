package com.google.code.booktogether.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 저자 도메인
 * 
 * @author ParkHaeCheol
 * 
 */
@Entity
@Table(name="author")
public class Author extends BaseObject {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Column(name="idNum")
	private Integer idNum;

	/**
	 * 이름
	 */
	@Column(name="name")
	private String name;

	/**
	 * 구분, 1: 지은이, 2: 옮김, 3: 원저자
	 */
	@Column(name="author_div")
	private Integer authorDiv;

	/**
	 * 책 정보
	 */
	@ManyToOne
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

	public Integer getAuthorDiv() {
		return authorDiv;
	}

	public void setAuthorDiv(Integer authorDiv) {
		this.authorDiv = authorDiv;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof Author == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		Author rhs = (Author) o;

		// 비교 대상이 되는 값이 하나 뿐이더라도 EqualsBuilder를 사용하라.
		// 그렇지 않을 경우 비교 대상 값의 null 여부를 직접 일일이 검사해야 한다.

		// TestObject가 BaseObject를 상속한 다른 다른 클래스에서 상속 받았다면
		// 맨 처음에 .appendSuper(super.equals(rhs))를 추가한다.
		// equals()와 hashCode()는 다루는 필드가 동일해야 한다.

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(name, rhs.getName());
		equb.append(authorDiv, rhs.getAuthorDiv());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {
		// equals()와 hashCode()는 다루는 필드가 동일해야 한다.

		// 아래 상수는 0을 제외한 홀수로, 각 클래스별로 서로 다른 값으로 지정해주는 것이 좋다.
		// TestObject가 BaseObject를 상속한 다른 다른 클래스에서 상속 받았다면
		// 맨 처음에 .appendSuper(super.hashCode())를 추가한다.

		HashCodeBuilder hashcode = new HashCodeBuilder(17, 37);
		hashcode.append(idNum);
		hashcode.append(name);
		hashcode.append(authorDiv);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		// ToStringStyle은 출력 형태를 의미한다.
		// TestObject가 BaseObject를 상속한 다른 클래스에서 상속 받았다면
		// 맨 처음에 .appendSuper(super.toString())을 추가해준다.

		// ToStringBuilder에서 append("accountId", account.getId()) 처럼 다른 객체의 값을
		// 출력할 때는
		// 그 객체가 null인지 여부를 직접 검사한 뒤에 append해야 한다.
		// 그렇지 않으면 toString()에서 NullPointerException이 발생할 수도 있다.

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("name", name);
		tob.append("authorDiv", authorDiv);

		return tob.toString();

	}

}
