package com.example.main;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * fragment基类
 * 
 * @author Kevin
 * 
 */
public abstract class BaseFragment extends Fragment {

	public MainActivity mActivity;
	
	// fragment创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
	}

	// 处理fragment的布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initViews();
	}

	// 依附的activity创建完成 当Activity中的onCreate方法执行完后调用
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
	}

	// 子类必须实现初始化布局的方法
	public abstract View initViews();

	// 初始化数据, 可以不实现
	public void initData() {

	}

}
