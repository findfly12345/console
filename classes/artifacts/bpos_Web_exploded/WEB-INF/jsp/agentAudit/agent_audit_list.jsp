<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>代理商商户审核</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="tblAgentInfoDo" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/agentAudit/query_agent_audit_list" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${tblAgentInfoDo.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${tblAgentInfoDo.pageCurrent}">
        <div class="bjui-searchBar">


            <label>代理商号：</label>
            <input type="text" name="memberId" id="memberId" value="${tblAgentInfoDo.memberId}"  size="15">&nbsp;
            <label>代理商名称：</label>
            <input type="text" name="agentName" id="agentName" value="${tblAgentInfoDo.agentName}"  size="15">&nbsp;

            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">代理商号</th>
            <th align="center">代理商名称</th>
            <th align="center">公司注册名</th>
            <th align="center">注册号</th>
            <th align="center">公司简称</th>
            <th align="center">审核状态</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${tblAgentInfoDo.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.memberId}"/>">
                <td align="center"><c:out value="${record.memberId}"/></td>
                <td align="center"><c:out value="${record.agentName}"/></td>
                <td align="center"><c:out value="${record.regName}"/></td>
                <td align="center"><c:out value="${record.regCode}"/></td>
                <td align="center"><c:out value="${record.agentShortName}"/></td>
                <td align="center">未审核</td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/agentAudit/query_agent_detail_for_audit?id=<c:out value="${record.memberId}"/>" class="btn btn-green" data-toggle="navtab" data-id="navtab-detail"  data-title="代理商审核">审核</a>
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
        <span> 条，共 <c:out value="${tblAgentInfoDo.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${tblAgentInfoDo.pagination.itemCount}"/>" data-page-size="<c:out value="${tblAgentInfoDo.pageSize}"/>" data-page-current="<c:out value="${tblAgentInfoDo.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
