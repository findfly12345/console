<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>代理商详情</title>
</head>
<body>
<div class="bjui-pageHeader">
</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="agentDetailForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/MER101/UpdateMerDetailInfo" method="post">
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
                                    <input class="form-text" type="text" name="memberId" id="memberId" value="${agentDetailForm.memberId}" disabled="ture" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">代理商名称:</label>
                                    <input class="form-text" type="text" name="agentName" id="agentName" value="${agentDetailForm.agentName}" disabled="ture">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">代理商简称:</label>
                                    <%--<span class="form-text">${agentDetailForm.legalName}</span>--%>
                                    <input class="form-text" type="text" name="agentShortName" id="agentShortName" value="${agentDetailForm.agentShortName}" disabled="ture" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">公司注册名:</label>
                                    <%--<span class="form-text"></span>--%>
                                    <input class="form-text" type="text" name="regName" id="regName" value="${agentDetailForm.regName}" disabled="ture" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">公司注册号:</label>
                                    <%--<span class="form-text">${agentDetailForm.idNo}</span>--%>
                                    <input class="form-text" type="text" name="regCode" id="regCode" value="${agentDetailForm.regCode}" disabled="ture" >
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">税务登记号:</label>
                                    <input class="form-text" type="text" name="taxCode" id="taxCode" value="${agentDetailForm.taxCode}" disabled="ture">
                                </div>
                                <%--<div class="form-group">--%>
                                <%--<label class="control-label x85">信用卡卡号:</label>--%>
                                <%--<span class="form-text">${detailVo.bankAcctId}</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group hidden">--%>
                                <%--<label class="control-label x85">工商注册地址:</label>--%>
                                <%--&lt;%&ndash;<span class="form-text">${agentDetailForm.bankAcctName}</span>&ndash;%&gt;--%>
                                <%--<input class="form-text" type="text" name="regAddr" id="regAddr" value="${agentDetailForm.regAddr}" disabled="ture" >--%>
                                <%--</div>--%>
                                <%--<div class="form-group  hidden">--%>
                                <%--<label class="control-label x85">公司性质:</label>--%>
                                <%--<input class="form-text" type="text" name="licType" id="licType" disabled="true"--%>
                                <%--<c:if test="${agentDetailForm.licType == '1'}">value="国有企业"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '2'}">value="集体企业"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '3'}">value="联营企业"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '4'}">value="股份合作制企业"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '5'}">value="私营企业"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '6'}">value="个体户"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '7'}">value="合伙企业"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '8'}">value="有限责任公司"</c:if>--%>
                                <%--<c:if test="${agentDetailForm.licType == '9'}">value="股份有限公司"</c:if>--%>
                                <%-->--%>
                                <%--</div>--%>
                                <%--<div class="form-group  hidden">--%>
                                <%--<label class="control-label x85">注册资本(万):</label>--%>
                                <%--<input class="form-text" type="text" name="licAmt" id="licAmt" value="${agentDetailForm.licAmt}" disabled="true">--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label x85">法人代表:</label>
                                    <input class="form-text" type="text" name="legalName" id="legalName" value="${agentDetailForm.legalName}" disabled="true">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">法人代表身份证号码:</label>
                                    <input class="form-text" type="text" name="idno" id="idno" value="${agentDetailForm.idno}" disabled="true">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">联系人:</label>
                                    <input class="form-text" type="text" name="contactName" id="contactName" value="${agentDetailForm.contactName}" disabled="true">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">联系电话:</label>
                                    <input class="form-text" type="text" name="contactMobile" id="contactMobile" value="${agentDetailForm.contactMobile}" disabled="true">
                                </div>
                                <div class="form-group">
                                    <label class="control-label x85">联系地址:</label>
                                    <input class="form-text" type="text" name="contactAddr" id="contactAddr" value="${agentDetailForm.contactAddr}" disabled="true">
                                </div>
                                <%--<div class="form-group  hidden">--%>
                                <%--<label class="control-label x85">联系邮箱:</label>--%>
                                <%--<input class="form-text" type="text" name="email" id="email" value="${agentDetailForm.email}" disabled="true">--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label x85">代理商状态:</label>
                                    <input class="form-text" type="text" name="memberStat" id="memberStat" value="${agentDetailForm.memberStat}" disabled="true">
                                </div>


                                <%--<div class="form-group">--%>
                                <%--<label class="control-label x85">代理商状态:</label>--%>
                                <%--<select class="form-unit" id="bankId" name="bankId">--%>
                                <%--<option value="ONSELECT">请选择</option>--%>
                                <%--<c:forEach var="record" items="${agentDetailForm.bankIdList}" varStatus="status">--%>
                                <%--<option value="${record.bankId}" <c:if test="${record.bankId == agentDetailForm.bankId}">selected</c:if> >${record.bankName}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>
                                <%--</div>--%>
                            </div></div></div></div></div>

        </div>
    </form>
</div>
<div class="bjui-pageFooter" id="pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>

</div>

</div>

</body>
</html>
