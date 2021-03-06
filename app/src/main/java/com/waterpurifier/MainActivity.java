package com.waterpurifier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.waterpurifier.base.BaseActivity;
import com.waterpurifier.fragment.DiscoverFragment;
import com.waterpurifier.fragment.MineFragment;
import com.waterpurifier.fragment.DeviceFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;

    private long clickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        //BadgeItem numberBadgeItem = new BadgeItem().setBorderWidth(2).setBackgroundColor(Color.RED).setText("3").setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.main_purify_2, "净化").setActiveColor("blue").setInactiveIconResource(R.mipmap.main_purify_1)).setInActiveColor("blue");
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.main_discover_2, "发现").setActiveColor("blue").setInactiveIconResource(R.mipmap.main_discover_1)).setInActiveColor("blue");
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.main_mine_2, "我的").setActiveColor("blue").setInactiveIconResource(R.mipmap.main_mine_1)).setInActiveColor("blue");
        bottomNavigationBar.setFirstSelectedPosition(0).initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DeviceFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new MineFragment());
        return fragments;
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame, new DeviceFragment());
        ft.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fragments.get(position);
        ft.replace(R.id.main_frame, fragment);
        ft.commitAllowingStateLoss();

//        if (fragments != null) {
//            if (position < fragments.size()) {
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment fragment = fragments.get(position);
//                if (fragment.isAdded()) {
//                    ft.replace(R.id.main_frame, fragment);
//                } else {
//                    ft.add(R.id.main_frame, fragment);
//                }
//                ft.commitAllowingStateLoss();
//            }
//        }
    }

    @Override
    public void onTabUnselected(int position) {
//        if (fragments != null) {
//            if (position < fragments.size()) {
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment fragment = fragments.get(position);
//                ft.remove(fragment);
//                ft.commitAllowingStateLoss();
//            }
//        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - clickTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
                clickTime = System.currentTimeMillis();
            } else {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
