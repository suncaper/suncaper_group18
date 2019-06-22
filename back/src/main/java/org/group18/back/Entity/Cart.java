package org.group18.back.Entity;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cart.id
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cart.user_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private String userUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cart.goods_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Integer goodsUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cart.date
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cart.specification_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Integer specificationUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cart.amount
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Integer amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cart.is_exchange
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Boolean isExchange;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_cart
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cart.id
     *
     * @return the value of t_cart.id
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cart.id
     *
     * @param id the value for t_cart.id
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cart.user_uid
     *
     * @return the value of t_cart.user_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cart.user_uid
     *
     * @param userUid the value for t_cart.user_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cart.goods_uid
     *
     * @return the value of t_cart.goods_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Integer getGoodsUid() {
        return goodsUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cart.goods_uid
     *
     * @param goodsUid the value for t_cart.goods_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setGoodsUid(Integer goodsUid) {
        this.goodsUid = goodsUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cart.date
     *
     * @return the value of t_cart.date
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cart.date
     *
     * @param date the value for t_cart.date
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cart.specification_uid
     *
     * @return the value of t_cart.specification_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Integer getSpecificationUid() {
        return specificationUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cart.specification_uid
     *
     * @param specificationUid the value for t_cart.specification_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setSpecificationUid(Integer specificationUid) {
        this.specificationUid = specificationUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cart.amount
     *
     * @return the value of t_cart.amount
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cart.amount
     *
     * @param amount the value for t_cart.amount
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cart.is_exchange
     *
     * @return the value of t_cart.is_exchange
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Boolean getIsExchange() {
        return isExchange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cart.is_exchange
     *
     * @param isExchange the value for t_cart.is_exchange
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setIsExchange(Boolean isExchange) {
        this.isExchange = isExchange;
    }
}