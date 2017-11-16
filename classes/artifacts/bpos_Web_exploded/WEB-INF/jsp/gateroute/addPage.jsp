<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加网关</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="gateroute/add" novalidate="novalidate"
			modelAttribute="route" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关号：</label>
						<form:input path="gateId" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关号:required;" data-rule-required="[/^\w{2}$/, '请填写2位字符']" data-tip="请输入网关号" data-ok="网关号可用" />
					</td>
				</tr>
				
				<tr>
					<td>
						<label class="control-label x85" for="j_custom_name">网关类型：</label>
               			<form:select path="gateType" data-toggle="selectpicker">
                   			 <form:option value="0001">POS</form:option>
                    		<form:option value="0002">二维码</form:option>
                		</form:select>
					</td>
				</tr>
				
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关描述：</label>
						<form:input path="gateDesp" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关描述:required" data-tip="请输入网关描述" data-ok="网关描述" />
					</td>
				</tr>
				
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关费率：</label>
						<form:input path="gateFee" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" aria-required="true"
							data-rule="网关费率:required" data-tip="请输入网关费率" data-ok="网关费率" />
					</td>
				</tr>
				
				<tr>
					<td><label class="control-label x85" for="j_custom_name">网关状态：</label>
						<form:select path="gateStat">
							<form:option value="Y">打开</form:option>
							<form:option value="N">关闭</form:option>
						</form:select></td>
					</td>
				</tr>
				
				<tr>
					<td><label class="control-label x85" for="j_custom_name">备注：</label>
						<form:input path="remark" class="form-control" type="text" size="15"
							name="custom.name" style="width: 150px;" />
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
