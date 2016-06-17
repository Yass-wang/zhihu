package com.thmoo.zhihu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utility {

	public static Boolean checkNetworkConnection(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = cm.getActiveNetworkInfo();
		return network != null && network.isConnected();
	}
	
	public static void noNetworkAler(Context context){
		Toast.makeText(context, "无网络连接", Toast.LENGTH_SHORT).show();
	}
	
	public static String getTime(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  E");
		return format.format(calendar.getTime());
	}
	
}
