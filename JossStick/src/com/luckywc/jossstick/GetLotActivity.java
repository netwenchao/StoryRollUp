package com.luckywc.jossstick;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GetLotActivity extends Activity {

	 private ImageView mAnimLot;
	 private ImageView mLot;
	 private Animation mLotAnim;
	 private ImageView mZhibei;
	 private MediaPlayer mMPLotSound;
	 private int mRanIndex;
	 private String mRanText;
	 private TextView mVerticalLotTitle;
	 private String[] mNameList;
	 private AnimationDrawable mLotAnimDrawable = null;
	 private Random mRan=new Random();
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_get_lot);
		mAnimLot=(ImageView)findViewById(R.id.show_anim_lot);
		mLot=(ImageView)findViewById(R.id.show_lot);
		mZhibei=(ImageView)findViewById(R.id.show_zhibei);
		mLotAnim=AnimationUtils.loadAnimation(GetLotActivity.this,R.anim.lot_out);
		mVerticalLotTitle=(TextView)findViewById(R.id.show_vertical_lot_title);
		mNameList=getResources().getStringArray(R.array.lot_names);
		this.mMPLotSound = MediaPlayer.create(this,R.raw.lot_sound);
		mLotAnimDrawable=new AnimationDrawable();
		
		for(int j=0;j<3;j++){
			this.mLotAnimDrawable.addFrame(getResources().getDrawable(getResources().getIdentifier("lot_" + j % 3, "drawable", getPackageName())), 100);
		}
		mAnimLot.setImageDrawable(mLotAnimDrawable);
		mLotAnimDrawable.setOneShot(false);
		mLotAnimDrawable.start();
		
		mLotAnim.setAnimationListener(new AnimationListener() {					
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
				GetLotActivity.this.mRanIndex = (1 + GetLotActivity.this.mRan.nextInt(100));
				GetLotActivity.this.mRanText = GetLotActivity.this.mNameList[GetLotActivity.this.mRanIndex];
				GetLotActivity.this.mZhibei.setVisibility(0);
				GetLotActivity.this.mVerticalLotTitle.setVisibility(0);
		        String str = "";
		        for (int i = 0; ; i++)
		        {
		          if (i >= GetLotActivity.this.mRanText.length())
		          {
		        	  GetLotActivity.this.mVerticalLotTitle.setText(str);
		            return;
		          }
		          str = str + GetLotActivity.this.mRanText.substring(i, i + 1) + "\n";
		        }
		        /**/
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
	    try
	    {
	    	this.mMPLotSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
	        {
	          public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
	          {	        	
	            paramAnonymousMediaPlayer.release();
	            GetLotActivity.this.mLot.setVisibility(0);
	            GetLotActivity.this.mLot.startAnimation(GetLotActivity.this.mLotAnim);
	            GetLotActivity.this.mLotAnimDrawable.stop();
	            Toast.makeText(GetLotActivity.this,"Stoped", 1000);
	          }
	        });
	    	//this.mMPLotSound.prepare();
	        this.mMPLotSound.start();	        	        
	      }
	      catch (IllegalStateException localIllegalStateException)
	      {
	          localIllegalStateException.printStackTrace();
	      }	    
	}
}
