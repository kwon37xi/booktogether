package com.google.code.booktogether.web.util;

import javax.servlet.http.HttpServletRequest;

public class ParamUtil {


	/**
	 * 파라미터값을 문자열로 추출
	 * @param req
	 * @param param_name
	 * @param init
	 * @return
	 */
	public static String validStringParam(HttpServletRequest req, String param_name, String init){

		if(req.getParameter(param_name)==null || req.getParameter(param_name)==""){

		}else {
			init=req.getParameter(param_name);
		}
		return init; 

	}

	/**
	 * 파라미터값을 숫자로 추출
	 * @param req
	 * @param param_name
	 * @param init
	 * @return
	 */

	public static int validIntegerParam(HttpServletRequest req, String param_name, int init){

		if(req.getParameter(param_name)==null ||req.getParameter(param_name)==""){

		}else {
			init=Integer.parseInt(req.getParameter(param_name));
		}
		return init; 
	}



	/**
	 * 문자를 숫자로 변환하는것
	 * NULL일경우를 대비하여 만들었음
	 * @param value
	 * @param init 초기값 : 널일경우 init값으로 대처한다.
	 * @return
	 */
	public static int parseInt(String value, int init){

		if(value==null){
			return init;
		}else {

			try{
				init=Integer.parseInt(value);
			}catch(Exception e){
				e.printStackTrace();
				return init;
			}
		}
		return init;
	}
}
