<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>交易管理</title>
<!-- POS交易查询 -->
    <script language="javascript">
        function prtReport() {
            transRateCountForm.action = '${pageContext.request.contextPath}/trans/download';
            transRateCountForm.submit();
            transRateCountForm.action = '${pageContext.request.contextPath}/trans/search';
        }
    </script>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/trans/search" method="post"
			modelAttribute="trans">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent"/>
			
			<div class="form-group" style="margin: 15px 0 15px; ">
				<label>交易卡号:</label>
				<form:input path="cardNo" class="form-control" size="15" />
				<label>大商户号:</label>
                <form:input path="bigMerId" class="form-control" size="15" />				
                <label>参考号:</label>
                <form:input path="refNum" class="form-control" size="15" />
                <label>订单号:</label>
                <form:input path="ordId" class="form-control" size="20"/>				
				<label>上游商户编号:</label>
                <form:input path="posMerId" class="form-control" size="15" />
			</div>

			<div class="form-group" style="margin: 15px 0 15px; ">
			    <label>商户名称:</label>
                <form:input path="sysMerName" class="form-control" size="15" />
			    <label>代理商号:</label>
                <form:input path="agentId" class="form-control" size="15" />
			</div>
			
			<div class="form-group" style="margin: 15px 0 15px; ">
				<label>交易日期:</label>
				<form:input path="startTransDateTime" type="text" data-toggle="datepicker" value="${trans.startTransDateTime}"
					class="form-control" size="15" data-pattern="yyyyMMdd"/>
				<label>至：</label>
				<form:input path="endTransDateTime" type="text" data-toggle="datepicker" value="${trans.endTransDateTime}"
					class="form-control" size="15" data-pattern="yyyyMMdd" />
				<label>网关号：</label>
                <form:select path="gateId" data-toggle="selectpicker">
                    <form:option value="">请选择</form:option>
                    <form:option value="U1">钱宝</form:option>
                    <form:option value="U2">银嘉</form:option>
                    <form:option value="U4">翰银</form:option>
                </form:select>
                <label>卡类型：</label>
                <form:select path="cardFlag" data-toggle="selectpicker">
                    <form:option value="">请选择</form:option>
                    <form:option value="1">借记卡</form:option>
                    <form:option value="2">贷记卡</form:option>
                    <form:option value="3">其他</form:option>
                </form:select>
                <label>交易状态：</label>
                <form:select path="transStat" data-toggle="selectpicker">
                    <form:option value="">请选择</form:option>
                    <form:option value="S">成功</form:option>
                    <form:option value="F">失败</form:option>
                    <form:option value="I">待处理</form:option>
                </form:select>
                <label>机构号：</label>
				<form:select path="instId" data-toggle="selectpicker">
					<form:option value="">请选择</form:option>
					<c:forEach items="${tblBtsInstDOList}" var="tblBtsInstDO">
						<form:option value="${tblBtsInstDO.instCode}">${tblBtsInstDO.instName}</form:option>
					</c:forEach>
				</form:select>
				<button type="submit" class="btn-default" data-icon="search">查询</button>
				<button type="button" class="btn-default" data-icon="home" onclick="prtReport();">导出</button>&nbsp;
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
		<table
			class="table table-bordered table-hover table-striped table-top"
			data-toggle="tablefixed" data-width="100%" data-nowrap="true">
			<thead>
				<tr>
				    <th align="center">商户号</th>
					<th align="center">订单号</th>
					<th align="center">终端号</th>
					<th align="center">交易类型</th>
					<th align="center">网关</th>
					<th align="center">应答码</th>
					<th align="center">交易时间</th>
					<th align="center">卡类型</th>
					<th align="center">金额</th>
					<th align="center">机构号</th>
					<th align="center">交易状态</th>
					<th align="center">卡号</th>
					<th align="center">分润</th>
					<th align="center">手续费</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pageUser.list}" varStatus="status">
					<tr data-id="<c:out value="${record.ordId}"/>">
					    <td align="center"><c:out value="${record.instPosMerId}" /></td>
						<td align="center"><c:out value="${record.ordId}" /></td>
						<td align="center"><c:out value="${record.instPosTermId}" /></td>
						<td align="center">
							<c:if test="${record.transType == 'P'}">消费</c:if>		
							<c:if test="${record.transType == 'Q'}">消费撤销</c:if>			
							<c:if test="${record.transType == 'X'}">冲正</c:if>						
						</td>
						<td align="center">
							<c:if test="${record.gateId == 'U1'}">钱宝</c:if>		
							<c:if test="${record.gateId == 'U2'}">银嘉</c:if>			
							<c:if test="${record.gateId == 'U4'}">翰银</c:if>
						</td>
						<td align="center"><c:out value="${record.respCd}" /></td>
						<td align="center"><c:out value="${record.acctDate} ${record.sysTime}" /></td>
						<td align="center">
                        	<c:if test="${record.cardFlag == '1'}">借记卡</c:if>
                        	<c:if test="${record.cardFlag == '2'}">贷记卡</c:if>
                        	<c:if test="${record.cardFlag == '3'}">其他</c:if>
                        </td>						
						<td align="center"><c:out value="${record.ordAmt}" /></td>
						<td align="center"><c:out value="${record.acqInstIdCode}" /></td>
						<td align="center">
							<c:if test="${record.transStat == 'S'}">成功</c:if>	
							<c:if test="${record.transStat == 'F'}">失败</c:if>		
							<c:if test="${record.transStat == 'I'}">初始</c:if>									
						</td>
						<td align="center"><c:out value="${record.cardNo}" /></td>
						<td align="center"><c:out value="${record.profitAmt}" /></td>
						<td align="center"><c:out value="${record.feeAmt}" /></td>
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
			<span> 条，共 <c:out value="${pageUser.itemCount}" /> 条 选中交易</span>
            <span><c:out value="${pageUser.itemCount}"/> 条 交易总金额：</span>&nbsp;
            <span><c:out value="${pageUser.selectAmt}"/> 元</span>
			</span>
		</div>
		<div class="pagination-box" data-toggle="pagination"
			data-total="<c:out value="${pageUser.itemCount}"/>"
			data-page-size="<c:out value="${pageUser.pageSize}"/>"
			data-page-current="<c:out value="${pageUser.pageIndex}"/>"></div>
	</div>
</body>
</html>