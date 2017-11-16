<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>代理商审核</title>
</head>
<style>
    .v-error {
        border: 1px solid red;
        color: red;
    }
</style>

<script type="text/javascript">
    var img = document.getElementById('img');
    function bigger(){
        img.style.width = '400px';
        img.style.height = '400px';
    }

    function smaller(){
        img.style.width = '100px';
        img.style.height = '100px';
    }

    var img1 = document.getElementById('img1');
    function bigger1(){
        img1.style.width = '400px';
        img1.style.height = '400px';
    }

    function smaller1(){
        img1.style.width = '100px';
        img1.style.height = '100px';
    }

    var img2 = document.getElementById('img2');
    function bigger2(){
        img2.style.width = '400px';
        img2.style.height = '400px';
    }

    function smaller2(){
        img2.style.width = '100px';
        img2.style.height = '100px';
    }

    var img3 = document.getElementById('img3');
    function bigger3(){
        img3.style.width = '400px';
        img3.style.height = '400px';
    }

    function smaller3(){
        img3.style.width = '100px';
        img3.style.height = '100px';
    }

    var img4 = document.getElementById('img4');
    function bigger4(){
        img4.style.width = '400px';
        img4.style.height = '400px';
    }

    function smaller4(){
        img4.style.width = '100px';
        img4.style.height = '100px';
    }

    var img5 = document.getElementById('img5');
    function bigger5(){
        img5.style.width = '400px';
        img5.style.height = '400px';
    }

    function smaller5(){
        img5.style.width = '100px';
        img5.style.height = '100px';
    }

    var img6 = document.getElementById('img6');
    function bigger6(){
        img6.style.width = '400px';
        img6.style.height = '400px';
    }

    function smaller6(){
        img6.style.width = '100px';
        img6.style.height = '100px';
    }

    var img7 = document.getElementById('img7');
    function bigger7(){
        img7.style.width = '400px';
        img7.style.height = '400px';
    }

    function smaller7(){
        img7.style.width = '100px';
        img7.style.height = '100px';
    }

    var img8 = document.getElementById('img8');
    function bigger8(){
        img8.style.width = '400px';
        img8.style.height = '400px';
    }

    function smaller8(){
        img8.style.width = '100px';
        img8.style.height = '100px';
    }

    var img9 = document.getElementById('img9');
    function bigger9(){
        img9.style.width = '400px';
        img9.style.height = '400px';
    }

    function smaller9(){
        img9.style.width = '100px';
        img9.style.height = '100px';
    }

    var img10 = document.getElementById('img10');
    function bigger10(){
        img10.style.width = '400px';
        img10.style.height = '400px';
    }

    function smaller10(){
        img10.style.width = '100px';
        img10.style.height = '100px';
    }

    var img11 = document.getElementById('img11');
    function bigger11(){
        img11.style.width = '400px';
        img11.style.height = '400px';
    }

    function smaller11(){
        img11.style.width = '100px';
        img11.style.height = '100px';
    }

    var img12 = document.getElementById('img12');
    function bigger12(){
        img12.style.width = '400px';
        img12.style.height = '400px';
    }

    function smaller12(){
        img12.style.width = '100px';
        img12.style.height = '100px';
    }

    var img13 = document.getElementById('img13');
    function bigger13(){
        img13.style.width = '400px';
        img13.style.height = '400px';
    }

    function smaller13(){
        img13.style.width = '100px';
        img13.style.height = '100px';
    }

</script>

<body>
<div class="bjui-pageContent" id="addAgentMer">


    <div style="margin:15px auto 0; width:96%;">
        <div class="row" style="padding: 0 8px;">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">代理商信息</h3></div>
                    <div class="panel-body">
                        <dl class="detail-list">
                            <dd class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">代理商号:</label>
                                    <input id="memberId" name="memberId" class="form-control input-nm"
                                           value="${tblAgentInfoDo.memberId}" size="20" readonly/>
                                    <input type="checkbox" value="memberId"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">代理商名称:</label>
                                    <input id="agentName" name="agentName" class="form-control input-nm"
                                           value="${tblAgentInfoDo.agentName}" size="20" readonly/>
                                    <input type="checkbox" value="agentName"/>

                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">代理商简称名称:</label>
                                    <input id="agentShortName" name="agentShortName" class="form-control input-nm"
                                           value="${tblAgentInfoDo.agentShortName}" size="20" readonly/>
                                    <input type="checkbox" value="agentShortName"/>

                                </div>
                            </dd>

                            <dd class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">注册公司名:</label>
                                    <input id="regName" name="regName" class="form-control input-nm"
                                           value="${tblAgentInfoDo.regName}" size="20" readonly/>
                                    <input type="checkbox" value="regName"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">注册公司号:</label>
                                    <input id="regCode" name="regCode" class="form-control input-nm"
                                           value="${tblAgentInfoDo.regCode}" size="20" readonly/>
                                    <input type="checkbox" value="regCode"/>

                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">税务登记号:</label>
                                    <input id="taxCode" name="taxCode" class="form-control input-nm"
                                           value="${tblAgentInfoDo.taxCode}" size="20" readonly/>
                                    <input type="checkbox" value="taxCode"/>

                                </div>
                            </dd>


                            <dd class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">法人代表:</label>
                                    <input id="legalName" name="legalName" class="form-control input-nm"
                                           value="${tblAgentInfoDo.legalName}" size="20" readonly/>
                                    <input type="checkbox" value="legalName"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">法人身份证:</label>
                                    <input id="idno" name="idno" class="form-control input-nm"
                                           value="${tblAgentInfoDo.idno}" size="20" readonly/>
                                    <input type="checkbox" value="idno"/>

                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">联系人:</label>
                                    <input id="contactName" name="contactName" class="form-control input-nm"
                                           value="${tblAgentInfoDo.contactName}" size="20" readonly/>
                                    <input type="checkbox" value="contactName"/>

                                </div>
                            </dd>

                            <dd class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">联系电话:</label>
                                    <input id="contactMobile" name="contactMobile" class="form-control input-nm"
                                           value="${tblAgentInfoDo.contactMobile}" size="20" readonly/>
                                    <input type="checkbox" value="contactMobile"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">联系地址:</label>
                                    <input id="contactAddr" name="contactAddr" class="form-control input-nm"
                                           value="${tblAgentInfoDo.contactAddr}" size="20" readonly/>
                                    <input type="checkbox" value="contactAddr"/>

                                </div>

                            </dd>
                        </dl>
                    </div>
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
                                <input type="checkbox" value="fee01"/>
                            </div>
                            <div class="form-group col-md-5">
                                <label class="control-label x130">借记卡封顶（元）:</label>
                                <input type="text" name="fee01L" id="fee01L" value="${tblAgentInfoDo.fee01L}"
                                       readonly class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee01L"/>

                            </div>
                        </dd>
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">02贷记卡费率（%）:</label>
                                <input type="text" name="fee02" id="fee02" value="${tblAgentInfoDo.fee02}" readonly
                                       class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee02"/>

                            </div>
                        </dd>

                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">03微信T0交易费率（%）:</label>
                                <input type="text" name="fee07" id="fee07" value="${tblAgentInfoDo.fee03}" readonly
                                       class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee07"/>

                            </div>

                            <div class="form-group col-md-5">
                                <label class="control-label x130">04支付宝T0交易费率（%）:</label>
                                <input type="text" name="fee08" id="fee08" value="${tblAgentInfoDo.fee04}" readonly
                                       class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee08"/>

                            </div>
                        </dd>

                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">05微信T1交易费率（%）:</label>
                                <input type="text" name="fee03" id="fee03" value="${tblAgentInfoDo.fee05}" readonly
                                       class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee03"/>

                            </div>

                            <div class="form-group col-md-5">
                                <label class="control-label x130">06支付宝T1交易费率（%）:</label>
                                <input type="text" name="fee04" id="fee04" value="${tblAgentInfoDo.fee06}" readonly
                                       class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee04"/>

                            </div>

                        </dd>
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">07 T0提现费率（%）:</label>
                                <input type="text" name="fee05" id="fee05" value="${tblAgentInfoDo.fee07}" readonly
                                       class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee05"/>

                            </div>

                            <div class="form-group col-md-5">
                                <label class="control-label x130">08 T1提现费率（%）:</label>
                                <input type="text" name="fee06" id="fee06" value="${tblAgentInfoDo.fee08}" readonly
                                       class="form-control input-nm" size="20"/>
                                <input type="checkbox" value="fee06"/>

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
                            <textarea id="remark" name="remark" value="" style="width:100%;height: 60px;"
                                      placeholder="这里填写审核备注，如：驳回原因"></textarea>
                </div>
            </div>
        </div>
        <div class="form-group" style="margin: 20px 0 20px; ">
            <button type="button" id="auditOkBtn" class="btn  btn-success">审核通过</button>
            &nbsp;
            <button type="button" id="auditRejectBtn" class="btn btn-warning">审核不通过</button>
            &nbsp;
            <button style="display: none" type="button" id="closeBtn" class="btn-close" data-icon="close">关闭</button>

        </div>

    </div>
</div>
</div>
<script>
    $("#addAgentMer :checkbox").click(function () {
        var ck = $(this)[0].checked;
        if (ck) {
            $(this).prev().addClass("v-error");
        } else {
            $(this).prev().removeClass("v-error");
        }
    });
    $("#picArea :checkbox").click(function () {
        var ck = $(this)[0].checked;
        if (ck) {
            $(this).parent().parent().addClass("v-error");
        } else {
            $(this).parent().parent().removeClass("v-error");
        }
    });

    $("#auditRejectBtn").click(function () {
        var checkBoxList = $("#addAgentMer :checkbox");
        var errorFields = [];
        checkBoxList.each(function () {
            if ($(this).attr("checked")) {
                errorFields.push($(this).val());
            }
        });

        if (errorFields.length == 0) {
            alert("请标注审核异常数据！ 选中相应数据后面复选框即可");
            return;
        }
        if (confirm("确认审核不通过该商户吗？")) {

            $.post("${pageContext.request.contextPath}/agentAudit/agent_audit", {
                "memberId": "${tblAgentInfoDo.memberId}",
                "errorFields": errorFields.join(","),
                "audit": "reject",
                "remark": $("#remark").val()
            }, function (res) {

                if (res.statusCode == "200") {
                    $(this).alertmsg("ok", "审核成功！");
                    $("#closeBtn").click();
                } else {
                    $(this).alertmsg("warn", "审核失败！");
                }
            });
        }
    });


    $("#auditOkBtn").click(function () {
alert();
        var checkBoxList = $("#addAgentMer :checkbox");
        var errorFields = [];
        checkBoxList.each(function () {
            if ($(this).attr("checked")) {
                errorFields.push($(this).val());
            }
        });
        $("#audit").val("ok");
        if (errorFields.length != 0) {
            if (confirm("您标注了异常数据，确认审核通过吗？")) {

                alert();
                $.post("${pageContext.request.contextPath}/agentAudit/agent_audit", {
                    "memberId": "${tblAgentInfoDo.memberId}",
                    "errorFields": errorFields.join(","),
                    "audit": "ok",
                    "remark": $("#remark").val()
                }, function (res) {
                    if (res.statusCode == "200") {
                        $(this).alertmsg("ok", "审核成功！");

                        $("#closeBtn").click();
                    } else {
                        $(this).alertmsg("warn", "审核失败！");
                    }
                });
            }
        } else {
            $.post("${pageContext.request.contextPath}/agentAudit/agent_audit", {
                "memberId": "${tblAgentInfoDo.memberId}",
                "errorFields": "",
                "audit": "ok",
                "remark": $("#remark").val()
            }, function (res) {
                if (res.statusCode == "200") {
                    $(this).alertmsg("ok", "审核成功！");

                    $("#closeBtn").click();
                } else {
                    $(this).alertmsg("warn", "审核失败！");
                }
            });
        }

    });
</script>
</body>

</html>
