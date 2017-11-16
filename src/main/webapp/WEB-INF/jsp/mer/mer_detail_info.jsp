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
    <%java.util.Date date=new java.util.Date();%>

        <div style="margin:15px auto 0; width:96%;">
            <div class="row" style="padding: 0 8px;">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">商户基础信息</h3></div>
                        <div class="panel-body">
                            <dl class="detail-list"  >
                                <dd>
                                    <div class="form-group">
                                        <label>商户号:</label>
                                        <span class="form-text">${merInfo.merId}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>商户名称:</label>
                                        <span class="form-text">${merInfo.merName}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>注册名称:</label>
                                        <span class="form-text">${merInfo.regName}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>注册简称:</label>
                                        <span class="form-text">${merInfo.regShortName}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>商户状态:</label>
                                        <span class="form-text">${merInfo.merStat}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>功能状态:</label>
                                        <span class="form-text">${merInfo.funcStat}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>商户类型:</label>
                                        <span class="form-text">${merInfo.merType}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>注册地址:</label>
                                        <span class="form-text">${merInfo.merAddress}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>注册资金:</label>
                                        <span class="form-text">${merInfo.regFunds}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>营业执照编号:</label>
                                        <span class="form-text">${merInfo.busLicNm}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>营业执照有效期:</label>
                                        <span class="form-text">${merInfo.busLicExpire}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>税务登记证:</label>
                                        <span class="form-text">${merInfo.taxRegCert}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>法人代表:</label>
                                        <span class="form-text">${merInfo.legalPerson}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>法人证件类型:</label>
                                        <span class="form-text">${merInfo.legalPersonCertType}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>法人证件号:</label>
                                        <span class="form-text">${merInfo.legalPersonCertNm}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>法人证件有效期:</label>
                                        <span class="form-text">${merInfo.legalPersonCertExpire}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>联系人:</label>
                                        <span class="form-text">${merInfo.contactPerson}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>联系电话:</label>
                                        <span class="form-text">${merInfo.contactMobile}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>联系邮箱:</label>
                                        <span class="form-text">${merInfo.contactEmail}</span>
                                    </div>
                                    <div class="form-group">
                                        <label>创建时间:</label>
                                        <span class="form-text">${merInfo.userName}</span>
                                    </div>

                                </dd>
                            </dl>

                        </div>
                    </div>

                </div>

            </div>
            <div class="col-md-12">
                <div  class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户结算信息</h3></div>
                    <div class="panel-body" >
                        <dl class="detail-list">
                            <dd>
                                <div class="form-group">
                                    <label>省名称:</label>
                                    <span class="form-text">${merInfo.provName}</span>
                                </div>
                                <div class="form-group">
                                    <label>城市名称:</label>
                                    <span class="form-text">${merInfo.cityName}</span>
                                </div>
                                <div class="form-group">
                                    <label>总行名称:</label>
                                    <span class="form-text">${merInfo.bankName}</span>
                                </div>
                                <div class="form-group">
                                    <label>联行号:</label>
                                    <span class="form-text">${merInfo.cnaps}</span>
                                </div>
                                <div class="form-group">
                                    <label>支行名称:</label>
                                    <span class="form-text">${merInfo.bankBranchName}</span>
                                </div>
                                <div class="form-group">
                                    <label>结算类型:</label>
                                    <span class="form-text">${merInfo.isPrivate}</span>
                                </div>
                                <div class="form-group">
                                    <label>账户名:</label>
                                    <span class="form-text">${merInfo.acctName}</span>
                                </div>
                                <div class="form-group">
                                    <label>账户号:</label>
                                    <span class="form-text">${merInfo.acctNo}</span>
                                </div>
                                <div class="form-group">
                                    <a href="${pageContext.request.contextPath}/mer/query_mer_bank?id=${merInfo.merId}" class="btn btn-green" data-toggle="dialog" data-width="600" data-height="450" data-id="dialog-normal-modify"   data-title="商户结算信息(${record.merName})">编辑</a>
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
                            <dd>
                                <c:forEach var="record" items="${merInfo.merFeeList}" varStatus="status">
                                    <div class="form-group">
                                        <label>${record.feeDesc}</label>
                                        <span class="form-text">${record.feeRate}</span>
                                    </div>
                                </c:forEach>
                            </dd>
                            <div class="form-group">
                                <a href="${pageContext.request.contextPath}/mer/query_mer_fee?id=${merInfo.merId}" class="btn btn-green" data-toggle="navtab" data-width="800" data-height="400" data-id="form-normal-modify"   data-title="商户结算信息(${merInfo.merName})">编辑</a>
                            </div>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading"><h3 class="panel-title">商户附件</h3></div>
            <div class="panel-body">
                <c:forEach var="record" items="${tblMerFileInfoDOList}" varStatus="status">
                    <dl class="dll">
                       <%--  <dt>
                            <span class="form-text">${record.picAddress1}</span>
                        <dt> --%>
                        <dd>
                        <img src="${record.picAddress1}" width="22.5%" height="22.5%">
                        </dd>

                    </dl>
                </c:forEach>

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
</body>

</html>
