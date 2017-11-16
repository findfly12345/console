<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/2/8
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>终端编辑</title>
</head>
<body>
<div class="bjui-pageHeader">
</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="terminalForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/terminal/update_terminal_info" method="post">
        <div class="bjui-searchBar">
            <div style="margin:15px auto 0; width:96%;">
                <div class="row" style="padding: 0 8px;">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">终端详细信息</h3></div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="control-label x85">终端号:</label>
                                    <%--<span class="form-text">${terminalForm.memberId}</span>--%>
                                    <input class="form-text input-nm" size="20" type="text" name="termId" id="termId" value="${terminalForm.termId}" readonly="ture" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端名称:</label>
                                    <input class="form-text input-nm" size="20" type="text" name="termName" id="termName" maxlength="20" data-rule="终端名称:required" value="${terminalForm.termName}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端类型:</label>
                                    <%--<span class="form-text">${terminalForm.legalName}</span>--%>
                                    <select  id="termType" name="termType" data-toggle="selectpicker" data-width="200">
                                        <option value="0" <c:if test="${terminalForm.termType == '0'}">selected</c:if>>移动</option>
                                        <option value="1" <c:if test="${terminalForm.termType == '1'}">selected</c:if>>固定</option>
                                        <option value="2" <c:if test="${terminalForm.termType == '2'}">selected="selected"</c:if>>MPOS</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端状态:</label>
                                    <select  id="termStat" name="termStat" data-toggle="selectpicker" data-width="200">
                                        <option value="1" <c:if test="${terminalForm.termStat == '1'}">selected</c:if>>已开通</option>
                                        <option value="0" <c:if test="${terminalForm.termStat == '0'}">selected</c:if>>未开通</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端安装省:</label>
                                    <%--<span class="form-text">${terminalForm.idNo}</span>--%>
                                    <input class="form-text input-nm" size="20" type="text" name="termInstallProv" id="termInstallProv" maxlength="20" data-rule="终端安装省:required" value="${terminalForm.termInstallProv}"  >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端安装市:</label>
                                    <input class="form-text input-nm" size="20" type="text" name="termInstallCity" id="termInstallCity"  maxlength="20" data-rule="终端安装市:required" value="${terminalForm.termInstallCity}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端安装县:</label>
                                    <input class="form-text input-nm" size="20" type="text" name="termInstallCounty" id="termInstallCounty" maxlength="20" data-rule="终端安装县:required" value="${terminalForm.termInstallCounty}" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端安装地址:</label>
                                    <input class="form-text input-nm" size="20" type="text" name="termInstallAddress" id="termInstallAddress"  maxlength="100" data-rule="终端安装地址:required" value="${terminalForm.termInstallAddress}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">终端SN号:</label>
                                    <input class="form-text input-nm" size="20" type="text" name="termSn" id="termSn" maxlength="10"  data-rule="终端SN号:required" value="${terminalForm.termSn}" >
                                </div>


                            </div></div></div></div></div>

        </div>
    </form>
</div>
<div class="bjui-pageFooter" id="pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>&nbsp;
    </ul>
    <div class="pages">
    </div>
</div>

</div>

</body>
</html>
