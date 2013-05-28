package com.luckywc.todolist.provider;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LocalDataMgr {
	
	public void SaveFile(ArrayList<HashMap<String,String>> jokeData) throws ParserConfigurationException{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc=documentBuilder.newDocument();
		Element rootEle=doc.createElement("jokes");
		for(int i=0;i<jokeData.size();i++){
			Element item=doc.createElement("item");
			Element title=doc.createElement("title");
			Element content=doc.createElement("content");
			Element content=doc.createElement("dateadd");
		}
	}
}
