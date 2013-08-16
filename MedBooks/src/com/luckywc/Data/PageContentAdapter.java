package com.luckywc.Data;

import android.database.Cursor;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class PageContentAdapter extends PagerAdapter{
	
	private Cursor mCursor;
	
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
