<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/29
  Time: 下午4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<form id="userForm" action="${pageContext.request.contextPath}/userManagement/changepwd" data-toggle="validate" method="post">
<div class="bjui-pageContent">
    <input type="hidden" id="userid" name="userid" value="${userForm.userid}">
    <input type="hidden" id="username" name="username" value="${userForm.username}">

    <hr>
    <div class="form-group">
      <label for="oldTellerPass" class="control-label x85">旧密码：</label>
      <input type="password" data-rule="required" name="oldpassword" id="oldpassword" value="${userForm.oldpassword}" placeholder="旧密码" size="20">
    </div>
    <div class="form-group" style="margin: 20px 0 20px; ">
      <label for="tellerPass" class="control-label x85">新密码：</label>
      <input type="password" data-rule="新密码:required" name="pass" id="pass" value="${userForm.pass}" placeholder="新密码" size="20">
    </div>
    <div class="form-group">
      <label for="secpassword" class="control-label x85">确认密码：</label>
      <input type="password" data-rule="required;match(pass)" name="" id="secpassword" placeholder="确认新密码" size="20">
    </div>

</div>
<div class="bjui-pageFooter">
  <ul>
    <li><button type="button" class="btn-close">取消</button></li>
    <li><button type="submit" class="btn-default">保存</button></li>
  </ul>
</div>
</form>

</body>
</html>
