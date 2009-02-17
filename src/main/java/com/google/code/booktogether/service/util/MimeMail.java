package com.google.code.booktogether.service.util;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.web.domain.MailInfo;

/**
 * 이메일 보내기
 * 
 * @author ParkHaeCheol
 * 
 */
@Component("sendEmailTo")
public class MimeMail {

	private JavaMailSender mailSender;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	public void setMailSender(JavaMailSender mailSender) {

		this.mailSender = mailSender;

	}

	/**
	 * 첨부파일이 없는 이메일 보내기
	 * 
	 * @param user
	 * @return
	 */
	public boolean sendEmailWithoutAttach(MailInfo mailInfo) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					false, mailInfo.getEncode());

			messageHelper.setSubject(mailInfo.getSubject());

			String htmlContent = mailInfo.getContent();

			messageHelper.setText(htmlContent, true);
			messageHelper.setFrom(mailInfo.getFrom());
			messageHelper.setTo(mailInfo.getTo());

		} catch (Exception e) {
			throw new BooktogetherException("첨부파일없는 이메일 생성과정중 에러발생", e);
		}

		try {
			mailSender.send(message);
		} catch (MailException e) {
			throw new BooktogetherException("첨부파일없는 이메일 전송과정중 에러발생", e);
		}

		return true;
	}

	/**
	 * 첨부파일이 있는 이메일 보내기
	 * 
	 * @param user
	 * @return
	 */
	public boolean sendEmailWithAttach(MailInfo mailInfo) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, mailInfo.getEncode());

			messageHelper.setSubject(mailInfo.getSubject());

			String htmlContent = mailInfo.getContent();

			messageHelper.setText(htmlContent, true);
			messageHelper.setFrom(mailInfo.getFrom());
			messageHelper.setTo(mailInfo.getTo());

			for (String file : mailInfo.getFiles()) {
				DataSource dataSource = new FileDataSource(file);
				messageHelper.addAttachment(MimeUtility.encodeText(dataSource
						.getName(), mailInfo.getEncode(), "B"), dataSource);
			}

		} catch (Exception e) {
			throw new BooktogetherException("첨부파일있는 이메일 생성과정중 에러발생", e);
		}

		try {
			mailSender.send(message);
		} catch (MailException e) {
			throw new BooktogetherException("첨부파일있는 이메일 전송과정중 에러발생", e);
		}

		return true;
	}

}