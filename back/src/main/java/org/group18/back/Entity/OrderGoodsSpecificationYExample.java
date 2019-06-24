package org.group18.back.Entity;

import java.util.ArrayList;
import java.util.List;

public class OrderGoodsSpecificationYExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public OrderGoodsSpecificationYExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
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
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderUidIsNull() {
            addCriterion("order_uid is null");
            return (Criteria) this;
        }

        public Criteria andOrderUidIsNotNull() {
            addCriterion("order_uid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUidEqualTo(Integer value) {
            addCriterion("order_uid =", value, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidNotEqualTo(Integer value) {
            addCriterion("order_uid <>", value, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidGreaterThan(Integer value) {
            addCriterion("order_uid >", value, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_uid >=", value, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidLessThan(Integer value) {
            addCriterion("order_uid <", value, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidLessThanOrEqualTo(Integer value) {
            addCriterion("order_uid <=", value, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidIn(List<Integer> values) {
            addCriterion("order_uid in", values, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidNotIn(List<Integer> values) {
            addCriterion("order_uid not in", values, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidBetween(Integer value1, Integer value2) {
            addCriterion("order_uid between", value1, value2, "orderUid");
            return (Criteria) this;
        }

        public Criteria andOrderUidNotBetween(Integer value1, Integer value2) {
            addCriterion("order_uid not between", value1, value2, "orderUid");
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

        public Criteria andSpecificationUidIsNull() {
            addCriterion("specification_uid is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidIsNotNull() {
            addCriterion("specification_uid is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidEqualTo(Integer value) {
            addCriterion("specification_uid =", value, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidNotEqualTo(Integer value) {
            addCriterion("specification_uid <>", value, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidGreaterThan(Integer value) {
            addCriterion("specification_uid >", value, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("specification_uid >=", value, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidLessThan(Integer value) {
            addCriterion("specification_uid <", value, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidLessThanOrEqualTo(Integer value) {
            addCriterion("specification_uid <=", value, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidIn(List<Integer> values) {
            addCriterion("specification_uid in", values, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidNotIn(List<Integer> values) {
            addCriterion("specification_uid not in", values, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidBetween(Integer value1, Integer value2) {
            addCriterion("specification_uid between", value1, value2, "specificationUid");
            return (Criteria) this;
        }

        public Criteria andSpecificationUidNotBetween(Integer value1, Integer value2) {
            addCriterion("specification_uid not between", value1, value2, "specificationUid");
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

        public Criteria andPointsIsNull() {
            addCriterion("points is null");
            return (Criteria) this;
        }

        public Criteria andPointsIsNotNull() {
            addCriterion("points is not null");
            return (Criteria) this;
        }

        public Criteria andPointsEqualTo(Integer value) {
            addCriterion("points =", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotEqualTo(Integer value) {
            addCriterion("points <>", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThan(Integer value) {
            addCriterion("points >", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThanOrEqualTo(Integer value) {
            addCriterion("points >=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThan(Integer value) {
            addCriterion("points <", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThanOrEqualTo(Integer value) {
            addCriterion("points <=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsIn(List<Integer> values) {
            addCriterion("points in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotIn(List<Integer> values) {
            addCriterion("points not in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsBetween(Integer value1, Integer value2) {
            addCriterion("points between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotBetween(Integer value1, Integer value2) {
            addCriterion("points not between", value1, value2, "points");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated do_not_delete_during_merge Mon Jun 24 17:11:39 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_order_goods_specification_y
     *
     * @mbg.generated Mon Jun 24 17:11:39 CST 2019
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