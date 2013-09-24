package com.luckywc.jossstick;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityAlmanac extends Activity implements View.OnClickListener{

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		Calendar cal=GregorianCalendar.getInstance();
	}

	private TextView mCongText;
	private TextView mDateText;
	private TextView mJiText;
	private TextView mJsyqText;
	private TextView mLunarText;
	private TextView mPzbjText;
	private TextView mSuiciText;
	private TextView mTszfText;
	private TextView mWuhangText;
	private TextView mXsyjText;
	private TextView mYiText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_almanac);
		initViews();		
		LinearLayout layout=(LinearLayout)findViewById(R.id.AdLinearLayout);		
		AdViewTargeting.setRunMode(RunMode.NORMAL); 
		AdViewTargeting.setUpdateMode(UpdateMode.DEFAULT); 
		AdViewLayout adViewLayout = new AdViewLayout(this, "SDK20131012100521kidb0cxesk8oste"); 
		//adViewLayout.setAdViewInterface(this); 
		adViewLayout.setAdViewInterface(new AdViewInterface(){

			@Override
			public void onClickAd() {
				// TODO Auto-generated method stub
				Log.v("AD","Clicked");
			}

			@Override
			public void onDisplayAd() {
				// TODO Auto-generated method stub
				Log.v("AD","Displayed");
			}
			
		});
		layout.addView(adViewLayout); 
		layout.invalidate();
	}
	
	private void initViews()
	{
	    this.mDateText = ((TextView)findViewById(R.id.tv_date));
	    this.mLunarText = ((TextView)findViewById(R.id.tv_lunar));
	    this.mSuiciText = ((TextView)findViewById(R.id.tv_suici));
	    this.mWuhangText = ((TextView)findViewById(R.id.tv_wuhang));
	    this.mTszfText = ((TextView)findViewById(R.id.tv_tszf));
	    this.mPzbjText = ((TextView)findViewById(R.id.tv_pzbj));
	    this.mCongText = ((TextView)findViewById(R.id.tv_cong));
	    this.mYiText = ((TextView)findViewById(R.id.tv_yi));
	    this.mJiText = ((TextView)findViewById(R.id.tv_ji));
	    this.mJsyqText = ((TextView)findViewById(R.id.tv_jsyq));
	    this.mXsyjText = ((TextView)findViewById(R.id.tv_xsyj));
	  }

	@Override
	public void onClick(View v) {
		switch(v.getId()){			
			case R.id.btn_almanac_query:
				break;
			case R.id.btn_compatibility_query:
				break;
			case R.id.btn_wedding_query:
				break;
			default:
				return;
		}		
	}

}
