package com.example.base.tab.pager;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import com.example.base.httputils.AppCustomDialog;
import com.example.base.httputils.Constants;
import com.example.base.httputils.Utils;
import com.example.viewpage.BasePager;

public class NewsPager extends BasePager {

	public NewsPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initData() {
		tvTitle.setText("´ó°×³Õ");
		TextView txtview = new TextView(mActivity);
		txtview.setText("°×Ñë");
		txtview.setGravity(Gravity.CENTER);
	
		try {
			if(!Utils.isNetworkAvailable(mActivity)){
				Utils.ToastShort(mActivity, Constants.ERROR_NOAVAIABLE_NETWORK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utils.ToastShort(mActivity, e.getMessage());
		}
		flContent.addView(txtview);
	}

}
