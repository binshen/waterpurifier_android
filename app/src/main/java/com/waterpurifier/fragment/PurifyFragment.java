package com.waterpurifier.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;

public class PurifyFragment extends BaseFragment {


    public PurifyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_purify, container, false);
    }
}
