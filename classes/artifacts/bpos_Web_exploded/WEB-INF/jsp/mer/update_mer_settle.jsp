<%--
        Created by IntelliJ IDEA.
        User: fireWorks
        Date: 2016/3/14
        Time: 19:27
        To change this template use File | Settings | File Templates.
        --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户详细信息</title>
</head>
<div class="bjui-pageContent">
    <form id="pagerForm" name="merBankInfoForm" data-toggle="validate" novalidate="novalidate"
          action="${pageContext.request.contextPath}/mer/update_mer_bank" method="post">
        <div style="margin:15px auto 0; width:96%;">
            <div class="row" style="padding: 0 8px;">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">商户结算信息</h3></div>
                        <div class="panel-body">
                            <dl class="detail-list">
                                <dd>
                                    <input type="hidden" id="merId" value="${merBankInfoForm.merId}" name="merId" class="form-control">
                                    <div class="form-group">
                                        <label>开户行所在地:</label>
                                        <select name="provName" id="provName" data-toggle="selectpicker"
                                                data-nextselect="#cityName"
                                                data-refurl="${pageContext.request.contextPath}/mer/query_city_by_prov?provinceId={value}"
                                                style="width: 134px">
                                            <option value="">-省份--</option>
                                            <c:forEach var="record" items="${merBankInfoForm.bankProvNameList}"
                                                       varStatus="status">
                                                <option value="${record.provinceId}"
                                                        <c:if test="${record.provinceName == merBankInfoForm.provName}">selected</c:if> >${record.provinceName}</option>
                                            </c:forEach>
                                        </select>
                                        <select id="cityName" name="cityName" data-toggle="selectpicker"
                                                style="width: 134px">
                                            <option value="${merBankInfoForm.cityName}"
                                                    selected>${merBankInfoForm.cityName}</option>
                                        </select><font color="red">*</font>
                                    </div>
                                    <div class="form-group">
                                        <label>开户行:</label>
                                        <input class="form-text" type="text" name="bankName" id="bankName"
                                               value="${merBankInfoForm.bankName}">
                                       <%-- <select class="form-unit" id="bankName" name="bankName">
                                            <option value="ONSELECT">请选择</option>
                                            <c:forEach var="record" items="${merBankInfoForm.bankIdList}"
                                                       varStatus="status">
                                                <option
                                                        <c:if test="${merBankInfoForm.bankName==record}">selected </c:if>>${record}</option>
                                            </c:forEach>
                                        </select>--%>
                                    </div>
                                    <div class="form-group">
                                        <label>联行号:</label>
                                        <input class="form-text" type="text" name="cnaps" id="cnaps"
                                               value="${merBankInfoForm.cnaps}">
                                    </div>
                                    <div class="form-group">
                                        <label>支行名称:</label>
                                        <input class="form-text" type="text" name="bankBranchName" id="bankBranchName"
                                               value="${merBankInfoForm.bankBranchName}">
                                    </div>
                                    <div class="form-group">
                                        <label>结算类型:</label>
                                        <select class="form-unit" id="isPrivate" name="isPrivate">
                                            <option value="ONSELECT">请选择</option>
                                            <option value="Y" <c:if test="${merBankInfoForm.isPrivate == 'Y'}">selected</c:if>>对私</option>
                                            <option value="N" <c:if test="${merBankInfoForm.isPrivate == 'N'}">selected</c:if>>对公</option>
                                        </select>
                                        <%--<input class="form-text" type="text" name="isPrivate" id="isPrivate"--%>
                                               <%--value="${merBankInfoForm.isPrivate}">--%>
                                    </div>
                                    <div class="form-group">
                                        <label>账户名:</label>
                                        <input class="form-text" type="text" name="acctName" id="acctName"
                                               value="${merBankInfoForm.acctName}">
                                    </div>
                                    <div class="form-group">
                                        <label>账户号:</label>
                                        <input class="form-text" type="text" name="acctNo" id="acctNo"
                                               value="${merBankInfoForm.acctNo}">
                                    </div>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </form>
</div>


</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close" data-icon="close">关闭</button>
        </li>
        <li>
            <button type="submit" class="btn-default">保存</button>
        </li>
    </ul>
</div>
</body>
</html>
