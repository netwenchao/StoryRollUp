package com.netwc.Provider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper{
	Context mContext=null;
	SQLiteDatabase mDB=null;
	final DbOpenHelper openHelper;
	final String TABLE_NAME_NetJoke="NetJoke";
	final String TABLE_NAME_Fav="Favorite";

	public MyDBHelper(Context context){
		mContext=context;
		openHelper=new DbOpenHelper(mContext);
		mDB=openHelper.getWritableDatabase();
	}

	public void Close(){
		if(mDB!=null) mDB.close();
	}

	public void AddJoke(com.netwc.Provider.Entities.JokeInfo joke){
		if(!JokeExists(joke.Url)){
			ContentValues data=new ContentValues();		
			data.put("title",joke.Title);
			data.put("content",joke.Content);
			data.put("siteDate",joke.SiteDate);
			data.put("dateadd",joke.dateAdd);
			data.put("url",joke.Url);
			mDB.insert(TABLE_NAME_NetJoke,null, data);
		}		
	}

	public Boolean JokeExists(String url){
		Cursor cursor=mDB.query(TABLE_NAME_NetJoke,new String[]{"_id","title"}," url=?",new String[]{url},null,null," siteDate desc");
		return cursor.getCount()>0;
	}

	public Cursor GetJokeBySiteDate(String siteDate){
		if(siteDate!="")
			return mDB.query(TABLE_NAME_NetJoke, new String[]{"_id","title","content","siteDate","dateadd","url"},"siteDate=?",new String[]{siteDate}, null, null," siteDate desc");
		return mDB.query(TABLE_NAME_NetJoke, new String[]{"_id","title","content","siteDate","dateadd","url"},"",new String[]{}, null, null," siteDate desc");
	}

	public void AddFavoriteInfo(com.netwc.Provider.Entities.FavoriteInfo fav){
		ContentValues data=new ContentValues();
		data.put("joke_id",fav.ID);
		mDB.insert(TABLE_NAME_Fav,null, data);
	}
	class DbOpenHelper extends SQLiteOpenHelper{
		static final String DB_NAME="Joke_Data.db";
		static final int DB_VERSION=4;		

		public DbOpenHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);			
		}
		/*
		Table NetJoke
		--Table History
		Table Favorite	
		*/

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE NetJoke(_id integer PRIMARY KEY AUTOINCREMENT,title text,content text,url text,dateadd UNSIGNED BIG INT,siteDate text);");		
			db.execSQL("CREATE TABLE Favorite(joke_id integer);");		
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS NetJoke");
			db.execSQL("DROP TABLE IF EXISTS Favorite");
			onCreate(db);
		}
	}
}
