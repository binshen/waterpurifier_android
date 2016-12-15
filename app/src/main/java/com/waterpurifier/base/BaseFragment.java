package com.waterpurifier.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.waterpurifier.widget.LoadDialog;

public class BaseFragment extends Fragment {

    protected String basePath = Constant.API_BASE_PATH;

    protected LoadDialog mLoadDialog;

    protected BaseApplication application;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLoadDialog = new LoadDialog(getContext());
        application = (BaseApplication) getActivity().getApplication();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        mLoadDialog.dismiss();
        super.onPause();
    }
}
