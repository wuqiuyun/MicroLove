package com.mmt.microlove.application;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.mmt.microlove.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseApplication
 */
public class BaseApplication extends Application {

    private static BaseApplication context;
    private static List<Activity> activityList = new ArrayList<>();
    private static UserInfo mUser =null;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initData();
    }

    private void initData() {
        // 初始化百度地图
        SDKInitializer.initialize(this);

    }

    /**
     * 获取当前用户
     * @return 当前用户，若未登录则的返回null
     */
    public static UserInfo getmUser(){
        UserInfo userInfo = mUser.getCurrentUser(UserInfo.class);
        if (userInfo!=null){
            return userInfo;
        }
        return null;
    }

    public static BaseApplication getContext() {
        return context;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    /**
     * @Description 获取当前Activity（栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        return activityList.get(activityList.size() - 1);
    }

    /**
     * @Description 当前打开的Activity集合
     */
    public static List<Activity> getActivityList() {
        return activityList;
    }

    /**
     * 添加一个Activity到activityList集合,在onCreate()中调用
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (activityList != null) {
            activityList.add(activity);
        }
    }

    /**
     * 关闭所有的Activity,在onDestroy()中调用
     */
    public static void finishAllActivity() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                if (null != activity) {
                    activity.finish();
                }
            }
        }
        activityList.clear();
    }
}
