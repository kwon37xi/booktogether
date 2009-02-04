package com.google.code.booktogether.web.interceptor;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.google.code.booktogether.web.util.ParamUtil;


/**
 * 아직 사용하지 않음
 * API를 위해서 임시 클래스
 * @author ParkHaeCheol
 *
 */
public class ImageTextRequestURLServlet extends HttpServlet {

	/**
	 * 작성자 : microflower
	 * 작성일 : 09.01.21
	 * 수정일 : 
	 */
	private static final long serialVersionUID = 1L;

	//네이버 APIEKY
	private final String NAVER_API_KEY="ab46a1f5b917bff198e97e15c6968bfb";

	//네이버 이미지 검색 주소
	private final String N_IMAGE_URL="http://openapi.naver.com/search";

	private SAXBuilder builder=null;
	private StringReader stringxml=null;
	private Document xmldoc=null;
	private String[] filenames;

	/**
	 * 주소 요청하고 output에 맞게 받기
	 * 인코딩 UTF-8
	 */
	private void execute(HttpServletRequest request, HttpServletResponse response){

		//Request UTF-8로 인코딩
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		//입력문자열
		String inputText=ParamUtil.validStringParam(request, "inputText", " ");

		//파라미터 세팅(문자열 또는 숫자로 변환)-유효성 검사

		//bg,inner
		String innerText=ParamUtil.validStringParam(request, "isInnerText", "bg");

		//seq, random
		String random=ParamUtil.validStringParam(request, "isRandom", "seq");


		//이미지 검색 대상
		String word=ParamUtil.validStringParam(request, "s_word", "");

		//폰트 크기
		int fontsize=ParamUtil.validIntegerParam(request, "fontsize", 200);

		//폰트색상
		int fontcolor=ParamUtil.validIntegerParam(request, "fontcolor", 0);

		String params="";


		//word같은 경우 한글일 가능성이 있으므로 URL주소체계인 
		//URLEncoder.encode()를 이용
		//ex) 한 -> %EF 로 변환
		try {
			word=URLEncoder.encode(word, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		//네이버
		params="?query="+word;
		params+="&start="+1;
		params+="&sort="+"sim";
		params+="&display="+50;
		params+="&target="+"image";
		params+="&key="+NAVER_API_KEY;
		
		try {
			inputText=new String(inputText.getBytes("UTF-8"),"8859_1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}


		//URL클래스를 통한 요청과 응답 송수신 변수들
		BufferedReader in = null;
		String inputLine="";
		StringBuffer xmlcontent=null;

		try{

			URL openapi=null;

			//주소 만들기
			openapi= new URL(N_IMAGE_URL+params);

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
		xmlNaverParserThumbnail();
		
		String filename="";
		
		boolean isInnerText=innerText.equals("bg")? true: false;
		boolean isRandom=random.equals("random")? true: false;
		
		//순차적일때 38개 이상이 아니면 안된다.
		if(filenames.length<38 && !isRandom){
			
			filename="moreNeedImage.gif";
		
		}else{
			
			String real_path=getServletContext().getRealPath("/main/images/imagetext");
			
			long cur_time=System.currentTimeMillis();
			
			filename=""+cur_time+fontsize+fontcolor+".jpg";
			
			File file=new File(real_path+File.separatorChar+filename);
			
			while(file.exists()){
				filename="easy"+filename;
			}
			
			
		}
		
		//생성된 이미지 경로
		String image_url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/main/images/imagetext/"+filename;
		
		request.setAttribute("image_url",image_url );

		try {
			RequestDispatcher view=getServletContext().getRequestDispatcher("/main/imagetext.jsp" );
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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


	//네이버
	@SuppressWarnings("unchecked")
	private void xmlNaverParserThumbnail(){

		List item_list=xmldoc.getRootElement().getChild("channel").getChildren("item");
		Element item=null;

		filenames=new String[item_list.size()];

		for(int i=0;i<item_list.size();i++){

			item=(Element)item_list.get(i);

			filenames[i]=item.getChild("thumbnail").getText();

		}
	}



	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		try {
			execute(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}