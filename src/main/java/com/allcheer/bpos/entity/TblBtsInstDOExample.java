package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsInstDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsInstDOExample() {
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

        public Criteria andInstTypeIsNull() {
            addCriterion("INST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInstTypeIsNotNull() {
            addCriterion("INST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInstTypeEqualTo(String value) {
            addCriterion("INST_TYPE =", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeNotEqualTo(String value) {
            addCriterion("INST_TYPE <>", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeGreaterThan(String value) {
            addCriterion("INST_TYPE >", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INST_TYPE >=", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeLessThan(String value) {
            addCriterion("INST_TYPE <", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeLessThanOrEqualTo(String value) {
            addCriterion("INST_TYPE <=", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeLike(String value) {
            addCriterion("INST_TYPE like", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeNotLike(String value) {
            addCriterion("INST_TYPE not like", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeIn(List<String> values) {
            addCriterion("INST_TYPE in", values, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeNotIn(List<String> values) {
            addCriterion("INST_TYPE not in", values, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeBetween(String value1, String value2) {
            addCriterion("INST_TYPE between", value1, value2, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeNotBetween(String value1, String value2) {
            addCriterion("INST_TYPE not between", value1, value2, "instType");
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

        public Criteria andInstEmailIsNull() {
            addCriterion("INST_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andInstEmailIsNotNull() {
            addCriterion("INST_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andInstEmailEqualTo(String value) {
            addCriterion("INST_EMAIL =", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailNotEqualTo(String value) {
            addCriterion("INST_EMAIL <>", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailGreaterThan(String value) {
            addCriterion("INST_EMAIL >", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailGreaterThanOrEqualTo(String value) {
            addCriterion("INST_EMAIL >=", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailLessThan(String value) {
            addCriterion("INST_EMAIL <", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailLessThanOrEqualTo(String value) {
            addCriterion("INST_EMAIL <=", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailLike(String value) {
            addCriterion("INST_EMAIL like", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailNotLike(String value) {
            addCriterion("INST_EMAIL not like", value, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailIn(List<String> values) {
            addCriterion("INST_EMAIL in", values, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailNotIn(List<String> values) {
            addCriterion("INST_EMAIL not in", values, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailBetween(String value1, String value2) {
            addCriterion("INST_EMAIL between", value1, value2, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstEmailNotBetween(String value1, String value2) {
            addCriterion("INST_EMAIL not between", value1, value2, "instEmail");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeIsNull() {
            addCriterion("INST_TELPHOME is null");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeIsNotNull() {
            addCriterion("INST_TELPHOME is not null");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeEqualTo(String value) {
            addCriterion("INST_TELPHOME =", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeNotEqualTo(String value) {
            addCriterion("INST_TELPHOME <>", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeGreaterThan(String value) {
            addCriterion("INST_TELPHOME >", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeGreaterThanOrEqualTo(String value) {
            addCriterion("INST_TELPHOME >=", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeLessThan(String value) {
            addCriterion("INST_TELPHOME <", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeLessThanOrEqualTo(String value) {
            addCriterion("INST_TELPHOME <=", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeLike(String value) {
            addCriterion("INST_TELPHOME like", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeNotLike(String value) {
            addCriterion("INST_TELPHOME not like", value, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeIn(List<String> values) {
            addCriterion("INST_TELPHOME in", values, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeNotIn(List<String> values) {
            addCriterion("INST_TELPHOME not in", values, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeBetween(String value1, String value2) {
            addCriterion("INST_TELPHOME between", value1, value2, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andInstTelphomeNotBetween(String value1, String value2) {
            addCriterion("INST_TELPHOME not between", value1, value2, "instTelphome");
            return (Criteria) this;
        }

        public Criteria andCreateByIdIsNull() {
            addCriterion("CREATE_BY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIdIsNotNull() {
            addCriterion("CREATE_BY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByIdEqualTo(String value) {
            addCriterion("CREATE_BY_ID =", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdNotEqualTo(String value) {
            addCriterion("CREATE_BY_ID <>", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdGreaterThan(String value) {
            addCriterion("CREATE_BY_ID >", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY_ID >=", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdLessThan(String value) {
            addCriterion("CREATE_BY_ID <", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY_ID <=", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdLike(String value) {
            addCriterion("CREATE_BY_ID like", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdNotLike(String value) {
            addCriterion("CREATE_BY_ID not like", value, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdIn(List<String> values) {
            addCriterion("CREATE_BY_ID in", values, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdNotIn(List<String> values) {
            addCriterion("CREATE_BY_ID not in", values, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdBetween(String value1, String value2) {
            addCriterion("CREATE_BY_ID between", value1, value2, "createById");
            return (Criteria) this;
        }

        public Criteria andCreateByIdNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY_ID not between", value1, value2, "createById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdIsNull() {
            addCriterion("UPDATE_BY_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdIsNotNull() {
            addCriterion("UPDATE_BY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdEqualTo(String value) {
            addCriterion("UPDATE_BY_ID =", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdNotEqualTo(String value) {
            addCriterion("UPDATE_BY_ID <>", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdGreaterThan(String value) {
            addCriterion("UPDATE_BY_ID >", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY_ID >=", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdLessThan(String value) {
            addCriterion("UPDATE_BY_ID <", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY_ID <=", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdLike(String value) {
            addCriterion("UPDATE_BY_ID like", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdNotLike(String value) {
            addCriterion("UPDATE_BY_ID not like", value, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdIn(List<String> values) {
            addCriterion("UPDATE_BY_ID in", values, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdNotIn(List<String> values) {
            addCriterion("UPDATE_BY_ID not in", values, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdBetween(String value1, String value2) {
            addCriterion("UPDATE_BY_ID between", value1, value2, "updateById");
            return (Criteria) this;
        }

        public Criteria andUpdateByIdNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY_ID not between", value1, value2, "updateById");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("UPDATE_TIME like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("UPDATE_TIME not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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