<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 17/01/18
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加代理商</title>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="agentDetailForm" data-toggle="validate" novalidate="novalidate"
          action="${pageContext.request.contextPath}/agtInfo/add_new_agent" method="post">
        <div class="bjui-searchBar">
            <div style="margin:15px auto 0; width:96%;">
                <div class="row" style="padding: 0 8px;">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">代理商详细信息</h3></div>
                            <div class="panel-body">
                                <dl class="detail-list" >
                                    <dd class="row">
                                        <div class="form-group col-md-4">
                                            <label class="control-label x120">代理商名称:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="agentName" id="agentName" maxlength="20"
                                                   data-rule="代理商名称:required" value="${agentDetailForm.agentName}">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label class="control-label x120">代理商简称:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="agentShortName" id="agentShortName"
                                                   maxlength="10" data-rule="代理商简称:required"
                                                   value="${agentDetailForm.agentShortName}">
                                        </div>
                                    </dd>
                                    <dd class="row">
                                        <div class="form-group  col-md-4">
                                            <label class="control-label x120">公司注册名:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="regName" id="regName" maxlength="20"
                                                   data-rule="公司注册名:required" value="${agentDetailForm.regName}">
                                        </div>
                                        <div class="form-group  col-md-4">
                                            <label class="control-label x120">公司注册号:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="regCode" id="regCode" maxlength="15"
                                                   data-rule="公司注册号:required" value="${agentDetailForm.regCode}">
                                        </div>
                                    </dd>
                                    <dd class="row">
                                        <div class="form-group  col-md-4">
                                            <label class="control-label x120">税务登记号:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="taxCode" id="taxCode" maxlength="32"
                                                   data-rule="税务登记号:required" value="${agentDetailForm.taxCode}">
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label class="control-label x120">法人代表:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="legalName" id="legalName" maxlength="10"
                                                   data-rule="法人代表:required" value="${agentDetailForm.legalName}">
                                        </div>
                                    </dd>
                                    <dd class="row">
                                        <div class="form-group col-md-4">
                                            <label class="control-label x120">法人代表身份证号码:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="idno" id="idno" maxlength="30"
                                                   data-rule="法人代表身份证号码:required ID_card" value="${agentDetailForm.idno}">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label class="control-label x120">联系人:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="contactName" id="contactName"
                                                   maxlength="10" data-rule="联系人:required"
                                                   value="${agentDetailForm.contactName}">
                                        </div>
                                    </dd>
                                    <dd class="row">
                                        <div class="form-group col-md-4">
                                            <label class="control-label x120">联系电话:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="contactMobile" id="contactMobile"
                                                   maxlength="11" data-rule="联系电话:required mobile"
                                                   value="${agentDetailForm.contactMobile}">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label class="control-label x120">联系地址:</label>
                                            <input class="form-control input-nm" size="20" type="text" name="contactAddr" id="contactAddr"
                                                   maxlength="50" data-rule="联系地址:required"
                                                   value="${agentDetailForm.contactAddr}">
                                        </div>
                                    </dd>
                                </dl>
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
            <button type="button" class="btn-close" data-icon="close">关闭</button>
        </li>
        <li>
            <button type="submit" class="btn-default" data-icon="save">保存</button>
        </li>
        &nbsp;
    </ul>
    <div class="pages">
    </div>
</div>

</div>

</body>
</html>
