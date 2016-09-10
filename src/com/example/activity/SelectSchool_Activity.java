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
import com.example.modle.CategoryModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SelectSchool_Activity extends BaseActivity implements BaseAsyTaskInterface {
	private ListView provinceListView, schoolListView;
	private SelectProAdapter Padapter;
	private SelectSchoolAdapter Sadapter;
	private String[] arryProvince;
	private ArrayList<String> arrySchool;
	private String url = IpUtils.MainIpServer + "/yitiaojie/SelectProvinceJson?";
	private String cid = "1";
	private int mPosition;

	@SuppressLint("ResourceAsColor")
	@Override
	public void initView() {
		act_base_title.setText("ѧУѡ��");
		ViewIsNetWorkState(LayoutInflater.from(this).inflate(R.layout.activity_selectschool, null), 0);
		provinceListView = (ListView) findViewById(R.id.province);
		schoolListView = (ListView) findViewById(R.id.school);

	}

	@Override
	public void initData() {

		arryProvince = getResources().getStringArray(R.array.navigate);
		List<HashMap<String, CategoryModel>> categoriesList = new ArrayList<HashMap<String, CategoryModel>>();
		// �ָ����������ַ���
		for (int i = 0; i < arryProvince.length; i++) {
			String[] temp = arryProvince[i].split("[|]");
			if (temp.length == 2) {
				cid = temp[0];
				String title = temp[1];
				CategoryModel type = new CategoryModel(StringUtil.String2Int(cid), title);
				HashMap<String, CategoryModel> hashMap = new HashMap<String, CategoryModel>();
				hashMap.put("category_title", type);
				categoriesList.add(hashMap);
			}
		}

		// ��ʼ����һ��Item ������
		Map<String, String> params = new HashMap<String, String>();
		params.put("pid", "1");
		new DoHttpAsyn(SelectSchool_Activity.this, this).execute(url, params);

		provinceListView.setOnItemClickListener(new arryProOnItemClick());
		Padapter = new SelectProAdapter(this, categoriesList);
		provinceListView.setAdapter(Padapter);
	}

	/**
	 * ѧУ������ʡ�ݵ���¼�
	 * 
	 * @author zhanghengming
	 *
	 */
	public class arryProOnItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {

			Map<String, String> params = new HashMap<String, String>();
			params.put("pid", Integer.toString(position + 1));// ���ݿ���ĵ�һ�����ݾ��Ǵ�1��ʼ�����Լ�1
			new DoHttpAsyn(SelectSchool_Activity.this, SelectSchool_Activity.this).execute(url, params);
			Padapter.selectPosition(position);
			Padapter.notifyDataSetInvalidated();
			if (Sadapter == null) { // ��������ֹͣ����SadapterΪ�� �ͻᱨ����˵��ж�
				return;
			} else {

				Sadapter.notifyDataSetInvalidated();

			}

		}

	}

	/**
	 * �����������ݷ���jsonresult
	 */
	public void dataSuccess(JSONObject result) {
		SchoolCase sCase = new SchoolCase();
		arrySchool = sCase.AsySchoolToJson(result);

		Sadapter = new SelectSchoolAdapter(SelectSchool_Activity.this, arrySchool);
		schoolListView.setAdapter(Sadapter);
		schoolListView.setOnItemClickListener(new schoolOnItemClick());
		Sadapter.notifyDataSetInvalidated();

	}

	public void dataError(String msg) {
		Utils.ToastShort(getBaseContext(), msg);
		return;
	}

	/**
	 * ѧУ���Ƶ���¼�
	 * 
	 * @author zhanghengmign
	 *
	 */
	public class schoolOnItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {

			// Utils.ToastShort(getApplication(), position + "");
			Intent intent = new Intent();
			intent.putExtra("KEY_POSITION", position);
			intent.putStringArrayListExtra("schoolList", arrySchool);
			setResult(RESULT_OK, intent);
			finish();
		}
	}
}
