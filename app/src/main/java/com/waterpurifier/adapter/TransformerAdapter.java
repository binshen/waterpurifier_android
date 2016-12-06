package com.waterpurifier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.waterpurifier.R;

/**
 * Created by bin.shen on 06/12/2016.
 */

public class TransformerAdapter extends BaseAdapter {

    private Context mContext;

    public TransformerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return SliderLayout.Transformer.values().length;
    }

    @Override
    public Object getItem(int position) {
        return SliderLayout.Transformer.values()[position].toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView t = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item, null);
        t.setText(getItem(position).toString());
        return t;

    }
}
