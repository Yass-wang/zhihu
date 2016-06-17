package com.thmoo.zhihu.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Http {

	public static final String NEWSLIST_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
	
	public static final String NEWSDETAIL = "http://news-at.zhihu.com/api/4/news/";
	
	
	public static String getFromServer(String urlAddr) throws IOException{
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String readLine = null;
				StringBuilder builder = new StringBuilder();
				while ((readLine = reader.readLine()) != null) {
					builder.append(readLine);
				}
				reader.close();
				return builder.toString();
			}else {
				throw new IOException("Network Error - response code: " + conn.getResponseCode());
			}
		} finally{
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	public static String getLatestNewsList() throws IOException{
		return getFromServer(NEWSLIST_LATEST);
	}
	
	public static String getNewsDetail(int id) throws IOException{
		return getFromServer(NEWSDETAIL+id);
	}
}
