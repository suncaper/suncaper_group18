package org.group18.back.Entity;

import java.io.Serializable;

public class OrderState implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_state.id
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_state.uid
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_state.description
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_state
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_state.id
     *
     * @return the value of t_order_state.id
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_state.id
     *
     * @param id the value for t_order_state.id
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_state.uid
     *
     * @return the value of t_order_state.uid
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_state.uid
     *
     * @param uid the value for t_order_state.uid
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_state.description
     *
     * @return the value of t_order_state.description
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_state.description
     *
     * @param description the value for t_order_state.description
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}