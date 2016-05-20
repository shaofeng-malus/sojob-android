/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.malus.pushshow.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malus.pushshow.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {
    /**
     * Title text used when no title is provided by the adapter.
     */
    private static final CharSequence EMPTY_TITLE = "";
    private static boolean isDisable = false;
    private long mOldClickedTime;
    public void setLastOneDisable(boolean b) {
        isDisable = b;
    }

    /**
     * Interface for a callback when the selected tab has been reselected.
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);

        boolean onTabSelected(int position);
    }

    private Runnable mTabSelector;
    private final OnClickListener mTabClickListener = new OnClickListener() {
        public synchronized void onClick(View view) {
            long newClickTime=System.currentTimeMillis();
            if (mOldClickedTime==0){
                mOldClickedTime=newClickTime;
            }else{
                if (newClickTime-mOldClickedTime<500){
                    mOldClickedTime=newClickTime;
                    return;
                }else{
                    mOldClickedTime=newClickTime;
                }
            }

            TabView tabView = (TabView) view;
            final int oldSelected = mViewPager.getCurrentItem();
            final int newSelected = tabView.getIndex();


            //推送
//            if(Variables.ispusht){
//            	mViewPager.setCurrentItem(Variables.zhuanqu);
//            	if (oldSelected == newSelected && mTabReselectedListener != null) {
//                    mTabReselectedListener.onTabReselected(Variables.zhuanqu);
//                }
//            }else {

            mViewPager.setCurrentItem(newSelected);

            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
//			}

            if (mTabReselectedListener != null) {
                boolean shouldReturn = mTabReselectedListener.onTabSelected(newSelected);
            }
        }
    };

    private final IcsLinearLayout mTabLayout;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;

    private int mMaxTabWidth;
    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        mTabLayout = new IcsLinearLayout(context, R.attr.vpiTabPageIndicatorStyle);
        addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT));


        //监听TAB滑动
        scrollerTask = new Runnable() {
            @Override
            public void run() {
                int newPosition = getScrollX();
                if (intitPosition - newPosition == 0) {
                    if (onScrollstopListner == null) {
                        return;
                    }
                    onScrollstopListner.onScrollStoped();
                    Rect outRect = new Rect();
                    getDrawingRect(outRect);
                    if (getScrollX() == 0) {
                        onScrollstopListner.onScrollToLeftEdge();
                    } else if (childWidth + getPaddingLeft() + getPaddingRight() == outRect.right) {
                        onScrollstopListner.onScrollToRightEdge();
                    } else {
                        onScrollstopListner.onScrollToMiddle();
                    }
                } else {
                    intitPosition = getScrollX();
                    postDelayed(scrollerTask, newCheck);
                }
            }
        };

    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(scrollPos, 0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }

    private void addTab(int index, CharSequence text, int iconResId) {
        final TabView tabView = new TabView(getContext());
        tabView.mIndex = index;
        tabView.setFocusable(true);
        if (ViewPagerCount == index + 1) {
            if (!isDisable) {
                tabView.setOnClickListener(mTabClickListener);
            } else {
                tabView.getBackground().setAlpha(100);
                tabView.setTextColor(getResources().getColor(R.color.text_color_medium));
            }
        } else {

            tabView.setOnClickListener(mTabClickListener);
        }
        tabView.setText(text);

        if (iconResId != 0) {
            tabView.setCompoundDrawablesWithIntrinsicBounds(iconResId, 0, 0, 0);
        }
//        tabView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.fgx_11);
        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(arg0);
        }

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if (mListener != null) {
            mListener.onPageScrolled(arg0, arg1, arg2);
        }

    }

    @Override
    public synchronized void onPageSelected(int arg0) {
            setCurrentItem(arg0);
            if (mListener != null) {
                mListener.onPageSelected(arg0);
            }
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (view == null) {
            return;
        }
        if (mViewPager == view && (mViewPager != null && mViewPager.getAdapter() == view.getAdapter())) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    private int ViewPagerCount = 0;

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        IconPagerAdapter iconAdapter = null;
        if (adapter instanceof IconPagerAdapter) {
            iconAdapter = (IconPagerAdapter) adapter;
        }
        final int count = adapter.getCount();
        ViewPagerCount = count;
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            int iconResId = 0;
            if (iconAdapter != null) {
                iconResId = iconAdapter.getIconResId(i);
            }
            addTab(i, title, iconResId);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override
    public synchronized void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
        mViewPager.setCurrentItem(item);

        int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            View child = mTabLayout.getChildAt(i);
            boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(item);
            }
        }
    }

    /**
     * 获得子item
     */
    public void invalidataAllItems() {
        int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            View child = mTabLayout.getChildAt(i);
            child.setSelected(false);
        }
    }

    public void validataItem() {
        mViewPager.setCurrentItem(mSelectedTabIndex);

        int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            View child = mTabLayout.getChildAt(i);
            boolean isSelected = (i == mSelectedTabIndex);
            child.setSelected(isSelected);
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }

    private class TabView extends TextView {
        private int mIndex;

        public TabView(Context context) {
            super(context, null, R.attr.vpiTabPageIndicatorStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // Re-measure if we went beyond our maximum size.
            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }

        public int getIndex() {
            return mIndex;
        }
    }


    private Runnable scrollerTask;
    private int intitPosition;
    private int newCheck = 100;
    private int childWidth = 0;

    public interface OnScrollStopListner {
        /**
         * scroll have stoped
         */
        void onScrollStoped();

        /**
         * scroll have stoped, and is at left edge
         */
        void onScrollToLeftEdge();

        /**
         * scroll have stoped, and is at right edge
         */
        void onScrollToRightEdge();

        /**
         * scroll have stoped, and is at middle
         */
        void onScrollToMiddle();
    }

    private OnScrollStopListner onScrollstopListner;


    public void setOnScrollStopListner(OnScrollStopListner listner) {
        onScrollstopListner = listner;
    }

    public void startScrollerTask() {
        intitPosition = getScrollX();
        postDelayed(scrollerTask, newCheck);
        checkTotalWidth();
    }

    private void checkTotalWidth() {
        if (childWidth > 0) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            childWidth += getChildAt(i).getWidth();
        }
    }

}
