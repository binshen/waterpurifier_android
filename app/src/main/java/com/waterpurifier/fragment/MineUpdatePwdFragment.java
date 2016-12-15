package com.waterpurifier.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;
import com.waterpurifier.base.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MineUpdatePwdFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtInputPwd;
    private EditText mEtInputPwd2;

    private Button mBtnUpdatePwd;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine_update_pwd, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout head_layout = (RelativeLayout) view.findViewById(R.id.head_layout);
        //head_layout.setBackgroundResource(R.mipmap.head_discover);
        //head_layout.setBackgroundColor(getResources().getColor(R.color.head_blue_bg));
        head_layout.setBackgroundResource(R.color.head_blue_bg);
        ImageView btn_head_left = (ImageView) view.findViewById(R.id.btn_head_left);
        btn_head_left.setOnClickListener(this);
        TextView tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);
        tv_head_title.setText("修改密码");
        tv_head_title.setTextColor(Color.WHITE);

        mBtnUpdatePwd = (Button) view.findViewById(R.id.btn_update_pwd);
        mBtnUpdatePwd.setOnClickListener(this);

        mEtInputPwd = (EditText) view.findViewById(R.id.et_input_new_pwd);
        mEtInputPwd2 = (EditText) view.findViewById(R.id.et_input_new_pwd2);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_head_left:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, new MineBasicFragment());
                ft.commitAllowingStateLoss();
                break;

            case R.id.btn_update_pwd:
                String password = mEtInputPwd.getText().toString();
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String password2 = mEtInputPwd2.getText().toString();
                if(TextUtils.isEmpty(password2)) {
                    Toast.makeText(getContext(), "请输入确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(password2)) {
                    Toast.makeText(getContext(), "两次输入的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }
                updatePassword(password);
                break;
        }
    }

    private void updatePassword(String password) {
        mLoadDialog.show();

        String url = basePath + "/" + application.loginUser.optString("_id")+ "/update_password";
        RequestQueue queue = application.getRequestQueue();

        final Map<String, String> params = new HashMap<String, String>();
        params.put("password", password);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.optInt("code") < 0) {
                    Toast.makeText(getContext(), response.optString("error"), Toast.LENGTH_SHORT).show();
                    if (mLoadDialog.isShowing()) {
                        mLoadDialog.dismiss();
                    }
                } else {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main_frame, new MineBasicFragment());
                    ft.commitAllowingStateLoss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), Constant.IS_DEBUG_MODE ? error.toString() : "网络故障，请稍候重试", Toast.LENGTH_SHORT).show();
                if (mLoadDialog.isShowing()) {
                    mLoadDialog.dismiss();
                }
            }
        });
        queue.add(jsonRequest);
    }
}
