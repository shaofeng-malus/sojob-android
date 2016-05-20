package com.malus.pushshow.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.malus.pushshow.R;

public abstract class BaseActivity extends FragmentActivity {
    // 检查弹出框数量
    int dialogNum;
    private Dialog loadDialog;

    private View loadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout();
        loadView = findViewById(R.id.loadView);
        initView();
        initAction();
        initData();
    }
    public abstract void setContentLayout();
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
        loadDialog = new Dialog(this, R.style.dialog);
        loadDialog.setCanceledOnTouchOutside(false);

        loadDialog.setContentView(R.layout.layout_dialog);
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
