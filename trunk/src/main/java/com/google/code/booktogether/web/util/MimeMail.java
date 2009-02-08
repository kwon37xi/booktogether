package com.google.code.booktogether.web.util;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.google.code.booktogether.web.domain.MailInfo;

@Component("sendEmailTo")
public class MimeMail{ 

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender){

		this.mailSender=mailSender;

	}

	
	/**
	 * 첨부파일이 없는 이메일 보내기
	 * @param user
	 * @return
	 */
	public boolean sendEmailWithoutAttach(MailInfo mailInfo){
		
		MimeMessage message= mailSender.createMimeMessage();
		
		try{
			MimeMessageHelper messageHelper=new MimeMessageHelper(message, false, mailInfo.getEncode());
			
			messageHelper.setSubject(mailInfo.getSubject());
			
			String htmlContent=mailInfo.getContent();
			
			messageHelper.setText(htmlContent,true);
			messageHelper.setFrom(mailInfo.getFrom());
			messageHelper.setTo(mailInfo.getTo());
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		try{
			mailSender.send(message);
		}catch(MailException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * 첨부파일이 있는 이메일 보내기
	 * @param user
	 * @return
	 */
	public boolean sendEmailWithAttach(MailInfo mailInfo){
		
		MimeMessage message= mailSender.createMimeMessage();

		try{
			MimeMessageHelper messageHelper=new MimeMessageHelper(message, true, mailInfo.getEncode());
			
			messageHelper.setSubject(mailInfo.getSubject());
			
			String htmlContent=mailInfo.getContent();
			
			messageHelper.setText(htmlContent,true);
			messageHelper.setFrom(mailInfo.getFrom());
			messageHelper.setTo(mailInfo.getTo());
			
			for(String file: mailInfo.getFiles()){
				DataSource dataSource=new FileDataSource(file);
				messageHelper.addAttachment(MimeUtility.encodeText(dataSource.getName(),mailInfo.getEncode(),"B"), dataSource);
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		try{
			mailSender.send(message);
		}catch(MailException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

}