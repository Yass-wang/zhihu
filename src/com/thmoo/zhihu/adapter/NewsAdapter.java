package com.thmoo.zhihu.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thmoo.zhihu.R;
import com.thmoo.zhihu.entity.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {
	
	private LayoutInflater mInflater;
	private int resource;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_launcher)
			.showImageOnFail(R.drawable.ic_launcher)
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.build();
	
	public NewsAdapter(Context context, int resource,
			List<News> objects) {
		super(context, resource, objects);
		this.mInflater = LayoutInflater.from(context);
		this.resource = resource;
	}

	public NewsAdapter(Context context, int resource) {
		super(context, resource);
		this.mInflater = LayoutInflater.from(context);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(resource, null);
			viewHolder.newsImage = (ImageView) convertView.findViewById(R.id.news_image);
			viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.news_title);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		News news = getItem(position);
		imageLoader.displayImage(news.getImage(), viewHolder.newsImage,options);
		viewHolder.newsTitle.setText(news.getTitle());
		return convertView;
	}
	
	class ViewHolder{
		ImageView newsImage;
		TextView newsTitle;
	}
	
	public void refreshNewsList(List<News> newslist){
		clear();
		addAll(newslist);
		notifyDataSetChanged();
	}

}
