package com.example.viewpage;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class BasePagerAdapter extends PagerAdapter {
	private List<BasePager> ArryList;

	public BasePagerAdapter(List<BasePager> ArryList) {
		ArryList = ArryList;
	}

	public int getCount() {
		return ArryList.size();
	}

	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		BasePager pager = ArryList.get(position);
		container.addView(pager.mRootView);
		// pager.initData();// ��ʼ������.... ��Ҫ���ڴ˴���ʼ������, �����Ԥ������һ��ҳ��
		return pager.mRootView;
	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

}
