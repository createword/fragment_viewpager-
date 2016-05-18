package com.example.base.tab.pager;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import com.example.base.httputils.AppCustomDialog;
import com.example.base.httputils.Constants;
import com.example.base.httputils.Utils;
import com.example.exception.ConnectException;
import com.example.viewpage.BasePager;

public class NewsPager extends BasePager {

	public NewsPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initViewData() {
		ViewErrorState();
		tvTitle.setText("´ó°×³Õ");
		TextView txtview = new TextView(mActivity);
		txtview.setText("°×Ñë");
		txtview.setGravity(Gravity.CENTER);
		flContent.addView(txtview);
	}


	
}
