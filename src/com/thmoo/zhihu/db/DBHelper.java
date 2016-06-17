package com.thmoo.zhihu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "daily_news.db";
	public static final String TABLE_NAME = "daily_news_fav";
	public static final int DB_VERSION = 1;
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NEWS_ID = "news_id";
	public static final String COLUMN_NEWS_TITLE = "news_title";
	public static final String COLUMN_NEWS_IMAGE = "news_image";
	
	public static final String DATABASE_CREATE = "create table "+ TABLE_NAME
							+ "(" + COLUMN_ID + " integer primary key autoincrement,"
							+ COLUMN_NEWS_ID + " integer unique,"
							+ COLUMN_NEWS_TITLE + " text,"
							+ COLUMN_NEWS_IMAGE + " text)";
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists" + TABLE_NAME);
		onCreate(db);
	}

}
