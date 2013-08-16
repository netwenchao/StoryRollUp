package com.luckywc.core.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	private final int BUFFER_SIZE=4000;
	private String mDBName;
	private String mDBPath;
	public SQLiteDatabase DataBase;
	private Context ctx;
	private int mRawDBID;
	
	public DBManager(Context context,int rawDBID,String dbName,String dbPath){
		ctx=context;
		mRawDBID=rawDBID;
		mDBName=dbName;
		mDBPath=dbPath;
	}
	
	public void OpenDatabase(){this.DataBase=openDatabase(mDBPath,mDBName);}

	public void CloseDatabase(){
		if(null!=DataBase){
			DataBase.close();
		}
	}
	
	private SQLiteDatabase openDatabase(String dbPath,String dbName){
		try{
			String dbFullPath=dbPath+"/"+dbName;
			File dic=new File(dbPath);
			if(!dic.exists()){
				try{
					dic.mkdir();
				}catch(Exception e){
					Log.e("Error",e.getLocalizedMessage());
					return null;
				}
			}
			if(!new File(dbFullPath).exists()){
				InputStream io=this.ctx.getResources().openRawResource(mRawDBID);
				File file=new File(dbFullPath);				
				FileOutputStream fsOut=new FileOutputStream(dbFullPath,true);
				byte [] buffer=new byte[BUFFER_SIZE];
				int count=0;
				while((count=io.read(buffer))>0){
					fsOut.write(buffer);
				}
				fsOut.close();
				io.close();
			}
			SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(dbFullPath,null);
			return db;
		}catch(FileNotFoundException e){
			Log.e("DBManager", "DataBase File not found in '"+dbPath+"/"+dbName+"'");
			e.printStackTrace();
		}catch(Exception e){
			Log.e("DBManager", "IO Exception");
			e.printStackTrace();
		}
		return null;
	}

}