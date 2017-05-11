package com.mmt.microlove.model.callback;

import com.mmt.microlove.bean.UserInfo;

/**
 * Created by wuqiuyun on 2017/5/2.
 */
public interface LoginCallback {
    void onSuccessLogin(UserInfo userInfo);
}
