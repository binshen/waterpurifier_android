package com.waterpurifier.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnLogout;
    private ImageView mIvBtnSetting;
    private ImageView mIvBtnRecharge;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine, container, false);
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
        tv_head_title.setText("我 的");
        tv_head_title.setTextColor(Color.WHITE);


        mBtnLogout = (Button) view.findViewById(R.id.btn_logout);
        mBtnLogout.setOnClickListener(this);

        mIvBtnSetting = (ImageView) view.findViewById(R.id.iv_btn_setting);
        mIvBtnSetting.setOnClickListener(this);

        mIvBtnRecharge = (ImageView) view.findViewById(R.id.iv_btn_recharge);
        mIvBtnRecharge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.iv_btn_setting:
                ft.replace(R.id.main_frame, new MineBasicFragment());
                ft.commitAllowingStateLoss();
                break;

            case R.id.btn_logout:
                getActivity().finish();
                break;

            case R.id.iv_btn_recharge:
                ft.replace(R.id.main_frame, new MineRechargeFragment());
                ft.commitAllowingStateLoss();
                break;
        }

    }
}
