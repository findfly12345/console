<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>商户基本信息修改</title>
</head>
<body>
	<div class="bjui-pageContent">
		<form:form class="nice-validator n-red" data-alertmsg="false"
			data-toggle="validate" action="mer/update_quick_mer_base_info" novalidate="novalidate"
			modelAttribute="merInfoForm" id="merInfoForm">
			
			<table class="table table-condensed table-hover" width="100%">
                       <div class="panel-body">
                            <dl class="detail-list"  >
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户号:</label>
                                        <input type="text" name="merId" id="merId" value="${tblMerInfoDO.merId}"  class="form-control input-nm" size="20"
                                               data-rule="商户号:required;" />                                                                               </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户名称:</label>
                                        <input type="text" name="merName" id="merName" value="${tblMerInfoDO.merName}"  class="form-control input-nm" size="20" data-rule="商户名称:required;" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">商户类型:</label>
                                        <select name="merType" id="merType" data-toggle="selectpicker" data-width="200" >
                                            <option value="0" <c:if test="${tblMerInfoDO.merType == 0}">selected</c:if> >公司商户</option>
                                            <option value="0" <c:if test="${tblMerInfoDO.merType == 1}">selected</c:if> >个体商户(有执照)</option>
                                            <option value="0" <c:if test="${tblMerInfoDO.merType == 2}">selected</c:if> >无执照商户</option>
                                        </select>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">注册名:</label>
                                        <input type="text" name="regName" id="regName" value="${tblMerInfoDO.regName}"  class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">注册简称:</label>
                                        <input type="text" name="regShortName" id="regShortName" value="${tblMerInfoDO.regShortName}"  class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">注册地址:</label>
                                        <input type="text" name="merAddress" id="merAddress" value="${tblMerInfoDO.merAddress}"  class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                
                                <dd class="row">
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">省市编码:</label>
                                        <input type="text" name="provinceCode" id="provinceCode" value="${CustAddressDo.provinceCode}"  class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">城市编码:</label>
                                        <input type="text" name="cityCode" id="cityCode" value="${CustAddressDo.cityCode}"  class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label x110">地区编码:</label>
                                        <input type="text" name="areaCode" id="areaCode" value="${CustAddressDo.areaCode}"  class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                                
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">营业执照编号:</label>
                                        <input type="text" name="busLicNm" id="busLicNm" value="${tblMerInfoDO.busLicNm}"  class="form-control input-nm" size="20" />
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">营业执照有效期:</label>
                                        <input type="text" data-toggle="datepicker" name="busLicExpire" id="busLicExpire"   value="${tblMerInfoDO.busLicExpire}" data-rule="营业执照有效期:required;date"  />
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表:</label>
                                        <input type="text" name="legalPerson" id="legalPerson" value="${tblMerInfoDO.legalPerson}"  class="form-control input-nm" size="20" data-rule="法人代表:required;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件类型:</label>
                                        <select name="legalPersonCertType" id="legalPersonCertType"  data-toggle="selectpicker"  data-width="200">
                                            <option value="0">身份证</option>
                                        </select>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件号码:</label>
                                        <input type="text" name="legalPersonCertNm" id="legalPersonCertNm"  value="${tblMerInfoDO.legalPersonCertNm}"  class="form-control input-nm" size="20" data-rule="法人证件号:required ID_card;"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">法人代表证件有效期:</label>
                                        <input type="text" data-toggle="datepicker" name="legalPersonCertExpire"  value="${tblMerInfoDO.legalPersonCertExpire}"  id="legalPersonCertExpire"  data-rule="法人代表证件有效期:required;date">
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系人:</label>
                                        <input type="text" name="contactPerson" id="contactPerson"  value="${tblMerInfoDO.contactPerson}"  class="form-control input-nm" size="20" data-rule="联系人:required;"/>
                                    </div>
                                </dd>
                                <dd class="row">
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系号码:</label>
                                        <input type="text" name="contactMobile" id="contactMobile"  value="${tblMerInfoDO.contactMobile}"  class="form-control input-nm" size="20" data-rule="联系电话:required mobile"/>
                                    </div>
                                    <div class="form-group  col-md-4">
                                        <label class="control-label x110">联系邮箱:</label>
                                        <input type="text" name="contactEmail" id="contactEmail"  value="${tblMerInfoDO.contactEmail}"  class="form-control input-nm" size="20" />
                                    </div>
                                </dd>
                            </dl>

                        </div>
			</table>
		</form:form>
	</div>
	<div class="bjui-pageFooter">
		<ul>
			<li><button type="button" class="btn-close">关闭</button></li>
			<li><button type="submit" class="btn-default">保存</button></li>
		</ul>
	</div>
</body>
</html>
