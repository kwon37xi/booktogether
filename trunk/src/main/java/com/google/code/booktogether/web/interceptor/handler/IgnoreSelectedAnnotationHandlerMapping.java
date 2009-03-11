package com.google.code.booktogether.web.interceptor.handler;

import java.util.ArrayList;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;


public class IgnoreSelectedAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {

	private ArrayList<String> a;

	public IgnoreSelectedAnnotationHandlerMapping() {
	}

	public void setUrls(ArrayList<String> arraylist) {
		a = arraylist;
	}

	public String[] getFiltered(String as[]) {
		
		if (as == null) {
			return null;
		}

		ArrayList<String> arraylist = new ArrayList<String>();

		for (String s : as) {

			if (!a.contains(s)) {
				arraylist.add(s);
			}

		}

		return (String[]) arraylist.toArray(new String[arraylist.size()]);
		
	}

	protected String[] determineUrlsForHandler(String s) {
		return getFiltered(super.determineUrlsForHandler(s));
	}

}