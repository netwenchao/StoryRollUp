package com.luckywc.todolist.provider;

import java.util.ArrayList;

import com.luckywc.todolist.provider.EnumMgr.TaskStatus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseAdapter extends SQLiteOpenHelper{
	private Context mContext;
	private final static String DataBaseName="TaskInfo.db";
	private final static Integer DataBaseVersion=1;
	private static SQLiteDatabase mDataBase=null;

	private final static String DBCreateTableTaskInfos="CREATE TABLE TaskInfos(_id INTEGER PRIMARY KEY,name TEXT,category INTEGER NOT NUll,priority INTEGER DEFAULT 2,status INTEGER DEFAULT 0,textColor INTEGER,iconUrl VARCHAR(100),perComplete INTEGER,taskDesc TEXT,startDate UNSIGNED BIG INT,endDate UNSIGNED BIG INT,dateAdd UNSIGNED BIG INT,other1 TEXT,other2 TEXT)";
	private final static String DBCreateTableCategoryInfos="CREATE TABLE CategoryInfos(_id INTEGER PRIMARY KEY,name TEXT,dateAdd UNSIGNED BIG INT,other1 TEXT,other2 TEXT)";
	private final static String DBDropTableTaskInfos="DROP TABLE IF EXISTS TaskInfos";
	private final static String DBDropTableCategoryInfos="DROP TABLE IF EXISTS CategoryInfos";

	private final static String DBTableNameTaskInfos="TaskInfos";
	private final static String DBTableNameCategoryInfos="CategoryInfos";


	public MyDatabaseAdapter(Context context) {
		super(context, DataBaseName, null,DataBaseVersion);
		mContext=context;		
	}

	private void InitDataBase(){
		if(null==mDataBase) mDataBase=getWritableDatabase();		
	}

	private void CloseDataBase(){
		if(null!=mDataBase && mDataBase.isOpen()) mDataBase.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DBCreateTableCategoryInfos);
		db.execSQL(DBCreateTableTaskInfos);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Cursor taskCursor=db.query(DBTableNameTaskInfos,null,null,null,null,null,null);
		ArrayList taskArr=new ArrayList();
		ArrayList categoryArr=new ArrayList();
		if((taskCursor != null) && (taskCursor.moveToFirst())){
			do{
				ContentValues content=new ContentValues();				
				content.put("name",taskCursor.getString(1));
				content.put("category",Integer.valueOf(taskCursor.getInt(2)));
				content.put("priority",Integer.valueOf(taskCursor.getInt(3)));
				content.put("status",Integer.valueOf(taskCursor.getInt(4)));
				content.put("textColor",taskCursor.getString(5));
				content.put("iconUrl",taskCursor.getString(6));
				content.put("perComplete",Integer.valueOf(taskCursor.getInt(7)));
				content.put("taskDesc",taskCursor.getString(8));
				content.put("startDate",Integer.valueOf(taskCursor.getInt(9)));
				content.put("endDate",Integer.valueOf(taskCursor.getInt(10)));
				content.put("dateAdd",Integer.valueOf(taskCursor.getInt(11)));
				content.put("other1",taskCursor.getString(12));
				content.put("other2",taskCursor.getString(13));
				taskArr.add(content);
			}
			while(taskCursor.moveToNext());
			taskCursor.close();
		}

		Cursor categoryCursor=db.query(DBTableNameCategoryInfos,null,null,null,null,null,null);
		if(categoryCursor!=null && categoryCursor.moveToFirst()){
			do{
				ContentValues content=new ContentValues();				
				content.put("name",taskCursor.getString(1));
				content.put("dateAdd",Integer.valueOf(taskCursor.getInt(2)));
				content.put("other1",taskCursor.getString(3));
				content.put("other2",taskCursor.getString(4));
				categoryArr.add(content);
			}while(categoryCursor.moveToNext());
			categoryCursor.close();
		}
		db.execSQL(DBDropTableCategoryInfos);
		db.execSQL(DBDropTableTaskInfos);
		onCreate(db);
		for(int i=0;;i++){
			db.insert(DBTableNameTaskInfos,null,(ContentValues)taskArr.get(i));			
			if(i>=taskArr.size()) break;
		}
		for(int j=0;;j++){
			db.insert(DBTableNameCategoryInfos,null,(ContentValues)categoryArr.get(j));			
			if(j>=categoryArr.size()) break;
		}
	}

	public long TaskInfo_Add(TaskInfo task)
	{
		ContentValues taskValues = new ContentValues();
		taskValues.put("name",task.Name);
		taskValues.put("category",Integer.valueOf(task.Category.Id));
		taskValues.put("priority",Integer.valueOf(task.Priority.getValue()));
		taskValues.put("status",task.Status.getValue());
		taskValues.put("textColor",task.TextColor);
		taskValues.put("iconUrl",task.IconUrl);
		taskValues.put("perComplete",Integer.valueOf(task.PerComplete));
		taskValues.put("taskDesc",task.TaskDesc);
		taskValues.put("startDate",Long.valueOf(task.StartDate));
		taskValues.put("endDate",Long.valueOf(task.EndDate));
		taskValues.put("dateAdd",Long.valueOf(task.DateAdd));
		taskValues.put("other1",task.Other1);
		taskValues.put("other2",task.Other2);
		
		InitDataBase();
		return mDataBase.insert(DBTableNameTaskInfos, null, taskValues);				
	}

	public long TaskInfo_StatusChange(int taskId,TaskStatus status){
		return 0;
	}
	
}
