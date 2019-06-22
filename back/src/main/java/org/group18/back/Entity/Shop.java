package org.group18.back.Entity;

import java.io.Serializable;

public class Shop implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shop.uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shop.name
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shop.img_url
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private String imgUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shop.describe
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private String describe;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shop.address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shop.seller_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private String sellerUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shop.type
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shop.uid
     *
     * @return the value of t_shop.uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shop.uid
     *
     * @param uid the value for t_shop.uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shop.name
     *
     * @return the value of t_shop.name
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shop.name
     *
     * @param name the value for t_shop.name
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shop.img_url
     *
     * @return the value of t_shop.img_url
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shop.img_url
     *
     * @param imgUrl the value for t_shop.img_url
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shop.describe
     *
     * @return the value of t_shop.describe
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shop.describe
     *
     * @param describe the value for t_shop.describe
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shop.address
     *
     * @return the value of t_shop.address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shop.address
     *
     * @param address the value for t_shop.address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shop.seller_uid
     *
     * @return the value of t_shop.seller_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public String getSellerUid() {
        return sellerUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shop.seller_uid
     *
     * @param sellerUid the value for t_shop.seller_uid
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid == null ? null : sellerUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shop.type
     *
     * @return the value of t_shop.type
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shop.type
     *
     * @param type the value for t_shop.type
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }
}