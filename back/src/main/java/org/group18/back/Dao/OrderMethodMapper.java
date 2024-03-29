package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.OrderMethod;
import org.group18.back.Entity.OrderMethodExample;

public interface OrderMethodMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    long countByExample(OrderMethodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByExample(OrderMethodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insert(OrderMethod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insertSelective(OrderMethod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    List<OrderMethod> selectByExample(OrderMethodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    OrderMethod selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExampleSelective(@Param("record") OrderMethod record, @Param("example") OrderMethodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExample(@Param("record") OrderMethod record, @Param("example") OrderMethodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKeySelective(OrderMethod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_method
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKey(OrderMethod record);
}