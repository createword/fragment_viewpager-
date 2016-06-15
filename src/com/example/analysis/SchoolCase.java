package com.example.analysis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.base.utils.Utils;
import com.example.modle.CategoryModel;

public class SchoolCase {
	private ArrayList<String> listString;

	public ArrayList<String> AsySchoolToJson(JSONObject jObject) {

		try {
			// 获取返回码，0表示成功
			int retCode = jObject.getInt("ret");
			if (0 == retCode) {

				listString = new ArrayList<String>();
				JSONArray array = jObject.getJSONArray("info");
				for (int i = 0; i < array.length(); i++) {

					JSONObject obj = array.getJSONObject(i);
					listString.add(obj.getString("title"));
				}
			}else{
				return null;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listString;

	}
}
