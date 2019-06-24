package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.User;
import org.group18.back.Entity.UserExample;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKey(User record);
}