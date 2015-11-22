package com.example.think.testview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Zane on 15/11/22.
 * 对于利用onTouchListener的优先级高于onTouchEvent的原理做的练习
 * 如果一个view在onTouch()方法里面返回了True，那么他的onTouchevent()方法将不会被
 * 调用。比如Button，他的CLICK方法是通过onTouchEvent()方法实现的，如果它实现了onTouchListener
 * 接口，并且返回true，那么这个点击事件将无法响应。
 */
public class TouchListenActivity extends AppCompatActivity implements View.OnTouchListener{

    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_listener);

        mCheckBox = (CheckBox) findViewById(R.id.checkbox_lock);

        findViewById(R.id.selection_first).setOnTouchListener(this);
        findViewById(R.id.selection_second).setOnTouchListener(this);
        findViewById(R.id.selection_third).setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return mCheckBox.isChecked();

    }
}
