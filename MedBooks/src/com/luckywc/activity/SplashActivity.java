package com.luckywc.activity;

import com.luckywc.medbooks.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		 (new Handler()).postDelayed(new Runnable(){			
			@Override
			public void run() {
				Intent tabs=new Intent(SplashActivity.this,TabMainActivity.class);
				startActivity(tabs);
				SplashActivity.this.finish();
			}
		 },3000);		 
	}
}
