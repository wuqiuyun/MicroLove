package com.mmt.microlove.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 用户动态
 * Created by wuqiuyun on 2017/5/16.
 */
public class UserDynamic extends BmobObject {

    private String content;//发布内容
    private List<String> photoList;//照片集合
    private String releaseTime;//发布时间
    private String address;//地址
    private String photoModel;//手机型号
    private String photoBrand;//系统定制商
    private String network;//网络
    private UserInfo author;//动态的发布者，这里体现的是一对一的关系，该帖子属于某个用户
    private Integer commentNum;
    private Integer PointPraiseNum;
    private List<UserReview> userReviews;//评论
    private List<UserPointPraise> userPointPraises;//点赞

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getPointPraiseNum() {
        return PointPraiseNum;
    }

    public void setPointPraiseNum(Integer pointPraiseNum) {
        PointPraiseNum = pointPraiseNum;
    }

    public List<UserReview> getUserComments() {
        return userReviews;
    }

    public void setUserComments(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }

    public List<UserPointPraise> getUserPointPraises() {
        return userPointPraises;
    }

    public void setUserPointPraises(List<UserPointPraise> userPointPraises) {
        this.userPointPraises = userPointPraises;
    }

    @Override
    public String toString() {
        return "UserDynamic{" +
                "content='" + content + '\'' +
                ", photoList=" + photoList +
                ", releaseTime='" + releaseTime + '\'' +
                ", address='" + address + '\'' +
                ", photoModel='" + photoModel + '\'' +
                ", photoBrand='" + photoBrand + '\'' +
                ", network='" + network + '\'' +
                ", author=" + author +
                ", commentNum=" + commentNum +
                ", PointPraiseNum=" + PointPraiseNum +
                ", userReviews=" + userReviews +
                ", userPointPraises=" + userPointPraises +
                '}';
    }
}
