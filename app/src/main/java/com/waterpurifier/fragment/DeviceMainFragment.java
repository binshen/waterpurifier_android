package com.waterpurifier.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.waterpurifier.R;
import com.waterpurifier.base.BaseFragment;

public class DeviceMainFragment extends BaseFragment {

    private SliderLayout mGallery;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_device_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout head_layout = (RelativeLayout) view.findViewById(R.id.head_layout);
        head_layout.setBackgroundResource(R.mipmap.head_discover);
        ImageView btn_head_left = (ImageView) view.findViewById(R.id.btn_head_left);
        btn_head_left.setVisibility(View.INVISIBLE);
        TextView tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);
        tv_head_title.setText("智能净水器");
        tv_head_title.setTextColor(Color.WHITE);

        mGallery = (SliderLayout) view.findViewById(R.id.gallery);

        DefaultSliderView sliderView1 = new DefaultSliderView(getContext());
        sliderView1.image(R.mipmap.device_view_1);
        sliderView1.setScaleType(BaseSliderView.ScaleType.Fit);
        mGallery.addSlider(sliderView1);

        DefaultSliderView sliderView2 = new DefaultSliderView(getContext());
        sliderView2.image(R.mipmap.device_view_2);
        sliderView2.setScaleType(BaseSliderView.ScaleType.Fit);
        mGallery.addSlider(sliderView2);
        mGallery.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mGallery.stopAutoCycle();
    }
}
