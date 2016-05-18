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
	public View mRootView;// ���ֶ���

	public TextView tvTitle;// �������

	public FrameLayout flContent;// ����

	public ImageButton btnMenu;// �˵���ť

	public BasePager(Activity activity) {
		mActivity = activity;
		initViews();
	}

	/**
	 * ��ʼ������
	 */
	public void initViews() {
		mRootView = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) mRootView.findViewById(R.id.base_title);
		flContent = (FrameLayout) mRootView.findViewById(R.id.pager_content);

	}

	/**
	 * ��ʼ������
	 */
	public void initData() {
		
	}
	

}
