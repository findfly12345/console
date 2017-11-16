<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-nowrap="true">
        <c:if test="${resultList!=null}">
        <thead>
        <tr>
            <th>文件编号</th>
            <th>商户号</th>
            <th>终端号</th>
            <th>交易日期</th>
            <th>交易时间</th>
            <th>交易类型</th>
            <th>交易卡号</th>
            <th>交易金额</th>
            <th>终端流水号</th>
            <th>参考号</th>
            <th>授权码</th>
            <th>操作员</th>
            <th>更新时间</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${resultList}" var="result">
            <tr>
                <td>${result.fileNo}</td>
                <td>${result.mercno}</td>
                <td>${result.termid}</td>
                <td>${result.sysdate}</td>
                <td>${result.loctime}</td>
                <td>${result.trntyp}</td>
                <td>${result.priacno}</td>
                <td>${result.tranamt}</td>
                <td>${result.termtrc}</td>
                <td>${result.refnum}</td>
                <td>${result.authid}</td>
                <td>${result.userName}</td>
                <td>${result.updateTime}</td>
                <td>${result.createTime}</td>
            </tr>
            </c:forEach>
        </tbody>
        </c:if>

    </table>
</div>