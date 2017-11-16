<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构费率编辑</title>
</head>
<body>
<div class="bjui-pageHeader">
</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="agentFeeForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/instmccfee/update_instFee_setting" method="post">
        <div class="bjui-searchBar">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">机构费率信息</h3></div>
                    <div class="panel-body">
                        <input type="hidden" id="instId" value="${agentFeeForm.instId}" name="instId" class="form-control">
                        <table width="100%">
                            <tr>
                                <td align="right"> <label>01借记卡费率（%）：</label></td><td align="left"><input type="text" value="${agentFeeForm.fee01}" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后01借记卡费率（%）：</label></td><td align="left"><input type="text" id="fee01" value="${agentFeeForm.fee01}" name="fee01" class="form-control" size="15" ></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>借记卡封顶（元）：</label></td><td align="left"><input type="text"  value="${agentFeeForm.fee01L}"  class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后借记卡封顶（元）：</label></td><td align="left"><input type="text" id="fee01L" value="${agentFeeForm.fee01L}" name="fee01L" class="form-control" size="15" ></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>02贷记卡费率（%）：</label></td><td align="left"><input type="text"  value="${agentFeeForm.fee02}"  class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后02贷记卡费率（%）：</label></td><td align="left"><input type="text" id="fee02" value="${agentFeeForm.fee02}" name="fee02" class="form-control" size="15" ></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>03微信T0交易费率（%）：</label></td><td align="left"><input type="text" value="${agentFeeForm.fee03}" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后03微信T0交易费率（%）：</label></td><td align="left"><input type="text" id="fee03" value="${agentFeeForm.fee03}" name="fee03" class="form-control" size="15"></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>04支付宝T0交易费率（%）：</label></td><td align="left"><input type="text" value="${agentFeeForm.fee04}"  class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后04支付宝T0交易费率（%）：</label></td><td align="left"><input type="text" id="fee04" value="${agentFeeForm.fee04}" name="fee04" class="form-control" size="15"></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>05微信T1交易费率（%）：</label></td><td align="left"><input type="text"value="${agentFeeForm.fee05}" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后05微信T1交易费率（%）：</label></td><td align="left"><input type="text" id="fee05" value="${agentFeeForm.fee05}" name="fee05" class="form-control" size="15"></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>06支付宝T1交易费率（%）：</label></td><td align="left"><input type="text" value="${agentFeeForm.fee06}"  class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后06支付宝T1交易费率（%）：</label></td><td align="left"><input type="text" id="fee06" value="${agentFeeForm.fee06}" name="fee06" class="form-control" size="15" ></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>07 T0提现费率（元）：</label></td><td align="left"><input type="text" value="${agentFeeForm.fee07}"  class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后07 T0提现费率（元）：</label></td><td align="left"><input type="text" id="fee07" value="${agentFeeForm.fee07}" name="fee07" class="form-control" size="15" ></td>
                            </tr>

                            <tr>
                                <td align="right"> <label>08 T0垫资手续费（%）：</label></td><td align="left"><input type="text" value="${agentFeeForm.fee08}" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后08 T0提现费率（%）：</label></td><td align="left"><input type="text" id="fee08" value="${agentFeeForm.fee08}" name="fee08" class="form-control" size="15" ></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
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
