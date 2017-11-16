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
    <title>系统费率维护</title>
        <script language="javascript">
          $(document).ready(function(){

          });


        </script>
</head>
<body>
<div class="bjui-pageHeader">

    <input type="hidden" name="pageSize" value="${rateDetailForm.pageSize}">
    <input type="hidden" name="pageCurrent" value="${rateDetailForm.pageCurrent}">
    <input type="hidden" name="pagination" value="${rateDetailForm.pagination}">


</div>
<div class="bjui-pageContent tableContent">
<form id="pagerForm"  name="rateDetailForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/RM/query_sys_rate" method="post">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tabledit" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">描述</th>
      <th align="center">配置值</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${rateDetailForm.pagination.list}" varStatus="status">
<tr data-id="<c:out value="${record.kekName}"/>">

      <td align="center"><c:out value="${record.comm}"/></td>
      <!-- <td align="center"><input type="text" class="<c:out value="${record.kekName}"/>" name="<c:out value="${record.kekName}"/>" size="10" value="${record.keyValue}"  class="form-control ok" aria-required="true"></td> -->
      <td align="center"><c:out value="${record.keyValue}"/></td>
      <td align="center">
        <button data-toggle="doedit" class="btn btn-green" type="button">编辑</button>
         <a href="${pageContext.request.contextPath}/update_sys_rate?id=<c:out value="${record.kekName}"/>&value=" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定保存更改的费率吗吗？");">保存</a>
      </td>
    </tr>
  </c:forEach>
    </tbody>
  </table>
</form>
</div>
<div class="bjui-pageFooter" id="pageFooter">
<!-- <ul>
    <li>
        <button class="btn btn-default" data-icon="save" type="submit">
             <i class="fa fa-save"></i>
                 全部保存
        </button>
    </li>
</ul> -->
</div>

</div>

</body>
</html>
