package com.example.wangluo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class CateGoryListBean implements MultiItemEntity {
    /**
     * id : 14
     * categoryName : Apple
     * categoryIcon : https://img13.360buyimg.com/n7/jfs/t2590/338/4222589387/237425/25e40fb/57b12ac6N61374a75.jpg
     * parentId : c
     */

    public static final int TEXT = 1;
    public static final int IMG = 2;
    private int itemType;
    private int id;
    private String categoryName;
    private String categoryIcon;
    private int parentId;

    public CateGoryListBean(int itemType, int id, String categoryName, String categoryIcon, int parentId) {
        this.itemType = itemType;
        this.id = id;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.parentId = parentId;
    }

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

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
