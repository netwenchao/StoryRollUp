package com.netwc.joke;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class JokeListActivity extends Activity{		
	int[] Q = { R.array.Tfq, R.array.Tla, R.array.Tcr, R.array.Tet, R.array.Tjiating, R.array.Tyr, R.array.Tmr, R.array.Tzc, 
		R.array.Txy, R.array.Taft, R.array.Tgd, R.array.Txd, R.array.Tdn, R.array.Tzz, R.array.Tjs, R.array.Tjt, R.array.Tzj, R.array.Tmj, 
		R.array.Tgh, R.array.Tsf, R.array.Tty, R.array.Tjy, R.array.Tyl, R.array.Tgw, R.array.Twr };
    ListView mListView;
    String[] mQ;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_main);
		this.mListView = ((ListView)findViewById(R.id.bookList));
	    this.mQ = getResources().getStringArray(this.Q[getIntent().getExtras().getInt("Num")]);
	    ((TextView)findViewById(R.id.txtTitle)).setText(getIntent().getExtras().getString("Title"));
	    setListAdapter(this.mQ);
	    this.mListView.setOnItemClickListener(new OnItemClickListener()
	    {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent localIntent = new Intent(JokeListActivity.this, JokeDetail.class);
		        Bundle localBundle = new Bundle();
		        localBundle.putInt("Num", getIntent().getExtras().getInt("Num"));
		        localBundle.putInt("ItemNum",arg2);
		        localIntent.putExtras(localBundle);
		        startActivity(localIntent);				
			}
	    });
	    ((Button)findViewById(R.id.btnHome)).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
	    	
	    });
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
	

	  public void setListAdapter(String[] paramArrayOfString)
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
