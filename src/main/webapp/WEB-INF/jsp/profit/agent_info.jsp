<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>代理商分润</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="profitForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/profit/agentsearch"
			method="post" modelAttribute="profit">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent" />
			<div class="bjui-searchBar">
				<label>代理商号：</label>
				<form:input path="memberId" class="form-control" size="15" />

				<label>交易类型：</label>
				<form:select path="transType" data-toggle="selectpicker">
					<form:option value="">请选择</form:option>
					<form:option value="03">微信</form:option>
					<form:option value="04">支付宝</form:option>
					<form:option value="01">借记卡</form:option>
					<form:option value="02">贷记卡</form:option>
				</form:select>

				<label>日期：</label>
				<form:input path="startDate" type="text" data-toggle="datepicker" value="${instForm.startDate}"
							class="form-control" size="15" data-pattern="yyyyMMdd"/>
				<label>至：</label>
				<form:input path="endDate" type="text" data-toggle="datepicker" value="${instForm.endDate}"
							class="form-control" size="15" data-pattern="yyyyMMdd" />
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
					<th align="center">代理商号</th>
					<th align="center">代理名</th>
					<th align="center">日期</th>
					<th align="center">交易类型</th>
					<th align="center">交易总金额</th>
					<th align="center">分润</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pageUser.list}" varStatus="status">
					<td>${status.index+1}</td>
					<td align="center"><c:out value="${record.memberId}" /></td>
					<td align="center"><c:out value="${record.agentName}" /></td>
					<td align="center"><c:out value="${record.transDate}" /></td>
					<td align="center">
						<c:if test="${record.transType == '03'}">微信</c:if>
						<c:if test="${record.transType == '04'}">支付宝</c:if>
						<c:if test="${record.transType == '01'}">借记卡</c:if>
						<c:if test="${record.transType == '02'}">贷记卡</c:if>
					</td>
					<td align="center"><c:out value="${record.transSum}" /></td>
					<td align="center"><c:out value="${record.profitAmt}" /></td>				
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