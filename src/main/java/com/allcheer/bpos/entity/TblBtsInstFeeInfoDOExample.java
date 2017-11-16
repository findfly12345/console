package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsInstFeeInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsInstFeeInfoDOExample() {
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

        public Criteria andInstTermIdIsNull() {
            addCriterion("INST_TERM_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstTermIdIsNotNull() {
            addCriterion("INST_TERM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstTermIdEqualTo(String value) {
            addCriterion("INST_TERM_ID =", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotEqualTo(String value) {
            addCriterion("INST_TERM_ID <>", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdGreaterThan(String value) {
            addCriterion("INST_TERM_ID >", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_TERM_ID >=", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdLessThan(String value) {
            addCriterion("INST_TERM_ID <", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdLessThanOrEqualTo(String value) {
            addCriterion("INST_TERM_ID <=", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdLike(String value) {
            addCriterion("INST_TERM_ID like", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotLike(String value) {
            addCriterion("INST_TERM_ID not like", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdIn(List<String> values) {
            addCriterion("INST_TERM_ID in", values, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotIn(List<String> values) {
            addCriterion("INST_TERM_ID not in", values, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdBetween(String value1, String value2) {
            addCriterion("INST_TERM_ID between", value1, value2, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotBetween(String value1, String value2) {
            addCriterion("INST_TERM_ID not between", value1, value2, "instTermId");
            return (Criteria) this;
        }

        public Criteria andCalcModeIsNull() {
            addCriterion("CALC_MODE is null");
            return (Criteria) this;
        }

        public Criteria andCalcModeIsNotNull() {
            addCriterion("CALC_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andCalcModeEqualTo(String value) {
            addCriterion("CALC_MODE =", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotEqualTo(String value) {
            addCriterion("CALC_MODE <>", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeGreaterThan(String value) {
            addCriterion("CALC_MODE >", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeGreaterThanOrEqualTo(String value) {
            addCriterion("CALC_MODE >=", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeLessThan(String value) {
            addCriterion("CALC_MODE <", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeLessThanOrEqualTo(String value) {
            addCriterion("CALC_MODE <=", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeLike(String value) {
            addCriterion("CALC_MODE like", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotLike(String value) {
            addCriterion("CALC_MODE not like", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeIn(List<String> values) {
            addCriterion("CALC_MODE in", values, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotIn(List<String> values) {
            addCriterion("CALC_MODE not in", values, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeBetween(String value1, String value2) {
            addCriterion("CALC_MODE between", value1, value2, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotBetween(String value1, String value2) {
            addCriterion("CALC_MODE not between", value1, value2, "calcMode");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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