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
		"拉拉秀平台",
		"拉拉秀传媒",
		"火车票订购",
		"快递查询",
		"地图导航",
		"生活工具",
		"违章查询",
		"在线翻译",
		"机票订购"
	};
	public int[] imgs = {
		R.mipmap.app_icon_1,
		R.mipmap.app_icon_2,
		R.mipmap.app_icon_3,
		R.mipmap.app_icon_4,
		R.mipmap.app_icon_5,
		R.mipmap.app_icon_6,
		R.mipmap.app_icon_7,
		R.mipmap.app_icon_8,
		R.mipmap.app_icon_9
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
