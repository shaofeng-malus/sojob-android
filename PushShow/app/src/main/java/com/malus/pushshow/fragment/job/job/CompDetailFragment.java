package com.malus.pushshow.fragment.job.job;


import android.content.Intent;
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
import com.malus.pushshow.activity.Job.CompJobsActivity;
import com.malus.pushshow.base.BaseFragment;
import com.malus.pushshow.bean.job51.CompDetailBody;
import com.malus.pushshow.bean.job51.CompDetailInfo;
import com.malus.pushshow.bean.job51.JobDetailBody;
import com.malus.pushshow.bean.job51.JobDetailInfo;
import com.malus.pushshow.http.Url;
import com.malus.pushshow.utils.HttpTools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 * 公司详情
 */
public class CompDetailFragment extends BaseFragment {
    private View mRootView;

    TextView name;
    TextView indtype;
    TextView property;
    TextView scope;
    TextView address;
    View jobs;
    TextView desc;

    String coid;

    CompDetailBody compDetailBody;

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

    public CompDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_comp_detail, container, false);
        startAct();
        if(onCreateViewListener!=null){
            onCreateViewListener.onCreateView();
        }
        return mRootView;
    }

    @Override
    public void initView() {
        loadView = mRootView.findViewById(R.id.loadView);
        name = (TextView) mRootView.findViewById(R.id.comp_detail_name);
        indtype = (TextView) mRootView.findViewById(R.id.comp_detail_indtype);
        property = (TextView) mRootView.findViewById(R.id.comp_detail_property);
        scope = (TextView) mRootView.findViewById(R.id.comp_detail_scope);
        address = (TextView) mRootView.findViewById(R.id.comp_detail_address);
        desc = (TextView) mRootView.findViewById(R.id.comp_detail_desc);
        jobs =  mRootView.findViewById(R.id.comp_detail_jobs);
    }

    @Override
    public void initAction() {
        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CompJobsActivity.class);
                intent.putExtra("coid",coid);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

    }

    @Override
    public void initData() {

    }
    public void getDetail(final String coid) {
        this.coid = coid;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpTools.get(Url.get51CompDetail(coid), new HttpTools.HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        CompDetailInfo compDetailInfo = new CompDetailInfo();
                        try {
                            compDetailInfo.parse(new ByteArrayInputStream(result.getBytes("UTF-8")));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if(!compDetailInfo.getResult().equals("1")){
                            handler.sendEmptyMessage(HANDLER_GET_DATA_FAUIL);
                        }else{
                            compDetailBody = compDetailInfo.getResultbody();
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
        if(compDetailBody!=null){
            dismissLoadView();
            if(!TextUtils.isEmpty(compDetailBody.getConame())){
                name.setText(compDetailBody.getConame());
            }else{
                name.setText("");
            }
            if(!TextUtils.isEmpty(compDetailBody.getCotype())){
                property.setText(compDetailBody.getCotype());
            }else{
                property.setText("");
            }
            if(!TextUtils.isEmpty(compDetailBody.getCosize())){
                scope.setText(compDetailBody.getCosize());
            }else{
                scope.setText("");
            }
            if(!TextUtils.isEmpty(compDetailBody.getCaddr())){
                address.setText(compDetailBody.getCaddr());
            }else{
                address.setText("");
            }
            if(!TextUtils.isEmpty(compDetailBody.getCoinfo())){
                desc.setText(Html.fromHtml(compDetailBody.getCoinfo()));
            }else{
                desc.setText("");
            }
            if(!TextUtils.isEmpty(compDetailBody.getIndtype1())){
                indtype.setText(compDetailBody.getIndtype1());
            }else{
                indtype.setText("");
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
