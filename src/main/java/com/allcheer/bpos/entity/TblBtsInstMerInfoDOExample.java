package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsInstMerInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsInstMerInfoDOExample() {
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

        public Criteria andInstMerIdIsNull() {
            addCriterion("INST_MER_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstMerIdIsNotNull() {
            addCriterion("INST_MER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstMerIdEqualTo(String value) {
            addCriterion("INST_MER_ID =", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotEqualTo(String value) {
            addCriterion("INST_MER_ID <>", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdGreaterThan(String value) {
            addCriterion("INST_MER_ID >", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MER_ID >=", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdLessThan(String value) {
            addCriterion("INST_MER_ID <", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdLessThanOrEqualTo(String value) {
            addCriterion("INST_MER_ID <=", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdLike(String value) {
            addCriterion("INST_MER_ID like", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotLike(String value) {
            addCriterion("INST_MER_ID not like", value, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdIn(List<String> values) {
            addCriterion("INST_MER_ID in", values, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotIn(List<String> values) {
            addCriterion("INST_MER_ID not in", values, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdBetween(String value1, String value2) {
            addCriterion("INST_MER_ID between", value1, value2, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstMerIdNotBetween(String value1, String value2) {
            addCriterion("INST_MER_ID not between", value1, value2, "instMerId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdIsNull() {
            addCriterion("INST_TERM_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstTermIdIsNotNull() {
            addCriterion("INST_TERM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstTermIdEqualTo(String value) {
            addCriterion("INST_TERM_ID =", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotEqualTo(String value) {
            addCriterion("INST_TERM_ID <>", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdGreaterThan(String value) {
            addCriterion("INST_TERM_ID >", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_TERM_ID >=", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdLessThan(String value) {
            addCriterion("INST_TERM_ID <", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdLessThanOrEqualTo(String value) {
            addCriterion("INST_TERM_ID <=", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdLike(String value) {
            addCriterion("INST_TERM_ID like", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotLike(String value) {
            addCriterion("INST_TERM_ID not like", value, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdIn(List<String> values) {
            addCriterion("INST_TERM_ID in", values, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotIn(List<String> values) {
            addCriterion("INST_TERM_ID not in", values, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdBetween(String value1, String value2) {
            addCriterion("INST_TERM_ID between", value1, value2, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstTermIdNotBetween(String value1, String value2) {
            addCriterion("INST_TERM_ID not between", value1, value2, "instTermId");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeIsNull() {
            addCriterion("INST_MER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeIsNotNull() {
            addCriterion("INST_MER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeEqualTo(String value) {
            addCriterion("INST_MER_TYPE =", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeNotEqualTo(String value) {
            addCriterion("INST_MER_TYPE <>", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeGreaterThan(String value) {
            addCriterion("INST_MER_TYPE >", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MER_TYPE >=", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeLessThan(String value) {
            addCriterion("INST_MER_TYPE <", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeLessThanOrEqualTo(String value) {
            addCriterion("INST_MER_TYPE <=", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeLike(String value) {
            addCriterion("INST_MER_TYPE like", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeNotLike(String value) {
            addCriterion("INST_MER_TYPE not like", value, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeIn(List<String> values) {
            addCriterion("INST_MER_TYPE in", values, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeNotIn(List<String> values) {
            addCriterion("INST_MER_TYPE not in", values, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeBetween(String value1, String value2) {
            addCriterion("INST_MER_TYPE between", value1, value2, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerTypeNotBetween(String value1, String value2) {
            addCriterion("INST_MER_TYPE not between", value1, value2, "instMerType");
            return (Criteria) this;
        }

        public Criteria andInstMerDescIsNull() {
            addCriterion("INST_MER_DESC is null");
            return (Criteria) this;
        }

        public Criteria andInstMerDescIsNotNull() {
            addCriterion("INST_MER_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andInstMerDescEqualTo(String value) {
            addCriterion("INST_MER_DESC =", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescNotEqualTo(String value) {
            addCriterion("INST_MER_DESC <>", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescGreaterThan(String value) {
            addCriterion("INST_MER_DESC >", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MER_DESC >=", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescLessThan(String value) {
            addCriterion("INST_MER_DESC <", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescLessThanOrEqualTo(String value) {
            addCriterion("INST_MER_DESC <=", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescLike(String value) {
            addCriterion("INST_MER_DESC like", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescNotLike(String value) {
            addCriterion("INST_MER_DESC not like", value, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescIn(List<String> values) {
            addCriterion("INST_MER_DESC in", values, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescNotIn(List<String> values) {
            addCriterion("INST_MER_DESC not in", values, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescBetween(String value1, String value2) {
            addCriterion("INST_MER_DESC between", value1, value2, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerDescNotBetween(String value1, String value2) {
            addCriterion("INST_MER_DESC not between", value1, value2, "instMerDesc");
            return (Criteria) this;
        }

        public Criteria andInstMerStatIsNull() {
            addCriterion("INST_MER_STAT is null");
            return (Criteria) this;
        }

        public Criteria andInstMerStatIsNotNull() {
            addCriterion("INST_MER_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andInstMerStatEqualTo(String value) {
            addCriterion("INST_MER_STAT =", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatNotEqualTo(String value) {
            addCriterion("INST_MER_STAT <>", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatGreaterThan(String value) {
            addCriterion("INST_MER_STAT >", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MER_STAT >=", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatLessThan(String value) {
            addCriterion("INST_MER_STAT <", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatLessThanOrEqualTo(String value) {
            addCriterion("INST_MER_STAT <=", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatLike(String value) {
            addCriterion("INST_MER_STAT like", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatNotLike(String value) {
            addCriterion("INST_MER_STAT not like", value, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatIn(List<String> values) {
            addCriterion("INST_MER_STAT in", values, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatNotIn(List<String> values) {
            addCriterion("INST_MER_STAT not in", values, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatBetween(String value1, String value2) {
            addCriterion("INST_MER_STAT between", value1, value2, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerStatNotBetween(String value1, String value2) {
            addCriterion("INST_MER_STAT not between", value1, value2, "instMerStat");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkIsNull() {
            addCriterion("INST_MER_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkIsNotNull() {
            addCriterion("INST_MER_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkEqualTo(String value) {
            addCriterion("INST_MER_REMARK =", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkNotEqualTo(String value) {
            addCriterion("INST_MER_REMARK <>", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkGreaterThan(String value) {
            addCriterion("INST_MER_REMARK >", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("INST_MER_REMARK >=", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkLessThan(String value) {
            addCriterion("INST_MER_REMARK <", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkLessThanOrEqualTo(String value) {
            addCriterion("INST_MER_REMARK <=", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkLike(String value) {
            addCriterion("INST_MER_REMARK like", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkNotLike(String value) {
            addCriterion("INST_MER_REMARK not like", value, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkIn(List<String> values) {
            addCriterion("INST_MER_REMARK in", values, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkNotIn(List<String> values) {
            addCriterion("INST_MER_REMARK not in", values, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkBetween(String value1, String value2) {
            addCriterion("INST_MER_REMARK between", value1, value2, "instMerRemark");
            return (Criteria) this;
        }

        public Criteria andInstMerRemarkNotBetween(String value1, String value2) {
            addCriterion("INST_MER_REMARK not between", value1, value2, "instMerRemark");
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