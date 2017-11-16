<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/2/9
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>选择商户</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merInfoForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/terminal/go_to_select_mer" method="post"
          method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${merInfoForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${merInfoForm.pageCurrent}">
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">商户名称</label>
                <input path="merName" class="form-control" size="15" />
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">商户号</label>
                <input path="merId" class="form-control" size="15" />
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <button type="submit" class="btn-default" data-icon="save">获取数据</button>&nbsp;
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent" id="selectMerInfo">
    <c:if test="${tblMerInfoDOS!=null}">
        <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
            <thead>
            <tr>
                <th align="center">商户号</th>
                <th align="center">商户名称</th>
                <th align="center">注册简称</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tblMerInfoDo" items="${tblMerInfoDOS.list}">
                <tr data-id="${tblMerInfoDo.merId},${tblMerInfoDo.merName}">
                    <td align="center">${tblMerInfoDo.merId}</td>
                    <td align="center">${tblMerInfoDo.merName}</td>
                    <td align="center">${tblMerInfoDo.regShortName}</td>
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
        var value = $.CurrentDialog.find('#bjui-selected').val();
        if(value != undefined && value != "" && value != null) {
            var arr  = value.split(',');
            $('#addMerInfo #merId').val(arr[0]);
            $('#addMerInfo #merName').val(arr[1]);
        }
        $('#closeSelectA').click();
    }
</script>
</body>
</html>
