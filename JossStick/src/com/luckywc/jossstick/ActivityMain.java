package com.luckywc.jossstick;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ActivityMain extends Activity{

	PopupWindow ppw;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((ImageButton)findViewById(R.id.btn_lot)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(ActivityMain.this,StartPage.class);
				startActivity(it);
			}			
		});
		
		((ImageButton)findViewById(R.id.btn_word_analysis)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(ActivityMain.this,WordAnalysisActivity.class);
				startActivity(it);
			}			
		});
		
		((ImageButton)findViewById(R.id.btn_zhougong)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(ActivityMain.this,JieMengActivity.class);
				startActivity(it);
			}			
		});
		
		((ImageButton)findViewById(R.id.btnLanch)).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowAbout();
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
	
	private void ShowAbout(){
		View detailView=getLayoutInflater().inflate(R.layout.activity_about,null);
		int width=getWindowManager().getDefaultDisplay().getWidth();
		int height=(int)(getWindowManager().getDefaultDisplay().getHeight());		
		((Button)detailView.findViewById(R.id.btnClose)).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ppw.dismiss();
			}
			
		});
		ppw=new PopupWindow(detailView,width,height);
		ppw.setOutsideTouchable(true);
		ColorDrawable dw=new ColorDrawable(-00000);
		ppw.setBackgroundDrawable(dw);
		ppw.setFocusable(true);
		ppw.update();
		ppw.showAtLocation(findViewById(R.id.rlmainContainer), BIND_AUTO_CREATE, 0,0);
	}

}
