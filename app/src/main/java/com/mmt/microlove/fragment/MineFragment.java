package com.mmt.microlove.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmt.microlove.R;
import com.mmt.microlove.activity.LoginActivity;
import com.mmt.microlove.activity.SettingActivity;
import com.mmt.microlove.application.BaseApplication;
import com.mmt.microlove.bean.UserInfo;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.ToastUtil;
import com.mmt.microlove.utils.UIUtils;

import cn.bmob.v3.BmobUser;

public class MineFragment extends Fragment implements View.OnClickListener {
    private TextView tvExit;
    private TextView tvNickname;
    private TextView tvSetting;

    private UserInfo userInfo = BaseApplication.getmUser();
    private String tag = Constants.TAG;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
        updateUserInfo();
    }

    private void updateUserInfo() {
        //BmobUser中的特定属性
        String username = (String) BmobUser.getObjectByKey("username");
        //MyUser中的扩展属性
        String nickname = (String) BmobUser.getObjectByKey("nickname");
        LogUtil.i(tag, "我的username:" + username + "\n我的nickname:" + nickname);

    }

    private void initView() {
        tvExit = (TextView) getView().findViewById(R.id.tv_exit);
        tvNickname = (TextView) getView().findViewById(R.id.tv_nickname);
        tvSetting = (TextView) getView().findViewById(R.id.tv_setting);
    }

    private void setListener() {
        tvExit.setOnClickListener(this);
        tvNickname.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_exit:
                ToastUtil.shortShow(UIUtils.getString(R.string.exit));
                BmobUser.logOut();   //清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser为null
                break;

            case R.id.tv_nickname:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
