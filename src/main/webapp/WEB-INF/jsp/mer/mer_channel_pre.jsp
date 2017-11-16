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
    <title>商户渠道报备</title>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="merInfoForm" data-toggle="doajax"
          action="${pageContext.request.contextPath}/mer/mer_channel_pre" method="post">
        <div class="bjui-searchBar">
            <div style="margin:15px auto 0; width:96%;">
                <div class="row" style="padding: 0 8px;">
                    <div class="col-md-12">
                        <div class="panel panel-default">

                            <div class="panel-body">

                                <input type="hidden" name="merId" id="merId" value="${merInfoForm.merId}">
                                <input type="hidden" name="merName" id="merName" value="${merInfoForm.merName}">
                                <input type="hidden" name="instId" id="instId" value="${merInfoForm.instId}">
                                <input type="hidden" name="instMerId" id="instMerId" value="${merInfoForm.instMerId}">
                                <input type="hidden" name="instTermId" id="instTermId" value="${merInfoForm.instTermId}">
                                <input type="hidden" name="startTime" id="startTime" value="${merInfoForm.startTime}">
                                <input type="hidden" name="endTime" id="endTime" value="${merInfoForm.endTime}">

                                <div>
                                    <label class="control-label x85">报备渠道：</label>
                                    <select name="channelId" id="channelId" data-rule="报备渠道:required;"
                                            data-toggle="selectpicker" size="14">
                                        <option value="">请选择</option>
                                        <option value="U1">钱宝</option>

                                    </select>&nbsp;</div>
                                </br>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </form>
</div>
<div class="bjui-pageFooter" id="pageFooter">
    <ul>
        <li>
            <button type="submit" class="btn-default">导出报备文件</button>
        </li>
    </ul>
    <div class="pages">
    </div>
</div>

</div>

</body>
</html>
