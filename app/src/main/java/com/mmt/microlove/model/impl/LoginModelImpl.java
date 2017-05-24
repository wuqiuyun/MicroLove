package com.mmt.microlove.model.impl;

import android.content.Intent;

import com.mmt.microlove.activity.main.MainActivity;
import com.mmt.microlove.application.BaseApplication;
import com.mmt.microlove.model.LoginModel;
import com.mmt.microlove.utils.Constants;

import org.json.JSONObject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * 账号登录
 * Created by wuqiuyun on 2017/5/1.
 */
public class LoginModelImpl implements LoginModel{
    private BaseApplication mContext = BaseApplication.getContext();
    private String tag = Constants.TAG;

    @Override
    public void thirdLogin(String snsType, String accessToken, String expiresIn, String userId) {
        BmobUser.BmobThirdUserAuth authInfo = new BmobUser.BmobThirdUserAuth(snsType,accessToken, expiresIn,userId);
        BmobUser.loginWithAuthData(authInfo, new LogInListener<JSONObject>() {

            @Override
            public void done(JSONObject userAuth,BmobException e) {
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void bmobLogin(String name, String pwd) {

    }
}
