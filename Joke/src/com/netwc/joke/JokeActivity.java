package com.netwc.joke;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.tencent.exmobwin.MobWINManager;
import com.tencent.exmobwin.Type;
import com.tencent.exmobwin.banner.AdListener;
import com.tencent.exmobwin.banner.TAdView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class JokeActivity extends Activity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MobWINManager.destroy();
		super.onDestroy();
	}
	String[] Catalogs;
	ListView mListView;
	LinearLayout contentAd;
	private final Handler AdRefreshHandler=new Handler(Looper.getMainLooper()){
		@SuppressLint("ShowToast")
		public void handleMessage(Message msg) {
		//	adView.loadAd(new AdRequest());
		}
	};
	public class RefreshThread extends Thread{
		private Handler handler;
		
		public RefreshThread(Handler handl){
			handler=handl;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				Message msg=handler.obtainMessage();
				handler.sendMessage(msg);
				try {
					network=cwjManager.getActiveNetworkInfo();
					if(network)
					Thread.sleep(55000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
	
	private ConnectivityManager cwjManager;
	private NetworkInfo network;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_main);
		mListView=(ListView)findViewById(R.id.bookList);
		this.Catalogs = getResources().getStringArray(R.array.Catalogs);
		setListAdapter(Catalogs);
		cwjManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {				
				Bundle bl=new Bundle();
				bl.putInt("Num", arg2);
				bl.putString("Title", Catalogs[arg2]);
				Intent intent=new Intent(JokeActivity.this,JokeListActivity.class);
				intent.putExtras(bl);
				startActivity(intent);
			}			
		});
		contentAd=(LinearLayout)findViewById(R.id.adContainer);
		MobWINManager.init(this,Type.MOBWIN_BANNER);
		TAdView tadview=new TAdView(JokeActivity.this);
		tadview.setAdListener(new AdListener(){

			@Override
			public void onReceiveAd() {
				// TODO Auto-generated method stub
				Log.d("ad","received");
			}

			@Override
			public void onReceiveFailed(int arg0) {
				// TODO Auto-generated method stub
				Log.d("ad","error");
			}
			
		});
		contentAd.addView(tadview);
		contentAd.invalidate();
		/* baidu
		AdSettings.setKey(new String[] { "baidu", "жа Йњ " });
		AdView adView = new AdView(this,AdSize.Banner,"");
		adView.setListener(new AdViewListener() {

			@Override
			public void onAdClick(JSONObject arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAdFailed(String arg0) {
				// TODO Auto-generated method stub
				Log.v("Ad","onAdFailed"+arg0);
			}

			@Override
			public void onAdReady(AdView arg0) {
				// TODO Auto-generated method stub
				Log.v("Ad","Ready");
			}

			@Override
			public void onAdShow(JSONObject arg0) {
				// TODO Auto-generated method stub
				Log.v("Ad","Show");
			}

			@Override
			public void onAdSwitch() {
				// TODO Auto-generated method stub
				Log.v("Ad","onAdSwitch");
			}

			@Override
			public void onVideoClickAd() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoClickClose() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoClickReplay() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoError() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoFinish() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoStart() {
				// TODO Auto-generated method stub
				
			}
		});		
		contentAd.addView(adView);
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.joke, menu);
		return true;
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
	          SimpleAdapter localSimpleAdapter = new SimpleAdapter(this, localArrayList,R.layout.book_item, new String[] {"ItemTitle" }, new int[] {R.id.ItemTitle});
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
