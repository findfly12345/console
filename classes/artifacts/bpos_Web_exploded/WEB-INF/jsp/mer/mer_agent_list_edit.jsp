<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>代理商商户信息</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merInfoForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/mer/query_agent_mer_edit_list" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${merInfoForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${merInfoForm.pageCurrent}">
        <div class="bjui-searchBar">

            <label>商户名：</label>
            <input type="text" name="merName" id="merName" value="${merInfoForm.merName}"  size="15">&nbsp;
            <label>商户号：</label>
            <input type="text" name="merId" id="merId" value="${merInfoForm.merId}"  size="15">&nbsp;
            <label>所属代理商：</label>
            <select name="agentId" id="agentId" data-toggle="selectpicker">
                <option value="">请选择</option>
                <c:forEach var="item" items="${merInfoForm.tblAgentInfoDos}" varStatus="status">
                    <option value="${item.memberId}" <c:if test="${item.memberId == merInfoForm.agentId}">selected</c:if>>${item.agentName}</option>
                </c:forEach>
            </select>&nbsp;
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
            <th align="center">商户状态</th>
            <th align="center">终端号</th>
            <th align="center">代理商号</th>
            <th align="center">代理商简称</th>
            <th align="center">注册名</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${merInfoForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.merId}"/>">
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center"><c:out value="${record.merName}"/></td>
                <td align="center">
                    <c:if test="${record.merStat=='3'}">未提交</c:if>
                    <c:if test="${record.merStat=='2'}">审核失败</c:if>
                    <c:if test="${record.merStat=='1'}">未审核</c:if>
                    <c:if test="${record.merStat=='0'}">审核通过</c:if>
                    <c:if test="${record.merStat=='4'}">审核中</c:if>
                </td>
                <td align="center"><c:out value="${record.termId}"/></td>
                <td align="center"><c:out value="${record.agentId}"/></td>
                <td align="center"><c:out value="${record.agentShortName}"/></td>
                <td align="center"><c:out value="${record.regName}"/></td>
                <td align="center">
                    <c:if test="${record.merStat =='1' || record.merStat =='2' || record.merStat =='3' }">
                        <a href="${pageContext.request.contextPath}/mer/query_mer_agent_edit?id=<c:out value="${record.merId}"/>" class="btn btn-green" data-toggle="navtab"data-id="navtab-detail"  data-title="商户详细信息(${record.merName})">编辑</a>
                    </c:if>
                        <%--<a href="${pageContext.request.contextPath}/IM/delete_inst?id=<c:out value="${record.instCode}"/>" class="btn btn-red" data-toggle="doajax"  data-confirm-msg="确定删除吗？">删除</a>--%>
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
        <span> 条，共 <c:out value="${merInfoForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${merInfoForm.pagination.itemCount}"/>" data-page-size="<c:out value="${merInfoForm.pageSize}"/>" data-page-current="<c:out value="${merInfoForm.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
