package com.example.base.tab.pager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.analysis.AtestCase;
import com.example.analysis.BaseAsyTaskInterface;
import com.example.base.utils.AppCustomDialog;
import com.example.base.utils.Constants;
import com.example.base.utils.DoHttpAsyn;
import com.example.base.utils.IpUtils;
import com.example.base.utils.Utils;
import com.example.exception.ConnectException;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class NewsPager extends BasePager {

	View view;
	private TextView text;
	private ImageView switch_pic;
	private AtestCase casenew = new AtestCase();

	public NewsPager(Activity activity) {
		super(activity);
	}

	@Override
	public void CustomOnCreate() {
		ViewIsNetWorkState();
		base_left.setText("所有学校");
		tvTitle.setText("首页");
	/*	view = LayoutInflater.from(mActivity)
				.inflate(R.layout.news_pager, null);
		flContent.addView(view);*/
	//	initData();
	}


	public void initData() {
		// TODO Auto-generated method stub
		text = (TextView) view.findViewById(R.id.menu);
		view.findViewById(R.id.btn_asy).setOnClickListener(
				new OnClickListener() {
					@SuppressWarnings("unchecked")
					public void onClick(View v) {
						final String url = IpUtils.MainIpServer
								+ "/blog/test";
						final Map<String, String> params = new HashMap<String, String>();
						params.put("id", "1");
						new DoHttpAsyn(mActivity,
								new BaseAsyTaskInterface() {
									public void dataSuccess(JSONObject result) {
										for (String[] str : casenew.getArrayList(result)) {
											text.setText(str[0]+str[1]);
										}
                             
									}
									public void dataError(String msg) {

									}
								
								
								}).execute( url,params);

					}
				});
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}
	
}
