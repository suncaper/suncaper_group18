package org.group18.back.Model;

import org.group18.back.Entity.Goods;
import org.group18.back.Entity.GoodsSpecification;

public class GoodsSpecificationModel {
    private Goods goods;
    private GoodsSpecification goodsSpecification;
    private Boolean reviewState;//评论状态
    private int amount;//购买的数量

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsSpecification getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(GoodsSpecification goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Boolean getReviewState() {
        return reviewState;
    }

    public void setReviewState(Boolean reviewState) {
        this.reviewState = reviewState;
    }
}
