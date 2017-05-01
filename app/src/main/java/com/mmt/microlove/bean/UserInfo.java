package com.mmt.microlove.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by wuqiuyun on 2017/4/17.
 */
public class UserInfo extends BmobUser {
    private Boolean sex;
    private String nick;
    private Integer age;

    public UserInfo() {
    }

    public UserInfo(Boolean sex, String nick, Integer age) {
        this.sex = sex;
        this.nick = nick;
        this.age = age;
    }

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "sex=" + sex +
                ", nick='" + nick + '\'' +
                ", age=" + age +
                '}';
    }
}
