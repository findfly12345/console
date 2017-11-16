<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加机构费率</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="instmccfee/add" novalidate="novalidate"
			modelAttribute="rout" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">机构号：</label>
						<form:input path="instId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="机构号:required;" data-rule-required="[/^\w{8}$/, '请填写8位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">费率类型：</label>
						<form:select  path="mccType" data-toggle="selectpicker">
							<form:option value="01">借记卡</form:option>
							<form:option value="02">贷记卡</form:option>
							<form:option value="03">微信T0</form:option>
							<form:option value="04">支付宝T0</form:option>
							<form:option value="05">微信T1</form:option>
							<form:option value="06">支付宝T1</form:option>
							<form:option value="07">T0提现</form:option>
							<form:option value="08">T1提现</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">费率公式：</label>
						<form:textarea path="calcMode" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="费率公式:required;"  data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">备注：</label>
						<form:textarea path="remark" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"/>
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
