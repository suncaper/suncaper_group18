package org.group18.back.Model;

import org.group18.back.Entity.Order;
import org.group18.back.Entity.Shop;
import org.group18.back.Entity.UserAddress;

import java.math.BigDecimal;
import java.util.List;

public class OrderPageModel {
    private Shop shopBaseInfo;//订单对应的店的基本信息
    private Order orderInfo;//订单总体信息
    private int totalAmount;//总共的数量
    private BigDecimal totalPrice;//总共实付价格
    private int totalPoints;//总共的积分消费
    private UserAddress userAddress;//收货人地址信息
    private List<GoodsSpecificationModel> GSN;//商品规格-不使用积分兑换
    private List<GoodsSpecificationModel> GSY;//商品规格-使用积分兑换

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

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
