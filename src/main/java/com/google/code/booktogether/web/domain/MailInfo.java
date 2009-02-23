package com.google.code.booktogether.web.domain;


public class MailInfo {
	
	/**
	 * 받는사람
	 */
	private String[] to=null;
	
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
	private String encode="utf-8";
	
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
