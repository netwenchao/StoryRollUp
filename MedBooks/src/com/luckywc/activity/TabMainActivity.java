package com.luckywc.activity;

import com.luckywc.medbooks.R;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabMainActivity extends ActivityGroup{
	private TabHost tabHost;
	
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabHost=(TabHost)findViewById(R.id.edit_item_tab_host);
		tabHost.setup(getLocalActivityManager());
		TabHost.TabSpec welComTab = tabHost.newTabSpec("TabWelcome");
		welComTab.setContent(new Intent(this, WelcomeActivity.class));
		welComTab.setIndicator(getString(R.string.tabName_Welcome), getResources().getDrawable(R.drawable.tabhost1));
	    tabHost.addTab(welComTab);
	    
	    TabHost.TabSpec tabFood = tabHost.newTabSpec("TabFood");
	    tabFood.setContent(new Intent(this, FoodActivity.class));
	    tabFood.setIndicator(getString(R.string.tabName_Food), getResources().getDrawable(R.drawable.tabhost2));
	    tabHost.addTab(tabFood);
	    
	    TabHost.TabSpec searchTab = tabHost.newTabSpec("TabSearch");
	    searchTab.setContent(new Intent(this, SearchActivity.class));
	    searchTab.setIndicator(getString(R.string.tabName_Search), getResources().getDrawable(R.drawable.tabhost3));
	    tabHost.addTab(searchTab);
	    
	    TabHost.TabSpec tabSetting = tabHost.newTabSpec("TabSetting");
	    tabSetting.setContent(new Intent(this, SettingActivity.class));
	    tabSetting.setIndicator(getString(R.string.tabName_Setting), getResources().getDrawable(R.drawable.tabhost4));
	    tabHost.addTab(tabSetting);
	    
	    tabHost.setCurrentTab(0);
	}
}
