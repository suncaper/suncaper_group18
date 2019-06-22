package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.OrderState;
import org.group18.back.Entity.OrderStateExample;

public interface OrderStateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    long countByExample(OrderStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int deleteByExample(OrderStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int insert(OrderState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int insertSelective(OrderState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    List<OrderState> selectByExample(OrderStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    OrderState selectByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") OrderState record, @Param("example") OrderStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByExample(@Param("record") OrderState record, @Param("example") OrderStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByPrimaryKeySelective(OrderState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_state
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByPrimaryKey(OrderState record);
}