package com.mmt.microlove.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.mmt.microlove.R;
import com.mmt.microlove.adapter.MainPagerAdapter;
import com.mmt.microlove.fragment.ChatFragment;
import com.mmt.microlove.fragment.LocationFragment;
import com.mmt.microlove.fragment.MineFragment;
import com.mmt.microlove.fragment.TimeFragment;
import com.mmt.microlove.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 "定位"界面
 * @作者 wuqiuyun
 * @时间 2017-4-11
 */
public class MainActivity extends FragmentActivity implements
        OnCheckedChangeListener, OnPageChangeListener {
    private TextView tvTitle;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton rbLocation;
    private RadioButton rbChat;
    private RadioButton rbCouple;
    private RadioButton rbMine;

    private List<Fragment> fragments;
    private MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        setAdapter();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_bottom);
        rbLocation = (RadioButton) findViewById(R.id.rb_location);
        rbChat = (RadioButton) findViewById(R.id.rb_chat);
        rbCouple = (RadioButton) findViewById(R.id.rb_time);
        rbMine = (RadioButton) findViewById(R.id.rb_mine);

    }

    private void setListener() {
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager.setOnPageChangeListener(this);
    }

    private void setAdapter() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new LocationFragment());
        fragments.add(new ChatFragment());
        fragments.add(new TimeFragment());
        fragments.add(new MineFragment());
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),
                fragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        switch (arg1) {
            case R.id.rb_location:
                setTvTitle(R.string.my_location);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_chat:
                setTvTitle(R.string.fragment_chat);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_time:
                setTvTitle(R.string.fragment_time);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_mine:
                setTvTitle(R.string.fragment_mine);
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        switch (arg0) {
            case 0:
                setTvTitle(R.string.my_location);
                rbLocation.setChecked(true);
                break;
            case 1:
                setTvTitle(R.string.fragment_chat);
                rbChat.setChecked(true);
                break;
            case 2:
                setTvTitle(R.string.fragment_time);
                rbCouple.setChecked(true);
                break;
            case 3:
                setTvTitle(R.string.fragment_mine);
                rbMine.setChecked(true);
                break;

            default:
                break;
        }
    }

    private void setTvTitle(int resId) {
        tvTitle.setText(UIUtils.getString(resId));
        tvTitle.setTextColor(UIUtils.getColor(R.color.white));
    }

}
