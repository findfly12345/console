<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-nowrap="true">
        <c:if test="${resultList!=null}">
            <thead>
            <tr>
                <th align="center">订单号</th>
                <th align="center">交易类型</th>
                <th align="center">参考号</th>
                <th align="center">交易时间</th>
                <th align="center">金额</th>
                <th align="center">机构号</th>
                <th align="center">交易状态</th>
                <th align="center">卡号</th>
                <th align="center">手续费</th>
                <th align="center">清算日期</th>
                <th align="center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${resultList}" varStatus="status">
            <tr>
                <td align="center"><c:out value="${record.ordId}" /></td>
                <td align="center"><c:out value="${record.transType}" /></td>
                <td align="center"><c:out value="${record.sysSeqId}" /></td>
                <td align="center"><c:out value="${record.acctDate}${record.sysTime}" /></td>
                <td align="center"><c:out value="${record.ordAmt}" /></td>
                <td align="center"><c:out value="${record.acqInstIdCode}" /></td>
                <td align="center"><c:out value="${record.transStat}" /></td>
                <td align="center"><c:out value="${record.cardNo}" /></td>
                <td align="center"><c:out value="${record.feeAmt}" /></td>
                <td align="center"><c:out value="${record.acctDate}" /></td>
                <th align="center">
                    <c:if test="${record.chkFlag=='I'}">
                        <a type="button" class="btn btn-green" href="${pageContext.request.contextPath}/check/u1_manual_check?manualCheckStat=S&ordId=${record.ordId}&posSeqId=${record.posSeqId}&refNum=${record.refNum}" data-toggle="doajax" data-confirm-msg="确定要勾兑成功吗？">勾兑成功</a>
                        <a type="button" class="btn btn-green" href="${pageContext.request.contextPath}/check/u1_manual_check?manualCheckStat=F&ordId=${record.ordId}&posSeqId=${record.posSeqId}&refNum=${record.refNum}" data-toggle="doajax" data-confirm-msg="确定要勾兑失败吗？">勾兑失败</a>
                    </c:if>
                </th>
                </c:forEach>
            </tbody>
        </c:if>

    </table>
</div>