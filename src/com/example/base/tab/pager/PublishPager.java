package com.example.base.tab.pager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.activity.Login_Activity;
import com.example.activity.SwitchPicActivity;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class PublishPager extends BasePager {
	private View view;
	private ScrollView s1, s2;
	private ImageView switchpic;

	public PublishPager(Context context) {
		super(context);

	}

	@Override
	public void initView() {

		view = LayoutInflater.from(mActivity).inflate(R.layout.publish, null);
		flContent.addView(view);
		switchpic = (ImageView) view.findViewById(R.id.switch_pic);

	}

	@Override
	public void initData() {
		switchpic.setOnClickListener(new myOnClick());
	}
/**
 * 事件点击处理事件
 * @author WINTER
 *
 */
	class myOnClick implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.switch_pic:
				Intent in = new Intent(mActivity, SwitchPicActivity.class);
				mActivity.startActivity(in);
				break;

			default:
				break;
			}
		}

	}
}
