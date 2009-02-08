package com.google.code.booktogether.service.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordAuthenticator{

	public static byte[] generatorSalt(){
		
		//새로운 salt를 생성한다.
		SecureRandom random = new SecureRandom();
		
		byte[] salt = new byte[12];
		
		random.nextBytes(salt);
		
		return salt;
	}

	public static byte[] createPasswordDigest(String password,byte[] salt) throws Exception{

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(salt);
		md.update(password.getBytes("UTF8"));
		byte[] digest = md.digest();

		return digest;

	}
}

