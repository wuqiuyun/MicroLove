package com.mmt.microlove.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.mmt.microlove.R;
import com.mmt.microlove.application.BaseApplication;
import com.mmt.microlove.bean.UserDynamic;
import com.mmt.microlove.bean.UserInfo;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.DateUtil;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.StringUtil;
import com.mmt.microlove.utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TimeFragment extends Fragment implements View.OnClickListener {
    private static final int SAVE_SUCCESS = 201;
    private static final int SAVE_FAIL = 202;
    private TextView tvTitle;
    private TextView tvChat;
    private EditText etSay;
    private Button btnSend;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAVE_SUCCESS:
                    String content = msg.getData().getString("content", "容本宫打会盹儿...");
                    tvChat.append(content + "\n");
                    ToastUtil.shortShow("创建数据成功");
                    break;
                case SAVE_FAIL:
                    ToastUtil.shortShow("创建数据失败");
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }


    private void initView() {
        tvChat = (TextView) getActivity().findViewById(R.id.tv_chat);
        etSay = (EditText) getActivity().findViewById(R.id.et_say);
        btnSend = (Button) getActivity().findViewById(R.id.btn_nosend);
        tvTitle = (TextView) getActivity().findViewById(R.id.tv_title);

    }

    private void setListener() {
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_nosend:
                ToastUtil.shortShow("发送");
                sendDynamic();
                break;
        }
    }

    public void sendDynamic() {//发布动态
        UserDynamic userDynamic = new UserDynamic();
        //发布内容
        LogUtil.v(Constants.TAG, "sendDynamic-->执行");
        final String content = etSay.getText().toString().trim();
        if (!StringUtil.isEmpty(content)) {
            userDynamic.setContent(content);
            tvChat.append(content + "\n");
            //发布地址
            LocationFragment locationFragment = new LocationFragment();
            BDLocation location = locationFragment.getLocation();
            if (location != null) {
                String address = location.getAddrStr();
                userDynamic.setAddress(address);
            }
            //发布时间
            Long time = System.currentTimeMillis();
            String currentTime = DateUtil.getDay(time);
            userDynamic.setReleaseTime(currentTime);
            //发布者
            UserInfo userInfo = BaseApplication.getmUser();
            if (userInfo != null) {
                userDynamic.setAuthor(userInfo);
            }
            //添加数据
            userDynamic.save(new SaveListener<String>() {

                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {//创建数据成功
                        ToastUtil.shortShow("创建数据成功");
                    } else {//创建数据失败
                        ToastUtil.shortShow("创建数据失败");
                    }
                }
            });
        }

    }
}
