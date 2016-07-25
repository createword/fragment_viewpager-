package com.example.adapter;

import java.util.ArrayList;

import com.example.frag.R;
import com.example.modle.TopNewPicModel;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TopViewAdapter extends PagerAdapter {
	private ArrayList<TopNewPicModel> arrayList;
	private Context context;
	BitmapUtils mBitmap;

	public TopViewAdapter(Context contex, ArrayList<TopNewPicModel> arrayList) {
		this.context = contex;
		this.arrayList = arrayList;
		mBitmap=new BitmapUtils(context);
		mBitmap.configDefaultLoadingImage(R.drawable.ic_launcher);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView image = new ImageView(context);
		image.setScaleType(ScaleType.FIT_XY);// 基于控件大小填充图片
		String s=arrayList.get(position).url;
		
		mBitmap.display(image, s);// 传递imagView对象和图片地址

		((ViewGroup) container).addView(image);

		System.out.println("instantiateItem....." + position);
		return image;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewGroup) container).removeView((View) object);
	}
}
