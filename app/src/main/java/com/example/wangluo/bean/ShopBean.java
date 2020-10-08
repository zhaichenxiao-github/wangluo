package com.example.wangluo.bean;

public class ShopBean {
    /**
     * id : 1
     * categoryId : 14
     * goodsDesc : Apple MacBook Air 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存 MMGF2CH/A)
     * goodsDefaultIcon : https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg
     * goodsDefaultPrice : 100
     * goodsDetailOne : https://img20.360buyimg.com/vc/jfs/t3034/151/748569500/226790/d6cd86a2/57b15612N81dc489d.jpg
     * goodsDetailTwo : https://img20.360buyimg.com/vc/jfs/t2683/60/4222930118/169462/233c7678/57b15616N1e285f09.jpg
     * goodsSalesCount : 5000
     * goodsStockCount : 10000
     * goodsCode : 10000000001
     * goodsDefaultSku : 1.6GHz i5处理器,GB内存/128GB SSD,1件
     * maxPage : 2
     */

    private int id;
    private int categoryId;
    private String goodsDesc;
    private String goodsDefaultIcon;
    private String goodsDefaultPrice;
    private String goodsDetailOne;
    private String goodsDetailTwo;
    private int goodsSalesCount;
    private int goodsStockCount;
    private String goodsCode;
    private String goodsDefaultSku;
    private int maxPage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDefaultIcon() {
        return goodsDefaultIcon;
    }

    public void setGoodsDefaultIcon(String goodsDefaultIcon) {
        this.goodsDefaultIcon = goodsDefaultIcon;
    }

    public String getGoodsDefaultPrice() {
        return goodsDefaultPrice;
    }

    public void setGoodsDefaultPrice(String goodsDefaultPrice) {
        this.goodsDefaultPrice = goodsDefaultPrice;
    }

    public String getGoodsDetailOne() {
        return goodsDetailOne;
    }

    public void setGoodsDetailOne(String goodsDetailOne) {
        this.goodsDetailOne = goodsDetailOne;
    }

    public String getGoodsDetailTwo() {
        return goodsDetailTwo;
    }

    public void setGoodsDetailTwo(String goodsDetailTwo) {
        this.goodsDetailTwo = goodsDetailTwo;
    }

    public int getGoodsSalesCount() {
        return goodsSalesCount;
    }

    public void setGoodsSalesCount(int goodsSalesCount) {
        this.goodsSalesCount = goodsSalesCount;
    }

    public int getGoodsStockCount() {
        return goodsStockCount;
    }

    public void setGoodsStockCount(int goodsStockCount) {
        this.goodsStockCount = goodsStockCount;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsDefaultSku() {
        return goodsDefaultSku;
    }

    public void setGoodsDefaultSku(String goodsDefaultSku) {
        this.goodsDefaultSku = goodsDefaultSku;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
