package com.example.zane.testpath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Zane on 16/8/19.
 * Email: zanebot96@gmail.com
 */

public class CircleImageView extends ImageView {

    private Paint mPaint;
    private Rect rect;
    private Path mPath;

    private int width;
    private int height;
    private float length;

    private Bitmap mBitmap;

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context) {
        this(context, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        length = (float) Math.min(width, height) / 2;
        init();
        scaleBitmap();
        Log.i("testview", width+"");
    }

    private void scaleBitmap(){
        if (getDrawable() == null){
            return;
        }
        if (!(getDrawable() instanceof BitmapDrawable)){
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        float scaleX = length / bitmap.getWidth();
        float scaleY =  length / bitmap.getHeight();
        Log.i("testview", width + " width " + height + " height");
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        Log.i("testview", scaleX + " x " + scaleY + " y");
        Log.i("testview", bitmap.getWidth()+" bitmap width " + bitmap.getHeight() + " bitmap height");
        mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setColor(Color.BLUE);
        //mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);
        Log.i("testview", -(width/2)+" set");
        rect = new Rect(0, 0, width, height);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        mPath.addCircle(length, length, length, Path.Direction.CW);
        canvas.clipPath(mPath, Region.Op.INTERSECT);
        //mPath.reset();
        canvas.drawBitmap(mBitmap, rect, rect, mPaint);
        Log.i("testview", canvas.getWidth() + " canvans");
    }
}
