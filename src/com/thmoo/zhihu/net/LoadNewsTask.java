package com.thmoo.zhihu.net;

import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import com.thmoo.zhihu.adapter.NewsAdapter;
import com.thmoo.zhihu.entity.News;
import com.thmoo.zhihu.utils.JsonHelper;
import com.thmoo.zhihu.utils.Utility;

import android.os.AsyncTask;
import android.util.Log;

public class LoadNewsTask extends AsyncTask<Void, Void, List<News>> {

	private NewsAdapter adapter;
	private OnRefreshFinishListener listener;
	
	public LoadNewsTask(NewsAdapter adapter) {
		this.adapter = adapter;
	}
	public LoadNewsTask(NewsAdapter adapter,OnRefreshFinishListener listener) {
		this.adapter = adapter;
		this.listener = listener;
	}
	
	@Override
	protected List<News> doInBackground(Void... params) {
		List<News> newslist = null;
		try {
			String json = Http.getLatestNewsList();
			if (json != null) {
				newslist = JsonHelper.parseJsonToList(json);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newslist;
	}

	protected void onPostExecute(List<News> newslist) {
		if (newslist != null) {
			adapter.refreshNewsList(newslist);
			if (listener != null) {
				listener.afterRefresh();
			}
		}else {
			Log.i("tag", "网络太慢");
		}
	}

	public interface OnRefreshFinishListener{
		public void afterRefresh();
	}
	
}
