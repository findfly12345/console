<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/1/12
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%java.util.Date date=new java.util.Date();%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="bjui-pageContent" id="addAgentMer">
    <form id="pagerForm" name="pagerForm"  data-toggle="validate" novalidate="novalidate"
          action="${pageContext.request.contextPath}/mer/add_agent_mer" method="post">
        <div style="margin:15px auto 0; width:96%;">
            <div class="row" style="padding: 0 8px;">
                <div class="col-md-12">
                    <div  class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">代理商信息</h3></div>
                        <div class="panel-body" >
                            <dl class="detail-list" >
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label>代理商商号:</label>
                                        <a href="${pageContext.request.contextPath}/mer/go_to_select_agent_mer" class="btn btn-green" data-toggle="dialog" data-width="500" data-height="550" data-id="dialog-mask" data-mask="true" data-title="选择代理商">选择代理商</a>&nbsp;
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x100">代理商商号:</label>
                                        <input  id="memberId" name="memberId" class="form-control input-nm" size="20" readonly/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x100">代理商商户名称:</label>
                                        <input  id="agentName" name="agentName" class="form-control input-nm" size="20" readonly/>
                                    </div>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">商户详细信息</h3></div>
                        <div class="panel-body">
                            <dl class="detail-list"  >
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x100">商户号:</label>
                                        <input type="text" name="merId" id="merId" value="${merId}" class="form-control input-nm" size="20"
                                               data-rule="商户号:required;" readonly/>
                                        <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x100">商户名称:</label>
                                        <input type="text" name="merName" id="merName" value="" class="form-control  input-nm" size="20" data-rule="商户名称:required;"/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x100">商户类型:</label>
                                        <select name="merType" id="merType" data-toggle="selectpicker" data-width="200">
                                            <option value="0">公司商户</option>
                                            <option value="1">个体商户</option>
                                            <option value="2">无执照商户</option>
                                        </select>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x100">注册名:</label>
                                        <input type="text" name="regName" id="regName" value="" class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x100">注册简称:</label>
                                        <input type="text" name="regShortName" id="regShortName" value="" class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">注册资本:</label>
                                        <input type="text" name="regFunds" id="regFunds" value="" class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">营业执照编号:</label>
                                        <input type="text" name="busLicNm" id="busLicNm" value="" class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">营业执照有效期:</label>
                                        <input type="text" data-toggle="datepicker" name="busLicExpire" id="busLicExpire" value="" data-rule="营业执照有效期:required;date"  />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">税务登记证:</label>
                                        <input type="text" name="taxRegCert" id="taxRegCert" value="" class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">法人代表:</label>
                                        <input type="text" name="legalPerson" id="legalPerson" value="" class="form-control input-nm" size="20" data-rule="法人代表:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">法人代表证件类型:</label>
                                        <select name="legalPersonCertType" id="legalPersonCertType" data-toggle="selectpicker" data-width="200">
                                            <option value="0">身份证</option>
                                        </select>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">法人代表证件号码:</label>
                                        <input type="text" name="legalPersonCertNm" id="legalPersonCertNm" value="" class="form-control input-nm" size="20" data-rule="法人证件号:required ID_card"/>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">法人代表证件有效期:</label>
                                        <input type="text" data-toggle="datepicker" name="legalPersonCertExpire" id="legalPersonCertExpire"  data-rule="法人代表证件有效期:required;date">
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">联系人:</label>
                                        <input type="text" name="contactPerson" id="contactPerson" value=""  class="form-control input-nm" size="20" data-rule="联系人:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">联系号码:</label>
                                        <input type="text" name="contactMobile" id="contactMobile" value=""  class="form-control input-nm" size="20" data-rule="联系电话:required mobile"/>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x100">联系邮箱:</label>
                                        <input type="text" name="contactEmail" id="contactEmail" value="" class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-12">
                <div  class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">选择注册地</h3></div>
                    <div class="panel-body" >
                        <dl class="detail-list"  >
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label>选择商户地区:</label>
                                    <a href="${pageContext.request.contextPath}/mer/go_to_select_mer_address_info" class="btn btn-green" data-toggle="dialog" data-width="600" data-height="550" data-id="dialog-mask" data-mask="true" data-title="选择开户行">选择商户地区</a>&nbsp;
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">商户省份:</label>
                                    <input type="text" name=provinceId id="provinceId" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">商户市:</label>
                                    <input type="text" name="cityId" id="cityId" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">商户市(县):</label>
                                    <input type="text" name="areaId" id="areaId" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                            </dd>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">详细地址:</label>
                                    <input type="text" name="merAddress" id="merAddress" value="" class="form-control input-nm" size="20" data-rule="详细地址:required;"/>
                                </div>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
            
            <div class="col-md-12">
                <div  class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户开户行信息</h3></div>
                    <div class="panel-body" >
                        <dl class="detail-list"  >
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label>选择开户行:</label>
                                    <a href="${pageContext.request.contextPath}/mer/go_to_select_bank_info" class="btn btn-green" data-toggle="dialog" data-width="600" data-height="550" data-id="dialog-mask" data-mask="true" data-title="选择开户行">选择开户行</a>&nbsp;
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">开户行:</label>
                                    <input type="text" name="bankName" id="bankName" value="" class="form-control input-nm" size="20"   readonly/>
                                    <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">开户行省:</label>
                                    <input type="text" name="provName" id="provName" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">开户行市:</label>
                                    <input type="text" name="cityName" id="cityName" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">开户支行:</label>
                                    <input type="text" name="bankBranchName" id="bankBranchName" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">联行号:</label>
                                    <input type="text" name="cnaps" id="cnaps" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">账户类型:</label>
                                    <select name="isPrivate" id="isPrivate" data-toggle="selectpicker" data-width="200">
                                        <option value="Y">对私</option>
                                        <option value="N">对公</option>
                                    </select>
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">账户名:</label>
                                    <input type="text" name="acctName" id="acctName" value="" class="form-control input-nm" size="20" data-rule="账户名:required;"/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x100">账户号:</label>
                                    <input type="text" name="acctNo" id="acctNo" value="" class="form-control input-nm" size="20" data-rule="账户号:required;"/>
                                </div>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div  class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户费率信息</h3></div>
                    <div class="panel-body" >
                        <dl class="detail-list"  >

                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label>选择行业MCC:</label>
                                    <a href="${pageContext.request.contextPath}/mer/go_to_select_mer_mcc" class="btn btn-green" data-toggle="dialog" data-width="600" data-height="550" data-id="dialog-mask" data-mask="true" data-title="选择行业MCC">选择行业MCC</a>&nbsp;
                                </div>
                            </dd>

                            <dd class="row">

                                <div class="form-group  col-md-5">
                                    <label class="control-label x100">MCC码:</label>
                                    <input type="text" name="mccValue" id="mccValue" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-5">
                                    <label class="control-label x100">MCC描述:</label>
                                    <input type="text" name="mccDesp" id="mccDesp" value="" class="form-control input-nm" size="20"   readonly/>
                                </div>


                                <div class="form-group col-md-5">
                                    <label class="control-label x100">借记卡T1手续费（%）:</label>
                                    <input type="text" name="fee01" id="fee01" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                    <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                </div>
                                <div class="form-group col-md-5">
                                    <label class="control-label x100">借记卡T1-封顶（元）:</label>
                                    <input type="text" name="fee01L" id="fee01L" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x100">贷记卡T1手续费（%）:</label>
                                    <input type="text" name="fee02" id="fee02" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>
                            </dd>

                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x100">T0提现手续费（元）:</label>
                                    <input type="text" name="fee07" id="fee07" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>

                                <div class="form-group col-md-5">
                                    <label class="control-label x100">T0垫资手续费（%）:</label>
                                    <input type="text" name="fee08" id="fee08" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>
                            </dd>

                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x100">微信T0交易手续费（%）:</label>
                                    <input type="text" name="fee03" id="fee03" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>

                                <div class="form-group col-md-5">
                                    <label class="control-label x100">支付宝T0交易手续费（%）:</label>
                                    <input type="text" name="fee04" id="fee04" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>

                            </dd>
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x100">微信T1交易手续费（%）:</label>
                                    <input type="text" name="fee05" id="fee05" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>

                                <div class="form-group col-md-5">
                                    <label class="control-label x100">支付宝T1交易手续费（%）:</label>
                                    <input type="text" name="fee06" id="fee06" value="" placeholder="0.00" class="form-control input-nm" size="20" />
                                </div>
                            </dd>

                        </dl>
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
