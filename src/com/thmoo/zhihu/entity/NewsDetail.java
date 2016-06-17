package com.thmoo.zhihu.entity;

import java.util.ArrayList;

public class NewsDetail {

	private String body;
	private ArrayList<String> css;
	private String ga_prefix;
	private long id;
	private String image;
	private String image_source;
	private ArrayList<String> images;
	private ArrayList<String> js;
	private String share_url;
	private String title;
	private int type;
	
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ArrayList<String> getCss() {
		return css;
	}
	public void setCss(ArrayList<String> css) {
		this.css = css;
	}
	public String getGa_prefix() {
		return ga_prefix;
	}
	public void setGa_prefix(String ga_prefix) {
		this.ga_prefix = ga_prefix;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage_source() {
		return image_source;
	}
	public void setImage_source(String image_source) {
		this.image_source = image_source;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public ArrayList<String> getJs() {
		return js;
	}
	public void setJs(ArrayList<String> js) {
		this.js = js;
	}
	public String getShare_url() {
		return share_url;
	}
	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
}
