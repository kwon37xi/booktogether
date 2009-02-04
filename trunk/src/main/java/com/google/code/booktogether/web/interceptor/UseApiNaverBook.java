package com.google.code.booktogether.web.interceptor;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.google.code.booktogether.web.domain.Book;


/**
 * 아직 사용하지 않음
 * API를 위해서 임시 클래스
 * @author ParkHaeCheol
 *
 */
public class UseApiNaverBook {

	/**
	 * 작성자 : microflower
	 * 작성일 : 09.01.21
	 * 수정일 : 
	 */
	private static final long serialVersionUID = 1L;

	//네이버 APIEKY
	private final String DAUM_API_KEY="4cf1b8e1876394d1334d699b0568f32ef762db19";

	//다음  검색 주소
	private final String DAUM_SEARCH_URL="http://apis.daum.net/search/book";

	private SAXBuilder builder=null;
	private StringReader stringxml=null;
	private Document xmldoc=null;
	private Book book=null;

	/**
	 * 주소 요청하고 output에 맞게 받기
	 * 인코딩 UTF-8
	 */
	@SuppressWarnings("unused")
	private void execute(String isbn){

		String params="";

		//네이버
		params="?q="+isbn;
		params+="&searchType="+"isbn";
		params+="&target="+"meta";
		params+="&output="+"xml";
		params+="&key="+DAUM_API_KEY;

		//URL클래스를 통한 요청과 응답 송수신 변수들
		BufferedReader in = null;
		String inputLine="";
		StringBuffer xmlcontent=null;

		try{

			URL openapi=null;

			//주소 만들기
			openapi= new URL(DAUM_SEARCH_URL+params);

			in = new BufferedReader(new InputStreamReader(openapi.openStream(),"UTF-8"));

			xmlcontent=new StringBuffer();

			while ((inputLine = in.readLine()) != null){
				xmlcontent.append(inputLine);
			}

			in.close();

		}catch(Exception e){

			e.printStackTrace();

		}finally {
			in=null;
		}

		//네이버
		//XML파서
		xmlParser(xmlcontent);

		//item 파서
		xmlNaverBookParser();
		
		
	}

	private void xmlParser(StringBuffer xmlcontent){

		builder=new SAXBuilder();

		stringxml=new StringReader((String)xmlcontent.toString());

		try {
			xmldoc = builder.build(stringxml);
		}catch(Exception e){
			e.printStackTrace();
		}

	}


	//네이버책
	@SuppressWarnings("unchecked")
	private void xmlNaverBookParser(){

		List item_list=xmldoc.getRootElement().getChild("channel").getChildren("item");
		
		Element item=null;
		
		book=new Book();
		
		for(int i=0;i<item_list.size();i++){

			item=(Element)item_list.get(i);

			book.setName(item.getChild("title").getText());
			book.setCorver(item.getChild("image").getText());
			book.setPrice(Integer.parseInt(item.getChild("price").getText()));
			book.setPublish_comp(item.getChild("publisher").getText());
			book.setPublish_date(item.getChild("pubdate").getText());
			
			
			//book.setAuthor(item.getChild("title").getText());

		}
	}



}