package com.netwc.joke;

import java.util.ArrayList;
import java.util.HashMap;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class JokeListActivity extends Activity{
    ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_main);
		this.mListView = ((ListView)findViewById(R.id.jokeList));
	    bindData2List(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return super.onMenuItemSelected(featureId, item);
	}
	

	  public void bindData2List(String[] paramArrayOfString)
	  {
	    try
	    {
	      ArrayList localArrayList = new ArrayList();
	      for (int i = 0; ; i++)
	      {
	        if (i >= paramArrayOfString.length)
	        {
	          SimpleAdapter localSimpleAdapter = new SimpleAdapter(this, localArrayList, R.layout.book_item, new String[] {"ItemTitle" }, new int[] {R.id.ItemTitle });
	          this.mListView.setAdapter(localSimpleAdapter);
	          return;
	        }	        
	        HashMap localHashMap = new HashMap();	        
	        localHashMap.put("ItemTitle", paramArrayOfString[i]);
	        localArrayList.add(localHashMap);
	      }
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	  }

}
