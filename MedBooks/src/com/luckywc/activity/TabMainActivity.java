package com.luckywc.activity;

import com.luckywc.medbooks.R;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabMainActivity extends ActivityGroup{
	private TabHost tabHost;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabHost=(TabHost)findViewById(R.id.edit_item_tab_host);
		tabHost.setup(getLocalActivityManager());
		TabHost.TabSpec localTabSpec1 = tabHost.newTabSpec("WelcomePage");
	    localTabSpec1.setContent(new Intent(this, Main.class));
	    localTabSpec1.setIndicator("偏方主页", getResources().getDrawable(2130837699));
	    tabHost.addTab(localTabSpec1);
	    TabHost.TabSpec localTabSpec2 = tabHost.newTabSpec("WelcomePage_youmi");
	    localTabSpec2.setContent(new Intent(this, MainApp.class));
	    localTabSpec2.setIndicator("饮食搭配", getResources().getDrawable(2130837700));
	    tabHost.addTab(localTabSpec2);
	    TabHost.TabSpec localTabSpec3 = tabHost.newTabSpec("WelcomePage_waps");
	    localTabSpec3.setContent(new Intent(this, WelcomeRxRecipeQuery.class));
	    localTabSpec3.setIndicator("药方查询", getResources().getDrawable(2130837698));
	    tabHost.addTab(localTabSpec3);
	    TabHost.TabSpec localTabSpec4 = tabHost.newTabSpec("Weather");
	    localTabSpec4.setContent(new Intent(this, Weather.class));
	    localTabSpec4.setIndicator("相关设置", getResources().getDrawable(2130837701));
	    tabHost.addTab(localTabSpec4);
	    tabHost.setCurrentTab(0);
	}
}
