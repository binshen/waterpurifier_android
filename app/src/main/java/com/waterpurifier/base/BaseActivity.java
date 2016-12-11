package com.waterpurifier.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.waterpurifier.widget.LoadDialog;

/**
 * Created by bin.shen on 03/12/2016.
 */

public class BaseActivity extends FragmentActivity {

    protected String basePath = Constant.API_BASE_PATH;

    protected LoadDialog mLoadDialog;

    protected BaseApplication application;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mLoadDialog = new LoadDialog(this);
        application = (BaseApplication) getApplication();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        mLoadDialog.dismiss();
        super.onPause();
    }
}
