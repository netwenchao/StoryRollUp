package com.luckywc.jossstick;

import java.util.Random;

import com.kyview.AdViewInterface;
import com.kyview.AdViewLayout;
import com.kyview.AdViewTargeting;
import com.kyview.AdViewTargeting.RunMode;
import com.kyview.AdViewTargeting.UpdateMode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShengBei extends Activity{
	
	private AnimationDrawable cupAnmi;
	private ImageView mcup;
	private ImageView mcupBtn;
	private TextView mcupPrompt;
	private TextView mcupCount;
	private MediaPlayer mMPLotSound;
	private Random mRan=new Random();
	private int count=0;
	private int btnAction=0;//0:next,1:back,2:showdetail
	private int mRanIndex=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pelt_cup);
		mRanIndex=getIntent().getExtras().getInt("RanIndex");
		GetControl();		
	}
	
	private void GetControl(){
		mMPLotSound=MediaPlayer.create(this,R.raw.cup);
		mcup=(ImageView)findViewById(R.id.pelt_cup_cup);
		mcupPrompt=(TextView)findViewById(R.id.pelt_cup_prompt);
		mcupCount=(TextView)findViewById(R.id.pelt_cup_count);

		cupAnmi=new AnimationDrawable();		
		for(int i=0;i<3;i++) cupAnmi.addFrame(getResources().getDrawable(getResources().getIdentifier("bei_"+i,"drawable", getPackageName())),100);				
		cupAnmi.setOneShot(false);
		mMPLotSound.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				//mp.release();	
				cupAnmi.stop();
				if(ShengBei.this.mRan.nextInt(100)+1>83){					
					mcup.setImageDrawable(getResources().getDrawable(ShengBei.this.mRan.nextInt(2)+1>1?R.drawable.bei_1:R.drawable.bei_2));
					//dialog to first
					btnAction=1;				
					mcupPrompt.setText(getResources().getString(R.string.pelt_cup_no));
					mcupBtn.setImageDrawable(getResources().getDrawable(R.drawable.chongchou));//Set Img
				}else{
					count++;
					String countFormat=getResources().getString(R.string.pelt_cup_count);
					Object [] arr=new Object[1];
					arr[0]=Integer.valueOf(count);
					mcupCount.setText(String.format(countFormat,arr));
					mcup.setImageDrawable(getResources().getDrawable(R.drawable.bei_0));
					if(count>=3){
						//Shot button
						btnAction=2;						
						mcupBtn.setImageDrawable(getResources().getDrawable(R.drawable.chakan));//Set Img
						mp.release();
					}
				}
			}
		});
		mMPLotSound.setLooping(false);

		mcupBtn=((ImageView)findViewById(R.id.pelt_cup_bt));
		mcupBtn.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				switch(btnAction){
					case 0:
						mcup.setImageDrawable(cupAnmi);
						cupAnmi.start();
						mMPLotSound.start();
						break;
					case 1://back
						finish();
						Intent i=new Intent(ShengBei.this,GetLotActivity.class);
						startActivity(i);
						overridePendingTransition(R.anim.activity_open, R.anim.activity_close);
						break;
					case 2://show Detail
						Intent detail=new Intent(ShengBei.this,ShowLotDetail.class);
						detail.putExtra("mRanIndex",mRanIndex);
						startActivity(detail);
						overridePendingTransition(R.anim.activity_open, R.anim.activity_close);
						finish();
						break;
				}				
			}
		});		
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
}
