package com.example.base.tab.pager;

import com.example.activity.Login_Activity;
import com.example.base.utils.Utils;
import com.example.frag.R;
import com.example.viewpage.BasePager;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class SettingPager extends BasePager {
	private RelativeLayout rel_login, rel_sett;
	private View view;

	public SettingPager(Activity activity) {
		super(activity);

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		view = LayoutInflater.from(mActivity).inflate(R.layout.setting_pager,
				null);
		flContent.addView(view);
		rel_login = (RelativeLayout) view.findViewById(R.id.lin_login_id);
		rel_sett = (RelativeLayout) view.findViewById(R.id.sett_id);

	}

	@Override
	public void initData() {
		rel_login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent in = new Intent(mActivity, Login_Activity.class);
				mActivity.startActivity(in);
			}
		});
		rel_sett.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (!Login_Activity.isFlag) {
					
					Intent in = new Intent(mActivity, Login_Activity.class);
					mActivity.startActivity(in);
				} else {
					Login_Activity.isFlag = false;
					Utils.ToastShort(mActivity, "用户已取消");
				}

			}
		});

	}

}
