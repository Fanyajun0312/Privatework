package com.example.three.reqstbean;

/**
 * @date：2020/8/10
 * @describe：作为请求头信息
 * @author：FanYaJun
 */
public class CategoryPrams {
    /**
     * parentId : 0
     */
    private String parentId;
    /**
     * categoryId : 24
     * pageNo : 1
     */

    private int categoryId;
    private int pageNo;

    public String getParentId() {
        return parentId;
    }

    public String setParentId(String parentId) {
        this.parentId = parentId;
        return parentId;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
