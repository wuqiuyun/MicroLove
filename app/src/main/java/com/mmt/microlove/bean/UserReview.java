package com.mmt.microlove.bean;

import cn.bmob.v3.BmobObject;

/**
 * 动态评论
 * Created by wuqiuyun on 2017/5/16.
 */
public class UserReview extends BmobObject {

    private String review;//评论内容
    private UserInfo author;//评论的用户
    private UserInfo replyAuthor;//回复评论的用户
    private String releaseTime;//发布时间
    private String photoModel;//手机型号
    private String photoBrand;//系统定制商
    private String network;//网络

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public UserInfo getReplyAuthor() {
        return replyAuthor;
    }

    public void setReplyAuthor(UserInfo replyAuthor) {
        this.replyAuthor = replyAuthor;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPhotoModel() {
        return photoModel;
    }

    public void setPhotoModel(String photoModel) {
        this.photoModel = photoModel;
    }

    public String getPhotoBrand() {
        return photoBrand;
    }

    public void setPhotoBrand(String photoBrand) {
        this.photoBrand = photoBrand;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String toString() {
        return "UserReview{" +
                "review='" + review + '\'' +
                ", author=" + author +
                ", replyAuthor=" + replyAuthor +
                ", releaseTime='" + releaseTime + '\'' +
                ", photoModel='" + photoModel + '\'' +
                ", photoBrand='" + photoBrand + '\'' +
                ", network='" + network + '\'' +
                '}';
    }
}
