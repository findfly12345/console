package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsGateRoutDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsGateRoutDOExample() {
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

        public Criteria andLineIpIsNull() {
            addCriterion("LINE_IP is null");
            return (Criteria) this;
        }

        public Criteria andLineIpIsNotNull() {
            addCriterion("LINE_IP is not null");
            return (Criteria) this;
        }

        public Criteria andLineIpEqualTo(String value) {
            addCriterion("LINE_IP =", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpNotEqualTo(String value) {
            addCriterion("LINE_IP <>", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpGreaterThan(String value) {
            addCriterion("LINE_IP >", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpGreaterThanOrEqualTo(String value) {
            addCriterion("LINE_IP >=", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpLessThan(String value) {
            addCriterion("LINE_IP <", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpLessThanOrEqualTo(String value) {
            addCriterion("LINE_IP <=", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpLike(String value) {
            addCriterion("LINE_IP like", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpNotLike(String value) {
            addCriterion("LINE_IP not like", value, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpIn(List<String> values) {
            addCriterion("LINE_IP in", values, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpNotIn(List<String> values) {
            addCriterion("LINE_IP not in", values, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpBetween(String value1, String value2) {
            addCriterion("LINE_IP between", value1, value2, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLineIpNotBetween(String value1, String value2) {
            addCriterion("LINE_IP not between", value1, value2, "lineIp");
            return (Criteria) this;
        }

        public Criteria andLinePortIsNull() {
            addCriterion("LINE_PORT is null");
            return (Criteria) this;
        }

        public Criteria andLinePortIsNotNull() {
            addCriterion("LINE_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andLinePortEqualTo(Integer value) {
            addCriterion("LINE_PORT =", value, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortNotEqualTo(Integer value) {
            addCriterion("LINE_PORT <>", value, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortGreaterThan(Integer value) {
            addCriterion("LINE_PORT >", value, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortGreaterThanOrEqualTo(Integer value) {
            addCriterion("LINE_PORT >=", value, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortLessThan(Integer value) {
            addCriterion("LINE_PORT <", value, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortLessThanOrEqualTo(Integer value) {
            addCriterion("LINE_PORT <=", value, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortIn(List<Integer> values) {
            addCriterion("LINE_PORT in", values, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortNotIn(List<Integer> values) {
            addCriterion("LINE_PORT not in", values, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortBetween(Integer value1, Integer value2) {
            addCriterion("LINE_PORT between", value1, value2, "linePort");
            return (Criteria) this;
        }

        public Criteria andLinePortNotBetween(Integer value1, Integer value2) {
            addCriterion("LINE_PORT not between", value1, value2, "linePort");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNull() {
            addCriterion("LINE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNotNull() {
            addCriterion("LINE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLineNameEqualTo(String value) {
            addCriterion("LINE_NAME =", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotEqualTo(String value) {
            addCriterion("LINE_NAME <>", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThan(String value) {
            addCriterion("LINE_NAME >", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThanOrEqualTo(String value) {
            addCriterion("LINE_NAME >=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThan(String value) {
            addCriterion("LINE_NAME <", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThanOrEqualTo(String value) {
            addCriterion("LINE_NAME <=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLike(String value) {
            addCriterion("LINE_NAME like", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotLike(String value) {
            addCriterion("LINE_NAME not like", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameIn(List<String> values) {
            addCriterion("LINE_NAME in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotIn(List<String> values) {
            addCriterion("LINE_NAME not in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameBetween(String value1, String value2) {
            addCriterion("LINE_NAME between", value1, value2, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotBetween(String value1, String value2) {
            addCriterion("LINE_NAME not between", value1, value2, "lineName");
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