package com.example.base.tab.pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
import com.example.analysis.AqueryNameShowInfo;
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
import com.example.main.MainActivity;
import com.example.main.MainActivity.onResultParams;
import com.example.modle.SchoolInfo;
import com.example.modle.TopNewPicModel;

import com.example.viewpage.BasePager;
import com.example.viewpage.TopViewPager;
import com.viewpagerindicator.CirclePageIndicator;

public class HomePager extends BasePager
		implements OnClickListener, OnItemClickListener, OnPageChangeListener, onResultParams {
	private TextView text;
	private View view, headView;
	private ArrayList<SchoolInfo> arryList;
	private RefreshListView homListView;
	private TopViewPager topViewPager;
	private AtopViewData atopdata;
	private String url = IpUtils.MainIpServer + "/NewsShow/topShow?";
	private String queryUrl = IpUtils.MainIpServer + "/yitiaojie/getinfomation?";// 学校名查询
	private ArrayList<TopNewPicModel> arrayList;
	private TextView headTitle;
	private CirclePageIndicator indicator;
	private String scName;
	private ArrayList<SchoolInfo> InfoarryList;

	public HomePager(MainActivity activity) {
		super(activity);

	}

	@Override
	public void initView() {
		ViewIsNetWorkState();
		base_left.setText("所有学校");
		tvTitle.setText("首页");
		view = LayoutInflater.from(mActivity).inflate(R.layout.home_pager, null);
		headView = View.inflate(mActivity, R.layout.home_top_page, null);
		headTitle = (TextView) headView.findViewById(R.id.titleText);
		indicator = (CirclePageIndicator) headView.findViewById(R.id.id_indicator);

		topViewPager = (TopViewPager) headView.findViewById(R.id.topViewPager1);
		homListView = (RefreshListView) view.findViewById(R.id.home_listview);

		homListView.addHeaderView(headView);
		flContent.addView(view);

	}

	@Override
	public void initData() {
		mActivity.getMapParams(this);
		topViewPager.setOnPageChangeListener(this);
		new DoHttpAsyn(mActivity, new NewsShowFace()).execute(url, null);
		base_left.setOnClickListener(this);

		// 初始化第一条数据

		Map<String, String> StrParamas = new HashMap<String, String>();
		StrParamas.put("schoolname", "北京音乐学院");
		new DoHttpAsyn(mActivity, new QueryDataFace()).execute(queryUrl, StrParamas);

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
			Intent in = new Intent(mActivity, SelectSchool_Activity.class);
			mActivity.startActivityForResult(in, 1);

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

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		headTitle.setText(arrayList.get(arg0).title);

	}

	/**
	 * 获取onresult 数据
	 * 
	 * @param params
	 */
	@Override
	public void MapParams(Map<String, Object> params) {
		int position = (Integer) params.get("position");
		ArrayList<String> arrySchool = (ArrayList<String>) params.get("array");
		scName = arrySchool.get(position).toString();
		base_left.setText(scName);

		Map<String, String> StrParamas = new HashMap<String, String>();
		StrParamas.put("schoolname", arrySchool.get(position).toString());
		new DoHttpAsyn(mActivity, new QueryDataFace()).execute(queryUrl, StrParamas);

	}

	class NewsShowFace implements BaseAsyTaskInterface {

		/**
		 * 处理网络数据返回的结果
		 */
		@Override
		public void dataSuccess(JSONObject result) {
			atopdata = new AtopViewData();
			arrayList = atopdata.AsyTopNewsToJson(result);
			topViewPager.setAdapter(new TopViewAdapter(mActivity, arrayList));
			indicator.setViewPager(topViewPager);
			indicator.setOnPageChangeListener(HomePager.this);
			headTitle.setText(arrayList.get(0).title);

		}

		@Override
		public void dataError(String msg) {

		}

	}

	class QueryDataFace implements BaseAsyTaskInterface {

		/**
		 * 处理网络数据返回的结果
		 */
		@Override
		public void dataSuccess(JSONObject result) {
			AqueryNameShowInfo showInfo = new AqueryNameShowInfo();
			InfoarryList = showInfo.astJson(result);
			HomeAdapter homeAda = new HomeAdapter(mActivity, InfoarryList, scName);
			homListView.setAdapter(homeAda);
			homeAda.notifyDataSetInvalidated();

		}

		@Override
		public void dataError(String msg) {

		}

	}
}
