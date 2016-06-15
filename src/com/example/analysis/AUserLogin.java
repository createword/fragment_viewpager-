package com.example.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.modle.UserLoginModel;

public class AUserLogin {
	public Map<String, Object> mapPara;

	public AUserLogin() {
		mapPara = new HashMap();
	}

	public Map<String, Object> setUserPara(JSONObject jObject) {
		try {
			JSONObject object = jObject.getJSONObject("data");
			Boolean bool = object.getBoolean("isflag");// 判断是否登录成功
			String msg = object.getString("msg");// 是否登录成功信息false and true
			mapPara.put("isflag", bool);
			mapPara.put("msg", msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapPara;

	}
}
