package com.waterpurifier.fragment;


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

import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;

public class MineRechargeFragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnRecharge;
    private Button mBtnReinput;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine_recharge, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout head_layout = (RelativeLayout) view.findViewById(R.id.head_layout);
        head_layout.setBackgroundResource(R.mipmap.head_discover);
        ImageView btn_head_left = (ImageView) view.findViewById(R.id.btn_head_left);
        btn_head_left.setOnClickListener(this);
        TextView tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);
        tv_head_title.setText("充 值");
        tv_head_title.setTextColor(Color.WHITE);

        mBtnRecharge = (Button) view.findViewById(R.id.btn_rechange);
        mBtnRecharge.setOnClickListener(this);

        mBtnReinput = (Button) view.findViewById(R.id.btn_reinput);
        mBtnReinput.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_head_left:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, new MineFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.btn_rechange:
                Toast.makeText(getContext(), "ReCharge", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_reinput:
                Toast.makeText(getContext(), "ReInput", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
