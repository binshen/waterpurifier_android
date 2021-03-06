package com.waterpurifier.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.waterpurifier.R;
import com.waterpurifier.adapter.MyGridAdapter;
import com.waterpurifier.base.BaseFragment;
import com.waterpurifier.widget.MyGridView;

public class DiscoverFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, AdapterView.OnItemClickListener {

    private SliderLayout mSlider;
    private MyGridView mGridview;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_discover, container, false);
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
        tv_head_title.setText("发 现");
        tv_head_title.setTextColor(Color.WHITE);

        mSlider = (SliderLayout) view.findViewById(R.id.slider);

//        HashMap<String,String> url_maps = new HashMap<String, String>();
//        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
//
//        for(String name : url_maps.keySet()){
//            TextSliderView textSliderView = new TextSliderView(getContext());
//            //textSliderView.description(name);
//            textSliderView.image(url_maps.get(name));
//            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
//            //textSliderView.bundle(new Bundle());
//            //textSliderView.getBundle().putString("extra",name);
//            //textSliderView.setOnSliderClickListener(this);
//            mSlider.addSlider(textSliderView);
//        }

        TextSliderView sliderView1 = new TextSliderView(getContext());
        sliderView1.image(R.drawable.demo);
        sliderView1.setScaleType(BaseSliderView.ScaleType.Fit);
        mSlider.addSlider(sliderView1);
        TextSliderView sliderView2 = new TextSliderView(getContext());
        sliderView2.image(R.drawable.demo1);
        sliderView2.setScaleType(BaseSliderView.ScaleType.Fit);
        mSlider.addSlider(sliderView2);
        TextSliderView sliderView3 = new TextSliderView(getContext());
        sliderView3.image(R.drawable.demo2);
        sliderView3.setScaleType(BaseSliderView.ScaleType.Fit);
        mSlider.addSlider(sliderView3);
        TextSliderView sliderView4 = new TextSliderView(getContext());
        sliderView4.image(R.drawable.demo3);
        sliderView4.setScaleType(BaseSliderView.ScaleType.Fit);
        mSlider.addSlider(sliderView4);
        TextSliderView sliderView5 = new TextSliderView(getContext());
        sliderView5.image(R.drawable.demo4);
        sliderView5.setScaleType(BaseSliderView.ScaleType.Fit);
        mSlider.addSlider(sliderView5);

        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        //mSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        //mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(5000);
        mSlider.addOnPageChangeListener(this);

        mSlider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));

        mGridview = (MyGridView) view.findViewById(R.id.gridview);
        mGridview.setAdapter(new MyGridAdapter(getContext()));
        mGridview.setOnItemClickListener(this);
    }

    @Override
    public void onStop() {
        mSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(getContext(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.d("Slider Demo", "onPageScrolled: " + position);
    }

    @Override
    public void onPageSelected(int position) {
        //Log.d("Slider Demo", "onPageSelected: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Log.d("Slider Demo", "onPageScrollStateChanged: " + state);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {
            case 0: //拉拉秀平台
                Toast.makeText(getContext(), "拉拉秀平台", Toast.LENGTH_SHORT).show();
                break;
            case 1: //拉拉秀传媒
                Toast.makeText(getContext(), "拉拉秀传媒", Toast.LENGTH_SHORT).show();
                break;
            case 2: //火车票订购
                Toast.makeText(getContext(), "火车票订购", Toast.LENGTH_SHORT).show();
                break;
            case 3: //快递查询
                Toast.makeText(getContext(), "快递查询", Toast.LENGTH_SHORT).show();
                break;
            case 4: //地图导航
                Toast.makeText(getContext(), "地图导航", Toast.LENGTH_SHORT).show();
                break;
            case 5: //生活工具
                Toast.makeText(getContext(), "生活工具", Toast.LENGTH_SHORT).show();
                break;
            case 6: //违章查询
                Toast.makeText(getContext(), "违章查询", Toast.LENGTH_SHORT).show();
                break;
            case 7: //在线翻译
                Toast.makeText(getContext(), "在线翻译", Toast.LENGTH_SHORT).show();
                break;
            case 8: //机票订购
                Toast.makeText(getContext(), "机票订购", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
