package com.luckywc.jossstick;

import java.text.DecimalFormat;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowLotDetail extends Activity{
	private int mRanIndex=0;
	private ImageView mLogImage;
	private TextView lotTitle;
	private TextView lotDesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_show_detail);
		mRanIndex=getIntent().getExtras().getInt("RanIndex");
		mLogImage=(ImageView)findViewById(R.id.lotImage);
		lotTitle=(TextView)findViewById(R.id.lotTitle);
		lotDesc=(TextView)findViewById(R.id.lotDesc);	
		
		String [] titleS=getResources().getStringArray(R.array.lot_names);
		String lotIdx=new DecimalFormat("00").format(mRanIndex+1);
		this.mLogImage.setImageBitmap(Utility.getImageFromAssetsFile(this,lotIdx+".gif"));
		this.lotDesc.setText(Utility.getStringFromAssetsFile(this,lotIdx+".txt"));
		this.lotTitle.setText(titleS[mRanIndex]);
		
		LinearLayout layout=(LinearLayout)findViewById(R.id.AdLinearLayout);		
		AdViewTargeting.setRunMode(RunMode.NORMAL); 
		AdViewTargeting.setUpdateMode(UpdateMode.EVERYTIME); 
		AdViewLayout adViewLayout = new AdViewLayout(this, "SDK20130927090430cbq70w7q3d4kvax"); 
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
}
