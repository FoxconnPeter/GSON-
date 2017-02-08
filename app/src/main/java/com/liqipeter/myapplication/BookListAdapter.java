package com.liqipeter.myapplication;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class BookListAdapter extends BaseAdapter {
	private Context c;
	private ArrayList<Book> list;

	public BookListAdapter(Context context, ArrayList<Book> books) {
		this.c = context;
		this.list = books;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			convertView = View.inflate(c, R.layout.item_list, null);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView.findViewById(R.id.tv);

			holder.meizi=(ImageView)convertView.findViewById(R.id.meizi);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Book b = list.get(position);

		holder.tv.setText(b.getDesc());

		Picasso.with(c).load(list.get(position).getUrl()).placeholder(R.mipmap.avatar).into(holder.meizi);


		return convertView;
	}

	class ViewHolder {
		TextView tv;
		ImageView meizi;
	}

}
