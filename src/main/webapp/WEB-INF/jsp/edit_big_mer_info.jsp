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
    <title>大商户信息</title>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="bigMerRouteForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/routeManage/editBigMeInfo" method="post">
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">商户号：</label>
                <input type="text" value="<c:out value="${bigMerRouteForm.bigMerId}"/>" name="bigMerId" size="15" data-rule="商户号:required;" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">商户名：</label>
                <input type="text" value="${bigMerRouteForm.bigMerName}" name="bigMerName" size="15" data-rule="商户名:required;" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">月金额上限：</label>
                <input type="text" value="<c:out value="${bigMerRouteForm.monthAmtLimit}"/>" name="funcFatherId" size="15" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">日金额上限：</label>
                <input type="text" value="<c:out value="${bigMerRouteForm.dayAmtLimit}"/>" name="funcDesc" size="15" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">窗口时间起：</label>
                <input type="text" value="<c:out value="${bigMerRouteForm.openTimeStart}"/>" name="funcRemark" size="15" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">窗口时间止：</label>
                <input type="text" value="<c:out value="${bigMerRouteForm.openTimeEnd}"/>" name="funcTag" size="15"  data-rule-maxlength="1"/>&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">状态：</label>
                <input type="text" value="<c:out value="${bigMerRouteForm.openFlag}"/>" name="funcTag" size="15"  data-rule-maxlength="1"/>&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">安全时间：</label>
                <input type="text" value="<c:out value="${bigMerRouteForm.safeTimeFlag}"/>" name="funcLevel" size="15" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85"></label>
                <button type="submit" class="btn-default" data-icon="save">保存</button>&nbsp;
            </div>

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
