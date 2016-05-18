package com.example.base.httputils;

import com.example.frag.R;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * zhanghengming 自定义 正在加载 Dialog
 */
public class AppCustomDialog extends Dialog {
	private static int default_width = 160; // 默认宽度
	private static int default_height = 120;// 默认高度
	public AppCustomDialog(Context context, int layout, int style) {
		this(context, default_width, default_height, layout, style);
	}

	public AppCustomDialog(Context context, int width, int height, int layout,
			int style) {
		super(context, style);
		// 设置内容
		setContentView(layout);
		// 设置窗口属性
		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		// 设置宽度、高度、密度、对齐方式
		float density = getDensity(context);
		params.width = (int) (width * density);
		params.height = (int) (height * density);
		params.gravity = Gravity.CENTER;
		window.setAttributes(params);

	}

	/**
	 * getDensity 获取密度
	 * 
	 * @param context
	 * @return 密度
	 */
	public float getDensity(Context context) {
		Resources res = context.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		return dm.density;
	}


}
