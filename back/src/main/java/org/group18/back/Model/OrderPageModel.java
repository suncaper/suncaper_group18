package org.group18.back.Model;

import org.group18.back.Entity.Order;
import org.group18.back.Entity.Shop;

import java.util.List;

public class OrderPageModel {
    private Shop shopBaseInfo;//订单对应的店的基本信息
    private Order orderInfo;//订单总体信息
    private List<GoodsSpecificationModel> GSN;//商品规格-不使用积分兑换
    private List<GoodsSpecificationModel> GSY;//商品规格-使用积分兑换

    public Order getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<GoodsSpecificationModel> getGSN() {
        return GSN;
    }

    public void setGSN(List<GoodsSpecificationModel> GSN) {
        this.GSN = GSN;
    }

    public List<GoodsSpecificationModel> getGSY() {
        return GSY;
    }

    public void setGSY(List<GoodsSpecificationModel> GSY) {
        this.GSY = GSY;
    }

    public Shop getShopBaseInfo() {
        return shopBaseInfo;
    }

    public void setShopBaseInfo(Shop shopBaseInfo) {
        this.shopBaseInfo = shopBaseInfo;
    }
}
