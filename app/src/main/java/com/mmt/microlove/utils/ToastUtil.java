package com.mmt.microlove.utils;

import android.widget.Toast;

import com.mmt.microlove.application.BaseApplication;

/**
 * Created by wuqiuyun on 2017/4/17.
 */
public class ToastUtil {
    public static void shortShow(String msg){
        Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void longShow(String msg){
        Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_LONG).show();
    }
}
