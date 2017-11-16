<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户费率配置</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" name="agentQueryForm" action="${pageContext.request.contextPath}/agtInfo/query_agents_fees" method="post">
        <input type="hidden" name="pageSize" value="${agentQueryForm.pageSize}">
        <input type="hidden" name="pageCurrent" value="${agentQueryForm.pageCurrent}">
        <%--<input type="hidden" name="orderField" value="${param.orderField}">--%>
        <%--<input type="hidden" name="orderDirection" value="${param.orderDirection}">--%>
        <div class="bjui-searchBar">
            <div class="form-group">
                <label>代理商简称:</label>
                <input type="text" id="agentShortName" name="agentShortName" value="${agentQueryForm.agentShortName}" class="form-unit" size="15">&nbsp;

                <label>代理商号:</label>
                <input type="text" id="memberId" name="memberId"  value="${agentQueryForm.memberId}" class="form-unit" size="15">&nbsp;

                <label>代理商状态:</label>
                <select class="form-unit" id="memberStat" name="memberStat" data-toggle="selectpicker">
                    <option value="">全部</option>
                    <option value="0" <c:if test="${agentQueryForm.memberStat=='0'}">selected</c:if>>已上线</option>
                    <option value="1" <c:if test="${agentQueryForm.memberStat=='1'}">selected</c:if>>关闭</option>
                    <option value="2" <c:if test="${agentQueryForm.memberStat=='4'}">selected</c:if>>暂停</option>
                </select>
            </div>
            <%--<div class="form-group">--%>
            <%--<c:if test="${agentQueryForm.showOemSelect == '1'}">--%>
            <%--<option value="ALL">全部</option>--%>
            <%--<c:forEach var="record" items="${agentQueryForm.MposOEMList}" varStatus="status">--%>
            <%--<option value="${record.bankId}" <c:if test="${record.bankId == certApplyForm.bankId}">selected</c:if> >${record.bankShortName}</option>--%>
            <%--</c:forEach>--%>
            <%--</c:if>--%>
            <%--</div>--%>
            <div class="form-group">
                <label></label>
                <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">代理商简称</th>
            <th align="center">代理商商号</th>
            <th align="center">代理商状态</th>
            <th align="center">代理商级别</th>
            <th align="center">上级代理商简称(号码)</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${agentQueryForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.memberId}"/>">
                <td align="center"><c:out value="${record.agentShortName}"/></td>
                <td align="center"><c:out value="${record.memberId}"/></td>
                <td align="center">
                    <c:if test="${record.memberStat=='2'}">暂时停用</c:if>
                    <c:if test="${record.memberStat=='1'}">关闭</c:if>
                    <c:if test="${record.memberStat=='0'}">已上线</c:if>
                </td>
                <td align="center"><c:out value="${record.agentLevel}"/></td>
                <td align="center"><c:out value="${record.upAgentId}"/></td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/agtInfo/query_agent_fee_edit?memberId=${record.memberId}" class="btn btn-green" data-toggle="navtab" data-id="form-agtfee"  data-title="代理商费率配置">费率配置</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
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
        <span> 条，共 <c:out value="${agentQueryForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${agentQueryForm.pagination.itemCount}"/>" data-page-size="<c:out value="${agentQueryForm.pageSize}"/>" data-page-current="<c:out value="${agentQueryForm.pageCurrent}"/>">
    </div>

</div>
</body>
</html>

