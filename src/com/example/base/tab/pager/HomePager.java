package com.example.base.tab.pager;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.httputils.AppCustomDialog;
import com.example.base.httputils.HttpUtils;
import com.example.base.httputils.IpUtils;
import com.example.base.httputils.Utils;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class HomePager extends BasePager {
	String Purl = null;
	private Handler handlerUI = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Utils.ToastShort(mActivity, Purl);
				AppCustomDialog.getInstance(mActivity).StatusDialog(2);
				break;
			}
		};
	};

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("JSON½âÎö");
		View view = LayoutInflater.from(mActivity).inflate(R.layout.home_pager,
				null);
		flContent.addView(view);
		view.findViewById(R.id.btn_asy).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						final String url = IpUtils.MainIpServer+"/webServlet/MyServlet";
						final Map<String, String> params = new HashMap<String, String>();
						params.put("name", "xiao");
						params.put("age", "12");
						AppCustomDialog.getInstance(mActivity).StatusDialog(1);
						new Thread() {
							public void run() {
								Purl = HttpUtils.GetByHttpClient(mActivity,
										url, params);
								handlerUI.sendEmptyMessage(1);
							};
						}.start();
					
					}
				});
	}

}
