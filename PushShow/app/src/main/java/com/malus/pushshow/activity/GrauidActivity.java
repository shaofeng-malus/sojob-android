package com.malus.pushshow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.malus.pushshow.MainActivity;
import com.malus.pushshow.R;
import com.malus.pushshow.base.BaseActivity;
import com.malus.pushshow.utils.SPHelper;

public class GrauidActivity extends BaseActivity {
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_grauid);
    }

    @Override
    public void initView() {
        pager = (ViewPager) findViewById(R.id.grauid_pager);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void initData() {
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                View view = LayoutInflater.from(GrauidActivity.this).inflate(R.layout.graid_pager_view, null);
                ImageView image = (ImageView) view.findViewById(R.id.graid_pager_image);
                switch (position){
                    case 0:
                        image.setImageResource(R.mipmap.guide_one);
                        image.setOnClickListener(null);
                        break;
                    case 1:
                        image.setImageResource(R.mipmap.guide_two);
                        image.setOnClickListener(null);
                        break;
                    case 2:
                        image.setImageResource(R.mipmap.guide_three);
                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SPHelper.setLaunch();
                                startActivity(new Intent(GrauidActivity.this, MainActivity.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                finish();
                            }
                        });
                        break;
                }
                container.addView(view);
                return view;
            }
        });
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
