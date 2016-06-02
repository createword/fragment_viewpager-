package com.example.base.tab.pager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activity.SelectSchool_Activity;
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

public class HomePager extends BasePager implements OnClickListener {

	private TextView text;
	private AtestCase casenew = new AtestCase();

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initViewData() {
		ViewIsNetWorkState();
		base_left.setText("所有学校");
		tvTitle.setText("首页");
		base_left.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.base_left:
        Intent in=new Intent(mActivity,SelectSchool_Activity.class);
        mActivity.startActivity(in);
			break;

		default:
			break;
		}

	}
}
