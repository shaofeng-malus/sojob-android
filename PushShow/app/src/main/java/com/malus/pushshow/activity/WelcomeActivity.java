package com.malus.pushshow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.malus.pushshow.MainActivity;
import com.malus.pushshow.R;
import com.malus.pushshow.base.BaseActivity;
import com.malus.pushshow.utils.SPHelper;

import java.util.Timer;

public class WelcomeActivity extends BaseActivity {
    public static final int HANDLER_LAUNCH = 100;
    public static final int HANDLER_GRADUI = 101;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_LAUNCH:
                    if(SPHelper.hasLaunch()){
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
                        finish();
                    }else{
                        startActivity(new Intent(WelcomeActivity.this, GrauidActivity.class));
                        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                        finish();
                    }
                    break;
                case HANDLER_GRADUI:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_welcome);

    }

    @Override
    public void initView() {
        handler.sendEmptyMessageDelayed(HANDLER_LAUNCH,3000);

    }

    @Override
    public void initAction() {

    }

    @Override
    public void initData() {

    }
    private long lastBackPress = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastBackPress > 2000){
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                lastBackPress = currentTime;
                return true;
            }else{
                return super.onKeyDown(keyCode, event);
            }
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
