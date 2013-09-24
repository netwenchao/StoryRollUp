package com.luckywc.jossstick;

import java.util.ArrayList;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class JieMengActivity extends Activity{

	ArrayList<String> keywords;
	AutoCompleteTextView tv;
	TextView tvContent;
	private ScrollView sv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiemeng);
		DBManager manager=new DBManager(JieMengActivity.this);
		manager.OpenStickDb();
		keywords=manager.GetJieMengKeywords();
		manager.CloseStickDB();
		sv=(ScrollView)findViewById(R.id.svResult);
		tv=(AutoCompleteTextView)findViewById(R.id.autoKeywords);		
		ArrayAdapter adapter=new ArrayAdapter(JieMengActivity.this,android.R.layout.simple_dropdown_item_1line,keywords);
		tv.setAdapter(adapter);
		tv.setThreshold(1);
		
		tvContent=(TextView)findViewById(R.id.tvjieMengContent);		
		((Button)findViewById(R.id.btnSearch)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DBManager manager=new DBManager(JieMengActivity.this);
				manager.OpenStickDb();
				ArrayList<JieMengEntity> jieMengEntities=manager.GetJieMengResult(tv.getText().toString());
				manager.CloseStickDB();
				sv.setVisibility(View.VISIBLE);	
				String str="";
				for(int i=0;i<jieMengEntities.size();i++){
					str+="<font color=\"#FF0000\">"+jieMengEntities.get(i).Keyword+"</font>";
					str+="<br/>";
					str+=jieMengEntities.get(i).Content;
					str+="<br/><br/>";
				}
				tvContent.setText(Html.fromHtml(str));
				if(jieMengEntities.size()==0){
					((TextView)findViewById(R.id.tvjieMengContent)).setText(getString(R.string.label_nodata));
				}
			}			
		});		
		
		LinearLayout layout=(LinearLayout)findViewById(R.id.AdLinearLayout);		
		AdViewTargeting.setRunMode(RunMode.NORMAL); 
		AdViewTargeting.setUpdateMode(UpdateMode.DEFAULT); 
		AdViewLayout adViewLayout = new AdViewLayout(this, "SDK20131012100521kidb0cxesk8oste"); 
		//adViewLayout.setAdViewInterface(this); 
		adViewLayout.setAdViewInterface(new AdViewInterface(){

			@Override
			public void onClickAd() {
				// TODO Auto-generated method stub
				Log.v("AD","Clicked");
			}

			@Override
			public void onDisplayAd() {
				// TODO Auto-generated method stub
				Log.v("AD","Displayed");
			}
			
		});
		layout.addView(adViewLayout); 
		layout.invalidate();
	}

}
