package com.luckywc.zdalarm.SystemManager;

import android.content.Context;
import android.os.PowerManager;

public class AlarmAlertWakeLock {
	private static PowerManager.WakeLock sCpuWakeLock;
	private static PowerManager.WakeLock sScreenWakeLock;
	
	public static void aceuireCpuWakeLock(Context ctx){
		if(sCpuWakeLock!=null) return;
		sCpuWakeLock=((PowerManager)ctx.getSystemService("power")).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"ZDAlarm");
		sCpuWakeLock.acquire();
	}
	
	public static void aceuireScreenWakeLock(Context ctx){
		if(sScreenWakeLock!=null) return;
		sScreenWakeLock=((PowerManager)ctx.getSystemService("power")).newWakeLock(PowerManager.FULL_WAKE_LOCK,"ZDAlarm");
		sScreenWakeLock.acquire();
	}
	
	public static void relaceAll(){
		if (sCpuWakeLock != null)
	    {
	      sCpuWakeLock.release();
	      sCpuWakeLock = null;
	    }
	    if (sScreenWakeLock != null)
	    {
	      sScreenWakeLock.release();
	      sScreenWakeLock = null;
	    }
	}
}
