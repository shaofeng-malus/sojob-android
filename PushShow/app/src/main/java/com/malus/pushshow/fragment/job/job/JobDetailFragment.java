package com.malus.pushshow.fragment.job.job;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.malus.pushshow.R;
import com.malus.pushshow.base.BaseFragment;
import com.malus.pushshow.bean.job51.JobDetailBody;
import com.malus.pushshow.bean.job51.JobDetailInfo;
import com.malus.pushshow.bean.job51.JobInfoForm51;
import com.malus.pushshow.http.Url;
import com.malus.pushshow.utils.HttpTools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobDetailFragment extends BaseFragment {
    private View mRootView;

    TextView name;
    TextView salary;
    TextView comp;
    TextView property;
    TextView scope;
    TextView publish;
    TextView person;
    TextView experience;
    TextView education;
    TextView area;
    TextView address;
    TextView walfare;
    TextView tag;
    TextView desc;

    JobDetailBody jobDetailBody;

    private static final int HANDLER_GET_DATA = 1;
    private static final int HANDLER_GET_DATA_FAUIL = 0;

    private int mPage = 0;
    private boolean mNeedRefresh;
    private String mKey;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object obj = msg.obj;
            switch (msg.what) {
                case HANDLER_GET_DATA:
                    setDatas();
                    break;
                case HANDLER_GET_DATA_FAUIL:
                    Toast.makeText(getActivity(), "获取详情失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    };

    public JobDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_job_detail, container, false);
        startAct();
        if(onCreateViewListener!=null){
            onCreateViewListener.onCreateView();
        }
        return mRootView;
    }

    @Override
    public void initView() {
        loadView = mRootView.findViewById(R.id.loadView);
        name = (TextView) mRootView.findViewById(R.id.job_detail_name);
        salary = (TextView) mRootView.findViewById(R.id.job_detail_salary);
        comp = (TextView) mRootView.findViewById(R.id.job_detail_comp);
        property = (TextView) mRootView.findViewById(R.id.job_detail_property);
        scope = (TextView) mRootView.findViewById(R.id.job_detail_scope);
        publish = (TextView) mRootView.findViewById(R.id.job_detail_publish);
        person = (TextView) mRootView.findViewById(R.id.job_detail_person);
        experience = (TextView) mRootView.findViewById(R.id.job_detail_experience);
        education = (TextView) mRootView.findViewById(R.id.job_detail_education);
        area = (TextView) mRootView.findViewById(R.id.job_detail_area);
        address = (TextView) mRootView.findViewById(R.id.job_detail_address);
        walfare = (TextView) mRootView.findViewById(R.id.job_detail_walfare);
        tag = (TextView) mRootView.findViewById(R.id.job_detail_tag);
        desc = (TextView) mRootView.findViewById(R.id.job_detail_desc);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void initData() {

    }
    public void getDetail(final String jobid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpTools.get(Url.get51JobDetail(jobid), new HttpTools.HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        JobDetailInfo jobDetailInfo = new JobDetailInfo();
                        try {
                            jobDetailInfo.parse(new ByteArrayInputStream(result.getBytes("UTF-8")));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if(!jobDetailInfo.getResult().equals("1")){
                            handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
                        }else{
                            jobDetailBody = jobDetailInfo.getResultbody();
                            handler.sendEmptyMessage(HANDLER_GET_DATA);
                        }
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

    private void setDatas() {
        if(jobDetailBody!=null){
            dismissLoadView();
            if(!TextUtils.isEmpty(jobDetailBody.getJobname())){
                name.setText(jobDetailBody.getJobname());
            }else{
                name.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getProvidesalary())){
                salary.setText(jobDetailBody.getProvidesalary());
            }else{
                salary.setText("面议");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getConame())){
                comp.setText(jobDetailBody.getConame());
            }else{
                comp.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getCotype())){
                property.setText(jobDetailBody.getCotype());
            }else{
                property.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getCosize())){
                scope.setText(jobDetailBody.getCosize());
            }else{
                scope.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getIssuedate())){
                publish.setText(jobDetailBody.getIssuedate());
            }else{
                publish.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getJobnum())){
                person.setText(jobDetailBody.getJobnum());
            }else{
                person.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getWorkyear())){
                experience.setText(jobDetailBody.getWorkyear());
            }else{
                experience.setText("不限");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getDegree())){
                education.setText(jobDetailBody.getDegree());
            }else{
                education.setText("不限");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getJobarea())){
                area.setText(jobDetailBody.getJobarea());
            }else{
                area.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getAddress())){
                address.setText(jobDetailBody.getAddress());
            }else{
                address.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getWelfare())){
                walfare.setText(jobDetailBody.getWelfare());
            }else{
                walfare.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getJobtag())){
                tag.setText(jobDetailBody.getJobtag());
            }else{
                tag.setText("");
            }
            if(!TextUtils.isEmpty(jobDetailBody.getJobinfo())){
                desc.setText(Html.fromHtml(jobDetailBody.getJobinfo()));
            }else{
                desc.setText("");
            }
        }else{

        }
    }
    private OnCreateViewListener onCreateViewListener;

    public void setOnCreateViewListener(OnCreateViewListener onCreateViewListener) {
        this.onCreateViewListener = onCreateViewListener;
    }

    public interface OnCreateViewListener{
       void onCreateView();
    }

}
