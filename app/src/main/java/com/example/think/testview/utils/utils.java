package com.example.think.testview.utils;

import com.example.think.testview.app.App;

/**
 * Created by Zane on 15/12/10.
 */
public class utils {
    public static int dip2dx(float dpValue){
        float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale + 0.5F);
    }

    public static int px2dip(float pxValue){
        float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale + 0.5F);
    }
}
