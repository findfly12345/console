package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblOnlineRouteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblOnlineRouteExample() {
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

        public Criteria andOnlineRouteSeqIsNull() {
            addCriterion("ONLINE_ROUTE_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqIsNotNull() {
            addCriterion("ONLINE_ROUTE_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqEqualTo(String value) {
            addCriterion("ONLINE_ROUTE_SEQ =", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqNotEqualTo(String value) {
            addCriterion("ONLINE_ROUTE_SEQ <>", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqGreaterThan(String value) {
            addCriterion("ONLINE_ROUTE_SEQ >", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqGreaterThanOrEqualTo(String value) {
            addCriterion("ONLINE_ROUTE_SEQ >=", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqLessThan(String value) {
            addCriterion("ONLINE_ROUTE_SEQ <", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqLessThanOrEqualTo(String value) {
            addCriterion("ONLINE_ROUTE_SEQ <=", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqLike(String value) {
            addCriterion("ONLINE_ROUTE_SEQ like", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqNotLike(String value) {
            addCriterion("ONLINE_ROUTE_SEQ not like", value, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqIn(List<String> values) {
            addCriterion("ONLINE_ROUTE_SEQ in", values, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqNotIn(List<String> values) {
            addCriterion("ONLINE_ROUTE_SEQ not in", values, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqBetween(String value1, String value2) {
            addCriterion("ONLINE_ROUTE_SEQ between", value1, value2, "onlineRouteSeq");
            return (Criteria) this;
        }

        public Criteria andOnlineRouteSeqNotBetween(String value1, String value2) {
            addCriterion("ONLINE_ROUTE_SEQ not between", value1, value2, "onlineRouteSeq");
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

        public Criteria andMerIdIsNull() {
            addCriterion("MER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerIdIsNotNull() {
            addCriterion("MER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerIdEqualTo(String value) {
            addCriterion("MER_ID =", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotEqualTo(String value) {
            addCriterion("MER_ID <>", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThan(String value) {
            addCriterion("MER_ID >", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("MER_ID >=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThan(String value) {
            addCriterion("MER_ID <", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThanOrEqualTo(String value) {
            addCriterion("MER_ID <=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLike(String value) {
            addCriterion("MER_ID like", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotLike(String value) {
            addCriterion("MER_ID not like", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdIn(List<String> values) {
            addCriterion("MER_ID in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotIn(List<String> values) {
            addCriterion("MER_ID not in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdBetween(String value1, String value2) {
            addCriterion("MER_ID between", value1, value2, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotBetween(String value1, String value2) {
            addCriterion("MER_ID not between", value1, value2, "merId");
            return (Criteria) this;
        }

        public Criteria andGateLevelIsNull() {
            addCriterion("GATE_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andGateLevelIsNotNull() {
            addCriterion("GATE_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andGateLevelEqualTo(String value) {
            addCriterion("GATE_LEVEL =", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelNotEqualTo(String value) {
            addCriterion("GATE_LEVEL <>", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelGreaterThan(String value) {
            addCriterion("GATE_LEVEL >", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelGreaterThanOrEqualTo(String value) {
            addCriterion("GATE_LEVEL >=", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelLessThan(String value) {
            addCriterion("GATE_LEVEL <", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelLessThanOrEqualTo(String value) {
            addCriterion("GATE_LEVEL <=", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelLike(String value) {
            addCriterion("GATE_LEVEL like", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelNotLike(String value) {
            addCriterion("GATE_LEVEL not like", value, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelIn(List<String> values) {
            addCriterion("GATE_LEVEL in", values, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelNotIn(List<String> values) {
            addCriterion("GATE_LEVEL not in", values, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelBetween(String value1, String value2) {
            addCriterion("GATE_LEVEL between", value1, value2, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andGateLevelNotBetween(String value1, String value2) {
            addCriterion("GATE_LEVEL not between", value1, value2, "gateLevel");
            return (Criteria) this;
        }

        public Criteria andDestGateIdIsNull() {
            addCriterion("DEST_GATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDestGateIdIsNotNull() {
            addCriterion("DEST_GATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDestGateIdEqualTo(String value) {
            addCriterion("DEST_GATE_ID =", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdNotEqualTo(String value) {
            addCriterion("DEST_GATE_ID <>", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdGreaterThan(String value) {
            addCriterion("DEST_GATE_ID >", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEST_GATE_ID >=", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdLessThan(String value) {
            addCriterion("DEST_GATE_ID <", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdLessThanOrEqualTo(String value) {
            addCriterion("DEST_GATE_ID <=", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdLike(String value) {
            addCriterion("DEST_GATE_ID like", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdNotLike(String value) {
            addCriterion("DEST_GATE_ID not like", value, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdIn(List<String> values) {
            addCriterion("DEST_GATE_ID in", values, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdNotIn(List<String> values) {
            addCriterion("DEST_GATE_ID not in", values, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdBetween(String value1, String value2) {
            addCriterion("DEST_GATE_ID between", value1, value2, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestGateIdNotBetween(String value1, String value2) {
            addCriterion("DEST_GATE_ID not between", value1, value2, "destGateId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdIsNull() {
            addCriterion("DEST_MER_ID is null");
            return (Criteria) this;
        }

        public Criteria andDestMerIdIsNotNull() {
            addCriterion("DEST_MER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDestMerIdEqualTo(String value) {
            addCriterion("DEST_MER_ID =", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdNotEqualTo(String value) {
            addCriterion("DEST_MER_ID <>", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdGreaterThan(String value) {
            addCriterion("DEST_MER_ID >", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEST_MER_ID >=", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdLessThan(String value) {
            addCriterion("DEST_MER_ID <", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdLessThanOrEqualTo(String value) {
            addCriterion("DEST_MER_ID <=", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdLike(String value) {
            addCriterion("DEST_MER_ID like", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdNotLike(String value) {
            addCriterion("DEST_MER_ID not like", value, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdIn(List<String> values) {
            addCriterion("DEST_MER_ID in", values, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdNotIn(List<String> values) {
            addCriterion("DEST_MER_ID not in", values, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdBetween(String value1, String value2) {
            addCriterion("DEST_MER_ID between", value1, value2, "destMerId");
            return (Criteria) this;
        }

        public Criteria andDestMerIdNotBetween(String value1, String value2) {
            addCriterion("DEST_MER_ID not between", value1, value2, "destMerId");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNull() {
            addCriterion("FEE_RATE is null");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNotNull() {
            addCriterion("FEE_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRateEqualTo(String value) {
            addCriterion("FEE_RATE =", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotEqualTo(String value) {
            addCriterion("FEE_RATE <>", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThan(String value) {
            addCriterion("FEE_RATE >", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_RATE >=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThan(String value) {
            addCriterion("FEE_RATE <", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThanOrEqualTo(String value) {
            addCriterion("FEE_RATE <=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLike(String value) {
            addCriterion("FEE_RATE like", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotLike(String value) {
            addCriterion("FEE_RATE not like", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateIn(List<String> values) {
            addCriterion("FEE_RATE in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotIn(List<String> values) {
            addCriterion("FEE_RATE not in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateBetween(String value1, String value2) {
            addCriterion("FEE_RATE between", value1, value2, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotBetween(String value1, String value2) {
            addCriterion("FEE_RATE not between", value1, value2, "feeRate");
            return (Criteria) this;
        }

        public Criteria andStatIsNull() {
            addCriterion("STAT is null");
            return (Criteria) this;
        }

        public Criteria andStatIsNotNull() {
            addCriterion("STAT is not null");
            return (Criteria) this;
        }

        public Criteria andStatEqualTo(String value) {
            addCriterion("STAT =", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotEqualTo(String value) {
            addCriterion("STAT <>", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThan(String value) {
            addCriterion("STAT >", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThanOrEqualTo(String value) {
            addCriterion("STAT >=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThan(String value) {
            addCriterion("STAT <", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThanOrEqualTo(String value) {
            addCriterion("STAT <=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLike(String value) {
            addCriterion("STAT like", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotLike(String value) {
            addCriterion("STAT not like", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatIn(List<String> values) {
            addCriterion("STAT in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotIn(List<String> values) {
            addCriterion("STAT not in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatBetween(String value1, String value2) {
            addCriterion("STAT between", value1, value2, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotBetween(String value1, String value2) {
            addCriterion("STAT not between", value1, value2, "stat");
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