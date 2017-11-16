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
    <title>新增商户</title>
</head>
<body>
<div class="bjui-pageHeader">
</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="instMerKeyForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/IM/add_mer_for_inst" method="post">
        <div class="bjui-searchBar">
            <input type="hidden" name="instId" value="${instMerKeyForm.instId}">
            <%--<input type="hidden" name="instType" value="${instMerKeyForm.instType}">--%>
            <%--<input type="hidden" name="instName" value="${instMerKeyForm.instName}">--%>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">商户号：</label>
                <input type="text" value="<c:out value="${instMerKeyForm.instMerId}"/>" name="instMerId" size="15" data-rule="商户号:required" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">终端号：</label>
                <input type="text" value="<c:out value="${instMerKeyForm.instMerTermId}"/>" name="instMerTermId" size="15" data-rule="终端号:required" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85"></label>
                <button type="submit" class="btn-default" data-icon="save">新增</button>
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
