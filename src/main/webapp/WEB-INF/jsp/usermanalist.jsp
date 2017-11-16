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
    <title>账户管理</title>
    <!-- 用户维护 -->
</head>
<body>
<div class="bjui-pageHeader">
  <form id="pagerForm" name="userManageForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/userManagement/query_a_user" method="post">
    <input type="hidden" name="pageSize" value="${userManageForm.pageSize}">
    <input type="hidden" name="pageCurrent" value="${userManageForm.pageCurrent}">
    <div class="bjui-searchBar">
        <label>帐户名：</label>
        <input type="text" name="username" id="username" value="${userManageForm.username}"  size="15">&nbsp;
        <label>组标识：</label>
        <input type="text" name="usrRemark" id="usrRemark" value="${userManageForm.usrRemark}"  size="15">&nbsp;
        <label>商户号：</label>
        <input type="text" name="merNumber" id="merNumber" value="${userManageForm.merNumber}"  size="15">&nbsp;        
        <select id="selectUsrDisableTag" name="usrDisableTag">
            <option value="" <c:out value="${userManageForm.usrDisableTag==''?'selected':''}" />>--用户状态--</option>
            <option value="1" <c:out value="${userManageForm.usrDisableTag=='1'?'selected':''}" />>启用</option>
            <option value="0" <c:out value="${userManageForm.usrDisableTag=='0'?'selected':''}" />>禁用</option>
        </select>
        <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
        <a href="${pageContext.request.contextPath}/userManagement/addnewpage" class="btn btn-green" data-toggle="dialog" data-width="550" data-height="550" data-id="dialog-normal" data-title="新增账户信息">新增用户</a>
    </div>
  </form>
</div>
<div class="bjui-pageContent tableContent">
  <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">账户ID</th>
      <th align="center">账户名</th>
      <th align="center">关联商户</th>
      <th align="center">组标识</th>
      <th align="center">状态</th>
      <th align="center">邮箱</th>
      <th align="center">操作</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${userManageForm.pagination.list}" varStatus="status">
<tr data-id="<c:out value="${record.usrId}"/>">
      <td align="center"><c:out value="${record.usrId}"/></td>
      <td align="center"><c:out value="${record.usrName}"/></td>
      <td align="center"><c:out value="${record.merNumber}"/></td>
      <td align="center"><c:out value="${record.usrRemark}"/></td>
      <td align="center"><c:out value="${record.usrDisableTag}"/></td>
      <td align="center"><c:out value="${record.usrEmail}"/></td>
      <td align="center">
        <a href="${pageContext.request.contextPath}/userManagement/edit?id=<c:out value="${record.usrId}"/>" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-normal" data-title="账户信息">修改</a>
        <a href="${pageContext.request.contextPath}/userManagement/goBindWithMer?id=<c:out value="${record.usrName}"/>&merNumber=<c:out value="${record.merNumber}"/>" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-normal" data-title="账户信息">绑定</a>
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
    <span> 条，共 <c:out value="${userManageForm.pagination.itemCount}"/> 条</span>
  </div>
  <div class="pagination-box" data-toggle="pagination"
       data-total="<c:out value="${userManageForm.pagination.itemCount}"/>"
       data-page-size="<c:out value="${userManageForm.pageSize}"/>"
       data-page-current="<c:out value="${userManageForm.pageCurrent}"/>">
  </div>
</div>

</div>

</body>
</html>
