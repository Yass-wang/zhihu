package com.thmoo.zhihu.activity;

import com.thmoo.zhihu.R;
import com.thmoo.zhihu.adapter.NewsAdapter;
import com.thmoo.zhihu.db.DailyNewsDB;
import com.thmoo.zhihu.entity.News;
import com.thmoo.zhihu.net.LoadNewsTask;
import com.thmoo.zhihu.utils.Utility;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnRefreshListener,OnItemClickListener{

	private boolean isConnect = false;
	private NewsAdapter adapter;
	private SwipeRefreshLayout refreshLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}
	
	private void initViews(){
		isConnect = Utility.checkNetworkConnection(this);
		refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
		refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
				android.R.color.holo_green_light,android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		refreshLayout.setOnRefreshListener(this);
		ListView lv = (ListView) findViewById(R.id.lv_zhihu);
		adapter = new NewsAdapter(this, R.layout.listview_item);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		setTitle(Utility.getTime());
		if (isConnect) {
			new LoadNewsTask(adapter).execute();
		}else {
			Utility.noNetworkAler(this);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_favourite:
			startActivity(new Intent(MainActivity.this, FavouriteActivity.class));
			break;
		case R.id.action_settings:
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		Intent intent = new Intent(this, NewsDetailActivity.class);
//		intent.putExtra("news", (News)adapter.getItem(arg2));
//		startActivity(intent);
		NewsDetailActivity.startActivity(this, adapter.getItem(arg2));
	}

	@Override
	public void onRefresh() {
		isConnect = Utility.checkNetworkConnection(this);
		if (isConnect) {
			new LoadNewsTask(adapter,new LoadNewsTask.OnRefreshFinishListener() {
				
				@Override
				public void afterRefresh() {
					refreshLayout.setRefreshing(false);
				}
			}).execute();
			
		}else {
			Utility.noNetworkAler(this);
			refreshLayout.setRefreshing(false);
		}
	}
	
}
