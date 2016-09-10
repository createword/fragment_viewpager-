package com.example.adapter;

import com.example.frag.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter extends BaseAdapter {
	private Context mContext;

	public String[] img_text = { "电子产品", "文体乐器", "闲置礼品", "数码产品", "办公用品", "彩票", "代步工具", "服饰箱包", "二手笔记本", "电动车" };
	public int[] imgs = { R.drawable.app_phonecharge, R.drawable.app_phonecharge, R.drawable.app_phonecharge,
			R.drawable.app_phonecharge, R.drawable.app_phonecharge, R.drawable.app_phonecharge,
			R.drawable.app_phonecharge, R.drawable.app_phonecharge, R.drawable.app_phonecharge,
			R.drawable.app_phonecharge };

	public MyGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return img_text.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(img_text[position]);
		return convertView;
	}

}
