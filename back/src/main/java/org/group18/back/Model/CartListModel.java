package org.group18.back.Model;

import java.math.BigDecimal;

//购物车商品信息
public class CartListModel {
    private Integer amount;
    private BigDecimal discount_price;
    private String goods_name;
    private String shop_name;
    private String img_url;
    private boolean is_exchange;
    private Integer points;
    private String specification_name;
    private String user_uid;
    private Integer specification_uid;

    public String getSpecification_name() {
        return specification_name;
    }

    public void setSpecification_name(String specification_name) {
        this.specification_name = specification_name;
    }

    public boolean isIs_exchange() {
        return is_exchange;
    }

    public void setIs_exchange(boolean is_exchange) {
        this.is_exchange = is_exchange;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(BigDecimal discount_price) {
        this.discount_price = discount_price;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public Integer getSpecification_uid() {
        return specification_uid;
    }

    public void setSpecification_uid(Integer specification_uid) {
        this.specification_uid = specification_uid;
    }
}
