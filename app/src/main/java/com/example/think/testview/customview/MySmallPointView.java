package com.example.think.testview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by think on 2015/9/19.
 */
public class MySmallPointView extends View{

    private Paint mPaint;

    private float x = 50;

    private float y = 50;

    private int r = 50;

    public MySmallPointView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, r, mPaint);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                x = event.getX();
                y = event.getY();
                r = 50;
                break;
            case MotionEvent.ACTION_UP:

                r = 0;

                break;
            case MotionEvent.ACTION_MOVE:

                x = event.getX();
                y = event.getY();
                r++;

                break;


        }

        invalidate();

        return true;
    }
}
