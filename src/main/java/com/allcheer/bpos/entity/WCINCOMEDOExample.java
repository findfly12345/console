package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class WCINCOMEDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WCINCOMEDOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateIsNull() {
            addCriterion("INCOME_RECV_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateIsNotNull() {
            addCriterion("INCOME_RECV_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateEqualTo(String value) {
            addCriterion("INCOME_RECV_DATE =", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateNotEqualTo(String value) {
            addCriterion("INCOME_RECV_DATE <>", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateGreaterThan(String value) {
            addCriterion("INCOME_RECV_DATE >", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_RECV_DATE >=", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateLessThan(String value) {
            addCriterion("INCOME_RECV_DATE <", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateLessThanOrEqualTo(String value) {
            addCriterion("INCOME_RECV_DATE <=", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateLike(String value) {
            addCriterion("INCOME_RECV_DATE like", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateNotLike(String value) {
            addCriterion("INCOME_RECV_DATE not like", value, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateIn(List<String> values) {
            addCriterion("INCOME_RECV_DATE in", values, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateNotIn(List<String> values) {
            addCriterion("INCOME_RECV_DATE not in", values, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateBetween(String value1, String value2) {
            addCriterion("INCOME_RECV_DATE between", value1, value2, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvDateNotBetween(String value1, String value2) {
            addCriterion("INCOME_RECV_DATE not between", value1, value2, "incomeRecvDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeIsNull() {
            addCriterion("INCOME_RECV_TIME is null");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeIsNotNull() {
            addCriterion("INCOME_RECV_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeEqualTo(String value) {
            addCriterion("INCOME_RECV_TIME =", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeNotEqualTo(String value) {
            addCriterion("INCOME_RECV_TIME <>", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeGreaterThan(String value) {
            addCriterion("INCOME_RECV_TIME >", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_RECV_TIME >=", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeLessThan(String value) {
            addCriterion("INCOME_RECV_TIME <", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeLessThanOrEqualTo(String value) {
            addCriterion("INCOME_RECV_TIME <=", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeLike(String value) {
            addCriterion("INCOME_RECV_TIME like", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeNotLike(String value) {
            addCriterion("INCOME_RECV_TIME not like", value, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeIn(List<String> values) {
            addCriterion("INCOME_RECV_TIME in", values, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeNotIn(List<String> values) {
            addCriterion("INCOME_RECV_TIME not in", values, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeBetween(String value1, String value2) {
            addCriterion("INCOME_RECV_TIME between", value1, value2, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeRecvTimeNotBetween(String value1, String value2) {
            addCriterion("INCOME_RECV_TIME not between", value1, value2, "incomeRecvTime");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeIsNull() {
            addCriterion("INCOME_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeIsNotNull() {
            addCriterion("INCOME_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeEqualTo(String value) {
            addCriterion("INCOME_TYPE =", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeNotEqualTo(String value) {
            addCriterion("INCOME_TYPE <>", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeGreaterThan(String value) {
            addCriterion("INCOME_TYPE >", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_TYPE >=", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeLessThan(String value) {
            addCriterion("INCOME_TYPE <", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeLessThanOrEqualTo(String value) {
            addCriterion("INCOME_TYPE <=", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeLike(String value) {
            addCriterion("INCOME_TYPE like", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeNotLike(String value) {
            addCriterion("INCOME_TYPE not like", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeIn(List<String> values) {
            addCriterion("INCOME_TYPE in", values, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeNotIn(List<String> values) {
            addCriterion("INCOME_TYPE not in", values, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeBetween(String value1, String value2) {
            addCriterion("INCOME_TYPE between", value1, value2, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeNotBetween(String value1, String value2) {
            addCriterion("INCOME_TYPE not between", value1, value2, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformIsNull() {
            addCriterion("INCOME_PLATFORM is null");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformIsNotNull() {
            addCriterion("INCOME_PLATFORM is not null");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformEqualTo(String value) {
            addCriterion("INCOME_PLATFORM =", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformNotEqualTo(String value) {
            addCriterion("INCOME_PLATFORM <>", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformGreaterThan(String value) {
            addCriterion("INCOME_PLATFORM >", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_PLATFORM >=", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformLessThan(String value) {
            addCriterion("INCOME_PLATFORM <", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformLessThanOrEqualTo(String value) {
            addCriterion("INCOME_PLATFORM <=", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformLike(String value) {
            addCriterion("INCOME_PLATFORM like", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformNotLike(String value) {
            addCriterion("INCOME_PLATFORM not like", value, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformIn(List<String> values) {
            addCriterion("INCOME_PLATFORM in", values, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformNotIn(List<String> values) {
            addCriterion("INCOME_PLATFORM not in", values, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformBetween(String value1, String value2) {
            addCriterion("INCOME_PLATFORM between", value1, value2, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomePlatformNotBetween(String value1, String value2) {
            addCriterion("INCOME_PLATFORM not between", value1, value2, "incomePlatform");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIsNull() {
            addCriterion("INCOME_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIsNotNull() {
            addCriterion("INCOME_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountEqualTo(String value) {
            addCriterion("INCOME_AMOUNT =", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotEqualTo(String value) {
            addCriterion("INCOME_AMOUNT <>", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountGreaterThan(String value) {
            addCriterion("INCOME_AMOUNT >", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_AMOUNT >=", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountLessThan(String value) {
            addCriterion("INCOME_AMOUNT <", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountLessThanOrEqualTo(String value) {
            addCriterion("INCOME_AMOUNT <=", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountLike(String value) {
            addCriterion("INCOME_AMOUNT like", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotLike(String value) {
            addCriterion("INCOME_AMOUNT not like", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIn(List<String> values) {
            addCriterion("INCOME_AMOUNT in", values, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotIn(List<String> values) {
            addCriterion("INCOME_AMOUNT not in", values, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountBetween(String value1, String value2) {
            addCriterion("INCOME_AMOUNT between", value1, value2, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotBetween(String value1, String value2) {
            addCriterion("INCOME_AMOUNT not between", value1, value2, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeIdIsNull() {
            addCriterion("INCOME_ID is null");
            return (Criteria) this;
        }

        public Criteria andIncomeIdIsNotNull() {
            addCriterion("INCOME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeIdEqualTo(String value) {
            addCriterion("INCOME_ID =", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotEqualTo(String value) {
            addCriterion("INCOME_ID <>", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdGreaterThan(String value) {
            addCriterion("INCOME_ID >", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_ID >=", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdLessThan(String value) {
            addCriterion("INCOME_ID <", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdLessThanOrEqualTo(String value) {
            addCriterion("INCOME_ID <=", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdLike(String value) {
            addCriterion("INCOME_ID like", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotLike(String value) {
            addCriterion("INCOME_ID not like", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdIn(List<String> values) {
            addCriterion("INCOME_ID in", values, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotIn(List<String> values) {
            addCriterion("INCOME_ID not in", values, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdBetween(String value1, String value2) {
            addCriterion("INCOME_ID between", value1, value2, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotBetween(String value1, String value2) {
            addCriterion("INCOME_ID not between", value1, value2, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdIsNull() {
            addCriterion("INCOME_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdIsNotNull() {
            addCriterion("INCOME_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdEqualTo(String value) {
            addCriterion("INCOME_ORG_ID =", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdNotEqualTo(String value) {
            addCriterion("INCOME_ORG_ID <>", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdGreaterThan(String value) {
            addCriterion("INCOME_ORG_ID >", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_ORG_ID >=", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdLessThan(String value) {
            addCriterion("INCOME_ORG_ID <", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdLessThanOrEqualTo(String value) {
            addCriterion("INCOME_ORG_ID <=", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdLike(String value) {
            addCriterion("INCOME_ORG_ID like", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdNotLike(String value) {
            addCriterion("INCOME_ORG_ID not like", value, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdIn(List<String> values) {
            addCriterion("INCOME_ORG_ID in", values, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdNotIn(List<String> values) {
            addCriterion("INCOME_ORG_ID not in", values, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdBetween(String value1, String value2) {
            addCriterion("INCOME_ORG_ID between", value1, value2, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeOrgIdNotBetween(String value1, String value2) {
            addCriterion("INCOME_ORG_ID not between", value1, value2, "incomeOrgId");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileIsNull() {
            addCriterion("INCOME_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileIsNotNull() {
            addCriterion("INCOME_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileEqualTo(String value) {
            addCriterion("INCOME_MOBILE =", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileNotEqualTo(String value) {
            addCriterion("INCOME_MOBILE <>", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileGreaterThan(String value) {
            addCriterion("INCOME_MOBILE >", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_MOBILE >=", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileLessThan(String value) {
            addCriterion("INCOME_MOBILE <", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileLessThanOrEqualTo(String value) {
            addCriterion("INCOME_MOBILE <=", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileLike(String value) {
            addCriterion("INCOME_MOBILE like", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileNotLike(String value) {
            addCriterion("INCOME_MOBILE not like", value, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileIn(List<String> values) {
            addCriterion("INCOME_MOBILE in", values, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileNotIn(List<String> values) {
            addCriterion("INCOME_MOBILE not in", values, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileBetween(String value1, String value2) {
            addCriterion("INCOME_MOBILE between", value1, value2, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeMobileNotBetween(String value1, String value2) {
            addCriterion("INCOME_MOBILE not between", value1, value2, "incomeMobile");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusIsNull() {
            addCriterion("INCOME_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusIsNotNull() {
            addCriterion("INCOME_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusEqualTo(String value) {
            addCriterion("INCOME_STATUS =", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusNotEqualTo(String value) {
            addCriterion("INCOME_STATUS <>", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusGreaterThan(String value) {
            addCriterion("INCOME_STATUS >", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_STATUS >=", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusLessThan(String value) {
            addCriterion("INCOME_STATUS <", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusLessThanOrEqualTo(String value) {
            addCriterion("INCOME_STATUS <=", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusLike(String value) {
            addCriterion("INCOME_STATUS like", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusNotLike(String value) {
            addCriterion("INCOME_STATUS not like", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusIn(List<String> values) {
            addCriterion("INCOME_STATUS in", values, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusNotIn(List<String> values) {
            addCriterion("INCOME_STATUS not in", values, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusBetween(String value1, String value2) {
            addCriterion("INCOME_STATUS between", value1, value2, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusNotBetween(String value1, String value2) {
            addCriterion("INCOME_STATUS not between", value1, value2, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusIsNull() {
            addCriterion("INCOME_AUDIT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusIsNotNull() {
            addCriterion("INCOME_AUDIT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusEqualTo(String value) {
            addCriterion("INCOME_AUDIT_STATUS =", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusNotEqualTo(String value) {
            addCriterion("INCOME_AUDIT_STATUS <>", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusGreaterThan(String value) {
            addCriterion("INCOME_AUDIT_STATUS >", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_AUDIT_STATUS >=", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusLessThan(String value) {
            addCriterion("INCOME_AUDIT_STATUS <", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("INCOME_AUDIT_STATUS <=", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusLike(String value) {
            addCriterion("INCOME_AUDIT_STATUS like", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusNotLike(String value) {
            addCriterion("INCOME_AUDIT_STATUS not like", value, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusIn(List<String> values) {
            addCriterion("INCOME_AUDIT_STATUS in", values, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusNotIn(List<String> values) {
            addCriterion("INCOME_AUDIT_STATUS not in", values, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusBetween(String value1, String value2) {
            addCriterion("INCOME_AUDIT_STATUS between", value1, value2, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeAuditStatusNotBetween(String value1, String value2) {
            addCriterion("INCOME_AUDIT_STATUS not between", value1, value2, "incomeAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("MEMBER_ID =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("MEMBER_ID <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("MEMBER_ID >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("MEMBER_ID <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("MEMBER_ID like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("MEMBER_ID not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("MEMBER_ID in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("MEMBER_ID not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("MEMBER_ID between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("MEMBER_ID not between", value1, value2, "memberId");
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

        public Criteria andOutMerchantIdIsNull() {
            addCriterion("OUT_MERCHANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdIsNotNull() {
            addCriterion("OUT_MERCHANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdEqualTo(String value) {
            addCriterion("OUT_MERCHANT_ID =", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdNotEqualTo(String value) {
            addCriterion("OUT_MERCHANT_ID <>", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdGreaterThan(String value) {
            addCriterion("OUT_MERCHANT_ID >", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_MERCHANT_ID >=", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdLessThan(String value) {
            addCriterion("OUT_MERCHANT_ID <", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdLessThanOrEqualTo(String value) {
            addCriterion("OUT_MERCHANT_ID <=", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdLike(String value) {
            addCriterion("OUT_MERCHANT_ID like", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdNotLike(String value) {
            addCriterion("OUT_MERCHANT_ID not like", value, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdIn(List<String> values) {
            addCriterion("OUT_MERCHANT_ID in", values, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdNotIn(List<String> values) {
            addCriterion("OUT_MERCHANT_ID not in", values, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdBetween(String value1, String value2) {
            addCriterion("OUT_MERCHANT_ID between", value1, value2, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andOutMerchantIdNotBetween(String value1, String value2) {
            addCriterion("OUT_MERCHANT_ID not between", value1, value2, "outMerchantId");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateIsNull() {
            addCriterion("INCOME_SEND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateIsNotNull() {
            addCriterion("INCOME_SEND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateEqualTo(String value) {
            addCriterion("INCOME_SEND_DATE =", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateNotEqualTo(String value) {
            addCriterion("INCOME_SEND_DATE <>", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateGreaterThan(String value) {
            addCriterion("INCOME_SEND_DATE >", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_SEND_DATE >=", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateLessThan(String value) {
            addCriterion("INCOME_SEND_DATE <", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateLessThanOrEqualTo(String value) {
            addCriterion("INCOME_SEND_DATE <=", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateLike(String value) {
            addCriterion("INCOME_SEND_DATE like", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateNotLike(String value) {
            addCriterion("INCOME_SEND_DATE not like", value, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateIn(List<String> values) {
            addCriterion("INCOME_SEND_DATE in", values, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateNotIn(List<String> values) {
            addCriterion("INCOME_SEND_DATE not in", values, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateBetween(String value1, String value2) {
            addCriterion("INCOME_SEND_DATE between", value1, value2, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendDateNotBetween(String value1, String value2) {
            addCriterion("INCOME_SEND_DATE not between", value1, value2, "incomeSendDate");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeIsNull() {
            addCriterion("INCOME_SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeIsNotNull() {
            addCriterion("INCOME_SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeEqualTo(String value) {
            addCriterion("INCOME_SEND_TIME =", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeNotEqualTo(String value) {
            addCriterion("INCOME_SEND_TIME <>", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeGreaterThan(String value) {
            addCriterion("INCOME_SEND_TIME >", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_SEND_TIME >=", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeLessThan(String value) {
            addCriterion("INCOME_SEND_TIME <", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeLessThanOrEqualTo(String value) {
            addCriterion("INCOME_SEND_TIME <=", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeLike(String value) {
            addCriterion("INCOME_SEND_TIME like", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeNotLike(String value) {
            addCriterion("INCOME_SEND_TIME not like", value, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeIn(List<String> values) {
            addCriterion("INCOME_SEND_TIME in", values, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeNotIn(List<String> values) {
            addCriterion("INCOME_SEND_TIME not in", values, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeBetween(String value1, String value2) {
            addCriterion("INCOME_SEND_TIME between", value1, value2, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andIncomeSendTimeNotBetween(String value1, String value2) {
            addCriterion("INCOME_SEND_TIME not between", value1, value2, "incomeSendTime");
            return (Criteria) this;
        }

        public Criteria andReturnCodeIsNull() {
            addCriterion("RETURN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andReturnCodeIsNotNull() {
            addCriterion("RETURN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andReturnCodeEqualTo(String value) {
            addCriterion("RETURN_CODE =", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotEqualTo(String value) {
            addCriterion("RETURN_CODE <>", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeGreaterThan(String value) {
            addCriterion("RETURN_CODE >", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_CODE >=", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeLessThan(String value) {
            addCriterion("RETURN_CODE <", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeLessThanOrEqualTo(String value) {
            addCriterion("RETURN_CODE <=", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeLike(String value) {
            addCriterion("RETURN_CODE like", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotLike(String value) {
            addCriterion("RETURN_CODE not like", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeIn(List<String> values) {
            addCriterion("RETURN_CODE in", values, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotIn(List<String> values) {
            addCriterion("RETURN_CODE not in", values, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeBetween(String value1, String value2) {
            addCriterion("RETURN_CODE between", value1, value2, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotBetween(String value1, String value2) {
            addCriterion("RETURN_CODE not between", value1, value2, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnMsgIsNull() {
            addCriterion("RETURN_MSG is null");
            return (Criteria) this;
        }

        public Criteria andReturnMsgIsNotNull() {
            addCriterion("RETURN_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andReturnMsgEqualTo(String value) {
            addCriterion("RETURN_MSG =", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotEqualTo(String value) {
            addCriterion("RETURN_MSG <>", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgGreaterThan(String value) {
            addCriterion("RETURN_MSG >", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_MSG >=", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgLessThan(String value) {
            addCriterion("RETURN_MSG <", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgLessThanOrEqualTo(String value) {
            addCriterion("RETURN_MSG <=", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgLike(String value) {
            addCriterion("RETURN_MSG like", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotLike(String value) {
            addCriterion("RETURN_MSG not like", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgIn(List<String> values) {
            addCriterion("RETURN_MSG in", values, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotIn(List<String> values) {
            addCriterion("RETURN_MSG not in", values, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgBetween(String value1, String value2) {
            addCriterion("RETURN_MSG between", value1, value2, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotBetween(String value1, String value2) {
            addCriterion("RETURN_MSG not between", value1, value2, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1IsNull() {
            addCriterion("RETURN_SEQ1 is null");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1IsNotNull() {
            addCriterion("RETURN_SEQ1 is not null");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1EqualTo(String value) {
            addCriterion("RETURN_SEQ1 =", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1NotEqualTo(String value) {
            addCriterion("RETURN_SEQ1 <>", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1GreaterThan(String value) {
            addCriterion("RETURN_SEQ1 >", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1GreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_SEQ1 >=", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1LessThan(String value) {
            addCriterion("RETURN_SEQ1 <", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1LessThanOrEqualTo(String value) {
            addCriterion("RETURN_SEQ1 <=", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1Like(String value) {
            addCriterion("RETURN_SEQ1 like", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1NotLike(String value) {
            addCriterion("RETURN_SEQ1 not like", value, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1In(List<String> values) {
            addCriterion("RETURN_SEQ1 in", values, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1NotIn(List<String> values) {
            addCriterion("RETURN_SEQ1 not in", values, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1Between(String value1, String value2) {
            addCriterion("RETURN_SEQ1 between", value1, value2, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq1NotBetween(String value1, String value2) {
            addCriterion("RETURN_SEQ1 not between", value1, value2, "returnSeq1");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2IsNull() {
            addCriterion("RETURN_SEQ2 is null");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2IsNotNull() {
            addCriterion("RETURN_SEQ2 is not null");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2EqualTo(String value) {
            addCriterion("RETURN_SEQ2 =", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2NotEqualTo(String value) {
            addCriterion("RETURN_SEQ2 <>", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2GreaterThan(String value) {
            addCriterion("RETURN_SEQ2 >", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2GreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_SEQ2 >=", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2LessThan(String value) {
            addCriterion("RETURN_SEQ2 <", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2LessThanOrEqualTo(String value) {
            addCriterion("RETURN_SEQ2 <=", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2Like(String value) {
            addCriterion("RETURN_SEQ2 like", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2NotLike(String value) {
            addCriterion("RETURN_SEQ2 not like", value, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2In(List<String> values) {
            addCriterion("RETURN_SEQ2 in", values, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2NotIn(List<String> values) {
            addCriterion("RETURN_SEQ2 not in", values, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2Between(String value1, String value2) {
            addCriterion("RETURN_SEQ2 between", value1, value2, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnSeq2NotBetween(String value1, String value2) {
            addCriterion("RETURN_SEQ2 not between", value1, value2, "returnSeq2");
            return (Criteria) this;
        }

        public Criteria andReturnDateIsNull() {
            addCriterion("RETURN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReturnDateIsNotNull() {
            addCriterion("RETURN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReturnDateEqualTo(String value) {
            addCriterion("RETURN_DATE =", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateNotEqualTo(String value) {
            addCriterion("RETURN_DATE <>", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateGreaterThan(String value) {
            addCriterion("RETURN_DATE >", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_DATE >=", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateLessThan(String value) {
            addCriterion("RETURN_DATE <", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateLessThanOrEqualTo(String value) {
            addCriterion("RETURN_DATE <=", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateLike(String value) {
            addCriterion("RETURN_DATE like", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateNotLike(String value) {
            addCriterion("RETURN_DATE not like", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateIn(List<String> values) {
            addCriterion("RETURN_DATE in", values, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateNotIn(List<String> values) {
            addCriterion("RETURN_DATE not in", values, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateBetween(String value1, String value2) {
            addCriterion("RETURN_DATE between", value1, value2, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateNotBetween(String value1, String value2) {
            addCriterion("RETURN_DATE not between", value1, value2, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIsNull() {
            addCriterion("RETURN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIsNotNull() {
            addCriterion("RETURN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeEqualTo(String value) {
            addCriterion("RETURN_TIME =", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotEqualTo(String value) {
            addCriterion("RETURN_TIME <>", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThan(String value) {
            addCriterion("RETURN_TIME >", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_TIME >=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThan(String value) {
            addCriterion("RETURN_TIME <", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThanOrEqualTo(String value) {
            addCriterion("RETURN_TIME <=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLike(String value) {
            addCriterion("RETURN_TIME like", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotLike(String value) {
            addCriterion("RETURN_TIME not like", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIn(List<String> values) {
            addCriterion("RETURN_TIME in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotIn(List<String> values) {
            addCriterion("RETURN_TIME not in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeBetween(String value1, String value2) {
            addCriterion("RETURN_TIME between", value1, value2, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotBetween(String value1, String value2) {
            addCriterion("RETURN_TIME not between", value1, value2, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIsNull() {
            addCriterion("RETURN_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIsNotNull() {
            addCriterion("RETURN_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andReturnStatusEqualTo(String value) {
            addCriterion("RETURN_STATUS =", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotEqualTo(String value) {
            addCriterion("RETURN_STATUS <>", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusGreaterThan(String value) {
            addCriterion("RETURN_STATUS >", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_STATUS >=", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLessThan(String value) {
            addCriterion("RETURN_STATUS <", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLessThanOrEqualTo(String value) {
            addCriterion("RETURN_STATUS <=", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLike(String value) {
            addCriterion("RETURN_STATUS like", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotLike(String value) {
            addCriterion("RETURN_STATUS not like", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIn(List<String> values) {
            addCriterion("RETURN_STATUS in", values, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotIn(List<String> values) {
            addCriterion("RETURN_STATUS not in", values, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusBetween(String value1, String value2) {
            addCriterion("RETURN_STATUS between", value1, value2, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotBetween(String value1, String value2) {
            addCriterion("RETURN_STATUS not between", value1, value2, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andClearDateIsNull() {
            addCriterion("CLEAR_DATE is null");
            return (Criteria) this;
        }

        public Criteria andClearDateIsNotNull() {
            addCriterion("CLEAR_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andClearDateEqualTo(String value) {
            addCriterion("CLEAR_DATE =", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateNotEqualTo(String value) {
            addCriterion("CLEAR_DATE <>", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateGreaterThan(String value) {
            addCriterion("CLEAR_DATE >", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateGreaterThanOrEqualTo(String value) {
            addCriterion("CLEAR_DATE >=", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateLessThan(String value) {
            addCriterion("CLEAR_DATE <", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateLessThanOrEqualTo(String value) {
            addCriterion("CLEAR_DATE <=", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateLike(String value) {
            addCriterion("CLEAR_DATE like", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateNotLike(String value) {
            addCriterion("CLEAR_DATE not like", value, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateIn(List<String> values) {
            addCriterion("CLEAR_DATE in", values, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateNotIn(List<String> values) {
            addCriterion("CLEAR_DATE not in", values, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateBetween(String value1, String value2) {
            addCriterion("CLEAR_DATE between", value1, value2, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearDateNotBetween(String value1, String value2) {
            addCriterion("CLEAR_DATE not between", value1, value2, "clearDate");
            return (Criteria) this;
        }

        public Criteria andClearTimeIsNull() {
            addCriterion("CLEAR_TIME is null");
            return (Criteria) this;
        }

        public Criteria andClearTimeIsNotNull() {
            addCriterion("CLEAR_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andClearTimeEqualTo(String value) {
            addCriterion("CLEAR_TIME =", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeNotEqualTo(String value) {
            addCriterion("CLEAR_TIME <>", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeGreaterThan(String value) {
            addCriterion("CLEAR_TIME >", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CLEAR_TIME >=", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeLessThan(String value) {
            addCriterion("CLEAR_TIME <", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeLessThanOrEqualTo(String value) {
            addCriterion("CLEAR_TIME <=", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeLike(String value) {
            addCriterion("CLEAR_TIME like", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeNotLike(String value) {
            addCriterion("CLEAR_TIME not like", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeIn(List<String> values) {
            addCriterion("CLEAR_TIME in", values, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeNotIn(List<String> values) {
            addCriterion("CLEAR_TIME not in", values, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeBetween(String value1, String value2) {
            addCriterion("CLEAR_TIME between", value1, value2, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeNotBetween(String value1, String value2) {
            addCriterion("CLEAR_TIME not between", value1, value2, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearIdIsNull() {
            addCriterion("CLEAR_ID is null");
            return (Criteria) this;
        }

        public Criteria andClearIdIsNotNull() {
            addCriterion("CLEAR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClearIdEqualTo(String value) {
            addCriterion("CLEAR_ID =", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotEqualTo(String value) {
            addCriterion("CLEAR_ID <>", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdGreaterThan(String value) {
            addCriterion("CLEAR_ID >", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLEAR_ID >=", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdLessThan(String value) {
            addCriterion("CLEAR_ID <", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdLessThanOrEqualTo(String value) {
            addCriterion("CLEAR_ID <=", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdLike(String value) {
            addCriterion("CLEAR_ID like", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotLike(String value) {
            addCriterion("CLEAR_ID not like", value, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdIn(List<String> values) {
            addCriterion("CLEAR_ID in", values, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotIn(List<String> values) {
            addCriterion("CLEAR_ID not in", values, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdBetween(String value1, String value2) {
            addCriterion("CLEAR_ID between", value1, value2, "clearId");
            return (Criteria) this;
        }

        public Criteria andClearIdNotBetween(String value1, String value2) {
            addCriterion("CLEAR_ID not between", value1, value2, "clearId");
            return (Criteria) this;
        }

        public Criteria andResv1IsNull() {
            addCriterion("RESV1 is null");
            return (Criteria) this;
        }

        public Criteria andResv1IsNotNull() {
            addCriterion("RESV1 is not null");
            return (Criteria) this;
        }

        public Criteria andResv1EqualTo(String value) {
            addCriterion("RESV1 =", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1NotEqualTo(String value) {
            addCriterion("RESV1 <>", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1GreaterThan(String value) {
            addCriterion("RESV1 >", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1GreaterThanOrEqualTo(String value) {
            addCriterion("RESV1 >=", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1LessThan(String value) {
            addCriterion("RESV1 <", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1LessThanOrEqualTo(String value) {
            addCriterion("RESV1 <=", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1Like(String value) {
            addCriterion("RESV1 like", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1NotLike(String value) {
            addCriterion("RESV1 not like", value, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1In(List<String> values) {
            addCriterion("RESV1 in", values, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1NotIn(List<String> values) {
            addCriterion("RESV1 not in", values, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1Between(String value1, String value2) {
            addCriterion("RESV1 between", value1, value2, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv1NotBetween(String value1, String value2) {
            addCriterion("RESV1 not between", value1, value2, "resv1");
            return (Criteria) this;
        }

        public Criteria andResv2IsNull() {
            addCriterion("RESV2 is null");
            return (Criteria) this;
        }

        public Criteria andResv2IsNotNull() {
            addCriterion("RESV2 is not null");
            return (Criteria) this;
        }

        public Criteria andResv2EqualTo(String value) {
            addCriterion("RESV2 =", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2NotEqualTo(String value) {
            addCriterion("RESV2 <>", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2GreaterThan(String value) {
            addCriterion("RESV2 >", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2GreaterThanOrEqualTo(String value) {
            addCriterion("RESV2 >=", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2LessThan(String value) {
            addCriterion("RESV2 <", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2LessThanOrEqualTo(String value) {
            addCriterion("RESV2 <=", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2Like(String value) {
            addCriterion("RESV2 like", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2NotLike(String value) {
            addCriterion("RESV2 not like", value, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2In(List<String> values) {
            addCriterion("RESV2 in", values, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2NotIn(List<String> values) {
            addCriterion("RESV2 not in", values, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2Between(String value1, String value2) {
            addCriterion("RESV2 between", value1, value2, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv2NotBetween(String value1, String value2) {
            addCriterion("RESV2 not between", value1, value2, "resv2");
            return (Criteria) this;
        }

        public Criteria andResv3IsNull() {
            addCriterion("RESV3 is null");
            return (Criteria) this;
        }

        public Criteria andResv3IsNotNull() {
            addCriterion("RESV3 is not null");
            return (Criteria) this;
        }

        public Criteria andResv3EqualTo(String value) {
            addCriterion("RESV3 =", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3NotEqualTo(String value) {
            addCriterion("RESV3 <>", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3GreaterThan(String value) {
            addCriterion("RESV3 >", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3GreaterThanOrEqualTo(String value) {
            addCriterion("RESV3 >=", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3LessThan(String value) {
            addCriterion("RESV3 <", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3LessThanOrEqualTo(String value) {
            addCriterion("RESV3 <=", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3Like(String value) {
            addCriterion("RESV3 like", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3NotLike(String value) {
            addCriterion("RESV3 not like", value, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3In(List<String> values) {
            addCriterion("RESV3 in", values, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3NotIn(List<String> values) {
            addCriterion("RESV3 not in", values, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3Between(String value1, String value2) {
            addCriterion("RESV3 between", value1, value2, "resv3");
            return (Criteria) this;
        }

        public Criteria andResv3NotBetween(String value1, String value2) {
            addCriterion("RESV3 not between", value1, value2, "resv3");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNull() {
            addCriterion("OUT_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNotNull() {
            addCriterion("OUT_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdEqualTo(String value) {
            addCriterion("OUT_ORDER_ID =", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotEqualTo(String value) {
            addCriterion("OUT_ORDER_ID <>", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThan(String value) {
            addCriterion("OUT_ORDER_ID >", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_ORDER_ID >=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThan(String value) {
            addCriterion("OUT_ORDER_ID <", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThanOrEqualTo(String value) {
            addCriterion("OUT_ORDER_ID <=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLike(String value) {
            addCriterion("OUT_ORDER_ID like", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotLike(String value) {
            addCriterion("OUT_ORDER_ID not like", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIn(List<String> values) {
            addCriterion("OUT_ORDER_ID in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotIn(List<String> values) {
            addCriterion("OUT_ORDER_ID not in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdBetween(String value1, String value2) {
            addCriterion("OUT_ORDER_ID between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotBetween(String value1, String value2) {
            addCriterion("OUT_ORDER_ID not between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andCheckFlagIsNull() {
            addCriterion("CHECK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCheckFlagIsNotNull() {
            addCriterion("CHECK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCheckFlagEqualTo(String value) {
            addCriterion("CHECK_FLAG =", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagNotEqualTo(String value) {
            addCriterion("CHECK_FLAG <>", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagGreaterThan(String value) {
            addCriterion("CHECK_FLAG >", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_FLAG >=", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagLessThan(String value) {
            addCriterion("CHECK_FLAG <", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagLessThanOrEqualTo(String value) {
            addCriterion("CHECK_FLAG <=", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagLike(String value) {
            addCriterion("CHECK_FLAG like", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagNotLike(String value) {
            addCriterion("CHECK_FLAG not like", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagIn(List<String> values) {
            addCriterion("CHECK_FLAG in", values, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagNotIn(List<String> values) {
            addCriterion("CHECK_FLAG not in", values, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagBetween(String value1, String value2) {
            addCriterion("CHECK_FLAG between", value1, value2, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagNotBetween(String value1, String value2) {
            addCriterion("CHECK_FLAG not between", value1, value2, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNull() {
            addCriterion("AGENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("AGENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(String value) {
            addCriterion("AGENT_ID =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(String value) {
            addCriterion("AGENT_ID <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(String value) {
            addCriterion("AGENT_ID >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(String value) {
            addCriterion("AGENT_ID >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(String value) {
            addCriterion("AGENT_ID <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(String value) {
            addCriterion("AGENT_ID <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLike(String value) {
            addCriterion("AGENT_ID like", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotLike(String value) {
            addCriterion("AGENT_ID not like", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<String> values) {
            addCriterion("AGENT_ID in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<String> values) {
            addCriterion("AGENT_ID not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(String value1, String value2) {
            addCriterion("AGENT_ID between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(String value1, String value2) {
            addCriterion("AGENT_ID not between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andCardFlagIsNull() {
            addCriterion("CARD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCardFlagIsNotNull() {
            addCriterion("CARD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCardFlagEqualTo(String value) {
            addCriterion("CARD_FLAG =", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagNotEqualTo(String value) {
            addCriterion("CARD_FLAG <>", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagGreaterThan(String value) {
            addCriterion("CARD_FLAG >", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_FLAG >=", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagLessThan(String value) {
            addCriterion("CARD_FLAG <", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagLessThanOrEqualTo(String value) {
            addCriterion("CARD_FLAG <=", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagLike(String value) {
            addCriterion("CARD_FLAG like", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagNotLike(String value) {
            addCriterion("CARD_FLAG not like", value, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagIn(List<String> values) {
            addCriterion("CARD_FLAG in", values, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagNotIn(List<String> values) {
            addCriterion("CARD_FLAG not in", values, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagBetween(String value1, String value2) {
            addCriterion("CARD_FLAG between", value1, value2, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andCardFlagNotBetween(String value1, String value2) {
            addCriterion("CARD_FLAG not between", value1, value2, "cardFlag");
            return (Criteria) this;
        }

        public Criteria andTransFeeIsNull() {
            addCriterion("TRANS_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTransFeeIsNotNull() {
            addCriterion("TRANS_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTransFeeEqualTo(String value) {
            addCriterion("TRANS_FEE =", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeNotEqualTo(String value) {
            addCriterion("TRANS_FEE <>", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeGreaterThan(String value) {
            addCriterion("TRANS_FEE >", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_FEE >=", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeLessThan(String value) {
            addCriterion("TRANS_FEE <", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeLessThanOrEqualTo(String value) {
            addCriterion("TRANS_FEE <=", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeLike(String value) {
            addCriterion("TRANS_FEE like", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeNotLike(String value) {
            addCriterion("TRANS_FEE not like", value, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeIn(List<String> values) {
            addCriterion("TRANS_FEE in", values, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeNotIn(List<String> values) {
            addCriterion("TRANS_FEE not in", values, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeBetween(String value1, String value2) {
            addCriterion("TRANS_FEE between", value1, value2, "transFee");
            return (Criteria) this;
        }

        public Criteria andTransFeeNotBetween(String value1, String value2) {
            addCriterion("TRANS_FEE not between", value1, value2, "transFee");
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