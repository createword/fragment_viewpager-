package com.example.base.tab.pager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.frag.R;
import com.example.viewpage.BasePager;

public class LoginPager extends BasePager{

	public LoginPager(Activity activity) {
		super(activity);
	
	}
	@Override
	public void CustomOnCreate() {
		ViewIsNetWorkState();
		base_left.setVisibility(View.GONE);
		tvTitle.setText("µÇÂ½");
	
		flContent.addView(LayoutInflater.from(mActivity)
				.inflate(R.layout.login_activity, null));
		initData();
	}

	public void initData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}
	
}

