package com.waterpurifier.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.waterpurifier.LoginActivity;
import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnLogout;
    private ImageView mIvBtnSetting;
    private ImageView mIvBtnRecharge;

    private RelativeLayout mRlMySetRow1;
    private RelativeLayout mRlMySetRow2;
    private RelativeLayout mRlMySetRow3;
    private RelativeLayout mRlMySetRow4;

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

        mRlMySetRow1 = (RelativeLayout) view.findViewById(R.id.rl_my_set_row_1);
        mRlMySetRow1.setOnClickListener(this);

        mRlMySetRow2 = (RelativeLayout) view.findViewById(R.id.rl_my_set_row_2);
        mRlMySetRow2.setOnClickListener(this);

        mRlMySetRow3 = (RelativeLayout) view.findViewById(R.id.rl_my_set_row_3);
        mRlMySetRow3.setOnClickListener(this);

        mRlMySetRow4 = (RelativeLayout) view.findViewById(R.id.rl_my_set_row_4);
        mRlMySetRow4.setOnClickListener(this);
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
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;

            case R.id.iv_btn_recharge:
                ft.replace(R.id.main_frame, new MineRechargeFragment());
                ft.commitAllowingStateLoss();
                break;

            case R.id.rl_my_set_row_1:
                Toast.makeText(getContext(), "分享有礼", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rl_my_set_row_2:
                Toast.makeText(getContext(), "帮助中心", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rl_my_set_row_3:
                Toast.makeText(getContext(), "意见反馈", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rl_my_set_row_4:
                Toast.makeText(getContext(), "检查更新", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
