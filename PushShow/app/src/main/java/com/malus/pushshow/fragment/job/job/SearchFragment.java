package com.malus.pushshow.fragment.job.job;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.malus.pushshow.R;
import com.malus.pushshow.activity.Job.CitySelectActivity;
import com.malus.pushshow.activity.JobDetailActivity;
import com.malus.pushshow.activity.KeyGetActivity;
import com.malus.pushshow.adapter.job.JobListAdapter;
import com.malus.pushshow.base.BaseFragment;
import com.malus.pushshow.bean.job51.JobInfoForm51;
import com.malus.pushshow.bean.JobInfoFormZL;
import com.malus.pushshow.bean.job51.JobItemForm51;
import com.malus.pushshow.http.JsonRead;
import com.malus.pushshow.http.Url;
import com.malus.pushshow.utils.HttpTools;
import com.malus.pushshow.utils.Logs;
import com.malus.pushshow.utils.SPHelper;
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

/**
 * 首页搜索
 */
public class SearchFragment extends BaseFragment {
    private String tag = "SearchFragment";
    View mRootView;
    EditText mSearchKey;
    TextView mCity;
    ImageView mSearchBtn;
    XListView listview;
    JobListAdapter mAdapter;
    JobInfoFormZL jobInfoFormZL;
    String jobInfo;
//    List<JobItemFormZL> jobItemFormZLs = new ArrayList<>();
    List<JobItemForm51> jobItemForm51s = new ArrayList<>();

    JobInfoForm51 jobInfoForm51;
    private static final int HANDLER_GET_DATA = 1;
    private static final int HANDLER_GET_DATA_FAUIL = 0;

    private int mPage = 0;
    private boolean mNeedRefresh;
    private String mKey;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object obj = msg.obj;
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

//                    if(obj!=null&&obj instanceof JobInfoFormZL){
//                        jobInfoFormZL = (JobInfoFormZL)obj;
//                        if(jobInfoFormZL.getPositions()!=null){
//                            jobItemFormZLs.addAll(jobInfoFormZL.getPositions());
//                        }
//                    }
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

//        if(jobItemFormZLs.size()>0){
//            if(listview!=null){
//                listview.setPullLoadEnable(true);
//                listview.setPullRefreshEnable(true);
//                if(jobInfoFormZL!=null){
//                    if(jobInfoFormZL.getCount()<=jobItemFormZLs.size()){
//                        listview.setNoMoreData(true);
//                    }
//                }
//            }
//        }
    }

    private void afterGetData() {
        if(mNeedRefresh){
            mNeedRefresh = false;
//            jobItemFormZLs.clear();
            jobItemForm51s.clear();
        }
        if(listview!=null){
            listview.stopRefresh();
            listview.stopLoadMore();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search, container, false);
        startAct();
        return mRootView;
    }

    @Override
    public void initView() {
        mCity = (TextView)mRootView.findViewById(R.id.search_search_city);
        mSearchBtn = (ImageView)mRootView.findViewById(R.id.search_search_button);
        mSearchKey = (EditText)mRootView.findViewById(R.id.search_search_edit);
        listview = (XListView) mRootView.findViewById(R.id.search_search_list);
        listview.setPullRefreshEnable(false);
        listview.setPullLoadEnable(false);
        mAdapter = new JobListAdapter(getActivity(),jobItemForm51s,R.layout.item_job_list);
        listview.setAdapter(mAdapter);
    }

    @Override
    public void initAction() {
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNeedRefresh = true;
                getJobListForm51();
                hiddenKeyboard();
            }
        });
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mNeedRefresh = true;
                getJobListForm51();
            }

            @Override
            public void onLoadMore() {
                getJobListForm51();
            }

            @Override
            public void onRightSlip() {

            }

            @Override
            public void onLeftSlip() {

            }
        });
        mCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CitySelectActivity.class), 101);
                getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - 1;
                if (position >= 0 && position < jobItemForm51s.size()) {
                    JobItemForm51 item = jobItemForm51s.get(position);
                    Intent intent = new Intent(getActivity(), JobDetailActivity.class);
                    intent.putExtra("jobid", item.getJobid());
                    intent.putExtra("coid", item.getCoid());
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }
        });
//        mSearchKey.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                startActivity(new Intent(getActivity(), KeyGetActivity.class));
//                return true;
//            }
//        });
    }

    @Override
    public void initData() {
        jobInfo = SPHelper.getJobInfoFormZL();
        mKey = SPHelper.getSearchKey();

        if (!TextUtils.isEmpty(jobInfo)) {
            jobInfoForm51 = new JobInfoForm51();
            try {
                jobInfoForm51.parse(new ByteArrayInputStream(jobInfo.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (jobInfoForm51 != null) {
                Message message = handler.obtainMessage();
                message.what = HANDLER_GET_DATA;
                message.obj = jobInfoForm51;
                handler.sendMessage(message);
            } else {
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
            }
        }
        Logs.d(tag,"city:"+SPHelper.getCity());
        if(!TextUtils.isEmpty(SPHelper.getCity())){
            mCity.setText(SPHelper.getCity());
        }
        if(!TextUtils.isEmpty(mKey)){
            mSearchKey.setText(mKey);
        }
    }

    private void getJobLists() {
        String key = mSearchKey.getText().toString();
        if(!TextUtils.isEmpty(key)){
            mKey = key;
        }
        if (mNeedRefresh) {
            mPage = 1;
            jobInfo = "";
        }else{
            mPage++;
        }

        if (!TextUtils.isEmpty(mKey)) {
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder().url(Url.getZlSearchJob(mKey, mPage)).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String json = response.body().string();
                    if(TextUtils.isEmpty(jobInfo)){
                        jobInfo = json;
                    }
                    if (!TextUtils.isEmpty(json)) {
                        JobInfoFormZL jobInfoFormZL = JsonRead.getJobInfoFormZL(json);
                        if (jobInfoFormZL != null) {
                            Message message = handler.obtainMessage();
                            message.what = HANDLER_GET_DATA;
                            message.obj = jobInfoFormZL;
                            handler.sendMessage(message);
                        } else {
                            handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
                        }
                    } else {
                        handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
                    }
                }
            });
        }else{
            Toast.makeText(getActivity(),"请输入关键词",Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("message",jobInfoFormZL);
        outState.putString("key",mKey);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            mKey = savedInstanceState.getString("key");
            jobInfoFormZL = (JobInfoFormZL) savedInstanceState.getSerializable("message");
            if (jobInfoFormZL != null) {
                Message message = handler.obtainMessage();
                message.what = HANDLER_GET_DATA;
                message.obj = jobInfoFormZL;
                handler.sendMessage(message);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SPHelper.setJobInfoFormZL(jobInfo);
        SPHelper.setJobInfoForm51(jobInfo);
        SPHelper.setSearchKey(mKey);
    }

    public void hiddenKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mSearchKey != null) {
            imm.hideSoftInputFromWindow(mSearchKey.getWindowToken(), 0);
            mSearchKey.clearFocus();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            if(!TextUtils.isEmpty(SPHelper.getCity())){
                mCity.setText(SPHelper.getCity());
                mNeedRefresh = true;
                getJobListForm51();
            }
        }
    }

    private void getJobListForm51() {
        String key = mSearchKey.getText().toString();
        if(!TextUtils.isEmpty(key)){
            mKey = key;
        }
        if (mNeedRefresh) {
            mPage = 1;
            jobInfo = "";
        }else{
            mPage++;
        }

        if (!TextUtils.isEmpty(mKey)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpTools.get(Url.get51SearchJob(mKey, mPage), new HttpTools.HttpCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                jobInfoForm51  = new JobInfoForm51();
                                jobInfoForm51.parse(new ByteArrayInputStream(result.getBytes("UTF-8")));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            if(TextUtils.isEmpty(jobInfo)){
                                jobInfo = result;
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

        }else{
            Toast.makeText(getActivity(),"请输入关键词",Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
        }
    }



}
