package com.example.wangluo.bean;

public class RegisterIdBean {
    /**
     * mobile : 1
     * pwd : 123456
     * verifyCode : 123456
     */

    private String mobile;
    private String pwd;
    private String verifyCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
