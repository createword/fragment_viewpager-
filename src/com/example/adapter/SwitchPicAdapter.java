package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SwitchPicAdapter extends BaseAdapter {
	private Context context;
	private List<String> mdList;
	private int itemLayoutId;
	private LayoutInflater inflater;

	public SwitchPicAdapter(Context context, List<String> mDatas,
			int itemLayoutId) {
		this.context = context;
		this.mdList = mDatas;
		this.itemLayoutId = itemLayoutId;
		inflater=LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mdList.size();
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
		convertView =inflater.inflate(itemLayoutId, null);
		
		return null;
	}

}
