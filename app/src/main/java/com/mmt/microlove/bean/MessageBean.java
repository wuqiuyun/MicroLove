package com.mmt.microlove.bean;

/**
 * 聊天消息
 * Created by wuqiuyun on 2017/5/23.
 */
public class MessageBean {
    private String content;
    private int type;

    public MessageBean() {
    }

    public MessageBean(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
