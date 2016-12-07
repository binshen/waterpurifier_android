package com.waterpurifier.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTestButton;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTestButton = (TextView) view.findViewById(R.id.btn_test_button);
        mTestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "11111111111111111", Toast.LENGTH_LONG).show();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame, new MineBasicFragment());
        ft.commitAllowingStateLoss();
    }
}
