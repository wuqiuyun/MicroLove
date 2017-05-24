package com.mmt.microlove.bean;

/**
 * 用户点赞
 * Created by wuqiuyun on 2017/5/16.
 */
public class UserPointPraise {

    private Boolean isPraise;
    private UserInfo author;//点赞的用户
    private String releaseTime;//发布时间
    private String photoModel;//手机型号
    private String photoBrand;//系统定制商
    private String network;//网络

    public Boolean getPraise() {
        return isPraise;
    }

    public void setPraise(Boolean praise) {
        isPraise = praise;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
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
        return "UserPointPraise{" +
                "isPraise=" + isPraise +
                ", author=" + author +
                ", releaseTime='" + releaseTime + '\'' +
                ", photoModel='" + photoModel + '\'' +
                ", photoBrand='" + photoBrand + '\'' +
                ", network='" + network + '\'' +
                '}';
    }
}
