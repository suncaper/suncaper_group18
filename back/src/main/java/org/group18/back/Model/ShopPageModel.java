package org.group18.back.Model;


import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.Shop;

import java.util.List;

//商店页面模型
public class ShopPageModel {
    private Shop shopBaseInfo;//商店信息
    private List<Goods> shopGoodsList;//商店商品列表
    private List<Category> shopCategories;//商店种类列表
    private int salesVolumeCount;//商品销售总数量

    public int getSalesVolumeCount() {
        return salesVolumeCount;
    }

    public void setSalesVolumeCount(int salesVolumeCount) {
        this.salesVolumeCount = salesVolumeCount;
    }

    public Shop getShopBaseInfo() {
        return shopBaseInfo;
    }

    public void setShopBaseInfo(Shop shopBaseInfo) {
        this.shopBaseInfo = shopBaseInfo;
    }

    public List<Goods> getShopGoodsList() {
        return shopGoodsList;
    }

    public void setShopGoodsList(List<Goods> shopGoodsList) {
        this.shopGoodsList = shopGoodsList;
    }

    public List<Category> getShopCategories() {
        return shopCategories;
    }

    public void setShopCategories(List<Category> shopCategories) {
        this.shopCategories = shopCategories;
    }
}
