package com.google.code.booktogether.service.util;

import java.util.Random;

/**
 * 임시 비밀번호 발급
 * @author ParkHaeCheol
 */
public class GenerateTempPassword {

	/**
	 * 임시 비밀번호 발급
	 * @return 임시 비밀번호 7자리
	 */
	public static String generatePassword(){
		
		Random random=new Random(System.currentTimeMillis());

		String[] char_key={"a","b","c","d","e","f","g","h","i","j","k"
							,"l","n","m","o","p","q","r","s","t","u","v"
							,"w","x","y","z","0","1","2","3","4","5","6"
							,"7","8","9","A","B","C","D","E","F","G","H"
							,"I","J","K","L","M","O","P","Q","R","S","T"
							,"U","V","W","X","Y","Z","!","@","#","$","$"
							,"%","^","&"};

		String temp_pw="";

		for(int i=0;i<7;i++){

			int ran=random.nextInt(char_key.length);

			temp_pw+=char_key[ran];

		}
		
		return temp_pw;
		
	}

}
