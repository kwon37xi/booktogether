package com.google.code.booktogether.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.code.booktogether.service.CurrentDateService;

@Service("currentDateService")
public class CurrentDateServiceImpl implements CurrentDateService {

	private Logger log=Logger.getLogger(this.getClass());

	public Date getCurrentDate(){
		
		if(log.isInfoEnabled()){
			log.info("시작");
		}
		
		Date date=new Date();
		
		if(log.isInfoEnabled()){
			log.info("끝");
		}
		return date;
	}

}
