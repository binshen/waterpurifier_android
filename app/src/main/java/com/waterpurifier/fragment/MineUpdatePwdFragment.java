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

public class MineUpdatePwdFragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnUpdatePwd;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine_update_pwd, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout head_layout = (RelativeLayout) view.findViewById(R.id.head_layout);
        //head_layout.setBackgroundResource(R.mipmap.head_discover);
        //head_layout.setBackgroundColor(getResources().getColor(R.color.head_blue_bg));
        head_layout.setBackgroundResource(R.color.head_blue_bg);
        ImageView btn_head_left = (ImageView) view.findViewById(R.id.btn_head_left);
        btn_head_left.setOnClickListener(this);
        TextView tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);
        tv_head_title.setText("修改密码");
        tv_head_title.setTextColor(Color.WHITE);

        mBtnUpdatePwd = (Button) view.findViewById(R.id.btn_update_pwd);
        mBtnUpdatePwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_head_left:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, new MineBasicFragment());
                ft.commitAllowingStateLoss();
                break;

            case R.id.btn_update_pwd:
                Toast.makeText(getContext(), "修改密码完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
