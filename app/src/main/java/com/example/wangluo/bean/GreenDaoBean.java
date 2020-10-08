package com.example.wangluo.bean;

import androidx.transition.Slide;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDaoBean {
    private String goodsDefaultIcon;
    private String goodsDesc;
    private String goodsDefaultSku;
    private String goodsDefaultPrice;
    private Long goodsCount;




    @Generated(hash = 630624864)
    public GreenDaoBean(String goodsDefaultIcon, String goodsDesc, String goodsDefaultSku, String goodsDefaultPrice, Long goodsCount) {
        this.goodsDefaultIcon = goodsDefaultIcon;
        this.goodsDesc = goodsDesc;
        this.goodsDefaultSku = goodsDefaultSku;
        this.goodsDefaultPrice = goodsDefaultPrice;
        this.goodsCount = goodsCount;
    }

    @Generated(hash = 826843181)
    public GreenDaoBean() {
    }

    public String getGoodsDefaultIcon() {
        return goodsDefaultIcon;
    }

    public void setGoodsDefaultIcon(String goodsDefaultIcon) {
        this.goodsDefaultIcon = goodsDefaultIcon;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDefaultSku() {
        return goodsDefaultSku;
    }

    public void setGoodsDefaultSku(String goodsDefaultSku) {
        this.goodsDefaultSku = goodsDefaultSku;
    }

    public String getGoodsDefaultPrice() {
        return goodsDefaultPrice;
    }

    public void setGoodsDefaultPrice(String goodsDefaultPrice) {
        this.goodsDefaultPrice = goodsDefaultPrice;
    }

    public Long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Long goodsCount) {
        this.goodsCount = goodsCount;
    }


}
