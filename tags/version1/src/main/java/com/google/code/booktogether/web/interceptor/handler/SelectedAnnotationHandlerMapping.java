package com.google.code.booktogether.web.interceptor.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class SelectedAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {

	private List<String> urlList;

	public SelectedAnnotationHandlerMapping() {

	}

	public void setUrls(List<String> arrayList) {
		urlList = arrayList;
	}

	public String[] getFiltered(String as[]) {

		if (as == null) {
			return null;
		}

		List<String> arraylist = new ArrayList<String>();

		for (String s : as) {

			if (urlList.contains(s)) {
				arraylist.add(s);
			}

		}

		return (String[]) arraylist.toArray(new String[arraylist.size()]);
	}

	@Override
	protected String[] determineUrlsForHandler(String s) {
		return getFiltered(super.determineUrlsForHandler(s));
	}

}