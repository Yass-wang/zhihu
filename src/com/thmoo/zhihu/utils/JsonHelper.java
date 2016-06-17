package com.thmoo.zhihu.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.thmoo.zhihu.entity.News;
import com.thmoo.zhihu.entity.NewsDetail;

public class JsonHelper {

	public static List<News> parseJsonToList(String json) throws JSONException{
		JSONObject newsObject = new JSONObject(json);
		JSONArray newsArray = newsObject.getJSONArray("stories");
		List<News> newslist = new ArrayList<News>();
		for (int i = 0; i < newsArray.length(); i++) {
			JSONObject newsInJson = newsArray.getJSONObject(i);
			int id = newsInJson.optInt("id");
			String title = newsInJson.optString("title");
			String image = (String) newsInJson.getJSONArray("images").get(0);
			News news = new News(id,title,image);
			newslist.add(news);
		}
		return newslist;
	}
	
	public static NewsDetail parseJsonToDetail(String json) throws JSONException{
		Gson gson = new Gson();
		return gson.fromJson(json, NewsDetail.class);
	}
}
