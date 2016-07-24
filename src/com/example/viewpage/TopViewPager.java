package com.example.viewpage;
import com.example.adapter.TopViewAdapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 
 * 
 * @author zhm
 * 
 */
public class TopViewPager extends ViewPager {

	int startX;
	int startY;

	public TopViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TopViewPager(Context context) {
		super(context);
	}

	/**
	 * �¼��ַ�, ���󸸿ؼ������ڿؼ��Ƿ������¼� 1. �һ�, �����ǵ�һ��ҳ��, ��Ҫ���ؼ����� 2. ��, ���������һ��ҳ��, ��Ҫ���ؼ�����
	 * 3. ���»���, ��Ҫ���ؼ�����
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);// ��Ҫ����,
																	// ������Ϊ�˱�֤ACTION_MOVE����
			startX = (int) ev.getRawX();
			startY = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:

			int endX = (int) ev.getRawX();
			int endY = (int) ev.getRawY();

			if (Math.abs(endX - startX) > Math.abs(endY - startY)) {// ���һ���
				if (endX > startX) {// �һ�
					if (getCurrentItem() == 0) {// ��һ��ҳ��, ��Ҫ���ؼ�����
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				} else {// ��
					if (getCurrentItem() == getAdapter().getCount() - 1) {// ���һ��ҳ��,
																			// ��Ҫ����
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}
			} else {// ���»���
				getParent().requestDisallowInterceptTouchEvent(false);
			}

			break;

		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}


}
