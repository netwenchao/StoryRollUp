package com.netwc.joke;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainAcvitity extends ActivityGroup{

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_page);
		TabHost tabHost=(TabHost)findViewById(R.id.tabhost);
		tabHost.setup(getLocalActivityManager());
		
		TabHost.TabSpec tcWebView=tabHost.newTabSpec("localJoke");
		tcWebView.setContent(new Intent(this,JokeActivity.class));
		tcWebView.setIndicator(getString(R.string.tab_spec_daily));
		tabHost.addTab(tcWebView);
		
		TabHost.TabSpec tabFood = tabHost.newTabSpec("dailyJoke");
	    tabFood.setContent(new Intent(this, JokeDailyActivity.class));
	    tabFood.setIndicator(getString(R.string.tab_spec_local));
	    tabHost.addTab(tabFood);
		
	    tabHost.setCurrentTab(0);
	}

}
