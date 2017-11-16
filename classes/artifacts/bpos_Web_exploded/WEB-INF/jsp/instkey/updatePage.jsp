<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>机构密钥编辑</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="instkey/update" novalidate="novalidate"
			modelAttribute="rout" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
					<tr>
					<td><label class="control-label x85" for="j_custom_name">机构号：</label>
						<form:input path="instId" class="form-control" type="text" size="15" readonly="true"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="机构号:required;" data-rule-required="[/^\w{8}$/, '请填写8位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">主密钥：</label>
						<form:textarea path="instPrimaryKey" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="主密钥:required;" data-rule-required="[/^\w{1,32}$/, '请填写1~32位字符']" data-tip="请输入" data-ok="可用" />
					</td>
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
