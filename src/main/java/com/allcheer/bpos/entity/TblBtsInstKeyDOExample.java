package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsInstKeyDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsInstKeyDOExample() {
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

        public Criteria andInstPrimaryKeyIndexIsNull() {
            addCriterion("INST_PRIMARY_KEY_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexIsNotNull() {
            addCriterion("INST_PRIMARY_KEY_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX =", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexNotEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX <>", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexGreaterThan(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX >", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexGreaterThanOrEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX >=", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexLessThan(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX <", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexLessThanOrEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX <=", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexLike(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX like", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexNotLike(String value) {
            addCriterion("INST_PRIMARY_KEY_INDEX not like", value, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexIn(List<String> values) {
            addCriterion("INST_PRIMARY_KEY_INDEX in", values, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexNotIn(List<String> values) {
            addCriterion("INST_PRIMARY_KEY_INDEX not in", values, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexBetween(String value1, String value2) {
            addCriterion("INST_PRIMARY_KEY_INDEX between", value1, value2, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIndexNotBetween(String value1, String value2) {
            addCriterion("INST_PRIMARY_KEY_INDEX not between", value1, value2, "instPrimaryKeyIndex");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIsNull() {
            addCriterion("INST_PRIMARY_KEY is null");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIsNotNull() {
            addCriterion("INST_PRIMARY_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY =", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyNotEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY <>", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyGreaterThan(String value) {
            addCriterion("INST_PRIMARY_KEY >", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyGreaterThanOrEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY >=", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyLessThan(String value) {
            addCriterion("INST_PRIMARY_KEY <", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyLessThanOrEqualTo(String value) {
            addCriterion("INST_PRIMARY_KEY <=", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyLike(String value) {
            addCriterion("INST_PRIMARY_KEY like", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyNotLike(String value) {
            addCriterion("INST_PRIMARY_KEY not like", value, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyIn(List<String> values) {
            addCriterion("INST_PRIMARY_KEY in", values, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyNotIn(List<String> values) {
            addCriterion("INST_PRIMARY_KEY not in", values, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyBetween(String value1, String value2) {
            addCriterion("INST_PRIMARY_KEY between", value1, value2, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPrimaryKeyNotBetween(String value1, String value2) {
            addCriterion("INST_PRIMARY_KEY not between", value1, value2, "instPrimaryKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyIsNull() {
            addCriterion("INST_PIN_KEY is null");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyIsNotNull() {
            addCriterion("INST_PIN_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyEqualTo(String value) {
            addCriterion("INST_PIN_KEY =", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyNotEqualTo(String value) {
            addCriterion("INST_PIN_KEY <>", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyGreaterThan(String value) {
            addCriterion("INST_PIN_KEY >", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyGreaterThanOrEqualTo(String value) {
            addCriterion("INST_PIN_KEY >=", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyLessThan(String value) {
            addCriterion("INST_PIN_KEY <", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyLessThanOrEqualTo(String value) {
            addCriterion("INST_PIN_KEY <=", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyLike(String value) {
            addCriterion("INST_PIN_KEY like", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyNotLike(String value) {
            addCriterion("INST_PIN_KEY not like", value, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyIn(List<String> values) {
            addCriterion("INST_PIN_KEY in", values, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyNotIn(List<String> values) {
            addCriterion("INST_PIN_KEY not in", values, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyBetween(String value1, String value2) {
            addCriterion("INST_PIN_KEY between", value1, value2, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstPinKeyNotBetween(String value1, String value2) {
            addCriterion("INST_PIN_KEY not between", value1, value2, "instPinKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyIsNull() {
            addCriterion("INST_MAC_KEY is null");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyIsNotNull() {
            addCriterion("INST_MAC_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyEqualTo(String value) {
            addCriterion("INST_MAC_KEY =", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyNotEqualTo(String value) {
            addCriterion("INST_MAC_KEY <>", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyGreaterThan(String value) {
            addCriterion("INST_MAC_KEY >", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MAC_KEY >=", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyLessThan(String value) {
            addCriterion("INST_MAC_KEY <", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyLessThanOrEqualTo(String value) {
            addCriterion("INST_MAC_KEY <=", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyLike(String value) {
            addCriterion("INST_MAC_KEY like", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyNotLike(String value) {
            addCriterion("INST_MAC_KEY not like", value, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyIn(List<String> values) {
            addCriterion("INST_MAC_KEY in", values, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyNotIn(List<String> values) {
            addCriterion("INST_MAC_KEY not in", values, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyBetween(String value1, String value2) {
            addCriterion("INST_MAC_KEY between", value1, value2, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstMacKeyNotBetween(String value1, String value2) {
            addCriterion("INST_MAC_KEY not between", value1, value2, "instMacKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyIsNull() {
            addCriterion("INST_TD_KEY is null");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyIsNotNull() {
            addCriterion("INST_TD_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyEqualTo(String value) {
            addCriterion("INST_TD_KEY =", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyNotEqualTo(String value) {
            addCriterion("INST_TD_KEY <>", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyGreaterThan(String value) {
            addCriterion("INST_TD_KEY >", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyGreaterThanOrEqualTo(String value) {
            addCriterion("INST_TD_KEY >=", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyLessThan(String value) {
            addCriterion("INST_TD_KEY <", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyLessThanOrEqualTo(String value) {
            addCriterion("INST_TD_KEY <=", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyLike(String value) {
            addCriterion("INST_TD_KEY like", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyNotLike(String value) {
            addCriterion("INST_TD_KEY not like", value, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyIn(List<String> values) {
            addCriterion("INST_TD_KEY in", values, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyNotIn(List<String> values) {
            addCriterion("INST_TD_KEY not in", values, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyBetween(String value1, String value2) {
            addCriterion("INST_TD_KEY between", value1, value2, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstTdKeyNotBetween(String value1, String value2) {
            addCriterion("INST_TD_KEY not between", value1, value2, "instTdKey");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusIsNull() {
            addCriterion("INST_SIGN_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusIsNotNull() {
            addCriterion("INST_SIGN_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusEqualTo(String value) {
            addCriterion("INST_SIGN_STATUS =", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusNotEqualTo(String value) {
            addCriterion("INST_SIGN_STATUS <>", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusGreaterThan(String value) {
            addCriterion("INST_SIGN_STATUS >", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusGreaterThanOrEqualTo(String value) {
            addCriterion("INST_SIGN_STATUS >=", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusLessThan(String value) {
            addCriterion("INST_SIGN_STATUS <", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusLessThanOrEqualTo(String value) {
            addCriterion("INST_SIGN_STATUS <=", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusLike(String value) {
            addCriterion("INST_SIGN_STATUS like", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusNotLike(String value) {
            addCriterion("INST_SIGN_STATUS not like", value, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusIn(List<String> values) {
            addCriterion("INST_SIGN_STATUS in", values, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusNotIn(List<String> values) {
            addCriterion("INST_SIGN_STATUS not in", values, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusBetween(String value1, String value2) {
            addCriterion("INST_SIGN_STATUS between", value1, value2, "instSignStatus");
            return (Criteria) this;
        }

        public Criteria andInstSignStatusNotBetween(String value1, String value2) {
            addCriterion("INST_SIGN_STATUS not between", value1, value2, "instSignStatus");
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