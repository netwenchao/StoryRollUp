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
		mRanIndex=getIntent().getExtras().getInt("mRanIndex");
		DBManager dbm=new DBManager(ShowLotDetail.this);
		dbm.OpenStickDb();
		if(dbm.DataBase!=null){
			StickResult rslt=dbm.GetStickResult(mRanIndex);
			if(rslt!=null){
				Log.v("JossStick",rslt.Title);
				((TextView)findViewById(R.id.tv_title)).setText(rslt.Title);				
				((TextView)findViewById(R.id.tv_guishi)).setText(rslt.GuiShi);
				((TextView)findViewById(R.id.tv_jieyue)).setText(rslt.JieYue);
				((TextView)findViewById(R.id.tv_name)).setText(rslt.Name);
				((TextView)findViewById(R.id.tv_shiyi)).setText(rslt.ShiYi);
				((TextView)findViewById(R.id.tv_type)).setText(rslt.Type);
				((TextView)findViewById(R.id.tv_xianji)).setText(rslt.XianJi);
			}
			else{
				Log.v("JossStick","Null Item"+mRanIndex);
			}
		}else{
			Log.v("JossStick","dbm.DataBase==null");
		}
		/*
		return;
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
		*/
	}
}
