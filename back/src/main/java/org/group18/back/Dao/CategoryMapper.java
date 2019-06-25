package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.Category;
import org.group18.back.Entity.CategoryExample;

public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    long countByExample(CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByExample(CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int deleteByPrimaryKey(Integer uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int insertSelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    List<Category> selectByExample(CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    Category selectByPrimaryKey(Integer uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_category
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    int updateByPrimaryKey(Category record);

    List<Category> findCategoryByShopUid(@Param(value = "shopUid") Integer shopUid);
}