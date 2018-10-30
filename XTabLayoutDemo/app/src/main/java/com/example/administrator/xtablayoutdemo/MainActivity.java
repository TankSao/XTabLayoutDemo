package com.example.administrator.xtablayoutdemo;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_tab)
    XTabLayout mainTab;
    @BindView(R.id.tab_viewpager)
    ViewPager mainViewpager;
    private MyFragmentPagerAdapter mLabVpAdapter;
    private List<String> mTitles;
    private ArrayList<Fragment> mFragments;
    private int postion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        hideTopUIMenu();
        hideBottomUIMenu();
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mainViewpager.setOffscreenPageLimit(2);
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitles.add("语文");
        mTitles.add("数学");
        mTitles.add("英语");
        mTitles.add("物理");
        mTitles.add("化学");
        mFragments.add(new MyFragment("语文"));
        mFragments.add(new MyFragment("数学"));
        mFragments.add(new MyFragment("英语"));
        mFragments.add(new MyFragment("物理"));
        mFragments.add(new MyFragment("化学"));
        mLabVpAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mainViewpager.setAdapter(mLabVpAdapter);
        mainTab.setupWithViewPager(mainViewpager);
        mainTab.getTabAt(0).setCustomView(getTabView(0));
        mainTab.getTabAt(1).setCustomView(getTabView(1));
        mainTab.getTabAt(2).setCustomView(getTabView(2));
        mainTab.getTabAt(3).setCustomView(getTabView(3));
        mainTab.getTabAt(4).setCustomView(getTabView(4));
        mainTab.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
    }
    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_top, null);
        TextView txt_title = view.findViewById(R.id.txt_title);
        TextView txt_xian = view.findViewById(R.id.txt_xian);
        txt_title.setText(mTitles.get(position));
        if (position == 0) {
            txt_title.setAlpha(1f);
        }
        if (position == 1) {
            txt_xian.setVisibility(View.INVISIBLE);
            txt_title.setAlpha(0.8f);
        }
        if (position == 2) {
            txt_xian.setVisibility(View.INVISIBLE);
            txt_title.setAlpha(0.8f);
        }
        if (position == 3) {
            txt_xian.setVisibility(View.INVISIBLE);
            txt_title.setAlpha(0.8f);
        }
        if (position == 4) {
            txt_xian.setVisibility(View.INVISIBLE);
            txt_title.setAlpha(0.8f);
        }
        return view;
    }
    private void changeTabSelect(XTabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView txt_xian = view.findViewById(R.id.txt_xian);
        TextView txt_title = view.findViewById(R.id.txt_title);
        if (txt_title.getText().toString().equals("语文")) {
            mainViewpager.setCurrentItem(0);
            postion = 0;
        }
        if (txt_title.getText().toString().equals("数学")) {
            mainViewpager.setCurrentItem(1);
            postion = 1;
        }
        if (txt_title.getText().toString().equals("英语")) {
            mainViewpager.setCurrentItem(2);
            postion = 2;
        }
        if (txt_title.getText().toString().equals("物理")) {
            mainViewpager.setCurrentItem(3);
            postion = 3;
        }
        if (txt_title.getText().toString().equals("化学")) {
            mainViewpager.setCurrentItem(4);
            postion = 4;
        }
        txt_xian.setVisibility(View.VISIBLE);
        txt_title.setAlpha(1f);
    }

    private void changeTabNormal(XTabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView txt_xian = view.findViewById(R.id.txt_xian);
        TextView txt_title = view.findViewById(R.id.txt_title);
        txt_xian.setVisibility(View.INVISIBLE);
        txt_title.setAlpha(0.8f);
    }
    /**
     * 隐藏虚拟按键，并且全屏
     */
    public void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    /**
     * 沉浸式导航栏
     */
    public void hideTopUIMenu() {
        StatusBarUtils.with(this)
                .init();
    }
}
