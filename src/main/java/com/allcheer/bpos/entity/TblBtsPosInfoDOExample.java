package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsPosInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsPosInfoDOExample() {
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

        public Criteria andBatchIdIsNull() {
            addCriterion("BATCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("BATCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(String value) {
            addCriterion("BATCH_ID =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(String value) {
            addCriterion("BATCH_ID <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(String value) {
            addCriterion("BATCH_ID >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_ID >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(String value) {
            addCriterion("BATCH_ID <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(String value) {
            addCriterion("BATCH_ID <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLike(String value) {
            addCriterion("BATCH_ID like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotLike(String value) {
            addCriterion("BATCH_ID not like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<String> values) {
            addCriterion("BATCH_ID in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<String> values) {
            addCriterion("BATCH_ID not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(String value1, String value2) {
            addCriterion("BATCH_ID between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(String value1, String value2) {
            addCriterion("BATCH_ID not between", value1, value2, "batchId");
            return (Criteria) this;
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

        public Criteria andInstNameIsNull() {
            addCriterion("INST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInstNameIsNotNull() {
            addCriterion("INST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInstNameEqualTo(String value) {
            addCriterion("INST_NAME =", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotEqualTo(String value) {
            addCriterion("INST_NAME <>", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameGreaterThan(String value) {
            addCriterion("INST_NAME >", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameGreaterThanOrEqualTo(String value) {
            addCriterion("INST_NAME >=", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameLessThan(String value) {
            addCriterion("INST_NAME <", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameLessThanOrEqualTo(String value) {
            addCriterion("INST_NAME <=", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameLike(String value) {
            addCriterion("INST_NAME like", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotLike(String value) {
            addCriterion("INST_NAME not like", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameIn(List<String> values) {
            addCriterion("INST_NAME in", values, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotIn(List<String> values) {
            addCriterion("INST_NAME not in", values, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameBetween(String value1, String value2) {
            addCriterion("INST_NAME between", value1, value2, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotBetween(String value1, String value2) {
            addCriterion("INST_NAME not between", value1, value2, "instName");
            return (Criteria) this;
        }

        public Criteria andMainKeyIsNull() {
            addCriterion("MAIN_KEY is null");
            return (Criteria) this;
        }

        public Criteria andMainKeyIsNotNull() {
            addCriterion("MAIN_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andMainKeyEqualTo(String value) {
            addCriterion("MAIN_KEY =", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyNotEqualTo(String value) {
            addCriterion("MAIN_KEY <>", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyGreaterThan(String value) {
            addCriterion("MAIN_KEY >", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_KEY >=", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyLessThan(String value) {
            addCriterion("MAIN_KEY <", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyLessThanOrEqualTo(String value) {
            addCriterion("MAIN_KEY <=", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyLike(String value) {
            addCriterion("MAIN_KEY like", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyNotLike(String value) {
            addCriterion("MAIN_KEY not like", value, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyIn(List<String> values) {
            addCriterion("MAIN_KEY in", values, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyNotIn(List<String> values) {
            addCriterion("MAIN_KEY not in", values, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyBetween(String value1, String value2) {
            addCriterion("MAIN_KEY between", value1, value2, "mainKey");
            return (Criteria) this;
        }

        public Criteria andMainKeyNotBetween(String value1, String value2) {
            addCriterion("MAIN_KEY not between", value1, value2, "mainKey");
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