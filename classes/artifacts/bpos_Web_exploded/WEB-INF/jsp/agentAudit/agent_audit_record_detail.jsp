<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>代理商审核</title>
</head>
<style>
    .v-error {
        border:1px solid red;
        color:red;
    }
</style>

<body>
<div class="bjui-pageContent" id="addAgentMer">


    <div style="margin:15px auto 0; width:96%;">

            <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">代理商信息</h3></div>
                <div class="panel-body">
                    <dl class="detail-list">
                        <dd class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label x110">代理商号:</label>
                                <input  type="text" name="memberId"  id="memberId" class="form-control input-nm"
                                       value="${tblAgentInfoDo.memberId}" size="20" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label x110">代理商名称:</label>
                                <input type="text"  id="agentName" name="agentName" class="form-control input-nm"
                                       value="${tblAgentInfoDo.agentName}" size="20" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label x110">代理商简称名称:</label>
                                <input type="text"  id="agentShortName" name="agentShortName" class="form-control input-nm"
                                       value="${tblAgentInfoDo.agentShortName}" size="20" readonly/>
                            </div>
                        </dd>

                        <dd class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label x110">注册公司名:</label>
                                <input type="text"  id="regName" name="regName" class="form-control input-nm"
                                       value="${tblAgentInfoDo.regName}" size="20" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label x110">注册公司号:</label>
                                <input  type="text" id="regCode" name="regCode" class="form-control input-nm"
                                       value="${tblAgentInfoDo.regCode}" size="20" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label x110">税务登记号:</label>
                                <input type="text"  id="taxCode" name="taxCode" class="form-control input-nm"
                                       value="${tblAgentInfoDo.taxCode}" size="20" readonly/>
                            </div>
                        </dd>


                        <dd class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label x110">法人代表:</label>
                                <input type="text" id="legalName" name="legalName" class="form-control input-nm"
                                       value="${tblAgentInfoDo.legalName}" size="20" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label x110">法人身份证:</label>
                                <input id="idno" name="idno" class="form-control input-nm"
                                       value="${tblAgentInfoDo.idno}" size="20" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label x110">联系人:</label>
                                <input type="text" id="contactName" name="contactName" class="form-control input-nm"
                                       value="${tblAgentInfoDo.contactName}" size="20" readonly/>
                            </div>
                        </dd>

                        <dd class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label x110">联系电话:</label>
                                <input  type="text" id="contactMobile" name="contactMobile" class="form-control input-nm"
                                       value="${tblAgentInfoDo.contactMobile}" size="20" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label x110">联系地址:</label>
                                <input type="text" id="contactAddr" name="contactAddr" class="form-control input-nm"
                                       value="${tblAgentInfoDo.contactAddr}" size="20" readonly/>
                            </div>

                        </dd>
                    </dl>
                </div>
            </div>

        </div>

        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">代理商费率信息</h3></div>
                <div class="panel-body">
                    <dl class="detail-list">
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">01借记卡费率（%）:</label>
                                <input type="text" name="fee01" id="fee01" value="${tblAgentInfoDo.fee01}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>
                            <div class="form-group col-md-5">
                                <label class="control-label x130">借记卡封顶（元）:</label>
                                <input type="text" name="fee01L" id="fee01L" value="${tblAgentInfoDo.fee01L}"
                                       readonly class="form-control input-nm" size="20"/>
                            </div>
                        </dd>
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">02贷记卡费率（%）:</label>
                                <input type="text" name="fee02" id="fee02" value="${tblAgentInfoDo.fee02}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>
                        </dd>

                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">03微信T0交易费率（%）:</label>
                                <input type="text" name="fee07" id="fee07" value="${tblAgentInfoDo.fee03}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>

                            <div class="form-group col-md-5">
                                <label class="control-label x130">04支付宝T0交易费率（%）:</label>
                                <input type="text" name="fee08" id="fee08" value="${tblAgentInfoDo.fee04}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>
                        </dd>

                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">05微信T1交易费率（%）:</label>
                                <input type="text" name="fee03" id="fee03" value="${tblAgentInfoDo.fee05}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>

                            <div class="form-group col-md-5">
                                <label class="control-label x130">06支付宝T1交易费率（%）:</label>
                                <input type="text" name="fee04" id="fee04" value="${tblAgentInfoDo.fee06}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>

                        </dd>
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">07 T0提现费率（%）:</label>
                                <input type="text" name="fee05" id="fee05" value="${tblAgentInfoDo.fee07}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>

                            <div class="form-group col-md-5">
                                <label class="control-label x130">08 T1提现费率（%）:</label>
                                <input type="text" name="fee06" id="fee06" value="${tblAgentInfoDo.fee08}" readonly
                                       class="form-control input-nm" size="20"/>
                            </div>
                        </dd>

                    </dl>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">审核备注</h3></div>
                <div class="panel-body">
                            <textarea id="remark" name="remark" disabled
                                      style="width:100%;height: 60px;"
                            >${tblAgentAuditRecordDo.remark}</textarea>
                </div>
            </div>
        </div>


    </div>
</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close" data-icon="close">关闭</button>
        </li>
    </ul>
</div>

<script>
    var errorFields = '${tblAgentAuditRecordDo.errorField}'.split(",");
    setTimeout(function(){
        for (var i = 0; i < errorFields.length; i++) {
            $("#" + errorFields[i]).addClass("v-error");
        }
    },100);


</script>
</body>

</html>
