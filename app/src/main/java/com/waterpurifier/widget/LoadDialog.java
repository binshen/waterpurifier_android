package com.waterpurifier.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.waterpurifier.R;

/**
 * Created by bin.shen on 11/12/2016.
 */

public class LoadDialog {

    public Dialog mDialog;
    private Context context;
    private AnimationDrawable mAnimationDrawable = null;

    public LoadDialog(Context context) {
        this.context = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.widget_load_dialog, null);
        ImageView loadingImage = (ImageView)view.findViewById(R.id.iv_progress);
        loadingImage.setImageResource(R.drawable.load_dialog_animation);
        mAnimationDrawable = (AnimationDrawable)loadingImage.getDrawable();
        if(mAnimationDrawable != null) {
            mAnimationDrawable.setOneShot(false);
            mAnimationDrawable.start();
        }
        mDialog = new Dialog(context, R.style.MyLoadDialog);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//            }
//        });
    }

    public void show() {
        if(!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if(!((Activity)context).isFinishing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if(mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if(mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }

    public boolean isShowing() {
        if(mDialog.isShowing()) {
            return true;
        }
        return false;
    }
}
