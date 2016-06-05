package com.example.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @Description:解决在scrollview中只显示第一行数据的问题 
 *Gridview和ListView都是可以根据子item的宽高来显示大小的，但是一旦嵌套到ScrollView中就可以上下滑动
 *于是系统就不能确定到底该画多大，所以才会产生这样的问题
 */
public class MyGridView extends GridView {
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView(Context context) {
		super(context);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
