package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class GateBankCheckLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected java.util.List<Criteria> oredCriteria;

    public GateBankCheckLogExample() {
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

    public java.util.List<Criteria> getOredCriteria() {
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
        protected java.util.List<Criterion> criteria;

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

        public java.util.List<Criterion> getCriteria() {
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

        public Criteria andGateIdIn(java.util.List<java.lang.String> values) {
            addCriterion("GATE_ID in", values, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotIn(java.util.List<java.lang.String> values) {
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

        public Criteria andBankCheckDateIsNull() {
            addCriterion("BANK_CHECK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateIsNotNull() {
            addCriterion("BANK_CHECK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateEqualTo(String value) {
            addCriterion("BANK_CHECK_DATE =", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotEqualTo(String value) {
            addCriterion("BANK_CHECK_DATE <>", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateGreaterThan(String value) {
            addCriterion("BANK_CHECK_DATE >", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CHECK_DATE >=", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateLessThan(String value) {
            addCriterion("BANK_CHECK_DATE <", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateLessThanOrEqualTo(String value) {
            addCriterion("BANK_CHECK_DATE <=", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateLike(String value) {
            addCriterion("BANK_CHECK_DATE like", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotLike(String value) {
            addCriterion("BANK_CHECK_DATE not like", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateIn(java.util.List<java.lang.String> values) {
            addCriterion("BANK_CHECK_DATE in", values, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotIn(java.util.List<java.lang.String> values) {
            addCriterion("BANK_CHECK_DATE not in", values, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateBetween(String value1, String value2) {
            addCriterion("BANK_CHECK_DATE between", value1, value2, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotBetween(String value1, String value2) {
            addCriterion("BANK_CHECK_DATE not between", value1, value2, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultIsNull() {
            addCriterion("BANK_CHECK_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultIsNotNull() {
            addCriterion("BANK_CHECK_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultEqualTo(String value) {
            addCriterion("BANK_CHECK_RESULT =", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultNotEqualTo(String value) {
            addCriterion("BANK_CHECK_RESULT <>", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultGreaterThan(String value) {
            addCriterion("BANK_CHECK_RESULT >", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CHECK_RESULT >=", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultLessThan(String value) {
            addCriterion("BANK_CHECK_RESULT <", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultLessThanOrEqualTo(String value) {
            addCriterion("BANK_CHECK_RESULT <=", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultLike(String value) {
            addCriterion("BANK_CHECK_RESULT like", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultNotLike(String value) {
            addCriterion("BANK_CHECK_RESULT not like", value, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultIn(java.util.List<java.lang.String> values) {
            addCriterion("BANK_CHECK_RESULT in", values, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultNotIn(java.util.List<java.lang.String> values) {
            addCriterion("BANK_CHECK_RESULT not in", values, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultBetween(String value1, String value2) {
            addCriterion("BANK_CHECK_RESULT between", value1, value2, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckResultNotBetween(String value1, String value2) {
            addCriterion("BANK_CHECK_RESULT not between", value1, value2, "bankCheckResult");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesIsNull() {
            addCriterion("BANK_CHECK_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesIsNotNull() {
            addCriterion("BANK_CHECK_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesEqualTo(String value) {
            addCriterion("BANK_CHECK_TIMES =", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesNotEqualTo(String value) {
            addCriterion("BANK_CHECK_TIMES <>", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesGreaterThan(String value) {
            addCriterion("BANK_CHECK_TIMES >", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CHECK_TIMES >=", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesLessThan(String value) {
            addCriterion("BANK_CHECK_TIMES <", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesLessThanOrEqualTo(String value) {
            addCriterion("BANK_CHECK_TIMES <=", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesLike(String value) {
            addCriterion("BANK_CHECK_TIMES like", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesNotLike(String value) {
            addCriterion("BANK_CHECK_TIMES not like", value, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesIn(java.util.List<java.lang.String> values) {
            addCriterion("BANK_CHECK_TIMES in", values, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesNotIn(java.util.List<java.lang.String> values) {
            addCriterion("BANK_CHECK_TIMES not in", values, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesBetween(String value1, String value2) {
            addCriterion("BANK_CHECK_TIMES between", value1, value2, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andBankCheckTimesNotBetween(String value1, String value2) {
            addCriterion("BANK_CHECK_TIMES not between", value1, value2, "bankCheckTimes");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeIsNull() {
            addCriterion("LAST_BANK_CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeIsNotNull() {
            addCriterion("LAST_BANK_CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeEqualTo(String value) {
            addCriterion("LAST_BANK_CHECK_TIME =", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeNotEqualTo(String value) {
            addCriterion("LAST_BANK_CHECK_TIME <>", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeGreaterThan(String value) {
            addCriterion("LAST_BANK_CHECK_TIME >", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_BANK_CHECK_TIME >=", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeLessThan(String value) {
            addCriterion("LAST_BANK_CHECK_TIME <", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeLessThanOrEqualTo(String value) {
            addCriterion("LAST_BANK_CHECK_TIME <=", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeLike(String value) {
            addCriterion("LAST_BANK_CHECK_TIME like", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeNotLike(String value) {
            addCriterion("LAST_BANK_CHECK_TIME not like", value, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeIn(java.util.List<java.lang.String> values) {
            addCriterion("LAST_BANK_CHECK_TIME in", values, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeNotIn(java.util.List<java.lang.String> values) {
            addCriterion("LAST_BANK_CHECK_TIME not in", values, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeBetween(String value1, String value2) {
            addCriterion("LAST_BANK_CHECK_TIME between", value1, value2, "lastBankCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastBankCheckTimeNotBetween(String value1, String value2) {
            addCriterion("LAST_BANK_CHECK_TIME not between", value1, value2, "lastBankCheckTime");
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