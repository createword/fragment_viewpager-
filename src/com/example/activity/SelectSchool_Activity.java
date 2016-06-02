package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.example.adapter.SelectProAdapter;
import com.example.adapter.SelectSchoolAdapter;
import com.example.analysis.BaseAsyTaskInterface;
import com.example.analysis.SchoolCase;
import com.example.base.utils.DoHttpAsyn;
import com.example.base.utils.IpUtils;
import com.example.base.utils.StringUtil;
import com.example.base.utils.Utils;

import com.example.frag.R;
import com.example.modle.Category;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SelectSchool_Activity extends BaseActivity implements
		BaseAsyTaskInterface {
	private ListView provinceListView, schoolListView;

	private SelectProAdapter Padapter;
	private SelectSchoolAdapter Sadapter;
	private String[] arryProvince;
	private ArrayList<String> arrySchool;
	private String url = IpUtils.MainIpServer
			+ "/yitiaojie/SelectProvinceJson?";
	private String cid = "1";

	@Override
	public void initView() {
		act_base_title.setText("学校选择");
		ViewIsNetWorkState(
				LayoutInflater.from(this).inflate(
						R.layout.activity_selectschool, null), 0);
		provinceListView = (ListView) findViewById(R.id.province);
		schoolListView = (ListView) findViewById(R.id.school);

	}

	@Override
	public void initData() {

		arryProvince = getResources().getStringArray(R.array.navigate);
		List<HashMap<String, Category>> categoriesList = new ArrayList<HashMap<String, Category>>();
		// 分割新闻类型字符串
		for (int i = 0; i < arryProvince.length; i++) {
			String[] temp = arryProvince[i].split("[|]");
			if (temp.length == 2) {
				cid = temp[0];
				String title = temp[1];
				Category type = new Category(StringUtil.String2Int(cid), title);
				HashMap<String, Category> hashMap = new HashMap<String, Category>();
				hashMap.put("category_title", type);
				categoriesList.add(hashMap);
			}
		}
		Padapter = new SelectProAdapter(this, categoriesList);
		provinceListView.setAdapter(Padapter);
		Map<String, String> params = new HashMap<String, String>();
		params.put("pid", "1");
		new DoHttpAsyn(SelectSchool_Activity.this, this).execute(url, params);
		provinceListView.setOnItemClickListener(new arryProOnItemClick());
	}

	public class arryProOnItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View arg1, int position,
				long arg3) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pid", Integer.toString(position + 1));
			new DoHttpAsyn(SelectSchool_Activity.this,
					SelectSchool_Activity.this).execute(url, params);
			if (Sadapter == null) {
				return;
			} else {
				Sadapter.notifyDataSetInvalidated();
			}

		}

	}

	/**
	 * 访问网络数据返回jsonresult
	 */
	public void darSuccess(JSONObject result) {
		SchoolCase sCase = new SchoolCase();
		arrySchool = sCase.AsySchoolToJson(result);

		Sadapter = new SelectSchoolAdapter(SelectSchool_Activity.this,
				arrySchool, StringUtil.String2Int(cid));
		schoolListView.setAdapter(Sadapter);

		Sadapter.notifyDataSetInvalidated();

	}

	public void dataError(String msg) {
		Utils.ToastShort(getBaseContext(), msg);
		return;
	}

}
