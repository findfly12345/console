package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsGateMerInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsGateMerInfoDOExample() {
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

        public Criteria andPosMerTypeIsNull() {
            addCriterion("POS_MER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeIsNotNull() {
            addCriterion("POS_MER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeEqualTo(String value) {
            addCriterion("POS_MER_TYPE =", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeNotEqualTo(String value) {
            addCriterion("POS_MER_TYPE <>", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeGreaterThan(String value) {
            addCriterion("POS_MER_TYPE >", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("POS_MER_TYPE >=", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeLessThan(String value) {
            addCriterion("POS_MER_TYPE <", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeLessThanOrEqualTo(String value) {
            addCriterion("POS_MER_TYPE <=", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeLike(String value) {
            addCriterion("POS_MER_TYPE like", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeNotLike(String value) {
            addCriterion("POS_MER_TYPE not like", value, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeIn(List<String> values) {
            addCriterion("POS_MER_TYPE in", values, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeNotIn(List<String> values) {
            addCriterion("POS_MER_TYPE not in", values, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeBetween(String value1, String value2) {
            addCriterion("POS_MER_TYPE between", value1, value2, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerTypeNotBetween(String value1, String value2) {
            addCriterion("POS_MER_TYPE not between", value1, value2, "posMerType");
            return (Criteria) this;
        }

        public Criteria andPosMerDescIsNull() {
            addCriterion("POS_MER_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPosMerDescIsNotNull() {
            addCriterion("POS_MER_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPosMerDescEqualTo(String value) {
            addCriterion("POS_MER_DESC =", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescNotEqualTo(String value) {
            addCriterion("POS_MER_DESC <>", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescGreaterThan(String value) {
            addCriterion("POS_MER_DESC >", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescGreaterThanOrEqualTo(String value) {
            addCriterion("POS_MER_DESC >=", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescLessThan(String value) {
            addCriterion("POS_MER_DESC <", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescLessThanOrEqualTo(String value) {
            addCriterion("POS_MER_DESC <=", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescLike(String value) {
            addCriterion("POS_MER_DESC like", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescNotLike(String value) {
            addCriterion("POS_MER_DESC not like", value, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescIn(List<String> values) {
            addCriterion("POS_MER_DESC in", values, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescNotIn(List<String> values) {
            addCriterion("POS_MER_DESC not in", values, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescBetween(String value1, String value2) {
            addCriterion("POS_MER_DESC between", value1, value2, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerDescNotBetween(String value1, String value2) {
            addCriterion("POS_MER_DESC not between", value1, value2, "posMerDesc");
            return (Criteria) this;
        }

        public Criteria andPosMerStatIsNull() {
            addCriterion("POS_MER_STAT is null");
            return (Criteria) this;
        }

        public Criteria andPosMerStatIsNotNull() {
            addCriterion("POS_MER_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andPosMerStatEqualTo(String value) {
            addCriterion("POS_MER_STAT =", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatNotEqualTo(String value) {
            addCriterion("POS_MER_STAT <>", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatGreaterThan(String value) {
            addCriterion("POS_MER_STAT >", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatGreaterThanOrEqualTo(String value) {
            addCriterion("POS_MER_STAT >=", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatLessThan(String value) {
            addCriterion("POS_MER_STAT <", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatLessThanOrEqualTo(String value) {
            addCriterion("POS_MER_STAT <=", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatLike(String value) {
            addCriterion("POS_MER_STAT like", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatNotLike(String value) {
            addCriterion("POS_MER_STAT not like", value, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatIn(List<String> values) {
            addCriterion("POS_MER_STAT in", values, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatNotIn(List<String> values) {
            addCriterion("POS_MER_STAT not in", values, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatBetween(String value1, String value2) {
            addCriterion("POS_MER_STAT between", value1, value2, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerStatNotBetween(String value1, String value2) {
            addCriterion("POS_MER_STAT not between", value1, value2, "posMerStat");
            return (Criteria) this;
        }

        public Criteria andPosMerProvIsNull() {
            addCriterion("POS_MER_PROV is null");
            return (Criteria) this;
        }

        public Criteria andPosMerProvIsNotNull() {
            addCriterion("POS_MER_PROV is not null");
            return (Criteria) this;
        }

        public Criteria andPosMerProvEqualTo(String value) {
            addCriterion("POS_MER_PROV =", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvNotEqualTo(String value) {
            addCriterion("POS_MER_PROV <>", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvGreaterThan(String value) {
            addCriterion("POS_MER_PROV >", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvGreaterThanOrEqualTo(String value) {
            addCriterion("POS_MER_PROV >=", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvLessThan(String value) {
            addCriterion("POS_MER_PROV <", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvLessThanOrEqualTo(String value) {
            addCriterion("POS_MER_PROV <=", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvLike(String value) {
            addCriterion("POS_MER_PROV like", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvNotLike(String value) {
            addCriterion("POS_MER_PROV not like", value, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvIn(List<String> values) {
            addCriterion("POS_MER_PROV in", values, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvNotIn(List<String> values) {
            addCriterion("POS_MER_PROV not in", values, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvBetween(String value1, String value2) {
            addCriterion("POS_MER_PROV between", value1, value2, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerProvNotBetween(String value1, String value2) {
            addCriterion("POS_MER_PROV not between", value1, value2, "posMerProv");
            return (Criteria) this;
        }

        public Criteria andPosMerCityIsNull() {
            addCriterion("POS_MER_CITY is null");
            return (Criteria) this;
        }

        public Criteria andPosMerCityIsNotNull() {
            addCriterion("POS_MER_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andPosMerCityEqualTo(String value) {
            addCriterion("POS_MER_CITY =", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityNotEqualTo(String value) {
            addCriterion("POS_MER_CITY <>", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityGreaterThan(String value) {
            addCriterion("POS_MER_CITY >", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityGreaterThanOrEqualTo(String value) {
            addCriterion("POS_MER_CITY >=", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityLessThan(String value) {
            addCriterion("POS_MER_CITY <", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityLessThanOrEqualTo(String value) {
            addCriterion("POS_MER_CITY <=", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityLike(String value) {
            addCriterion("POS_MER_CITY like", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityNotLike(String value) {
            addCriterion("POS_MER_CITY not like", value, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityIn(List<String> values) {
            addCriterion("POS_MER_CITY in", values, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityNotIn(List<String> values) {
            addCriterion("POS_MER_CITY not in", values, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityBetween(String value1, String value2) {
            addCriterion("POS_MER_CITY between", value1, value2, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerCityNotBetween(String value1, String value2) {
            addCriterion("POS_MER_CITY not between", value1, value2, "posMerCity");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkIsNull() {
            addCriterion("POS_MER_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkIsNotNull() {
            addCriterion("POS_MER_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkEqualTo(String value) {
            addCriterion("POS_MER_REMARK =", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkNotEqualTo(String value) {
            addCriterion("POS_MER_REMARK <>", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkGreaterThan(String value) {
            addCriterion("POS_MER_REMARK >", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("POS_MER_REMARK >=", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkLessThan(String value) {
            addCriterion("POS_MER_REMARK <", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkLessThanOrEqualTo(String value) {
            addCriterion("POS_MER_REMARK <=", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkLike(String value) {
            addCriterion("POS_MER_REMARK like", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkNotLike(String value) {
            addCriterion("POS_MER_REMARK not like", value, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkIn(List<String> values) {
            addCriterion("POS_MER_REMARK in", values, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkNotIn(List<String> values) {
            addCriterion("POS_MER_REMARK not in", values, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkBetween(String value1, String value2) {
            addCriterion("POS_MER_REMARK between", value1, value2, "posMerRemark");
            return (Criteria) this;
        }

        public Criteria andPosMerRemarkNotBetween(String value1, String value2) {
            addCriterion("POS_MER_REMARK not between", value1, value2, "posMerRemark");
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