<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>用户编辑</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="user/update" novalidate="novalidate"
			modelAttribute="user" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">用户名：</label>
						<form:input path="name" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="用户名:required;email" data-tip="请输入用户名" data-ok="用户名可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">密码：</label>
						<form:input path="passwd" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" data-rule="密码:required" data-tip="请输入密码" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">是否可用：</label>
						<form:select path="status" data-toggle="selectpicker">
							<form:option value="S">正常</form:option>
							<form:option value="F">冻结</form:option>
							<form:option value="D">删除</form:option>
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
