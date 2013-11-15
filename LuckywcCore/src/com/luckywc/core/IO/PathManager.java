package com.luckywc.core.Io;

import android.os.Environment;

public class PathManager {
	public static String GetMobileDataDirectory(){
		return "/data"+Environment.getDataDirectory().getAbsolutePath();
	}
}
