package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Entity.UserAddressExample;

public interface UserAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    long countByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int deleteByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int insert(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int insertSelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    List<UserAddress> selectByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    UserAddress selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int updateByPrimaryKeySelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Wed Jun 26 16:57:00 CST 2019
     */
    int updateByPrimaryKey(UserAddress record);
}