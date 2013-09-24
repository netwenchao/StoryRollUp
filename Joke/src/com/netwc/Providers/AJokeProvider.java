package com.netwc.Providers;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public abstract class AJokeProvider {

	public String UrlEncodeChina(String str){
		Pattern p=Pattern.compile("(?m)(?u)([\u4E00-\u9FA5]+)");
		String outStr=str;
		Matcher m=p.matcher(str);
		while(m.find()){
			Log.v("Match",Integer.toString(m.group(1).length()));
			Log.v("Match",m.group(1));
			outStr=outStr.replace(m.group(1),URLEncoder.encode(m.group(1)));
		}
		return outStr;
	}
	
	public String RemoveHtmlCode(String str){
		return str.replaceAll("(?m)(?u)\\<br[ ]*(/)*\\>","\n").replaceAll("(?m)\\<[^<>]*\\>","")
		.replaceAll("(?m)(?u)&amp;","&")
		.replaceAll("(?m)(?u)&lt;","<")
		.replaceAll("(?m)(?u)&gt;",">")
		.replaceAll("(?m)(?u)&quot;","\"")
		.replaceAll("(?m)(?u)&apos;","'")
		.replaceAll("(?m)(?u)&nbsp[ ]*;"," ");
	}

}
