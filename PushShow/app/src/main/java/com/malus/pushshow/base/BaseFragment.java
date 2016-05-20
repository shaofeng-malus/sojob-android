package com.malus.pushshow.base;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.malus.pushshow.R;

/**
 * 首页搜索
 */
public abstract class BaseFragment extends Fragment {
    // 检查弹出框数量
    int dialogNum;
    private Dialog loadDialog;

    public View loadView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void startAct() {
        initView();
        initAction();
        initData();
    }

    ;

    public abstract void initView();

    public abstract void initAction();

    public abstract void initData();

    /**
     * 显示正在加载的进度条
     */
    public void showLoadDialog() {
        dialogNum++;
        if (dialogNum > 1) {
            return;
        }
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
            loadDialog = null;
        }
        loadDialog = new Dialog(getActivity(), R.style.dialog);
        loadDialog.setCanceledOnTouchOutside(false);

        loadDialog.setContentView(R.layout.layout_base_dialog);
        try {
            loadDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 隐藏正在加载的进度条
     */
    public void dismissLoadDialog() {
        dialogNum--;
        if (dialogNum > 0) {
            return;
        }
        if (null != loadDialog && loadDialog.isShowing() == true) {
            loadDialog.dismiss();
            loadDialog = null;
        }
    }

    /**
     * 显示正在加载
     */
    public void showLoadView() {
        if(loadView!=null){
            loadView.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 隐藏正在加载
     */
    public void dismissLoadView() {
        if(loadView!=null){
            loadView.setVisibility(View.GONE);
        }
    }
}
