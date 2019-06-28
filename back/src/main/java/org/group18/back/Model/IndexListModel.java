package org.group18.back.Model;

import org.group18.back.Entity.Goods;
import org.group18.back.Entity.Shop;

public class IndexListModel {
    private Integer goods_uid;
    private Integer shop_uid;

    public Integer getGoods_uid() {
        return goods_uid;
    }

    public void setGoods_uid(Integer goods_uid) {
        this.goods_uid = goods_uid;
    }

    public Integer getShop_uid() {
        return shop_uid;
    }

    public void setShop_uid(Integer shop_uid) {
        this.shop_uid = shop_uid;
    }
}
