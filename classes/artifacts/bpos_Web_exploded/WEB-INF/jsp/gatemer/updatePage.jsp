<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>渠道大商户编辑</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="gatemer/update" novalidate="novalidate"
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
					<td><label class="control-label x85" for="j_custom_name">商户类型：</label>
					<form:select  path="posMerType" data-toggle="selectpicker">
						<form:option value="1">餐娱类</form:option>
						<form:option value="2">一般类</form:option>
						<form:option value="3">民生类</form:option>
						<form:option value="4">批发类</form:option>
						<form:option value="5">房车类</form:option>
						<form:option value="6">公益类</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">商户描述：</label>
						<form:input path="posMerDesc" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="商户描述:required;" data-rule-required="[/^\w{1,80}$/, '请填写1~80位字符']" data-tip="请输入" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">商户状态：</label>
						<form:select  path="posMerStat" data-toggle="selectpicker">
						<form:option value="Y">可用</form:option>
						<form:option value="N">不可用</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">省份：</label>
						<form:input path="posMerProv" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="省份:aaa;" data-rule-aaa="[/^\w{0,100}$/, '请填写0~100位字符']" data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">地区：</label>
						<form:input path="posMerCity" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="地区:aaa;" data-rule-aaa="[/^\w{0,100}$/, '请填写0~100位字符']"  data-ok="可用" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">备注：</label>
						<form:textarea path="posMerRemark" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="备注:aaa;" data-rule-aaa="[/^\w{0,200}$/, '请填写0~200位字符']" data-ok="可用" />
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
