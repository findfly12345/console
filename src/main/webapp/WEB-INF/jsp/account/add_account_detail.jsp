<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%java.util.Date date=new java.util.Date();%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>添加账号</title>
</head>
<body>
<div class="bjui-pageContent" id="addAgentMer">
    <form id="pagerForm" name="pagerForm"  data-toggle="validate" novalidate="novalidate"
          action="${pageContext.request.contextPath}/account/add_account_detail" method="post">
        <div style="margin:15px auto 0; width:96%;">
            <div class="row" style="padding: 0 8px;">
                <div class="col-md-12">
                    <div  class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">代理商信息</h3></div>
                        <div class="panel-body" >
                            <dl class="detail-list" >
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label>代理商号:</label>
                                        <a href="${pageContext.request.contextPath}/mer/go_to_select_agent_mer" class="btn btn-green" data-toggle="dialog" data-width="500" data-height="550" data-id="dialog-mask" data-mask="true" data-title="选择代理商">选择代理商</a>&nbsp;
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">代理商商号:</label>
                                        <input  id="memberId" name="memberId" class="form-control input-nm" size="20" readonly/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">代理商商户名称:</label>
                                        <input  id="agentName" name="agentName" class="form-control input-nm" size="20" readonly/>
                                    </div>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">账号详细信息</h3></div>
                        <div class="panel-body">
                            <dl class="detail-list"  >
                                <dd class="row">

                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">客户类型：:</label>
                                        <select name="partyClassCd" id="partyClassCd" data-toggle="selectpicker" data-width="200">
                                            <option value="Y">对私</option>
                                            <option value="N">对公</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">账户类型:</label>
                                        <select name="aml1TypeCd" id="aml1TypeCd" data-toggle="selectpicker" data-width="200">
                                            <option value="01">银行账户号</option>
                                            <option value="02">银行卡卡号</option>
                                        </select>
                                    </div>

                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">证件类型:</label>
                                        <select name="cardType" id="cardType" data-toggle="selectpicker" data-width="200">
                                            <option value="01">身份证</option>
                                        </select>
                                    </div>

                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">账号:</label>
                                        <input type="text" name="acctNum" id="acctNum" value="" class="form-control input-nm" size="20"  data-rule="账号:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">开户机构名称:</label>
                                        <input type="text" name="organkeyName" id="organkeyName" value="" class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">开户机构:</label>
                                        <input type="text" name="organkey" id="organkey" value="" class="form-control input-nm" size="20" />
                                    </div>

                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">开户子机构:</label>
                                        <input type="text" name="subOrgankey" id="subOrgankey" value="" class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">开户子机构名称:</label>
                                        <input type="text" name="subOrgankeyName" id="subOrgankeyName" value="" class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">证件号码:</label>
                                        <input type="text" name="cardNo" id="cardNo" value="" class="form-control input-nm" size="20"  data-rule="证件号码:required;"/>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">手机号:</label>
                                        <input type="text" name="tel" id="tel" value="" class="form-control input-nm" size="20" data-rule="手机号:required;"/>
                                    </div>

                                </dd>
                            </dl>

                        </div>
                    </div>

                </div>

            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <button type="submit" class="btn-default" data-icon="save">提交</button>&nbsp;
            </div>
        </div>
    </form>
</div>
</div>
</body>
</html>
