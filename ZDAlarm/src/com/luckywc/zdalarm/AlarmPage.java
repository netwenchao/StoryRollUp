package com.luckywc.zdalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlarmPage extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_alarm_page);
		/*
		//显示对话框  
        new AlertDialog.Builder(AlarmPage.this).  
            setTitle("闹钟").//设置标题  
            setMessage("时间到了！").//设置内容  
            setPositiveButton("知道了", new DialogInterface.OnClickListener(){//设置按钮
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					AlarmPage.this.finish();//关闭Activity
				}  
            }).create().show();
        */  	
	}
}
