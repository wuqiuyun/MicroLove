package com.mmt.microlove.model;

import com.mmt.microlove.bean.UserInfo;

/**
 * 存取当前用户信息
 * Created by wuqiuyun on 2017/5/11.
 */
public interface CurrentUserInfoModel {
    void saveCurrentUser(UserInfo userInfo);

    void getCurrentUser(String... key);

}
