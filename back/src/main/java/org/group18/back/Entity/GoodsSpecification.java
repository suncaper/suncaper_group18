package org.group18.back.Entity;

import java.io.Serializable;

public class GoodsSpecification implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_specification.uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_specification.specification_name
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private String specificationName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_specification.amount
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Integer amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_specification.sale_amount
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Integer saleAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_specification.goods_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Integer goodsUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_specification.uid
     *
     * @return the value of t_goods_specification.uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_specification.uid
     *
     * @param uid the value for t_goods_specification.uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_specification.specification_name
     *
     * @return the value of t_goods_specification.specification_name
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public String getSpecificationName() {
        return specificationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_specification.specification_name
     *
     * @param specificationName the value for t_goods_specification.specification_name
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName == null ? null : specificationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_specification.amount
     *
     * @return the value of t_goods_specification.amount
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_specification.amount
     *
     * @param amount the value for t_goods_specification.amount
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_specification.sale_amount
     *
     * @return the value of t_goods_specification.sale_amount
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Integer getSaleAmount() {
        return saleAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_specification.sale_amount
     *
     * @param saleAmount the value for t_goods_specification.sale_amount
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setSaleAmount(Integer saleAmount) {
        this.saleAmount = saleAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_specification.goods_uid
     *
     * @return the value of t_goods_specification.goods_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Integer getGoodsUid() {
        return goodsUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_specification.goods_uid
     *
     * @param goodsUid the value for t_goods_specification.goods_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setGoodsUid(Integer goodsUid) {
        this.goodsUid = goodsUid;
    }
}