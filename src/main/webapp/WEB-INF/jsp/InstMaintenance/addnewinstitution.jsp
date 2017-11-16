<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<title>新增机构</title>
</head>
<body>
	<div class="bjui-pageHeader"></div>
	<div class="bjui-pageContent tableContent">
		<form id="pagerForm" name="institutionForm" data-toggle="validate"
			novalidate="novalidate"
			action="${pageContext.request.contextPath}/IM/add_new_inst"
			method="post">
			<div class="bjui-searchBar">
				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">机构名称：</label> <input type="text"
						value="<c:out value="${institutionForm.instName}"/>"
						name="instName" size="15" data-rule="机构名称:required;"
						placeholder="请输入机构名称" />&nbsp;
				</div>
				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">机构类型：</label> <select
						name="instType" id="instType" data-rule="机构类型:required;"
						data-toggle="selectpicker">
						<option value="">请选择</option>
						<option value="0">收单机构</option>
						<option value="1">银行渠道</option>
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
					<button type="submit" class="btn-default" data-icon="save">新增</button>
					&nbsp;
				</div>
			</div>
		</form>
	</div>
	<div class="bjui-pageFooter" id="pageFooter">
		<div class="pages"></div>
	</div>
</body>
</html>
