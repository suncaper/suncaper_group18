package org.group18.back.Model;

import org.group18.back.Entity.Goods;
import org.group18.back.Entity.GoodsReview;
import org.group18.back.Entity.GoodsSpecification;
import org.group18.back.Entity.Shop;

import java.util.List;

public class GoodsDeatilInfoModel {
    private Goods goods;
    private Shop shop;
    private GoodsReview goodsReview;
    private List<Goods> recommendGoods;
    private List<GoodsSpecification> goodsSpecification;

    public List<GoodsSpecification> getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(List<GoodsSpecification> goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public List<Goods> getRecommendGoods() {
        return recommendGoods;
    }

    public void setRecommendGoods(List<Goods> recommendGoods) {
        this.recommendGoods = recommendGoods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public GoodsReview getGoodsReview() {
        return goodsReview;
    }

    public void setGoodsReview(GoodsReview goodsReview) {
        this.goodsReview = goodsReview;
    }
}
