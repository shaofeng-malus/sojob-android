package com.malus.pushshow.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/********************
 * 作者：malus
 * 日期：16/2/25
 * 时间：上午11:46
 * 注释：
 ********************/
public class ViewPagerWithScrollable extends ViewPager {
    private boolean isCanScroll = false;
    public ViewPagerWithScrollable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerWithScrollable(Context context) {
        super(context);
    }

    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if(isCanScroll){
            return super.canScroll(v, checkV, dx, x, y);
        }else{
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isCanScroll){
            return super.onTouchEvent(ev);
        }else{
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isCanScroll){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }
}
