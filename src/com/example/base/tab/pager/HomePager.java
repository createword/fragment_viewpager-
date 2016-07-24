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

import com.example.activity.Login_Activity;
import com.example.activity.SelectSchool_Activity;
import com.example.adapter.HomeAdapter;
import com.example.adapter.TopViewAdapter;
import com.example.analysis.AtestCase;
import com.example.analysis.AtopViewData;
import com.example.analysis.BaseAsyTaskInterface;
import com.example.base.utils.AppCustomDialog;
import com.example.base.utils.DoHttpAsyn;
import com.example.base.utils.HttpUtils;
import com.example.base.utils.IpUtils;
import com.example.base.utils.LogUtils;
import com.example.base.utils.Utils;
import com.example.custom.view.RefreshListView;
import com.example.custom.view.RefreshListView.onRefreshDataListener;
import com.example.frag.R;
import com.example.modle.TopNewPicModel;

import com.example.viewpage.BasePager;
import com.example.viewpage.TopViewPager;

public class HomePager extends BasePager implements OnClickListener, OnItemClickListener, BaseAsyTaskInterface {
	private TextView text;
	private View view, headView;
	private ArrayList<String> strs;
	private RefreshListView homListView;
	private TopViewPager topViewPager;
	private AtopViewData atopdata;
	private String url = IpUtils.MainIpServer + "/NewsShow/topShow?";

	public HomePager(Activity activity) {
		super(activity);
		
	}

	@Override
	public void initView() {
		ViewIsNetWorkState();
		base_left.setText("所有学校");
		tvTitle.setText("首页");
		view = LayoutInflater.from(mActivity).inflate(R.layout.home_pager, null);
		headView = View.inflate(mActivity, R.layout.home_top_page, null);
		topViewPager = (TopViewPager) headView.findViewById(R.id.topViewPager);
		homListView = (RefreshListView) view.findViewById(R.id.home_listview);
		homListView.addHeaderView(headView);
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
		topViewPager.setAdapter(new TopViewAdapter());
		homListView.setOnRefreshListener(new onRefreshDataListener() {

			public void onRefreshData() {

			}

			public void onLoadMore() {

			}

		});

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.base_left:
			new DoHttpAsyn(mActivity, this).execute(url, null);
	/*		Intent in = new Intent(mActivity, SelectSchool_Activity.class);
			mActivity.startActivity(in);*/
			break;

		default:
			break;
		}

	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent in = new Intent(mActivity, Login_Activity.class);
		mActivity.startActivity(in);
		 Utils.ToastShort(mActivity, position + "");
	}

	/**
	 * 处理网络数据返回的结果
	 */
	@Override
	public void dataSuccess(JSONObject result) {
		atopdata=new AtopViewData();
		TopNewPicModel d=atopdata.AsyTopNewsToJson(result);

		Utils.ToastShort(mActivity,d.getUrl());
		
	}

	@Override
	public void dataError(String msg) {
	

	}

}
