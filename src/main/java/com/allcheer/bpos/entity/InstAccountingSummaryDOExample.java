package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class InstAccountingSummaryDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstAccountingSummaryDOExample() {
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

        public Criteria andAccountingDateIsNull() {
            addCriterion("ACCOUNTING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAccountingDateIsNotNull() {
            addCriterion("ACCOUNTING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAccountingDateEqualTo(String value) {
            addCriterion("ACCOUNTING_DATE =", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateNotEqualTo(String value) {
            addCriterion("ACCOUNTING_DATE <>", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateGreaterThan(String value) {
            addCriterion("ACCOUNTING_DATE >", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNTING_DATE >=", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateLessThan(String value) {
            addCriterion("ACCOUNTING_DATE <", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNTING_DATE <=", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateLike(String value) {
            addCriterion("ACCOUNTING_DATE like", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateNotLike(String value) {
            addCriterion("ACCOUNTING_DATE not like", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateIn(List<String> values) {
            addCriterion("ACCOUNTING_DATE in", values, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateNotIn(List<String> values) {
            addCriterion("ACCOUNTING_DATE not in", values, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateBetween(String value1, String value2) {
            addCriterion("ACCOUNTING_DATE between", value1, value2, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateNotBetween(String value1, String value2) {
            addCriterion("ACCOUNTING_DATE not between", value1, value2, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtIsNull() {
            addCriterion("TRANS_SUM_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtIsNotNull() {
            addCriterion("TRANS_SUM_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtEqualTo(String value) {
            addCriterion("TRANS_SUM_AMT =", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtNotEqualTo(String value) {
            addCriterion("TRANS_SUM_AMT <>", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtGreaterThan(String value) {
            addCriterion("TRANS_SUM_AMT >", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_SUM_AMT >=", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtLessThan(String value) {
            addCriterion("TRANS_SUM_AMT <", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtLessThanOrEqualTo(String value) {
            addCriterion("TRANS_SUM_AMT <=", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtLike(String value) {
            addCriterion("TRANS_SUM_AMT like", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtNotLike(String value) {
            addCriterion("TRANS_SUM_AMT not like", value, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtIn(List<String> values) {
            addCriterion("TRANS_SUM_AMT in", values, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtNotIn(List<String> values) {
            addCriterion("TRANS_SUM_AMT not in", values, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtBetween(String value1, String value2) {
            addCriterion("TRANS_SUM_AMT between", value1, value2, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransSumAmtNotBetween(String value1, String value2) {
            addCriterion("TRANS_SUM_AMT not between", value1, value2, "transSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtIsNull() {
            addCriterion("TRANS_FEE_SUM_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtIsNotNull() {
            addCriterion("TRANS_FEE_SUM_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtEqualTo(String value) {
            addCriterion("TRANS_FEE_SUM_AMT =", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtNotEqualTo(String value) {
            addCriterion("TRANS_FEE_SUM_AMT <>", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtGreaterThan(String value) {
            addCriterion("TRANS_FEE_SUM_AMT >", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_FEE_SUM_AMT >=", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtLessThan(String value) {
            addCriterion("TRANS_FEE_SUM_AMT <", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtLessThanOrEqualTo(String value) {
            addCriterion("TRANS_FEE_SUM_AMT <=", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtLike(String value) {
            addCriterion("TRANS_FEE_SUM_AMT like", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtNotLike(String value) {
            addCriterion("TRANS_FEE_SUM_AMT not like", value, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtIn(List<String> values) {
            addCriterion("TRANS_FEE_SUM_AMT in", values, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtNotIn(List<String> values) {
            addCriterion("TRANS_FEE_SUM_AMT not in", values, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtBetween(String value1, String value2) {
            addCriterion("TRANS_FEE_SUM_AMT between", value1, value2, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransFeeSumAmtNotBetween(String value1, String value2) {
            addCriterion("TRANS_FEE_SUM_AMT not between", value1, value2, "transFeeSumAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtIsNull() {
            addCriterion("TRANS_REF_FEE_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtIsNotNull() {
            addCriterion("TRANS_REF_FEE_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtEqualTo(String value) {
            addCriterion("TRANS_REF_FEE_AMT =", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtNotEqualTo(String value) {
            addCriterion("TRANS_REF_FEE_AMT <>", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtGreaterThan(String value) {
            addCriterion("TRANS_REF_FEE_AMT >", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_REF_FEE_AMT >=", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtLessThan(String value) {
            addCriterion("TRANS_REF_FEE_AMT <", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtLessThanOrEqualTo(String value) {
            addCriterion("TRANS_REF_FEE_AMT <=", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtLike(String value) {
            addCriterion("TRANS_REF_FEE_AMT like", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtNotLike(String value) {
            addCriterion("TRANS_REF_FEE_AMT not like", value, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtIn(List<String> values) {
            addCriterion("TRANS_REF_FEE_AMT in", values, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtNotIn(List<String> values) {
            addCriterion("TRANS_REF_FEE_AMT not in", values, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtBetween(String value1, String value2) {
            addCriterion("TRANS_REF_FEE_AMT between", value1, value2, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransRefFeeAmtNotBetween(String value1, String value2) {
            addCriterion("TRANS_REF_FEE_AMT not between", value1, value2, "transRefFeeAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtIsNull() {
            addCriterion("TRANS_CLEAR_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtIsNotNull() {
            addCriterion("TRANS_CLEAR_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtEqualTo(String value) {
            addCriterion("TRANS_CLEAR_AMT =", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtNotEqualTo(String value) {
            addCriterion("TRANS_CLEAR_AMT <>", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtGreaterThan(String value) {
            addCriterion("TRANS_CLEAR_AMT >", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_CLEAR_AMT >=", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtLessThan(String value) {
            addCriterion("TRANS_CLEAR_AMT <", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtLessThanOrEqualTo(String value) {
            addCriterion("TRANS_CLEAR_AMT <=", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtLike(String value) {
            addCriterion("TRANS_CLEAR_AMT like", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtNotLike(String value) {
            addCriterion("TRANS_CLEAR_AMT not like", value, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtIn(List<String> values) {
            addCriterion("TRANS_CLEAR_AMT in", values, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtNotIn(List<String> values) {
            addCriterion("TRANS_CLEAR_AMT not in", values, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtBetween(String value1, String value2) {
            addCriterion("TRANS_CLEAR_AMT between", value1, value2, "transClearAmt");
            return (Criteria) this;
        }

        public Criteria andTransClearAmtNotBetween(String value1, String value2) {
            addCriterion("TRANS_CLEAR_AMT not between", value1, value2, "transClearAmt");
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