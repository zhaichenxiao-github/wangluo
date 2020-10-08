package com.example.wangluo.bean;

public class AddCartGoods {
    //商品数量
    int goodsCount;
    //商品描述
    String goodsDesc;
    //商品图标
    String goodsIcon;
    //商品id
    int goodsId;
    //商品价格
    int goodsPrice;
    //商品选配
    String goodsSku;

    public AddCartGoods(int goodsCount, String goodsDesc, String goodsIcon, int goodsId, int goodsPrice, String goodsSku) {
        this.goodsCount = goodsCount;
        this.goodsDesc = goodsDesc;
        this.goodsIcon = goodsIcon;
        this.goodsId = goodsId;
        this.goodsPrice = goodsPrice;
        this.goodsSku = goodsSku;
    }

    public AddCartGoods() {
    }
}
