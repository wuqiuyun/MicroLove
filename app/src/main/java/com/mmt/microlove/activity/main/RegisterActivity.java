package com.mmt.microlove.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mmt.microlove.R;
import com.mmt.microlove.activity.BaseActivity;
import com.mmt.microlove.bean.UserInfo;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.EncryptionUtil;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.StringUtil;
import com.mmt.microlove.utils.ToastUtil;
import com.mmt.microlove.utils.UIUtils;
import com.mmt.microlove.view.DeletableEditText;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private DeletableEditText mEdtName;
    private DeletableEditText mEdtPwd;
    private DeletableEditText mEdtConfirmPwd;
    private Button mBtnRegistration;
    private LinearLayout mLlToLogin;

    private RegisterActivity mActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        setContentView(R.layout.activity_register);
        intiView();
        setListener();
    }

    private void intiView() {
        mEdtName = (DeletableEditText) findViewById(R.id.edt_name);
        mEdtPwd = (DeletableEditText) findViewById(R.id.edt_pwd);
        mEdtConfirmPwd = (DeletableEditText) findViewById(R.id.edt_confirm_pwd);
        mBtnRegistration = (Button) findViewById(R.id.btn_registration);
        mLlToLogin = (LinearLayout) findViewById(R.id.ll_to_login);
    }

    private void setListener() {
        mBtnRegistration.setOnClickListener(mActivity);
        mLlToLogin.setOnClickListener(mActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registration:
                String name = mEdtName.getText().toString().trim();
                String pwd = mEdtPwd.getText().toString().trim();
                String confirmPwd = mEdtConfirmPwd.getText().toString().trim();
                if (StringUtil.isEmpty(name) && StringUtil.isEmpty(pwd) && StringUtil.isEmpty(confirmPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.please_enter_account_information));
                    return;
                }
                if (StringUtil.isEmpty(name)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.username_not_empty));
                    return;
                }
                if (StringUtil.isEmpty(pwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.password_not_empty));
                    return;
                }
                if (StringUtil.isEmpty(confirmPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.confirm_password_not_empty));
                    return;
                }
                if (!pwd.equals(confirmPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.passwords_not_match_please_check));
                    return;
                }
                register(name, pwd);
                break;

            case R.id.ll_to_login:
                if (mActivity != null) {
                    mActivity.finish();
                }
                break;
        }
    }

    /**
     * 注册
     *
     * @param name
     * @param pwd
     */
    private void register(final String name, final String pwd) {
        UserInfo user = new UserInfo();
        user.setUsername(name);
        String md5Pwd = EncryptionUtil.md5(pwd);
        user.setPassword(md5Pwd);

        user.signUp(new SaveListener<UserInfo>() {

            @Override
            public void done(UserInfo user, BmobException e) {
                if (e == null) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.registration_success));
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.username, name);
                    bundle.putString(Constants.password, pwd);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    if (mActivity != null) {
                        mActivity.finish();
                    }
                } else {
                    LogUtil.e(Constants.TAG, e.toString());
                }
            }
        });
    }

}
