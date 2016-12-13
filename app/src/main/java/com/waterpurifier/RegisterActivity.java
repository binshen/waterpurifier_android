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

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvLeft;
    private TextView mTvTitle;

    private EditText mEtTelNum;
    private EditText mEtInputValidate1;
    private EditText mEtInputName;
    private EditText mEtPassword;

    private Button mBtnRegister;
    private Button mBtnGetvalidate1;

    private CountDownTimer mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTvTitle = (TextView)findViewById(R.id.tv_head_title);
        mTvTitle.setText("注册");

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

        mEtTelNum = (EditText)findViewById(R.id.et_tel_num1);
        mEtInputValidate1 = (EditText)findViewById(R.id.et_input_validate1);
        mEtInputName = (EditText)findViewById(R.id.et_input_name);
        mEtPassword = (EditText)findViewById(R.id.et_input_pwd1);

        mBtnRegister = (Button)findViewById(R.id.btn_register1);
        mBtnRegister.setOnClickListener(this);

        mBtnGetvalidate1 = (Button)findViewById(R.id.btn_getvalidate1);
        mBtnGetvalidate1.setOnClickListener(this);

        mTime = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                mBtnGetvalidate1.setClickable(false);
                mBtnGetvalidate1.setText("剩余" + millisUntilFinished / 1000 + "秒");
            }

            public void onFinish() {
                mBtnGetvalidate1.setClickable(true);
                mBtnGetvalidate1.setText("获取验证码");
            }
        };
    }

    @Override
    public void onClick(View v) {
        String tel  = mEtTelNum.getText().toString().trim();
        String code = mEtInputValidate1.getText().toString().trim();
        String name = mEtInputName.getText().toString().trim();
        String password  = mEtPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn_getvalidate1:
                if(TextUtils.isEmpty(tel)) {
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendMessage(tel);
                break;

            case R.id.btn_register1:
                if(TextUtils.isEmpty(tel)) {
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(code)) {
                    Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerUser(tel, name, password, code);
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
                Log.d("RegisterActivity", response.toString());
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

    private void registerUser(String tel, String name, String password, String code) {
        String url = basePath + "/user/register";
        final Map<String, String> params = new HashMap<>();
        params.put("tel", tel);
        params.put("name", name);
        params.put("password", password);
        params.put("code", code);

        RequestQueue queue = application.getRequestQueue();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("RegisterActivity", response.toString());
                int code = response.optInt("code");
                if (code > 0) {
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
