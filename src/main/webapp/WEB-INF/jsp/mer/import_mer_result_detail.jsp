<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-nowrap="true">
        <c:if test="${resultList!=null}">
        <thead>
        <tr>
            <th>批次号</th>
            <th>机构号</th>
            <th>机构商户号</th>
            <th>机构终端号</th>
            <th>平台商户号</th>
            <th>平台终端号</th>
            <th>处理结果</th>
            <th>详细描述</th>
            <th>操作员</th>
            <th>更新时间</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${resultList}" var="result">
            <tr>
                <td>${result.batchNo}</td>
                <td>${result.instId}</td>
                <td>${result.instMerId}</td>
                <td>${result.instTermId}</td>
                <td>${result.merId}</td>
                <td>${result.termId}</td>
                <td>${result.resultFlag}</td>
                <td>${result.resultDesc}</td>
                <td>${result.userName}</td>
                <td>${result.updateTime}</td>
                <td>${result.createTime}</td>
            </tr>
            </c:forEach>
        </tbody>
        </c:if>

    </table>
</div>