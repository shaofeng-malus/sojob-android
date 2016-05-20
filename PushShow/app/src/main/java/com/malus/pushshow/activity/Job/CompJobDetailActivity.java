package com.malus.pushshow.activity.Job;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.malus.pushshow.R;
import com.malus.pushshow.base.BaseActivity;
import com.malus.pushshow.fragment.job.job.JobDetailFragment;
import com.malus.pushshow.widget.Topbar;

public class CompJobDetailActivity extends BaseActivity {
    View mContent;
    String jobid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_comp_job_detail);
    }

    @Override
    public void initView() {
        mContent = (View) findViewById(R.id.comp_job_detail_content);
    }

    @Override
    public void initAction() {
        ((Topbar)findViewById(R.id.topbar)).setOnTopbarClickListener(new Topbar.onTopbarClickListener() {
            @Override
            public void onLeftButtonClick() {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }

            @Override
            public void onRightButtonClick() {

            }

            @Override
            public void onSearchButtonClick() {

            }
        });
    }

    @Override
    public void initData() {
        jobid = getIntent().getStringExtra("jobid");
        final JobDetailFragment fragment = new JobDetailFragment();
        fragment.setOnCreateViewListener(new JobDetailFragment.OnCreateViewListener() {
            @Override
            public void onCreateView() {
                fragment.getDetail(jobid);
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.comp_job_detail_content, fragment);
        transaction.commit();

    }

}
