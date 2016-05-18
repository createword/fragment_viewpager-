package com.example.frag;

import java.util.ArrayList;

import com.example.base.tab.pager.HomePager;
import com.example.base.tab.pager.NewsPager;
import com.example.pupwindow.SelectPopupWinow;
import com.example.viewpage.BasePager;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class ContentFragment extends BaseFragment {
	private RadioGroup rg;
	private ViewPager vp;
	private ArrayList<BasePager> ArryList;
	private SelectPopupWinow spw;

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.item, null);
		// rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		rg = (RadioGroup) view.findViewById(R.id.group);
		vp = (ViewPager) view.findViewById(R.id.noScrollViewPager1);
		return view;
	}

	@Override
	public void initData() {
		ArryList = new ArrayList<BasePager>();
		ArryList.add(new HomePager(mActivity));
		ArryList.add(new NewsPager(mActivity));
		vp.setAdapter(new BasePagerAdapter());

		rg.check(R.id.radio_home);// 默认勾选首页
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio_home:
					vp.setCurrentItem(0, false);// 去掉切换页面的动画
					break;
				case R.id.radio_h1:
					vp.setCurrentItem(1, false);// 去掉切换页面的动画
					break;
				case R.id.radio_h2:
					vp.setCurrentItem(0, false);// 去掉切换页面的动画
					break;
				case R.id.radio_h3:
					vp.setCurrentItem(1, false);// 去掉切换页面的动画
					break;
				default:
					break;
				}

			}
		});
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {

				ArryList.get(position).initData();// 获取当前被选中的页面, 初始化该页面数据
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});
		ArryList.get(0).initData();// 初始化首页数据
	
	}


	class BasePagerAdapter extends PagerAdapter {

		
		public int getCount() {
			return ArryList.size();
		}

		
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = ArryList.get(position);
			container.addView(pager.mRootView);
			// pager.initData();// 初始化数据.... 不要放在此处初始化数据, 否则会预加载下一个页面
			return pager.mRootView;
		}

		
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}
}
