package com.example.think.testview;

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mulTouch, smallPoint, imagebutton, movetouch, interceptouch, touchDelegate,
    touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mulTouch = (Button) findViewById(R.id.multouch);
        mulTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyMulTouchRectActivity.class);
                startActivity(intent);
            }
        });
        smallPoint = (Button) findViewById(R.id.smallpoint);
        smallPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MySmallPointViewActivity.class);
                startActivity(intent);
            }
        });

        imagebutton = (Button) findViewById(R.id.imagebutton);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyImageButtonActivity.class);
                startActivity(intent);
            }
        });

        movetouch = (Button) findViewById(R.id.movetouch);
        movetouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyMoveTouchActivity.class);
                startActivity(intent);
            }
        });

        interceptouch = (Button) findViewById(R.id.interceptouch);
        interceptouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TouchInterceptActivity.class));
            }
        });

        touchDelegate = (Button) findViewById(R.id.touchdelegate);
        touchDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TouchDelegateActivity.class));
            }
        });

        touchListener = (Button) findViewById(R.id.touchlistener);
        touchListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TouchListenActivity.class));
            }
        });

    }


}
