<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>风控触发商户</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="riskBlackListForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/riskblacklist/search"
			method="post" modelAttribute="rout">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent" />
			<div class="bjui-searchBar">
				<label>卡号：</label>
				<form:input path="priCardNo" class="form-control" size="15" />
				<label>商户号：</label>
				<form:input path="merchId" class="form-control" size="15" />
				<label>终端号：</label>
				<form:input path="termCode" class="form-control" size="15" />
				<button type="submit" class="btn-default" data-icon="search">查询</button>
				<a class="btn btn-green" data-title="增加" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400"
					data-id="user-add" href="riskblacklist/addpage">新增黑名单</a>
				<button type="submit" id="exportRiskControl" class="btn-default" data-icon="save" onclick="exportRiskTriggerMer()">导出</button>&nbsp;            					
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
					<th align="center">卡号</th>
					<th align="center">机构号</th>
					<th align="center">商户号</th>
					<th align="center">终端号</th>
					<th align="center">触发规则</th>
					<th align="center">状态</th>
					<th align="center">创建日期</th>
					<th align="center">创建时间</th>
					<th align="center">备注</th>		
					<th align="center">操作</th>												
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pageUser.list}" varStatus="status">
					<td>${status.index+1}</td>
					<td align="center"><c:out value="${record.priCardNo}" /></td>
					<td align="center"><c:out value="${record.instCode}" /></td>
					<td align="center"><c:out value="${record.merchId}" /></td>
					<td align="center"><c:out value="${record.termCode}" /></td>
					<td align="center"><c:out value="${record.errRule}" /></td>
					<td align="center"><c:if test="${record.riskFlag == 'Y'}">启用</c:if>
						<c:if test="${record.riskFlag == 'N'}">关闭</c:if></td>
					<td align="center"><c:out value="${record.createDate}" /></td>
				    <td align="center"><c:out value="${record.createTime}" /></td>
				    <td align="center"><c:out value="${record.remark}" /></td>
				   
					<td align="center">
					<a class="btn btn-red" data-confirm-msg="确定要删除该行信息吗？"
					   data-toggle="doajax"  href="riskblacklist/delete/${record.priCardNo}/${record.merchId}/${record.termCode}">删除</a>
		
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

<script type="text/javascript">

function exportRiskTriggerMer() {
	riskBlackListForm.action = 
		'${pageContext.request.contextPath}/riskblacklist/export_risk_trigger_mer';
	riskBlackListForm.submit();
	riskBlackListForm.action = '${pageContext.request.contextPath}/riskblacklist/search';
}	

</script>

</html>