package com.example.viewpage;

import com.example.frag.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePager {

	public Activity mActivity;
	public View mRootView;// 布局对象

	public TextView tvTitle;// 标题对象

	public FrameLayout flContent;// 内容

	public ImageButton btnMenu;// 菜单按钮

	public BasePager(Activity activity) {
		mActivity = activity;
		initViews();
	}

	/**
	 * 初始化布局
	 */
	public void initViews() {
		mRootView = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) mRootView.findViewById(R.id.base_title);
		flContent = (FrameLayout) mRootView.findViewById(R.id.pager_content);

	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		
	}
	

}
