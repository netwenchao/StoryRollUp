package com.luckywc.Data;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;

import com.luckywc.core.Database.*;
import com.luckywc.core.IO.PathManager;
import com.luckywc.medbooks.R;

public class DataMgr {
	private Context mContext;
	private final String dbName="medbooks.jpg";
	private final String packageName="com.luckywc.medbooks";
	private DBManager dbMgr;
	private String dbPath;
	
	public DataMgr(Context ctx){
		mContext=ctx;
		dbPath=PathManager.GetMobileDataDirectory()+"/"+packageName;
		dbMgr=new DBManager(mContext,R.raw.medbook,dbName,dbPath);
	}
	
	public Cursor GetMedCategorys(){		
		dbMgr.OpenDatabase();
		Cursor cur=dbMgr.DataBase.query("Category", new String[]{"_id","name"},"",null,"","","");
		return cur;
	}
	
	public Cursor GetContentByCategory(int categoryId){
		dbMgr.OpenDatabase();
		return dbMgr.DataBase.query("Content", 
				new String[]{"_id","title","articalInfo","isfav","isRead","isNew"},"category=?",
				new String[]{String.valueOf(categoryId)},"","","_id asc,date_add desc");
	}
	
	public Cursor GetFoodInfo(){
		dbMgr.OpenDatabase();
		return dbMgr.DataBase.query("FoodInfo", 
				new String[]{"_id","fd1","fd2","Desc","isfav","isNew"},"category=?",
				null,"","","_id asc,date_add desc");
	}
	
	public void MarkFav(int tb,int id,Boolean bFav){
		dbMgr.OpenDatabase();
		MarkAsFavInternal((tb==1?"FoodInfo":"Content"),id,bFav);
		
	}
	
	private void MarkAsFavInternal(String tableName,int id,Boolean bfav){
		dbMgr.DataBase.execSQL("update "+tableName+" set isfav="+(bfav?"1":"0")+" where _id="+String.valueOf(id));
	}
	
 	public void Dispose(){
		dbMgr.CloseDatabase();
	}
}
