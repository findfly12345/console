<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>代理商商户审核</title>
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
        <div class="row" style="padding: 0 8px;">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">代理商信息</h3></div>
                    <div class="panel-body">
                        <dl class="detail-list">
                            <dd class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">代理商商号:</label>
                                    <input id="memberId" name="memberId" class="form-control input-nm"
                                           value="${tblAgentInfoDo.memberId}" size="20" readonly/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">代理商商户名称:</label>
                                    <input id="agentName" name="agentName" class="form-control input-nm"
                                           value="${tblAgentInfoDo.agentName}" size="20" readonly/>

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
                        <dl class="detail-list">
                            <dd class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">商户号:</label>
                                    <input type="text" value="${tblMerInfoDO.merId}"
                                           class="form-control input-nm" size="20"
                                           data-rule="商户号:required;" readonly/>

                                    <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">商户名称:</label>
                                    <input type="text" name="merName" id="merName" value="${tblMerInfoDO.merName}"
                                           class="form-control  input-nm" size="20" data-rule="商户名称:required;"
                                           readonly/>

                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">商户类型:</label>
                                    <input type="text" name="merType" id="merType" value="${tblMerInfoDO.merType}"
                                           class="form-control  input-nm" size="20"
                                           readonly/>
                                    <%--<select name="merType" id="" data-toggle="selectpicker" data-width="200"--%>
                                    <%--disabled="true">--%>
                                    <%--<c:if test="${ == 0}">--%>
                                    <%--<option value="0">公司商户</option>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${tblMerInfoDO.merType == 1}">--%>
                                    <%--<option value="0">个体商户</option>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${tblMerInfoDO.merType == 2}">--%>
                                    <%--<option value="0">无执照商户</option>--%>
                                    <%--</c:if>--%>
                                    <%--</select>--%>

                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">注册名:</label>
                                    <input type="text" name="regName" id="regName" value="${tblMerInfoDO.regName}"
                                           class="form-control input-nm" size="20" readonly/>

                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">注册简称:</label>
                                    <input type="text" name="regShortName" id="regShortName"
                                           value="${tblMerInfoDO.regShortName}" readonly
                                           class="form-control input-nm" size="20"/>

                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label x110">注册地址:</label>
                                    <input type="text" name="merAddress" id="merAddress"
                                           value="${tblMerInfoDO.merAddress}" readonly class="form-control input-nm"
                                           size="20"/>

                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">注册资本:</label>
                                    <input type="text" name="regFunds" id="regFunds"
                                           value="${tblMerInfoDO.regFunds}" readonly class="form-control input-nm"
                                           size="20"/>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">营业执照编号:</label>
                                    <input type="text" name="busLicNm" id="busLicNm"
                                           value="${tblMerInfoDO.busLicNm}" readonly class="form-control input-nm"
                                           size="20"/>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">营业执照有效期:</label>
                                    <input type="text" name="busLicExpire" id="busLicExpire"
                                           value="${tblMerInfoDO.busLicExpire}" readonly
                                           class="form-control input-nm" size="20"/>

                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">税务登记证:</label>
                                    <input type="text" name="taxRegCert" id="taxRegCert"
                                           value="${tblMerInfoDO.taxRegCert}" readonly class="form-control input-nm"
                                           size="20"/>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">法人代表:</label>
                                    <input type="text" name="legalPerson" id="legalPerson"
                                           value="${tblMerInfoDO.legalPerson}" readonly
                                           class="form-control input-nm" size="20" data-rule="法人代表:required;"/>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">法人代表证件类型:</label>
                                    <input type="text" name="legalPersonCertType" id="legalPersonCertType"
                                           value="身份证" readonly
                                           class="form-control input-nm" size="20" data-rule="法人代表:required;"/>

                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">法人代表证件号码:</label>
                                    <input type="text" name="legalPersonCertNm" id="legalPersonCertNm"
                                           value="${tblMerInfoDO.legalPersonCertNm}" readonly
                                           class="form-control input-nm" size="20" data-rule="法人证件号:required;"/>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">法人代表证件有效期:</label>
                                    <input type="text" name="legalPersonCertExpire"
                                           value="${tblMerInfoDO.legalPersonCertExpire}" readonly
                                           id="legalPersonCertExpire" readonly class="form-control input-nm"
                                           size="20">

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">联系人:</label>
                                    <input type="text" name="contactPerson" id="contactPerson"
                                           value="${tblMerInfoDO.contactPerson}" readonly
                                           class="form-control input-nm" size="20" data-rule="联系人:required;"/>

                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">联系号码:</label>
                                    <input type="text" name="contactMobile" id="contactMobile"
                                           value="${tblMerInfoDO.contactMobile}" readonly
                                           class="form-control input-nm" size="20" data-rule="联系电话:required;"/>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">联系邮箱:</label>
                                    <input type="text" name="contactEmail" id="contactEmail"
                                           value="${tblMerInfoDO.contactEmail}" readonly
                                           class="form-control input-nm" size="20"/>

                                </div>
                            </dd>
                        </dl>

                    </div>
                </div>

            </div>

        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">商户开户行信息</h3></div>
                <div class="panel-body">
                    <dl class="detail-list">
                        <dd class="row">
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">开户行:</label>
                                <input type="text" name="bankName" id="bankName"
                                       value="${tblMerBankInfoDO.bankName}" class="form-control input-nm" size="20"
                                       readonly/>

                                <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                            </div>
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">开户行省:</label>
                                <input type="text" name="provName" id="provName"
                                       value="${tblMerBankInfoDO.provName}" class="form-control input-nm" size="20"
                                       readonly/>

                            </div>
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">开户行市:</label>
                                <input type="text" name="cityName" id="cityName"
                                       value="${tblMerBankInfoDO.cityName}" class="form-control input-nm" size="20"
                                       readonly/>

                            </div>
                        </dd>
                        <dd class="row">
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">开户支行:</label>
                                <input type="text" name="bankBranchName" id="bankBranchName"
                                       value="${tblMerBankInfoDO.bankBranchName}" class="form-control input-nm"
                                       size="20" readonly/>

                            </div>
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">联行号:</label>
                                <input type="text" name="cnaps" id="cnaps" value="${tblMerBankInfoDO.cnaps}"
                                       class="form-control input-nm" size="20" readonly/>

                            </div>
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">账户类型:</label>
                                <input type="text" name="isPrivate" id="isPrivate"
                                       value="${tblMerBankInfoDO.isPrivate == 'Y'?"对私":"对公"}"
                                       class="form-control input-nm" size="20" readonly/>

                            </div>
                        </dd>
                        <dd class="row">
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">账户名:</label>
                                <input type="text" name="acctName" id="acctName"
                                       value="${tblMerBankInfoDO.acctName}" readonly class="form-control input-nm"
                                       size="20" data-rule="账户名:required;"/>

                            </div>
                            <div class="form-group  col-md-4">
                                <label class="control-label x110">账户号:</label>
                                <input type="text" name="acctNo" id="acctNo" value="${tblMerBankInfoDO.acctNo}"
                                       readonly class="form-control input-nm" size="20" data-rule="账户号:required;"/>

                            </div>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">商户费率信息</h3></div>
                <div class="panel-body">
                    <dl class="detail-list">
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">借记卡T1手续费（%）:</label>
                                <input type="text" name="fee01" id="fee01" value="${agentMerFeeForm.fee01}" readonly
                                       class="form-control input-nm" size="20"/>

                                <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                            </div>
                            <div class="form-group col-md-5">
                                <label class="control-label x130">借记卡T1-封顶（元）:</label>
                                <input type="text" name="fee01L" id="fee01L" value="${agentMerFeeForm.fee01L}"
                                       readonly class="form-control input-nm" size="20"/>

                            </div>
                        </dd>
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">贷记卡T1手续费（%）:</label>
                                <input type="text" name="fee02" id="fee02" value="${agentMerFeeForm.fee02}" readonly
                                       class="form-control input-nm" size="20"/>

                            </div>
                        </dd>
                        
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">T0提现手续费:</label>
                                <input type="text" name="fee07" id="fee07" value="${agentMerFeeForm.fee07}" readonly
                                       class="form-control input-nm" size="20"/>

                            </div>
                        
                            <div class="form-group col-md-5">
                                <label class="control-label x130">T0垫资手续费（%）:</label>
                                <input type="text" name="fee08" id="fee08" value="${agentMerFeeForm.fee08}" readonly
                                       class="form-control input-nm" size="20"/>

                            </div>
                        </dd>
                        
                        <dd class="row">
                            <div class="form-group col-md-5">
                                <label class="control-label x130">微信T0交易手续费:</label>
                                <input type="text" name="fee03" id="fee03" value="${agentMerFeeForm.fee03}" readonly
                                       class="form-control input-nm" size="20"/>

                            </div>
                            
                            <div class="form-group col-md-5">
                                <label class="control-label x130">支付宝T0交易手续费:</label>
                                <input type="text" name="fee04" id="fee04" value="${agentMerFeeForm.fee04}" readonly
                                       class="form-control input-nm" size="20"/>

                            </div>
                         
                        </dd>
                        <dd class="row">
                           <div class="form-group col-md-5">
                                <label class="control-label x130">微信T1交易手续费:</label>
                                <input type="text" name="fee05" id="fee05" value="${agentMerFeeForm.fee05}" readonly
                                       class="form-control input-nm" size="20"/>

                            </div>
                        
                            <div class="form-group col-md-5">
                                <label class="control-label x130">支付宝T1交易手续费:</label>
                                <input type="text" name="fee06" id="fee06" value="${agentMerFeeForm.fee06}" readonly
                                       class="form-control input-nm" size="20"/>

                            </div>
                        </dd>
                       
                    </dl>
                </div>
            </div>
        </div>
        <div class="row" style="padding: 0 8px;" id="picArea">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户图片详情</h3></div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress1">
                                <div style="font-size: 14px;color:#999;text-align: center;">营业执照</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                    <c:if test="${tblMerFileInfoDO.picAddress1 != null}">
                                        <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress1}"
                                             width="150" height="150">
                                    </c:if>
                                </span>

                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress2">
                                <div style="font-size: 14px;color:#999;text-align: center;">法人代表身份证正面</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress2 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress2}"
                                         width="150" height="150">
                                </c:if>
                            </span>


                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress3">
                                <div style="font-size: 14px;color:#999;text-align: center;">法人代表身份证反面 </div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress3 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress3}"
                                         width="150" height="150">
                                </c:if>
                            </span>


                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress4">
                                <div style="font-size: 14px;color:#999;text-align: center;">开户许可证复印件</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress4 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress4}"
                                         width="150" height="150">
                                </c:if>
                            </span>


                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress5">
                                <div style="font-size: 14px;color:#999;text-align: center;">组织机构代码证复印件</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress5 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress5}"
                                         width="150" height="150">
                                </c:if>
                            </span>


                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress6">
                                <div style="font-size: 14px;color:#999;text-align: center;">税务登记证复印件</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress6 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress6}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress7">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户门头照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress7 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress7}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress8">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户内部照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress8 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress8}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress9">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户经营产品照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress9 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress9}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress10">
                                <div style="font-size: 14px;color:#999;text-align: center;">银行卡正面照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress10 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress10}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px;" id="picAddress11">
                                <div style="font-size: 14px;color:#999;text-align: center;">银行卡反面照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress11 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress11}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress12">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户信息调查表</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress12 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress12}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress13">
                                <div style="font-size: 14px;color:#999;text-align: center;">合同协议照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress13 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress13}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress15">
                                <div style="font-size: 14px;color:#999;text-align: center;">其他资料照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress15 != null}">
                                    <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress15}"
                                         width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">审核备注</h3></div>
                <div class="panel-body">
                            <textarea id="remark" name="remark" disabled
                                      style="width:100%;height: 60px;"
                            >${tblMerAuditRecordDO.remark}</textarea>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">审核备注</h3></div>
                <div class="panel-body">
                            <textarea id="inRemark" name="inRemark" disabled
                                      style="width:100%;height: 60px;"
                            >${tblMerAuditRecordDO.inRemark}</textarea>
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
    var errorFields = '${tblMerAuditRecordDO.errorField}'.split(",");

   setTimeout(function(){
       for (var i = 0; i < errorFields.length; i++) {
           $("#" + errorFields[i]).addClass("v-error");
       }
   },1000);


</script>
</body>

</html>
