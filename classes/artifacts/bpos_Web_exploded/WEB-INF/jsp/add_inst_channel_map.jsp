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
    <title>新增机构关联</title>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="instChannelMapForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/IM/add_map_for_inst_channel" method="post">
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">机构号：</label>
                <input type="text" value="<c:out value="${instChannelMapForm.instId}"/>" name="instId" size="15" data-rule="机构号:required;" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">机构商户号：</label>
                <input type="text" value="<c:out value="${instChannelMapForm.instMerId}"/>" name="instMerId" size="15" data-rule="机构商户号:required;" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">商户终端号：</label>
                <input type="text" value="<c:out value="${instChannelMapForm.instMerTermId}"/>" name="instMerTermId" size="15" data-rule="商户终端号:required;" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">渠道号：</label>
                <input type="text" value="<c:out value="${instChannelMapForm.channelId}"/>" name="channelId" size="15" data-rule="渠道号:required;" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">渠道商户号：</label>
                <input type="text" value="<c:out value="${instChannelMapForm.channelMerId}"/>" name="channelMerId" size="15" data-rule="渠道商户号:required;" />&nbsp;
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">渠道终端号：</label>
                <input type="text" value="<c:out value="${instChannelMapForm.channelMerTermId}"/>" name="channelMerTermId" size="15" data-rule="渠道终端号:required;" />&nbsp;
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
