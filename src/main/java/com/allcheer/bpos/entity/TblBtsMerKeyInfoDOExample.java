package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsMerKeyInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsMerKeyInfoDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andInstIdIsNull() {
            addCriterion("INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNotNull() {
            addCriterion("INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstIdEqualTo(String value) {
            addCriterion("INST_ID =", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotEqualTo(String value) {
            addCriterion("INST_ID <>", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThan(String value) {
            addCriterion("INST_ID >", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_ID >=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThan(String value) {
            addCriterion("INST_ID <", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThanOrEqualTo(String value) {
            addCriterion("INST_ID <=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLike(String value) {
            addCriterion("INST_ID like", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotLike(String value) {
            addCriterion("INST_ID not like", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdIn(List<String> values) {
            addCriterion("INST_ID in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotIn(List<String> values) {
            addCriterion("INST_ID not in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdBetween(String value1, String value2) {
            addCriterion("INST_ID between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotBetween(String value1, String value2) {
            addCriterion("INST_ID not between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdIsNull() {
            addCriterion("INST_MER_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstMerIdIsNotNull() {
            addCriterion("INST_MER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstMerIdEqualTo(String value) {
            addCriterion("INST_MER_ID =", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotEqualTo(String value) {
            addCriterion("INST_MER_ID <>", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdGreaterThan(String value) {
            addCriterion("INST_MER_ID >", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MER_ID >=", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdLessThan(String value) {
            addCriterion("INST_MER_ID <", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdLessThanOrEqualTo(String value) {
            addCriterion("INST_MER_ID <=", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdLike(String value) {
            addCriterion("INST_MER_ID like", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotLike(String value) {
            addCriterion("INST_MER_ID not like", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdIn(List<String> values) {
            addCriterion("INST_MER_ID in", values, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotIn(List<String> values) {
            addCriterion("INST_MER_ID not in", values, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdBetween(String value1, String value2) {
            addCriterion("INST_MER_ID between", value1, value2, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotBetween(String value1, String value2) {
            addCriterion("INST_MER_ID not between", value1, value2, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdIsNull() {
            addCriterion("INST_MER_TERM_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdIsNotNull() {
            addCriterion("INST_MER_TERM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdEqualTo(String value) {
            addCriterion("INST_MER_TERM_ID =", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdNotEqualTo(String value) {
            addCriterion("INST_MER_TERM_ID <>", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdGreaterThan(String value) {
            addCriterion("INST_MER_TERM_ID >", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MER_TERM_ID >=", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdLessThan(String value) {
            addCriterion("INST_MER_TERM_ID <", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdLessThanOrEqualTo(String value) {
            addCriterion("INST_MER_TERM_ID <=", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdLike(String value) {
            addCriterion("INST_MER_TERM_ID like", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdNotLike(String value) {
            addCriterion("INST_MER_TERM_ID not like", value, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdIn(List<String> values) {
            addCriterion("INST_MER_TERM_ID in", values, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdNotIn(List<String> values) {
            addCriterion("INST_MER_TERM_ID not in", values, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdBetween(String value1, String value2) {
            addCriterion("INST_MER_TERM_ID between", value1, value2, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTermIdNotBetween(String value1, String value2) {
            addCriterion("INST_MER_TERM_ID not between", value1, value2, "instMerTermId");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIsNull() {
            addCriterion("PRIMARY_KEY is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIsNotNull() {
            addCriterion("PRIMARY_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyEqualTo(String value) {
            addCriterion("PRIMARY_KEY =", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotEqualTo(String value) {
            addCriterion("PRIMARY_KEY <>", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyGreaterThan(String value) {
            addCriterion("PRIMARY_KEY >", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PRIMARY_KEY >=", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLessThan(String value) {
            addCriterion("PRIMARY_KEY <", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLessThanOrEqualTo(String value) {
            addCriterion("PRIMARY_KEY <=", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLike(String value) {
            addCriterion("PRIMARY_KEY like", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotLike(String value) {
            addCriterion("PRIMARY_KEY not like", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIn(List<String> values) {
            addCriterion("PRIMARY_KEY in", values, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotIn(List<String> values) {
            addCriterion("PRIMARY_KEY not in", values, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyBetween(String value1, String value2) {
            addCriterion("PRIMARY_KEY between", value1, value2, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotBetween(String value1, String value2) {
            addCriterion("PRIMARY_KEY not between", value1, value2, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyIsNull() {
            addCriterion("PIN_KEY is null");
            return (Criteria) this;
        }

        public Criteria andPinKeyIsNotNull() {
            addCriterion("PIN_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andPinKeyEqualTo(String value) {
            addCriterion("PIN_KEY =", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotEqualTo(String value) {
            addCriterion("PIN_KEY <>", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyGreaterThan(String value) {
            addCriterion("PIN_KEY >", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PIN_KEY >=", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLessThan(String value) {
            addCriterion("PIN_KEY <", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLessThanOrEqualTo(String value) {
            addCriterion("PIN_KEY <=", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLike(String value) {
            addCriterion("PIN_KEY like", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotLike(String value) {
            addCriterion("PIN_KEY not like", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyIn(List<String> values) {
            addCriterion("PIN_KEY in", values, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotIn(List<String> values) {
            addCriterion("PIN_KEY not in", values, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyBetween(String value1, String value2) {
            addCriterion("PIN_KEY between", value1, value2, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotBetween(String value1, String value2) {
            addCriterion("PIN_KEY not between", value1, value2, "pinKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyIsNull() {
            addCriterion("MAC_KEY is null");
            return (Criteria) this;
        }

        public Criteria andMacKeyIsNotNull() {
            addCriterion("MAC_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andMacKeyEqualTo(String value) {
            addCriterion("MAC_KEY =", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotEqualTo(String value) {
            addCriterion("MAC_KEY <>", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyGreaterThan(String value) {
            addCriterion("MAC_KEY >", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyGreaterThanOrEqualTo(String value) {
            addCriterion("MAC_KEY >=", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyLessThan(String value) {
            addCriterion("MAC_KEY <", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyLessThanOrEqualTo(String value) {
            addCriterion("MAC_KEY <=", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyLike(String value) {
            addCriterion("MAC_KEY like", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotLike(String value) {
            addCriterion("MAC_KEY not like", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyIn(List<String> values) {
            addCriterion("MAC_KEY in", values, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotIn(List<String> values) {
            addCriterion("MAC_KEY not in", values, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyBetween(String value1, String value2) {
            addCriterion("MAC_KEY between", value1, value2, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotBetween(String value1, String value2) {
            addCriterion("MAC_KEY not between", value1, value2, "macKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyIsNull() {
            addCriterion("TD_KEY is null");
            return (Criteria) this;
        }

        public Criteria andTdKeyIsNotNull() {
            addCriterion("TD_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andTdKeyEqualTo(String value) {
            addCriterion("TD_KEY =", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyNotEqualTo(String value) {
            addCriterion("TD_KEY <>", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyGreaterThan(String value) {
            addCriterion("TD_KEY >", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyGreaterThanOrEqualTo(String value) {
            addCriterion("TD_KEY >=", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyLessThan(String value) {
            addCriterion("TD_KEY <", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyLessThanOrEqualTo(String value) {
            addCriterion("TD_KEY <=", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyLike(String value) {
            addCriterion("TD_KEY like", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyNotLike(String value) {
            addCriterion("TD_KEY not like", value, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyIn(List<String> values) {
            addCriterion("TD_KEY in", values, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyNotIn(List<String> values) {
            addCriterion("TD_KEY not in", values, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyBetween(String value1, String value2) {
            addCriterion("TD_KEY between", value1, value2, "tdKey");
            return (Criteria) this;
        }

        public Criteria andTdKeyNotBetween(String value1, String value2) {
            addCriterion("TD_KEY not between", value1, value2, "tdKey");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("CHECK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("CHECK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(String value) {
            addCriterion("CHECK_STATUS =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(String value) {
            addCriterion("CHECK_STATUS <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(String value) {
            addCriterion("CHECK_STATUS >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(String value) {
            addCriterion("CHECK_STATUS <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLike(String value) {
            addCriterion("CHECK_STATUS like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotLike(String value) {
            addCriterion("CHECK_STATUS not like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<String> values) {
            addCriterion("CHECK_STATUS in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<String> values) {
            addCriterion("CHECK_STATUS not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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