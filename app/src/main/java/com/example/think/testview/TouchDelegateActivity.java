package com.example.think.testview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.think.testview.customview.TouchDelegateLayout;

/**
 * Created by Zane on 15/11/22.
 * 触摸代理的练习
 * 一个layout里面有两个子VIE，一个是充满整个布局的一个长方形作为代理块，一个是位于中央
 * 的checkbox
 */
public class TouchDelegateActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TouchDelegateLayout touchDelegateLayout = new TouchDelegateLayout(this);
        setContentView(touchDelegateLayout);
    }
}
