package com.luckywc.Data;

import com.luckywc.medbooks.R;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PageContentAdapter extends PagerAdapter{
	private Context mContext;
	private Cursor mCursor;	
	
	public PageContentAdapter(Context context,Cursor cursor){
		mContext=context;
		mCursor=cursor;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		//return super.instantiateItem(container, position);
		LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dView= inflater.inflate(R.layout.activity_detail,container);
		return container;
	}	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCursor.getCount();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

}
