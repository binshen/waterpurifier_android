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

    public int getCount() {
        return deviceList.size();
    }

    public Object getItem(int position) {
        return deviceList.get(position);
    }

    public long getItemId(int position) {
        return (long)position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder = null;
        if(convertView == null) {
            vHolder = new DeviceListAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.widget_list_device, null);
            vHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_device_name);
            vHolder.tc_status = (TextView)convertView.findViewById(R.id.tv_device_status);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        Device device = deviceList.get(position);
        String deviceName = device.getName();
        String deviceStatus = device.getStatus() == 1 ? "局域网在线" : "不在线";
        vHolder.tv_name.setText(deviceName);
        vHolder.tc_status.setText(deviceStatus);
        return convertView;
    }

    class ViewHolder {
        public TextView tc_status;
        public TextView tv_name;
    }
}
