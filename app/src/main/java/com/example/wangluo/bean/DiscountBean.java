package com.example.wangluo.bean;

public class DiscountBean {
    private String image;
    private String price;
    private String original_price;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public DiscountBean(String image, String price, String original_price) {
        this.image = image;
        this.price = price;
        this.original_price = original_price;
    }
}
