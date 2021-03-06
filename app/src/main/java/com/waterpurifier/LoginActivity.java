package com.waterpurifier;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLoginLayout;

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

        mLoginLayout = (LinearLayout)findViewById(R.id.ll_login_main_layout);
        mLoginLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(null != getWindow().getCurrentFocus()){
                    /**
                     * 点击空白位置 隐藏软键盘
                     */
                    InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    return mInputMethodManager.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                }
                return false;
            }
        });
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
//                if(TextUtils.isEmpty(tel)) {
//                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(psw)) {
//                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                login(tel, psw);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

                break;

            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    public void login(final String tel, final String pwd) {
        mLoadDialog.show();

        String url = basePath + "/user/login";
        RequestQueue queue = application.getRequestQueue();

        final Map<String, String> params = new HashMap<String, String>();
        params.put("tel", tel);
        params.put("password", pwd);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.optInt("code") < 0) {
                    Toast.makeText(getApplicationContext(), response.optString("error"), Toast.LENGTH_SHORT).show();
                    if (mLoadDialog.isShowing()) {
                        mLoadDialog.dismiss();
                    }
                } else {
                    application.loginUser = response.optJSONObject("user");

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), Constant.IS_DEBUG_MODE ? error.toString() : "网络故障，请稍候重试", Toast.LENGTH_SHORT).show();
                if (mLoadDialog.isShowing()) {
                    mLoadDialog.dismiss();
                }
            }
        });
        queue.add(jsonRequest);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(null != this.getCurrentFocus()){
//            /**
//             * 点击空白位置 隐藏软键盘
//             */
//            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
//        }
//        return super .onTouchEvent(event);
//    }
}
