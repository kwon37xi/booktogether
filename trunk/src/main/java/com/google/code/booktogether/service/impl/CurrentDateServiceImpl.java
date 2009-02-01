package com.google.code.booktogether.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.google.code.booktogether.service.CurrentDateService;

@Service("currentDateService")
public class CurrentDateServiceImpl implements CurrentDateService {

	public Date getCurrentDate(){
		return new Date();
	}

}
