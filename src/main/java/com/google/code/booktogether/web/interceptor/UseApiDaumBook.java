package com.google.code.booktogether.web.interceptor;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;


/**
 * API를 위해서 임시 클래스
 * @author ParkHaeCheol
 *
 */
public class UseApiDaumBook {

	/**
	 * 작성자 : microflower
	 */
	private static final long serialVersionUID = 1L;

	//네이버 APIEKY
	private final String DAUM_API_KEY="4cf1b8e1876394d1334d699b0568f32ef762db19";

	//다음  검색 주소
	private final String DAUM_SEARCH_URL="http://apis.daum.net/search/book";

	private SAXBuilder builder=null;
	private StringReader stringxml=null;
	private Document xmldoc=null;
	private StringBuffer xmlcontent=null;

	private int totalcount;
	/**
	 * 주소 요청하고 output에 맞게 받기
	 * 인코딩 UTF-8
	 */
	public Book viewBook(String isbn){

		ConnectURL(isbn,"isbn",1);

		//네이버 XML파서
		xmlParser(xmlcontent);

		//item 파서
		Book book=xmlBookParser();

		return book;
	}
	
	public int getTotalCount(){
		return totalcount;
	}

	public List<Book> searchBook(String query, String searchType,int pageno){

		try {
			query=URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		ConnectURL(query, searchType, pageno);

		//네이버 XML파서
		xmlParser(xmlcontent);
		
		//header파서(총갯수)
		xmlBookHeaderParse();
		
		//item 파서
		List<Book> booklist=xmlBookListParser();

		return booklist;
	}

	private void ConnectURL(String query,String searchType,int pageno){

		String params="";

		//네이버
		params="?q="+query;
		params+="&searchType="+searchType;
		params+="&target="+"meta";
		params+="&pageno="+"meta";
		params+="&result="+20;
		params+="&sort="+"accu";
		params+="&pageno="+pageno;
		params+="&output="+"xml";
		params+="&apikey="+DAUM_API_KEY;

		//URL클래스를 통한 요청과 응답 송수신 변수들
		BufferedReader in = null;
		String inputLine="";
		
		try{

			URL openapi=null;

			//주소 만들기
			openapi= new URL(DAUM_SEARCH_URL+params);

			in = new BufferedReader(new InputStreamReader(openapi.openStream(),"UTF-8"));

			xmlcontent=new StringBuffer();

			while ((inputLine = in.readLine()) != null){
				xmlcontent.append(inputLine);
				System.out.println(inputLine);
			}

			in.close();

		}catch(Exception e){

			e.printStackTrace();

		}finally {
			in=null;
		}
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


	//네이버책(조회시)
	@SuppressWarnings("unchecked")
	private Book xmlBookParser(){

		List item_list=xmldoc.getRootElement().getChildren("item");

		Element item=null;

		Book book=new Book();

		for(int i=0;i<item_list.size();i++){

			item=(Element)item_list.get(i);

			book.setName(item.getChild("title").getText());
			book.setCorver(item.getChild("cover_s_url").getText());
			book.setPrice(item.getChild("list_price").getText());
			book.setPublish_comp(item.getChild("pub_nm").getText());
			book.setPublish_date(item.getChild("pub_date").getText());
			book.setDescription(item.getChild("description").getText());
			book.setCategory(item.getChild("category").getText());

			String translator_name=item.getChild("translator").getText();
			String isbn=item.getChild("isbn").getText();

			if(isbn.indexOf("<b>")!=-1){
				book.setISBN10(isbn.substring(3,13));
				book.setISBN13("");
			}else{
				book.setISBN10("");
				book.setISBN13(isbn);
			}


			Author[] authors;
			Author author=new Author();
			author.setName(item.getChild("author").getText());
			author.setAuthor_div(0);


			if(translator_name!=null && !translator_name.equals("")){
				authors=new Author[2];

				Author translator=new Author();
				translator.setName(translator_name);
				translator.setAuthor_div(1);

				authors[0]=author;
				authors[1]=translator;

			}else{
				authors=new Author[1];
				authors[0]=author;
			}

			book.setAuthors(authors);
		}
		return book;
	}

	//네이버책(검색시)
	@SuppressWarnings("unchecked")
	private List<Book> xmlBookListParser(){

		List item_list=xmldoc.getRootElement().getChildren("item");

		Element item=null;
		
		List<Book> booklist=new ArrayList<Book>();

		for(int i=0;i<item_list.size();i++){
			
			Book book=new Book();

			item=(Element)item_list.get(i);
			
			String title=item.getChild("title").getText();
			
			if(title.length()>13){
				book.setName(title.substring(0,14)+"...");
			}else{
				book.setName(title);
			}

			book.setCorver(item.getChild("cover_s_url").getText());
			book.setPublish_comp(item.getChild("pub_nm").getText());

			String isbn=item.getChild("isbn").getText();
			book.setISBN10(isbn);


			Author[] authors=new Author[1];
			
			Author author=new Author();
			author.setName(item.getChild("author").getText());
			author.setAuthor_div(0);
			authors[0]=author;

			book.setAuthors(authors);
			
			booklist.add(book);
		}
		
		return booklist;
		
	}
	
	private void xmlBookHeaderParse(){
		Element child=xmldoc.getRootElement();

		//title=child.getChildText("title");
		totalcount=Integer.parseInt(child.getChildText("totalCount"));
		//int result=child.getChildText("result");
		//sort=ParamUtil.parseInt(child.getChildText("sort"),0);
		//pageno=ParamUtil.parseInt(child.getChildText("pageno"),0);
	}



}