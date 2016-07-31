package com.example.main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.base.utils.Utils;
import com.example.frag.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class MainActivity extends FragmentActivity {
	private onResultParams resultParams;
	private static final String FRAGMENT_CONTENT = "fragment_content";
	private FragmentManager fm;
	public String mposition;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity_framelayout);

		initFragment();
	}

	private void initFragment() {
		fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.parent_content, new ContentFragment(), FRAGMENT_CONTENT);

		transaction.commit();// 提交

	}

	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm.findFragmentByTag(FRAGMENT_CONTENT);

		return fragment;
	}

	/*
	 * 学校选择 (non-Javadoc)
	 * 
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		// requestCode标示请求的标示 resultCode表示有数据
		if (requestCode == 1 && resultCode == RESULT_OK) {
			// String p = data.getStringExtra("KEY_POSITION");
			int POSITION = data.getIntExtra("KEY_POSITION", 0);
			 ArrayList<Parcelable> arrayList=data.getParcelableArrayListExtra("schoolList");
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("position", POSITION);
			p.put("array", arrayList);
			resultParams.MapParams(p);
		}

		else if (requestCode == 2 && resultCode == RESULT_OK) {
			// String p = data.getStringExtra("KEY_POSITION");
			int POSITION = data.getIntExtra("KEY", 0);
			Utils.ToastShort(this, POSITION + "");
		}
	}

	public void getMapParams(onResultParams params) {
		resultParams = params;

	}

	public interface onResultParams {
		public void MapParams(Map<String, Object> params);
	}
}
