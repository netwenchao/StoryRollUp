package com.luckywc.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TabStyleControl extends LinearLayout{

	private Context mContext;
	public TabStyleControl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext=context;
	}
	public TabStyleControl(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@SuppressLint("NewApi")
	public TabStyleControl(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

}
