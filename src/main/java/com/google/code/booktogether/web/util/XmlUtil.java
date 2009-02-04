package com.google.code.booktogether.web.util;

import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.ResourceUtils;

/**
 * SQL 관리하는 XML을 파싱하는 유틸
 * @author ParkHaeCheol
 *
 */
public class XmlUtil {
	
	private static HashMap<String,String> map = new HashMap<String,String>();

	public static XmlUtil instance = null;
	public static XmlUtil getInstance(){
		if( instance == null ){
			instance = new XmlUtil();
		}
		return instance;
	}
	
	private XmlUtil(){
		load();
	}
	
	@SuppressWarnings("unchecked")
	private void load(){
		
		map.clear();
		
		SAXBuilder builder;
		Document doc;
		Element root;
		
		builder = new SAXBuilder();
		doc = null;
		try	{
			doc = builder.build(ResourceUtils.getFile("classpath:booksqls.xml"));
		}catch(Exception ioe){
			ioe.printStackTrace();
		}
			
		root = doc.getRootElement();
		
		List<Element> sqlElement = root.getChildren("sql");
		for(int i = 0; i < sqlElement.size(); i++) {
			Element e = sqlElement.get(i);
			map.put(e.getAttributeValue("name"), e.getChildText("query"));
		}
	}

	public String getSQL(String sqlKey)	{
		String sql = "";
		if( map.containsKey(sqlKey) ){
			sql = (String)map.get(sqlKey);
		}
		return sql;
	}
	
	public void reload(){
		load();
	}
}
