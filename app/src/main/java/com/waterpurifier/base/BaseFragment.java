package com.waterpurifier.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public static BaseFragment newInstance(String index) {
        BaseFragment f = new BaseFragment();
        Bundle args = new Bundle();
        args.putString("index", index);
        f.setArguments(args);
        return f;
    }
}
