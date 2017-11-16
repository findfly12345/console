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
    <title>添加新的用户</title>
</head>
<body>
<div class="bjui-pageHeader">

    <label>机构号：</label>
    <label  name="instCode" id="instCode" value="${instUserForm.instCode}"  size="15">${instUserForm.instCode}</label>&nbsp;
    <label>机构名：</label>
    <label  name="instName" id="instName" value="${instUserForm.instName}"  size="15">${instUserForm.instName}</label>&nbsp;
    <label>机构类型：</label>
    <label  name="instType" id="instType" value="${instUserForm.instType}"  size="15">${instUserForm.instType}</label>&nbsp;

</div>
<div class="bjui-pageContent tableContent">
 <form id="pagerForm" name="instUserForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/IM/add_new_inst_user" method="post">
    <div class="bjui-searchBar">
      <input type="hidden" name="instCode" value="${instUserForm.instCode}">
      <div class="form-group" style="margin: 20px 0 20px; ">
      <label class="control-label x85">操作员号码：</label>
      <input type="text" value="<c:out value="${instUserForm.usrName}"/>" name="usrName" size="15" maxlength="3" data-rule="操作员号码:required" placeholder="请输入三位操作员编号"/>&nbsp;
      </div>
       <div class="form-group" style="margin: 20px 0 20px; ">
      <label class="control-label x85">操作员类型：</label>
           <select name="usrType" id="usrType" data-rule="操作员类型:required;" data-toggle="selectpicker">
               <option value="">请选择</option>
               <option  value="机构操作员">机构操作员</option>
               <%--<option  value="1">渠道</option>--%>
           </select>&nbsp;
      <%--<input type="text" value="<c:out value="${instUserForm.usrType}"/>" name="usrType"  size="15" data-rule="操作员类型:required" />&nbsp;--%>
      </div>
       <div class="form-group" style="margin: 20px 0 20px; ">
      <label class="control-label x85">操作员名称：</label>
      <input type="text" value="<c:out value="${instUserForm.usrRemark}"/>" name="usrRemark" size="15" data-rule="操作员名称:required">
      </div>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn-default" data-icon="save">新增</button>&nbsp;
    </div>
  </form>
</div>
<div class="bjui-pageFooter" id="pageFooter">
  <div class="pages">
  </div>
</div>

</div>

</body>
</html>
