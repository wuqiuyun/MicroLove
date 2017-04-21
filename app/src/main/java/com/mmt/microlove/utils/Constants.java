package com.mmt.microlove.utils;

import com.baidu.mapapi.map.MyLocationConfiguration;

/**
 * @描述 常量工具类
 * @作者 wuqiuyun
 * @时间 2017-4-13
 */
public class Constants {
    /**
     * @Description TAG = "joey"
     */
    public static final String TAG = "joey";
    /**
     * @Description 用户名
     */
    public static final String username = "username";
    /**
     * @Description 密码
     */
    public static final String password = "password";
    /**
     * @Description 昵称
     */
    public static final String nickname = "nickname";
    /**
     * @Description 个性签名
     */
    public static final String signature = "signature";
    /**
     * @Description 兴趣爱好
     */
    public static final String hobby = "hobby";
    /**
     * @Description 性别
     */
    public static final String sex = "sex";
    /**
     * @Description 出生年月
     */
    public static final String birthday = "birthday";
    /**
     * @Description 头像
     */
    public static final String icon = "icon";



    /**
     * 定位状态：跟随态
     */
    public static final MyLocationConfiguration.LocationMode LOCATIONMODE_FOLLOWING = MyLocationConfiguration.LocationMode.FOLLOWING;
    /**
     * 定位状态：罗盘态
     */
    public static final MyLocationConfiguration.LocationMode LOCATIONMODE_COMPASS = MyLocationConfiguration.LocationMode.COMPASS;
    /**
     * 定位状态：普通态
     */
    public static final MyLocationConfiguration.LocationMode LOCATIONMODE_NORMAL = MyLocationConfiguration.LocationMode.NORMAL;

    /**
     * Bmob appkey
     */
    public static final String BMOB_APPLICATION_ID = "bfebaf52406389d89d09f91ffc8954e6";
}
