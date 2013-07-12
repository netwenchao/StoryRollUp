package com.luckywc.todolist.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ExpandablePanel extends LinearLayout{

	public ExpandablePanel(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public ExpandablePanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@SuppressLint("NewApi")
	public ExpandablePanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	
	private int mHandleId;
    private int mContentId; 
    private View mHandle;
    private View mContent; 
    private boolean mExpanded = true;
    private int mCollapsedHeight = 0;
    private int mContentHeight = 0;
    
    
}
