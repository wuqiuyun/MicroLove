package com.mmt.microlove.model.impl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.mmt.microlove.application.BaseApplication;
import com.mmt.microlove.bean.UserInfo;
import com.mmt.microlove.model.CurrentUserInfoModel;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.ToastUtil;

/**
 * Created by wuqiuyun on 2017/5/11.
 */
public class CurrentUserInfoModelImpl implements CurrentUserInfoModel {
    private Context mContext = BaseApplication.getContext();
    private String tag = Constants.TAG;

    @Override
    public void saveCurrentUser(UserInfo userInfo) {
        LogUtil.i(tag, "传递当前用户信息user= " + userInfo);
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = mContext.getSharedPreferences("microlove",
                Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("nickname", "rabbit");
        editor.putString("gender", "female");
        //提交当前数据
        editor.commit();
        //使用toast信息提示框提示成功写入数据
        ToastUtil.shortShow("数据成功写入SharedPreferences！");
    }

    @Override
    public void getCurrentUser(String... keys) {

//同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("microlove",
                Activity.MODE_PRIVATE);
// 使用getString方法获得value，注意第2个参数是value的默认值

//        int length = keys.length;
//        String[] args = new String[length];
//        for (int i = 0; i < length; i++) {
//            args[i] = keys[i];
//            sharedPreferences.getString(args[i], "");
//        }

        for (String key : keys) {
            key = sharedPreferences.getString(key, "");
            LogUtil.i(tag,"key:"+key);
        }

//        String nickname = sharedPreferences.getString("nickname", "");
//        String gender = sharedPreferences.getString("gender", "");
//使用toast信息提示框显示信息

//        ToastUtil.shortShow("读取数据如下：" + "\nnickname：" + nickname + "\ngender：" + gender);
    }
}
