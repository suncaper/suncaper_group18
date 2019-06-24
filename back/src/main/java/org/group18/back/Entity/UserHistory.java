package org.group18.back.Entity;

import java.io.Serializable;
import java.util.Date;

public class UserHistory implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_history.id
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_history.user_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private String userUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_history.goods_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Integer goodsUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_history.specification
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Integer specification;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_history.create_date
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_history.id
     *
     * @return the value of t_user_history.id
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_history.id
     *
     * @param id the value for t_user_history.id
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_history.user_uid
     *
     * @return the value of t_user_history.user_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_history.user_uid
     *
     * @param userUid the value for t_user_history.user_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_history.goods_uid
     *
     * @return the value of t_user_history.goods_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Integer getGoodsUid() {
        return goodsUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_history.goods_uid
     *
     * @param goodsUid the value for t_user_history.goods_uid
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setGoodsUid(Integer goodsUid) {
        this.goodsUid = goodsUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_history.specification
     *
     * @return the value of t_user_history.specification
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Integer getSpecification() {
        return specification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_history.specification
     *
     * @param specification the value for t_user_history.specification
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setSpecification(Integer specification) {
        this.specification = specification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_history.create_date
     *
     * @return the value of t_user_history.create_date
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_history.create_date
     *
     * @param createDate the value for t_user_history.create_date
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}