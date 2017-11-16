<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="instBatchResultForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/mer/inst_batch_result_history" method="post" modelAttribute="instBatchResultForm">
			<form:input type="hidden" path="pageSize" />
			<form:input type="hidden" path="pageCurrent" />
			<label>机构号：</label>
			<select path="instId" data-toggle="selectpicker" id="instCode" onchange="selectBatch()">
				<option value="">请选择</option>
				<c:forEach items="${tblBtsInstDOList}" var="tblBtsInstDO">
					<option value="${tblBtsInstDO.instCode}">${tblBtsInstDO.instName}</option>
				</c:forEach>
			</select>	
			
			<div class="form-group" style="margin: 15px 0 15px; ">
				<label>机构商户号:</label>
				<form:input path="instMerId" class="form-control" size="15" />
			</div>
			
			<div class="form-group" style="margin: 15px 0 15px; ">
				<label>操作员:</label>
				<form:input path="operUser" class="form-control" size="15" />
			</div>
			
			<div class="form-group" style="margin: 15px 0 15px;">
				<label>进件日期:</label>
				<form:input path="batchDate" type="text"
					data-toggle="datepicker" value="${instBatchResultForm.batchDate}"
					class="form-control" size="15" data-pattern="yyyyMMdd" />
					
			   <button type="button" id="batchSel" class="btn btn-info" data-icon="info" data-toggle="dialog" 
			       data-width="500" data-height="550" data-id="dialog-batch-sel" data-mask="true" data-title="选择进件批次" 
                   href="${pageContext.request.contextPath}/mer/select_inst_batch_no?instCode=$('#instCode').val()">选择进件批次</button>
               <input  id="batchNo" name="batchNo" class="form-control input-nm" value="${tblAgentInfoDo.memberId}" readonly size="20" />
            </div>
             <div class="form-group" style="margin: 15px 0 15px;">
				<button type="submit" class="btn-default" data-icon="search">查询</button>
				<button type="button" class="btn-default" data-icon="home"
					onclick="prtReport();">导出</button>
				&nbsp;
			</div>
		</form:form>
	</div>



	<div class="bjui-pageContent tableContent">
		<table
			class="table table-bordered table-hover table-striped table-top"
			data-toggle="tablefixed" data-nowrap="true">
			<c:if test="${resultList!=null}">
				<thead>
					<tr>
						<th>批次号</th>
						<th>机构号</th>
						<th>机构商户号</th>
						<th>机构终端号</th>
						<th>平台商户号</th>
						<th>平台终端号</th>
						<th>处理结果</th>
						<th>详细描述</th>
						<th>操作员</th>
						<th>更新时间</th>
						<th>创建时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="result">
						<tr>
							<td>${result.batchNo}</td>
							<td>${result.instId}</td>
							<td>${result.instMerId}</td>
							<td>${result.instTermId}</td>
							<td>${result.merId}</td>
							<td>${result.termId}</td>
							<td>${result.resultFlag}</td>
							<td>${result.resultDesc}</td>
							<td>${result.userName}</td>
							<td>${result.updateTime}</td>
							<td>${result.createTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>

		</table>
	</div>

</body>

<script type="text/javascript">

var instIdForm = $("#instCode").val();
var instMerIdForm = $("#instMerId").val();

alert("instIdForm=" + instIdForm);

function selectBatch() {
	var instIdForm = $("#instCode").val();
	alert("2");
	
	var aaa = document.getElementById("instCode").value;
	
	alert("4+" + $("#instCode").val());
	
	alert("5+" + aaa);
	
	$("#instCode").change("7");
	
	alert("7+" + $("#instCode").val());
	
	var bbb = "{instBatchResultForm.instCode}";
	alert("8+" + bbb);
	
}


function batchNoUrl () {
	
	
	
	
}

</script>

</html>