package com.malus.pushshow.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.malus.pushshow.R;
import com.malus.pushshow.base.BaseActivity;
import com.malus.pushshow.fragment.job.interview.InterviewFragment;
import com.malus.pushshow.fragment.job.job.CompDetailFragment;
import com.malus.pushshow.fragment.job.job.JobDetailFragment;

public class JobDetailActivity extends BaseActivity {
    ImageView mBack;
    RadioGroup mRadio;
    RadioButton mRadioBtn1;
    RadioButton mRadioBtn2;

    ViewPager mPager;
    Fragment[] fragments = new Fragment[2];

    String coid;
    String jobId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_job_detail);
    }

    @Override
    public void initView() {
        mBack = (ImageView)findViewById(R.id.detail_back);
        mRadio = (RadioGroup)findViewById(R.id.detail_radio_group);
        mRadioBtn1 = (RadioButton)findViewById(R.id.detail_radio_btn1);
        mRadioBtn2 = (RadioButton)findViewById(R.id.detail_radio_btn2);
        mPager = (ViewPager)findViewById(R.id.detail_viewpager);
        coid = getIntent().getStringExtra("coid");
        jobId = getIntent().getStringExtra("jobid");
    }

    @Override
    public void initAction() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    mRadio.check(mRadioBtn1.getId());
                }else{
                    mRadio.check(mRadioBtn2.getId());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == mRadioBtn1.getId()){
                    mPager.setCurrentItem(0);
                }else{
                    mPager.setCurrentItem(1);
                }
            }
        });
    }

    @Override
    public void initData() {

        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = fragments[position];
                if(fragment == null){
                    if(position==0){
                        fragment = new JobDetailFragment();
                        fragments[position] = fragment;
                        ((JobDetailFragment)fragment).setOnCreateViewListener(new JobDetailFragment.OnCreateViewListener() {
                            @Override
                            public void onCreateView() {
                                Fragment fragment = fragments[0];
                                ((JobDetailFragment)fragment).getDetail(jobId);
                            }
                        });
                    }else {
                        fragment = new CompDetailFragment();
                        fragments[position] = fragment;
                        ((CompDetailFragment)fragment).setOnCreateViewListener(new CompDetailFragment.OnCreateViewListener() {
                            @Override
                            public void onCreateView() {
                                Fragment fragment = fragments[1];
                                ((CompDetailFragment)fragment).getDetail(coid);
                            }
                        });
                    }
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}