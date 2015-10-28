package com.example.think.testview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.think.testview.R;

/**
 * Created by Zane on 15/10/27.
 */
public class MyImageButton extends Button{

    int srcId;

    public MyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public MyImageButton(Context context) {
        super(context);
    }

    public MyImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    void initAttrs(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyImageButton);
        try{
            srcId = a.getResourceId(R.styleable.MyImageButton_src, 0);
        }finally {
            a.recycle();
        }
    }

    void setSrc(int srcId){
        this.srcId = srcId;
        invalidate();
        requestLayout();
    }
}
