package org.group18.back.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.id
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.uid
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.user_name
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.phone
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.email
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.balance
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private BigDecimal balance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.points
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private Integer points;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.registration_date
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private Date registrationDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.pass_word
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private String passWord;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.is_seller
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private Boolean isSeller;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.id
     *
     * @return the value of t_user.id
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.id
     *
     * @param id the value for t_user.id
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.uid
     *
     * @return the value of t_user.uid
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.uid
     *
     * @param uid the value for t_user.uid
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.user_name
     *
     * @return the value of t_user.user_name
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.user_name
     *
     * @param userName the value for t_user.user_name
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.phone
     *
     * @return the value of t_user.phone
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.phone
     *
     * @param phone the value for t_user.phone
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.email
     *
     * @return the value of t_user.email
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.email
     *
     * @param email the value for t_user.email
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.balance
     *
     * @return the value of t_user.balance
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.balance
     *
     * @param balance the value for t_user.balance
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.points
     *
     * @return the value of t_user.points
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.points
     *
     * @param points the value for t_user.points
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.registration_date
     *
     * @return the value of t_user.registration_date
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.registration_date
     *
     * @param registrationDate the value for t_user.registration_date
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.pass_word
     *
     * @return the value of t_user.pass_word
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.pass_word
     *
     * @param passWord the value for t_user.pass_word
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.is_seller
     *
     * @return the value of t_user.is_seller
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public Boolean getIsSeller() {
        return isSeller;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.is_seller
     *
     * @param isSeller the value for t_user.is_seller
     *
     * @mbg.generated Mon Jun 24 16:43:39 CST 2019
     */
    public void setIsSeller(Boolean isSeller) {
        this.isSeller = isSeller;
    }
}