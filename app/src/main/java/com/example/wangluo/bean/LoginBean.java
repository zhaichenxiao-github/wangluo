package com.example.wangluo.bean;

public class LoginBean {
    /**
     * id : 12
     * userName : 1
     * userMobile : 1
     * pushId : 1
     */

    private int id;
    private String userName;
    private String userMobile;
    private String pushId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
