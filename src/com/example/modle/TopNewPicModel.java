package com.example.modle;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.modle.TopNewPicModel.TopPicNews;

/**
 * �ü��ϵ���ʽ ��װmodel �ô� �ӿ��ȡ���ݵ��ٶ�
 * 
 * @author WINTER
 *
 */
public class TopNewPicModel {

	public ArrayList<TopPicNews> picNewsList;
	public int resCode;

	public class TopPicNews {

		public TopPicNews(String url, String title) {
			picNewsList=new ArrayList<TopNewPicModel.TopPicNews>();
			this.title = title;

			this.url = url;
		}

		public String id;
		public String title;
		public int type;
		public String url;

	}

	
}
