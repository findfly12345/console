<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>修改机构交易权限</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="insttransauth/alterUpdateInstTransAuth"
			novalidate="novalidate" modelAttribute="insttransauth" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构号：</label>
						<form:input path="instCode" class="form-control" type="text"
							size="15" name="custom.name"
							style="width: 150px;" aria-required="true" readonly="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">POS交易：</label>
						<form:select path="posStat">
							<form:option value="Y">已开通</form:option>
							<form:option value="N">未开通</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">微信：</label>
						<form:select path="chatStat">
							<form:option value="Y">已开通</form:option>
							<form:option value="N">未开通</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">支付宝：</label>
						<form:select path="allipayStat">
							<form:option value="Y">已开通</form:option>
							<form:option value="N">未开通</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">权限控制：</label>
						<form:input path="authStat" type="text" 
							class="form-control" size="15" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">备注：</label>
						<form:input path="remark" type="text" 
							class="form-control" size="15" /></td>
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