package com.netwc.DataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.netwc.Entities.CategoryInfo;
import com.netwc.Entities.JokeInfo;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DataCenter {
	private Context mContext;
	private SQLiteDatabase db;
	
	public DataCenter(Context ctx){
		this.mContext=ctx;
	}
	
	private SQLiteDatabase OpenDataBase(String dbName){
		File dataFolder=Environment.getDataDirectory(); 
		File dbFile=new File(dataFolder.getAbsolutePath()+"/"+dbName);
		if(!dbFile.exists()){
			try {
				InputStream inputStream=mContext.getAssets().open("jokeInfo");
				FileOutputStream fso=new FileOutputStream(dbFile);
				byte[] buffer=new byte[1024];
				int readCount=0;
				while((readCount=inputStream.read(buffer))>0){
					fso.write(buffer);
				}
				inputStream.close();
				fso.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		SQLiteDatabase db=SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.CREATE_IF_NECESSARY);
		return db;
	} 

	/*
	 * Add CategoryInfo
	 * */
	public boolean AddCategoryInfo(CategoryInfo category){
		try {
			db.execSQL("insert into categoryinfo(ID,Name,PageUrl) values(?,?,?)", new Object[]{
				category.ID,category.Name,category.PageUrl	
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * Add Joke Info to database*/
	public boolean AddJokeInfo(JokeInfo joke){
		try {
			String jokeInsetString="insert into JokeInfo(ID,Title,Url,Content,Category,SiteDate,DataFrom,DateAdd,IsDownLoad,IsNew,IsFavourite) values(?,?,?,?,?,?,?,?,?,?,?)";
			db.execSQL(jokeInsetString,new Object[]{
				joke.ID,joke.Title,joke.Url,joke.Content,joke.CategoryID,joke.SiteDate,joke.DataFrom,joke.DateAdd,joke.IsDownLoad?1:0,joke.IsNew?1:0,joke.IsFavourite?1:0
			});				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	/*
	 * 
	 * */
	public Cursor GetJokeInfo(int pageSize,int pageIndex){
		try {
			return db.rawQuery("select * from jokeInfo limit "+pageSize+" offset "+pageSize*pageIndex, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Add to or remove from fav
	 * */
	public boolean Add2Fav(int jokeId,boolean bFav){
		try {
			String jokeInsetString="update JokeInfo set IsFavourite=? where id=?";
			db.execSQL(jokeInsetString,new Object[]{bFav?1:0,jokeId});				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	/*
	 * Get ID
	 * */
	public int GenerateCategoryId(){
		try {
			Cursor cur=db.rawQuery("select max(id) from categoryinfo",null);
			if(cur.getCount()>0){
				cur.moveToFirst();
				return cur.getInt(0)+1;
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * Get ID
	 * */
	public int GenerateJokeInfoId(){
		try {
			Cursor cur=db.rawQuery("select max(id) from jokeInfo",null);
			if(cur.getCount()>0){
				cur.moveToFirst();
				return cur.getInt(0)+1;
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
}
