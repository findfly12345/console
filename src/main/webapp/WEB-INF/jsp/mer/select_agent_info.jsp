<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>选择代理商</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form:form id="pagerForm" name="tblAgentInfoDo" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/mer/go_to_select_agent_mer" method="post"
               modelAttribute="tblAgentInfoDo">
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">代理商名称</label>
                <form:input path="agentName" class="form-control" size="15" />
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">代理商简称</label>
                <form:input path="agentShortName" class="form-control" size="15" />
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <button type="submit" class="btn-default" data-icon="save">获取数据</button>&nbsp;
            </div>
        </div>
    </form:form>
</div>
<div class="bjui-pageContent tableContent">
    <c:if test="${tblAgentInfoDoList!=null}">
        <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
            <thead>
            <tr>
                <th align="center">代理商户号</th>
                <th align="center">代理商名称</th>
                <th align="center">代理商简称</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tblAgentInfoDo" items="${tblAgentInfoDoList}">
                <tr data-id="${tblAgentInfoDo.memberId},${tblAgentInfoDo.agentName}">
                    <td align="center">${tblAgentInfoDo.memberId}</td>
                    <td align="center">${tblAgentInfoDo.agentName}</td>
                    <td align="center">${tblAgentInfoDo.agentShortName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" id="closeSelectA">关闭</button></li>
        <li><button type="button"  onclick="close_dialog();" data-url="/select_agent_mer?id={#bjui-selected}"
                    data-on-close="doc_dialog_onClose" class="btn-default">选择</button></li>
    </ul>
</div>
<script>
    function close_dialog() {
        var id = $.CurrentDialog.find('#bjui-selected').val();
        if(id != undefined && id != "" && id != null) {
            var arr  = id.split(',');
            $('#addAgentMer #memberId').val(arr[0]);
            $('#addAgentMer #agentName').val(arr[1]);
        }
        $('#closeSelectA').click();
    }
</script>
</body>
</html>
