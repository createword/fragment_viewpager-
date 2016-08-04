package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.frag.R;
import com.example.modle.SchoolInfo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater;
	ArrayList<SchoolInfo> array;
	public int foodpoition;
	private String scName;

	public HomeAdapter(Context context, ArrayList<SchoolInfo> array, String scName) {
		this.context = context;
		this.array = array;
		this.scName = scName;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return array.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getItem(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.home_pager_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView.findViewById(R.id.home_text_u);
			viewHolder.home_text_school = (TextView) convertView.findViewById(R.id.home_text_school);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(array.get(position).getInfotitle());
	viewHolder.home_text_school.setText(scName);

		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
		public TextView home_text_school;
	}

}
