package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.OrderGoodsSpecificationY;
import org.group18.back.Entity.OrderGoodsSpecificationYExample;

public interface OrderGoodsSpecificationYMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    long countByExample(OrderGoodsSpecificationYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByExample(OrderGoodsSpecificationYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insert(OrderGoodsSpecificationY record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insertSelective(OrderGoodsSpecificationY record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    List<OrderGoodsSpecificationY> selectByExample(OrderGoodsSpecificationYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    OrderGoodsSpecificationY selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExampleSelective(@Param("record") OrderGoodsSpecificationY record, @Param("example") OrderGoodsSpecificationYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExample(@Param("record") OrderGoodsSpecificationY record, @Param("example") OrderGoodsSpecificationYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKeySelective(OrderGoodsSpecificationY record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKey(OrderGoodsSpecificationY record);
}