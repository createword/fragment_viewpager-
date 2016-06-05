package com.example.base.tab.pager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.adapter.MyGridAdapter;
import com.example.adapter.MyGridView;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class classifyPager  extends BasePager{
private View view;
private MyGridView myGridView;
	public classifyPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initView() {
		ViewIsNetWorkState();
		base_left.setText("·µ»Ø");
	
		tvTitle.setText("·ÖÀà");
		view = LayoutInflater.from(mActivity)
				.inflate(R.layout.gridview_main, null);
		myGridView=	(MyGridView) view.findViewById(R.id.grid_view);
		flContent.addView(view);
	}

	@Override
	public void initData() {
		
		myGridView.setAdapter(new MyGridAdapter(mActivity));
		base_left.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActivity.finish();
			}
		});
	}

}
