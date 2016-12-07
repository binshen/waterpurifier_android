package com.waterpurifier;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterpurifier.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvLeft;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTvTitle = (TextView)findViewById(R.id.tv_head_title);
        mTvTitle.setText("注册");

        mIvLeft = (ImageView)findViewById(R.id.btn_head_left);
        mIvLeft.setImageResource(R.mipmap.back);
        mIvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
