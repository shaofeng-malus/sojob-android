package com.malus.pushshow.fragment.job.job;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.malus.pushshow.R;
import com.malus.pushshow.adapter.job.ProvinceListAdapter;
import com.malus.pushshow.base.BaseFragment;
import com.malus.pushshow.bean.ProvinceInfoForm51;
import com.malus.pushshow.bean.ProvinceItemForm51;
import com.malus.pushshow.http.Url;
import com.malus.pushshow.utils.HttpTools;
import com.malus.pushshow.utils.Logs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CitySelectCityFragment extends BaseFragment {
    private static final String TAG = "CitySelectProviceFragment";
    private static final int HANDLER_GET_CITY_INFO_SUCCESS = 200;
    private static final int HANDLER_INT_ERROR = 400;
    private View mRootView;

    private ListView mListview;
    private ProvinceListAdapter mAdapter;
    private List<ProvinceItemForm51> datas = new ArrayList<>();
    ProvinceInfoForm51 provinceInfoForm51 = new ProvinceInfoForm51();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dismissLoadDialog();
            switch (msg.what) {
                case HANDLER_GET_CITY_INFO_SUCCESS:
                    Logs.d(TAG, provinceInfoForm51.toString());
                    datas.clear();
                    datas.addAll(provinceInfoForm51.getResultbody());
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
                case HANDLER_INT_ERROR:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_city_select_city, null);
        startAct();
        return mRootView;
    }

    @Override
    public void initView() {
        mAdapter = new ProvinceListAdapter(getActivity(), datas, R.layout.item_province_list);
        mListview = (ListView) mRootView.findViewById(R.id.city_select_city_list);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (datas.size() > position && position >= 0) {
                    if(position==0){
                        onFragmentClickListener.onItemClick(-1,datas.get(position).getValue(),datas.get(position).getCode());
                    }else{
                        if(datas.get(position)!=null){
                            if(datas.get(position).getHassub()!=null){
                                if (datas.get(position).getHassub().equals("1")) {
                                    onFragmentClickListener.onItemClick(1,datas.get(position).getValue(),datas.get(position).getCode());
                                }else{
                                    onFragmentClickListener.onItemClick(0,datas.get(position).getValue(),datas.get(position).getCode());
                                }
                            }else{
                                onFragmentClickListener.onItemClick(0,datas.get(position).getValue(),datas.get(position).getCode());
                            }
                        }else{
                            onFragmentClickListener.onItemClick(0,datas.get(position).getValue(),datas.get(position).getCode());
                        }
                    }


                }
            }
        });
    }

    @Override
    public void initAction() {}

    @Override
    public void initData() {

    }

    public void getProvinceData(final String code) {
        showLoadDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpTools.getInputStream(Url.getJob51CityCity(code), new HttpTools.HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {
                    }

                    @Override
                    public void onSuccess(InputStream result) {
                        provinceInfoForm51.parse(result);
                        handler.sendEmptyMessage(HANDLER_GET_CITY_INFO_SUCCESS);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        handler.sendEmptyMessage(HANDLER_INT_ERROR);
                    }
                });
            }
        }).start();
    }

    private OnFragmentClickListener onFragmentClickListener;
    public void setOnFragmentClickListener(OnFragmentClickListener onFragmentClickListener){
        this.onFragmentClickListener = onFragmentClickListener;
    }
    public interface OnFragmentClickListener{
        //sub -1 返回上一级  0 选择当前  1 下一级
        void onItemClick(int sub,String city,String code);
    }

}
