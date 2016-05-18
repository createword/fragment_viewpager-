package com.example.base.tab.pager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analysis.AtestCase;
import com.example.analysis.BaseAsyTaskInterface;
import com.example.base.httputils.AppCustomDialog;
import com.example.base.httputils.DoHttpAsyn;
import com.example.base.httputils.HttpUtils;
import com.example.base.httputils.IpUtils;
import com.example.base.httputils.LogUtils;
import com.example.base.httputils.Utils;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class HomePager extends BasePager {
	View view;
	private TextView text;
	private AtestCase casenew = new AtestCase();

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initViewData() {
		ViewErrorState();
		tvTitle.setText("Ê×Ò³");
		view = LayoutInflater.from(mActivity)
				.inflate(R.layout.home_pager, null);
		flContent.addView(view);
		text = (TextView) view.findViewById(R.id.menu);
		view.findViewById(R.id.btn_asy).setOnClickListener(
				new OnClickListener() {
					@SuppressWarnings("unchecked")
					public void onClick(View v) {
						final String url = IpUtils.MainIpServer
								+ "/webServlet/student";
						final Map<String, String> params = new HashMap<String, String>();
						params.put("name", "xiaoming");
						params.put("age", "12");

						new DoHttpAsyn(mActivity,
								new BaseAsyTaskInterface() {
									public void darSuccess(JSONObject result) {
										for (String str : casenew
												.getArrayList(result)) {
											text.setText(str);
										}
                             
									}
									public void dataError() {

									}
								
								
								}).execute( url,params);

					}
				});
	}

}
