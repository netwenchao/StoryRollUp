package com.luckywc.zdalarm;

import java.util.Calendar;

import com.luckywc.zdalarm.Receiver.ZDAlarmReceiver;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class AlarmMain extends Activity {

	private Calendar cal=Calendar.getInstance();
	AlarmManager alarmMgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_alarm_main);
		Button btn=(Button)findViewById(R.id.btnAdd);
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(0);
			}			
		});
		alarmMgr=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_main, menu);
		return true;
	}

	protected Dialog onCreateDialog(int id){
		Dialog dlg=null;
		switch(id){
			case 0:
				dlg=new TimePickerDialog(this,id, new TimePickerDialog.OnTimeSetListener() {					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						Calendar cal=Calendar.getInstance();
						cal.setTimeInMillis(System.currentTimeMillis());
						cal.set(Calendar.HOUR_OF_DAY,hourOfDay);
						cal.set(Calendar.MINUTE,minute);
						cal.set(Calendar.SECOND,0);
						cal.set(Calendar.MILLISECOND,0);
						Intent itent= new Intent(AlarmMain.this,ZDAlarmReceiver.class);
						PendingIntent pi=PendingIntent.getBroadcast(AlarmMain.this,0,itent,0);
						alarmMgr.cancel(pi);
						alarmMgr.set(AlarmManager.RTC,cal.getTimeInMillis(),pi);
						Log.v("t","Time set");
						Log.v("t",String.valueOf(cal.getTimeInMillis()));
						Toast.makeText(AlarmMain.this,"闹钟时间设置成功", Toast.LENGTH_LONG).show();
					}
				}, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
		}
		return dlg;
	}
}
