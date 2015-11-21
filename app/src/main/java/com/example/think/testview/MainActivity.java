package com.example.think.testview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mulTouch, smallPoint, imagebutton;

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

    }


}
