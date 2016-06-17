package com.thmoo.zhihu.activity;

import java.util.List;

import com.thmoo.zhihu.R;
import com.thmoo.zhihu.adapter.NewsAdapter;
import com.thmoo.zhihu.db.DailyNewsDB;
import com.thmoo.zhihu.entity.News;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FavouriteActivity extends Activity implements OnItemClickListener{

	private NewsAdapter adapter;
	private List<News> favouriteList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favourite);
		ListView lv = (ListView) findViewById(R.id.lv_favourite);
		favouriteList = DailyNewsDB.getInstance(this).loadFavouriteList();
		adapter = new NewsAdapter(this, R.layout.listview_item, favouriteList);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		favouriteList = DailyNewsDB.getInstance(this).loadFavouriteList();
		adapter.clear();
		adapter.addAll(favouriteList);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		NewsDetailActivity.startActivity(this, adapter.getItem(position));
	}
	
}
