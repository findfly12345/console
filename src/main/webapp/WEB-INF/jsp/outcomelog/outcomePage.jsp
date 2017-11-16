<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>结算管理</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/outcome/search"
			method="post" modelAttribute="trans">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent" />
			<div class="bjui-searchBar">
				<label>网关号：</label>
				<form:select path="channelId" data-toggle="selectpicker">
					<form:option value="">全部</form:option>
				</form:select>
				<label>商户号：</label>
				<form:input path="memberId" class="form-control" size="15" />
				<label>交易日期：</label>
				<form:input path="startTransDateTime" type="text"
					data-toggle="datepicker" class="form-control" size="15"
					data-pattern="yyyyMMdd" />
				<label>至：</label>
				<form:input path="endTransDateTime" type="text"
					data-toggle="datepicker" class="form-control" size="15"
					data-pattern="yyyyMMdd" />
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
					<th align="center">结算订单号</th>
					<th align="center">商户号</th>
					<th align="center">交易时间</th>
                    <th align="center">金额</th>
                    <th align="center">手续费</th>
					<th align="center">实际金额</th>
					<th align="center">交易状态</th>
					<th align="center">结算帐号</th>
					<th align="center">交易订单号</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pageUser.list}" varStatus="status">
					<tr data-id="<c:out value="${record.orderId}"/>">
						<td align="center"><c:out value="${record.orderId}" /></td>
						<td align="center"><c:out value="${record.instMerId}" /></td>
						<td align="center"><c:out value ="${record.orderRecvTime}" /></td>
						<td align="center"><c:out value="${record.outcomeAmount}" /></td>
                        <td align="center"><c:out value="${record.outcomeFee}" /></td>
                        <td align="center"><c:out value="${record.outcomeNetAmount}" /></td>
						<td align="center"><c:out value="${record.outcomeStatus}" /></td>
						<td align="center"><c:out value="${record.acctNo}" /></td>
						<td align="center"><c:out value="${record.transId}" /></td>
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