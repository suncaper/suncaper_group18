package org.group18.back.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.id
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.sn
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private String sn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.price
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.discount_price
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private BigDecimal discountPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.create_date
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.state_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private String stateUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.type_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private String typeUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.user_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private String userUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.seller_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private String sellerUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.is_paid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Boolean isPaid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.method_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private String methodUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.paid_by_points
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Boolean paidByPoints;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.paid_by_money
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Boolean paidByMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.address_id
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Integer addressId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.user_delete_state
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Boolean userDeleteState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.seller_delete_state
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private Boolean sellerDeleteState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.id
     *
     * @return the value of t_order.id
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.id
     *
     * @param id the value for t_order.id
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.sn
     *
     * @return the value of t_order.sn
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public String getSn() {
        return sn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.sn
     *
     * @param sn the value for t_order.sn
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.price
     *
     * @return the value of t_order.price
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.price
     *
     * @param price the value for t_order.price
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.discount_price
     *
     * @return the value of t_order.discount_price
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.discount_price
     *
     * @param discountPrice the value for t_order.discount_price
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.create_date
     *
     * @return the value of t_order.create_date
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.create_date
     *
     * @param createDate the value for t_order.create_date
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.state_uid
     *
     * @return the value of t_order.state_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public String getStateUid() {
        return stateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.state_uid
     *
     * @param stateUid the value for t_order.state_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setStateUid(String stateUid) {
        this.stateUid = stateUid == null ? null : stateUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.type_uid
     *
     * @return the value of t_order.type_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public String getTypeUid() {
        return typeUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.type_uid
     *
     * @param typeUid the value for t_order.type_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setTypeUid(String typeUid) {
        this.typeUid = typeUid == null ? null : typeUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.user_uid
     *
     * @return the value of t_order.user_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.user_uid
     *
     * @param userUid the value for t_order.user_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.seller_uid
     *
     * @return the value of t_order.seller_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public String getSellerUid() {
        return sellerUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.seller_uid
     *
     * @param sellerUid the value for t_order.seller_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid == null ? null : sellerUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.is_paid
     *
     * @return the value of t_order.is_paid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Boolean getIsPaid() {
        return isPaid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.is_paid
     *
     * @param isPaid the value for t_order.is_paid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.method_uid
     *
     * @return the value of t_order.method_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public String getMethodUid() {
        return methodUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.method_uid
     *
     * @param methodUid the value for t_order.method_uid
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setMethodUid(String methodUid) {
        this.methodUid = methodUid == null ? null : methodUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.paid_by_points
     *
     * @return the value of t_order.paid_by_points
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Boolean getPaidByPoints() {
        return paidByPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.paid_by_points
     *
     * @param paidByPoints the value for t_order.paid_by_points
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setPaidByPoints(Boolean paidByPoints) {
        this.paidByPoints = paidByPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.paid_by_money
     *
     * @return the value of t_order.paid_by_money
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Boolean getPaidByMoney() {
        return paidByMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.paid_by_money
     *
     * @param paidByMoney the value for t_order.paid_by_money
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setPaidByMoney(Boolean paidByMoney) {
        this.paidByMoney = paidByMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.address_id
     *
     * @return the value of t_order.address_id
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.address_id
     *
     * @param addressId the value for t_order.address_id
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.user_delete_state
     *
     * @return the value of t_order.user_delete_state
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Boolean getUserDeleteState() {
        return userDeleteState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.user_delete_state
     *
     * @param userDeleteState the value for t_order.user_delete_state
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setUserDeleteState(Boolean userDeleteState) {
        this.userDeleteState = userDeleteState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.seller_delete_state
     *
     * @return the value of t_order.seller_delete_state
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public Boolean getSellerDeleteState() {
        return sellerDeleteState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.seller_delete_state
     *
     * @param sellerDeleteState the value for t_order.seller_delete_state
     *
     * @mbg.generated Thu Jun 27 16:26:16 CST 2019
     */
    public void setSellerDeleteState(Boolean sellerDeleteState) {
        this.sellerDeleteState = sellerDeleteState;
    }
}