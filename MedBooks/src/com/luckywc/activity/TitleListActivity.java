package com.luckywc.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.NullCipher;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.AttributeList;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;

import com.luckywc.medbooks.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TitleListActivity extends ListActivity{

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		HashMap<String,String> itemHashMap=(HashMap<String,String>)l.getItemAtPosition(position);
		if(itemHashMap==null) return;
		Intent detail=new Intent(this,DetailActivity.class);		
		detail.putExtra("content",itemHashMap.get("Content"));
		startActivity(detail);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_titles);
		String filePath=getIntent().getStringExtra("fileName");
		try {
			ArrayList<HashMap<String,String>> t=GetContent(filePath);
			SimpleAdapter sa=new SimpleAdapter(this,t,R.layout.title_item,new String[]{"Title"},new int[]{R.id.title_item_title});
			setListAdapter(sa);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private ArrayList<HashMap<String,String>> GetContent(String fileName) throws IOException{		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser;
		try {
			saxParser = spf.newSAXParser();
			 //´´½¨½âÎöÆ÷
			//saxParser.setProperty("http://xml.org/sax/features/namespaces",true);
			InputStream xmlStream=getAssets().open(fileName);
			TitleContentParser handler=new TitleContentParser();
			saxParser.parse(xmlStream,handler);
			xmlStream.close();
			return handler.GetXmlData();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<HashMap<String,String>>();
	}
	
	@SuppressWarnings("deprecation")
	public class TitleContentParser extends HandlerBase{
		ArrayList<HashMap<String,String>> xmldata;
		public String tagName=null;		
		private String dataType=null;		
		private HashMap<String,String> current;
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			if(tagName!=null){
				String dataString=new String(ch,start,length);
				if(tagName=="Content"){
					if(dataString.trim().length()>0)
						current.put("Content",dataString);
				}else if(tagName=="Title"){
					if(dataString.trim().length()>0)
						current.put("Title",dataString);									
				}
			}
		}

		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			xmldata=new ArrayList<HashMap<String,String>>();
		}

		@Override
		public void startElement(String name, AttributeList attributes)
				throws SAXException {
			tagName=name;
			if(tagName=="ArticalInfo"){
				if(null!=current)
					xmldata.add(current);
				current=new HashMap<String,String>();
			}
			Log.v("Tag",tagName);
		}
		
		public ArrayList<HashMap<String,String>> GetXmlData(){
			return xmldata;
		}
	
	}
	
}
