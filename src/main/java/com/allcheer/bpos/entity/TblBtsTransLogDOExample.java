package com.allcheer.bpos.entity;

import java.util.ArrayList;
import java.util.List;

public class TblBtsTransLogDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblBtsTransLogDOExample() {
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

        public Criteria andOrdIdIsNull() {
            addCriterion("ORD_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrdIdIsNotNull() {
            addCriterion("ORD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrdIdEqualTo(String value) {
            addCriterion("ORD_ID =", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotEqualTo(String value) {
            addCriterion("ORD_ID <>", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThan(String value) {
            addCriterion("ORD_ID >", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORD_ID >=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThan(String value) {
            addCriterion("ORD_ID <", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThanOrEqualTo(String value) {
            addCriterion("ORD_ID <=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLike(String value) {
            addCriterion("ORD_ID like", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotLike(String value) {
            addCriterion("ORD_ID not like", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdIn(List<String> values) {
            addCriterion("ORD_ID in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotIn(List<String> values) {
            addCriterion("ORD_ID not in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdBetween(String value1, String value2) {
            addCriterion("ORD_ID between", value1, value2, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotBetween(String value1, String value2) {
            addCriterion("ORD_ID not between", value1, value2, "ordId");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("CARD_NO not between", value1, value2, "cardNo");
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

        public Criteria andPnrDevIdIsNull() {
            addCriterion("PNR_DEV_ID is null");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdIsNotNull() {
            addCriterion("PNR_DEV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdEqualTo(String value) {
            addCriterion("PNR_DEV_ID =", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdNotEqualTo(String value) {
            addCriterion("PNR_DEV_ID <>", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdGreaterThan(String value) {
            addCriterion("PNR_DEV_ID >", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdGreaterThanOrEqualTo(String value) {
            addCriterion("PNR_DEV_ID >=", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdLessThan(String value) {
            addCriterion("PNR_DEV_ID <", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdLessThanOrEqualTo(String value) {
            addCriterion("PNR_DEV_ID <=", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdLike(String value) {
            addCriterion("PNR_DEV_ID like", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdNotLike(String value) {
            addCriterion("PNR_DEV_ID not like", value, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdIn(List<String> values) {
            addCriterion("PNR_DEV_ID in", values, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdNotIn(List<String> values) {
            addCriterion("PNR_DEV_ID not in", values, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdBetween(String value1, String value2) {
            addCriterion("PNR_DEV_ID between", value1, value2, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPnrDevIdNotBetween(String value1, String value2) {
            addCriterion("PNR_DEV_ID not between", value1, value2, "pnrDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdIsNull() {
            addCriterion("POS_DEV_ID is null");
            return (Criteria) this;
        }

        public Criteria andPosDevIdIsNotNull() {
            addCriterion("POS_DEV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPosDevIdEqualTo(String value) {
            addCriterion("POS_DEV_ID =", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdNotEqualTo(String value) {
            addCriterion("POS_DEV_ID <>", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdGreaterThan(String value) {
            addCriterion("POS_DEV_ID >", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdGreaterThanOrEqualTo(String value) {
            addCriterion("POS_DEV_ID >=", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdLessThan(String value) {
            addCriterion("POS_DEV_ID <", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdLessThanOrEqualTo(String value) {
            addCriterion("POS_DEV_ID <=", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdLike(String value) {
            addCriterion("POS_DEV_ID like", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdNotLike(String value) {
            addCriterion("POS_DEV_ID not like", value, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdIn(List<String> values) {
            addCriterion("POS_DEV_ID in", values, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdNotIn(List<String> values) {
            addCriterion("POS_DEV_ID not in", values, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdBetween(String value1, String value2) {
            addCriterion("POS_DEV_ID between", value1, value2, "posDevId");
            return (Criteria) this;
        }

        public Criteria andPosDevIdNotBetween(String value1, String value2) {
            addCriterion("POS_DEV_ID not between", value1, value2, "posDevId");
            return (Criteria) this;
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

        public Criteria andOrdAmtIsNull() {
            addCriterion("ORD_AMT is null");
            return (Criteria) this;
        }

        public Criteria andOrdAmtIsNotNull() {
            addCriterion("ORD_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andOrdAmtEqualTo(String value) {
            addCriterion("ORD_AMT =", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtNotEqualTo(String value) {
            addCriterion("ORD_AMT <>", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtGreaterThan(String value) {
            addCriterion("ORD_AMT >", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtGreaterThanOrEqualTo(String value) {
            addCriterion("ORD_AMT >=", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtLessThan(String value) {
            addCriterion("ORD_AMT <", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtLessThanOrEqualTo(String value) {
            addCriterion("ORD_AMT <=", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtLike(String value) {
            addCriterion("ORD_AMT like", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtNotLike(String value) {
            addCriterion("ORD_AMT not like", value, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtIn(List<String> values) {
            addCriterion("ORD_AMT in", values, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtNotIn(List<String> values) {
            addCriterion("ORD_AMT not in", values, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtBetween(String value1, String value2) {
            addCriterion("ORD_AMT between", value1, value2, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andOrdAmtNotBetween(String value1, String value2) {
            addCriterion("ORD_AMT not between", value1, value2, "ordAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtIsNull() {
            addCriterion("REF_AMT is null");
            return (Criteria) this;
        }

        public Criteria andRefAmtIsNotNull() {
            addCriterion("REF_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andRefAmtEqualTo(String value) {
            addCriterion("REF_AMT =", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtNotEqualTo(String value) {
            addCriterion("REF_AMT <>", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtGreaterThan(String value) {
            addCriterion("REF_AMT >", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtGreaterThanOrEqualTo(String value) {
            addCriterion("REF_AMT >=", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtLessThan(String value) {
            addCriterion("REF_AMT <", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtLessThanOrEqualTo(String value) {
            addCriterion("REF_AMT <=", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtLike(String value) {
            addCriterion("REF_AMT like", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtNotLike(String value) {
            addCriterion("REF_AMT not like", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtIn(List<String> values) {
            addCriterion("REF_AMT in", values, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtNotIn(List<String> values) {
            addCriterion("REF_AMT not in", values, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtBetween(String value1, String value2) {
            addCriterion("REF_AMT between", value1, value2, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtNotBetween(String value1, String value2) {
            addCriterion("REF_AMT not between", value1, value2, "refAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtIsNull() {
            addCriterion("FEE_AMT is null");
            return (Criteria) this;
        }

        public Criteria andFeeAmtIsNotNull() {
            addCriterion("FEE_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andFeeAmtEqualTo(String value) {
            addCriterion("FEE_AMT =", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtNotEqualTo(String value) {
            addCriterion("FEE_AMT <>", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtGreaterThan(String value) {
            addCriterion("FEE_AMT >", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_AMT >=", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtLessThan(String value) {
            addCriterion("FEE_AMT <", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtLessThanOrEqualTo(String value) {
            addCriterion("FEE_AMT <=", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtLike(String value) {
            addCriterion("FEE_AMT like", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtNotLike(String value) {
            addCriterion("FEE_AMT not like", value, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtIn(List<String> values) {
            addCriterion("FEE_AMT in", values, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtNotIn(List<String> values) {
            addCriterion("FEE_AMT not in", values, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtBetween(String value1, String value2) {
            addCriterion("FEE_AMT between", value1, value2, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andFeeAmtNotBetween(String value1, String value2) {
            addCriterion("FEE_AMT not between", value1, value2, "feeAmt");
            return (Criteria) this;
        }

        public Criteria andSysTimeIsNull() {
            addCriterion("SYS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSysTimeIsNotNull() {
            addCriterion("SYS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSysTimeEqualTo(String value) {
            addCriterion("SYS_TIME =", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeNotEqualTo(String value) {
            addCriterion("SYS_TIME <>", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeGreaterThan(String value) {
            addCriterion("SYS_TIME >", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_TIME >=", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeLessThan(String value) {
            addCriterion("SYS_TIME <", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeLessThanOrEqualTo(String value) {
            addCriterion("SYS_TIME <=", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeLike(String value) {
            addCriterion("SYS_TIME like", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeNotLike(String value) {
            addCriterion("SYS_TIME not like", value, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeIn(List<String> values) {
            addCriterion("SYS_TIME in", values, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeNotIn(List<String> values) {
            addCriterion("SYS_TIME not in", values, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeBetween(String value1, String value2) {
            addCriterion("SYS_TIME between", value1, value2, "sysTime");
            return (Criteria) this;
        }

        public Criteria andSysTimeNotBetween(String value1, String value2) {
            addCriterion("SYS_TIME not between", value1, value2, "sysTime");
            return (Criteria) this;
        }

        public Criteria andAcctDateIsNull() {
            addCriterion("ACCT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAcctDateIsNotNull() {
            addCriterion("ACCT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAcctDateEqualTo(String value) {
            addCriterion("ACCT_DATE =", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateNotEqualTo(String value) {
            addCriterion("ACCT_DATE <>", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateGreaterThan(String value) {
            addCriterion("ACCT_DATE >", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_DATE >=", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateLessThan(String value) {
            addCriterion("ACCT_DATE <", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateLessThanOrEqualTo(String value) {
            addCriterion("ACCT_DATE <=", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateLike(String value) {
            addCriterion("ACCT_DATE like", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateNotLike(String value) {
            addCriterion("ACCT_DATE not like", value, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateIn(List<String> values) {
            addCriterion("ACCT_DATE in", values, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateNotIn(List<String> values) {
            addCriterion("ACCT_DATE not in", values, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateBetween(String value1, String value2) {
            addCriterion("ACCT_DATE between", value1, value2, "acctDate");
            return (Criteria) this;
        }

        public Criteria andAcctDateNotBetween(String value1, String value2) {
            addCriterion("ACCT_DATE not between", value1, value2, "acctDate");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdIsNull() {
            addCriterion("SYS_SEQ_ID is null");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdIsNotNull() {
            addCriterion("SYS_SEQ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdEqualTo(String value) {
            addCriterion("SYS_SEQ_ID =", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdNotEqualTo(String value) {
            addCriterion("SYS_SEQ_ID <>", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdGreaterThan(String value) {
            addCriterion("SYS_SEQ_ID >", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_SEQ_ID >=", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdLessThan(String value) {
            addCriterion("SYS_SEQ_ID <", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdLessThanOrEqualTo(String value) {
            addCriterion("SYS_SEQ_ID <=", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdLike(String value) {
            addCriterion("SYS_SEQ_ID like", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdNotLike(String value) {
            addCriterion("SYS_SEQ_ID not like", value, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdIn(List<String> values) {
            addCriterion("SYS_SEQ_ID in", values, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdNotIn(List<String> values) {
            addCriterion("SYS_SEQ_ID not in", values, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdBetween(String value1, String value2) {
            addCriterion("SYS_SEQ_ID between", value1, value2, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andSysSeqIdNotBetween(String value1, String value2) {
            addCriterion("SYS_SEQ_ID not between", value1, value2, "sysSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdIsNull() {
            addCriterion("POS_SEQ_ID is null");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdIsNotNull() {
            addCriterion("POS_SEQ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdEqualTo(String value) {
            addCriterion("POS_SEQ_ID =", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdNotEqualTo(String value) {
            addCriterion("POS_SEQ_ID <>", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdGreaterThan(String value) {
            addCriterion("POS_SEQ_ID >", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdGreaterThanOrEqualTo(String value) {
            addCriterion("POS_SEQ_ID >=", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdLessThan(String value) {
            addCriterion("POS_SEQ_ID <", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdLessThanOrEqualTo(String value) {
            addCriterion("POS_SEQ_ID <=", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdLike(String value) {
            addCriterion("POS_SEQ_ID like", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdNotLike(String value) {
            addCriterion("POS_SEQ_ID not like", value, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdIn(List<String> values) {
            addCriterion("POS_SEQ_ID in", values, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdNotIn(List<String> values) {
            addCriterion("POS_SEQ_ID not in", values, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdBetween(String value1, String value2) {
            addCriterion("POS_SEQ_ID between", value1, value2, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andPosSeqIdNotBetween(String value1, String value2) {
            addCriterion("POS_SEQ_ID not between", value1, value2, "posSeqId");
            return (Criteria) this;
        }

        public Criteria andRespCdIsNull() {
            addCriterion("RESP_CD is null");
            return (Criteria) this;
        }

        public Criteria andRespCdIsNotNull() {
            addCriterion("RESP_CD is not null");
            return (Criteria) this;
        }

        public Criteria andRespCdEqualTo(String value) {
            addCriterion("RESP_CD =", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdNotEqualTo(String value) {
            addCriterion("RESP_CD <>", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdGreaterThan(String value) {
            addCriterion("RESP_CD >", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdGreaterThanOrEqualTo(String value) {
            addCriterion("RESP_CD >=", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdLessThan(String value) {
            addCriterion("RESP_CD <", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdLessThanOrEqualTo(String value) {
            addCriterion("RESP_CD <=", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdLike(String value) {
            addCriterion("RESP_CD like", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdNotLike(String value) {
            addCriterion("RESP_CD not like", value, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdIn(List<String> values) {
            addCriterion("RESP_CD in", values, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdNotIn(List<String> values) {
            addCriterion("RESP_CD not in", values, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdBetween(String value1, String value2) {
            addCriterion("RESP_CD between", value1, value2, "respCd");
            return (Criteria) this;
        }

        public Criteria andRespCdNotBetween(String value1, String value2) {
            addCriterion("RESP_CD not between", value1, value2, "respCd");
            return (Criteria) this;
        }

        public Criteria andRefNumIsNull() {
            addCriterion("REF_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRefNumIsNotNull() {
            addCriterion("REF_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRefNumEqualTo(String value) {
            addCriterion("REF_NUM =", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumNotEqualTo(String value) {
            addCriterion("REF_NUM <>", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumGreaterThan(String value) {
            addCriterion("REF_NUM >", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumGreaterThanOrEqualTo(String value) {
            addCriterion("REF_NUM >=", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumLessThan(String value) {
            addCriterion("REF_NUM <", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumLessThanOrEqualTo(String value) {
            addCriterion("REF_NUM <=", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumLike(String value) {
            addCriterion("REF_NUM like", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumNotLike(String value) {
            addCriterion("REF_NUM not like", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumIn(List<String> values) {
            addCriterion("REF_NUM in", values, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumNotIn(List<String> values) {
            addCriterion("REF_NUM not in", values, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumBetween(String value1, String value2) {
            addCriterion("REF_NUM between", value1, value2, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumNotBetween(String value1, String value2) {
            addCriterion("REF_NUM not between", value1, value2, "refNum");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNull() {
            addCriterion("AUTH_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNotNull() {
            addCriterion("AUTH_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeEqualTo(String value) {
            addCriterion("AUTH_CODE =", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotEqualTo(String value) {
            addCriterion("AUTH_CODE <>", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThan(String value) {
            addCriterion("AUTH_CODE >", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_CODE >=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThan(String value) {
            addCriterion("AUTH_CODE <", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThanOrEqualTo(String value) {
            addCriterion("AUTH_CODE <=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLike(String value) {
            addCriterion("AUTH_CODE like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotLike(String value) {
            addCriterion("AUTH_CODE not like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIn(List<String> values) {
            addCriterion("AUTH_CODE in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotIn(List<String> values) {
            addCriterion("AUTH_CODE not in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeBetween(String value1, String value2) {
            addCriterion("AUTH_CODE between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotBetween(String value1, String value2) {
            addCriterion("AUTH_CODE not between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNull() {
            addCriterion("BATCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("BATCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(String value) {
            addCriterion("BATCH_ID =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(String value) {
            addCriterion("BATCH_ID <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(String value) {
            addCriterion("BATCH_ID >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_ID >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(String value) {
            addCriterion("BATCH_ID <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(String value) {
            addCriterion("BATCH_ID <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLike(String value) {
            addCriterion("BATCH_ID like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotLike(String value) {
            addCriterion("BATCH_ID not like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<String> values) {
            addCriterion("BATCH_ID in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<String> values) {
            addCriterion("BATCH_ID not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(String value1, String value2) {
            addCriterion("BATCH_ID between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(String value1, String value2) {
            addCriterion("BATCH_ID not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(String value) {
            addCriterion("CUST_ID =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(String value) {
            addCriterion("CUST_ID <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(String value) {
            addCriterion("CUST_ID >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ID >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(String value) {
            addCriterion("CUST_ID <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(String value) {
            addCriterion("CUST_ID <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLike(String value) {
            addCriterion("CUST_ID like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotLike(String value) {
            addCriterion("CUST_ID not like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<String> values) {
            addCriterion("CUST_ID in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<String> values) {
            addCriterion("CUST_ID not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(String value1, String value2) {
            addCriterion("CUST_ID between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(String value1, String value2) {
            addCriterion("CUST_ID not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdIsNull() {
            addCriterion("MER_USR_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdIsNotNull() {
            addCriterion("MER_USR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdEqualTo(String value) {
            addCriterion("MER_USR_ID =", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdNotEqualTo(String value) {
            addCriterion("MER_USR_ID <>", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdGreaterThan(String value) {
            addCriterion("MER_USR_ID >", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdGreaterThanOrEqualTo(String value) {
            addCriterion("MER_USR_ID >=", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdLessThan(String value) {
            addCriterion("MER_USR_ID <", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdLessThanOrEqualTo(String value) {
            addCriterion("MER_USR_ID <=", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdLike(String value) {
            addCriterion("MER_USR_ID like", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdNotLike(String value) {
            addCriterion("MER_USR_ID not like", value, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdIn(List<String> values) {
            addCriterion("MER_USR_ID in", values, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdNotIn(List<String> values) {
            addCriterion("MER_USR_ID not in", values, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdBetween(String value1, String value2) {
            addCriterion("MER_USR_ID between", value1, value2, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerUsrIdNotBetween(String value1, String value2) {
            addCriterion("MER_USR_ID not between", value1, value2, "merUsrId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdIsNull() {
            addCriterion("MER_ORD_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdIsNotNull() {
            addCriterion("MER_ORD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdEqualTo(String value) {
            addCriterion("MER_ORD_ID =", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdNotEqualTo(String value) {
            addCriterion("MER_ORD_ID <>", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdGreaterThan(String value) {
            addCriterion("MER_ORD_ID >", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdGreaterThanOrEqualTo(String value) {
            addCriterion("MER_ORD_ID >=", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdLessThan(String value) {
            addCriterion("MER_ORD_ID <", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdLessThanOrEqualTo(String value) {
            addCriterion("MER_ORD_ID <=", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdLike(String value) {
            addCriterion("MER_ORD_ID like", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdNotLike(String value) {
            addCriterion("MER_ORD_ID not like", value, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdIn(List<String> values) {
            addCriterion("MER_ORD_ID in", values, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdNotIn(List<String> values) {
            addCriterion("MER_ORD_ID not in", values, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdBetween(String value1, String value2) {
            addCriterion("MER_ORD_ID between", value1, value2, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andMerOrdIdNotBetween(String value1, String value2) {
            addCriterion("MER_ORD_ID not between", value1, value2, "merOrdId");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeIsNull() {
            addCriterion("REMARK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeIsNotNull() {
            addCriterion("REMARK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeEqualTo(String value) {
            addCriterion("REMARK_TYPE =", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotEqualTo(String value) {
            addCriterion("REMARK_TYPE <>", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeGreaterThan(String value) {
            addCriterion("REMARK_TYPE >", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK_TYPE >=", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeLessThan(String value) {
            addCriterion("REMARK_TYPE <", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeLessThanOrEqualTo(String value) {
            addCriterion("REMARK_TYPE <=", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeLike(String value) {
            addCriterion("REMARK_TYPE like", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotLike(String value) {
            addCriterion("REMARK_TYPE not like", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeIn(List<String> values) {
            addCriterion("REMARK_TYPE in", values, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotIn(List<String> values) {
            addCriterion("REMARK_TYPE not in", values, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeBetween(String value1, String value2) {
            addCriterion("REMARK_TYPE between", value1, value2, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotBetween(String value1, String value2) {
            addCriterion("REMARK_TYPE not between", value1, value2, "remarkType");
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

        public Criteria andTransStatIsNull() {
            addCriterion("TRANS_STAT is null");
            return (Criteria) this;
        }

        public Criteria andTransStatIsNotNull() {
            addCriterion("TRANS_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andTransStatEqualTo(String value) {
            addCriterion("TRANS_STAT =", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatNotEqualTo(String value) {
            addCriterion("TRANS_STAT <>", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatGreaterThan(String value) {
            addCriterion("TRANS_STAT >", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_STAT >=", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatLessThan(String value) {
            addCriterion("TRANS_STAT <", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatLessThanOrEqualTo(String value) {
            addCriterion("TRANS_STAT <=", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatLike(String value) {
            addCriterion("TRANS_STAT like", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatNotLike(String value) {
            addCriterion("TRANS_STAT not like", value, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatIn(List<String> values) {
            addCriterion("TRANS_STAT in", values, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatNotIn(List<String> values) {
            addCriterion("TRANS_STAT not in", values, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatBetween(String value1, String value2) {
            addCriterion("TRANS_STAT between", value1, value2, "transStat");
            return (Criteria) this;
        }

        public Criteria andTransStatNotBetween(String value1, String value2) {
            addCriterion("TRANS_STAT not between", value1, value2, "transStat");
            return (Criteria) this;
        }

        public Criteria andBankStatIsNull() {
            addCriterion("BANK_STAT is null");
            return (Criteria) this;
        }

        public Criteria andBankStatIsNotNull() {
            addCriterion("BANK_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andBankStatEqualTo(String value) {
            addCriterion("BANK_STAT =", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatNotEqualTo(String value) {
            addCriterion("BANK_STAT <>", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatGreaterThan(String value) {
            addCriterion("BANK_STAT >", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_STAT >=", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatLessThan(String value) {
            addCriterion("BANK_STAT <", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatLessThanOrEqualTo(String value) {
            addCriterion("BANK_STAT <=", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatLike(String value) {
            addCriterion("BANK_STAT like", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatNotLike(String value) {
            addCriterion("BANK_STAT not like", value, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatIn(List<String> values) {
            addCriterion("BANK_STAT in", values, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatNotIn(List<String> values) {
            addCriterion("BANK_STAT not in", values, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatBetween(String value1, String value2) {
            addCriterion("BANK_STAT between", value1, value2, "bankStat");
            return (Criteria) this;
        }

        public Criteria andBankStatNotBetween(String value1, String value2) {
            addCriterion("BANK_STAT not between", value1, value2, "bankStat");
            return (Criteria) this;
        }

        public Criteria andChkFlagIsNull() {
            addCriterion("CHK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andChkFlagIsNotNull() {
            addCriterion("CHK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andChkFlagEqualTo(String value) {
            addCriterion("CHK_FLAG =", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotEqualTo(String value) {
            addCriterion("CHK_FLAG <>", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagGreaterThan(String value) {
            addCriterion("CHK_FLAG >", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CHK_FLAG >=", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagLessThan(String value) {
            addCriterion("CHK_FLAG <", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagLessThanOrEqualTo(String value) {
            addCriterion("CHK_FLAG <=", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagLike(String value) {
            addCriterion("CHK_FLAG like", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotLike(String value) {
            addCriterion("CHK_FLAG not like", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagIn(List<String> values) {
            addCriterion("CHK_FLAG in", values, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotIn(List<String> values) {
            addCriterion("CHK_FLAG not in", values, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagBetween(String value1, String value2) {
            addCriterion("CHK_FLAG between", value1, value2, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotBetween(String value1, String value2) {
            addCriterion("CHK_FLAG not between", value1, value2, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andProcDriIsNull() {
            addCriterion("PROC_DRI is null");
            return (Criteria) this;
        }

        public Criteria andProcDriIsNotNull() {
            addCriterion("PROC_DRI is not null");
            return (Criteria) this;
        }

        public Criteria andProcDriEqualTo(String value) {
            addCriterion("PROC_DRI =", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriNotEqualTo(String value) {
            addCriterion("PROC_DRI <>", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriGreaterThan(String value) {
            addCriterion("PROC_DRI >", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DRI >=", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriLessThan(String value) {
            addCriterion("PROC_DRI <", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriLessThanOrEqualTo(String value) {
            addCriterion("PROC_DRI <=", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriLike(String value) {
            addCriterion("PROC_DRI like", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriNotLike(String value) {
            addCriterion("PROC_DRI not like", value, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriIn(List<String> values) {
            addCriterion("PROC_DRI in", values, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriNotIn(List<String> values) {
            addCriterion("PROC_DRI not in", values, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriBetween(String value1, String value2) {
            addCriterion("PROC_DRI between", value1, value2, "procDri");
            return (Criteria) this;
        }

        public Criteria andProcDriNotBetween(String value1, String value2) {
            addCriterion("PROC_DRI not between", value1, value2, "procDri");
            return (Criteria) this;
        }

        public Criteria andMachineNoIsNull() {
            addCriterion("MACHINE_NO is null");
            return (Criteria) this;
        }

        public Criteria andMachineNoIsNotNull() {
            addCriterion("MACHINE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMachineNoEqualTo(String value) {
            addCriterion("MACHINE_NO =", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoNotEqualTo(String value) {
            addCriterion("MACHINE_NO <>", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoGreaterThan(String value) {
            addCriterion("MACHINE_NO >", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoGreaterThanOrEqualTo(String value) {
            addCriterion("MACHINE_NO >=", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoLessThan(String value) {
            addCriterion("MACHINE_NO <", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoLessThanOrEqualTo(String value) {
            addCriterion("MACHINE_NO <=", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoLike(String value) {
            addCriterion("MACHINE_NO like", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoNotLike(String value) {
            addCriterion("MACHINE_NO not like", value, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoIn(List<String> values) {
            addCriterion("MACHINE_NO in", values, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoNotIn(List<String> values) {
            addCriterion("MACHINE_NO not in", values, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoBetween(String value1, String value2) {
            addCriterion("MACHINE_NO between", value1, value2, "machineNo");
            return (Criteria) this;
        }

        public Criteria andMachineNoNotBetween(String value1, String value2) {
            addCriterion("MACHINE_NO not between", value1, value2, "machineNo");
            return (Criteria) this;
        }

        public Criteria andTermFlagIsNull() {
            addCriterion("TERM_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTermFlagIsNotNull() {
            addCriterion("TERM_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTermFlagEqualTo(String value) {
            addCriterion("TERM_FLAG =", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagNotEqualTo(String value) {
            addCriterion("TERM_FLAG <>", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagGreaterThan(String value) {
            addCriterion("TERM_FLAG >", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_FLAG >=", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagLessThan(String value) {
            addCriterion("TERM_FLAG <", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagLessThanOrEqualTo(String value) {
            addCriterion("TERM_FLAG <=", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagLike(String value) {
            addCriterion("TERM_FLAG like", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagNotLike(String value) {
            addCriterion("TERM_FLAG not like", value, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagIn(List<String> values) {
            addCriterion("TERM_FLAG in", values, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagNotIn(List<String> values) {
            addCriterion("TERM_FLAG not in", values, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagBetween(String value1, String value2) {
            addCriterion("TERM_FLAG between", value1, value2, "termFlag");
            return (Criteria) this;
        }

        public Criteria andTermFlagNotBetween(String value1, String value2) {
            addCriterion("TERM_FLAG not between", value1, value2, "termFlag");
            return (Criteria) this;
        }

        public Criteria andCalcModeIsNull() {
            addCriterion("CALC_MODE is null");
            return (Criteria) this;
        }

        public Criteria andCalcModeIsNotNull() {
            addCriterion("CALC_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andCalcModeEqualTo(String value) {
            addCriterion("CALC_MODE =", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotEqualTo(String value) {
            addCriterion("CALC_MODE <>", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeGreaterThan(String value) {
            addCriterion("CALC_MODE >", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeGreaterThanOrEqualTo(String value) {
            addCriterion("CALC_MODE >=", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeLessThan(String value) {
            addCriterion("CALC_MODE <", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeLessThanOrEqualTo(String value) {
            addCriterion("CALC_MODE <=", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeLike(String value) {
            addCriterion("CALC_MODE like", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotLike(String value) {
            addCriterion("CALC_MODE not like", value, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeIn(List<String> values) {
            addCriterion("CALC_MODE in", values, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotIn(List<String> values) {
            addCriterion("CALC_MODE not in", values, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeBetween(String value1, String value2) {
            addCriterion("CALC_MODE between", value1, value2, "calcMode");
            return (Criteria) this;
        }

        public Criteria andCalcModeNotBetween(String value1, String value2) {
            addCriterion("CALC_MODE not between", value1, value2, "calcMode");
            return (Criteria) this;
        }

        public Criteria andPosSystemIsNull() {
            addCriterion("POS_SYSTEM is null");
            return (Criteria) this;
        }

        public Criteria andPosSystemIsNotNull() {
            addCriterion("POS_SYSTEM is not null");
            return (Criteria) this;
        }

        public Criteria andPosSystemEqualTo(String value) {
            addCriterion("POS_SYSTEM =", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemNotEqualTo(String value) {
            addCriterion("POS_SYSTEM <>", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemGreaterThan(String value) {
            addCriterion("POS_SYSTEM >", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemGreaterThanOrEqualTo(String value) {
            addCriterion("POS_SYSTEM >=", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemLessThan(String value) {
            addCriterion("POS_SYSTEM <", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemLessThanOrEqualTo(String value) {
            addCriterion("POS_SYSTEM <=", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemLike(String value) {
            addCriterion("POS_SYSTEM like", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemNotLike(String value) {
            addCriterion("POS_SYSTEM not like", value, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemIn(List<String> values) {
            addCriterion("POS_SYSTEM in", values, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemNotIn(List<String> values) {
            addCriterion("POS_SYSTEM not in", values, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemBetween(String value1, String value2) {
            addCriterion("POS_SYSTEM between", value1, value2, "posSystem");
            return (Criteria) this;
        }

        public Criteria andPosSystemNotBetween(String value1, String value2) {
            addCriterion("POS_SYSTEM not between", value1, value2, "posSystem");
            return (Criteria) this;
        }

        public Criteria andField22IsNull() {
            addCriterion("FIELD22 is null");
            return (Criteria) this;
        }

        public Criteria andField22IsNotNull() {
            addCriterion("FIELD22 is not null");
            return (Criteria) this;
        }

        public Criteria andField22EqualTo(String value) {
            addCriterion("FIELD22 =", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22NotEqualTo(String value) {
            addCriterion("FIELD22 <>", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22GreaterThan(String value) {
            addCriterion("FIELD22 >", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD22 >=", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22LessThan(String value) {
            addCriterion("FIELD22 <", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22LessThanOrEqualTo(String value) {
            addCriterion("FIELD22 <=", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22Like(String value) {
            addCriterion("FIELD22 like", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22NotLike(String value) {
            addCriterion("FIELD22 not like", value, "field22");
            return (Criteria) this;
        }

        public Criteria andField22In(List<String> values) {
            addCriterion("FIELD22 in", values, "field22");
            return (Criteria) this;
        }

        public Criteria andField22NotIn(List<String> values) {
            addCriterion("FIELD22 not in", values, "field22");
            return (Criteria) this;
        }

        public Criteria andField22Between(String value1, String value2) {
            addCriterion("FIELD22 between", value1, value2, "field22");
            return (Criteria) this;
        }

        public Criteria andField22NotBetween(String value1, String value2) {
            addCriterion("FIELD22 not between", value1, value2, "field22");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeIsNull() {
            addCriterion("TRANS_DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeIsNotNull() {
            addCriterion("TRANS_DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeEqualTo(String value) {
            addCriterion("TRANS_DATE_TIME =", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeNotEqualTo(String value) {
            addCriterion("TRANS_DATE_TIME <>", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeGreaterThan(String value) {
            addCriterion("TRANS_DATE_TIME >", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_DATE_TIME >=", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeLessThan(String value) {
            addCriterion("TRANS_DATE_TIME <", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeLessThanOrEqualTo(String value) {
            addCriterion("TRANS_DATE_TIME <=", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeLike(String value) {
            addCriterion("TRANS_DATE_TIME like", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeNotLike(String value) {
            addCriterion("TRANS_DATE_TIME not like", value, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeIn(List<String> values) {
            addCriterion("TRANS_DATE_TIME in", values, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeNotIn(List<String> values) {
            addCriterion("TRANS_DATE_TIME not in", values, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeBetween(String value1, String value2) {
            addCriterion("TRANS_DATE_TIME between", value1, value2, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andTransDateTimeNotBetween(String value1, String value2) {
            addCriterion("TRANS_DATE_TIME not between", value1, value2, "transDateTime");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumIsNull() {
            addCriterion("SYS_TRACE_AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumIsNotNull() {
            addCriterion("SYS_TRACE_AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumEqualTo(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM =", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumNotEqualTo(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM <>", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumGreaterThan(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM >", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM >=", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumLessThan(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM <", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumLessThanOrEqualTo(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM <=", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumLike(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM like", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumNotLike(String value) {
            addCriterion("SYS_TRACE_AUDIT_NUM not like", value, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumIn(List<String> values) {
            addCriterion("SYS_TRACE_AUDIT_NUM in", values, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumNotIn(List<String> values) {
            addCriterion("SYS_TRACE_AUDIT_NUM not in", values, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumBetween(String value1, String value2) {
            addCriterion("SYS_TRACE_AUDIT_NUM between", value1, value2, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andSysTraceAuditNumNotBetween(String value1, String value2) {
            addCriterion("SYS_TRACE_AUDIT_NUM not between", value1, value2, "sysTraceAuditNum");
            return (Criteria) this;
        }

        public Criteria andMchntTypeIsNull() {
            addCriterion("MCHNT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMchntTypeIsNotNull() {
            addCriterion("MCHNT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMchntTypeEqualTo(String value) {
            addCriterion("MCHNT_TYPE =", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeNotEqualTo(String value) {
            addCriterion("MCHNT_TYPE <>", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeGreaterThan(String value) {
            addCriterion("MCHNT_TYPE >", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MCHNT_TYPE >=", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeLessThan(String value) {
            addCriterion("MCHNT_TYPE <", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeLessThanOrEqualTo(String value) {
            addCriterion("MCHNT_TYPE <=", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeLike(String value) {
            addCriterion("MCHNT_TYPE like", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeNotLike(String value) {
            addCriterion("MCHNT_TYPE not like", value, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeIn(List<String> values) {
            addCriterion("MCHNT_TYPE in", values, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeNotIn(List<String> values) {
            addCriterion("MCHNT_TYPE not in", values, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeBetween(String value1, String value2) {
            addCriterion("MCHNT_TYPE between", value1, value2, "mchntType");
            return (Criteria) this;
        }

        public Criteria andMchntTypeNotBetween(String value1, String value2) {
            addCriterion("MCHNT_TYPE not between", value1, value2, "mchntType");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeIsNull() {
            addCriterion("ACQ_INST_ID_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeIsNotNull() {
            addCriterion("ACQ_INST_ID_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeEqualTo(String value) {
            addCriterion("ACQ_INST_ID_CODE =", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeNotEqualTo(String value) {
            addCriterion("ACQ_INST_ID_CODE <>", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeGreaterThan(String value) {
            addCriterion("ACQ_INST_ID_CODE >", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ACQ_INST_ID_CODE >=", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeLessThan(String value) {
            addCriterion("ACQ_INST_ID_CODE <", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeLessThanOrEqualTo(String value) {
            addCriterion("ACQ_INST_ID_CODE <=", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeLike(String value) {
            addCriterion("ACQ_INST_ID_CODE like", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeNotLike(String value) {
            addCriterion("ACQ_INST_ID_CODE not like", value, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeIn(List<String> values) {
            addCriterion("ACQ_INST_ID_CODE in", values, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeNotIn(List<String> values) {
            addCriterion("ACQ_INST_ID_CODE not in", values, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeBetween(String value1, String value2) {
            addCriterion("ACQ_INST_ID_CODE between", value1, value2, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andAcqInstIdCodeNotBetween(String value1, String value2) {
            addCriterion("ACQ_INST_ID_CODE not between", value1, value2, "acqInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeIsNull() {
            addCriterion("FWD_INST_ID_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeIsNotNull() {
            addCriterion("FWD_INST_ID_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeEqualTo(String value) {
            addCriterion("FWD_INST_ID_CODE =", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeNotEqualTo(String value) {
            addCriterion("FWD_INST_ID_CODE <>", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeGreaterThan(String value) {
            addCriterion("FWD_INST_ID_CODE >", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FWD_INST_ID_CODE >=", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeLessThan(String value) {
            addCriterion("FWD_INST_ID_CODE <", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeLessThanOrEqualTo(String value) {
            addCriterion("FWD_INST_ID_CODE <=", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeLike(String value) {
            addCriterion("FWD_INST_ID_CODE like", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeNotLike(String value) {
            addCriterion("FWD_INST_ID_CODE not like", value, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeIn(List<String> values) {
            addCriterion("FWD_INST_ID_CODE in", values, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeNotIn(List<String> values) {
            addCriterion("FWD_INST_ID_CODE not in", values, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeBetween(String value1, String value2) {
            addCriterion("FWD_INST_ID_CODE between", value1, value2, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andFwdInstIdCodeNotBetween(String value1, String value2) {
            addCriterion("FWD_INST_ID_CODE not between", value1, value2, "fwdInstIdCode");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdIsNull() {
            addCriterion("INST_POS_TERM_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdIsNotNull() {
            addCriterion("INST_POS_TERM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdEqualTo(String value) {
            addCriterion("INST_POS_TERM_ID =", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdNotEqualTo(String value) {
            addCriterion("INST_POS_TERM_ID <>", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdGreaterThan(String value) {
            addCriterion("INST_POS_TERM_ID >", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_POS_TERM_ID >=", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdLessThan(String value) {
            addCriterion("INST_POS_TERM_ID <", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdLessThanOrEqualTo(String value) {
            addCriterion("INST_POS_TERM_ID <=", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdLike(String value) {
            addCriterion("INST_POS_TERM_ID like", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdNotLike(String value) {
            addCriterion("INST_POS_TERM_ID not like", value, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdIn(List<String> values) {
            addCriterion("INST_POS_TERM_ID in", values, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdNotIn(List<String> values) {
            addCriterion("INST_POS_TERM_ID not in", values, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdBetween(String value1, String value2) {
            addCriterion("INST_POS_TERM_ID between", value1, value2, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosTermIdNotBetween(String value1, String value2) {
            addCriterion("INST_POS_TERM_ID not between", value1, value2, "instPosTermId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdIsNull() {
            addCriterion("INST_POS_MER_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdIsNotNull() {
            addCriterion("INST_POS_MER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdEqualTo(String value) {
            addCriterion("INST_POS_MER_ID =", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdNotEqualTo(String value) {
            addCriterion("INST_POS_MER_ID <>", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdGreaterThan(String value) {
            addCriterion("INST_POS_MER_ID >", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("INST_POS_MER_ID >=", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdLessThan(String value) {
            addCriterion("INST_POS_MER_ID <", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdLessThanOrEqualTo(String value) {
            addCriterion("INST_POS_MER_ID <=", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdLike(String value) {
            addCriterion("INST_POS_MER_ID like", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdNotLike(String value) {
            addCriterion("INST_POS_MER_ID not like", value, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdIn(List<String> values) {
            addCriterion("INST_POS_MER_ID in", values, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdNotIn(List<String> values) {
            addCriterion("INST_POS_MER_ID not in", values, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdBetween(String value1, String value2) {
            addCriterion("INST_POS_MER_ID between", value1, value2, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andInstPosMerIdNotBetween(String value1, String value2) {
            addCriterion("INST_POS_MER_ID not between", value1, value2, "instPosMerId");
            return (Criteria) this;
        }

        public Criteria andSysMerNameIsNull() {
            addCriterion("SYS_MER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSysMerNameIsNotNull() {
            addCriterion("SYS_MER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSysMerNameEqualTo(String value) {
            addCriterion("SYS_MER_NAME =", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameNotEqualTo(String value) {
            addCriterion("SYS_MER_NAME <>", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameGreaterThan(String value) {
            addCriterion("SYS_MER_NAME >", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_MER_NAME >=", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameLessThan(String value) {
            addCriterion("SYS_MER_NAME <", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameLessThanOrEqualTo(String value) {
            addCriterion("SYS_MER_NAME <=", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameLike(String value) {
            addCriterion("SYS_MER_NAME like", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameNotLike(String value) {
            addCriterion("SYS_MER_NAME not like", value, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameIn(List<String> values) {
            addCriterion("SYS_MER_NAME in", values, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameNotIn(List<String> values) {
            addCriterion("SYS_MER_NAME not in", values, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameBetween(String value1, String value2) {
            addCriterion("SYS_MER_NAME between", value1, value2, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andSysMerNameNotBetween(String value1, String value2) {
            addCriterion("SYS_MER_NAME not between", value1, value2, "sysMerName");
            return (Criteria) this;
        }

        public Criteria andFldReservedIsNull() {
            addCriterion("FLD_RESERVED is null");
            return (Criteria) this;
        }

        public Criteria andFldReservedIsNotNull() {
            addCriterion("FLD_RESERVED is not null");
            return (Criteria) this;
        }

        public Criteria andFldReservedEqualTo(String value) {
            addCriterion("FLD_RESERVED =", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedNotEqualTo(String value) {
            addCriterion("FLD_RESERVED <>", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedGreaterThan(String value) {
            addCriterion("FLD_RESERVED >", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedGreaterThanOrEqualTo(String value) {
            addCriterion("FLD_RESERVED >=", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedLessThan(String value) {
            addCriterion("FLD_RESERVED <", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedLessThanOrEqualTo(String value) {
            addCriterion("FLD_RESERVED <=", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedLike(String value) {
            addCriterion("FLD_RESERVED like", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedNotLike(String value) {
            addCriterion("FLD_RESERVED not like", value, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedIn(List<String> values) {
            addCriterion("FLD_RESERVED in", values, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedNotIn(List<String> values) {
            addCriterion("FLD_RESERVED not in", values, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedBetween(String value1, String value2) {
            addCriterion("FLD_RESERVED between", value1, value2, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andFldReservedNotBetween(String value1, String value2) {
            addCriterion("FLD_RESERVED not between", value1, value2, "fldReserved");
            return (Criteria) this;
        }

        public Criteria andRefNoIsNull() {
            addCriterion("REF_NO is null");
            return (Criteria) this;
        }

        public Criteria andRefNoIsNotNull() {
            addCriterion("REF_NO is not null");
            return (Criteria) this;
        }

        public Criteria andRefNoEqualTo(String value) {
            addCriterion("REF_NO =", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotEqualTo(String value) {
            addCriterion("REF_NO <>", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoGreaterThan(String value) {
            addCriterion("REF_NO >", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoGreaterThanOrEqualTo(String value) {
            addCriterion("REF_NO >=", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLessThan(String value) {
            addCriterion("REF_NO <", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLessThanOrEqualTo(String value) {
            addCriterion("REF_NO <=", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLike(String value) {
            addCriterion("REF_NO like", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotLike(String value) {
            addCriterion("REF_NO not like", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoIn(List<String> values) {
            addCriterion("REF_NO in", values, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotIn(List<String> values) {
            addCriterion("REF_NO not in", values, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoBetween(String value1, String value2) {
            addCriterion("REF_NO between", value1, value2, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotBetween(String value1, String value2) {
            addCriterion("REF_NO not between", value1, value2, "refNo");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeIsNull() {
            addCriterion("RCV_INST_ID_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeIsNotNull() {
            addCriterion("RCV_INST_ID_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeEqualTo(String value) {
            addCriterion("RCV_INST_ID_CODE =", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeNotEqualTo(String value) {
            addCriterion("RCV_INST_ID_CODE <>", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeGreaterThan(String value) {
            addCriterion("RCV_INST_ID_CODE >", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeGreaterThanOrEqualTo(String value) {
            addCriterion("RCV_INST_ID_CODE >=", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeLessThan(String value) {
            addCriterion("RCV_INST_ID_CODE <", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeLessThanOrEqualTo(String value) {
            addCriterion("RCV_INST_ID_CODE <=", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeLike(String value) {
            addCriterion("RCV_INST_ID_CODE like", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeNotLike(String value) {
            addCriterion("RCV_INST_ID_CODE not like", value, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeIn(List<String> values) {
            addCriterion("RCV_INST_ID_CODE in", values, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeNotIn(List<String> values) {
            addCriterion("RCV_INST_ID_CODE not in", values, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeBetween(String value1, String value2) {
            addCriterion("RCV_INST_ID_CODE between", value1, value2, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andRcvInstIdCodeNotBetween(String value1, String value2) {
            addCriterion("RCV_INST_ID_CODE not between", value1, value2, "rcvInstIdCode");
            return (Criteria) this;
        }

        public Criteria andTxnNumIsNull() {
            addCriterion("TXN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTxnNumIsNotNull() {
            addCriterion("TXN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTxnNumEqualTo(String value) {
            addCriterion("TXN_NUM =", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumNotEqualTo(String value) {
            addCriterion("TXN_NUM <>", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumGreaterThan(String value) {
            addCriterion("TXN_NUM >", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumGreaterThanOrEqualTo(String value) {
            addCriterion("TXN_NUM >=", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumLessThan(String value) {
            addCriterion("TXN_NUM <", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumLessThanOrEqualTo(String value) {
            addCriterion("TXN_NUM <=", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumLike(String value) {
            addCriterion("TXN_NUM like", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumNotLike(String value) {
            addCriterion("TXN_NUM not like", value, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumIn(List<String> values) {
            addCriterion("TXN_NUM in", values, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumNotIn(List<String> values) {
            addCriterion("TXN_NUM not in", values, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumBetween(String value1, String value2) {
            addCriterion("TXN_NUM between", value1, value2, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTxnNumNotBetween(String value1, String value2) {
            addCriterion("TXN_NUM not between", value1, value2, "txnNum");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeIsNull() {
            addCriterion("TYPE_PROCESS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeIsNotNull() {
            addCriterion("TYPE_PROCESS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeEqualTo(String value) {
            addCriterion("TYPE_PROCESS_CODE =", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeNotEqualTo(String value) {
            addCriterion("TYPE_PROCESS_CODE <>", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeGreaterThan(String value) {
            addCriterion("TYPE_PROCESS_CODE >", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_PROCESS_CODE >=", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeLessThan(String value) {
            addCriterion("TYPE_PROCESS_CODE <", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeLessThanOrEqualTo(String value) {
            addCriterion("TYPE_PROCESS_CODE <=", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeLike(String value) {
            addCriterion("TYPE_PROCESS_CODE like", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeNotLike(String value) {
            addCriterion("TYPE_PROCESS_CODE not like", value, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeIn(List<String> values) {
            addCriterion("TYPE_PROCESS_CODE in", values, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeNotIn(List<String> values) {
            addCriterion("TYPE_PROCESS_CODE not in", values, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeBetween(String value1, String value2) {
            addCriterion("TYPE_PROCESS_CODE between", value1, value2, "typeProcessCode");
            return (Criteria) this;
        }

        public Criteria andTypeProcessCodeNotBetween(String value1, String value2) {
            addCriterion("TYPE_PROCESS_CODE not between", value1, value2, "typeProcessCode");
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

        public Criteria andProfitAmtIsNull() {
            addCriterion("PROFIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andProfitAmtIsNotNull() {
            addCriterion("PROFIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andProfitAmtEqualTo(String value) {
            addCriterion("PROFIT_AMT =", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtNotEqualTo(String value) {
            addCriterion("PROFIT_AMT <>", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtGreaterThan(String value) {
            addCriterion("PROFIT_AMT >", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtGreaterThanOrEqualTo(String value) {
            addCriterion("PROFIT_AMT >=", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtLessThan(String value) {
            addCriterion("PROFIT_AMT <", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtLessThanOrEqualTo(String value) {
            addCriterion("PROFIT_AMT <=", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtLike(String value) {
            addCriterion("PROFIT_AMT like", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtNotLike(String value) {
            addCriterion("PROFIT_AMT not like", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtIn(List<String> values) {
            addCriterion("PROFIT_AMT in", values, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtNotIn(List<String> values) {
            addCriterion("PROFIT_AMT not in", values, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtBetween(String value1, String value2) {
            addCriterion("PROFIT_AMT between", value1, value2, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtNotBetween(String value1, String value2) {
            addCriterion("PROFIT_AMT not between", value1, value2, "profitAmt");
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