<%--
  Created by IntelliJ IDEA.
  User: liuBin
  Date: 2017/1/10
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>T0资金结算</title>

<script language="javascript">
	function prtReport() {
		checkFileForm.action = '${pageContext.request.contextPath}/checkWithOutCard/D0download';
		checkFileForm.submit();
	}
</script>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="checkFileForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/checkWithOutCard/get_t0_settlement"
			method="post" modelAttribute="checkFileForm">
			<div class="bjui-searchBar">
				<form:input type="hidden" path="pageSize" />
				<form:input type="hidden" path="pageCurrent" />
				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">对账日期</label>
					<form:input type="text" path="filterDate" data-pattern="yyyy-MM-dd"
						size="15" class="form-control" data-toggle="datepicker"
						data-rule="对账日期:required;"></form:input>

					<label class="control-label x85">商户号</label>
					<form:input path="merchantCode" class="form-control" size="15" />

					<label class="control-label x85">交易账号</label>
					<form:input path="accNo" class="form-control" size="15" />
					<label class="control-label x85">账号名</label>
					<form:input path="accName" class="form-control" size="15" />
				</div>
				<div class="form-group" style="margin: 20px 0 20px;">


					<label>机构号：</label>
					<form:select path="instCode" data-toggle="selectpicker">
						<form:option value="">请选择</form:option>
						<c:forEach items="${tblBtsInstDOList}" var="tblBtsInstDO">
							<form:option value="${tblBtsInstDO.instCode}">${tblBtsInstDO.instName}</form:option>
						</c:forEach>
					</form:select>
				</div>

				<div class="form-group" style="margin: 20px 0 20px;">
					<button type="submit" class="btn-default" data-icon="save">获取数据</button>
					&nbsp;
					<button type="button" class="btn-default" data-icon="home"
						onclick="prtReport();">导出</button>
					&nbsp;
				</div>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
		<c:if test="${tblT0CheckFileResponseDoList!=null}">
			<table
				class="table table-bordered table-hover table-striped table-top"
				data-toggle="tablefixed" data-width="100%" data-nowrap="true">
				<thead>
					<tr>
						<th align="center">清算日期</th>
						<th align="center">商户编号</th>
						<th align="center">交易流水号</th>
						<th align="center">交易账号</th>
						<th align="center">交易户名</th>
						<th align="center">清算金额</th>
						<th align="center">单笔提现手续费</th>
						<th align="center">商户交易手续费</th>
						<th align="center">清算状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tblT1CheckFileRespondeDo"
						items="${tblT0CheckFileResponseDoList}">
						<tr>
							<td align="center">${tblT1CheckFileRespondeDo.settledate}</td>
							<td align="center">${tblT1CheckFileRespondeDo.merchantcode}</td>
							<td align="center">${tblT1CheckFileRespondeDo.reqmsgid}</td>
							<td align="center">${tblT1CheckFileRespondeDo.accno}</td>
							<td align="center">${tblT1CheckFileRespondeDo.accname}</td>
							<td align="center">${tblT1CheckFileRespondeDo.drawamount}</td>
							<td align="center">${tblT1CheckFileRespondeDo.drawfee}</td>
							<td align="center">${tblT1CheckFileRespondeDo.tradefee}</td>
							<c:if test="${tblT1CheckFileRespondeDo.resptype == 'S'}">
								<td align="center">成功</td>
							</c:if>
							<c:if test="${tblT1CheckFileRespondeDo.resptype == 'E'}">
								<td align="center">失败</td>
							</c:if>
							<c:if test="${tblT1CheckFileRespondeDo.resptype == 'R'}">
								<td align="center">处理中</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
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

