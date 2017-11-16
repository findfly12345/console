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
    <title>机构操作员维护</title>
</head>
<body>
<div class="bjui-pageHeader">
  <form id="pagerForm" name="institutionForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/IM/inst_user_query_page" method="get">
    <input type="hidden" name="pageSize" value="${institutionForm.pageSize}">
    <input type="hidden" name="pageCurrent" value="${institutiaonForm.pageCurrent}">
    <div class="bjui-searchBar">
    <label>机构号：</label>
    <label  name="instCode" id="instCode" value="${institutionForm.instCode}"  size="15">${institutionForm.instCode}</label>&nbsp;
    <label>机构名：</label>
    <label  name="instName" id="instName" value="${institutionForm.instName}"  size="15">${institutionForm.instName}</label>&nbsp;
    <label>机构类型：</label>
    <label  name="instType" id="instType" value="${institutionForm.instType}"  size="15">${institutionForm.instType}</label>&nbsp;
    <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
    <a href="${pageContext.request.contextPath}/IM/add_inst_user_page?instCode=${institutionForm.instCode}&instName=${institutionForm.instName}&instType=${institutionForm.instType}" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="300" data-id="dialog-normal" data-title="新增操作员">新增操作员</a>
    </div>
  </form>
</div>
<div class="bjui-pageContent tableContent">
  <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">操作员号码</th>
      <th align="center">操作员名称</th>
      <th align="center">操作员类型</th>
      <th align="center">操作</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${institutionForm.pagination.list}" varStatus="status">
<tr data-id="<c:out value="${record.usrId}"/>">
      <td align="center"><c:out value="${record.usrName}"/></td>
      <td align="center"><c:out value="${record.usrRemark}"/></td>
      <td align="center"><c:out value="${record.usrType}"/></td>

      <td align="center">
        <a href="${pageContext.request.contextPath}/IM/reset_pass?id=<c:out value="${record.usrId}"/>" class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定重置密码？">重置密码</a>
        <a href="${pageContext.request.contextPath}/IM/disable_user?id=<c:out value="${record.usrId}"/>" class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定删除吗？">删除</a>
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
    <span> 条，共 <c:out value="${institutionForm.pagination.itemCount}"/> 条</span>
  </div>
  <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${institutionForm.pagination.itemCount}"/>" data-page-size="<c:out value="${institutionForm.pageSize}"/>" data-page-current="<c:out value="${institutionForm.pageCurrent}"/>">
  </div>
</div>

</div>

</body>
</html>
