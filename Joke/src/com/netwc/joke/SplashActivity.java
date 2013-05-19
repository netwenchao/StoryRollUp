package com.netwc.joke;

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
		(new Handler()).postDelayed(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent mainPage=new Intent(SplashActivity.this,MainAcvitity.class);
				startActivity(mainPage);
				finish();
			}
		},3000);		
	}	
}
