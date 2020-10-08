package com.example.wangluo.bean;

public class SubmitBean {
    /**
     * id : 39
     * shipUserName : 1804212634
     * shipUserMobile : 1804212634
     * shipAddress : 123
     * shipIsDefault : 0
     * userId : 212
     */

    private int id;
    private String shipUserName;
    private String shipUserMobile;
    private String shipAddress;
    private int shipIsDefault;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShipUserName() {
        return shipUserName;
    }

    public void setShipUserName(String shipUserName) {
        this.shipUserName = shipUserName;
    }

    public String getShipUserMobile() {
        return shipUserMobile;
    }

    public void setShipUserMobile(String shipUserMobile) {
        this.shipUserMobile = shipUserMobile;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public int getShipIsDefault() {
        return shipIsDefault;
    }

    public void setShipIsDefault(int shipIsDefault) {
        this.shipIsDefault = shipIsDefault;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
