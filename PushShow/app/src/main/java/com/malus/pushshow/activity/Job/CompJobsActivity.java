package com.malus.pushshow.activity.Job;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.malus.pushshow.R;
import com.malus.pushshow.activity.JobDetailActivity;
import com.malus.pushshow.adapter.job.JobListAdapter;
import com.malus.pushshow.base.BaseActivity;
import com.malus.pushshow.bean.JobInfoFormZL;
import com.malus.pushshow.bean.job51.JobInfoForm51;
import com.malus.pushshow.bean.job51.JobItemForm51;
import com.malus.pushshow.http.JsonRead;
import com.malus.pushshow.http.Url;
import com.malus.pushshow.utils.HttpTools;
import com.malus.pushshow.utils.Logs;
import com.malus.pushshow.utils.SPHelper;
import com.malus.pushshow.widget.Topbar;
import com.malus.pushshow.widget.XListView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CompJobsActivity extends BaseActivity {

    private String tag = "CompJobsActivity";
    String coid;
    XListView listview;
    JobListAdapter mAdapter;
    List<JobItemForm51> jobItemForm51s = new ArrayList<>();
    JobInfoForm51 jobInfoForm51;

    private static final int HANDLER_GET_DATA = 1;
    private static final int HANDLER_GET_DATA_FAUIL = 0;

    private int mPage = 0;
    private boolean mNeedRefresh;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case HANDLER_GET_DATA:
                    afterGetData();
                    if (jobInfoForm51 != null) {
                        if(jobInfoForm51.getResult()!=null){
                            if (jobInfoForm51.getResult().equals("1")) {
                                if (jobInfoForm51.getResultbody().getItems()!=null) {
                                    jobItemForm51s.addAll(jobInfoForm51.getResultbody().getItems());
                                }
                            }
                        }
                    }
                    if(mAdapter!=null){
                        mAdapter.notifyDataSetChanged();
                    }
                    configListview();
                    break;
                case HANDLER_GET_DATA_FAUIL:
                    afterGetData();
                    break;
            }
        }
    };

    private void configListview() {
        if(jobItemForm51s.size()>0){
            if(listview!=null){
                listview.setPullLoadEnable(true);
                listview.setPullRefreshEnable(true);
                if(jobInfoForm51!=null){
                    if(jobInfoForm51.getResultbody()!=null){
                        if(jobInfoForm51.getResultbody().getTotalcount()!=null){
                            int count = Integer.parseInt(jobInfoForm51.getResultbody().getTotalcount());
                            if(count<=jobItemForm51s.size()){
                                listview.setNoMoreData(true);
                            }
                        }
                    }

                }
            }
        }
    }

    private void afterGetData() {
        dismissLoadView();
        if(mNeedRefresh){
            mNeedRefresh = false;
            jobItemForm51s.clear();
        }
        if(listview!=null){
            listview.stopRefresh();
            listview.stopLoadMore();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_comp_jobs);
    }

    @Override
    public void initView() {
        listview = (XListView) findViewById(R.id.comp_jobs_listview);
        listview.setPullRefreshEnable(false);
        listview.setPullLoadEnable(false);
        mAdapter = new JobListAdapter(this,jobItemForm51s,R.layout.item_job_list);
        listview.setAdapter(mAdapter);
    }

    @Override
    public void initAction() {
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mNeedRefresh = true;
                getCompJobsListForm51();
            }

            @Override
            public void onLoadMore() {
                getCompJobsListForm51();
            }

            @Override
            public void onRightSlip() {

            }

            @Override
            public void onLeftSlip() {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - 1;
                if (position >= 0 && position < jobItemForm51s.size()) {
                    JobItemForm51 item = jobItemForm51s.get(position);
                    Intent intent = new Intent(CompJobsActivity.this, CompJobDetailActivity.class);
                    intent.putExtra("jobid", item.getJobid());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }
        });
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
        coid = getIntent().getStringExtra("coid");
        getCompJobsListForm51();
    }

    /**
     * 获取某公司职位列表
     */
    private void getCompJobsListForm51() {
        if (mNeedRefresh) {
            mPage = 1;
        }else{
            mPage++;
        }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpTools.get(Url.get51CompJobs(coid), new HttpTools.HttpCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                jobInfoForm51  = new JobInfoForm51();
                                jobInfoForm51.parse(new ByteArrayInputStream(result.getBytes("UTF-8")));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(HANDLER_GET_DATA);
                        }

                        @Override
                        public void onSuccess(InputStream result) {

                        }

                        @Override
                        public void onFailure(int code, String msg) {
                            handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
                        }
                    });
                }
            }).start();

    }
}
