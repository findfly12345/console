<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/user/search" method="post"
			modelAttribute="user">
			<input type="hidden" name="pageSize"
				value="${transRateCountForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${transRateCountForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>用户名：</label>
				<form:input path="userName" class="form-control" size="15" />
				&nbsp; <label>用户状态：</label>
				<form:select path="status" data-toggle="selectpicker">
					<form:option value="">未选择</form:option>
					<form:option value="00">正常</form:option>
					<form:option value="10">冻结</form:option>
					<form:option value="99">锁定</form:option>
				</form:select>
				<button type="submit" class="btn-default" data-icon="search">查询</button>
				&nbsp; <a class="btn btn-green" data-title="增加用户" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-add" href="user/addpage">增加用户</a>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">id</th>
      <th align="center">用户名</th>
      <th align="center">创建时间</th>
      <th align="center">最后登入时间</th>
      <th align="center">用户状态</th>
      <th align="center">编辑</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${pageUser.list}" varStatus="status">
<tr data-id="<c:out value="${record.userId}"/>">
      <td align="center"><c:out value="${record.userId}"/></td>
      <td align="center"><c:out value="${record.name}"/></td>
      <td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${record.createDate}" /></td>
      <td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${record.lastLoginDate}" /></td>
      <td align="center">${(record.status == 'S') ? '正常' : ''}${(record.status == 'F') ? '冻结' : ''}${(record.status == 'D') ? '删除' : ''}</td>
      <td align="center"><a class="btn btn-green" data-title="增加用户" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-update" href="user/updatepage/${record.name}">编辑</a>
		<a class="btn btn-red" data-confirm-msg="确定要删除该行信息吗？" data-toggle="doajax" href="user/delete/${record.name}">删</a></td>
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
    <span> 条，共 <c:out value="${pageUser.itemCount}"/> 条</span>
  </div>
  <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${pageUser.itemCount}"/>" data-page-size="<c:out value="${pageUser.pageSize}"/>" data-page-current="<c:out value="${pageUser.pageIndex}"/>">
  </div>
</div>
</body>
</html>
