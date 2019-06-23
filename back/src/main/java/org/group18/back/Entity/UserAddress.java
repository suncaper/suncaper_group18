package org.group18.back.Entity;

import java.io.Serializable;

public class UserAddress implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_address.id
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_address.user_uid
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    private String userUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_address.province
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_address.city
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_address.detail_address
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    private String detailAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user_address
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_address.id
     *
     * @return the value of t_user_address.id
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_address.id
     *
     * @param id the value for t_user_address.id
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_address.user_uid
     *
     * @return the value of t_user_address.user_uid
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_address.user_uid
     *
     * @param userUid the value for t_user_address.user_uid
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_address.province
     *
     * @return the value of t_user_address.province
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_address.province
     *
     * @param province the value for t_user_address.province
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_address.city
     *
     * @return the value of t_user_address.city
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_address.city
     *
     * @param city the value for t_user_address.city
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_address.detail_address
     *
     * @return the value of t_user_address.detail_address
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_address.detail_address
     *
     * @param detailAddress the value for t_user_address.detail_address
     *
     * @mbg.generated Sun Jun 23 09:14:42 CST 2019
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }
}