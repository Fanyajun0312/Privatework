package com.example.three.bean;

import java.io.Serializable;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.bean
 * 文件名：CategoryTab
 * 创建者：liangxq
 * 创建时间：2020/8/10  14:20
 * 描述：TODO
 */
public class CategoryTab implements Serializable {
    /**
     * id : 1
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

    @Override
    public String toString() {
        return "CategoryTab{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
