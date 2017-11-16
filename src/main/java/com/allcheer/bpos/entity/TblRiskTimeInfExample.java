package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblRiskTimeInfExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblRiskTimeInfExample() {
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

        public Criteria andRiskSeqIsNull() {
            addCriterion("RISK_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andRiskSeqIsNotNull() {
            addCriterion("RISK_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andRiskSeqEqualTo(String value) {
            addCriterion("RISK_SEQ =", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqNotEqualTo(String value) {
            addCriterion("RISK_SEQ <>", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqGreaterThan(String value) {
            addCriterion("RISK_SEQ >", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_SEQ >=", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqLessThan(String value) {
            addCriterion("RISK_SEQ <", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqLessThanOrEqualTo(String value) {
            addCriterion("RISK_SEQ <=", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqLike(String value) {
            addCriterion("RISK_SEQ like", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqNotLike(String value) {
            addCriterion("RISK_SEQ not like", value, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqIn(List<String> values) {
            addCriterion("RISK_SEQ in", values, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqNotIn(List<String> values) {
            addCriterion("RISK_SEQ not in", values, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqBetween(String value1, String value2) {
            addCriterion("RISK_SEQ between", value1, value2, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskSeqNotBetween(String value1, String value2) {
            addCriterion("RISK_SEQ not between", value1, value2, "riskSeq");
            return (Criteria) this;
        }

        public Criteria andRiskTypeIsNull() {
            addCriterion("RISK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRiskTypeIsNotNull() {
            addCriterion("RISK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRiskTypeEqualTo(String value) {
            addCriterion("RISK_TYPE =", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotEqualTo(String value) {
            addCriterion("RISK_TYPE <>", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeGreaterThan(String value) {
            addCriterion("RISK_TYPE >", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_TYPE >=", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeLessThan(String value) {
            addCriterion("RISK_TYPE <", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeLessThanOrEqualTo(String value) {
            addCriterion("RISK_TYPE <=", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeLike(String value) {
            addCriterion("RISK_TYPE like", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotLike(String value) {
            addCriterion("RISK_TYPE not like", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeIn(List<String> values) {
            addCriterion("RISK_TYPE in", values, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotIn(List<String> values) {
            addCriterion("RISK_TYPE not in", values, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeBetween(String value1, String value2) {
            addCriterion("RISK_TYPE between", value1, value2, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotBetween(String value1, String value2) {
            addCriterion("RISK_TYPE not between", value1, value2, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskDetailIsNull() {
            addCriterion("RISK_DETAIL is null");
            return (Criteria) this;
        }

        public Criteria andRiskDetailIsNotNull() {
            addCriterion("RISK_DETAIL is not null");
            return (Criteria) this;
        }

        public Criteria andRiskDetailEqualTo(String value) {
            addCriterion("RISK_DETAIL =", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailNotEqualTo(String value) {
            addCriterion("RISK_DETAIL <>", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailGreaterThan(String value) {
            addCriterion("RISK_DETAIL >", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_DETAIL >=", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailLessThan(String value) {
            addCriterion("RISK_DETAIL <", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailLessThanOrEqualTo(String value) {
            addCriterion("RISK_DETAIL <=", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailLike(String value) {
            addCriterion("RISK_DETAIL like", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailNotLike(String value) {
            addCriterion("RISK_DETAIL not like", value, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailIn(List<String> values) {
            addCriterion("RISK_DETAIL in", values, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailNotIn(List<String> values) {
            addCriterion("RISK_DETAIL not in", values, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailBetween(String value1, String value2) {
            addCriterion("RISK_DETAIL between", value1, value2, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andRiskDetailNotBetween(String value1, String value2) {
            addCriterion("RISK_DETAIL not between", value1, value2, "riskDetail");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(String value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(String value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(String value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(String value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLike(String value) {
            addCriterion("BEGIN_TIME like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotLike(String value) {
            addCriterion("BEGIN_TIME not like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<String> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<String> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("END_TIME like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("END_TIME not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
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

        public Criteria andRiskRemarkIsNull() {
            addCriterion("RISK_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkIsNotNull() {
            addCriterion("RISK_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkEqualTo(String value) {
            addCriterion("RISK_REMARK =", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkNotEqualTo(String value) {
            addCriterion("RISK_REMARK <>", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkGreaterThan(String value) {
            addCriterion("RISK_REMARK >", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_REMARK >=", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkLessThan(String value) {
            addCriterion("RISK_REMARK <", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkLessThanOrEqualTo(String value) {
            addCriterion("RISK_REMARK <=", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkLike(String value) {
            addCriterion("RISK_REMARK like", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkNotLike(String value) {
            addCriterion("RISK_REMARK not like", value, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkIn(List<String> values) {
            addCriterion("RISK_REMARK in", values, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkNotIn(List<String> values) {
            addCriterion("RISK_REMARK not in", values, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkBetween(String value1, String value2) {
            addCriterion("RISK_REMARK between", value1, value2, "riskRemark");
            return (Criteria) this;
        }

        public Criteria andRiskRemarkNotBetween(String value1, String value2) {
            addCriterion("RISK_REMARK not between", value1, value2, "riskRemark");
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