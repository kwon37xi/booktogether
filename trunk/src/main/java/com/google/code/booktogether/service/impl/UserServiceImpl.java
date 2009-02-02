package com.google.code.booktogether.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.google.code.booktogether.service.UserService;

@Service("currentDateService")
public class UserServiceImpl implements UserService {

	public Date getCurrentDate(){
		return new Date();
	}

}
