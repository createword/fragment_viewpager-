package com.example.analysis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.modle.SchoolInfo;
import com.google.gson.JsonObject;

public class AqueryNameShowInfo {

	private ArrayList<SchoolInfo> arryList;
	private SchoolInfo Sinfo;

	public ArrayList<SchoolInfo> AllasyJson(JSONObject job) {

		try {
			int ret = job.getInt("ret");
			arryList = new ArrayList<SchoolInfo>();
			if (ret == 0) {
				JSONArray array = job.getJSONArray("info");
				for (int i = 0; i < array.length(); i++) {
					Sinfo = new SchoolInfo();

					Sinfo.setSchoolname(array.getJSONObject(i).getString("schoolname"));
					Sinfo.setInfotitle(array.getJSONObject(i).getString("infotitle"));
					Sinfo.setPrice(array.getJSONObject(i).getString("price"));
					Sinfo.setTime(array.getJSONObject(i).getString("time"));
					Sinfo.setInfobody(array.getJSONObject(i).getString("infobody"));
					arryList.add(Sinfo);
				}

			} else {
				return null;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	System.out.println(e.getMessage());
		}

		return arryList;

	}

	/**
	 * 按学校名查寻
	 * 
	 * @param job
	 * @return
	 */
	public ArrayList<SchoolInfo> astJson(JSONObject job) {

		try {
			int ret = job.getInt("ret");
			arryList = new ArrayList<SchoolInfo>();
			if (ret == 0) {
				JSONArray array = job.getJSONArray("info");
				for (int i = 0; i < array.length(); i++) {
					Sinfo = new SchoolInfo();

					Sinfo.setSchoolname(array.getJSONObject(i).getString("schoolname"));
					Sinfo.setInfotitle(array.getJSONObject(i).getString("infotitle"));
					Sinfo.setPrice(array.getJSONObject(i).getString("price"));
					Sinfo.setTime(array.getJSONObject(i).getString("time"));
					Sinfo.setInfobody(array.getJSONObject(i).getString("infobody"));
					arryList.add(Sinfo);
				}

			} else {
				return null;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arryList;

	}

}
