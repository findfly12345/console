<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>乐刷商户管理</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merInfoForm" data-toggle="ajaxsearch"
          action="${pageContext.request.contextPath}/channel_mer/leshua_mer_manage" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${leShuaMerInfoForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${leShuaMerInfoForm.pageCurrent}">

        <div class="bjui-searchBar">

            <label>商户名：</label>
            <input type="text" name="merName" id="merName" value="${leShuaMerInfoForm.merName}" size="15">&nbsp;
            <label>商户号：</label>
            <input type="text" name="merId" id="merId" value="${leShuaMerInfoForm.merId}" size="15">&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            </button>
            &nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%"
           data-nowrap="true">
        <thead>
        <tr>
            <th align="center">商户号</th>
            <th align="center">商户名</th>
            <th align="center">乐刷商户号</th>
            <th align="center">乐刷商户名</th>
            <th align="center">更新时间</th>
            <th align="center">创建时间</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${leShuaMerInfoForm.pagination.list}" varStatus="status">
            <tr>
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center"><c:out value="${record.merName}"/></td>
                <td align="center"><c:out value="${record.leShuaMerId}"/></td>
                <td align="center"><c:out value="${record.leShuaMerName}"/></td>
                <td align="center"><c:out value="${record.updateTime}"/></td>
                <td align="center"><c:out value="${record.createTime}"/></td>
                <td align="center">
                        <%--乐刷商户进件通道--%>
                    <input type="button"
                           href="${pageContext.request.contextPath}/channel_mer/le_shua_register?memberId=${record.merId}"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.registedLeShua == '1'}"> disabled=true;</c:if> value="商户进件">
                    </input>
                    <input type="button"
                           href="${pageContext.request.contextPath}/channel_mer/le_shua_open_shaoma?memberId=${record.merId}&merchantId=${record.leShuaMerId}"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.openedShaoMa == '1'}"> disabled=true;</c:if> value="扫码支付业务开通">
                    </input>
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
        <span> 条，共 <c:out value="${leShuaMerInfoForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination"
         data-total="<c:out value="${leShuaMerInfoForm.pagination.itemCount}"/>"
         data-page-size="<c:out value="${leShuaMerInfoForm.pageSize}"/>"
         data-page-current="<c:out value="${leShuaMerInfoForm.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
