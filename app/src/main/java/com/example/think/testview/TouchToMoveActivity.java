package com.example.think.testview;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.think.testview.app.App;

import java.lang.reflect.Field;

/**
 * Created by Zane on 16/4/6.
 */
public class TouchToMoveActivity extends AppCompatActivity{

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_to_move_layout);

        button = new Button(this);
        button.setText("点我啊傻嗨");
        //获得activity的windowmanager
        //坑
        windowManager = getWindowManager();
        layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.x = 100;
        layoutParams.y = 300;
        windowManager.addView(button, layoutParams);

        Toast.makeText(TouchToMoveActivity.this, getStatusBarHeight(App.getInstance())+"", Toast.LENGTH_SHORT).show();

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int)event.getRawX();
                //坑啊，getRawY()居然获得是
                int rawY = (int)event.getRawY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        layoutParams.x = rawX;
                        layoutParams.y = rawY - getStatusBarHeight(App.getInstance());
                        //更新
                        windowManager.updateViewLayout(button, layoutParams);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
    //获取手机状态栏高度
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
