package com.example.modle;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * 用集合的形式 封装model 好处 加快获取数据的速度
 * 
 * @author WINTER
 *
 */
public class TopNewPicModel {


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String id;
	public String title;
	public int type;
	public String url;

}
