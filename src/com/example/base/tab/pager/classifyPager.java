package com.example.base.tab.pager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.adapter.MyGridAdapter;
import com.example.adapter.MyGridView;
import com.example.analysis.AUserLogin;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class ClassifyPager extends BasePager {
	private View view;
	private MyGridView myGridView;

	public ClassifyPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		ViewIsNetWorkState();
		base_left.setText("����");
		tvTitle.setText("����");
		view = LayoutInflater.from(mActivity).inflate(R.layout.gridview_main,
				null);
		myGridView = (MyGridView) view.findViewById(R.id.grid_view);
		flContent.addView(view);
	}

	@Override
	public void initData() {

		myGridView.setAdapter(new MyGridAdapter(mActivity));

	}

}
