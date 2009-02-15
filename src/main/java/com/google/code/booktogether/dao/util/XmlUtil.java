package com.google.code.booktogether.dao.util;

import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * SQL 관리하는 XML을 파싱하는 유틸
 * @author ParkHaeCheol
 *
 */
@Component("XmlUtil")
public class XmlUtil {
	
	private static HashMap<String,HashMap<String,String>> xml = new HashMap<String,HashMap<String,String>>();
	

	public XmlUtil(){
		load();
	}
	
	@SuppressWarnings("unchecked")
	private void load(){
		
		
		SAXBuilder builder= new SAXBuilder();
		
		String[] sqlXmlFilenames={"booksqls.xml","usersqls.xml","gradesqls.xml","reviewsqls.xml","bookmarksqls.xml"};
		
		for(String filename : sqlXmlFilenames){
			
			Document doc = null;
			Element root =null;
			HashMap<String,String> map =null;
			String rootName="";
			
			map = new HashMap<String,String>();
			map.clear();
			
			try	{
				doc = builder.build(ResourceUtils.getFile("classpath:sqls/"+filename));
			}catch(Exception ioe){
				ioe.printStackTrace();
			}
			
			root = doc.getRootElement();
			rootName=root.getAttributeValue("name");
			
			List<Element> sqlElement = root.getChildren("sql");
			
			for(int i = 0; i < sqlElement.size(); i++) {
				Element e = sqlElement.get(i);
				map.put(e.getAttributeValue("name"), e.getChildText("query"));
			}
			
			xml.put(rootName, map);
			
		}
	}

	public String getSQL(String xmlname,String sqlKey)	{
		String sql = "";
		if(xml.containsKey(xmlname)){
			
			HashMap<String,String> map =xml.get(xmlname);
			
			if( map.containsKey(sqlKey) ){
				sql = (String)map.get(sqlKey);
			}
		}
		
		return sql;
	}
	
	public void reload(){
		load();
	}
}
