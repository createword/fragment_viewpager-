package com.example.base.tab.pager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.activity.Login_Activity;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class PublishPager extends BasePager {
	private View view;
	private ScrollView s1, s2;

	public PublishPager(Context context) {
		super(context);

	}

	@Override
	public void initView() {

			view = LayoutInflater.from(mActivity).inflate(R.layout.publish, null);
			flContent.addView(view);
		
		
		
	}

	@Override
	public void initData() {
		
	}

}
