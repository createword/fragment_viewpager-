package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import com.example.frag.R;
import com.example.modle.Category;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * Ê¡·Ý
 * @author WINTER
 *
 */
public class SelectProAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private List<HashMap<String,Category>> categoriesList;
	public SelectProAdapter(Context context,List<HashMap<String, Category>> categoriesList) {
		this.context = context;
		this.categoriesList = categoriesList;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return categoriesList.size();
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
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item_ada_text, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.provincename);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(categoriesList.get(position).get("category_title").getMprovince());

		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
	}

	
}
