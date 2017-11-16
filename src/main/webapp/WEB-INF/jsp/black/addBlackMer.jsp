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
			   data-toggle="validate" action="blackmer/add" novalidate="novalidate"
			   modelAttribute="tblBlackAccDo" id="useraddfrom">
		<table class="table table-condensed table-hover" width="100%">
			<tr>
				<td><label class="control-label x85" for="j_custom_name">商户号：</label>
					<form:input path="merId" class="form-control" type="text"
								size="15" name="custom.merId"
								style="width: 150px;" aria-required="true" /></td>
			</tr>
			<tr>
				<td><label class="control-label x85" for="j_custom_name">结算卡号：</label>
					<form:input path="settlementCard" class="form-control" type="text"
								size="15" name="custom.settlementCard"
								style="width: 150px;" aria-required="true" /></td>
			</tr>
			<tr>
				<td><label class="control-label x85" for="j_custom_name">银行卡号：</label>
					<form:input path="bankCard" class="form-control" type="text"
								size="15" name="custom.bankCard"
								style="width: 150px;" aria-required="true" /></td>
			</tr>
			<tr>

				<td><label class="control-label x85" for="j_custom_name">备注：</label>
					<form:input path="remark" class="form-control" type="text"
								size="15" name="custom.remark"
								style="width: 300px;" aria-required="true" /></td>
			</tr>
			<tr>
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
