<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>机构分润</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="instForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/profit/instsearch"
			method="post" modelAttribute="profit">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent" />
			<div class="bjui-searchBar">
				<label>机构号：</label>
				<form:select path="instId" data-toggle="selectpicker">
					<form:option value="">请选择</form:option>
					<c:forEach items="${tblBtsInstDOList}" var="tblBtsInstDO">
						<form:option value="${tblBtsInstDO.instCode}">${tblBtsInstDO.instName}</form:option>
					</c:forEach>
				</form:select>
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
				<a class="btn btn-green" data-title="导出机构分润报表" data-id="form"
				   data-toggle="dialog" data-width="800" data-height="400"
				   data-id="user-add" href="profit/exportIinstProfit">导出机构分润报表</a>

					<a class="btn btn-green" data-title="导出通道分润报表" data-id="form"
				   data-toggle="dialog" data-width="800" data-height="400"
				   data-id="user-add" href="profit/exportChannelReport">导出通道分润报表</a>
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
					<th align="center">机构名</th>
					<th align="center">日期</th>
					<th align="center">交易类型</th>
					<th align="center">交易总金额</th>
					<th align="center">分润</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pageUser.list}" varStatus="status">
					<td>${status.index+1}</td>
					<td align="center"><c:out value="${record.instId}" /></td>
					<td align="center"><c:out value="${record.instName}" /></td>
					<td align="center"><c:out value="${record.transDate}" /></td>
					<td align="center">
						<c:if test="${record.transType == '03'}">微信</c:if>
						<c:if test="${record.transType == '04'}">支付宝</c:if>
						<c:if test="${record.transType == '01'}">借记卡</c:if>
						<c:if test="${record.transType == '02'}">贷记卡</c:if>
					</td>
					<td align="center"><c:out value="${record.transSum}" /></td>
					<td align="center"><c:out value="${record.amtSum}" /></td>				
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