package com.mmt.microlove.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmt.microlove.R;
import com.mmt.microlove.activity.BaseActivity;
import com.mmt.microlove.application.BaseApplication;
import com.mmt.microlove.bean.UserInfo;
import com.mmt.microlove.model.CurrentUserInfoModel;
import com.mmt.microlove.model.LoginModel;
import com.mmt.microlove.model.impl.CurrentUserInfoModelImpl;
import com.mmt.microlove.model.impl.LoginModelImpl;
import com.mmt.microlove.model.impl.QQLoginModelImpl;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.EncryptionUtil;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.StringUtil;
import com.mmt.microlove.utils.ToastUtil;
import com.mmt.microlove.utils.UIUtils;
import com.mmt.microlove.view.CircleImageView;
import com.mmt.microlove.view.DeletableEditText;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * @Description 用户登录
 * @Author wuqiuyun
 * @Time 2017/4/17
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static LoginActivity mActivity = null;
    private static final int REGISTRATION_REQUEST_CODE = 0X101;
    private String tag = Constants.TAG;
    private CircleImageView mIvUserIcon;
    private DeletableEditText mEdtName;
    private DeletableEditText mEdtPwd;
    private Button mBtnLogin;
    private TextView mTvNotLogin;
    private TextView mTvRegistration;
    private ImageView mIvQQ;
    private ImageView mIvWechat;
    private ImageView mIvSina;
    private QQLoginModelImpl qqLoginImpl = new QQLoginModelImpl();
    private CurrentUserInfoModel currentUserInfoModel = new CurrentUserInfoModelImpl();

    private LoginModel loginModel = new LoginModelImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mActivity = this;

        initView();
        initData();
        setListener();

    }

    private void initView() {
        mIvUserIcon = (CircleImageView) findViewById(R.id.iv_user_icon);
        mEdtName = (DeletableEditText) findViewById(R.id.edt_name);
        mEdtPwd = (DeletableEditText) findViewById(R.id.edt_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTvNotLogin = (TextView) findViewById(R.id.tv_not_login);
        mTvRegistration = (TextView) findViewById(R.id.tv_registration);
        mIvQQ = (ImageView) findViewById(R.id.iv_third_login_qq);
        mIvWechat = (ImageView) findViewById(R.id.iv_third_login_wechat);
        mIvSina = (ImageView) findViewById(R.id.iv_third_login_sina);

    }

    private void setListener() {
        mBtnLogin.setOnClickListener(mActivity);
        mTvNotLogin.setOnClickListener(mActivity);
        mTvRegistration.setOnClickListener(mActivity);
        mIvQQ.setOnClickListener(mActivity);
        mIvWechat.setOnClickListener(mActivity);
        mIvSina.setOnClickListener(mActivity);
    }

    private void initData() {
        initBmob();
    }

    public static void initBmob() {
        //初始化BmobSDK
        BmobConfig config = new BmobConfig.Builder(BaseApplication.getContext())
                //设置appkey
                .setApplicationId(Constants.BMOB_APPLICATION_ID)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String name = mEdtName.getText().toString();
                String pwd = mEdtPwd.getText().toString();
                if (StringUtil.isEmpty(name) && StringUtil.isEmpty(pwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.please_enter_account_information));
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.please_enter_login_name));
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.please_enter_password));
                    return;
                }
                bmobLogin(name, pwd);

                break;

            case R.id.iv_third_login_qq:
                qqLoginImpl.loginByQQ(mActivity);
                break;

            case R.id.iv_third_login_wechat:
//                String snsType = "weixin";
                break;

            case R.id.iv_third_login_sina:
//                snsType = "weibo";
                break;

            case R.id.tv_not_login:
                ToastUtil.shortShow("你再试试^_^");
                showCurrentUserInfo();
                break;

            case R.id.tv_registration:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, REGISTRATION_REQUEST_CODE);
                break;
        }
    }

    private void showCurrentUserInfo() {
        currentUserInfoModel.getCurrentUser("nickname","gender");
        LogUtil.i(tag,"获取nickname与gender");

    }

    /**
     * @param name
     * @param pwd
     * @Description Bmob账号登录
     */
    private void bmobLogin(String name, String pwd) {
        final com.mmt.microlove.bean.UserInfo userInfo = new com.mmt.microlove.bean.UserInfo();
        String md5Pwd = EncryptionUtil.md5(pwd);
        userInfo.loginByAccount(name, md5Pwd, new LogInListener<UserInfo>() {


            @Override
            public void done(UserInfo userinfo, BmobException e) {
                if (e == null && userinfo != null) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.login_successful));
                    startEnterActivity(MainActivity.class);//登录成功跳转到主页面
                    getCurrentUserInfo();//获取当前用户信息
                    finish();
                } else {
                    if (e.getErrorCode() == 101) {
                        ToastUtil.shortShow(UIUtils.getString(R.string.username_or_password_incorrect));
                    } else {
                        ToastUtil.shortShow(e.getMessage());
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REGISTRATION_REQUEST_CODE:
                String username = data.getStringExtra(Constants.username);
                String password = data.getStringExtra(Constants.password);
                mEdtName.setText(username);
                mEdtPwd.setText(password);
                break;
        }
    }

    public void getCurrentUserInfo() {
        //获取当前用户信息
        UserInfo user = BmobUser.getCurrentUser(UserInfo.class);
        LogUtil.i(tag, "获取当前用户信息user= " + user);
        if (user != null) {
            currentUserInfoModel.saveCurrentUser(user);
        }
    }
}
