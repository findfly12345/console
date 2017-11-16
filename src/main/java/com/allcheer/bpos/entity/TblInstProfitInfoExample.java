package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblInstProfitInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblInstProfitInfoExample() {
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

        public Criteria andTransDateIsNull() {
            addCriterion("TRANS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTransDateIsNotNull() {
            addCriterion("TRANS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTransDateEqualTo(String value) {
            addCriterion("TRANS_DATE =", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotEqualTo(String value) {
            addCriterion("TRANS_DATE <>", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThan(String value) {
            addCriterion("TRANS_DATE >", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_DATE >=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThan(String value) {
            addCriterion("TRANS_DATE <", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThanOrEqualTo(String value) {
            addCriterion("TRANS_DATE <=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLike(String value) {
            addCriterion("TRANS_DATE like", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotLike(String value) {
            addCriterion("TRANS_DATE not like", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateIn(List<String> values) {
            addCriterion("TRANS_DATE in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotIn(List<String> values) {
            addCriterion("TRANS_DATE not in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateBetween(String value1, String value2) {
            addCriterion("TRANS_DATE between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotBetween(String value1, String value2) {
            addCriterion("TRANS_DATE not between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNull() {
            addCriterion("TRANS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNotNull() {
            addCriterion("TRANS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeEqualTo(String value) {
            addCriterion("TRANS_TYPE =", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotEqualTo(String value) {
            addCriterion("TRANS_TYPE <>", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThan(String value) {
            addCriterion("TRANS_TYPE >", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE >=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThan(String value) {
            addCriterion("TRANS_TYPE <", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE <=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLike(String value) {
            addCriterion("TRANS_TYPE like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotLike(String value) {
            addCriterion("TRANS_TYPE not like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeIn(List<String> values) {
            addCriterion("TRANS_TYPE in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotIn(List<String> values) {
            addCriterion("TRANS_TYPE not in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE not between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransSumIsNull() {
            addCriterion("TRANS_SUM is null");
            return (Criteria) this;
        }

        public Criteria andTransSumIsNotNull() {
            addCriterion("TRANS_SUM is not null");
            return (Criteria) this;
        }

        public Criteria andTransSumEqualTo(String value) {
            addCriterion("TRANS_SUM =", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumNotEqualTo(String value) {
            addCriterion("TRANS_SUM <>", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumGreaterThan(String value) {
            addCriterion("TRANS_SUM >", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_SUM >=", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumLessThan(String value) {
            addCriterion("TRANS_SUM <", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumLessThanOrEqualTo(String value) {
            addCriterion("TRANS_SUM <=", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumLike(String value) {
            addCriterion("TRANS_SUM like", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumNotLike(String value) {
            addCriterion("TRANS_SUM not like", value, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumIn(List<String> values) {
            addCriterion("TRANS_SUM in", values, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumNotIn(List<String> values) {
            addCriterion("TRANS_SUM not in", values, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumBetween(String value1, String value2) {
            addCriterion("TRANS_SUM between", value1, value2, "transSum");
            return (Criteria) this;
        }

        public Criteria andTransSumNotBetween(String value1, String value2) {
            addCriterion("TRANS_SUM not between", value1, value2, "transSum");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeIsNull() {
            addCriterion("MER_TRANS_FEE is null");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeIsNotNull() {
            addCriterion("MER_TRANS_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeEqualTo(String value) {
            addCriterion("MER_TRANS_FEE =", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeNotEqualTo(String value) {
            addCriterion("MER_TRANS_FEE <>", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeGreaterThan(String value) {
            addCriterion("MER_TRANS_FEE >", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeGreaterThanOrEqualTo(String value) {
            addCriterion("MER_TRANS_FEE >=", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeLessThan(String value) {
            addCriterion("MER_TRANS_FEE <", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeLessThanOrEqualTo(String value) {
            addCriterion("MER_TRANS_FEE <=", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeLike(String value) {
            addCriterion("MER_TRANS_FEE like", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeNotLike(String value) {
            addCriterion("MER_TRANS_FEE not like", value, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeIn(List<String> values) {
            addCriterion("MER_TRANS_FEE in", values, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeNotIn(List<String> values) {
            addCriterion("MER_TRANS_FEE not in", values, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeBetween(String value1, String value2) {
            addCriterion("MER_TRANS_FEE between", value1, value2, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andMerTransFeeNotBetween(String value1, String value2) {
            addCriterion("MER_TRANS_FEE not between", value1, value2, "merTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeIsNull() {
            addCriterion("INST_TRANS_FEE is null");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeIsNotNull() {
            addCriterion("INST_TRANS_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeEqualTo(String value) {
            addCriterion("INST_TRANS_FEE =", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeNotEqualTo(String value) {
            addCriterion("INST_TRANS_FEE <>", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeGreaterThan(String value) {
            addCriterion("INST_TRANS_FEE >", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeGreaterThanOrEqualTo(String value) {
            addCriterion("INST_TRANS_FEE >=", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeLessThan(String value) {
            addCriterion("INST_TRANS_FEE <", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeLessThanOrEqualTo(String value) {
            addCriterion("INST_TRANS_FEE <=", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeLike(String value) {
            addCriterion("INST_TRANS_FEE like", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeNotLike(String value) {
            addCriterion("INST_TRANS_FEE not like", value, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeIn(List<String> values) {
            addCriterion("INST_TRANS_FEE in", values, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeNotIn(List<String> values) {
            addCriterion("INST_TRANS_FEE not in", values, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeBetween(String value1, String value2) {
            addCriterion("INST_TRANS_FEE between", value1, value2, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andInstTransFeeNotBetween(String value1, String value2) {
            addCriterion("INST_TRANS_FEE not between", value1, value2, "instTransFee");
            return (Criteria) this;
        }

        public Criteria andAmtSumIsNull() {
            addCriterion("AMT_SUM is null");
            return (Criteria) this;
        }

        public Criteria andAmtSumIsNotNull() {
            addCriterion("AMT_SUM is not null");
            return (Criteria) this;
        }

        public Criteria andAmtSumEqualTo(String value) {
            addCriterion("AMT_SUM =", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumNotEqualTo(String value) {
            addCriterion("AMT_SUM <>", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumGreaterThan(String value) {
            addCriterion("AMT_SUM >", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumGreaterThanOrEqualTo(String value) {
            addCriterion("AMT_SUM >=", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumLessThan(String value) {
            addCriterion("AMT_SUM <", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumLessThanOrEqualTo(String value) {
            addCriterion("AMT_SUM <=", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumLike(String value) {
            addCriterion("AMT_SUM like", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumNotLike(String value) {
            addCriterion("AMT_SUM not like", value, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumIn(List<String> values) {
            addCriterion("AMT_SUM in", values, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumNotIn(List<String> values) {
            addCriterion("AMT_SUM not in", values, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumBetween(String value1, String value2) {
            addCriterion("AMT_SUM between", value1, value2, "amtSum");
            return (Criteria) this;
        }

        public Criteria andAmtSumNotBetween(String value1, String value2) {
            addCriterion("AMT_SUM not between", value1, value2, "amtSum");
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