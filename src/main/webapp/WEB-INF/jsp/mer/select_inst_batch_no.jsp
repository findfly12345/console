<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>选择MCC信息</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form:form id="pagerForm" name="instBatchResultForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/mer/go_to_select_mer_mcc" method="post"
               modelAttribute="instBatchResultForm">
        <div class="bjui-searchBar">
            <input type="hidden" id="pageSize" name="pageSize" value="${tblMerCoreMccDo.pageSize}">
            <input type="hidden" id="pageCurrent" name="pageCurrent" value="${tblMerCoreMccDo.pageCurrent}">

            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x65">MCC描述</label>
                <form:input path="mccDesp" class="form-control" size="20" />
            </div>



            <div class="form-group" style="margin: 20px 0 20px; ">
                <button type="submit" class="btn-default" data-icon="save">获取数据</button>&nbsp;
            </div>
        </div>
    </form:form>
</div>
<div class="bjui-pageContent tableContent">
    <c:if test="${tblMerCoreMccDo.pagination.list!=null}">
        <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
            <thead>
            <tr>
                <th align="center">MCC码</th>
                <th align="center">MCC描述</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tblSubbranchInfoDo" items="${tblMerCoreMccDo.pagination.list}">
                <tr data-id="${tblSubbranchInfoDo.mccValue},${tblSubbranchInfoDo.mccDesp}">
                    <td align="center">${tblSubbranchInfoDo.mccValue}</td>
                    <td align="center">${tblSubbranchInfoDo.mccDesp}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>每页 </span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="10">10</option>
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="100">100</option>
            </select>
        </div>
        <span> 条，共 <c:out value="${tblMerCoreMccDo.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${tblMerCoreMccDo.pagination.itemCount}"/>" data-page-size="<c:out value="${tblMerCoreMccDo.pageSize}"/>" data-page-current="<c:out value="${tblMerCoreMccDo.pageCurrent}"/>">
    </div>
    <ul>
        <li><button type="button" class="btn-close" id="closeSelectA">关闭</button></li>
        <li><button type="button"  onclick="close_dialog();" data-url="/select_agent_mer?id={#bjui-selected}"
                    data-on-close="doc_dialog_onClose" class="btn-default">选择</button></li>
    </ul>
</div>
<script>
    function close_dialog() {
        var value = $.CurrentDialog.find('#bjui-selected').val();
        if(value != undefined && value != "" && value != null) {
            console.log(value);
            var arr = value.split(',');
            $('#addAgentMer #mccValue').val(arr[0]);
            $('#addAgentMer #mccDesp').val(arr[1]);
        }
        $('#closeSelectA').click();
    }
</script>
</body>
</html>
