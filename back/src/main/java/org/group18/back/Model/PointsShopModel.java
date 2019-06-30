package org.group18.back.Model;

import org.group18.back.Entity.Goods;
import org.group18.back.Entity.Shop;
import java.util.List;

import java.util.ArrayList;


public class PointsShopModel {

    private Shop shop;
    private List<Goods> goodsList;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
