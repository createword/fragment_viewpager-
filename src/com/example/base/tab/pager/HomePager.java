package com.example.base.tab.pager;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import com.example.viewpage.BasePager;

public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("��ɵ��");
		TextView txtview = new TextView(mActivity);
		txtview.setText("����");
		txtview.setGravity(Gravity.CENTER);
		flContent.addView(txtview);
	}

}
