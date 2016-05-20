package com.malus.pushshow.activity.Job;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.malus.pushshow.R;
import com.malus.pushshow.base.BaseActivity;
import com.malus.pushshow.fragment.job.job.CitySelectCityFragment;
import com.malus.pushshow.fragment.job.job.CitySelectProviceFragment;
import com.malus.pushshow.utils.SPHelper;
import com.malus.pushshow.widget.Topbar;
import com.malus.pushshow.widget.ViewPagerWithScrollable;

public class CitySelectActivity extends BaseActivity {
    private int fragmentCount = 3;

    ViewPagerWithScrollable mViewPager;
    Fragment[] fragments = new Fragment[fragmentCount];
    Topbar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_city_select);
    }

    @Override
    public void initView() {
        topbar = (Topbar)findViewById(R.id.topbar);
        topbar.setOnTopbarClickListener(new Topbar.onTopbarClickListener() {
            @Override
            public void onLeftButtonClick() {
                setResult(RESULT_CANCELED);
                finish();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }

            @Override
            public void onRightButtonClick() {

            }

            @Override
            public void onSearchButtonClick() {

            }
        });
        mViewPager = (ViewPagerWithScrollable) findViewById(R.id.city_select_pager);
        mViewPager.setCanScroll(false);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        if (fragments[position] == null) {
                            final CitySelectProviceFragment fragment = new CitySelectProviceFragment();
                            fragment.setOnFragmentClickListener(new CitySelectProviceFragment.OnFragmentClickListener() {
                                @Override
                                public void onItemClick(int sub, String city, String code) {
                                    if (sub == 0) {
                                        SPHelper.setCity(city);
                                        SPHelper.setCityCode(code);
                                        setResult(RESULT_OK);
                                        finish();
                                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                    } else {
                                        mViewPager.setCurrentItem(1);
                                        if(fragments[1]!=null && fragments[1] instanceof CitySelectCityFragment){
                                            CitySelectCityFragment cityFragment = (CitySelectCityFragment)fragments[1];
                                            cityFragment.getProvinceData(code);
                                        }
                                    }
                                }
                            });
                            fragments[position] = fragment;
                        }
                        break;
                    case 1:
                        if (fragments[position] == null) {
                            final CitySelectCityFragment fragment = new CitySelectCityFragment();
                            fragment.setOnFragmentClickListener(new CitySelectCityFragment.OnFragmentClickListener() {
                                @Override
                                public void onItemClick(int sub, String city, String code) {
                                    if (sub == 0) {
                                        SPHelper.setCity(city);
                                        SPHelper.setCityCode(code);
                                        setResult(RESULT_OK);
                                        finish();
                                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                    } else if(sub==-1){
                                        mViewPager.setCurrentItem(0);
                                    }else{
                                        mViewPager.setCurrentItem(2);
                                        if(fragments[2]!=null && fragments[2] instanceof CitySelectCityFragment){
                                            CitySelectCityFragment cityFragment = (CitySelectCityFragment)fragments[2];
                                            cityFragment.getProvinceData(code);
                                        }
                                    }
                                }

                            });
                            fragments[position] = fragment;
                        }
                        break;
                    case 2:
                        if (fragments[position] == null) {
                            final CitySelectCityFragment fragment = new CitySelectCityFragment();
                            fragment.setOnFragmentClickListener(new CitySelectCityFragment.OnFragmentClickListener() {
                                @Override
                                public void onItemClick(int sub, String city, String code) {
                                    if (sub==0) {
                                        SPHelper.setCity(city);
                                        SPHelper.setCityCode(code);
                                        setResult(RESULT_OK);
                                        finish();
                                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                    } else if(sub==-1){
                                        mViewPager.setCurrentItem(1);
                                    }
                                }
                            });
                            fragments[position] = fragment;
                        }
                        break;
                    default:
                        if (fragments[position] == null) {
                            fragments[position] = new CitySelectProviceFragment();
                        }
                        break;
                }

                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragmentCount;
            }
        });

    }

    @Override
    public void initAction() {

    }

    @Override
    public void initData() {

    }

}
