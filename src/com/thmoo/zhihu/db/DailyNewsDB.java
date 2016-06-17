package com.thmoo.zhihu.db;

import java.util.ArrayList;
import java.util.List;

import com.thmoo.zhihu.entity.News;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DailyNewsDB {

	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private static DailyNewsDB mDailyNewsDB;
	
	private DailyNewsDB(Context context) {
		dbHelper = new DBHelper(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	public static synchronized DailyNewsDB getInstance(Context context){
		if (mDailyNewsDB == null) {
			mDailyNewsDB = new DailyNewsDB(context);
		}
		return mDailyNewsDB;
	}
	
	public void saveFavourite(News news){
		if (news != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.COLUMN_NEWS_ID, news.getId());
			values.put(DBHelper.COLUMN_NEWS_TITLE, news.getTitle());
			values.put(DBHelper.COLUMN_NEWS_IMAGE, news.getImage());
			db.insert(DBHelper.TABLE_NAME, null, values);
		}
	}
	
	public void deleteFavourite(News news){
		if (news != null) {
			db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_NEWS_ID+"=?", new String[]{news.getId()+""});
		}
	}
	
	public boolean isFavourite(News news){
		if (news != null) {
			Cursor cursor = db.query(DBHelper.TABLE_NAME, null, DBHelper.COLUMN_NEWS_ID+"=?", new String[]{news.getId()+""}, null, null, null);
			if (cursor.moveToNext()) {
				cursor.close();
				return true;
			}
		}
		return false;
	}
	
	public List<News> loadFavouriteList(){
		List<News> newsList = new ArrayList<News>();
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(1);
			String title = cursor.getString(2);
			String image = cursor.getString(3);
			News news = new News(id,title,image);
			newsList.add(news);
		}
		cursor.close();
		return newsList;
	}
	
	public synchronized void closeDB(){
		if (db != null) {
			db.close();
		}
	}
	
}
