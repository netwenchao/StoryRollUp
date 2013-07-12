package com.luckywc.activity;

import com.luckywc.medbooks.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		TextView tv=(TextView)findViewById(R.id.tv_detail_content);
		Bundle bdl= getIntent().getExtras();
		String fileContent=bdl.get("content").toString();
		tv.setText(fileContent);
		Dialog dg=new Dialog(this);
		dg.show();
	}
}
