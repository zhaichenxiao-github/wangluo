package com.example.wangluo.bean;

public class RecyBean {
    private String url;
    private String title;
    private String desc;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RecyBean(String url, String title, String desc) {
        this.url = url;
        this.title = title;
        this.desc = desc;
    }
}
