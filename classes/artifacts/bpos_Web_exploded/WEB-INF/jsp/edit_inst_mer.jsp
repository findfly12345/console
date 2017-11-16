<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/18
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户信息</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="instMerKeyForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/IM/inst_mer_query" method="post">
        <input type="hidden" name="pageSize" value="${instMerKeyForm.pageSize}">
        <input type="hidden" name="pageCurrent" value="${instMerKeyForm.pageCurrent}">
        <input type="hidden" name="instId" value="${instMerKeyForm.instId}">
        <input type="hidden" name="instType" value="${instMerKeyForm.instType}">
        <div class="bjui-searchBar">
            <label>商户号：</label>
            <input type="text" name="instMerId" id="instMerId" value="${instMerKeyForm.instMerId}"  size="15">&nbsp;
            <label>终端号：</label>
            <input type="text" name="instMerTermId" id="instMerTermId" value="${instMerKeyForm.instMerTermId}"  size="15">&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a  href="${pageContext.request.contextPath}/IM/add_inst_mer_page?instId=${instMerKeyForm.instId}" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="300" data-id="dialog-mask" data-mask="true" data-title="新增机构商户(${instMerKeyForm.instId})">新增商户</a>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">商户号</th>
            <th align="center">终端号</th>
            <th align="center"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${instMerKeyForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.instId}"/>">
                <td align="center"><c:out value="${record.instMerId}"/></td>
                <td align="center"><c:out value="${record.instMerTermId}"/></td>
                <td align="center">
                <a href="${pageContext.request.contextPath}/IM/delete_mer_for_inst?id=<c:out value="${record.instId}"/>&merId=<c:out value="${record.instMerId}"/>" class="btn btn-red" data-toggle="doajax"  data-confirm-msg="确定删除吗？">删除</a>
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
        <span> 条，共 <c:out value="${instMerKeyForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${instMerKeyForm.pagination.itemCount}"/>" data-page-size="<c:out value="${instMerKeyForm.pageSize}"/>" data-page-current="<c:out value="${instMerKeyForm.pageCurrent}"/>">
    </div>
</div>

</div>

</body>
</html>
