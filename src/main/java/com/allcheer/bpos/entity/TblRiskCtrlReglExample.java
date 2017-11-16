package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblRiskCtrlReglExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblRiskCtrlReglExample() {
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

        public Criteria andRiskNumIsNull() {
            addCriterion("RISK_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRiskNumIsNotNull() {
            addCriterion("RISK_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRiskNumEqualTo(String value) {
            addCriterion("RISK_NUM =", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumNotEqualTo(String value) {
            addCriterion("RISK_NUM <>", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumGreaterThan(String value) {
            addCriterion("RISK_NUM >", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_NUM >=", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumLessThan(String value) {
            addCriterion("RISK_NUM <", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumLessThanOrEqualTo(String value) {
            addCriterion("RISK_NUM <=", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumLike(String value) {
            addCriterion("RISK_NUM like", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumNotLike(String value) {
            addCriterion("RISK_NUM not like", value, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumIn(List<String> values) {
            addCriterion("RISK_NUM in", values, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumNotIn(List<String> values) {
            addCriterion("RISK_NUM not in", values, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumBetween(String value1, String value2) {
            addCriterion("RISK_NUM between", value1, value2, "riskNum");
            return (Criteria) this;
        }

        public Criteria andRiskNumNotBetween(String value1, String value2) {
            addCriterion("RISK_NUM not between", value1, value2, "riskNum");
            return (Criteria) this;
        }

        public Criteria andParamNumIsNull() {
            addCriterion("PARAM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andParamNumIsNotNull() {
            addCriterion("PARAM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andParamNumEqualTo(String value) {
            addCriterion("PARAM_NUM =", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumNotEqualTo(String value) {
            addCriterion("PARAM_NUM <>", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumGreaterThan(String value) {
            addCriterion("PARAM_NUM >", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_NUM >=", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumLessThan(String value) {
            addCriterion("PARAM_NUM <", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumLessThanOrEqualTo(String value) {
            addCriterion("PARAM_NUM <=", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumLike(String value) {
            addCriterion("PARAM_NUM like", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumNotLike(String value) {
            addCriterion("PARAM_NUM not like", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumIn(List<String> values) {
            addCriterion("PARAM_NUM in", values, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumNotIn(List<String> values) {
            addCriterion("PARAM_NUM not in", values, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumBetween(String value1, String value2) {
            addCriterion("PARAM_NUM between", value1, value2, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumNotBetween(String value1, String value2) {
            addCriterion("PARAM_NUM not between", value1, value2, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamValIsNull() {
            addCriterion("PARAM_VAL is null");
            return (Criteria) this;
        }

        public Criteria andParamValIsNotNull() {
            addCriterion("PARAM_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andParamValEqualTo(String value) {
            addCriterion("PARAM_VAL =", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValNotEqualTo(String value) {
            addCriterion("PARAM_VAL <>", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValGreaterThan(String value) {
            addCriterion("PARAM_VAL >", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_VAL >=", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValLessThan(String value) {
            addCriterion("PARAM_VAL <", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValLessThanOrEqualTo(String value) {
            addCriterion("PARAM_VAL <=", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValLike(String value) {
            addCriterion("PARAM_VAL like", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValNotLike(String value) {
            addCriterion("PARAM_VAL not like", value, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValIn(List<String> values) {
            addCriterion("PARAM_VAL in", values, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValNotIn(List<String> values) {
            addCriterion("PARAM_VAL not in", values, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValBetween(String value1, String value2) {
            addCriterion("PARAM_VAL between", value1, value2, "paramVal");
            return (Criteria) this;
        }

        public Criteria andParamValNotBetween(String value1, String value2) {
            addCriterion("PARAM_VAL not between", value1, value2, "paramVal");
            return (Criteria) this;
        }

        public Criteria andRiskFlagIsNull() {
            addCriterion("RISK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andRiskFlagIsNotNull() {
            addCriterion("RISK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andRiskFlagEqualTo(String value) {
            addCriterion("RISK_FLAG =", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagNotEqualTo(String value) {
            addCriterion("RISK_FLAG <>", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagGreaterThan(String value) {
            addCriterion("RISK_FLAG >", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_FLAG >=", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagLessThan(String value) {
            addCriterion("RISK_FLAG <", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagLessThanOrEqualTo(String value) {
            addCriterion("RISK_FLAG <=", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagLike(String value) {
            addCriterion("RISK_FLAG like", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagNotLike(String value) {
            addCriterion("RISK_FLAG not like", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagIn(List<String> values) {
            addCriterion("RISK_FLAG in", values, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagNotIn(List<String> values) {
            addCriterion("RISK_FLAG not in", values, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagBetween(String value1, String value2) {
            addCriterion("RISK_FLAG between", value1, value2, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagNotBetween(String value1, String value2) {
            addCriterion("RISK_FLAG not between", value1, value2, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andModOperIsNull() {
            addCriterion("MOD_OPER is null");
            return (Criteria) this;
        }

        public Criteria andModOperIsNotNull() {
            addCriterion("MOD_OPER is not null");
            return (Criteria) this;
        }

        public Criteria andModOperEqualTo(String value) {
            addCriterion("MOD_OPER =", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperNotEqualTo(String value) {
            addCriterion("MOD_OPER <>", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperGreaterThan(String value) {
            addCriterion("MOD_OPER >", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperGreaterThanOrEqualTo(String value) {
            addCriterion("MOD_OPER >=", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperLessThan(String value) {
            addCriterion("MOD_OPER <", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperLessThanOrEqualTo(String value) {
            addCriterion("MOD_OPER <=", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperLike(String value) {
            addCriterion("MOD_OPER like", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperNotLike(String value) {
            addCriterion("MOD_OPER not like", value, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperIn(List<String> values) {
            addCriterion("MOD_OPER in", values, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperNotIn(List<String> values) {
            addCriterion("MOD_OPER not in", values, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperBetween(String value1, String value2) {
            addCriterion("MOD_OPER between", value1, value2, "modOper");
            return (Criteria) this;
        }

        public Criteria andModOperNotBetween(String value1, String value2) {
            addCriterion("MOD_OPER not between", value1, value2, "modOper");
            return (Criteria) this;
        }

        public Criteria andModDateIsNull() {
            addCriterion("MOD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andModDateIsNotNull() {
            addCriterion("MOD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andModDateEqualTo(String value) {
            addCriterion("MOD_DATE =", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotEqualTo(String value) {
            addCriterion("MOD_DATE <>", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateGreaterThan(String value) {
            addCriterion("MOD_DATE >", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateGreaterThanOrEqualTo(String value) {
            addCriterion("MOD_DATE >=", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateLessThan(String value) {
            addCriterion("MOD_DATE <", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateLessThanOrEqualTo(String value) {
            addCriterion("MOD_DATE <=", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateLike(String value) {
            addCriterion("MOD_DATE like", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotLike(String value) {
            addCriterion("MOD_DATE not like", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateIn(List<String> values) {
            addCriterion("MOD_DATE in", values, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotIn(List<String> values) {
            addCriterion("MOD_DATE not in", values, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateBetween(String value1, String value2) {
            addCriterion("MOD_DATE between", value1, value2, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotBetween(String value1, String value2) {
            addCriterion("MOD_DATE not between", value1, value2, "modDate");
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