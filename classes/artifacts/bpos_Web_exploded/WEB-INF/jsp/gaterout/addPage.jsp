<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加渠道</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="gaterout/add" novalidate="novalidate"
			modelAttribute="rout" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关号：</label>
						<form:input path="gateId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关号:required;" data-rule-required="[/^\w{2}$/, '请填写2位字符']" data-tip="请输入网关号" data-ok="网关号可用" />
					</td>
				</tr>
				
				<tr>
					<td><label class="control-label x85" for="j_custom_name">IP地址：</label>
						<form:input path="lineIp" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="IP:required" data-tip="请输入IP" data-ok="IP可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">端口号：</label>
						<form:input path="linePort" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="端口:required;" data-rule-required="[/^\d{1,5}$/, '请填写1-5位数字']" data-tip="请输入端口" data-ok="端口可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">名称：</label>
						<form:input path="lineName" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="名称:required" data-tip="请输入名称" data-ok="名称可用" />
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
