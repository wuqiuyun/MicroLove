package com.mmt.microlove.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmt.microlove.R;
import com.mmt.microlove.bean.MessageBean;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.LogUtil;

import java.util.List;

/**
 * 显示聊天内容的Adapter
 * Created by wuqiuyun on 2017/5/23.
 */
public class MessageAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MessageBean> messages;
    private Context mContext;

    //8种Item的类型
    private static final int TYPECOUNT = 8;
    //文本
    private final int TYPE_RECEIVER_TEXT = 0;
    private final int TYPE_SEND_TEXT = 1;
    //图片
    private final int TYPE_SEND_IMAGE = 2;
    private final int TYPE_RECEIVER_IMAGE = 3;
    //位置
    private final int TYPE_SEND_LOCATION = 4;
    private final int TYPE_RECEIVER_LOCATION = 5;
    //语音
    private final int TYPE_SEND_VOICE = 6;
    private final int TYPE_RECEIVER_VOICE = 7;


    public MessageAdapter(Context context, List<MessageBean> e) {
        this.inflater = inflater.from(context);
        this.messages = e;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public MessageBean getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReceiveTextViewHolder receiveTextHolder = null;
        SendTextViewHolder sendTextHolder = null;
        int type = getItemViewType(position);
        MessageBean msg = getItem(position);
        String content = msg.getContent().toString().trim();
        LogUtil.i(Constants.TAG, "getcontent" + content);

        if (convertView == null) {
            switch (type) {
                case TYPE_RECEIVER_TEXT:
                    receiveTextHolder = new ReceiveTextViewHolder();
                    convertView = inflater.inflate(R.layout.item_chat_receive_text, null);
                    receiveTextHolder.ivReceiveAvatar = (ImageView) convertView.findViewById(R.id.iv_receive_avatar);
                    receiveTextHolder.tvReceiveMsg = (TextView) convertView.findViewById(R.id.tv_msg_receive);
                    receiveTextHolder.tvReceiveTime = (TextView) convertView.findViewById(R.id.tv_receive_time);
                    convertView.setTag(receiveTextHolder);
                    break;
                case TYPE_SEND_TEXT:
                    convertView = inflater.inflate(R.layout.item_chat_send_text, null);
                    sendTextHolder = new SendTextViewHolder();
                    sendTextHolder.ivSendAvatar = (ImageView) convertView.findViewById(R.id.iv_send_avatar);
                    sendTextHolder.tvSendMsg = (TextView) convertView.findViewById(R.id.tv_msg_send);
                    sendTextHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_send_time);
                    convertView.setTag(sendTextHolder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_RECEIVER_TEXT:
                    receiveTextHolder = (ReceiveTextViewHolder) convertView.getTag();
                    break;
                case TYPE_SEND_TEXT:
                    sendTextHolder = (SendTextViewHolder) convertView.getTag();
                    break;
            }
        }
        if (!content.equals(null)) {
            switch (type) {
                case TYPE_RECEIVER_TEXT:
                    receiveTextHolder.tvReceiveMsg.setText(content);
                    receiveTextHolder.tvReceiveTime.setText("2013/7/14 13:14");
                    receiveTextHolder.ivReceiveAvatar.setImageResource(R.mipmap.icon_default);
                    break;
                case TYPE_SEND_TEXT:
                    sendTextHolder.tvSendMsg.setText(content);
                    sendTextHolder.tvSendTime.setText("2017/7/14 13:14");
                    sendTextHolder.ivSendAvatar.setImageResource(R.mipmap.ic_launcher);
                    break;
            }
        }

        return convertView;
    }

    //返回一共有几种不同类型的布局
    @Override
    public int getViewTypeCount() {
        return TYPECOUNT;
    }

    /* 通过逻辑判断，返回当前的item应该加载哪个布局，这个返回值是一个int值，
     *我们在getView方法里就根据这个int值来判断加载哪个布局，
     *并且这个数不能大于getViewTypeCount返回的数*/
    @Override
    public int getItemViewType(int position) {
        MessageBean message = getItem(position);
        int type = message.getType();
        switch (type) {
            case TYPE_RECEIVER_TEXT:
                return TYPE_RECEIVER_TEXT;
            case TYPE_SEND_TEXT:
                return TYPE_SEND_TEXT;
            case TYPE_RECEIVER_IMAGE:
                return TYPE_RECEIVER_IMAGE;
            case TYPE_SEND_IMAGE:
                return TYPE_SEND_IMAGE;
        }
        return 0;
    }

    class ReceiveTextViewHolder {
        ImageView ivReceiveAvatar;
        TextView tvReceiveTime, tvReceiveMsg;
    }

    class SendTextViewHolder {
        ImageView ivSendAvatar;
        TextView tvSendTime, tvSendMsg;
    }
}
