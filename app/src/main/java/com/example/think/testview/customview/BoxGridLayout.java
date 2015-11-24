package com.example.think.testview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.think.testview.R;

/**
 * Created by Zane on 15/11/24.
 * viewgroup,可以实现gridview的功能，并且可以一个gridview里面嵌套gridview
 */
public class BoxGridLayout extends ViewGroup{

    private Paint mSplitPaint;
    private int mChildNum;
    private int mColumnsNum;

    public BoxGridLayout(Context context) {
        this(context, null);
    }

    public BoxGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoxGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BoxGridLayout, 0, defStyleAttr);
        try {
            int mSplitWidth = array.getDimensionPixelSize(R.styleable.BoxGridLayout_splitWidth, 0);
            int mSplitColor = array.getColor(R.styleable.BoxGridLayout_splitColor, Color.WHITE);
            mColumnsNum = array.getInteger(R.styleable.BoxGridLayout_columnsNum, 3);
            mChildNum = mColumnsNum * mColumnsNum;

            //初始化我的笔
            mSplitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mSplitPaint.setStyle(Paint.Style.STROKE);
            mSplitPaint.setColor(mSplitColor);
            mSplitPaint.setStrokeWidth(mSplitWidth);
        }finally {
            array.recycle();
        }
    }

    //确定自己的大小并且安排孩子的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = getDefaultSize(0, widthMeasureSpec);
        int heightSize = getDefaultSize(0, heightMeasureSpec);

        //选最小的作为自己的最终边长（正方形）,并确定下来
        int finalSize = Math.min(widthSize, heightSize);
        setMeasuredDimension(finalSize, finalSize);

        //给孩子们设置建议的大小
        //Toast.makeText(getContext(), String.valueOf(mColumnsNum), Toast.LENGTH_LONG).show();
        int childSize = finalSize / mColumnsNum;
        //组装Spec,模式设为 规定尺寸
        int childSpec = MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY);
        //这个方法不用再去循环找出子view，看源码就知道了
        measureChildren(childSpec, childSpec);
    }

    //确定孩子的位置，参数分别是左上点的坐标top, left,和宽度和高度
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top;

        for (int i = 0; i < getChildCount(); i++){
            View child = getChildAt(i);
            //确定孩子的坐标
            left = (i % mColumnsNum) * child.getMeasuredWidth();
            top = (i / mColumnsNum) * child.getMeasuredHeight();
            //确定位置
            child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
        }
    }

    //分发分割线的绘画事件
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        //纵线
        for(int i = 0; i < getWidth(); i += (getWidth() / mColumnsNum)){
            canvas.drawLine(i, 0, i, getHeight(), mSplitPaint);
        }
        //横线
        for(int i = 0; i < getHeight(); i += (getHeight() / mColumnsNum)){
            canvas.drawLine(0, i, getWidth(), i, mSplitPaint);
        }

    }
}
