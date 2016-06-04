package com.example.main;

import java.util.ArrayList;

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

public class GuideActivity extends BaseActivity {

	@Override
	public void initView() {
		rel_Title_Bar.setVisibility(View.GONE);
		act_content.addView(LayoutInflater.from(this).inflate(
				R.layout.activity_guide, null));
		findViewById(R.id.vp_guide).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(GuideActivity.this, MainActivity.class);
				GuideActivity.this.startActivity(in);
			}
		});
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
