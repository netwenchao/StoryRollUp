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
		//��ʾ�Ի���  
        new AlertDialog.Builder(AlarmPage.this).  
            setTitle("����").//���ñ���  
            setMessage("ʱ�䵽�ˣ�").//��������  
            setPositiveButton("֪����", new DialogInterface.OnClickListener(){//���ð�ť
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					AlarmPage.this.finish();//�ر�Activity
				}  
            }).create().show();
        */  	
	}
}
