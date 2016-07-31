package com.example.main;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * fragment����
 * 
 * @author Kevin
 * 
 */
public abstract class BaseFragment extends Fragment {

	public MainActivity mActivity;
	
	// fragment����
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
	}

	// ����fragment�Ĳ���
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initViews();
	}

	// ������activity������� ��Activity�е�onCreate����ִ��������
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
	}

	// �������ʵ�ֳ�ʼ�����ֵķ���
	public abstract View initViews();

	// ��ʼ������, ���Բ�ʵ��
	public void initData() {

	}

}
