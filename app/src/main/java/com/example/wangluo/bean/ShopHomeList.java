package com.example.wangluo.bean;

import java.util.List;

public class ShopHomeList {
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
     * goodsBanner : https://img11.360buyimg.com/n1/s450x450_jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg,https://img13.360buyimg.com/n1/s450x450_jfs/t2632/157/4193453761/92922/2adb5ebc/57ad88f0Nb286ec7a.jpg,https://img11.360buyimg.com/n1/s450x450_jfs/t2977/86/2412624329/68019/dbe32c1f/57ad8846N64ac3c79.jpg
     * goodsSku : [{"id":1,"goodsId":1,"goodsSkuTitle":"版本","goodsSkuContent":"1.6GHz i5处理器,2017年i5处理器升级版,i7处理器 定制版","skuTitle":"版本","skuContent":["1.6GHz i5处理器","2017年i5处理器升级版","i7处理器 定制版"]},{"id":2,"goodsId":1,"goodsSkuTitle":"配置","goodsSkuContent":"8GB内存/128GB SSD,8GB内存/256GB SSD","skuTitle":"配置","skuContent":["8GB内存/128GB SSD","8GB内存/256GB SSD"]}]
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
    private String goodsBanner;
    private List<GoodsSkuBean> goodsSku;

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

    public String getGoodsBanner() {
        return goodsBanner;
    }

    public void setGoodsBanner(String goodsBanner) {
        this.goodsBanner = goodsBanner;
    }

    public List<GoodsSkuBean> getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(List<GoodsSkuBean> goodsSku) {
        this.goodsSku = goodsSku;
    }

    public static class GoodsSkuBean {
         /**
         * id : 1
         * goodsId : 1
         * goodsSkuTitle : 版本
         * goodsSkuContent : 1.6GHz i5处理器,2017年i5处理器升级版,i7处理器 定制版
         * skuTitle : 版本
         * skuContent : ["1.6GHz i5处理器","2017年i5处理器升级版","i7处理器 定制版"]
         */

        private int id;
        private int goodsId;
        private String goodsSkuTitle;
        private String goodsSkuContent;
        private String skuTitle;
        private List<String> skuContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsSkuTitle() {
            return goodsSkuTitle;
        }

        public void setGoodsSkuTitle(String goodsSkuTitle) {
            this.goodsSkuTitle = goodsSkuTitle;
        }

        public String getGoodsSkuContent() {
            return goodsSkuContent;
        }

        public void setGoodsSkuContent(String goodsSkuContent) {
            this.goodsSkuContent = goodsSkuContent;
        }

        public String getSkuTitle() {
            return skuTitle;
        }

        public void setSkuTitle(String skuTitle) {
            this.skuTitle = skuTitle;
        }

        public List<String> getSkuContent() {
            return skuContent;
        }

        public void setSkuContent(List<String> skuContent) {
            this.skuContent = skuContent;
        }
    }
}
