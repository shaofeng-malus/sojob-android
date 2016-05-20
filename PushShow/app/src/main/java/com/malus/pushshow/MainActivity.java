package com.malus.pushshow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.malus.pushshow.base.BaseActivity;
import com.malus.pushshow.fragment.job.interview.InterviewFragment;
import com.malus.pushshow.fragment.job.job.SearchFragment;
import com.malus.pushshow.utils.Variables;
import com.malus.pushshow.widget.Topbar;

public class MainActivity extends BaseActivity {
    ViewPager mViewPager;
    Fragment[] mFragments;
    RadioGroup mRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dms = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dms);
        Variables.screenW = dms.widthPixels;
        Variables.screenH = dms.heightPixels;

    }
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mRadio = (RadioGroup)findViewById(R.id.main_radio_group);
        mRadio.check(R.id.main_radio_btn1);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initAction() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        if (mFragments[position] != null) {
                            fragment = mFragments[position];
                        } else {
                            fragment = mFragments[position] = new SearchFragment();
                        }
                        break;
                    case 1:
                        if (mFragments[position] != null) {
                            fragment = mFragments[position];
                        } else {
                            fragment = mFragments[position] = new InterviewFragment();
                        }
                        break;
                    case 2:
                        if (mFragments[position] != null) {
                            fragment = mFragments[position];
                        } else {
                            fragment = mFragments[position] = new InterviewFragment();
                        }
                        break;
                    case 3:
                        if (mFragments[position] != null) {
                            fragment = mFragments[position];
                        } else {
                            fragment = mFragments[position] = new InterviewFragment();
                        }
                        break;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                if(mFragments==null){
                    mFragments = new Fragment[4];
                }
                return mFragments.length;
            }
        });
        mRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_radio_btn1:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.main_radio_btn2:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.main_radio_btn3:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.main_radio_btn4:
                        mViewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }
    private long lastBackPress = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastBackPress > 2000){
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                lastBackPress = currentTime;
                return true;
            }else{
                return super.onKeyDown(keyCode, event);
            }
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
