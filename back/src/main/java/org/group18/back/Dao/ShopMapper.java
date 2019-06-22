package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.Shop;
import org.group18.back.Entity.ShopExample;

public interface ShopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    long countByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int deleteByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int insert(Shop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int insertSelective(Shop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    List<Shop> selectByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") Shop record, @Param("example") ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shop
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByExample(@Param("record") Shop record, @Param("example") ShopExample example);
}