<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/18
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>费率修改</title>
</head>
<body>
<div class="bjui-pageHeader">


</div>
<div class="bjui-pageContent tableContent">
  <form id="pageForm" name="rateDetailForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/update_sys_rate" method="post">

      <input type="hidden" name="rateName" value="${rateDetailForm.rateName}">
      <label>${rateDetailForm.rateDesc}</label>
      <input type="text"  name="rateValue" id="rateValue" value="${rateDetailForm.rateValue}" name="rateValue" size="20" />&nbsp;
      <button type="submit" class="btn-default" data-icon="save">保存</button>&nbsp;
  </form>

</div>
<div class="bjui-pageFooter" id="pageFooter">
  <div class="pages">
    <span>每页 </span>
  </div>
</div>

</div>

</body>
</html>
