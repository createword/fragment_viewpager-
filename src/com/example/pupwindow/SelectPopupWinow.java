package com.example.pupwindow;

import com.example.frag.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

public class SelectPopupWinow extends PopupWindow {
	private View view;

	public SelectPopupWinow(OnClickListener onclicl, Activity context) {
		view = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
		
	}

}
