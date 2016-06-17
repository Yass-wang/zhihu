package com.thmoo.zhihu.activity;

import com.thmoo.zhihu.R;
import com.thmoo.zhihu.db.DailyNewsDB;
import com.thmoo.zhihu.entity.News;
import com.thmoo.zhihu.net.LoadNewsDetailTask;
import com.thmoo.zhihu.utils.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class NewsDetailActivity extends Activity {

	private WebView mWebView;
	private boolean isFavourite;
	private News news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		mWebView = (WebView) findViewById(R.id.webview);
		setWeb(mWebView);
		news = (News) getIntent().getSerializableExtra("news");
		new LoadNewsDetailTask(mWebView).execute(news.getId());
		isFavourite = DailyNewsDB.getInstance(this).isFavourite(news);
	}
	private void setWeb(WebView mWebView){
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setVerticalScrollBarEnabled(false);
		mWebView.setHorizontalScrollBarEnabled(false);
	}
	
	public static void startActivity(Context context, News news) {
        if (Utility.checkNetworkConnection(context)) {
            Intent i = new Intent(context, NewsDetailActivity.class);
            i.putExtra("news", news);
            context.startActivity(i);
        } else {
            Utility.noNetworkAler(context);
        }
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		if (isFavourite) {
			menu.findItem(R.id.action_favourite).setIcon(R.drawable.fav_active);
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_favourite) {
			if (isFavourite) {
				item.setIcon(R.drawable.fav_normal);
				DailyNewsDB.getInstance(this).deleteFavourite(news);
				isFavourite = false;
			}else {
				item.setIcon(R.drawable.fav_active);
				DailyNewsDB.getInstance(this).saveFavourite(news);
				isFavourite = true;
			}
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
