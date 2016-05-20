package com.malus.pushshow.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.malus.pushshow.R;


/**
 * 这是一个自定义标题栏，只提供了一个模板，可以根据需要进一步封装。
 * User: malus
 * Date: 14-12-29
 */
public class Topbar extends RelativeLayout {
    //分别定义左边按钮，右边按钮，中间标题及他们所需要的属性
    private RelativeLayout right;
    private RelativeLayout left;
    private ImageButton leftButton;
    private ImageButton rightButton;
    private TextView leftText;
    private TextView rightText;
    private int rightTextSize;
    private int leftTextSize;
    private int rightTextColor;
    private int leftTextColor;
    private String rightTextTitle;
    private String leftTextTitle;

    private ImageButton searchButton;
    private TextView textView;
    private Drawable leftButtonSrc;
    private int leftButtonWidth;
    private Drawable rightButtonSrc;
    private int rightButtonWidth;
    private Drawable searchButtonSrc;
    private int searchButtonWidth;
    private int searchButtonMarginRight;
    private String text;
    private int textSize;
    private int textColor;
    private int textBackground;
    private LayoutParams leftParams,rightParams,textParams,searchParams,rightLinearLayoutParams;
    private LinearLayout rightLinearLayout;
    //设置回调机制用来响应左右按钮点击事件
    onTopbarClickListener listener;

    /**
     * 构造函数，获取并设置参数。添加组件
     * @param context
     * @param attrs
     */
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        rightLinearLayout=new LinearLayout(context);
        //获取所有属性
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.Topbar);

        leftButtonSrc=ta.getDrawable(R.styleable.Topbar_leftButton_src);
        leftButtonWidth=(int)ta.getDimension(R.styleable.Topbar_leftButton_width,0);
        leftTextTitle=ta.getString(R.styleable.Topbar_leftButton_text);
        rightTextTitle=ta.getString(R.styleable.Topbar_rightButton_text);
        leftTextColor=ta.getInt(R.styleable.Topbar_leftText_color,0);
        rightTextColor=ta.getInt(R.styleable.Topbar_rightText_color,0);

        leftTextSize=(int)ta.getDimension(R.styleable.Topbar_leftText_size,10);
        rightTextSize=(int)ta.getDimension(R.styleable.Topbar_rightText_size,10);
        leftTextSize=px2dip(context,leftTextSize);
        rightTextSize=px2dip(context,rightTextSize);


        rightButtonSrc =ta.getDrawable(R.styleable.Topbar_rightButton_src);
        rightButtonWidth=(int)ta.getDimension(R.styleable.Topbar_rightButton_width,0);

        searchButtonSrc=ta.getDrawable(R.styleable.Topbar_searchButton_src);
        searchButtonWidth=(int)ta.getDimension(R.styleable.Topbar_searchButton_width,0);
        searchButtonMarginRight=(int)ta.getDimension(R.styleable.Topbar_searchButton_marginRight,0);

        text=ta.getString(R.styleable.Topbar_text);
        textSize=(int)ta.getDimension(R.styleable.Topbar_text_size,12);
        textSize=px2dip(context,textSize);
        textColor=ta.getColor(R.styleable.Topbar_text_color, 0);
        textBackground=ta.getColor(R.styleable.Topbar_text_background,0);
        //关闭获取属性流
        ta.recycle();
        //创建组件
        leftButton=new ImageButton(context);
        rightButton=new ImageButton(context);
        searchButton=new ImageButton(context);
        leftText=new TextView(context);
        rightText=new TextView(context);
        leftText.setText(leftTextTitle);
        rightText.setText(rightTextTitle);
        leftText.setTextColor(leftTextColor);
        rightText.setTextColor(rightTextColor);
        leftText.setTextSize(leftTextSize);
        leftText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        leftText.setGravity(Gravity.CENTER);
        rightText.setGravity(Gravity.CENTER);
        rightText.setTextSize(rightTextSize);

        right=new RelativeLayout(context);
        left=new RelativeLayout(context);
        textView=new TextView(context);
        //设置组件自定义属性
        leftButton.setBackgroundResource(android.R.color.transparent);
        leftButton.setImageDrawable(leftButtonSrc);
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onLeftButtonClick();
                }
            }
        });
        rightButton.setBackgroundResource(android.R.color.transparent);
        rightButton.setImageDrawable(rightButtonSrc);
        rightLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onRightButtonClick();
                }
            }
        });
        searchButton.setBackgroundResource(android.R.color.transparent);
        searchButton.setImageDrawable(searchButtonSrc);
        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSearchButtonClick();
                }
            }
        });
        rightButton.setClickable(false);
        leftButton.setClickable(false);
        textView.setText(text);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        textView.setBackgroundColor(textBackground);
        //设置组件位置及大小
        textView.setGravity(Gravity.CENTER);
        if(leftButtonWidth==0){
            leftParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        }else{
            leftParams=new LayoutParams(leftButtonWidth,LayoutParams.MATCH_PARENT);
        }
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        leftParams.setMargins(0,0,0,0);

        if (rightButtonWidth==0){
            rightParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        }else{
            rightParams=new LayoutParams(rightButtonWidth,LayoutParams.MATCH_PARENT);
        }
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);

        if (searchButtonWidth==0){
            searchParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        }else{
            searchParams=new LayoutParams(rightButtonWidth,LayoutParams.MATCH_PARENT);
        }
        //搜索按钮的对右边距
        searchParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        searchParams.addRule(RelativeLayout.LEFT_OF,rightButton.getId());
        searchParams.setMargins(0,0,searchButtonMarginRight,0);

        rightLinearLayoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightLinearLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        rightLinearLayoutParams.setMargins(0, 0, 0, 0);
        textParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        textParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        LayoutParams params =
                new LayoutParams(
                       LayoutParams.WRAP_CONTENT,
                        LayoutParams.MATCH_PARENT);
        right.setGravity(Gravity.CENTER);
        right.setLayoutParams(params);
        right.addView(rightButton);
        right.addView(rightText);
        rightLinearLayout.addView(searchButton);
        rightLinearLayout.addView(right);
        //添加组件
        setGravity(Gravity.CENTER);
        left.setGravity(Gravity.RIGHT);
        left.addView(leftButton,leftParams);
        left.addView(leftText);
        addView(left,leftParams);
        addView(rightLinearLayout, rightLinearLayoutParams);
        addView(textView, textParams);
    }
    //设置左右点击事件
    public void setOnTopbarClickListener(onTopbarClickListener listener){
        this.listener=listener;
    }



    //左右点击事件回调接口
    public interface onTopbarClickListener{
        public void onLeftButtonClick();
        public void onRightButtonClick();
        public void onSearchButtonClick();
    }

    /**
     * 设置topbar的标题
     * @param title
     */
    public void setTitle(String title){
        textView.setText(title);
    }
    public void setRightTitle(String title){
    	rightText.setText(title);
    }
    
    public void setRightImage(String title){
    	rightText.setText(title);
    }
    
    /**
     * 设置右边按钮的标题
     * @param title
     */
    public void setRightTextTitle(String title){
        rightText.setText(title);
    }

    /**
     * 设置左边按钮的标题
     * @param title
     */
    public void setLeftTextTitle(String title){
        leftText.setText(title);
    }

    /**
     * 设置topbar的背景颜色该背景<br/>
     * 该背景仅限于topbar中标题的背景
     * @param resid 资源
     */
    public void setTopbarBackground(int resid){
        textView.setBackgroundResource(resid);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
