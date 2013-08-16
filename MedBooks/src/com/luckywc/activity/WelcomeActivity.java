package com.luckywc.activity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.luckywc.Data.DataMgr;
import com.luckywc.medbooks.R;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class WelcomeActivity extends BaseActivity{

	private ListView lstView;
	private SimpleCursorAdapter cateAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		lstView=(ListView)findViewById(R.id.lstCategorys);
		lstView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 Cursor selItem=(Cursor) cateAdapter.getItem(arg2);
				 int idColIdx= selItem.getColumnIndex("_id");
				 int cateId=selItem.getInt(idColIdx);
				 Intent titles=new Intent(WelcomeActivity.this,TitleListActivity.class);
				 titles.putExtra("categoryId",cateId);
				 startActivity(titles);
			}			
		});
		InitAd();
		BindNav();		
	}
	
	private void BindNav(){
		DataMgr dm=new DataMgr(WelcomeActivity.this);		
		cateAdapter=new SimpleCursorAdapter(
				WelcomeActivity.this,
				R.layout.item_nav,
				dm.GetMedCategorys(),
				new String[]{"name"},new int[]{R.id.tvName}
		);	
		lstView.setAdapter(cateAdapter);
	}
}
