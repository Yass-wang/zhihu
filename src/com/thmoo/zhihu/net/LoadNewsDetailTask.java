package com.thmoo.zhihu.net;

import java.io.IOException;

import org.json.JSONException;

import com.thmoo.zhihu.entity.NewsDetail;
import com.thmoo.zhihu.utils.JsonHelper;

import android.os.AsyncTask;
import android.webkit.WebView;

public class LoadNewsDetailTask extends AsyncTask<Integer, Void, NewsDetail> {

	private WebView mWebView;
	
	public LoadNewsDetailTask(WebView mWebView) {
		this.mWebView = mWebView;
	}
	
	@Override
	protected NewsDetail doInBackground(Integer... params) {
		NewsDetail newsDetail = null;
		try {
			newsDetail = JsonHelper.parseJsonToDetail(Http.getNewsDetail(params[0]));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsDetail;
	}
	
	protected void onPostExecute(NewsDetail mNewsDetail) {
		String headerImage;
		if (mNewsDetail.getImage() ==null || mNewsDetail.getImage()=="") {
			headerImage = "file:///android_assets/news_detail_header_image.jpg";
		}else {
			headerImage = mNewsDetail.getImage();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"img-wrap\">")
        .append("<h1 class=\"headline-title\">")
        .append(mNewsDetail.getTitle()).append("</h1>")
        .append("<span class=\"img-source\">")
        .append(mNewsDetail.getImage_source()).append("</span>")
        .append("<img src=\"").append(headerImage)
        .append("\" alt=\"\">")
        .append("<div class=\"img-mask\"></div>");
		String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
				+ mNewsDetail.getBody().replace("<div class=\"img-place-holder\">", sb.toString());
		mWebView.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);
	};

}
