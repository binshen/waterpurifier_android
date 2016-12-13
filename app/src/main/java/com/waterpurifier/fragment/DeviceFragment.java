package com.waterpurifier.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.waterpurifier.R;
import com.waterpurifier.adapter.DeviceListAdapter;
import com.waterpurifier.base.BaseFragment;
import com.waterpurifier.model.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private DeviceListAdapter mDeviceListAdapter;
    private ListView mLvOnlineDevices;
    private List<Device> mDevices;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_device, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout head_layout = (RelativeLayout) view.findViewById(R.id.head_layout);
        head_layout.setBackgroundResource(R.mipmap.head_discover);
        ImageView btn_head_left = (ImageView) view.findViewById(R.id.btn_head_left);
        btn_head_left.setVisibility(View.INVISIBLE);
        TextView tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);
        tv_head_title.setText("设备列表");
        tv_head_title.setTextColor(Color.WHITE);

        mDevices = new ArrayList<>();

        Device d1 = new Device();
        d1.set_id("id_1");
        d1.setName("净水器A");
        d1.setStatus(1);
        mDevices.add(d1);

        Device d2 = new Device();
        d2.set_id("id_2");
        d2.setName("净水器B");
        d2.setStatus(0);
        mDevices.add(d2);

        Device d3 = new Device();
        d3.set_id("id_3");
        d3.setName("净水器C");
        d3.setStatus(1);
        mDevices.add(d3);

        mDeviceListAdapter = new DeviceListAdapter(getContext(), mDevices);
        mLvOnlineDevices = (ListView) view.findViewById(R.id.lv_online_devices);
        mLvOnlineDevices.setOnItemClickListener(this);
        mLvOnlineDevices.setAdapter(mDeviceListAdapter);

        setListViewHeightBasedOnChildren(mLvOnlineDevices);
        mDeviceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getContext(), mDevices.get(position).get_id(), Toast.LENGTH_SHORT).show();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
