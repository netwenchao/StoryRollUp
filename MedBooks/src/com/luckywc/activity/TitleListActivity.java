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
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TitleListActivity extends ListActivity{	
	private PopupWindow ppw;
	private TextView tvTitle;
	private TextView tvContent;
	
	private void ShowDetailDialog(String title,String content){
		View detailView=getLayoutInflater().inflate(R.layout.activity_detail,null);
		int width=getWindowManager().getDefaultDisplay().getWidth();
		int height=(int)(getWindowManager().getDefaultDisplay().getHeight());
		tvTitle=((TextView)detailView.findViewById(R.id.tv_detail_Title));
		tvTitle.setText(title);
		tvContent=((TextView)detailView.findViewById(R.id.tv_detail_content));
		tvContent.setText(content);		
				
		((Button)detailView.findViewById(R.id.btn_Detail_Share)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Intent.ACTION_SEND);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra(Intent.EXTRA_TEXT,tvContent.getText());
				intent.setType("text/plain");
				startActivity(Intent.createChooser(intent,getString(R.string.dialog_share)));
			}		
		});
		
		((Button)detailView.findViewById(R.id.btn_Detail_Close)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {ppw.dismiss();}		
		});
		
		ppw=new PopupWindow(detailView,width,height);
		ppw.setOutsideTouchable(false);
		ColorDrawable dw=new ColorDrawable(-00000);
		ppw.setBackgroundDrawable(dw);
		ppw.setFocusable(true);
		ppw.update();
		ppw.showAtLocation(findViewById(R.id.titleContainer), BIND_AUTO_CREATE, 0,0);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		HashMap<String,String> itemHashMap=(HashMap<String,String>)l.getItemAtPosition(position);
		if(itemHashMap==null) return;	
		ShowDetailDialog(itemHashMap.get("Title"),itemHashMap.get("Content"));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_titles_ctnav);
		String filePath=getIntent().getStringExtra("fileName");
		try {
			ArrayList<HashMap<String,String>> t=GetContent(filePath);
			SimpleAdapter sa=new SimpleAdapter(this,t,R.layout.title_item,new String[]{"Title"},new int[]{R.id.title_item_title});
			setListAdapter(sa);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((Button)findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}			
		});
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
