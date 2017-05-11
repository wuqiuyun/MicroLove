package com.mmt.microlove.model;

/**
 * Created by wuqiuyun on 2017/5/1.
 */
public interface LoginModel {
    /**
     * 第三方账号登录
     * @param snsType:只能是三种取值中的一种：weibo、qq、weixin
     * @param accessToken：接口调用凭证
     * @param expiresIn：access_token的有效时间
     * @param userId:用户身份的唯一标识，对应微博授权信息中的uid,对应qq和微信授权信息中的openid
     */
    void thirdLogin(String snsType,String accessToken,String expiresIn,String userId);

    /**
     * bmob 账号登录
     * @param name 用户名
     * @param pwd 密码
     */
    void bmobLogin(String name, String pwd);
}
