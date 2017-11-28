package com.example.recyclerview_alladapter;

import android.support.annotation.IdRes;

/**
 * Created by Administrator on 2017/11/1.
 */

public class MyBean{
    private int imageResID;
    private String context;

    public MyBean( int imageResID, String context) {
        this.imageResID = imageResID;
        this.context = context;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
