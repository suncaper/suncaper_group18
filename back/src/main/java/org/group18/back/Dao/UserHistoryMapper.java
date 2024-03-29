package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.UserHistory;
import org.group18.back.Entity.UserHistoryExample;

public interface UserHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    long countByExample(UserHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByExample(UserHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insert(UserHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insertSelective(UserHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    List<UserHistory> selectByExample(UserHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    UserHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserHistory record, @Param("example") UserHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExample(@Param("record") UserHistory record, @Param("example") UserHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKeySelective(UserHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_history
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKey(UserHistory record);
}