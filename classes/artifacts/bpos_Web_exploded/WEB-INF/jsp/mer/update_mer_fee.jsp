<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: APPLE
Date: 15/12/18
Time: 上午11:22
To change this template use File | settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>费率信息编辑</title>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="merFeeForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/mer/update_mer_fee" method="post">
        <div class="bjui-searchBar">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户费率信息</h3></div>
                    <div class="panel-body">
                        <input type="hidden" id="merId" value="${merFeeForm.merId}" name="merId" class="form-control">
                        <table width="100%">
                            <tr>
                                <td align="right"> <label>借记卡费率（%）：</label></td><td align="left"><input type="text" id="fee01" value="${merFeeForm.fee01}" name="fee01" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后借记卡费率（%）：</label></td><td align="left"><input type="text" id="fee01" value="${merFeeForm.fee01}" name="fee01" class="form-control" size="15" ></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>借记卡封顶（元）：</label></td><td align="left"><input type="text" id="fee01L" value="${merFeeForm.fee01L}" name="fee01L" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后借记卡封顶（元）：</label></td><td align="left"><input type="text" id="fee01L" value="${merFeeForm.fee01L}" name="fee01L" class="form-control" size="15"></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>贷记卡费率（%）：</label></td><td align="left"><input type="text" id="fee02" value="${merFeeForm.fee02}" name="fee02" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后贷记卡费率（%）：</label></td><td align="left"><input type="text" id="fee02" value="${merFeeForm.fee02}" name="fee02" class="form-control" size="15" ></td>
                            </tr>
                             <tr>
                                <td align="right"> <label>微信T0交易费率（%）：</label></td><td align="left"><input type="text" id="fee03" value="${merFeeForm.fee03}" name="fee03" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后微信T0交易费率（%）：</label></td><td align="left"><input type="text" id="fee03" value="${merFeeForm.fee03}" name="fee03" class="form-control" size="15" ></td>
                            </tr>                           
                            <tr>
                                <td align="right"> <label>支付宝T0交易费率（%）：</label></td><td align="left"><input type="text" id="fee04" value="${merFeeForm.fee04}" name="fee04" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后支付宝T0交易费率（%）</label></td><td align="left"><input type="text" id="fee04" value="${merFeeForm.fee04}" name="fee04" class="form-control" size="15" ></td>
                            </tr> 
                             <tr>
                                <td align="right"> <label>微信T1交易费率（%）：</label></td><td align="left"><input type="text" id="fee05" value="${merFeeForm.fee05}" name="fee05" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后微信T0交易费率（%）：</label></td><td align="left"><input type="text" id="fee05" value="${merFeeForm.fee05}" name="fee05" class="form-control" size="15" ></td>
                            </tr>                           
                            <tr>
                                <td align="right"> <label>支付宝T1交易费率（%）：</label></td><td align="left"><input type="text" id="fee06" value="${merFeeForm.fee06}" name="fee06" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后支付宝T0交易费率（%）</label></td><td align="left"><input type="text" id="fee06" value="${merFeeForm.fee06}" name="fee06" class="form-control" size="15" ></td>
                            </tr>                                                                                                 
                            <tr>
                                <td align="right"> <label>T0提现手续费(元)：</label></td><td align="left"><input type="text" id="fee07" value="${merFeeForm.fee07}" name="fee07" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后提现手续费(元)）：</label></td><td align="left"><input type="text" id="fee07" value="${merFeeForm.fee07}" name="fee07" class="form-control" size="15" ></td>
                            </tr>
                            <tr>
                                <td align="right"> <label>T0垫资手续费（%）：</label></td><td align="left"><input type="text" id="fee08" value="${merFeeForm.fee08}" name="fee08" class="form-control" size="15" disabled="true"></td>
                                <td align="right"> <label>修改后T0垫资手续费（%）：</label></td><td align="left"><input type="text" id="fee08" value="${merFeeForm.fee08}" name="fee08" class="form-control" size="15"></td>
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
