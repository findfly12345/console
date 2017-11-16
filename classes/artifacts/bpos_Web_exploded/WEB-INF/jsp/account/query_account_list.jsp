<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户账号查询</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="accountForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/account/query_account_list" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${accountForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${accountForm.pageCurrent}">
        <div class="bjui-searchBar">


            <label>代理商号：</label>
            <input type="text" name="agentId" id="agentId" value="${accountForm.agentId}"  size="15">&nbsp;
            <label>帐号：</label>
            <input type="text" name="acctNum" id="acctNum" value="${accountForm.acctNum}"  size="15">&nbsp;

            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">代理商号</th>
            <th align="center">客户类型</th>
            <th align="center">账户类型</th>
            <th align="center">账号</th>
            <th align="center">开户机构</th>
            <th align="center">开户子机构</th>
            <th align="center">开户机构名称</th>
            <th align="center">子机构名称</th>
            <th align="center">证件号码</th>
            <th align="center">证件类型</th>
            <th align="center">电话号码</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${accountForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.agentId}"/>">
                <td align="center"><c:out value="${record.agentId}"/></td>
                <td align="center"><c:out value="${record.partyClassCdStr}"/></td>
                <td align="center"><c:out value="${record.aml1TypeCdStr}"/></td>
                <td align="center"><c:out value="${record.acctNum}"/></td>
                <td align="center"><c:out value="${record.organkey}"/></td>
                <td align="center"><c:out value="${record.subOrgankey}"/></td>
                <td align="center"><c:out value="${record.organkeyName}"/></td>
                <td align="center"><c:out value="${record.subOrgankeyName}"/></td>
                <td align="center"><c:out value="${record.cardNo}"/></td>
                <td align="center"><c:out value="${record.cardTypeStr}"/></td>
                <td align="center"><c:out value="${record.tel}"/></td>
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
        <span> 条，共 <c:out value="${accountForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${accountForm.pagination.itemCount}"/>" data-page-size="<c:out value="${accountForm.pageSize}"/>" data-page-current="<c:out value="${accountForm.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
