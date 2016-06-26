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
	private int mPointWidth;// Բ���ľ���
	private int[] groupImg = new int[] { R.drawable.guid1, R.drawable.guid2,
			R.drawable.guid3 };

	private View viewRedPoint;// С���
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
		// ��ȡ��ͼ��, ��layout�����¼����м���
		linList.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					// ��layoutִ�н�����ص��˷���
					public void onGlobalLayout() {
						System.out.println("layout ����");
						linList.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						mPointWidth = linList.getChildAt(1).getLeft()
								- linList.getChildAt(0).getLeft();
						System.out.println("Բ�����:" + mPointWidth);
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

		// �����¼�
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// System.out.println("��ǰλ��:" + position + ";�ٷֱ�:" + positionOffset
			// + ";�ƶ�����:" + positionOffsetPixels);
			int len = (int) (mPointWidth * positionOffset) + position
					* mPointWidth;
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint
					.getLayoutParams();// ��ȡ��ǰ���Ĳ��ֲ���
			params.leftMargin = len;// ������߾�

			viewRedPoint.setLayoutParams(params);// ���¸�С������ò��ֲ���
		}

		// ĳ��ҳ�汻ѡ��
		public void onPageSelected(int position) {
			if (position == groupImg.length - 1) {// ���һ��ҳ��
				btn_start.setVisibility(View.VISIBLE);// ��ʾ��ʼ����İ�ť
			} else {
				btn_start.setVisibility(View.INVISIBLE);
			}
		}

		// ����״̬�����仯
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
