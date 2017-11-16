<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/18
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>商户用户绑定</title>
</head>
<body>
	<div class="bjui-pageHeader"></div>
	<div class="bjui-pageContent tableContent">
		<form id="pagerForm" name="userManageForm" data-toggle="validate"
			novalidate="novalidate"
			action="${pageContext.request.contextPath}/userManagement/goBind"
			method="post">
			<div class="bjui-searchBar">
				<div class="form-group" style="margin: 30px 0 30px;">
					<label class="control-label x85">账            号：</label>
					<input type="text" value="${userManageForm.bindUser}" readonly="true" name="bindUser" id="bindUser" size="15"/>&nbsp;
				</div>

				<div class="form-group" style="margin: 20px 0 20px;">
					<label class="control-label x85">关联商户：</label> 
				</div>

				<div class="form-group" style="margin: 20px 0 20px;" id="merArea">
					<label class="control-label x85">机构</label> 
					<input type="text" name="instId" size="15" value="${userManageForm.instId}"/>&nbsp; 
					<label class="control-label x85">代理商</label>
					<input type="text" name="agentId" size="15" value="${userManageForm.agentId}"/>&nbsp; 
					<label class="control-label x85">商户</label> 
					 <input type="text" name="merId" size="15" value="${userManageForm.merId}" />&nbsp;
				</div>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn-default" id= "goBind" data-icon="save">绑定</button>
				&nbsp;
			</div>
		</form>
	</div>
	<div class="bjui-pageFooter" id="pageFooter">
		<div class="pages"></div>
	</div>

	</div>
</body>

<script type="text/javascript">


</script>

</html>
