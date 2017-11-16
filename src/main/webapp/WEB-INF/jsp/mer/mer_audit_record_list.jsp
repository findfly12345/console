<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户审核记录</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merAuditForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/mer/query_audit_record_list" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${merAuditForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${merAuditForm.pageCurrent}">
        <div class="bjui-searchBar">

            <label>手机号&nbsp;&nbsp;&nbsp;：</label>
            <input type="text" name="phone" id="phone" value="${merAuditForm.phone}"  size="15">&nbsp;
            <label>商户号：</label>
            <input type="text" name="merId" id="merId" value="${merAuditForm.merId}"  size="15">&nbsp;
            <label>商户名称：</label>
            <input type="text" name="merName" id="merName" value="${merAuditForm.merName}"  size="15">&nbsp;
        </div>
        <div class="bjui-searchBar">

            <label>申请日期：</label>
            <input name="applyStartAt" data-toggle="datepicker" value="${merAuditForm.applyStartAt}"  class="form-control" size="15" data-pattern="yyyyMMdd">
            <label>至：</label>
            <input name="applyEndAt" data-toggle="datepicker" value="${merAuditForm.applyEndAt}"  class="form-control" size="15" data-pattern="yyyyMMdd">

            <label>商户认证状态：</label>
            <select name="auditResult" id=""auditResult"" data-toggle="selectpicker">
                <option value="">请选择</option>
                <option <c:if test="${merAuditForm.auditResult==0}">selected</c:if> value="0">审核通过</option>
                <option <c:if test="${merAuditForm.auditResult==1}">selected</c:if> value="1">未审核</option>
                <option <c:if test="${merAuditForm.auditResult==2}">selected</c:if> value="2">审核未通过</option>
                <option <c:if test="${merAuditForm.auditResult==3}">selected</c:if> value="3">未提交审核</option>
                <option <c:if test="${merAuditForm.auditResult==4}">selected</c:if> value="4">审核进行中</option>
            </select>&nbsp;
        </div>
        <div class="bjui-searchBar">
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;

        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">商户号</th>
            <th align="center">商户名称</th>
            <th align="center">手机号</th>
            <th align="center">申请时间</th>
            <th align="center">审核结果</th>
            <th align="center">最后操作时间</th>
            <th align="center">操作人</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${merAuditForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.merId}"/>">
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center"><c:out value="${record.merName}"/></td>
                <td align="center"><c:out value="${record.phone}"/></td>
                <td align="center"><c:out value="${record.applyAt}"/></td>
                <td align="center">
                    <c:if test="${record.auditResult == '0'}">审核通过 </c:if>
                    <c:if test="${record.auditResult == '1'}">未审核 </c:if>
                    <c:if test="${record.auditResult == '2'}">审核未通过 </c:if>
                    <c:if test="${record.auditResult == '3'}">未提交审核 </c:if>
                    <c:if test="${record.auditResult == '4'}">审核进行中 </c:if>
                </td>
                <td align="center"><c:out value="${record.lastAt}"/></td>
                <td align="center"><c:out value="${record.userName}"/></td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/mer/audit_record_detail?id=<c:out value="${record.auditId}"/>" class="btn btn-green" data-toggle="navtab" data-id="navtab-detail"  data-title="查看审核记录">查看</a>
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
        <span> 条，共 <c:out value="${merAuditForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${merAuditForm.pagination.itemCount}"/>" data-page-size="<c:out value="${merAuditForm.pageSize}"/>" data-page-current="<c:out value="${merAuditForm.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
