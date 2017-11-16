package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblGateRouteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblGateRouteExample() {
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

        public Criteria andGateTypeIsNull() {
            addCriterion("GATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGateTypeIsNotNull() {
            addCriterion("GATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGateTypeEqualTo(String value) {
            addCriterion("GATE_TYPE =", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeNotEqualTo(String value) {
            addCriterion("GATE_TYPE <>", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeGreaterThan(String value) {
            addCriterion("GATE_TYPE >", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GATE_TYPE >=", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeLessThan(String value) {
            addCriterion("GATE_TYPE <", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeLessThanOrEqualTo(String value) {
            addCriterion("GATE_TYPE <=", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeLike(String value) {
            addCriterion("GATE_TYPE like", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeNotLike(String value) {
            addCriterion("GATE_TYPE not like", value, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeIn(List<String> values) {
            addCriterion("GATE_TYPE in", values, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeNotIn(List<String> values) {
            addCriterion("GATE_TYPE not in", values, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeBetween(String value1, String value2) {
            addCriterion("GATE_TYPE between", value1, value2, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateTypeNotBetween(String value1, String value2) {
            addCriterion("GATE_TYPE not between", value1, value2, "gateType");
            return (Criteria) this;
        }

        public Criteria andGateDespIsNull() {
            addCriterion("GATE_DESP is null");
            return (Criteria) this;
        }

        public Criteria andGateDespIsNotNull() {
            addCriterion("GATE_DESP is not null");
            return (Criteria) this;
        }

        public Criteria andGateDespEqualTo(String value) {
            addCriterion("GATE_DESP =", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespNotEqualTo(String value) {
            addCriterion("GATE_DESP <>", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespGreaterThan(String value) {
            addCriterion("GATE_DESP >", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespGreaterThanOrEqualTo(String value) {
            addCriterion("GATE_DESP >=", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespLessThan(String value) {
            addCriterion("GATE_DESP <", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespLessThanOrEqualTo(String value) {
            addCriterion("GATE_DESP <=", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespLike(String value) {
            addCriterion("GATE_DESP like", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespNotLike(String value) {
            addCriterion("GATE_DESP not like", value, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespIn(List<String> values) {
            addCriterion("GATE_DESP in", values, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespNotIn(List<String> values) {
            addCriterion("GATE_DESP not in", values, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespBetween(String value1, String value2) {
            addCriterion("GATE_DESP between", value1, value2, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateDespNotBetween(String value1, String value2) {
            addCriterion("GATE_DESP not between", value1, value2, "gateDesp");
            return (Criteria) this;
        }

        public Criteria andGateFeeIsNull() {
            addCriterion("GATE_FEE is null");
            return (Criteria) this;
        }

        public Criteria andGateFeeIsNotNull() {
            addCriterion("GATE_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andGateFeeEqualTo(String value) {
            addCriterion("GATE_FEE =", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeNotEqualTo(String value) {
            addCriterion("GATE_FEE <>", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeGreaterThan(String value) {
            addCriterion("GATE_FEE >", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeGreaterThanOrEqualTo(String value) {
            addCriterion("GATE_FEE >=", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeLessThan(String value) {
            addCriterion("GATE_FEE <", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeLessThanOrEqualTo(String value) {
            addCriterion("GATE_FEE <=", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeLike(String value) {
            addCriterion("GATE_FEE like", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeNotLike(String value) {
            addCriterion("GATE_FEE not like", value, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeIn(List<String> values) {
            addCriterion("GATE_FEE in", values, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeNotIn(List<String> values) {
            addCriterion("GATE_FEE not in", values, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeBetween(String value1, String value2) {
            addCriterion("GATE_FEE between", value1, value2, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateFeeNotBetween(String value1, String value2) {
            addCriterion("GATE_FEE not between", value1, value2, "gateFee");
            return (Criteria) this;
        }

        public Criteria andGateStatIsNull() {
            addCriterion("GATE_STAT is null");
            return (Criteria) this;
        }

        public Criteria andGateStatIsNotNull() {
            addCriterion("GATE_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andGateStatEqualTo(String value) {
            addCriterion("GATE_STAT =", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatNotEqualTo(String value) {
            addCriterion("GATE_STAT <>", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatGreaterThan(String value) {
            addCriterion("GATE_STAT >", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatGreaterThanOrEqualTo(String value) {
            addCriterion("GATE_STAT >=", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatLessThan(String value) {
            addCriterion("GATE_STAT <", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatLessThanOrEqualTo(String value) {
            addCriterion("GATE_STAT <=", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatLike(String value) {
            addCriterion("GATE_STAT like", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatNotLike(String value) {
            addCriterion("GATE_STAT not like", value, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatIn(List<String> values) {
            addCriterion("GATE_STAT in", values, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatNotIn(List<String> values) {
            addCriterion("GATE_STAT not in", values, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatBetween(String value1, String value2) {
            addCriterion("GATE_STAT between", value1, value2, "gateStat");
            return (Criteria) this;
        }

        public Criteria andGateStatNotBetween(String value1, String value2) {
            addCriterion("GATE_STAT not between", value1, value2, "gateStat");
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