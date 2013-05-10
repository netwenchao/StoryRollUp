package com.luckywc.todolist.provider;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDB 
{
	public String DB_CREATE_TABLE_CATEGORY="CREATE TABLE Category(_id INTEGER PRIMARY KEY,name TEXT,date_add DATETIME,other1 TEXT,other2 TEXT)";
	public String DB_CREATE_TABLE_TASK="CREATE TABLE TaskInfo(_id INTEGER PRIMARY KEY,parent_id INTEGER,category INTEGER,name TEXT,date_start DATETIME,date_end DATETIME,state Integer,date_add DATETIME,other1 TEXT,other2 TEXT)";	
	public String DB_INSERT_TASK="INSERT INTO TaskInfo(parent_id,category,name,date_start,date_end,state,date_add,other1,other2) VALUES";
	public String DB_INSERT_CATEGORY="INSERT INTO Category(name,date_add) VALUES";
	public String DB_TABLE_CATEGORY="Category";
	public String DB_TABLE_TASK="TaskInfo";

	public String DBName="ToDoMgr.db";
	private Context mContext;
	private int DB_VERSION=1;

	private static class DatabaseHelper extends SQLiteOpenHelper{
		private static final String DB_DROP_TABLE_ALARMINFO = "DROP TABLE IF EXISTS Category;";
		private static final String DB_DROP_TABLE_TASKALARMINFO = "DROP TABLE IF EXISTS TaskInfo;";		

		DatabaseHelper(Context paramContext)
		{
			super(paramContext, "ToDoMgr.db", null,1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE Category(_id INTEGER PRIMARY KEY,name TEXT,date_add DATETIME,other1 TEXT,other2 TEXT)");
			db.execSQL("CREATE TABLE TaskInfo(_id INTEGER PRIMARY KEY,parent_id INTEGER,category INTEGER,name TEXT,date_start DATETIME,date_end DATETIME,state Integer,date_add DATETIME,other1 TEXT,other2 TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Cursor localCursor2 = db.query("TaskInfo", null, null, null, null, null, null);
			ArrayList tasks = new ArrayList();
			ArrayList categorys = new ArrayList();
			if ((localCursor2 != null) && (localCursor2.moveToFirst()))
				do{
					ContentValues taskValues = new ContentValues();
					taskValues.put("parent_id", Integer.valueOf(localCursor2.getInt(1)));
					taskValues.put("category", Integer.valueOf(localCursor2.getInt(2)));
					taskValues.put("name", localCursor2.getString(3));
					taskValues.put("date_start", localCursor2.getString(4));
					taskValues.put("date_end", localCursor2.getString(5));
					taskValues.put("state", Integer.valueOf(localCursor2.getInt(6)));
					taskValues.put("date_add", localCursor2.getString(7));
					taskValues.put("other1", localCursor2.getString(8));
					taskValues.put("other2", localCursor2.getString(9));
					tasks.add(taskValues);
				}
				while (localCursor2.moveToNext());
			if (localCursor2 != null) localCursor2.close();

			Cursor categoryCursor=db.query("Category", null, null, null, null, null, null);
			if ((categoryCursor != null) && (categoryCursor.moveToFirst()))
				do{
					ContentValues categoryValue = new ContentValues();
					categoryValue.put("name",categoryCursor.getString(1));
					categoryValue.put("date_add",categoryCursor.getString(2));
					categoryValue.put("other1", categoryCursor.getString(3));
					categoryValue.put("other2", categoryCursor.getString(4));
					categorys.add(categoryValue);
				}
				while (categoryCursor.moveToNext());
			if (categoryCursor != null) categoryCursor.close();

			db.execSQL(DB_DROP_TABLE_ALARMINFO);
			db.execSQL(DB_DROP_TABLE_TASKALARMINFO);
		
		}
	}
}
