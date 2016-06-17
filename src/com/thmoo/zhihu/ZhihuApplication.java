package com.thmoo.zhihu;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.Context;

public class ZhihuApplication extends Application {

	
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(this);
	}
	
	
	public static void initImageLoader(Context context){
		ImageLoaderConfiguration config= ImageLoaderConfiguration.createDefault(context);
		ImageLoader.getInstance().init(config);
	}
}
