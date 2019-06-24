package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.Ticket;
import org.group18.back.Entity.TicketExample;

public interface TicketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    long countByExample(TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int deleteByExample(TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int insert(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int insertSelective(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    List<Ticket> selectByExample(TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    Ticket selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByPrimaryKeySelective(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ticket
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByPrimaryKey(Ticket record);
}