<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加机构秘钥</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="instkey/add" novalidate="novalidate"
			modelAttribute="instkey" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构号：</label>
						<form:input path="instCode" class="form-control" type="text"
							size="15" name="custom.name"
							style="width: 150px;" aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">主秘钥：：</label>
						<form:input path="instMainKey" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">PIN秘钥：</label>
						<form:input path="instPinKey" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">MAC秘钥：</label>
						<form:input path="instMacKey" type="text"
							class="form-control" size="15"/></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">磁道秘钥：</label>
						<form:input path="instTdKey" type="text" 
							class="form-control" size="15"/></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">签到状态：</label>
						<form:select path="loginStat">
							<form:option value="Y">已签到</form:option>
							<form:option value="N">未签到</form:option>
						</form:select></td>
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