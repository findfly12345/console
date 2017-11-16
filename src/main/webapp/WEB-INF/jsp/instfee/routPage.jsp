<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构费率配置</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" name="tblBtsInstDO" action="${pageContext.request.contextPath}/instmccfee/page" method="post">
        <input type="hidden" name="pageSize" value="${tblBtsInstDO.pageSize}">
        <input type="hidden" name="pageCurrent" value="${tblBtsInstDO.pageCurrent}">
        <div class="bjui-searchBar">
            <div class="form-group">
                <label>机构号:</label>
                <input type="text" id="instCode" name="instCode" value="${tblBtsInstDO.instCode}" class="form-unit" size="15">&nbsp;

                <label>机构名称:</label>
                <input type="text" id="instName" name="instName"  value="${tblBtsInstDO.instName}" class="form-unit" size="15">&nbsp;
            </div>
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
            <th align="center">机构号</th>
            <th align="center">机构类型</th>
            <th align="center">机构名</th>
            <th align="center">机构邮箱</th>
            <th align="center">机构联系方式</th>
            <th align="center">注册时间</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${tblBtsInstDO.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.instCode}"/>">
                <td align="center"><c:out value="${record.instCode}"/></td>
                <td align="center">
                    <c:if test="${record.instType == '0'}">收单机构</c:if>
                    <c:if test="${record.instType == '1'}">渠道机构</c:if>
                </td>
                <td align="center"><c:out value="${record.instName}"/></td>
                <td align="center"><c:out value="${record.instEmail}"/></td>
                <td align="center"><c:out value="${record.instTelphome}"/></td>
                <td align="center"><c:out value="${record.createTime}"/></td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/instmccfee/query_inst_edit?instId=${record.instCode}" class="btn btn-green" data-toggle="navtab" data-id="form-agtfee"  data-title="机构费率配置">费率配置</a>
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
        <span> 条，共 <c:out value="${tblBtsInstDO.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${tblBtsInstDO.pagination.itemCount}"/>" data-page-size="<c:out value="${tblBtsInstDO.pageSize}"/>" data-page-current="<c:out value="${tblBtsInstDO.pageCurrent}"/>">
    </div>

</div>
</body>
</html>

