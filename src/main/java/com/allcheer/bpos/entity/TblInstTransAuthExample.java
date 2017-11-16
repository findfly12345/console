package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblInstTransAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblInstTransAuthExample() {
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

        public Criteria andPosStatIsNull() {
            addCriterion("POS_STAT is null");
            return (Criteria) this;
        }

        public Criteria andPosStatIsNotNull() {
            addCriterion("POS_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andPosStatEqualTo(String value) {
            addCriterion("POS_STAT =", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatNotEqualTo(String value) {
            addCriterion("POS_STAT <>", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatGreaterThan(String value) {
            addCriterion("POS_STAT >", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatGreaterThanOrEqualTo(String value) {
            addCriterion("POS_STAT >=", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatLessThan(String value) {
            addCriterion("POS_STAT <", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatLessThanOrEqualTo(String value) {
            addCriterion("POS_STAT <=", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatLike(String value) {
            addCriterion("POS_STAT like", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatNotLike(String value) {
            addCriterion("POS_STAT not like", value, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatIn(List<String> values) {
            addCriterion("POS_STAT in", values, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatNotIn(List<String> values) {
            addCriterion("POS_STAT not in", values, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatBetween(String value1, String value2) {
            addCriterion("POS_STAT between", value1, value2, "posStat");
            return (Criteria) this;
        }

        public Criteria andPosStatNotBetween(String value1, String value2) {
            addCriterion("POS_STAT not between", value1, value2, "posStat");
            return (Criteria) this;
        }

        public Criteria andChatStatIsNull() {
            addCriterion("CHAT_STAT is null");
            return (Criteria) this;
        }

        public Criteria andChatStatIsNotNull() {
            addCriterion("CHAT_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andChatStatEqualTo(String value) {
            addCriterion("CHAT_STAT =", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatNotEqualTo(String value) {
            addCriterion("CHAT_STAT <>", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatGreaterThan(String value) {
            addCriterion("CHAT_STAT >", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatGreaterThanOrEqualTo(String value) {
            addCriterion("CHAT_STAT >=", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatLessThan(String value) {
            addCriterion("CHAT_STAT <", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatLessThanOrEqualTo(String value) {
            addCriterion("CHAT_STAT <=", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatLike(String value) {
            addCriterion("CHAT_STAT like", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatNotLike(String value) {
            addCriterion("CHAT_STAT not like", value, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatIn(List<String> values) {
            addCriterion("CHAT_STAT in", values, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatNotIn(List<String> values) {
            addCriterion("CHAT_STAT not in", values, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatBetween(String value1, String value2) {
            addCriterion("CHAT_STAT between", value1, value2, "chatStat");
            return (Criteria) this;
        }

        public Criteria andChatStatNotBetween(String value1, String value2) {
            addCriterion("CHAT_STAT not between", value1, value2, "chatStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatIsNull() {
            addCriterion("ALLIPAY_STAT is null");
            return (Criteria) this;
        }

        public Criteria andAllipayStatIsNotNull() {
            addCriterion("ALLIPAY_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andAllipayStatEqualTo(String value) {
            addCriterion("ALLIPAY_STAT =", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatNotEqualTo(String value) {
            addCriterion("ALLIPAY_STAT <>", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatGreaterThan(String value) {
            addCriterion("ALLIPAY_STAT >", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatGreaterThanOrEqualTo(String value) {
            addCriterion("ALLIPAY_STAT >=", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatLessThan(String value) {
            addCriterion("ALLIPAY_STAT <", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatLessThanOrEqualTo(String value) {
            addCriterion("ALLIPAY_STAT <=", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatLike(String value) {
            addCriterion("ALLIPAY_STAT like", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatNotLike(String value) {
            addCriterion("ALLIPAY_STAT not like", value, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatIn(List<String> values) {
            addCriterion("ALLIPAY_STAT in", values, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatNotIn(List<String> values) {
            addCriterion("ALLIPAY_STAT not in", values, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatBetween(String value1, String value2) {
            addCriterion("ALLIPAY_STAT between", value1, value2, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAllipayStatNotBetween(String value1, String value2) {
            addCriterion("ALLIPAY_STAT not between", value1, value2, "allipayStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatIsNull() {
            addCriterion("AUTH_STAT is null");
            return (Criteria) this;
        }

        public Criteria andAuthStatIsNotNull() {
            addCriterion("AUTH_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStatEqualTo(String value) {
            addCriterion("AUTH_STAT =", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatNotEqualTo(String value) {
            addCriterion("AUTH_STAT <>", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatGreaterThan(String value) {
            addCriterion("AUTH_STAT >", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_STAT >=", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatLessThan(String value) {
            addCriterion("AUTH_STAT <", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatLessThanOrEqualTo(String value) {
            addCriterion("AUTH_STAT <=", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatLike(String value) {
            addCriterion("AUTH_STAT like", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatNotLike(String value) {
            addCriterion("AUTH_STAT not like", value, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatIn(List<String> values) {
            addCriterion("AUTH_STAT in", values, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatNotIn(List<String> values) {
            addCriterion("AUTH_STAT not in", values, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatBetween(String value1, String value2) {
            addCriterion("AUTH_STAT between", value1, value2, "authStat");
            return (Criteria) this;
        }

        public Criteria andAuthStatNotBetween(String value1, String value2) {
            addCriterion("AUTH_STAT not between", value1, value2, "authStat");
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