package com.mmt.microlove.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mmt.microlove.R;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.StringUtil;

/**
 * Created by wuqiuyun on 2017/5/15.
 */
public class ResetPhoneActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPhone;
    private EditText etVerifyCode;
    private Button btnCommit;
    private ResetPhoneActivity mActivity = null;
    private String tag = Constants.TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_phone);
        mActivity = this;

        initView();
        setListener();

    }

    private void initView() {
        btnCommit = (Button) findViewById(R.id.btn_commit);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etVerifyCode = (EditText) findViewById(R.id.et_verification_code);
    }

    private void setListener() {
        btnCommit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                Intent intent = new Intent();
                String phone = etPhone.getText().toString().trim();
                LogUtil.i(tag,"输入phone" + phone);
                if (!StringUtil.isEmpty(phone)) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.phone, phone);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    if (mActivity != null) {
                        mActivity.finish();
                    }
                }
                break;
        }
    }
}
