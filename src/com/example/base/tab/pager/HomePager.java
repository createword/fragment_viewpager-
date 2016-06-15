package com.example.base.tab.pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activity.SelectSchool_Activity;
import com.example.adapter.HomeAdapter;
import com.example.analysis.AtestCase;
import com.example.analysis.BaseAsyTaskInterface;
import com.example.base.utils.AppCustomDialog;
import com.example.base.utils.DoHttpAsyn;
import com.example.base.utils.HttpUtils;
import com.example.base.utils.IpUtils;
import com.example.base.utils.LogUtils;
import com.example.base.utils.Utils;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class HomePager extends BasePager implements OnClickListener,OnRefreshListener,OnItemClickListener {
	private TextView text;
	private ListView homListView;
	private View view;
	private ArrayList<String> strs;
	SwipeRefreshLayout refresh_layout;
	// 定义一个String数组用来显示ListView的内容private ListView lv;/** Called when the act
	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		ViewIsNetWorkState();
		base_left.setText("所有学校");
		tvTitle.setText("首页");
		view = LayoutInflater.from(mActivity)
				.inflate(R.layout.home_pager, null);
		homListView = (ListView) view.findViewById(R.id.home_listview);
		refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
		refresh_layout.setColorScheme(R.color.green, R.color.fenread, R.color.blue, R.color.read);
		flContent.addView(view);
		
	}
	@Override
	public void initData() {
		strs = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			strs.add("中华人民共和国");
		}
		
		base_left.setOnClickListener(this);
		homListView.setAdapter(new HomeAdapter(mActivity, strs));
		homListView.setOnItemClickListener(this);
		refresh_layout.setOnRefreshListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.base_left:
			Intent in = new Intent(mActivity, SelectSchool_Activity.class);
			mActivity.startActivity(in);
			break;

		default:
			break;
		}

	}

	public void onRefresh() {
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	
		Utils.ToastShort(mActivity, position+"");
	}



}
