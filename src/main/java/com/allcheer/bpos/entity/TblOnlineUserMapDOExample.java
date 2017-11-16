package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblOnlineUserMapDOExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public TblOnlineUserMapDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
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
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
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

        public Criteria andUsrNameIsNull() {
            addCriterion("USR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUsrNameIsNotNull() {
            addCriterion("USR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsrNameEqualTo(String value) {
            addCriterion("USR_NAME =", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameNotEqualTo(String value) {
            addCriterion("USR_NAME <>", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameGreaterThan(String value) {
            addCriterion("USR_NAME >", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameGreaterThanOrEqualTo(String value) {
            addCriterion("USR_NAME >=", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameLessThan(String value) {
            addCriterion("USR_NAME <", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameLessThanOrEqualTo(String value) {
            addCriterion("USR_NAME <=", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameLike(String value) {
            addCriterion("USR_NAME like", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameNotLike(String value) {
            addCriterion("USR_NAME not like", value, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameIn(List<String> values) {
            addCriterion("USR_NAME in", values, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameNotIn(List<String> values) {
            addCriterion("USR_NAME not in", values, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameBetween(String value1, String value2) {
            addCriterion("USR_NAME between", value1, value2, "usrName");
            return (Criteria) this;
        }

        public Criteria andUsrNameNotBetween(String value1, String value2) {
            addCriterion("USR_NAME not between", value1, value2, "usrName");
            return (Criteria) this;
        }

        public Criteria andMerIdIsNull() {
            addCriterion("MER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerIdIsNotNull() {
            addCriterion("MER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerIdEqualTo(String value) {
            addCriterion("MER_ID =", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotEqualTo(String value) {
            addCriterion("MER_ID <>", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThan(String value) {
            addCriterion("MER_ID >", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("MER_ID >=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThan(String value) {
            addCriterion("MER_ID <", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThanOrEqualTo(String value) {
            addCriterion("MER_ID <=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLike(String value) {
            addCriterion("MER_ID like", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotLike(String value) {
            addCriterion("MER_ID not like", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdIn(List<String> values) {
            addCriterion("MER_ID in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotIn(List<String> values) {
            addCriterion("MER_ID not in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdBetween(String value1, String value2) {
            addCriterion("MER_ID between", value1, value2, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotBetween(String value1, String value2) {
            addCriterion("MER_ID not between", value1, value2, "merId");
            return (Criteria) this;
        }

        public Criteria andCardUpIsNull() {
            addCriterion("CARD_UP is null");
            return (Criteria) this;
        }

        public Criteria andCardUpIsNotNull() {
            addCriterion("CARD_UP is not null");
            return (Criteria) this;
        }

        public Criteria andCardUpEqualTo(String value) {
            addCriterion("CARD_UP =", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpNotEqualTo(String value) {
            addCriterion("CARD_UP <>", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpGreaterThan(String value) {
            addCriterion("CARD_UP >", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_UP >=", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpLessThan(String value) {
            addCriterion("CARD_UP <", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpLessThanOrEqualTo(String value) {
            addCriterion("CARD_UP <=", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpLike(String value) {
            addCriterion("CARD_UP like", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpNotLike(String value) {
            addCriterion("CARD_UP not like", value, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpIn(List<String> values) {
            addCriterion("CARD_UP in", values, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpNotIn(List<String> values) {
            addCriterion("CARD_UP not in", values, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpBetween(String value1, String value2) {
            addCriterion("CARD_UP between", value1, value2, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardUpNotBetween(String value1, String value2) {
            addCriterion("CARD_UP not between", value1, value2, "cardUp");
            return (Criteria) this;
        }

        public Criteria andCardDownIsNull() {
            addCriterion("CARD_DOWN is null");
            return (Criteria) this;
        }

        public Criteria andCardDownIsNotNull() {
            addCriterion("CARD_DOWN is not null");
            return (Criteria) this;
        }

        public Criteria andCardDownEqualTo(String value) {
            addCriterion("CARD_DOWN =", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownNotEqualTo(String value) {
            addCriterion("CARD_DOWN <>", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownGreaterThan(String value) {
            addCriterion("CARD_DOWN >", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_DOWN >=", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownLessThan(String value) {
            addCriterion("CARD_DOWN <", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownLessThanOrEqualTo(String value) {
            addCriterion("CARD_DOWN <=", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownLike(String value) {
            addCriterion("CARD_DOWN like", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownNotLike(String value) {
            addCriterion("CARD_DOWN not like", value, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownIn(List<String> values) {
            addCriterion("CARD_DOWN in", values, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownNotIn(List<String> values) {
            addCriterion("CARD_DOWN not in", values, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownBetween(String value1, String value2) {
            addCriterion("CARD_DOWN between", value1, value2, "cardDown");
            return (Criteria) this;
        }

        public Criteria andCardDownNotBetween(String value1, String value2) {
            addCriterion("CARD_DOWN not between", value1, value2, "cardDown");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TBL_ONLINE_USER_MAP
     *
     * @mbggenerated
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