package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblFeeInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblFeeInfoDOExample() {
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

        public Criteria andGateIdIsNull() {
            addCriterion("GATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGateIdIsNotNull() {
            addCriterion("GATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGateIdEqualTo(String value) {
            addCriterion("GATE_ID =", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotEqualTo(String value) {
            addCriterion("GATE_ID <>", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdGreaterThan(String value) {
            addCriterion("GATE_ID >", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdGreaterThanOrEqualTo(String value) {
            addCriterion("GATE_ID >=", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdLessThan(String value) {
            addCriterion("GATE_ID <", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdLessThanOrEqualTo(String value) {
            addCriterion("GATE_ID <=", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdLike(String value) {
            addCriterion("GATE_ID like", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotLike(String value) {
            addCriterion("GATE_ID not like", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdIn(List<String> values) {
            addCriterion("GATE_ID in", values, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotIn(List<String> values) {
            addCriterion("GATE_ID not in", values, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdBetween(String value1, String value2) {
            addCriterion("GATE_ID between", value1, value2, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotBetween(String value1, String value2) {
            addCriterion("GATE_ID not between", value1, value2, "gateId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdIsNull() {
            addCriterion("POS_MER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPosMerIdIsNotNull() {
            addCriterion("POS_MER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPosMerIdEqualTo(String value) {
            addCriterion("POS_MER_ID =", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdNotEqualTo(String value) {
            addCriterion("POS_MER_ID <>", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdGreaterThan(String value) {
            addCriterion("POS_MER_ID >", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("POS_MER_ID >=", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdLessThan(String value) {
            addCriterion("POS_MER_ID <", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdLessThanOrEqualTo(String value) {
            addCriterion("POS_MER_ID <=", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdLike(String value) {
            addCriterion("POS_MER_ID like", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdNotLike(String value) {
            addCriterion("POS_MER_ID not like", value, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdIn(List<String> values) {
            addCriterion("POS_MER_ID in", values, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdNotIn(List<String> values) {
            addCriterion("POS_MER_ID not in", values, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdBetween(String value1, String value2) {
            addCriterion("POS_MER_ID between", value1, value2, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosMerIdNotBetween(String value1, String value2) {
            addCriterion("POS_MER_ID not between", value1, value2, "posMerId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdIsNull() {
            addCriterion("POS_TERM_ID is null");
            return (Criteria) this;
        }

        public Criteria andPosTermIdIsNotNull() {
            addCriterion("POS_TERM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPosTermIdEqualTo(String value) {
            addCriterion("POS_TERM_ID =", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdNotEqualTo(String value) {
            addCriterion("POS_TERM_ID <>", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdGreaterThan(String value) {
            addCriterion("POS_TERM_ID >", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdGreaterThanOrEqualTo(String value) {
            addCriterion("POS_TERM_ID >=", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdLessThan(String value) {
            addCriterion("POS_TERM_ID <", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdLessThanOrEqualTo(String value) {
            addCriterion("POS_TERM_ID <=", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdLike(String value) {
            addCriterion("POS_TERM_ID like", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdNotLike(String value) {
            addCriterion("POS_TERM_ID not like", value, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdIn(List<String> values) {
            addCriterion("POS_TERM_ID in", values, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdNotIn(List<String> values) {
            addCriterion("POS_TERM_ID not in", values, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdBetween(String value1, String value2) {
            addCriterion("POS_TERM_ID between", value1, value2, "posTermId");
            return (Criteria) this;
        }

        public Criteria andPosTermIdNotBetween(String value1, String value2) {
            addCriterion("POS_TERM_ID not between", value1, value2, "posTermId");
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