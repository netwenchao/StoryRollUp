package com.netwc.Provider;

import com.netwc.joke.R;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JokeAdapter extends CursorAdapter{

	private Context mContext;
	private LayoutInflater inflater;
	public JokeAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
		mContext=context;
		inflater=LayoutInflater.from(mContext);
	}

	@Override
	public void bindView(View v, Context arg1, Cursor cursor) {
		String Title=cursor.getString(cursor.getColumnIndex("title"));
		String Content=cursor.getString(cursor.getColumnIndex("content"));
		String SiteDate=cursor.getString(cursor.getColumnIndex("siteDate"));
		((TextView)v.findViewById(R.id.daily_item_jokeTitle)).setText(Title);
		((TextView)v.findViewById(R.id.daily_item_jokeContent)).setText(Content);
		((TextView)v.findViewById(R.id.daily_item_jokeUpdate)).setText(SiteDate);
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.daily_item,arg2,false);
	}
}
