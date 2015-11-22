package com.example.think.testview;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

/**
 * Created by Zane on 15/11/22.
 * 对 SLOP影响MOVE事件的 练习
 */
public class MyMoveTouchActivity extends AppCompatActivity implements View.OnTouchListener{

    public static final String TAG = "MyMoveTouchActivity";
    //系统SLOP常量
    private int mTouchSlop;
    //一个小球
    private Point mTouchPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymovetouch_activity_layout);

        findViewById(R.id.layout_haveslopdetect).setOnTouchListener(this);
        findViewById(R.id.layout_noslopdetect).setOnTouchListener(this);

        //获得系统SLOP常量
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        Log.i(TAG, "slop常量是＋ "+String.valueOf(mTouchSlop));

        mTouchPoint = new Point();

    }

    /**
     * 对不同的VIEW的ID区分TOUCH事件处理。
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mTouchPoint.set((int)event.getX(), (int)event.getY());
            return true;
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            switch (v.getId()){
                case R.id.layout_noslopdetect:
                    Toast.makeText(this, "top 你的手指移动了哦！", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "noslopdetect"+String.valueOf(event.getX())+" "+String.valueOf(event.getY()));
                    break;

                case R.id.layout_haveslopdetect:
                    if(Math.abs(event.getX() - mTouchPoint.x) > mTouchSlop &&
                            Math.abs(event.getY() - mTouchPoint.y) > mTouchSlop){
                        Toast.makeText(this, "bottom 你的手指移动了哦！", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "haveslopdetect"+String.valueOf(event.getX())+" "+String.valueOf(event.getY()));
                    }
                    break;
                default:
                    break;
            }
        }
        return false;
    }


}
