package org.group18.back.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.GoodsReview;
import org.group18.back.Entity.GoodsReviewExample;

public interface GoodsReviewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    long countByExample(GoodsReviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int deleteByExample(GoodsReviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int insert(GoodsReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int insertSelective(GoodsReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    List<GoodsReview> selectByExample(GoodsReviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    GoodsReview selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByExampleSelective(@Param("record") GoodsReview record, @Param("example") GoodsReviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByExample(@Param("record") GoodsReview record, @Param("example") GoodsReviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_review
     *
     * @mbg.generated Mon Jun 24 10:24:30 CST 2019
     */
    int updateByPrimaryKey(GoodsReview record);
}