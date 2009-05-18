package com.google.code.booktogether.dao.ibatis;


import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.ibatis.sqlmap.client.event.RowHandler;

/**
 * 
 *
 */
public class XmlRowHandler implements RowHandler{

	public Document doc;
	public Element e;
	public SAXBuilder builder;

	public XmlRowHandler(String xmlRootName) {
		
		builder=new SAXBuilder();
		
		e=new Element(xmlRootName);
		doc=new Document();
		doc.setRootElement(e);
		
		//doc=new Document(new Element(xmlRootName));

	}


	
	@SuppressWarnings("unchecked")
	public void handleRow(Object xmlContent) {
		
		
		StringReader stringxml=new StringReader((String)xmlContent);

		try {
			Document xmldoc;	//xml����

			xmldoc = builder.build(stringxml);	//���ڿ��ε� xml������ XML�� ��ȯ

			Element boardroot=new Element(xmldoc.getRootElement().getName());	//'board' �̸����� Element �ϳ������

			List child_list=xmldoc.getRootElement().getChildren();	//num,title,content,input_date Element List�� �ֱ�

			
			for(int i=0;i<child_list.size();i++){
				
				Element child=new Element(((Element)child_list.get(i)).getName());
				
				child.addContent(xmldoc.getRootElement().getChildText(child.getName()));
				
				boardroot.addContent(child);
				
			}
			
			e.addContent(boardroot);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Document getDocument(){

		return doc;
	}

	
	public void ConsolPrintOutput(){

		try{
			XMLOutputter outp=new XMLOutputter();

			Format format=Format.getCompactFormat();

			outp.setFormat(format.setIndent("    "));
			outp.setFormat(format.setEncoding("euc-kr"));
			outp.output(doc, System.out);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void FilePrintOutput(){
		
		try{
			XMLOutputter outp=new XMLOutputter();
			
			Format format=Format.getCompactFormat();
			
			outp.setFormat(format.setIndent("    "));
			outp.setFormat(format.setEncoding("euc-kr"));
			
			FileWriter file=new FileWriter(new File("���� ��� ��� ����ֱ�"));
			outp.output(doc, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
