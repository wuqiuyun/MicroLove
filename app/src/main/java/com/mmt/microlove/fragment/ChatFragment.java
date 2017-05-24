package com.mmt.microlove.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mmt.microlove.R;
import com.mmt.microlove.adapter.MessageAdapter;
import com.mmt.microlove.application.BaseApplication;
import com.mmt.microlove.bean.MessageBean;
import com.mmt.microlove.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private ListView lvChat;
    private Button btnSend;
    private EditText etContent;
    private List<MessageBean> data;
    private MessageAdapter messageAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        lvChat = (ListView) getView().findViewById(R.id.lv_chat);
        etContent = (EditText) getView().findViewById(R.id.et_content);
        btnSend = (Button) getView().findViewById(R.id.btn_send);
    }

    private void setListener() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                setAdapter();
                Log.i(Constants.TAG,data.toString()+"");
            }
        });
    }

    private void setData() {
        if (data == null) {
            data = new ArrayList<>();
        }

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                MessageBean msg = new MessageBean();
                msg.setContent(i + "收消息收消息收消息收消息收消息收消息收消息收消息收消息收消息收消息");
                msg.setType(0);
                data.add(msg);
            } else {
                MessageBean msg = new MessageBean();
                msg.setContent(i + "发消息发消息发消息发消息发消息发消息发消息发消息发消息发消息发消息");
                msg.setType(1);
                data.add(msg);
            }
        }
    }

    private void setAdapter() {
        if (messageAdapter == null) {
            messageAdapter = new MessageAdapter(BaseApplication.getContext(), data);
        }
        lvChat.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();
    }
}
