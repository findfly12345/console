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
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <title>渠道信息修改</title>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="channelForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/CM/save_channel_info" method="post">
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">网关号：</label>
                <input type="text" value="<c:out value="${channelForm.gateId}"/>" name="gateId" size="15" <c:if test="${channelForm.gateId!=''&& channelForm.gateId!=null}">readonly</c:if> data-rule="网关号:required;" maxlength="2" placeholder="请输入2位网关号"/>&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">商户号：</label>
                <input type="text" value="<c:out value="${channelForm.posMerId}"/>" name="posMerId" size="15" <c:if test="${channelForm.posMerId!=''&& channelForm.posMerId!=null}">readonly</c:if> data-rule="商户号:required;" maxlength="15"  placeholder="请输入商户号" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">终端号：</label>
                <input type="text" value="<c:out value="${channelForm.posTermId}"/>" name="posTermId" size="15" <c:if test="${channelForm.posTermId!=''&& channelForm.posTermId!=null}">readonly</c:if> data-rule="终端号:required;" maxlength="8"  placeholder="请输入终端号" />&nbsp;
            </div>

            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">批次号：</label>
                <input type="text" value="<c:out value="${channelForm.batchId}"/>" name="batchId" size="15" data-rule="批次号:required;" maxlength="6"  placeholder="请输入批次号" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">机构号：</label>
                <input type="text" value="<c:out value="${channelForm.instId}"/>" name="instId" size="15" data-rule="机构号:required;" maxlength="11"  placeholder="请输入机构号" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">机构名称：</label>
                <input type="text" value="<c:out value="${channelForm.instName}"/>" name="instName" size="15" data-rule="机构名称:required;" maxlength="60"  placeholder="请输入机构名称" />&nbsp;
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
