package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblParamInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblParamInfoExample() {
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

        public Criteria andParamNameIsNull() {
            addCriterion("PARAM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNotNull() {
            addCriterion("PARAM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParamNameEqualTo(String value) {
            addCriterion("PARAM_NAME =", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotEqualTo(String value) {
            addCriterion("PARAM_NAME <>", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThan(String value) {
            addCriterion("PARAM_NAME >", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME >=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThan(String value) {
            addCriterion("PARAM_NAME <", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME <=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLike(String value) {
            addCriterion("PARAM_NAME like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotLike(String value) {
            addCriterion("PARAM_NAME not like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameIn(List<String> values) {
            addCriterion("PARAM_NAME in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotIn(List<String> values) {
            addCriterion("PARAM_NAME not in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameBetween(String value1, String value2) {
            addCriterion("PARAM_NAME between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotBetween(String value1, String value2) {
            addCriterion("PARAM_NAME not between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNull() {
            addCriterion("PARAM_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNotNull() {
            addCriterion("PARAM_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andParamValueEqualTo(String value) {
            addCriterion("PARAM_VALUE =", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotEqualTo(String value) {
            addCriterion("PARAM_VALUE <>", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThan(String value) {
            addCriterion("PARAM_VALUE >", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_VALUE >=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThan(String value) {
            addCriterion("PARAM_VALUE <", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThanOrEqualTo(String value) {
            addCriterion("PARAM_VALUE <=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLike(String value) {
            addCriterion("PARAM_VALUE like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotLike(String value) {
            addCriterion("PARAM_VALUE not like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueIn(List<String> values) {
            addCriterion("PARAM_VALUE in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotIn(List<String> values) {
            addCriterion("PARAM_VALUE not in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueBetween(String value1, String value2) {
            addCriterion("PARAM_VALUE between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotBetween(String value1, String value2) {
            addCriterion("PARAM_VALUE not between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamStatIsNull() {
            addCriterion("PARAM_STAT is null");
            return (Criteria) this;
        }

        public Criteria andParamStatIsNotNull() {
            addCriterion("PARAM_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andParamStatEqualTo(String value) {
            addCriterion("PARAM_STAT =", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatNotEqualTo(String value) {
            addCriterion("PARAM_STAT <>", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatGreaterThan(String value) {
            addCriterion("PARAM_STAT >", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_STAT >=", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatLessThan(String value) {
            addCriterion("PARAM_STAT <", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatLessThanOrEqualTo(String value) {
            addCriterion("PARAM_STAT <=", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatLike(String value) {
            addCriterion("PARAM_STAT like", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatNotLike(String value) {
            addCriterion("PARAM_STAT not like", value, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatIn(List<String> values) {
            addCriterion("PARAM_STAT in", values, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatNotIn(List<String> values) {
            addCriterion("PARAM_STAT not in", values, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatBetween(String value1, String value2) {
            addCriterion("PARAM_STAT between", value1, value2, "paramStat");
            return (Criteria) this;
        }

        public Criteria andParamStatNotBetween(String value1, String value2) {
            addCriterion("PARAM_STAT not between", value1, value2, "paramStat");
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