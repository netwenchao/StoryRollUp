package com.luckywc.activity;

import com.luckywc.medbooks.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Intent tabs=new Intent(this,TabMainActivity.class);
		startActivity(tabs);
	}

}
