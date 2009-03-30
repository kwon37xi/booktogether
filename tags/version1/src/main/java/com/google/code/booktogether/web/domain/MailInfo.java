package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

public class MailInfo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 받는사람
	 */
	private String[] to = null;

	/**
	 * 보내는 사람
	 */
	private String from;

	/**
	 * 제목
	 */
	private String subject;

	/**
	 * 내용
	 */
	private String content;

	/**
	 * 본문 인코딩 방식
	 */
	private String encode = "utf-8";

	/**
	 * 첨부파일 경로(절대경로)
	 */
	private String[] files;

	/**
	 * 받는 사람이름
	 */
	private String toName;

	/**
	 * 보내는 사람 이름
	 */
	private String fromName;

	@Override
	public boolean equals(Object o) {

		if (o instanceof MailInfo == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		MailInfo rhs = (MailInfo) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(to, rhs.getTo());
		equb.append(from, rhs.getFrom());
		equb.append(subject, rhs.getSubject());
		equb.append(content, rhs.getContent());
		equb.append(encode, rhs.getEncode());
		equb.append(files, rhs.getFiles());
		equb.append(toName, rhs.getToName());
		equb.append(fromName, rhs.getFromName());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(19, 113);
		hashcode.append(to);
		hashcode.append(from);
		hashcode.append(subject);
		hashcode.append(content);
		hashcode.append(encode);
		hashcode.append(files);
		hashcode.append(toName);
		hashcode.append(fromName);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		if (to != null) {
			for (String t : to) {
				tob.append("to", t);
			}
		}

		tob.append("from", from);
		tob.append("subject", subject);
		tob.append("content", content);
		tob.append("encode", encode);

		if (files != null) {
			for (String file : files) {
				tob.append("files", file);
			}
		}
		
		tob.append("toName", toName);
		tob.append("fromName", fromName);

		return tob.toString();

	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

}
