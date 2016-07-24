package com.example.analysis;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.modle.TopNewPicModel;

public class AtopViewData {
	private TopNewPicModel newPicModel;

	public TopNewPicModel AsyTopNewsToJson(JSONObject jsonObject) {
		// String res = result.toString().replaceAll("\\/", "/");//这里注意json
		// 解析URL 的//时/会解析为\/ for 循环加断电要在循环外部加
		try {

			int resCode = jsonObject.getInt("ret");

			if (resCode == 200) {

				JSONArray array = jsonObject.getJSONArray("info");
				for (int i = 0; i < array.length(); i++) {
					newPicModel = new TopNewPicModel();
					JSONObject ob = array.getJSONObject(i);
					String picUrl = ob.getString("picurl");
				//	String url=picUrl.replaceAll("\\/", "/");
					String pictitle = ob.getString("pictitle");
					
					newPicModel.setTitle(pictitle);
					newPicModel.setUrl(picUrl);

				}
			}
			return newPicModel;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
