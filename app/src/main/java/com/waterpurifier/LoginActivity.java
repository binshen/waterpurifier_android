package com.waterpurifier;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.waterpurifier.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtTel;
    private EditText mEtPwd;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private TextView mTvForgetpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtTel = (EditText)findViewById(R.id.et_tel);
        mEtPwd = (EditText)findViewById(R.id.et_psw);
        mBtnLogin = (Button)findViewById(R.id.btn_login);
        mBtnRegister = (Button)findViewById(R.id.btn_register);
        mTvForgetpwd = (TextView)findViewById(R.id.tv_forget_psw);

        mTvForgetpwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mTvForgetpwd.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String tel = mEtTel.getText().toString().trim();
        String psw = mEtPwd.getText().toString().trim();
        switch (v.getId()) {
            case R.id.tv_forget_psw:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;

            case R.id.btn_login:
                if(TextUtils.isEmpty(tel)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(psw)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "mBtnLogin", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
