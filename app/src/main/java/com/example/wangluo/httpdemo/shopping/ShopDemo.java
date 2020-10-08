package com.example.wangluo.httpdemo.shopping;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.httpdemo.shopping
 * 文件名：ShopDemo
 * 创建者：liangxq
 * 创建时间：2020/8/4  16:12
 * 描述：TODO
 */
public class ShopDemo {

    /**
     * id : c
     * categoryName : 电脑办公
     * parentId : 0
     */

    private int id;
    private String categoryName;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
