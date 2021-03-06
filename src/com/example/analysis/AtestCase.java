package com.example.analysis;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class AtestCase {
	private ArrayList<String[]> arrayList;

	public AtestCase() {
		arrayList = new ArrayList<String[]>();
	}

	public ArrayList<String[]> getArrayList(JSONObject ob) {
		try {
			int code = ob.getInt("ret");
			if (code == 1) {
				JSONObject JO = ob.getJSONObject("ob");
				arrayList.add(new String[]{JO.getString("title"),JO.getString("content")});
			
				
			}else{
				arrayList.add(new String[]{ob.getString("msg")});
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayList;

	}
}
