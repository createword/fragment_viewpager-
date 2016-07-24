package com.example.analysis;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.modle.TopNewPicModel;
import com.example.modle.TopNewPicModel.TopPicNews;

public class AtopViewData {
	private TopNewPicModel newPicModel;

	public ArrayList<TopPicNews> AsyTopNewsToJson(JSONObject jsonObject) {

		try {

			newPicModel.resCode = jsonObject.getInt("ret");

			if (newPicModel.resCode == 200) {
				JSONArray array = jsonObject.getJSONArray("info");
				for (int i = 0; i < array.length(); i++) {
					JSONObject ob = array.getJSONObject(i);
					String url = ob.getString("Picurl");
					String picurl = ob.getString("pictitle");
					TopPicNews topnews = newPicModel.new TopPicNews(url, picurl);
					newPicModel.picNewsList.add(topnews);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newPicModel.picNewsList;

	}
}
