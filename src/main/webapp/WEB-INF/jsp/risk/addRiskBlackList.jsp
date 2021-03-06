<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加黑名单商户</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="riskblacklist/add" novalidate="novalidate"
			modelAttribute="rout" id="riskBlackListForm">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">卡号：</label>
						<form:input path="priCardNo" class="form-control" type="text"
							size="15" name="custom.name"
							style="width: 150px;" aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构号：</label>
						<form:input path="instCode" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">商户号：</label>
						<form:input path="merchId" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">终端号：</label>
						<form:input path="termCode" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">触发规则：</label>
						<form:input path="errRule" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">状态：</label>
						<form:select path="riskFlag">
							<form:option value="Y">可用</form:option>
							<form:option value="N">不可用</form:option>
						</form:select>
					</td>
				</tr>
	
				<tr>
					<td><label class="control-label x85" for="j_custom_name">备注：：</label>
						<form:input path="remark" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div class="bjui-pageFooter">
		<ul>
			<li><button type="button" class="btn-close">关闭</button></li>
			<li><button type="submit" class="btn-default">保存</button></li>
		</ul>
	</div>
</body>
</html>
