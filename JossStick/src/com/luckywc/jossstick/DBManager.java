package com.luckywc.jossstick;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {	
	private Context mContext;
	private final int Buffer_Size=1024;
	private final String DBName="JossStick.db";
	private final String Data_Path="Data";
	public SQLiteDatabase DataBase;
	
	public DBManager(Context context){
		mContext=context;				
	}
	
	public void OpenStickDb(){
		if(DataBase==null) DataBase=OpenDataBase(DBName);		
	}
	
	public void CloseStickDB(){
		if(DataBase!=null && DataBase.isOpen()) DataBase.close();
	}
	
	public ArrayList<JieMengEntity> GetJieMengResult(String keywords){
		ArrayList<JieMengEntity> entities=new ArrayList<JieMengEntity>();
		Cursor jieMengCur=DataBase.query("zgjm",new String[]{"id","keyword","content"}," keyword like '%"+keywords.replace("'","''")+"%'", null, null, null, null);
		if(jieMengCur.getCount()>0){
			jieMengCur.moveToFirst();
			do{
				JieMengEntity entity=new JieMengEntity();
				entity.ID=jieMengCur.getInt(0);
				entity.Keyword=jieMengCur.getString(1).replace("\"","");
				entity.Content=jieMengCur.getString(2).replace("\"","");
				entities.add(entity);
			}while(jieMengCur.moveToNext());
		}
		return entities;
	}
	
	public ArrayList<String> GetJieMengKeywords(){
		ArrayList<String> array=new ArrayList<String>();
		Cursor cur=DataBase.query("zgjm",new String[]{"keyword"},"",null,null, null, null);
		cur.moveToFirst();
		do{
			array.add(cur.getString(0).replace("\"",""));
		}
		while(cur.moveToNext());
		cur.close();
		return array;
	}
	
	public StickResult GetStickResult(int id){		
		if(id==0 || id==100) id=1;
		Cursor cur= DataBase.query("lot",new String[]{"_id","name","type","title","gongwei","shiyue","shiyi","jieyue","xianji","guishi"}, "_id=?",new String[]{String.valueOf(id)},"","","");
		if(cur.getCount()>0 && cur.moveToFirst()){
			StickResult rslt=new StickResult();
			rslt.ID=cur.getInt(0);
			rslt.Name=cur.getString(1);
			rslt.Type=cur.getString(2);
			rslt.Title=cur.getString(3);
			rslt.GongWei=cur.getString(4);
			rslt.ShiYue=cur.getString(5);
			rslt.ShiYi=cur.getString(6);
			rslt.JieYue=cur.getString(7);
			rslt.XianJi=cur.getString(8);
			rslt.GuiShi=cur.getString(9);
			cur.close();
			return rslt;
		}
		return null;
	}
	
	public WordAnalysisResult GetAnalysisResult(String word1,String word2,String word3)
	{
		Random mRan=new Random();
		WordAnalysisResult rslt=null;
		String [] words=word1.split("");
		String builder=new String();
		String queryStr="";
		if(word1!="") queryStr+="(hanzi like '%"+word1+"%')";
		if(word2!=""){
			if(queryStr=="") 
				queryStr+="(hanzi like '%"+word2+"%')";
			else  
				queryStr+="or (hanzi like '%"+word2+"%')";
		}
		
		if(word3!=""){
			if(queryStr=="") 
				queryStr+="(hanzi like '%"+word3+"%')";
			else  
				queryStr+="or (hanzi like '%"+word3+"%')";
		}
		
		Cursor bihuaCur=DataBase.query("bihua",new String[]{"num"},queryStr, null,"","","");
		int Counter=0;
		if(bihuaCur.moveToFirst()){
			do{
				Counter+=bihuaCur.getInt(0)*10;
			}while(bihuaCur.moveToNext());
		}else{
			Counter=mRan.nextInt(384);
			Log.v("JossStick","Can't not get bi hua,So random.");
		}
		bihuaCur.close();
		Counter%=384;
		
		Cursor zhuge=DataBase.query("zhuge",new String[]{"_id","title","content"},"_id=?", new String[]{String.valueOf(Counter)},"","","");
		if(zhuge.getCount()>0){
			zhuge.moveToFirst();
			rslt=new WordAnalysisResult();
			rslt.ID=String.valueOf(Counter);
			rslt.Title=zhuge.getString(1);
			rslt.Content=zhuge.getString(2);
		}
		zhuge.close();
		return rslt;
	}
	
	private SQLiteDatabase OpenDataBase(String dbName){
		File rootFolder=mContext.getFilesDir();
		File dataFolder=new File(rootFolder.getAbsolutePath()+"/"+Data_Path);
		if(!dataFolder.exists()) dataFolder.mkdir(); //Create Dic
		File dbFile=new File(dataFolder.getAbsolutePath()+"/"+dbName);
		if(!dbFile.exists()){
			//Create DB File
			try {
				InputStream io=mContext.getResources().getAssets().open(dbName);
				FileOutputStream fso=new FileOutputStream(dbFile);
				
				byte [] buffer=new byte[Buffer_Size];
				int count=0;
				while((count=io.read(buffer))>0){
					fso.write(buffer);
				}
				fso.close();
				io.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(dbFile.getAbsolutePath(),null);
		return db;
	}

}
