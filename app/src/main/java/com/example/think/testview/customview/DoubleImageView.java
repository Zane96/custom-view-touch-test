package com.example.think.testview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.think.testview.R;
import com.example.think.testview.utils.utils;

/**
 * Created by Zane on 15/12/10.
 * 将两张图片重叠放置，并且在组合图片的左边绘制文字
 */
public class DoubleImageView extends View {

    private TextPaint mTextPaint;
    //可以做到控制字符串的转行
    private StaticLayout mTextLayout;
    private Drawable mLeftDrawable, mRightDrawable;
    private Point mTextPoint;
    private int mSpacing;
    private CharSequence mText;

    public DoubleImageView(Context context) {
        this(context, null);
    }

    public DoubleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int def){
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPoint = new Point(0, 0);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DoubleImageView, 0, def);

        Drawable d = a.getDrawable(R.styleable.DoubleImageView_android_drawableLeft);
        if(d != null){
            //设置图片
            setLeftDrawable(d);
        }

        Drawable d2 = a.getDrawable(R.styleable.DoubleImageView_android_drawableRight);
        if (d2 != null){
            setRightDrawable(d);
        }

        int spacing = a.getDimensionPixelSize(R.styleable.DoubleImageView_android_spacing, 0);
        setSpacing(utils.px2dip(spacing));

        int color = a.getColor(R.styleable.DoubleImageView_android_textColor, 0);
        mTextPaint.setColor(color);

        int textSize = a.getDimensionPixelSize(R.styleable.DoubleImageView_android_textSize, 0);
        mTextPaint.setTextSize(utils.px2dip(textSize));

        CharSequence text = a.getText(R.styleable.DoubleImageView_android_text);
        setText(text);

        a.recycle();
    }
}
