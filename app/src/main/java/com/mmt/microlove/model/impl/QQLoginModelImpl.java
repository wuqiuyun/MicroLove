package com.mmt.microlove.model.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.mmt.microlove.application.BaseApplication;
import com.mmt.microlove.bean.UserInfo;
import com.mmt.microlove.model.LoginModel;
import com.mmt.microlove.model.QQLoginModel;
import com.mmt.microlove.utils.AppConstants;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.ToastUtil;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by wuqiuyun on 2017/4/28.
 * QQ第三方登录
 */
public class QQLoginModelImpl implements QQLoginModel {
    private Tencent mTencent;
    private Activity mActivity = null;
    private Context mContext = BaseApplication.getContext();
    private String tag = Constants.TAG;
    private com.tencent.connect.UserInfo mInfo;
    private LoginModel loginImpl = new LoginModelImpl();

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            /** 获取用户信息成功 */
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;

                if (response.has("nickname")) {
//                    ToastUtil.longShow("用户信息：" + response.toString());
                }
            } else if (msg.what == 1) {
                ToastUtil.longShow(msg + "");
            } else if (msg.what == 2) {
                ToastUtil.longShow(msg + "");
            }
        }

    };

    @Override
    public void loginByQQ(Activity activity) {
        this.mActivity = activity;
        //初始化QQ
        mTencent = Tencent.createInstance(AppConstants.APP_ID, BaseApplication.getContext());
        IUiListener loginListener = new BaseUiListener() {
            @Override
            public void onError(UiError uiError) {
                LogUtil.e(tag, "UiError-->" + uiError.toString());
            }

            @Override
            protected void doComplete(JSONObject values) {
                initOpenidAndToken(values);
                updateUserInfo();

            }

        };
        /** 判断是否登陆过 */
        if (!mTencent.isSessionValid()) {
            mTencent.login(mActivity, "all", loginListener);
        }/** 登陆过注销之后在登录 */
        else {
            mTencent.logout(mContext);
            mTencent.login(mActivity, "all", loginListener);
        }
    }


    /**
     * QQ登录第一步：获取token和openid
     */
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            if (null == response) {
                ToastUtil.shortShow("登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                ToastUtil.shortShow("登录失败");
                return;
            }
            LogUtil.i(Constants.TAG, "QQ登录成功返回结果-" + response.toString());
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject response) {
            initOpenidAndToken(response);
        }

        @Override
        public void onError(UiError e) {
            ToastUtil.shortShow("onError: " + e.errorDetail);
//            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
            ToastUtil.shortShow("onCancel: ");
//            Util.dismissDialog();
//            if (isServerSideLogin) {
//                isServerSideLogin = false;
//            }
        }
    }

    /**
     * QQ登录第二步：存储token和openid
     */
    public void initOpenidAndToken(JSONObject jsonObject) {
        Gson gson = new Gson();
        try {
            String response = jsonObject.toString();
            UserInfo user = gson.fromJson(response, UserInfo.class);
            String accessToken = user.getAccess_token();
            String userId = user.getOpenid();
            String expiresIn = user.getExpires_in();
            String snsType = "qq";
            loginImpl.thirdLogin(snsType, accessToken, expiresIn, userId);

        } catch (Exception e) {

        }
    }

    /**
     * QQ登录第三步：获取用户信息
     */
    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onError(UiError e) {
                    Message msg = new Message();
                    msg.obj = "把手机时间改成获取网络时间";
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onCancel() {
                    Message msg = new Message();
                    msg.obj = "获取用户信息失败";
                    msg.what = 2;
                    mHandler.sendMessage(msg);
                }
            };
            mInfo = new com.tencent.connect.UserInfo(mContext, mTencent.getQQToken());
            mInfo.getUserInfo(listener);
        } else {

        }
    }
}
