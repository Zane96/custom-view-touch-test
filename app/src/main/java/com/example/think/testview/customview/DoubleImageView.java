package com.example.think.testview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
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

        Drawable d = a.getDrawable(R.styleable.DoubleImageView_drawableLeft);
        if(d != null){
            //设置图片
            setLeftDrawable(d);
        }

        Drawable d2 = a.getDrawable(R.styleable.DoubleImageView_drawableRight);
        if (d2 != null){
            setRightDrawable(d2);
        }

        int spacing = a.getDimensionPixelSize(R.styleable.DoubleImageView_spacing2, 0);
        setSpacing(utils.px2dip(spacing));

        int color = a.getColor(R.styleable.DoubleImageView_textColor2, 0);
        mTextPaint.setColor(color);

        int textSize = a.getDimensionPixelSize(R.styleable.DoubleImageView_textSize2, 0);
        mTextPaint.setTextSize(utils.px2dip(textSize));

        CharSequence text = a.getText(R.styleable.DoubleImageView_text2);
        setText(text);

        a.recycle();
    }

    public void setRightDrawable(Drawable d){
        mRightDrawable = d;
        //调用最终控制view渲染的函数
        updateContentBounds();
        invalidate();
    }

    public void setLeftDrawable(Drawable d){
        mLeftDrawable = d;
        updateContentBounds();
        invalidate();
    }

    public void setText(CharSequence text){
        if(!TextUtils.equals(mText, text)) {
            mText = text;
            updateContentBounds();
            invalidate();
        }
    }

    public void setSpacing(int spacing){
        mSpacing = spacing;
        updateContentBounds();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获得最终的长宽
        int widthSize = View.resolveSize(getRequestWidth(), widthMeasureSpec);
        int heightSize = View.resolveSize(getRequestHeight(), heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);
    }

    //获得实际的长度
    public int getRequestWidth(){
        int widthSize;

        int leftWidth;
        if(mLeftDrawable == null){
            leftWidth = 0;
        } else {
            leftWidth = mLeftDrawable.getIntrinsicWidth();
        }

        int rightWidth;
        if(mRightDrawable == null){
            rightWidth = 0;
        } else {
            rightWidth = mRightDrawable.getIntrinsicWidth();
        }

        int textWidth;
        if(mTextLayout == null){
            textWidth = 0;
        } else {
            textWidth = mTextLayout.getWidth();
        }

        widthSize = (int)(leftWidth * 0.33f) + (int)(rightWidth) + (int)textWidth + mSpacing;

        return  widthSize;
    }

    public int getRequestHeight(){
        int heightSize;
        int textSize = 0;

        int leftHeight;
        if(mLeftDrawable == null){
            leftHeight = 0;
        } else {
            leftHeight = mLeftDrawable.getIntrinsicHeight();
        }

        int rightHeight;
        if(mRightDrawable == null){
            rightHeight = 0;
        } else {
            rightHeight = mRightDrawable.getIntrinsicHeight();
        }

        if(mTextLayout != null){
            textSize = mTextLayout.getHeight();
        }

        heightSize = Math.max(((int)(leftHeight * 0.33f) + (int)(rightHeight)), textSize);

        return heightSize;
    }

    //最后控制每个view的位置
    public void updateContentBounds(){
        int left;
        int top;

        if(mText == null){
            mText = "";
        } else {
            int textWidth = (int)mTextPaint.measureText(mText, 0, mText.length());
            mTextLayout = new StaticLayout(mText, mTextPaint, (int)(textWidth / 2), Layout.Alignment.ALIGN_CENTER, 1f, 0f, true);
        }

        left = (getWidth() - getRequestWidth()) / 2;
        top = (getHeight() - getRequestHeight()) / 2;

        if (mLeftDrawable != null){
            mLeftDrawable.setBounds(left, top, left+mLeftDrawable.getIntrinsicWidth()
                                           , top+mLeftDrawable.getIntrinsicWidth());

            //改变point
            left += (int)(mLeftDrawable.getIntrinsicWidth() * 0.33f);
            top += (int)(mLeftDrawable.getIntrinsicHeight() * 0.33f);
        }
        if (mRightDrawable != null){
            mRightDrawable.setBounds(left, top, left+mRightDrawable.getIntrinsicWidth()
                                            , top+mRightDrawable.getIntrinsicHeight());

            left += mRightDrawable.getIntrinsicWidth() + mSpacing;
        }

        if (mTextLayout != null){
            top = (getRequestHeight() - mTextLayout.getHeight()) / 2;
            mTextPoint.set(left, top+mRightDrawable.getIntrinsicHeight());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w != oldw || h != oldh){
            updateContentBounds();
        }
    }

    //渲染
    @Override
    protected void onDraw(Canvas canvas) {

        //在画布上画出第一个图片
        if (mLeftDrawable != null){
            mLeftDrawable.draw(canvas);
        }


        if (mRightDrawable != null){
            mRightDrawable.draw(canvas);
        }

        if (mTextLayout != null){
            canvas.save();
            canvas.translate(mTextPoint.x, mTextPoint.y);
            canvas.rotate(-90);

            mTextLayout.draw(canvas);
            canvas.restore();
        }
    }
}
