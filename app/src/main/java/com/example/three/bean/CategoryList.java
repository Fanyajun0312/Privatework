package com.example.three.bean;

import java.io.Serializable;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.bean
 * 文件名：CategoryList
 * 创建者：liangxq
 * 创建时间：2020/8/10  14:21
 * 描述：TODO
 */
public class CategoryList implements Serializable {
    /**
     * id : 14
     * categoryName : Apple
     * categoryIcon : https://img13.360buyimg.com/n7/jfs/t2590/338/4222589387/237425/25e40fb/57b12ac6N61374a75.jpg
     * parentId : 1
     */

    private int id;
    private String categoryName;
    private String categoryIcon;
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
    public String toString() {
        return "CategoryList{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryIcon='" + categoryIcon + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
