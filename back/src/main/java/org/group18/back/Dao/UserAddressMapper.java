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
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    long countByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int deleteByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int insert(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int insertSelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    List<UserAddress> selectByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address
     *
     * @mbg.generated Sat Jun 22 16:48:21 CST 2019
     */
    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);
}