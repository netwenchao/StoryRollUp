package com.luckywc.jossstick;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class WordAnalysisActivity extends Activity{

	private static final String ILLEGAL_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*()+-/<>,.;:'{}[]\\|\"\"��������������������@#��%����������";
	private EditText txtArea;
	private ScrollView sv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_analysis);
		txtArea=(EditText)findViewById(R.id.word2Anys);
		sv=(ScrollView)findViewById(R.id.svResult);
		((Button)findViewById(R.id.btnAnys)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String txt=txtArea.getText().toString().trim();
				if(ILLEGAL_STRING.contains(txt) || (txt.length()!=3)){
					Toast.makeText(WordAnalysisActivity.this,getString(R.string.type_three_word),Toast.LENGTH_SHORT);
					return;
				}				
				
				DBManager dbManager=new DBManager(WordAnalysisActivity.this);
				dbManager.OpenStickDb();
				String [] wd=txt.split("");		
				WordAnalysisResult rslt=dbManager.GetAnalysisResult(wd.length>1?wd[1]:"",wd.length>2?wd[2]:"",wd.length>3?wd[3]:"");
				dbManager.CloseStickDB();
				
				sv.setVisibility(View.VISIBLE);				
				if(null!=rslt){					
					((TextView)findViewById(R.id.tv_title)).setText(rslt.Title);
					((TextView)findViewById(R.id.tv_content)).setText(rslt.Content);
					return;
				}
				((TextView)findViewById(R.id.tv_content)).setText(getString(R.string.label_nodata));				
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
