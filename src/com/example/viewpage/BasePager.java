package com.example.viewpage;

import com.example.base.utils.Utils;
import com.example.exception.ConnectException;
import com.example.frag.R;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BasePager extends View { //�̳�View Ŀ������ContentFragemnt��getTag()Ҳ���Բ��̳�

	public BasePager(Context context) {
		super(context);

		mActivity = (Activity) context;
		BaseinitViews();

	}

	public Activity mActivity;
	public View mRootView;// ���ֶ���

	public TextView base_left;// �������
	public TextView tvTitle;// �������

	public FrameLayout flContent;// ����

	public ImageButton btnMenu;// �˵���ť

	public FrameLayout StateMainLayout;// ״̬��ʾ��

	/**
	 * ��ʼ������
	 */
	public void BaseinitViews() {
		mRootView = View.inflate(mActivity, R.layout.base_pager, null);
		base_left = (TextView) mRootView.findViewById(R.id.base_left);
		tvTitle = (TextView) mRootView.findViewById(R.id.base_title);
		flContent = (FrameLayout) mRootView.findViewById(R.id.pager_content);
		StateMainLayout = (FrameLayout) mRootView.findViewById(R.id.pager_state_content);

	}

	/**
	 * ��ʼ������
	 */
	public void CustomOnCreate() {
		flContent.removeAllViews();
		initView();
		initData();
	}

	public abstract void initView();

	public abstract void initData();

	/**
	 * ״̬ҳ�� ����ʱһ��Ҫ��initData���ʼ�� ����������ʱ��ʾ����û������ʾ�����쳣
	 */

	public void ViewIsNetWorkState() {
		try {
			if (Utils.isNetworkAvailable(mActivity)) {
				flContent.setVisibility(View.VISIBLE);
				StateMainLayout.setVisibility(View.GONE);

			}
		} catch (ConnectException e) {
			flContent.setVisibility(View.GONE);
			StateMainLayout.setVisibility(View.VISIBLE);
			StateMainLayout.addView(LayoutInflater.from(mActivity).inflate(
					R.layout.base_error_info, null));

		}
	}
}
