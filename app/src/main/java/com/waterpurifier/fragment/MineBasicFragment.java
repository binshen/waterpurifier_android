package com.waterpurifier.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;
import com.waterpurifier.SelectPicPopupWindow;

public class MineBasicFragment extends BaseFragment implements View.OnClickListener{

    private ImageView photo;

    private RelativeLayout mRlUpdatePwd;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine_basic, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout head_layout = (RelativeLayout) view.findViewById(R.id.head_layout);
        head_layout.setBackgroundResource(R.mipmap.head_discover);
        ImageView btn_head_left = (ImageView) view.findViewById(R.id.btn_head_left);
        btn_head_left.setOnClickListener(this);
        TextView tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);
        tv_head_title.setText("完善资料");
        tv_head_title.setTextColor(Color.WHITE);

        photo = (ImageView) view.findViewById(R.id.profile_image);
        photo.setOnClickListener(this);

        mRlUpdatePwd = (RelativeLayout) view.findViewById(R.id.rl_update_pwd);
        mRlUpdatePwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.profile_image:
                startActivityForResult(new Intent(getActivity(), SelectPicPopupWindow.class), 1);
                break;

            case R.id.btn_head_left:
                ft.replace(R.id.main_frame, new MineFragment());
                ft.commitAllowingStateLoss();
                break;

            case R.id.rl_update_pwd:
                ft.replace(R.id.main_frame, new MineUpdatePwdFragment());
                ft.commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case 1:
                if (data != null) {
                    //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
                    Uri mImageCaptureUri = data.getData();
                    //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
                    if (mImageCaptureUri != null) {
                        Bitmap image;
                        try {
                            //这个方法是根据Uri获取Bitmap图片的静态方法
                            image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mImageCaptureUri);
                            if (image != null) {
                                photo.setImageBitmap(image);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            //这里是有些拍照后的图片是直接存放到Bundle中的所以我们可以从这里面获取Bitmap图片
                            Bitmap image = extras.getParcelable("data");
                            if (image != null) {
                                photo.setImageBitmap(image);
                            }
                        }
                    }

                }
                break;
            default:
                break;

        }
    }
}
