package org.group18.back.Entity;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_category.uid
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_category.descriptions
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private String descriptions;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_category.create_date
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_category.uid
     *
     * @return the value of t_category.uid
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_category.uid
     *
     * @param uid the value for t_category.uid
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_category.descriptions
     *
     * @return the value of t_category.descriptions
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_category.descriptions
     *
     * @param descriptions the value for t_category.descriptions
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions == null ? null : descriptions.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_category.create_date
     *
     * @return the value of t_category.create_date
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_category.create_date
     *
     * @param createDate the value for t_category.create_date
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}