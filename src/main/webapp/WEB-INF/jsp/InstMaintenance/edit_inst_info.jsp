<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>修改机构信息</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<div class="bjui-searchBar">
			<label>机构号：</label> <label><c:out
					value="${institutionForm.instCode}" /></label>&nbsp;
		</div>
	</div>
	<div class="bjui-pageContent tableContent">
		<form id="pagerForm" name="institutionForm" data-toggle="validate"
			novalidate="novalidate"
			action="${pageContext.request.contextPath}/IM/change_inst_info"
			method="post">
			<div class="bjui-searchBar">
				<input type="hidden" name="instId"
					value="${institutionForm.instCode}"> <input type="hidden"
					name="instCode" value="${institutionForm.instCode}">
				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">机构名称：</label> <input type="text"
						value="<c:out value="${institutionForm.instName}"/>"
						name="instName" size="15" data-rule="机构名称:required;" />&nbsp;
				</div>
				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">机构类型：</label> <select
						name="instType" id="instType" data-rule="机构类型:required;"
						data-toggle="selectpicker">
						<option value="">请选择</option>
						<option
							<c:if test="${institutionForm.instType=='0'}">selected="selected"</c:if>
							value="0">收单机构</option>
						<option
							<c:if test="${institutionForm.instType=='1'}">selected="selected"</c:if>
							value="1">银行渠道</option>
					</select>&nbsp;
				</div>
				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">邮箱：</label> <input type="text"
						value="<c:out value="${institutionForm.instEmail}"/>"
						name="instEmail" size="20" data-rule="邮箱:required;email"
						placeholder="请输入邮箱地址" />&nbsp;
				</div>
				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">联系方式：</label> <input type="text"
						value="<c:out value="${institutionForm.instTelphome}"/>"
						name="instTelphome" size="20" data-rule="联系方式:required;mobile"
						placeholder="请输入联系方式" />&nbsp;
				</div>
				<div class="form-group" style="margin: 20px 0 20px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn-default" data-icon="save">保存</button>
					&nbsp;
				</div>
			</div>
		</form>
	</div>
	<div class="bjui-pageFooter" id="pageFooter"></div>
</body>
</html>
