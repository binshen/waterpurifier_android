package com.waterpurifier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.waterpurifier.R;
import com.waterpurifier.model.Device;

import java.util.List;

/**
 * Created by bin.shen on 10/12/2016.
 */

public class DeviceListAdapter extends BaseAdapter {

    private List<Device> deviceList;
    private LayoutInflater layoutInflater;

    public DeviceListAdapter(Context context, List<Device> devices) {
        layoutInflater = LayoutInflater.from(context);
        deviceList = devices;
    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            viewHolder = new DeviceListAdapter.ViewHolder();
            view = layoutInflater.inflate(R.layout.widget_list_device, null);
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_device_name);
            viewHolder.tc_status = (TextView) view.findViewById(R.id.tv_device_status);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Device device = deviceList.get(position);
        String deviceName = device.getName();
        String deviceStatus = device.getStatus() == 1 ? "局域网在线" : "不在线";
        viewHolder.tv_name.setText(deviceName);
        viewHolder.tc_status.setText(deviceStatus);
        return view;
    }

    class ViewHolder {
        public TextView tv_name;
        public TextView tc_status;
    }
}
