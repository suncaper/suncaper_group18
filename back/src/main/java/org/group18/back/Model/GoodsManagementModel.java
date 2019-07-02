package org.group18.back.Model;

import java.math.BigDecimal;

public class GoodsManagementModel {
    private Integer category_uid; //种类id
    private String goods_name;//商品名
    private BigDecimal price;//原价
    private BigDecimal discount_price;//优惠价格
    private String descriptions;//商品描述
    private Boolean is_exchange;//是否积分交换
    private Integer points = 0;//积分，不能积分交换的商品默认为0
    private String specification_name;//规格名
    private Integer amount;//规格数量
//    private String img_url;
//    private String detail_img_url;

    public Integer getCategory_uid() {
        return category_uid;
    }

    public void setCategory_uid(Integer category_uid) {
        this.category_uid = category_uid;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(BigDecimal discount_price) {
        this.discount_price = discount_price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getIs_exchange() {
        return is_exchange;
    }

    public void setIs_exchange(Boolean is_exchange) {
        this.is_exchange = is_exchange;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getSpecification_name() {
        return specification_name;
    }

    public void setSpecification_name(String specification_name) {
        this.specification_name = specification_name;
    }

//    public String getImg_url() {
//        return img_url;
//    }
//
//    public void setImg_url(String img_url) {
//        this.img_url = img_url;
//    }

//    public String getDetail_img_url() {
//        return detail_img_url;
//    }
//
//    public void setDetail_img_url(String detail_img_url) {
//        this.detail_img_url = detail_img_url;
//    }
}
