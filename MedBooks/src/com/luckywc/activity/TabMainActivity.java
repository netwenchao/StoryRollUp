package com.luckywc.activity;

import com.luckywc.medbooks.R;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TabHost;

public class TabMainActivity extends ActivityGroup{
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			exitApp();
		}
		return false;		
	}

	private void exitApp()
	  {
	    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
	    localBuilder.setIcon(2130837616);
	    localBuilder.setTitle("亲！");
	    localBuilder.setMessage("5555舍不得您走...祝您健康长寿！");
	    localBuilder.setPositiveButton("退出程序", new DialogInterface.OnClickListener()
	    {
	      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
	      {
	        
	    	  TabMainActivity.this.finish();
	      }
	    });
	    localBuilder.setNegativeButton("再看一会儿", new DialogInterface.OnClickListener()
	    {
	      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
	      {	        
	      }
	    });
	    localBuilder.create().show();
	  }
	
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
