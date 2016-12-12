package com.waterpurifier;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.waterpurifier.base.BaseActivity;
import com.waterpurifier.base.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPwdActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvLeft;
    private TextView mTvTitle;

    private EditText mEtTelNum;
    private EditText mEtInputValidate2;
    private EditText mEtPassword;

    private Button mBtnGetvalidate2;
    private Button mBtnConfirm;

    private CountDownTimer mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        mTvTitle = (TextView)findViewById(R.id.tv_head_title);
        mTvTitle.setText("忘记密码");

        RelativeLayout mRlHeadLayout = (RelativeLayout) findViewById(R.id.head_layout);
        mRlHeadLayout.setBackgroundColor(getResources().getColor(R.color.head_blue_bg2));

        mIvLeft = (ImageView)findViewById(R.id.btn_head_left);
        mIvLeft.setImageResource(R.mipmap.back);
        mIvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEtTelNum = (EditText)findViewById(R.id.et_tel_num);
        mEtInputValidate2 = (EditText)findViewById(R.id.et_input_validate2);
        mEtPassword = (EditText)findViewById(R.id.et_input_pwd1);

        mBtnConfirm = (Button)findViewById(R.id.btn_confirm);
        mBtnConfirm.setOnClickListener(this);

        mBtnGetvalidate2 = (Button)findViewById(R.id.btn_getvalidate2);
        mBtnGetvalidate2.setOnClickListener(this);

        mTime = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                mBtnGetvalidate2.setClickable(false);
                mBtnGetvalidate2.setText("剩余" + millisUntilFinished / 1000 + "秒");
            }

            public void onFinish() {
                mBtnGetvalidate2.setClickable(true);
                mBtnGetvalidate2.setText("获取验证码");
            }
        };
    }

    @Override
    public void onClick(View v) {
        String tel  = mEtTelNum.getText().toString().trim();
        String code = mEtInputValidate2.getText().toString().trim();
        String password  = mEtPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn_getvalidate2:
                if(TextUtils.isEmpty(tel)) {
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendMessage(tel);
                break;

            case R.id.btn_confirm:
                if(TextUtils.isEmpty(tel)) {
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(code)) {
                    Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                changePassword(tel, password, code);
                break;
        }
    }

    private void sendMessage(String tel) {
        String url = basePath + "/user/request_code";
        final Map<String, String> params = new HashMap<>();
        params.put("tel", tel);

        RequestQueue queue = application.getRequestQueue();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ForgetPwdActivity", response.toString());
                int code = response.optInt("code");
                if (code > 0) {
                    mTime.start();
                } else {
                    Toast.makeText(getApplicationContext(), response.optString("error"), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), Constant.IS_DEBUG_MODE ? error.toString() : "网络故障，请稍候重试", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonRequest);
    }

    private void changePassword(String tel, String password, String code) {
        String url = basePath + "/user/change_password";
        final Map<String, String> params = new HashMap<>();
        params.put("tel", tel);
        params.put("password", password);
        params.put("code", code);

        RequestQueue queue = application.getRequestQueue();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ForgetPwdActivity", response.toString());
                boolean success = response.optBoolean("success");
                if (success) {
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), response.optString("error"), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), Constant.IS_DEBUG_MODE ? error.toString() : "网络故障，请稍候重试", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonRequest);
    }
}
