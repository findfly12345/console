<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>代理商信息修改</title>
</head>
<body>
<div class="bjui-pageHeader">
</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="agentDetailForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/agtInfo/update_agent_detail_info" method="post">
        <div class="bjui-searchBar">
            <div style="margin:15px auto 0; width:96%;">
                <div class="row" style="padding: 0 8px;">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">代理商详细信息</h3></div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="control-label x85">代理商号:</label>
                                    <%--<span class="form-text">${agentDetailForm.memberId}</span>--%>
                                    <input class="form-text" type="text" name="memberId" id="memberId" value="${agentDetailForm.memberId}" readonly="ture" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">代理商名称:</label>
                                    <input class="form-text" type="text" name="agentName" id="agentName" maxlength="20" data-rule="代理商名称:required" value="${agentDetailForm.agentName}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">代理商简称:</label>
                                    <%--<span class="form-text">${agentDetailForm.legalName}</span>--%>
                                    <input class="form-text" type="text" name="agentShortName" id="agentShortName" maxlength="10" data-rule="代理商简称:required" value="${agentDetailForm.agentShortName}"  >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">公司注册名:</label>
                                    <%--<span class="form-text"></span>--%>
                                    <input class="form-text" type="text" name="regName" id="regName" maxlength="20" data-rule="公司注册名:required" value="${agentDetailForm.regName}"  >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">公司注册号:</label>
                                    <%--<span class="form-text">${agentDetailForm.idNo}</span>--%>
                                    <input class="form-text" type="text" name="regCode" id="regCode" maxlength="15" data-rule="公司注册号:required" value="${agentDetailForm.regCode}"  >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">税务登记号:</label>
                                    <input class="form-text" type="text" name="taxCode" id="taxCode"  maxlength="32" data-rule="税务登记号:required" value="${agentDetailForm.taxCode}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">法人代表:</label>
                                    <input class="form-text" type="text" name="legalName" id="legalName" maxlength="10" data-rule="法人代表:required" value="${agentDetailForm.legalName}" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">法人代表身份证号码:</label>
                                    <input class="form-text" type="text" name="idno" id="idno"  maxlength="30" data-rule="法人代表身份证号码:required" value="${agentDetailForm.idno}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">联系人:</label>
                                    <input class="form-text" type="text" name="contactName" id="contactName" maxlength="10"  data-rule="联系人:required" value="${agentDetailForm.contactName}" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">联系电话:</label>
                                    <input class="form-text" type="text" name="contactMobile" id="contactMobile"   maxlength="11"  data-rule="联系电话:required" value="${agentDetailForm.contactMobile}" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">联系地址:</label>
                                    <input class="form-text" type="text" name="contactAddr" id="contactAddr" maxlength="25"  data-rule="联系地址:required" value="${agentDetailForm.contactAddr}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">代理商状态:</label>
                                    <%--<input class="form-text" type="text" name="memberStat" id="memberStat" value="${agentDetailForm.memberStat}" readonly="true">--%>
                                    <select class="form-text" id="memberStat" name="memberStat" disabled="true">
                                        <option value="ALL">---请选择---</option>
                                        <option value="0" <c:if test="${agentDetailForm.memberStat == '0'}">selected</c:if>>已上线</option>
                                        <option value="1" <c:if test="${agentDetailForm.memberStat == '1'}">selected</c:if>>关闭</option>
                                        <option value="2" <c:if test="${agentDetailForm.memberStat == '2'}">selected</c:if>>暂停</option>
                                    </select>
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
