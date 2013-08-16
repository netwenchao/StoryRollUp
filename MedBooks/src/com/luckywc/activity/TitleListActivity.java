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

import com.luckywc.Data.DataMgr;
import com.luckywc.medbooks.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TitleListActivity extends ListActivity{	
	private PopupWindow ppw;
	private TextView tvTitle;
	private TextView tvContent;
	private SimpleCursorAdapter cateAdapter; 
	
	private void ShowDetailDialog(String title,String content){
		View detailView=getLayoutInflater().inflate(R.layout.activity_detail,null);
		int width=getWindowManager().getDefaultDisplay().getWidth();
		int height=(int)(getWindowManager().getDefaultDisplay().getHeight());
		tvTitle=((TextView)detailView.findViewById(R.id.tv_detail_Title));
		tvTitle.setText(title);
		tvContent=((TextView)detailView.findViewById(R.id.tv_detail_content));
		tvContent.setText(content);		
			/*	
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
		RelativeLayout container=((RelativeLayout)detailView.findViewById(R.id.windowContainer));
		LinearLayout.LayoutParams param=(LinearLayout.LayoutParams)container.getLayoutParams();
		param.height=(int)(height/2);
		container.setLayoutParams(param);
		*/
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
		Cursor selItem=(Cursor)cateAdapter.getItem(position);
		int titleCol=selItem.getColumnIndex("title");
		int contentCol=selItem.getColumnIndex("articalInfo");
		ShowDetailDialog(selItem.getString(titleCol),selItem.getString(contentCol));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_titles_ctnav);
		int categoryId=getIntent().getIntExtra("categoryId",1);
		DataMgr dm=new DataMgr(TitleListActivity.this);
		cateAdapter=new SimpleCursorAdapter(TitleListActivity.this,R.layout.activity_detail,dm.GetContentByCategory(categoryId),new String[]{"title","articalInfo","isfav"},new int[]{R.id.tv_detail_Title,R.id.tv_detail_content,R.id.rbFav});			
		setListAdapter(cateAdapter);
		((Button)findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}			
		});
	}

}
