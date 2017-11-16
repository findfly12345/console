<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/2/8
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>终端查询</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="terminalForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/terminal/query_terminal_list" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${terminalForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${terminalForm.pageCurrent}">
        <div class="bjui-searchBar">

            <label>终端名称：</label>
            <input type="text" name="termName" id="termName" value="${terminalForm.termName}"  size="15">&nbsp;
            <label>终端号：</label>
            <input type="text" name="termId" id="termId" value="${terminalForm.termId}"  size="15">&nbsp;
            <label>终端状态：</label>
            <select name="termStat" id="termStat" data-toggle="selectpicker">
                <option value="">请选择</option>
                <option value="1" >已开通</option>
                <option value="0" >未开通</option>
            </select>&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">终端号</th>
            <th align="center">终端名称</th>
            <th align="center">终端状态</th>
            <th align="center">商户号</th>
            <th align="center">终端类型</th>
            <th align="center">终端SN号</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${terminalForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.termId}"/>">
                <td align="center"><c:out value="${record.termId}"/></td>
                <td align="center"><c:out value="${record.termName}"/></td>
                <td align="center">
                    <c:if test="${record.termStat=='1'}">已开通</c:if>
                    <c:if test="${record.termStat=='0'}">未开通</c:if>
                </td>
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center">
                    <c:if test="${record.termType=='0'}">移动</c:if>
                    <c:if test="${record.termType=='1'}">固定</c:if>
                    <c:if test="${record.termType=='2'}">MPOS</c:if>
                </td>
                <td align="center"><c:out value="${record.termSn}"/></td>
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
        <span> 条，共 <c:out value="${terminalForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${terminalForm.pagination.itemCount}"/>" data-page-size="<c:out value="${terminalForm.pageSize}"/>" data-page-current="<c:out value="${terminalForm.pageCurrent}"/>">
    </div>
</div>
</body>
</html>

