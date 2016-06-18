package com.example.main;

import java.util.ArrayList;
import java.util.Map;

import com.example.activity.Login_Activity;
import com.example.base.tab.pager.HomePager;
import com.example.base.tab.pager.NewsPager;
import com.example.base.tab.pager.PublishPager;
import com.example.base.tab.pager.SettingPager;
import com.example.base.tab.pager.ClassifyPager;
import com.example.base.utils.Utils;
import com.example.frag.R;
import com.example.viewpage.BasePager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ContentFragment extends BaseFragment implements OnClickListener {
	private ViewPager vp;
	private ArrayList<BasePager> ArryList;
	BasePagerAdapter bAdapter;
	private int intnumber;
	private TextView radio_h1, radio_h2, radio_h3, radio_h4;

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.activity_main, null);
		radio_h1 = (TextView) view.findViewById(R.id.radio_h1);
		radio_h2 = (TextView) view.findViewById(R.id.radio_h2);
		radio_h3 = (TextView) view.findViewById(R.id.radio_h3);
		radio_h4 = (TextView) view.findViewById(R.id.radio_h4);
		vp = (ViewPager) view.findViewById(R.id.noScrollViewPager1);
		return view;
	}

	@Override
	public void initData() {

		ArryList = new ArrayList<BasePager>();
		ArryList.add(new HomePager(mActivity));
		ArryList.add(new ClassifyPager(mActivity));
		ArryList.add(new PublishPager(mActivity));
		ArryList.add(new SettingPager(mActivity));
		radio_h1.setOnClickListener(this);
		radio_h2.setOnClickListener(this);
		radio_h3.setOnClickListener(this);
		radio_h4.setOnClickListener(this);
		setCussentPosition(1);
		bAdapter = new BasePagerAdapter();
		vp.setAdapter(bAdapter);
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {

				ArryList.get(position).CustomOnCreate();// 获取当前被选中的页面, 初始化该页面数据
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});
		ArryList.get(0).CustomOnCreate();// 初始化首页数据

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

	public void onClick(View v) {
		resetImgs();
		switch (v.getId()) {
		case R.id.radio_h1:
			setCussentPosition(1);
			vp.setCurrentItem(0, false);// 去掉切换页面的动画
			break;
		case R.id.radio_h2:
			setCussentPosition(2);
			vp.setCurrentItem(1, false);// 去掉切换页面的动画
			break;
		case R.id.radio_h3:
			setCussentPosition(3);
			if (!Login_Activity.isFlag) {
				Intent intent = new Intent(mActivity, Login_Activity.class);
				mActivity.startActivity(intent);
			} else {
				vp.setCurrentItem(2, false);// 去掉切换页面的动画
			}
			break;
		case R.id.radio_h4:
			setCussentPosition(4);
			vp.setCurrentItem(3, false);// 去掉切换页面的动画

			break;
		default:
			break;
		}
	}

	/**
	 * 切换图片至暗色
	 */
	private void resetImgs() {
		Drawable drawable1 = getResources().getDrawable(R.drawable.nav_menu_home);
		Drawable drawable2 = getResources().getDrawable(R.drawable.nav_menu_like);
		Drawable drawable3 = getResources().getDrawable(R.drawable.nav_menu_category);
		Drawable drawable4 = getResources().getDrawable(R.drawable.nav_menu_me);
		drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),drawable1.getMinimumHeight());
		drawable2.setBounds(0, 0, drawable2.getMinimumWidth(),drawable2.getMinimumHeight());
		drawable3.setBounds(0, 0, drawable3.getMinimumWidth(),drawable3.getMinimumHeight());
		drawable4.setBounds(0, 0, drawable4.getMinimumWidth(),drawable4.getMinimumHeight());
		radio_h1.setCompoundDrawables(null, drawable1, null, null);
		radio_h2.setCompoundDrawables(null, drawable2, null, null);
		radio_h3.setCompoundDrawables(null, drawable3, null, null);
		radio_h4.setCompoundDrawables(null, drawable4, null, null);
	}

	public void setCussentPosition(int i) {
		switch (i) {
		case 1:
			Drawable drawable1 = getResources().getDrawable(R.drawable.nav_menu_home_selected);
			drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),drawable1.getMinimumHeight());
			radio_h1.setCompoundDrawables(null, drawable1, null, null);

			break;
		case 2:
			Drawable drawable2 = getResources().getDrawable(R.drawable.nav_menu_like_active);
			drawable2.setBounds(0, 0, drawable2.getMinimumWidth(),drawable2.getMinimumHeight());
			radio_h2.setCompoundDrawables(null, drawable2, null, null);
			break;

		case 3:
			Drawable drawable3 = getResources().getDrawable(R.drawable.nav_menu_category_active);
			drawable3.setBounds(0, 0, drawable3.getMinimumWidth(),drawable3.getMinimumHeight());
			radio_h3.setCompoundDrawables(null, drawable3, null, null);
			break;

		case 4:
			Drawable drawable4 = getResources().getDrawable(R.drawable.nav_menu_me_selected);
			drawable4.setBounds(0, 0, drawable4.getMinimumWidth(),drawable4.getMinimumHeight());
			radio_h4.setCompoundDrawables(null, drawable4, null, null);
			break;

		default:
			break;
		}
	}
}
