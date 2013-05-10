package com.luckywc.jossstick;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_start_page);
		
		LinearLayout layout=(LinearLayout)findViewById(R.id.AdLinearLayout);		
		AdViewTargeting.setRunMode(RunMode.NORMAL); 
		AdViewTargeting.setUpdateMode(UpdateMode.EVERYTIME); 
		AdViewLayout adViewLayout = new AdViewLayout(this, "SDK20130927090430cbq70w7q3d4kvax"); 
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

		((ImageView)findViewById(R.id.mian_qiuqian)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartPage.this,GetLotActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.activity_open, R.anim.activity_close);
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_page, menu);
		return true;
	}

}
