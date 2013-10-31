package com.netwc.Activities;

import java.util.List;

import com.netwc.joke.JokeListActivity;
import com.netwc.joke.R;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TabActivityMain extends Activity{

	private ViewPager mPager;
	private List<View> tabViews;
	private ImageView cursor;
	private TextView t1,t2,t3;
	private int offSet=0;
	private int currIndex=0;
	private LocalActivityManager manager=null;
	private final Context ctx=TabActivityMain.this;
	private TabHost tabHost;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ui_main);
		tabHost=(TabHost)findViewById(R.id.tabhost);
		tabHost.addTab(tabHost.newTabSpec("A").setIndicator("").setContent(new Intent(this,JokeListActivity.class)));
		tabHost.setCurrentTab(0);
	}
	
}
