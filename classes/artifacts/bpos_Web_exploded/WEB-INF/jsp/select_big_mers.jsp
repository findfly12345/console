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
    <title>选取对应大商户</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merRouteForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/routeManage/select_big_mers" method="post">
        <input type="hidden" name="pageSize" value="${merRouteForm.pageSize}">
        <input type="hidden" name="pageCurrent" value="${merRouteForm.pageCurrent}">


        <%--<datalist id="refsBigMers" name="refsBigMers" value="${merRouteForm.refsBigMers}"></datalist>--%>
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">终端商户号：</label>
                <input type="text" name="merId" id="merId" value="${merRouteForm.merId}"  size="15"/>&nbsp;
                <label class="control-label x85">第</label>
                <input type="text" name="currentTradeCount" id="currentTradeCount" value="${merRouteForm.currentTradeCount}"  size="3">&nbsp;
                <label>笔</label>
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">大商户号：</label>
                <textarea class ="form-control autosize" rows="1" cols="60" data-toggle="autoheight" id="refsBigMer" name="refsBigMer"  >${merRouteForm.refsBigMer}</textarea><br>
                <label class="control-label x85"></label>
                <label class="control-label x85">*用逗号分隔</label>
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85"></label>
                <button type="submit" class="btn-default" data-icon="save">保存</button>&nbsp;
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">


</div>
<div class="bjui-pageFooter" id="pageFooter">
    <div class="pages">

    </div>

</div>

</body>
</html>
