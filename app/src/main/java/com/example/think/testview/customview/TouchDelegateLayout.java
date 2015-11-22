package com.example.think.testview.customview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.TouchDelegate;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;

/**
 * Created by Zane on 15/11/22.
 * 触摸代理的练习
 * 一个layout里面有两个子VIE，一个是充满整个布局的一个长方形作为代理块，一个是位于中央
 * 的checkbox
 */
public class TouchDelegateLayout extends FrameLayout{

    private CheckBox mCheckBox;

    public TouchDelegateLayout(Context context) {
        super(context);
        init();
    }

    public TouchDelegateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchDelegateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mCheckBox = new CheckBox(getContext());
        mCheckBox.setText("触摸整个屏幕！");

        addView(mCheckBox, new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                                                               LayoutParams.WRAP_CONTENT, Gravity.CENTER));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Rect mRect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        TouchDelegate touchDelegate = new TouchDelegate(mRect, mCheckBox);
        setTouchDelegate(touchDelegate);
    }
}
