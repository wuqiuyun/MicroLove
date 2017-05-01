package com.mmt.microlove.bean;

/**
 * Created by wuqiuyun on 2017/4/28.
 */
public class QQUserInfo {
    private String openid;//"C03AC4B6AD69BD293936D2ADF69A975E"
    private String access_token;//"9C2746838859239A95049B0E7AF87981"
    private String auth_time;//"1493372962616"
    private String ret;//"0",
    private String pay_token;//"DA7A847E74C5E56C27641FC92A1B3120"
    private String pf;//"desktop_m_qq-10000144-android-2002-"
    private String page_type;//"1"
    private String expires_in;//"7776000"
    private String pfkey;//"cdec6a0d7bc726e3e4d4865b3e3b4088"


    public QQUserInfo() {
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAuth_time() {
        return auth_time;
    }

    public void setAuth_time(String auth_time) {
        this.auth_time = auth_time;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getPay_token() {
        return pay_token;
    }

    public void setPay_token(String pay_token) {
        this.pay_token = pay_token;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPage_type() {
        return page_type;
    }

    public void setPage_type(String page_type) {
        this.page_type = page_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getPfkey() {
        return pfkey;
    }

    public void setPfkey(String pfkey) {
        this.pfkey = pfkey;
    }

    @Override
    public String toString() {
        return "QQUserInfo{" +
                "openid='" + openid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", auth_time='" + auth_time + '\'' +
                ", ret='" + ret + '\'' +
                ", pay_token='" + pay_token + '\'' +
                ", pf='" + pf + '\'' +
                ", page_type='" + page_type + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", pfkey='" + pfkey + '\'' +
                '}';
    }
}
