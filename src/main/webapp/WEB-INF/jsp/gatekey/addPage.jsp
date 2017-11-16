<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加渠道密钥</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="gatekey/add" novalidate="novalidate"
			modelAttribute="rout" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关号：</label>
						<form:input path="gateId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关号:required;" data-rule-required="[/^\w{2}$/, '请填写2位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">商户号：</label>
						<form:input path="posMerId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="商户号:required;" data-rule-required="[/^\w{1,15}$/, '请填写1~15位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">终端号：</label>
						<form:input path="posTermId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="终端号:required;" data-rule-required="[/^\w{1,8}$/, '请填写1~8位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">批次号：</label>
						<form:input path="batchId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="批次号:aaa;" data-rule-aaa="[/^\w{0,6}$/, '请填写0~6位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构号：</label>
						<form:input path="instId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="机构号:aaa;" data-rule-aaa="[/^\w{0,11}$/, '请填写0~11位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构名称：</label>
						<form:textarea path="instName" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="机构名称:aaa;" data-rule-aaa="[/^\w{0,60}$/, '请填写0~60位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">主密钥：</label>
						<form:textarea path="mainKey" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="主密钥:aaa;" data-rule-aaa="[/^\w{0,32}$/, '请填写0~32位字符']" data-tip="请输入" data-ok="可用" />
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
