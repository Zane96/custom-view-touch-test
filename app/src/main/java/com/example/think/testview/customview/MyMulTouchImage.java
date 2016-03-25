package com.example.think.testview.customview;

import android.app.IntentService;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by think on 2015/9/19.
 */
public class MyMulTouchImage extends ImageView{

    private Matrix mMatrix;

    //缩放的手势侦听器,像一个工厂一样帮我们计算东西(数学引擎)
    private ScaleGestureDetector mScaleGestureDector;

    //旋转的角度
    private int mLastAngle;
    //旋转的中心点
    private int mPivotX, mPivotY;

    public MyMulTouchImage(Context context, AttributeSet attri) {
        super(context, attri);

        mScaleGestureDector = new ScaleGestureDetector(context, mScaleListener);
        //设置图片的缩放类型为矩阵类型
        setScaleType(ScaleType.MATRIX);
        mMatrix = new Matrix();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(w != oldw || h != oldh){
            //开始转化成新的view,新的宽减去旧的宽度除2就是横向移动的距离
            int transX = (w - getDrawable().getIntrinsicWidth()) / 2;
            int transY = (h - getDrawable().getIntrinsicHeight()) / 2;

            mMatrix.setTranslate(transX, transY);
            setImageMatrix(mMatrix);

            //更新中心点
            mPivotX = w / 2;
            mPivotY = h / 2;

        }
    }

    //这里负责把监听到的收缩手势最后得到的数据给矩阵，然后通过缩放数据改变矩阵
    private ScaleGestureDetector.SimpleOnScaleGestureListener mScaleListener
            = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scaleFactor = detector.getScaleFactor();

            mMatrix.postScale(scaleFactor, scaleFactor, mPivotX, mPivotY);
            setImageMatrix(mMatrix);

            return true;
        }
    };

    private boolean doRotationEvent(MotionEvent event) {
        //计算两个手指之间的角度
        float deltaX = event.getX(0) - event.getX(1);
        float deltaY = event.getY(0) - event.getY(1);
        double radians = Math.atan(deltaY / deltaX);
        //转化成角度
        int degrees = (int)(radians * 180 / Math.PI);

        //必须使用geiActionMask()，因为这里是多个手指触碰事件，而这个方法的手指触碰索引信息不是存储
        //在Movention里面，而是getActionIndex()来获得，而getAction()是吧索引信息存在Movention
        //里面，所以如果只传一个movention进来就不行了
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                //初始化角度
                mLastAngle = degrees;
                break;
            case MotionEvent.ACTION_MOVE:

                if ((degrees - mLastAngle) > 45) {
                    //Going CCW across the boundary
                    mMatrix.postRotate(-5, mPivotX, mPivotY);
                } else if ((degrees - mLastAngle) < -45) {
                    //Going CW across the boundary
                    mMatrix.postRotate(5, mPivotX, mPivotY);
                } else {
                    //Normal rotation, rotate the difference
                    mMatrix.postRotate(degrees - mLastAngle, mPivotX, mPivotY);
                }
                //Post the rotation to the image
                setImageMatrix(mMatrix);
                //Save the current angle
                mLastAngle = degrees;
                break;
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //一只手指的touch事件我们不感兴趣，但是仍要然会true，这样才能接受后面的多手指触碰时间
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            return true;
        }

        switch (event.getPointerCount()) {
            case 3:
                //把event交给手势处理器的onTouchEvent（）方法，判断是否有合适的回调函数来处理
                //用户的手势/
                return mScaleGestureDector.onTouchEvent(event);
            case 2:
                return doRotationEvent(event);
            default:
                return super.onTouchEvent(event);
        }
    }
}
