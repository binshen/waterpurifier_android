package com.waterpurifier.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;

public class MineFragment extends BaseFragment {


    public MineFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

}
