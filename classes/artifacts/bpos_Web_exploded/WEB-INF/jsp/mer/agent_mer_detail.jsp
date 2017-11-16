<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/1/12
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>代理商商户详情</title>
    <style>
        .j_custom_span_pic{position:relative;display: inline-block;height: auto;overflow: hidden;
            border:1px #eeeeee solid;}
        .j_custom_span_pic i {position: absolute;display:block;top: 8px;right: 8px;font-size: 17px;
            color: red;display: none;cursor: pointer}
        .j_custom_span_pic:hover{border: 1px #049cff solid}
        .j_custom_span_pic:hover i{display: block}
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
</head>
<body>
<div class="bjui-pageContent" id="addAgentMer">
    <form:form id="pagerForm" name="pagerForm"  validate="validate" data-toggle="ajaxsearch"
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
                                        <label class="control-label x110">代理商商号:</label>
                                        <input  id="memberId" name="memberId" class="form-control input-nm" value="${tblAgentInfoDo.memberId}" size="20" readonly/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">代理商商户名称:</label>
                                        <input  id="agentName" name="agentName" class="form-control input-nm" value="${tblAgentInfoDo.agentName}" size="20" readonly/>
                                    </div>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="padding: 0 8px;">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">商户详细信息</h3></div>
                        <div class="panel-body">
                            <dl class="detail-list"  >
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户号:</label>
                                        <input type="text" name="merId" id="merId" value="${tblMerInfoDO.merId}" class="form-control input-nm" size="20"
                                               data-rule="商户号:required;" readonly/>
                                            <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户名称:</label>
                                        <input type="text" name="merName" id="merName" value="${tblMerInfoDO.merName}" class="form-control  input-nm" size="20" data-rule="商户名称:required;" readonly/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户类型:</label>
                                        <select name="merType" id="merType" data-toggle="selectpicker" data-width="200" disabled="true">
                                            <c:if test="${tblMerInfoDO.merType == 0}"><option value="0">公司商户</option></c:if>
                                            <c:if test="${tblMerInfoDO.merType == 1}"><option value="0">个体商户</option></c:if>
                                            <c:if test="${tblMerInfoDO.merType == 2}"><option value="0">无执照商户</option></c:if>
                                        </select>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">注册名:</label>
                                        <input type="text" name="regName" id="regName" value="${tblMerInfoDO.regName}" class="form-control input-nm" size="20" readonly/>
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
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">注册资本:</label>
                                        <input type="text" name="regFunds" id="regFunds" value="${tblMerInfoDO.regFunds}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">营业执照编号:</label>
                                        <input type="text" name="busLicNm" id="busLicNm" value="${tblMerInfoDO.busLicNm}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">营业执照有效期:</label>
                                        <input type="text" name="busLicExpire" id="busLicExpire" value="${tblMerInfoDO.busLicExpire}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">税务登记证:</label>
                                        <input type="text" name="taxRegCert" id="taxRegCert" value="${tblMerInfoDO.taxRegCert}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表:</label>
                                        <input type="text" name="legalPerson" id="legalPerson" value="${tblMerInfoDO.legalPerson}" readonly class="form-control input-nm" size="20" data-rule="法人代表:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件类型:</label>
                                        <select name="legalPersonCertType" id="legalPersonCertType" disabled="true" data-toggle="selectpicker" data-width="200">
                                            <option value="0">身份证</option>
                                        </select>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件号码:</label>
                                        <input type="text" name="legalPersonCertNm" id="legalPersonCertNm" value="${tblMerInfoDO.legalPersonCertNm}" readonly class="form-control input-nm" size="20" data-rule="法人证件号:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件有效期:</label>
                                        <input type="text" data-toggle="datepicker" name="legalPersonCertExpire" value="${tblMerInfoDO.legalPersonCertExpire}" readonly id="legalPersonCertExpire"  data-rule="法人代表证件有效期:required;date">
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系人:</label>
                                        <input type="text" name="contactPerson" id="contactPerson" value="${tblMerInfoDO.contactPerson}" readonly class="form-control input-nm" size="20" data-rule="联系人:required;"/>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系号码:</label>
                                        <input type="text" name="contactMobile" id="contactMobile" value="${tblMerInfoDO.contactMobile}" readonly class="form-control input-nm" size="20" data-rule="联系电话:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系邮箱:</label>
                                        <input type="text" name="contactEmail" id="contactEmail" value="${tblMerInfoDO.contactEmail}" readonly class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                            </dl>

                        </div>
                    </div>

                </div>

            </div>
            <div class="row" style="padding: 0 8px;">
            <div class="col-md-12">
                <div  class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户开户行信息</h3></div>
                    <div class="panel-body" >
                        <dl class="detail-list"  >
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">开户行:</label>
                                    <input type="text" name="bankName" id="bankName" value="${tblMerBankInfoDO.bankName}" class="form-control input-nm" size="20"   readonly/>
                                        <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">开户行省:</label>
                                    <input type="text" name="provName" id="provName" value="${tblMerBankInfoDO.provName}" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">开户行市:</label>
                                    <input type="text" name="cityName" id="cityName" value="${tblMerBankInfoDO.cityName}" class="form-control input-nm" size="20"   readonly/>
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">开户支行:</label>
                                    <input type="text" name="bankBranchName" id="bankBranchName" value="${tblMerBankInfoDO.bankBranchName}" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">联行号:</label>
                                    <input type="text" name="cnaps" id="cnaps" value="${tblMerBankInfoDO.cnaps}" class="form-control input-nm" size="20"   readonly/>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label x110">账户类型:</label>
                                    <select name="isPrivate" id="isPrivate" disabled="true" data-toggle="selectpicker" data-width="200">
                                        <c:if test="${tblMerBankInfoDO.isPrivate == 'Y'}"><option value="Y">对私</option></c:if>
                                        <c:if test="${tblMerBankInfoDO.isPrivate == 'N'}"><option value="N">对公</option></c:if>
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
            </div>
        <div class="row" style="padding: 0 8px;">
            <div class="col-md-12">
                <div  class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户费率信息</h3></div>
                    <div class="panel-body" >
                        <dl class="detail-list"  >
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">MCC码:</label>
                                    <input type="text" name="mccValue" id="mccValue" value="${tblMerRelevanceMccDo.mccValue}" readonly class="form-control input-nm" size="20" />
                                        <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                </div>
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">MCC描述:</label>
                                    <input type="text" name="mccDesp" id="mccDesp" value="${tblMerRelevanceMccDo.mccDesp}" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>

                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">借记卡T1手续费（%）:</label>
                                    <input type="text" name="fee01" id="fee01" value="${agentMerFeeForm.fee01}" readonly class="form-control input-nm" size="20" />
                                        <%--<form:input path="merInfo.merId" type="text" name="merInfo.merId" id="merId" value=""  class="form-control" size="20" ></form:input>--%>
                                </div>
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">借记卡T1-封顶（元）:</label>
                                    <input type="text" name="fee01L" id="fee01L" value="${agentMerFeeForm.fee01L}" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">贷记卡T1手续费（%）:</label>
                                    <input type="text" name="fee02" id="fee02" value="${agentMerFeeForm.fee02}" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                            
                            <dd class="row">
                            	<div class="form-group col-md-5">
                                    <label class="control-label x130">T0提现手续费（元）:</label>
                                    <input type="text" name="fee07" id="fee07" value="${agentMerFeeForm.fee07}" readonly  class="form-control input-nm" size="20" />
                                </div>
                            
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">T0垫资手续费（%）:</label>
                                    <input type="text" name="fee08" id="fee08" value="${agentMerFeeForm.fee08}" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                            
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">微信T0交易手续费（%）:</label>
                                    <input type="text" name="fee03" id="fee03" value="${agentMerFeeForm.fee03}" readonly class="form-control input-nm" size="20" />
                                </div>
                            
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">支付宝T0交易手续费（%）:</label>
                                    <input type="text" name="fee04" id="fee04" value="${agentMerFeeForm.fee04}" readonly class="form-control input-nm" size="20" />
                                </div>
                                
                            </dd>
                            
                            
                            <dd class="row">
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">微信T1交易手续费（%）:</label>
                                    <input type="text" name="fee05" id="fee05" value="${agentMerFeeForm.fee05}" readonly class="form-control input-nm" size="20" />
                                </div>
                            
                                <div class="form-group col-md-5">
                                    <label class="control-label x130">支付宝T1交易手续费（%）:</label>
                                    <input type="text" name="fee06" id="fee06" value="${agentMerFeeForm.fee06}" readonly class="form-control input-nm" size="20" />
                                </div>
                            </dd>
                         
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="padding: 0 8px;">
            <div class="col-md-12">
                <div  class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户图片详情</h3></div>
                    <div class="panel-body" >
                        <div class="row">
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">营业执照</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress1 != null}">
                                    <img id="img" onmouseover="bigger()" onmouseout="smaller()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress1}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">法人代表身份证正面</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress2 != null}">
                                    <img id="img1" onmouseover="bigger1()" onmouseout="smaller1()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress2}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">法人代表身份证反面</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress3 != null}">
                                    <img id="img2" onmouseover="bigger2()" onmouseout="smaller2()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress3}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">开户许可证复印件</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress4 != null}">
                                    <img id="img3" onmouseover="bigger3()" onmouseout="smaller3()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress4}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                        </div>
                        <div class="row">
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">组织机构代码证复印件</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress5 != null}">
                                    <img id="img4" onmouseover="bigger4()" onmouseout="smaller4()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress5}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">税务登记证复印件</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress6 != null}">
                                    <img id="img5" onmouseover="bigger5()" onmouseout="smaller5()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress6}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户门头照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress7 != null}">
                                    <img id="img6" onmouseover="bigger6()" onmouseout="smaller6()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress7}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户内部照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress8 != null}">
                                    <img id="img7" onmouseover="bigger7()" onmouseout="smaller7()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress8}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                        </div>
                        <div class="row">
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户经营产品照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress9 != null}">
                                    <img id="img8" onmouseover="bigger8()" onmouseout="smaller8()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress9}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">银行卡正面照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress10 != null}">
                                    <img id="img9" onmouseover="bigger9()" onmouseout="smaller9()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress10}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">银行卡反面照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress11 != null}">
                                    <img id="img10" onmouseover="bigger10()" onmouseout="smaller10()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress11}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">商户信息调查表</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress12 != null}">
                                    <img id="img11" onmouseover="bigger11()" onmouseout="smaller11()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress12}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                        </div>
                        <div class="row">
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">合同协议照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress13 != null}">
                                    <img id="img12" onmouseover="bigger12()" onmouseout="smaller12()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress13}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                            <div  class="col-md-3" style=" margin-bottom: 20px">
                                <div style="font-size: 14px;color:#999;text-align: center;">其他资料照片</div>
                                <span class="j_custom_span_pic" style="margin-left: 40px">
                                <c:if test="${tblMerFileInfoDO.picAddress15 != null}">
                                    <img id="img13" onmouseover="bigger13()" onmouseout="smaller13()" src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress15}" width="150" height="150">
                                </c:if>
                            </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </form:form>
</div>
</div>
</body>
</html>
