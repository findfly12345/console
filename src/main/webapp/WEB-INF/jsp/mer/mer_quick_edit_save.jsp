<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/1/12
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>快捷商户编辑</title>
</head>
<style>
    .v-error {
        border:1px solid red;
    }
</style>
<body>
<div class="bjui-pageContent" id="merInfoForm">
    <form name="merInfoForm"  data-toggle="validate" novalidate="novalidate"
               action="${pageContext.request.contextPath}/mer/update_quick_mer" method="post">
        <div style="margin:15px auto 0; width:96%;">
            <div class="row" style="padding: 0 8px;">
                <div class="col-md-12">
                    <div  class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">商户上游</h3></div>
                        <div class="panel-body" >
                            <dl class="detail-list" >
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">代理商商号:</label>
                                        <input  id="memberId" name="memberId" class="form-control input-nm" value="${tblAgentInfoDo.memberId}" readonly size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">代理商名称:</label>
                                        <input  id="agentName" name="agentName" class="form-control input-nm" value="${tblAgentInfoDo.agentName}" readonly size="20" />
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">机构号:</label>
                                        <input  id="instCode" name=""instCode"" class="form-control input-nm" value="${tblBtsInstDo.instCode}" readonly size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">机构名称:</label>
                                        <input  id="instName" name="instName" class="form-control input-nm" value="${tblBtsInstDo.instName}" readonly size="20" />
                                    </div>
                                </dd>                                
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">商户基本信息</h3></div>
                        <div class="panel-body">
                            <dl class="detail-list"  >
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户号:</label>
                                        <input type="text" name="merId" id="merId" value="${tblMerInfoDO.merId}" readonly class="form-control input-nm" size="20"
                                               data-rule="商户号:required;" />                                                                               </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户名称:</label>
                                        <input type="text" name="merName" id="merName" value="${tblMerInfoDO.merName}" readonly class="form-control input-nm" size="20" data-rule="商户名称:required;" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户类型:</label>
                                        <select name="merType" id="merType" data-toggle="selectpicker" disabled data-width="200" >
                                            <option value="0" <c:if test="${tblMerInfoDO.merType == 0}">selected</c:if> >公司商户</option>
                                            <option value="0" <c:if test="${tblMerInfoDO.merType == 1}">selected</c:if> >个体商户(有执照)</option>
                                            <option value="0" <c:if test="${tblMerInfoDO.merType == 2}">selected</c:if> >无执照商户</option>
                                        </select>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">注册名:</label>
                                        <input type="text" name="regName" id="regName" value="${tblMerInfoDO.regName}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">注册简称:</label>
                                        <input type="text" name="regShortName" id="regShortName" value="${tblMerInfoDO.regShortName}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">注册地址:</label>
                                        <input type="text" name="merAddress" id="merAddress" value="${tblMerInfoDO.merAddress}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">省市编码:</label>
                                        <input type="text" name="provinceCode" id="provinceCode" value="${CustAddressDo.provinceCode}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">城市编码:</label>
                                        <input type="text" name="cityCode" id="cityCode" value="${CustAddressDo.cityCode}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">地区编码:</label>
                                        <input type="text" name="areaCode" id="areaCode" value="${CustAddressDo.areaCode}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">营业执照编号:</label>
                                        <input type="text" name="busLicNm" id="busLicNm" value="${tblMerInfoDO.busLicNm}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">营业执照有效期:</label>
                                        <input type="text" data-toggle="datepicker" name="busLicExpire" id="busLicExpire"  readonly value="${tblMerInfoDO.busLicExpire}" data-rule="营业执照有效期:required;date"  />
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表:</label>
                                        <input type="text" name="legalPerson" id="legalPerson" value="${tblMerInfoDO.legalPerson}" readonly class="form-control input-nm" size="20" data-rule="法人代表:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件类型:</label>
                                        <select name="legalPersonCertType" id="legalPersonCertType"  data-toggle="selectpicker" disabled data-width="200">
                                            <option value="0">身份证</option>
                                        </select>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件号码:</label>
                                        <input type="text" name="legalPersonCertNm" id="legalPersonCertNm" readonly value="${tblMerInfoDO.legalPersonCertNm}"  class="form-control input-nm" size="20" data-rule="法人证件号:required ID_card;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件有效期:</label>
                                        <input type="text" data-toggle="datepicker" name="legalPersonCertExpire" disabled value="${tblMerInfoDO.legalPersonCertExpire}"  id="legalPersonCertExpire"  data-rule="法人代表证件有效期:required;date">
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系人:</label>
                                        <input type="text" name="contactPerson" id="contactPerson" readonly value="${tblMerInfoDO.contactPerson}"  class="form-control input-nm" size="20" data-rule="联系人:required;"/>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系号码:</label>
                                        <input type="text" name="contactMobile" id="contactMobile" readonly value="${tblMerInfoDO.contactMobile}"  class="form-control input-nm" size="20" data-rule="联系电话:required mobile"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系邮箱:</label>
                                        <input type="text" name="contactEmail" id="contactEmail" readonly value="${tblMerInfoDO.contactEmail}"  class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                <div class="form-group  col-md-4">
                                    <a href="${pageContext.request.contextPath}/mer/query_quick_mer_base_info?id=${tblMerInfoDO.merId}" class="btn btn-green" data-toggle="navtab" data-id="navtab-detail" data-title="商户基础信息修改-(${tblMerInfoDO.merName})">编辑</a>
                                </div>
                            </dl>

                        </div>
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
                                    <label class="control-label x110">开户行:</label>
                                    <input type="text" name="bankName" id="bankName" value="${tblMerBankInfoDO.bankName}" readonly class="form-control input-nm" size="20"   />
                                        <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">开户行省:</label>
                                    <input type="text" name="provName" id="provName" value="${tblMerBankInfoDO.provName}" readonly class="form-control input-nm" size="20"   />
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">开户行市:</label>
                                    <input type="text" name="cityName" id="cityName" value="${tblMerBankInfoDO.cityName}" readonly class="form-control input-nm" size="20"   />
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">开户支行:</label>
                                    <input type="text" name="bankBranchName" id="bankBranchName" value="${tblMerBankInfoDO.bankBranchName}" readonly class="form-control input-nm" size="20"   />
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">联行号:</label>
                                    <input type="text" name="cnaps" id="cnaps" value="${tblMerBankInfoDO.cnaps}" readonly class="form-control input-nm" size="20"   />
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">账户类型:</label>
                                    <select name="isPrivate" id="isPrivate"  data-toggle="selectpicker" disabled data-width="200">
                                        <option value="Y" <c:if test="${tblMerBankInfoDO.isPrivate == 'Y'}">selected</c:if> >对私</option>
                                        <option value="N" <c:if test="${tblMerBankInfoDO.isPrivate == 'N'}">selected</c:if> >对公</option>
                                    </select>
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">账户名:</label>
                                    <input type="text" name="acctName" id="acctName" value="${tblMerBankInfoDO.acctName}" readonly class="form-control input-nm" size="20" data-rule="账户名:required;"/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">账户号:</label>
                                    <input type="text" name="acctNo" id="acctNo" value="${tblMerBankInfoDO.acctNo}" readonly class="form-control input-nm" size="20" data-rule="账户号:required;"/>
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
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">MCC码:</label>
                                    <input type="text" name="mccValue" id="mccValue" value="${tblMerRelevanceMccDo.mccValue}" readonly class="form-control input-nm" size="20" />
                                </div>
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">MCC描述:</label>
                                    <input type="text" name="mccDesp" id="mccDesp" value="${tblMerRelevanceMccDo.mccDesp}"  readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>

                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">借记卡T1手续费（%）:</label>
                                    <input type="text" name="fee01" id="fee01" value="${quickMerFeeForm.fee01}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">借记卡T1-封顶（元）:</label>
                                    <input type="text" name="fee01L" id="fee01L" value="${quickMerFeeForm.fee01L}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">贷记卡T1手续费（%）:</label>
                                    <input type="text" name="fee02" id="fee02" value="${quickMerFeeForm.fee02}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>                                         
                            <dd class="row">       
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">微信T0交易手续费（%）:</label>
                                    <input type="text" name="fee03" id="fee03" value="${quickMerFeeForm.fee03}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                                                 
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">支付宝T0交易手续费（%）:</label>
                                    <input type="text" name="fee04" id="fee04" value="${quickMerFeeForm.fee04}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                               
                            </dd>
                            <dd class="row">       
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">微信T0交易手续费（单笔）:</label>
                                    <input type="text" name="fee09" id="fee09" value="${quickMerFeeForm.fee09}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>                                                                             
                            </dd>                            
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">微信T1交易手续费（%）:</label>
                                    <input type="text" name="fee05" id="fee05" value="${quickMerFeeForm.fee05}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>

                                <div class="form-group col-md-5">
                                    <label class="control-label x130">支付宝T1交易手续费（%）:</label>
                                    <input type="text" name="fee06" id="fee06" value="${quickMerFeeForm.fee06}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                            <dd class="row">
                               <div class="form-group col-md-5">
                                    <label class="control-label x130">T0提现手续费（元）:</label>
                                    <input type="text" name="fee07" id="fee07" value="${quickMerFeeForm.fee07}"  placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                            
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">T0垫资手续费（%）:</label>
                                    <input type="text" name="fee08" id="fee08" value="${quickMerFeeForm.fee08}" placeholder="0.00" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                            
                        </dl>
                    </div>
                </div>
    </form>
</div>

	<div class="bjui-pageFooter">
		<ul>
			<li><button type="button" class="btn-close">关闭</button></li>
			<li><button type="submit" class="btn-default">保存</button></li>
		</ul>
	</div>
</body>
</html>
