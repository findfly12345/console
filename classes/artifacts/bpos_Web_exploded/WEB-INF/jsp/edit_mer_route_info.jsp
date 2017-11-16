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
    <form id="pagerForm" name="merRouteQueryForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/routeManage/QueryMerRouteConf" method="post">
        <div class="bjui-searchBar">
            <input type="hidden" name="id" value="${merRouteQueryForm.sId}">
            <input type="hidden" name="merId" value="${merRouteQueryForm.sMerId}">

                <label class="control-label x85">终端商户号：</label>
                <input type="text" value="<c:out value="${merRouteQueryForm.sMerId}"/>" name="sMerId" size="15"/>&nbsp;

                <label class="control-label x85"> 笔数：</label>
                <input type="text" value="<c:out value="${merRouteQueryForm.sCurrentTradeCount}"/>" name="sCurrentTradeCount" size="15"/>&nbsp;

                <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
                <a href="${pageContext.request.contextPath}/routeManage/select_big_mers" class="btn btn-green" data-toggle="dialog" data-width="750" data-height="240" data-id="dialog-normal-route-edit"  data-title="新增路由规则">
                新增</a>

        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">

    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">终端商户号</th>
            <th align="center">第几笔</th>
            <th align="center">对应大商户</th>
            <th align="center">操作</th>
            <th align="center"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${merRouteQueryForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.id}"/>">
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center"><c:out value="${record.currentTradeCount}"/></td>
                <td align="center"><c:out value="${record.refsBigMers}"/></td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/routeManage/select_big_mers?id=<c:out value="${record.id}"/>" class="btn btn-green" data-toggle="dialog" data-width="750" data-height="240" data-id="dialog-normal-route-edit"  data-title="路由规则修改">
                        修改</a>

                    <a href="${pageContext.request.contextPath}/routeManage/delete_trade_count_rule?id=<c:out value="${record.id}"/>" class="btn btn-red" data-toggle="doajax"  data-confirm-msg="确定删除吗？">
                         删除</a>
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
        <span> 条，共 <c:out value="${merRouteQueryForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${merRouteQueryForm.pagination.itemCount}"/>" data-page-size="<c:out value="${merRouteQueryForm.pageSize}"/>" data-page-current="<c:out value="${merRouteQueryForm.pageCurrent}"/>">
    </div>
</div>

</div>

</body>
</html>
