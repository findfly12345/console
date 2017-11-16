<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>钱宝交易差错处理</title>
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
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
        <tr>
            <th align="center">文件编号</th>
            <th align="center">渠道</th>
            <th align="center">渠道商户号</th>
            <th align="center">渠道终端号</th>
            <th align="center">交易日期</th>
            <th align="center">交易时间</th>
            <th align="center">交易卡号</th>
            <th align="center">交易金额</th>
            <th align="center">终端流水号</th>
            <th align="center">当前交易参考号</th>
            <th align="center">授权码</th>
            <th align="center">对账状态</th>
            <th align="center">操作员</th>
            <th align="center">更新时间</th>
            <th align="center">创建时间</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${tradeForm.pagination.list}" varStatus="status">
            <tr>
                <td align="center">${record.fileNo}</td>
                <td align="center">钱宝</td>
                <td align="center">${record.mercno}</td>
                <td align="center">${record.termid}</td>
                <td align="center">${record.sysdate}</td>
                <td align="center">${record.loctime}</td>
                <td align="center">${record.priacno}</td>
                <td align="center">${record.tranamt}</td>
                <td align="center">${record.termtrc}</td>
                <td align="center">${record.refnum}</td>
                <td align="center">${record.authid}</td>
                <c:if test="${record.checkStat=='0'}">
                    <td align="center">初始化</td>
                </c:if>
                <c:if test="${record.checkStat=='1'}">
                    <td align="center">对账失败</td>
                </c:if>
                <c:if test="${record.checkStat=='2'}">
                    <td align="center">对账成功</td>
                </c:if>
                <td align="center">${record.userName}</td>
                <td align="center">${record.updateTime}</td>
                <td align="center">${record.createTime}</td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/check/query_u1_plat_trade?termtrc=${record.termtrc}&refnum=${record.refnum}" class="btn btn-green" data-toggle="dialog" data-width="600" data-height="450" data-id="dialog-normal-modify"  data-max="true" data-title="查找平台相关交易">查找平台相关交易</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter" id="pageFooter">
    <div class="pages">
        <span>每页 </span>

        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="10">10</option>
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="100">100</option>
            </select>
        </div>
        <span> 条，共 <c:out value="${tradeForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination"
         data-total="<c:out value="${tradeForm.pagination.itemCount}"/>"
         data-page-size="<c:out value="${tradeForm.pageSize}"/>"
         data-page-current="<c:out value="${tradeForm.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
