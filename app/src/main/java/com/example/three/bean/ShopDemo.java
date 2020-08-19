package com.example.three.bean;

import java.util.List;

/**
 * Created by cc on 2020/8/18.
 */

public class ShopDemo {
    /**
     * id : 12
     * categoryId : 15
     * goodsDesc : 联想（ThinkPad）E460（20ETA063CD）14英寸笔记本电脑（i7-6498DU 8G 1T 2G独显 Win10）
     * goodsDefaultIcon : https://img14.360buyimg.com/n1/s450x450_jfs/t5446/184/2581563799/286954/9ebfad09/591bddbcNc724d048.jpg
     * goodsDefaultPrice : 100
     * goodsDetailOne : https://img20.360buyimg.com/vc/jfs/t5563/279/850602969/1094377/272f0258/5907fc23N3a312831.jpg
     * goodsDetailTwo : https://img20.360buyimg.com/vc/jfs/t5563/279/850602969/1094377/272f0258/5907fc23N3a312831.jpg
     * goodsSalesCount : 56667
     * goodsStockCount : 8434
     * goodsCode : 10000000015
     * goodsDefaultSku : 【i7豪华版】8G 1T,1件
     * goodsBanner : https://img14.360buyimg.com/n1/s450x450_jfs/t5446/184/2581563799/286954/9ebfad09/591bddbcNc724d048.jpg,https://img14.360buyimg.com/n1/s450x450_jfs/t5209/149/1168851360/329572/9bdcbaa5/590c421eN49bc2d11.jpg,https://img14.360buyimg.com/n1/s450x450_jfs/t5437/240/2545062154/189407/7ab57b0b/591bddc2N6c52f1ad.jpg,https://img14.360buyimg.com/n1/s450x450_jfs/t5326/216/1147917773/304909/e711dfc6/590c421fNc5def6a7.jpg
     * goodsSku : [{"id":21,"goodsId":12,"goodsSkuTitle":"颜色","goodsSkuContent":"【i7豪华版】8G 1T,【i7基础版】4G 500G,【i7顶配版】8G 256GSSD,【爆款】180开合i5 4G 500G,【高配款】180开合i5 8G 500G","skuTitle":"颜色","skuContent":["【i7豪华版】8G 1T","【i7基础版】4G 500G","【i7顶配版】8G 256GSSD","【爆款】180开合i5 4G 500G","【高配款】180开合i5 8G 500G"]}]
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
         * id : 21
         * goodsId : 12
         * goodsSkuTitle : 颜色
         * goodsSkuContent : 【i7豪华版】8G 1T,【i7基础版】4G 500G,【i7顶配版】8G 256GSSD,【爆款】180开合i5 4G 500G,【高配款】180开合i5 8G 500G
         * skuTitle : 颜色
         * skuContent : ["【i7豪华版】8G 1T","【i7基础版】4G 500G","【i7顶配版】8G 256GSSD","【爆款】180开合i5 4G 500G","【高配款】180开合i5 8G 500G"]
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
