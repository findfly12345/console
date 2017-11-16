<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>交易差错处理</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form:form id="pagerForm" name="tradeForm"
               data-toggle="ajaxsearch"
               action="${pageContext.request.contextPath}/check/error_trade_handling" method="post">
        <input type="hidden" name="pageSize"
               value="${tradeForm.pageSize}"/>
        <input type="hidden" name="pageCurrent"
               value="${tradeForm.pageCurrent}"/>

        <div class="bjui-searchBar">
            <table>
                <tr>
                    <td align="right">
                        <label>交易日期：</label>
                    </td>
                    <td align="left" colspan="3">
                        <input type="text" name="transBeginDate" id="transBeginDate" value="${tradeForm.transBeginDate}"
                               data-toggle="datepicker" data-rule="date">

                        <label>至：</label>
                        <input type="text" name="transEndDate" id="transEndDate" value="${tradeForm.transEndDate}"
                               data-toggle="datepicker" data-rule="date">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label>渠道商户号：</label>
                    </td>
                    <td align="left">
                        <input type="text" id="channelMerId" name="channelMerId" value="${tradeForm.channelMerId}" class="form-control"
                               size="15"/>
                    </td>
                    <td align="right">
                        <label>渠道：</label>
                    </td>
                    <td align="left">
                        <select id="gateId" name="gateId" data-toggle="selectpicker" data-rule="渠道:required;">
                            <option value="">请选择</option>
                            <option <c:if test="${tradeForm.gateId=='U1'}">selected</c:if> value="U1">钱宝</option>
                        </select>
                    </td>
                    <td align="right">
                        <label>交易状态：</label>
                    </td>
                    <td align="left">
                        <select id="transStat" name="transStat" data-toggle="selectpicker">
                            <option value="">请选择</option>
                            <option <c:if test="${tradeForm.transStat=='I'}">selected</c:if> value="I">初始化</option>
                            <option <c:if test="${tradeForm.transStat=='F'}">selected</c:if> value="F">失败</option>
                            <option <c:if test="${tradeForm.transStat=='S'}">selected</c:if> value="S">成功</option>
                        </select>
                    </td>
                    <td align="right">
                        <label>对账状态：</label>
                    </td>
                    <td align="left">
                        <select id="checkStat" name="checkStat" data-toggle="selectpicker">
                            <option value="">请选择</option>
                            <option <c:if test="${tradeForm.checkStat=='0'}">selected</c:if> value="0">初始化</option>
                            <option <c:if test="${tradeForm.checkStat=='1'}">selected</c:if> value="1">失败</option>
                            <option <c:if test="${tradeForm.checkStat=='2'}">selected</c:if> value="2">成功</option>
                        </select>
                    </td>
                </tr>
            </table>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
        </div>
    </form:form>
</div>
</body>
</html>
