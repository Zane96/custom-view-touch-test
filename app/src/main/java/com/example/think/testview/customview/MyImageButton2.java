package com.example.think.testview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.think.testview.R;

/**
 * Created by Zane on 15/10/27.
 */
public class MyImageButton2 extends FrameLayout{

    ImageView mImageView;
    Button mButton;
    int resId;

    public MyImageButton2(Context context) {
        super(context);
    }

    public MyImageButton2(Context context, AttributeSet attrs) {
        super(context, attrs);
        //导入布局
        LayoutInflater.from(context).inflate(R.layout.myimagebutton_layout, this);

        mImageView = (ImageView) findViewById(R.id.imageview_button);
        mButton = (Button) findViewById(R.id.button_imagebutton);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyImageButton);

        try{
            resId = a.getResourceId(R.styleable.MyImageButton_Imagesrc, 0);
        }finally {
            a.recycle();
        }

        if(resId != 0){
            mImageView.setImageResource(resId);
        }
    }

    public void setImage(int resId){
        mImageView.setImageResource(resId);
    }

}
