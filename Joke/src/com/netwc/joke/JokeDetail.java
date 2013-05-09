package com.netwc.joke;

import org.json.JSONObject;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.tencent.exmobwin.MobWINManager;
import com.tencent.exmobwin.Type;
import com.tencent.exmobwin.banner.AdListener;
import com.tencent.exmobwin.banner.TAdView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ZoomControls;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class JokeDetail extends Activity{
	int[] A = { R.array.Cfq, R.array.Cla, R.array.Ccr, R.array.Cet, R.array.Cjiating, R.array.Cyr, R.array.Cmr, R.array.Czc, R.array.Cxy, 
	R.array.Caft, R.array.Cgd, R.array.Cxd , R.array.Cdn, R.array.Czz, R.array.Cjs, R.array.Cjt, R.array.Czj, R.array.Cmj, R.array.Cgh, 
	R.array.Csf, R.array.Cty, R.array.Cjy, R.array.Cyl, R.array.Cgw, R.array.Cwr };
	int ItemNum = 0;
	int Num = 0;
	int[] Q = { R.array.Tfq, R.array.Tla, R.array.Tcr, R.array.Tet, R.array.Tjiating, R.array.Tyr, R.array.Tmr, R.array.Tzc, 
		R.array.Txy, R.array.Taft, R.array.Tgd, R.array.Txd, R.array.Tdn, R.array.Tzz, R.array.Tjs, R.array.Tjt, R.array.Tzj, R.array.Tmj, 
		R.array.Tgh, R.array.Tsf, R.array.Tty, R.array.Tjy, R.array.Tyl, R.array.Tgw, R.array.Twr };	
	String[] mA;	
	private TextView mTextView;
	String[] mQ;
	LinearLayout contentAd;
	private SharedPreferences prefrence;

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MobWINManager.destroy();
		super.onDestroy();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_content);
		mTextView=(TextView)findViewById(R.id.txtContent);
		this.mQ = getResources().getStringArray(this.Q[getIntent().getExtras().getInt("Num")]);
		this.mA = getResources().getStringArray(this.A[getIntent().getExtras().getInt("Num")]);
		this.ItemNum = getIntent().getExtras().getInt("ItemNum");
		contentAd=(LinearLayout)findViewById(R.id.contentAd);
		MobWINManager.init(this,Type.MOBWIN_BANNER);
		TAdView tadview=new TAdView(JokeDetail.this);
		tadview.setAdListener(new AdListener(){

			@Override
			public void onReceiveAd() {
				// TODO Auto-generated method stub
				Log.v("ad","received");
			}

			@Override
			public void onReceiveFailed(int arg0) {
				// TODO Auto-generated method stub
				Log.v("ad","error");
			}
			
		});
		contentAd.addView(tadview);
		
		/*
		AdSettings.setKey(new String[] { "baidu", "�� �� " });
		AdView adView = new AdView(this,AdSize.Banner,"");
		adView.setListener(new AdViewListener() {

			@Override
			public void onAdClick(JSONObject arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAdFailed(String arg0) {
				// TODO Auto-generated method stub
				Log.v("Ad","onAdFailed"+arg0);
			}

			@Override
			public void onAdReady(AdView arg0) {
				// TODO Auto-generated method stub
				Log.v("Ad","Ready");
			}

			@Override
			public void onAdShow(JSONObject arg0) {
				// TODO Auto-generated method stub
				Log.v("Ad","Show");
			}

			@Override
			public void onAdSwitch() {
				// TODO Auto-generated method stub
				Log.v("Ad","onAdSwitch");
			}

			@Override
			public void onVideoClickAd() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoClickClose() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoClickReplay() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoError() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoFinish() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoStart() {
				// TODO Auto-generated method stub
				
			}
		});		
		contentAd.addView(adView);
		
		*/
		prefrence=getPreferences(MODE_PRIVATE);
		this.mTextView.setFocusable(true);
		((TextView)findViewById(R.id.contentTitle)).setText(this.mQ[this.ItemNum]);
		this.mTextView.setText(this.mA[this.ItemNum]);
		
		mTextView.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		((Button)findViewById(R.id.btnBack2ClassList)).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Integer fSize=prefrence.getInt("fontSize",16);
		mTextView.setTextSize(fSize);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.content_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.action_changeappr){
			BuildDialog();
		}else if(item.getItemId()==R.id.action_share){
			Intent intent=new Intent(Intent.ACTION_SEND);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(Intent.EXTRA_TEXT,mTextView.getText());
			intent.setType("text/plain");
			startActivity(Intent.createChooser(intent,getString(R.string.action_share)));
		}
		return true;
	}
	
	 @SuppressLint("NewApi")
		private void BuildDialog(){
			 final Dialog dialog = new Dialog(this); 
			 dialog.setContentView(R.layout.change_apperance);
			 dialog.setTitle(getString(R.string.settingDialogTitle));
			 Integer fSize=prefrence.getInt("fontSize",16);
			 final SeekBar sb= (SeekBar)dialog.findViewById(R.id.sbFontSize);
			 sb.setProgress(fSize-10);
			 sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
				@Override
				public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
					// TODO Auto-generated method stub
					((TextView)dialog.findViewById(R.id.prevText)).setTextSize(arg1+10);
					((TextView)dialog.findViewById(R.id.fontSize)).setText(String.valueOf(arg1+10));
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}			 
			 });
			 ZoomControls zcs=(ZoomControls)dialog.findViewById(R.id.zoomFontSize);
			 zcs.setZoomSpeed(1);
			 zcs.setOnZoomInClickListener(new OnClickListener (){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					sb.setProgress(sb.getProgress()+1);
				}
				 
			 });
			 zcs.setOnZoomOutClickListener(new OnClickListener (){

				@Override
				public void onClick(View v) {
					sb.setProgress(sb.getProgress()-1);
					
				}
				 
			 });
			 ((Button)dialog.findViewById(R.id.btnSettingSure)).setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((TextView)findViewById(R.id.txtContent)).setTextSize(sb.getProgress()+10);
					Editor editor = prefrence.edit();
					editor.putInt("fontSize",sb.getProgress()+10);
					editor.commit();
					dialog.dismiss();
				}
				 
			 });
			 ((Button)dialog.findViewById(R.id.btnSettingCancel)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						dialog.cancel();
					}
					 
				 });
			 ((TextView)dialog.findViewById(R.id.prevText)).setTextSize(fSize);
			 ((TextView)dialog.findViewById(R.id.fontSize)).setText(String.valueOf(fSize));
			 dialog.setCancelable(false);
			 dialog.show();
		 }

}
