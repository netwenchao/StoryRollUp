package com.luckywc.jossstick;

import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class GetLotActivity extends Activity {

	 private ImageView mAnimLot;
	 private ImageView mLot;
	 private Animation mLotAnim;
	 private ImageView mZhibei;
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
		this.mLotAnimDrawable = new AnimationDrawable();
		int i=0;
		this.mAnimLot.setImageDrawable(this.mLotAnimDrawable);
		mLotAnimDrawable.start();
	}

}
