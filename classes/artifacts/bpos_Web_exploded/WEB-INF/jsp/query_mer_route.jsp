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
    <title>商户路由维护</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="instTransCtrlPerform" data-toggle="ajaxsearch"   action="${pageContext.request.contextPath}/routeManage/query_mer_route" method="post">
        <input type="hidden" name="pageSize" value="${instTransCtrlPerform.pageSize}">
        <input type="hidden" name="pageCurrent" value="${instTransCtrlPerform.pageCurrent}">
        <div class="bjui-searchBar">
            <a href="${pageContext.request.contextPath}/IM/add_inst_mer_key_batch" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="420" data-id="dialog-normal-batch-key" data-title="导入">批量导入</a>&nbsp;
            <label>机构名称：</label>
            <input type="text" name="instName" id="instName" value="${instTransCtrlPerform.instName}"  size="15">&nbsp;
            <label>机构类别：</label>
            <select name="instType" id="instType" data-toggle="selectpicker">
                <option value="">请选择</option>
                <option <c:if test="${instTransCtrlPerform.instType=='0'}">selected="selected"</c:if> value="0">收单机构</option>
                <option <c:if test="${instTransCtrlPerform.instType=='1'}">selected="selected"</c:if> value="1">渠道</option>
            </select>&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;

        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">机构名称</th>
            <th align="center">机构类型</th>
            <th align="center">商户号</th>
            <th align="center">终端号</th>
            <th align="center">操作</th>
            <th align="center"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${instTransCtrlPerform.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.instId}"/>">
                <td align="center"><c:out value="${record.instName}"/></td>
                <td align="center"><c:out value="${record.instType}"/></td>
                <td align="center"><c:out value="${record.instMerId}"/></td>
                <td align="center"><c:out value="${record.instMerTermId}"/></td>
                    <%--<td><input type="text" id="instHsmIndex" placeholder="请输入密钥索引" name="instSecKeys[${record.instCode}].instHsmIndex" value="${record.instHsmIndex}" /></td>--%>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/IM/generateInstSecKey?id=<c:out value="${record.instId}"/>" class="btn btn-red" data-toggle="doajax" >
                        <c:if test="${record.checkStatus=='Y'}">重新签到</c:if>
                        <c:if test="${record.checkStatus=='N'}">点击签到</c:if>
                    </a>
                        <%--<a href="javascript:void(0)" onclick="generateSecretKey('${record.instCode}')">--%>
                        <%--<c:if test="${record.checkStatus=='Y'}">重新签到</c:if>--%>
                        <%--<c:if test="${record.checkStatus=='N'}">点击签到</c:if>--%>
                        <%--</a>--%>
                </td>
                <td><font  color="blue" id="${record.instId}"><c:if test="${record.checkStatus=='Y'}">已经签到</c:if></font></td>
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
        <span> 条，共 <c:out value="${instTransCtrlPerform.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${instTransCtrlPerform.pagination.itemCount}"/>" data-page-size="<c:out value="${instTransCtrlPerform.pageSize}"/>" data-page-current="<c:out value="${instTransCtrlPerform.pageCurrent}"/>">
    </div>

</div>

</body>
</html>
