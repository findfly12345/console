<%--
  Created by IntelliJ IDEA.
  User: liuBin
  Date: 2017/1/5
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>无卡对账文件获取</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form:form id="pagerForm" name="checkFileForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/checkWithOutCard/get_channel_check_file" method="post"
               modelAttribute="checkFileForm">
        <div class="bjui-searchBar">
            <form:input type="hidden" path="pageSize" />
            <form:input type="hidden" path="pageCurrent" />
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85">交易日期</label>
                <form:input type="text" path="filterDate"
                            size="15" class="form-control" data-toggle="datepicker"  data-rule="交易日期:required;"></form:input>
            </div>
            <%--<div class="form-group" style="margin: 20px 0 20px; ">--%>
                <%--<label class="control-label x85"> 渠道名称：</label>--%>
                <%--<div style="display: inline-block; vertical-align: middle;">--%>
                    <%--<div style="display: inline-block; vertical-align: middle;">--%>
                        <%--<select id="gateId" name="gateId" data-toggle="selectpicker" data-width="200" data-rule="渠道名称:required">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<option value="U1">钱宝</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="form-group" style="margin: 20px 0 20px; ">
                <button type="submit" class="btn-default" data-icon="save">获取文件</button>&nbsp;
            </div>
        </div>
    </form:form>

</div>
<div class="bjui-pageContent tableContent">
    <c:if test="${tblMsCheckFileDoList!=null}">
        <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
            <thead>
            <tr>
                <th align="center">对账日期</th>
                <th align="center">平台流水号</th>
                <th align="center">流水类型</th>
                <th align="center">交易金额</th>
                <th align="center">合作方手续费</th>
                <th align="center">商户手续费</th>
                <th align="center">支付方式</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tblMsCheckFileDo" items="${tblMsCheckFileDoList}">
                <tr>
                    <td align="center">${tblMsCheckFileDo.settledate}</td>
                    <td align="center">${tblMsCheckFileDo.reqmsgid}</td>
                    <c:if test="${tblMsCheckFileDo.transactiontype == '1001'}">
                        <td align="center">支付</td>
                    </c:if>
                    <c:if test="${tblMsCheckFileDo.transactiontype == '1002'}">
                        <td align="center">退款</td>
                    </c:if>
                    <c:if test="${tblMsCheckFileDo.transactiontype == '1003'}">
                        <td align="center">撤销</td>
                    </c:if>
                    <td align="center">${tblMsCheckFileDo.amount}</td>
                    <td align="center">${tblMsCheckFileDo.fee}</td>
                    <td align="center">${tblMsCheckFileDo.shfee}</td>
                    <c:if test="${tblMsCheckFileDo.payway == 'ZFBZF'}">
                        <td align="center">支付宝支付</td>
                    </c:if>
                    <c:if test="${tblMsCheckFileDo.payway == 'WXZF'}">
                        <td align="center">微信支付</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<div class="bjui-pageFooter" id="pageFooter">
    <div class="pages">
        <span>每页 </span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker"
                    data-toggle-change="changepagesize">
                <option value="10">10</option>
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="100">100</option>
            </select>
        </div>
        <span> 条，共 <c:out value="${pageUser.itemCount}" /> 条 </span>
    </div>
    <div class="pagination-box" data-toggle="pagination"
         data-total="<c:out value="${pageUser.itemCount}"/>"
         data-page-size="<c:out value="${pageUser.pageSize}"/>"
         data-page-current="<c:out value="${pageUser.pageIndex}"/>"></div>
</div>
</body>
</html>

