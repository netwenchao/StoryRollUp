package com.luckywc.controls;

import com.luckywc.medbooks.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;

public class RoundCornerListView extends ListView{

	public RoundCornerListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public RoundCornerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public RoundCornerListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                int itemnum = pointToPosition(x, y);
 
                if (itemnum == AdapterView.INVALID_POSITION)
                        break;                 
                else
                {  
                        if(itemnum==0){
                                if(itemnum==(getAdapter().getCount()-1)){                                    
                                    setSelector(R.drawable.list_corner_round);
                                }else{
                                    setSelector(R.drawable.list_corner_round_top);
                                }
                        }else if(itemnum==(getAdapter().getCount()-1))
                                setSelector(R.drawable.list_corner_round_bottom);
                        else{                            
                            setSelector(R.drawable.list_corner_round_sharp);
                        }
                }
 
                break;
        case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
	}

}
