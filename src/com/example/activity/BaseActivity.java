package com.example.activity;

import com.example.base.utils.Utils;
import com.example.exception.ConnectException;
import com.example.frag.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {
	public TextView act_base_title;
	public FrameLayout act_content;
	public FrameLayout act_state_content;
	public RelativeLayout rel_Title_Bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity);
		intActivityView();
		initView();
		initData();
	}

	public void intActivityView() {
		act_base_title = (TextView) findViewById(R.id.act_base_title);
		act_content = (FrameLayout) findViewById(R.id.act_content);
		act_state_content = (FrameLayout) findViewById(R.id.act_state_content);
		rel_Title_Bar = (RelativeLayout) findViewById(R.id.relTitleBar);
	}
	public abstract void initView();
	public abstract void initData();

	/**
	 * ״̬ҳ�� ����ʱһ��Ҫ��initData���ʼ�� ����������ʱ��ʾ����û������ʾ�����쳣 state 0
	 * ����Ҫ��������д���ڲ����ϲ���Ҫ����������ʾ 1 ��֮
	 */

	public void ViewIsNetWorkState(View v, int state) {
		if (state == 0) {
			act_content.addView(v);
		} else if (state == 1) {
			try {
				if (Utils.isNetworkAvailable(this)) {
					act_content.setVisibility(View.VISIBLE);
					act_state_content.setVisibility(View.GONE);
					act_content.addView(v);
				}
			} catch (ConnectException e) {
				act_content.setVisibility(View.GONE);
				act_state_content.setVisibility(View.VISIBLE);
				act_state_content.addView(LayoutInflater.from(this).inflate(
						R.layout.base_error_info, null));

			}
		}
	}
}
