<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>机构交易权限管理</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="instTransAuthForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/insttransauth/search"
			method="post" modelAttribute="insttransauth">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent" />
			<div class="bjui-searchBar">
				<label>机构号：</label>
				<form:input path="instCode" class="form-control" size="15" />

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
					<th align="center">POS</th>
					<th align="center">微信</th>
					<th align="center">支付宝</th>
					<th align="center">其他权限</th>
					<th align="center">备注</th>
					<th align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pageUser.list}" varStatus="status">
					<td>${status.index+1}</td>
					<td align="center"><c:out value="${record.instCode}" /></td>
					<td align="center"><c:if test="${record.posStat == 'Y'}">已开通</c:if>
						<c:if test="${record.posStat == 'N'}">未开通</c:if></td>
					<td align="center"><c:if test="${record.chatStat == 'Y'}">已开通</c:if>
						<c:if test="${record.chatStat == 'N'}">未开通</c:if></td>
					<td align="center"><c:if test="${record.allipayStat == 'Y'}">已开通</c:if>
						<c:if test="${record.allipayStat == 'N'}">未开通</c:if></td>
					<td align="center"><c:out value="${record.authStat}" /></td>
					<td align="center"><c:out value="${record.remark}" /></td>
					<td align="center"><a class="btn btn-green" data-title="修改"
						data-id="form" data-toggle="dialog" data-width="800"
						data-height="400" data-id="user-update"
						href="insttransauth/updatepage/${record.instCode}">修改</a> <a
						class="btn btn-red" data-confirm-msg="确定要删除该行信息吗？"
						data-toggle="doajax" href="insttransauth/delete/${record.instCode}">删除</a>
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