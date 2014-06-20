package com.card;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.card.GridviewAdapter.ViewHolder;
import com.fidelitat.R;

public class GridviewSelectedItemAdapter extends BaseAdapter {
	private ArrayList<String> listCountry;
	private ArrayList<Integer> listFlag;
	private Activity activity;

	public GridviewSelectedItemAdapter(Activity activity, ArrayList<String> listContactes,
			ArrayList<Integer> listConImg) {
		super();
		this.listCountry = listContactes;
		this.listFlag = listConImg;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return listCountry.size();
	}

	@Override
	public String getItem(int position) {
		return listCountry.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public static class ViewHolder {
		public ImageView imgViewFlag;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();

		if (convertView == null) {
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.gridview_adaptar, null);

			
			view.imgViewFlag = (ImageView) convertView
					.findViewById(R.id.imageView1);

			convertView.setTag(view);
		} else {
			view = (ViewHolder) convertView.getTag();
		}

		view.imgViewFlag.setImageResource(listFlag.get(position));

		return convertView;
	}

}
