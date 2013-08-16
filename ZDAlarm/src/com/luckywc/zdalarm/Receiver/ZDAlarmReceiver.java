package com.luckywc.zdalarm.Receiver;

import com.luckywc.zdalarm.AlarmPage;
import com.luckywc.zdalarm.SystemManager.AlarmAlertWakeLock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class ZDAlarmReceiver extends BroadcastReceiver{
	private static PowerManager.WakeLock sCpuWakeLock;
	private static PowerManager.WakeLock sScreenWakeLock;
	
	@Override
	public void onReceive(Context context, Intent arg1) {
		Log.v("t"," BroadcastReceiver ");
		AlarmAlertWakeLock.aceuireCpuWakeLock(context);
		AlarmAlertWakeLock.aceuireScreenWakeLock(context);
		Intent armPage=new Intent(context,AlarmPage.class);
		armPage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(armPage);		
	}
}
