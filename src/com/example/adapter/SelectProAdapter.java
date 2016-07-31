package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import com.example.frag.R;
import com.example.modle.CategoryModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Ê¡·Ý
 * 
 * @author WINTER
 *
 */

public class SelectProAdapter extends BaseAdapter {
	private int selectItem;
	private Context context;
	private LayoutInflater layoutInflater;
	private List<HashMap<String, CategoryModel>> categoriesList;

	public SelectProAdapter(Context context, List<HashMap<String, CategoryModel>> categoriesList) {
		this.context = context;
		this.categoriesList = categoriesList;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return categoriesList.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return categoriesList.get(arg0);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub select_listview_isenble
		// convertView.setBackgroundResource(R.drawable.select_listview_isenble);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_ada_text, null);

			viewHolder.textView = (TextView) convertView.findViewById(R.id.provincename);
			// convertView.setBackgroundColor(R.drawable.select_listview_isenble);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (position == selectItem) {
			convertView.setBackgroundColor(Color.WHITE);
			viewHolder.textView.setEnabled(false);
		} 
		else {
			viewHolder.textView.setEnabled(true);
			convertView.setBackgroundColor(Color.TRANSPARENT);
		}	
		
		viewHolder.textView.setText(
				categoriesList.get(position).get("category_title").getMprovince());
		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
	}

	public void selectPosition(int position) {
		this.selectItem = position;

	}
}
