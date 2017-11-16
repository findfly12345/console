<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加路由</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="instrout/add" novalidate="novalidate"
			modelAttribute="rout" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构号：</label>
						<form:input path="instCode" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="机构号:required;" data-rule-required="[/^\w{1,11}$/, '请填写1~11位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构商户号：</label>
						<form:input path="instMerId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="机构商户号:required;" data-rule-required="[/^\w{15}$/, '请填写15位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构终端号：</label>
						<form:input path="instMerTermId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="机构终端号:required;" data-rule-required="[/^\w{1,8}$/, '请填写1~8位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关号：</label>
						<form:input path="gateId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关号:required;" data-rule-required="[/^\w{2}$/, '请填写2位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关商户号：</label>
						<form:input path="bankMerId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关商户号:required;" data-rule-required="[/^\w{15}$/, '请填15位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关终端号：</label>
						<form:input path="bankTermId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关终端号:required;" data-rule-required="[/^\w{8}$/, '请填写8位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">状态：</label>
						<form:select  path="stat">
						<form:option value="Y">可用</form:option>
						<form:option value="N">不可用</form:option>
						</form:select>
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
