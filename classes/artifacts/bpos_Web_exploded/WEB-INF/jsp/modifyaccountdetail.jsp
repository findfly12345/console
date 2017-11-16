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
    <title>账户权限设置</title>
</head>
<body>
<div class="bjui-pageHeader">
    <input type="hidden" name="pageSize" value="${accountOauthForm.pageSize}">
    <input type="hidden" name="pageCurrent" value="${accountOauthForm.pageCurrent}">
    <div class="bjui-searchBar">
      <label>账户名：</label>
      <label><c:out value="${accountOauthForm.usrName}"/></label>&nbsp;
      <label>账户状态：</label>
      <label><c:out value="${accountOauthForm.usrDisableTag}"/></label>&nbsp;
      <a href="${pageContext.request.contextPath}/userManagement/acctenable?id=${accountOauthForm.usrId}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定启用吗？" >启用</a>&nbsp;
      <a href="${pageContext.request.contextPath}/userManagement/acctdinable?id=${accountOauthForm.usrId}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定禁用吗？" >禁用</a>&nbsp;

      <!-- <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;-->
    </div>
</div>
<div class="bjui-pageContent tableContent">
<form id="pagerForm" name="accountOauthForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/userManagement/addroleforuser" method="post">
  <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <tbody>
<c:forEach var="record" items="${accountOauthForm.pagination.list}" varStatus="status">
<tr data-id="<c:out value="${record.roleId}"/>">
      <td align="center"><c:out value="${record.roleName}"/></td>
      <td><input type="checkbox" name="ids" value=""></td>
</tr>
  </c:forEach>
    </tbody>
  </table>
  <button type="submit" class="btn-default" data-icon="save">保存</button>
  </form>
</div>
<div class="bjui-pageFooter" id="pageFooter">

</div>

</div>

</body>
</html>
