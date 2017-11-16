<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>增加风控时间</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="risktime/add" novalidate="novalidate"
			modelAttribute="risktime" id="useraddfrom">
			<table class="table table-condensed table-hover" width="100%">
				<tr>
					<td><label class="control-label x85" for="j_custom_name">风控编号：</label>
						<form:input path="riskSeq" class="form-control" type="text"
							size="15" name="custom.name"
							style="width: 150px;" aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">风控类型：：</label>
						<form:input path="riskType" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">风控描述：</label>
						<form:input path="riskDetail" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">开始时间：</label>
						<form:input path="beginTime" type="text"
							class="form-control" size="15" data-pattern="HHMMSS" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">结束时间：</label>
						<form:input path="endTime" type="text" 
							class="form-control" size="15" data-pattern="HHMMSS" /></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">状态：</label>
						<form:select path="stat">
							<form:option value="Y">可用</form:option>
							<form:option value="N">不可用</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td><label class="control-label x85" for="j_custom_name">备注：：</label>
						<form:input path="riskRemark" class="form-control" type="text"
							size="15" name="custom.name" style="width: 150px;"
							aria-required="true" /></td>
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
