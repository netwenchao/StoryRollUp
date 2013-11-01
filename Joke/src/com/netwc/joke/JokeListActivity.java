package com.netwc.joke;

import java.util.ArrayList;
import java.util.HashMap;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;
import com.netwc.DataProvider.DataCenter;

import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class JokeListActivity extends Activity{
    ListView mListView;
    int pageSize=20;
    int pageIdx=0;
    public boolean isLoading=false;
    public boolean isScrolliing=true;
    private Cursor dataCursor;
    public DataCenter dCenter;
    public View FooterView;    
    public Spinner  dplCategoryOfJokes;
    public ArrayList<String> Categorys;
    
    private OnScrollListener mOnScrollListener=new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {	
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:	
				isScrolliing=true;
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				isScrolliing=true;
				break;
			case OnScrollListener.SCROLL_STATE_IDLE:
				isScrolliing=false;
				break;
			default:
				break;
			}
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if(isLoading) return;			
			Log.v("OnScorll","FirstVisibleItem:"+firstVisibleItem);
			Log.v("OnScorll","visibleItemCount:"+visibleItemCount);
			if(firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount>0){
				FetchDataAsyncTask fc=new FetchDataAsyncTask();
				fc.execute(new Integer[]{});
			}
		}
	};
	
	public Handler updateListViewData=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what==1){				
				bindData2List();
				((TextView)(FooterView.findViewById(R.id.item_main_footer_page))).setText("��"+String.valueOf(pageIdx)+1+"ҳ");
				//Toast.makeText(JokeListActivity.this,"��"+String.valueOf(pageIdx)+1+"ҳ",Toast.LENGTH_LONG);
			}
		}		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_main);
		this.mListView = ((ListView)findViewById(R.id.jokeList));
		dplCategoryOfJokes=(Spinner) findViewById(R.id.dplCategoryOfJokes);
		this.mListView.setOnScrollListener(mOnScrollListener);	
		dCenter=new DataCenter(this);
		Categorys=dCenter.GetCategorys();
		ArrayAdapter arr=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Categorys);
		arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dplCategoryOfJokes.setAdapter(arr);		
		dataCursor=dCenter.GetJokeInfo(pageSize,pageIdx);
		FooterView=((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_main_footer_view,null);		
		((TextView)(FooterView.findViewById(R.id.item_main_footer_page))).setText("��"+String.valueOf(pageIdx+1)+"ҳ");
		this.mListView.addFooterView(FooterView,null,false);
		bindData2List();
		
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
	
	public void bindData2List()
	{		
	    try
	    {	
	    	if(dataCursor==null) return;
	    	CursorAdapter adapter=new CursorAdapter(this,dataCursor) {				
				@Override
				public View newView(Context context, Cursor cursor, ViewGroup parent) {
					// TODO Auto-generated method stub
					LayoutInflater inflat=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					return inflat.inflate(R.layout.item_joke_main,parent,false);
				}
				
				@Override
				public void bindView(View view, Context context, Cursor cursor) {
					JokeItemControl controls=GetJokeItemFromView(view);
					controls.item_joke_title.setText(cursor.getString(1));
					controls.item_joke_day.setText("1");
					controls.item_joke_month.setText("11��");
					controls.item_joke_from.setText(cursor.getString(6));
					controls.item_joke_content.setText(cursor.getString(3).replace(" ��","\n").replace("����","\n"));					
				}
			};
			mListView.setAdapter(adapter);
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	}
	
	private JokeItemControl GetJokeItemFromView(View v){
		JokeItemControl rsltControl=new JokeItemControl();
		rsltControl.item_joke_day=(TextView)v.findViewById(R.id.item_joke_day);
		rsltControl.item_joke_month=(TextView)v.findViewById(R.id.item_joke_month);
		rsltControl.item_joke_from=(TextView)v.findViewById(R.id.item_joke_from);
		rsltControl.item_joke_title=(TextView)v.findViewById(R.id.item_joke_title);
		rsltControl.item_joke_content=(TextView)v.findViewById(R.id.item_joke_content);
		return rsltControl;
	}
	
 	public class JokeItemControl{
		public TextView item_joke_day;
		public TextView item_joke_month;
		public TextView item_joke_title;
		public TextView item_joke_from;
		public TextView item_joke_content;		
	}


 	private class FetchDataAsyncTask extends AsyncTask<Integer,Void,Void>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//mListView.addFooterView(v);
		}

		@Override
		protected Void doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			
			isLoading=true;
			pageIdx++;
			dataCursor=dCenter.GetJokeInfo(pageSize, pageIdx);
			Message msgMessage=new Message();
			msgMessage.what=1;
			updateListViewData.sendMessage(msgMessage);
			return null;			
			/**/
		}
 		
		protected void onPostExecute(Void result){
			isLoading=false;
		}
 	}
}
