package com.example.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.activity.BaseActivity;
import com.example.frag.R;

public class GuideActivity extends BaseActivity implements OnClickListener{
	private ViewPager mViewPager;
	private LinearLayout linList;
	private List<ImageView> arrayImageList;
	private int mPointWidth;// 圆点间的距离
	private int[] groupImg = new int[] { R.drawable.guid1, R.drawable.guid2,
			R.drawable.guid3 };

	private View viewRedPoint;// 小红点
	private View view;
	private Button btn_start;

	@Override
	public void initView() {
		rel_Title_Bar.setVisibility(View.GONE);
		view = LayoutInflater.from(this).inflate(R.layout.activity_guide, null);
		act_content.addView(view, 0);
		linList = (LinearLayout) view.findViewById(R.id.ll_point_group);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_guide);
		btn_start = (Button) view.findViewById(R.id.btn_start);
		viewRedPoint = view.findViewById(R.id.view_red_point);
	}

	@Override
	public void initData() {
		btn_start.setOnClickListener(this);
		arrayImageList = new ArrayList<ImageView>();
		for (int i = 0; i < groupImg.length; i++) {
			ImageView image = new ImageView(GuideActivity.this);
			image.setImageResource(groupImg[i]);
			arrayImageList.add(image);
		}
		for (int i = 0; i < groupImg.length; i++) {
			View imagePoint = new View(GuideActivity.this);
			LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
					30, 30);
			imagePoint.setBackgroundResource(R.drawable.shape_point_gray);
			if (i > 0)
				lParams.leftMargin = 15;
			imagePoint.setLayoutParams(lParams);
			linList.addView(imagePoint);

		}
		// 获取视图树, 对layout结束事件进行监听
		linList.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					// 当layout执行结束后回调此方法
					public void onGlobalLayout() {
						System.out.println("layout 结束");
						linList.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						mPointWidth = linList.getChildAt(1).getLeft()
								- linList.getChildAt(0).getLeft();
						System.out.println("圆点距离:" + mPointWidth);
					}
				});
		// TODO Auto-generated method stub
		mViewPager.setAdapter(new MyViewPager());
		mViewPager.setOnPageChangeListener(new MyPageChange());
	}

	class MyViewPager extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return groupImg.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(arrayImageList.get(position));
			return arrayImageList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	class MyPageChange implements OnPageChangeListener {

		// 滑动事件
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// System.out.println("当前位置:" + position + ";百分比:" + positionOffset
			// + ";移动距离:" + positionOffsetPixels);
			int len = (int) (mPointWidth * positionOffset) + position
					* mPointWidth;
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint
					.getLayoutParams();// 获取当前红点的布局参数
			params.leftMargin = len;// 设置左边距

			viewRedPoint.setLayoutParams(params);// 重新给小红点设置布局参数
		}

		// 某个页面被选中
		public void onPageSelected(int position) {
			if (position == groupImg.length - 1) {// 最后一个页面
				btn_start.setVisibility(View.VISIBLE);// 显示开始体验的按钮
			} else {
				btn_start.setVisibility(View.INVISIBLE);
			}
		}

		// 滑动状态发生变化
		public void onPageScrollStateChanged(int state) {

		}

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			Intent in=new Intent(this, MainActivity.class);
			startActivity(in);
			finish();
			break;

		default:
			break;
		}
		
	}
}
