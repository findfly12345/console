package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblInstKeyInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblInstKeyInfoExample() {
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

        public Criteria andInstCodeIsNull() {
            addCriterion("INST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInstCodeIsNotNull() {
            addCriterion("INST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInstCodeEqualTo(String value) {
            addCriterion("INST_CODE =", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotEqualTo(String value) {
            addCriterion("INST_CODE <>", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeGreaterThan(String value) {
            addCriterion("INST_CODE >", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeGreaterThanOrEqualTo(String value) {
            addCriterion("INST_CODE >=", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLessThan(String value) {
            addCriterion("INST_CODE <", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLessThanOrEqualTo(String value) {
            addCriterion("INST_CODE <=", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLike(String value) {
            addCriterion("INST_CODE like", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotLike(String value) {
            addCriterion("INST_CODE not like", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeIn(List<String> values) {
            addCriterion("INST_CODE in", values, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotIn(List<String> values) {
            addCriterion("INST_CODE not in", values, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeBetween(String value1, String value2) {
            addCriterion("INST_CODE between", value1, value2, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotBetween(String value1, String value2) {
            addCriterion("INST_CODE not between", value1, value2, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyIsNull() {
            addCriterion("INST_MAIN_KEY is null");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyIsNotNull() {
            addCriterion("INST_MAIN_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyEqualTo(String value) {
            addCriterion("INST_MAIN_KEY =", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyNotEqualTo(String value) {
            addCriterion("INST_MAIN_KEY <>", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyGreaterThan(String value) {
            addCriterion("INST_MAIN_KEY >", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MAIN_KEY >=", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyLessThan(String value) {
            addCriterion("INST_MAIN_KEY <", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyLessThanOrEqualTo(String value) {
            addCriterion("INST_MAIN_KEY <=", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyLike(String value) {
            addCriterion("INST_MAIN_KEY like", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyNotLike(String value) {
            addCriterion("INST_MAIN_KEY not like", value, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyIn(List<String> values) {
            addCriterion("INST_MAIN_KEY in", values, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyNotIn(List<String> values) {
            addCriterion("INST_MAIN_KEY not in", values, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyBetween(String value1, String value2) {
            addCriterion("INST_MAIN_KEY between", value1, value2, "instMainKey");
            return (Criteria) this;
        }

        public Criteria andInstMainKeyNotBetween(String value1, String value2) {
            addCriterion("INST_MAIN_KEY not between", value1, value2, "instMainKey");
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

        public Criteria andLoginStatIsNull() {
            addCriterion("LOGIN_STAT is null");
            return (Criteria) this;
        }

        public Criteria andLoginStatIsNotNull() {
            addCriterion("LOGIN_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andLoginStatEqualTo(String value) {
            addCriterion("LOGIN_STAT =", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatNotEqualTo(String value) {
            addCriterion("LOGIN_STAT <>", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatGreaterThan(String value) {
            addCriterion("LOGIN_STAT >", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_STAT >=", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatLessThan(String value) {
            addCriterion("LOGIN_STAT <", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_STAT <=", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatLike(String value) {
            addCriterion("LOGIN_STAT like", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatNotLike(String value) {
            addCriterion("LOGIN_STAT not like", value, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatIn(List<String> values) {
            addCriterion("LOGIN_STAT in", values, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatNotIn(List<String> values) {
            addCriterion("LOGIN_STAT not in", values, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatBetween(String value1, String value2) {
            addCriterion("LOGIN_STAT between", value1, value2, "loginStat");
            return (Criteria) this;
        }

        public Criteria andLoginStatNotBetween(String value1, String value2) {
            addCriterion("LOGIN_STAT not between", value1, value2, "loginStat");
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