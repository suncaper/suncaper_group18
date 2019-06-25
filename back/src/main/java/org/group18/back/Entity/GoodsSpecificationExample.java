package org.group18.back.Entity;

import java.util.ArrayList;
import java.util.List;

public class GoodsSpecificationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public GoodsSpecificationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameIsNull() {
            addCriterion("specification_name is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameIsNotNull() {
            addCriterion("specification_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameEqualTo(String value) {
            addCriterion("specification_name =", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameNotEqualTo(String value) {
            addCriterion("specification_name <>", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameGreaterThan(String value) {
            addCriterion("specification_name >", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameGreaterThanOrEqualTo(String value) {
            addCriterion("specification_name >=", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameLessThan(String value) {
            addCriterion("specification_name <", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameLessThanOrEqualTo(String value) {
            addCriterion("specification_name <=", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameLike(String value) {
            addCriterion("specification_name like", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameNotLike(String value) {
            addCriterion("specification_name not like", value, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameIn(List<String> values) {
            addCriterion("specification_name in", values, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameNotIn(List<String> values) {
            addCriterion("specification_name not in", values, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameBetween(String value1, String value2) {
            addCriterion("specification_name between", value1, value2, "specificationName");
            return (Criteria) this;
        }

        public Criteria andSpecificationNameNotBetween(String value1, String value2) {
            addCriterion("specification_name not between", value1, value2, "specificationName");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNull() {
            addCriterion("sale_amount is null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNotNull() {
            addCriterion("sale_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountEqualTo(Integer value) {
            addCriterion("sale_amount =", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotEqualTo(Integer value) {
            addCriterion("sale_amount <>", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThan(Integer value) {
            addCriterion("sale_amount >", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_amount >=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThan(Integer value) {
            addCriterion("sale_amount <", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThanOrEqualTo(Integer value) {
            addCriterion("sale_amount <=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIn(List<Integer> values) {
            addCriterion("sale_amount in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotIn(List<Integer> values) {
            addCriterion("sale_amount not in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountBetween(Integer value1, Integer value2) {
            addCriterion("sale_amount between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_amount not between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsUidIsNull() {
            addCriterion("goods_uid is null");
            return (Criteria) this;
        }

        public Criteria andGoodsUidIsNotNull() {
            addCriterion("goods_uid is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsUidEqualTo(Integer value) {
            addCriterion("goods_uid =", value, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidNotEqualTo(Integer value) {
            addCriterion("goods_uid <>", value, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidGreaterThan(Integer value) {
            addCriterion("goods_uid >", value, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_uid >=", value, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidLessThan(Integer value) {
            addCriterion("goods_uid <", value, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidLessThanOrEqualTo(Integer value) {
            addCriterion("goods_uid <=", value, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidIn(List<Integer> values) {
            addCriterion("goods_uid in", values, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidNotIn(List<Integer> values) {
            addCriterion("goods_uid not in", values, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidBetween(Integer value1, Integer value2) {
            addCriterion("goods_uid between", value1, value2, "goodsUid");
            return (Criteria) this;
        }

        public Criteria andGoodsUidNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_uid not between", value1, value2, "goodsUid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_goods_specification
     *
     * @mbg.generated do_not_delete_during_merge Mon Jun 24 18:23:20 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_goods_specification
     *
     * @mbg.generated Mon Jun 24 18:23:20 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}