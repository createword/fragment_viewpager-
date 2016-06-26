package com.example.viewpage;

import com.example.base.utils.Utils;
import com.example.exception.ConnectException;
import com.example.frag.R;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BasePager extends View { //继承View 目的是在ContentFragemnt里getTag()也可以不继承

	public BasePager(Context context) {
		super(context);

		mActivity = (Activity) context;
		BaseinitViews();

	}

	public Activity mActivity;
	public View mRootView;// 布局对象

	public TextView base_left;// 标题左边
	public TextView tvTitle;// 标题对象

	public FrameLayout flContent;// 内容

	public ImageButton btnMenu;// 菜单按钮

	public FrameLayout StateMainLayout;// 状态显示区

	/**
	 * 初始化布局
	 */
	public void BaseinitViews() {
		mRootView = View.inflate(mActivity, R.layout.base_pager, null);
		base_left = (TextView) mRootView.findViewById(R.id.base_left);
		tvTitle = (TextView) mRootView.findViewById(R.id.base_title);
		flContent = (FrameLayout) mRootView.findViewById(R.id.pager_content);
		StateMainLayout = (FrameLayout) mRootView.findViewById(R.id.pager_state_content);

	}

	/**
	 * 初始化数据
	 */
	public void CustomOnCreate() {
		flContent.removeAllViews();
		initView();
		initData();
	}

	public abstract void initView();

	public abstract void initData();

	/**
	 * 状态页面 调用时一定要在initData里初始化 当网络连接时显示内容没连接显示连接异常
	 */

	public void ViewIsNetWorkState() {
		try {
			if (Utils.isNetworkAvailable(mActivity)) {
				flContent.setVisibility(View.VISIBLE);
				StateMainLayout.setVisibility(View.GONE);

			}
		} catch (ConnectException e) {
			flContent.setVisibility(View.GONE);
			StateMainLayout.setVisibility(View.VISIBLE);
			StateMainLayout.addView(LayoutInflater.from(mActivity).inflate(
					R.layout.base_error_info, null));

		}
	}
}
