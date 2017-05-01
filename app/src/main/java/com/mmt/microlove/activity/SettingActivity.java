package com.mmt.microlove.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mmt.microlove.R;
import com.mmt.microlove.utils.EncryptionUtil;
import com.mmt.microlove.utils.StringUtil;
import com.mmt.microlove.utils.ToastUtil;
import com.mmt.microlove.utils.UIUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.mmt.microlove.R.id.et_old_password;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private EditText etOldPwd;
    private EditText etNewPwd;
    private EditText etConfirmPwd;
    private Button btnCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        setListener();
        setAdapter();
    }

    private void initView() {
        etOldPwd = (EditText) findViewById(et_old_password);
        etNewPwd = (EditText) findViewById(R.id.et_new_password);
        etConfirmPwd = (EditText) findViewById(R.id.et_confirm_password);
        btnCommit = (Button) findViewById(R.id.btn_commit);
    }

    private void setListener() {
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                String newPwd = etNewPwd.getText().toString();
                String oldPwd = etOldPwd.getText().toString();
                String confirmPwd = etConfirmPwd.getText().toString();
                if (StringUtil.isEmpty(oldPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.old_password_not_empty));
                    return;
                }
                if (StringUtil.isEmpty(newPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.new_password_not_empty));
                    return;
                }
                if (oldPwd.equals(newPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.old_new_passwords_not_same));
                }
                if (StringUtil.isEmpty(confirmPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.confirm_password_not_empty));
                    return;
                }
                if (!newPwd.equals(confirmPwd)) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.passwords_not_match_please_check));
                    return;
                }
                String newPassword = EncryptionUtil.md5(newPwd);
                String oldPassword = EncryptionUtil.md5(oldPwd);
                updatePassword(oldPassword, newPassword); //修改当前用户登录密码
                finish();
        }

    }

    //修改当前用户登录密码
    private void updatePassword(String oldPwd, String newPwd) {
        BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.shortShow(UIUtils.getString(R.string.reset_password_success));
                } else {
                    ToastUtil.shortShow(e.getMessage());
                }
            }

        });
    }


    private void setAdapter() {

    }


}
