package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblRiskBlackListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblRiskBlackListExample() {
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

        public Criteria andPriCardNoIsNull() {
            addCriterion("PRI_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andPriCardNoIsNotNull() {
            addCriterion("PRI_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPriCardNoEqualTo(String value) {
            addCriterion("PRI_CARD_NO =", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoNotEqualTo(String value) {
            addCriterion("PRI_CARD_NO <>", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoGreaterThan(String value) {
            addCriterion("PRI_CARD_NO >", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("PRI_CARD_NO >=", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoLessThan(String value) {
            addCriterion("PRI_CARD_NO <", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoLessThanOrEqualTo(String value) {
            addCriterion("PRI_CARD_NO <=", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoLike(String value) {
            addCriterion("PRI_CARD_NO like", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoNotLike(String value) {
            addCriterion("PRI_CARD_NO not like", value, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoIn(List<String> values) {
            addCriterion("PRI_CARD_NO in", values, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoNotIn(List<String> values) {
            addCriterion("PRI_CARD_NO not in", values, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoBetween(String value1, String value2) {
            addCriterion("PRI_CARD_NO between", value1, value2, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andPriCardNoNotBetween(String value1, String value2) {
            addCriterion("PRI_CARD_NO not between", value1, value2, "priCardNo");
            return (Criteria) this;
        }

        public Criteria andInstCodeIsNull() {
            addCriterion("INST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInstCodeIsNotNull() {
            addCriterion("INST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInstCodeEqualTo(String value) {
            addCriterion("INST_CODE =", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotEqualTo(String value) {
            addCriterion("INST_CODE <>", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeGreaterThan(String value) {
            addCriterion("INST_CODE >", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeGreaterThanOrEqualTo(String value) {
            addCriterion("INST_CODE >=", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLessThan(String value) {
            addCriterion("INST_CODE <", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLessThanOrEqualTo(String value) {
            addCriterion("INST_CODE <=", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLike(String value) {
            addCriterion("INST_CODE like", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotLike(String value) {
            addCriterion("INST_CODE not like", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeIn(List<String> values) {
            addCriterion("INST_CODE in", values, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotIn(List<String> values) {
            addCriterion("INST_CODE not in", values, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeBetween(String value1, String value2) {
            addCriterion("INST_CODE between", value1, value2, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotBetween(String value1, String value2) {
            addCriterion("INST_CODE not between", value1, value2, "instCode");
            return (Criteria) this;
        }

        public Criteria andMerchIdIsNull() {
            addCriterion("MERCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchIdIsNotNull() {
            addCriterion("MERCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchIdEqualTo(String value) {
            addCriterion("MERCH_ID =", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdNotEqualTo(String value) {
            addCriterion("MERCH_ID <>", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdGreaterThan(String value) {
            addCriterion("MERCH_ID >", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdGreaterThanOrEqualTo(String value) {
            addCriterion("MERCH_ID >=", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdLessThan(String value) {
            addCriterion("MERCH_ID <", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdLessThanOrEqualTo(String value) {
            addCriterion("MERCH_ID <=", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdLike(String value) {
            addCriterion("MERCH_ID like", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdNotLike(String value) {
            addCriterion("MERCH_ID not like", value, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdIn(List<String> values) {
            addCriterion("MERCH_ID in", values, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdNotIn(List<String> values) {
            addCriterion("MERCH_ID not in", values, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdBetween(String value1, String value2) {
            addCriterion("MERCH_ID between", value1, value2, "merchId");
            return (Criteria) this;
        }

        public Criteria andMerchIdNotBetween(String value1, String value2) {
            addCriterion("MERCH_ID not between", value1, value2, "merchId");
            return (Criteria) this;
        }

        public Criteria andTermCodeIsNull() {
            addCriterion("TERM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTermCodeIsNotNull() {
            addCriterion("TERM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTermCodeEqualTo(String value) {
            addCriterion("TERM_CODE =", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotEqualTo(String value) {
            addCriterion("TERM_CODE <>", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeGreaterThan(String value) {
            addCriterion("TERM_CODE >", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_CODE >=", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLessThan(String value) {
            addCriterion("TERM_CODE <", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLessThanOrEqualTo(String value) {
            addCriterion("TERM_CODE <=", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLike(String value) {
            addCriterion("TERM_CODE like", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotLike(String value) {
            addCriterion("TERM_CODE not like", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeIn(List<String> values) {
            addCriterion("TERM_CODE in", values, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotIn(List<String> values) {
            addCriterion("TERM_CODE not in", values, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeBetween(String value1, String value2) {
            addCriterion("TERM_CODE between", value1, value2, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotBetween(String value1, String value2) {
            addCriterion("TERM_CODE not between", value1, value2, "termCode");
            return (Criteria) this;
        }

        public Criteria andErrRuleIsNull() {
            addCriterion("ERR_RULE is null");
            return (Criteria) this;
        }

        public Criteria andErrRuleIsNotNull() {
            addCriterion("ERR_RULE is not null");
            return (Criteria) this;
        }

        public Criteria andErrRuleEqualTo(String value) {
            addCriterion("ERR_RULE =", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleNotEqualTo(String value) {
            addCriterion("ERR_RULE <>", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleGreaterThan(String value) {
            addCriterion("ERR_RULE >", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleGreaterThanOrEqualTo(String value) {
            addCriterion("ERR_RULE >=", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleLessThan(String value) {
            addCriterion("ERR_RULE <", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleLessThanOrEqualTo(String value) {
            addCriterion("ERR_RULE <=", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleLike(String value) {
            addCriterion("ERR_RULE like", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleNotLike(String value) {
            addCriterion("ERR_RULE not like", value, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleIn(List<String> values) {
            addCriterion("ERR_RULE in", values, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleNotIn(List<String> values) {
            addCriterion("ERR_RULE not in", values, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleBetween(String value1, String value2) {
            addCriterion("ERR_RULE between", value1, value2, "errRule");
            return (Criteria) this;
        }

        public Criteria andErrRuleNotBetween(String value1, String value2) {
            addCriterion("ERR_RULE not between", value1, value2, "errRule");
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

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("CREATE_DATE like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("CREATE_DATE not like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<String> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<String> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
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