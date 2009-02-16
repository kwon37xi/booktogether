package com.google.code.booktogether.dao.sqlparser.impl;

import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.ResourceUtils;

import com.google.code.booktogether.dao.sqlparser.SqlParser;

/**
 * SQL 관리하는 XML을 파싱하는 유틸
 * @author ParkHaeCheol
 *
 */
public class SqlParserXmlImpl implements SqlParser{
	
	private static HashMap<String,HashMap<String,String>> xml = new HashMap<String,HashMap<String,String>>();
	
	private List<String> filenames;
	
	
	//classpath:sqls/파일명
	public SqlParserXmlImpl(List<String> filenames){
		
		this.filenames=filenames;
		
		load();
		
	}

	@SuppressWarnings("unchecked")
	private void load(){
		
		SAXBuilder builder= new SAXBuilder();
		
		for(String filename : filenames){
			
			Document doc = null;
			Element root =null;
			HashMap<String,String> map =null;
			String rootName="";
			
			map = new HashMap<String,String>();
			map.clear();
			
			try	{
				doc = builder.build(ResourceUtils.getFile(filename));
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

	@Override
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
	
}
