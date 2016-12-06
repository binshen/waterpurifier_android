package com.waterpurifier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterpurifier.R;
import com.waterpurifier.base.BaseViewHolder;

public class MyGridAdapter extends BaseAdapter {

	private Context mContext;

	public String[] img_text = {
		"转账",
		"余额宝",
		"手机充值",
		"信用卡还款",
		"淘宝电影",
		"彩票",
		"当面付",
		"亲密付",
		"机票"
	};
	public int[] imgs = {
		R.mipmap.app_transfer,
		R.mipmap.app_fund,
		R.mipmap.app_phonecharge,
		R.mipmap.app_creditcard,
		R.mipmap.app_movie,
		R.mipmap.app_lottery,
		R.mipmap.app_facepay,
		R.mipmap.app_close,
		R.mipmap.app_plane
	};

	public MyGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return img_text.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_grid_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);
		tv.setText(img_text[position]);
		return convertView;
	}
}
