<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>POS路由维护</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="routeControlForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/route/search"
			method="post" modelAttribute="route">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent" />
			<div class="bjui-searchBar">
				<label>机构号：</label>
				<form:input path="instId" class="form-control" size="15" />
				<label>商户号：</label>
				<form:input path="merId" class="form-control" size="15" />
				<label>终端号：</label>
				<form:input path="termId" class="form-control" size="15" />
				<button type="submit" class="btn-default" data-icon="search">查询</button>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
		<table
			class="table table-bordered table-hover table-striped table-top"
			data-toggle="tablefixed" data-width="100%" data-nowrap="true">
			<thead>
				<tr>
					<th width="30" align="center">序号</th>
					<th align="center">机构号</th>
					<th align="center">商户号</th>
					<th align="center">终端号</th>
					<th align="center">目的网关</th>
					<th align="center">目的商户号</th>
					<th align="center">目的终端号</th>
					<th align="center">状态</th>
					<th align="center">备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pageUser.list}" varStatus="status">
					<td>${status.index+1}</td>
					<td align="center"><c:out value="${record.instId}" /></td>
					<td align="center"><c:out value="${record.merId}" /></td>
					<td align="center"><c:out value="${record.termId}" /></td>
					<td align="center"><c:out value="${record.destGateId}" /></td>
					<td align="center"><c:out value="${record.destMerId}" /></td>
					<td align="center"><c:out value="${record.destTermId}" /></td>
					<td align="center"><c:if test="${record.stat == 'Y'}">启用</c:if>
						<c:if test="${record.stat == 'N'}">关闭</c:if></td>
					<td align="center"><c:out value="${record.remark}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="bjui-pageFooter" id="pageFooter">
		<div class="pages">
			<span>每页 </span>
			<div class="selectPagesize">
				<select data-toggle="selectpicker"
					data-toggle-change="changepagesize">
					<option value="10">10</option>
					<option value="30">30</option>
					<option value="60">60</option>
					<option value="100">100</option>
				</select>
			</div>
			<span> 条，共 <c:out value="${pageUser.itemCount}" /> 条
			</span>
		</div>
		<div class="pagination-box" data-toggle="pagination"
			data-total="<c:out value="${pageUser.itemCount}"/>"
			data-page-size="<c:out value="${pageUser.pageSize}"/>"
			data-page-current="<c:out value="${pageUser.pageIndex}"/>"></div>
	</div>
</body>
</html>