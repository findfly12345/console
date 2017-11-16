<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/18
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>渠道维护</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="channelForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/CM/maintenance_the_channel" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${channelForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${channelForm.pageCurrent}">
        <div class="bjui-searchBar">
            <a href="${pageContext.request.contextPath}/CM/edit_channel_info" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="400" data-id="dialog-normal-inst" data-title="新增">新增</a>&nbsp;
            <label>网关：</label>
            <input type="text" name="gateId" id="gateId" value="${channelForm.gateId}"  maxlength="2" size="4">&nbsp;
            <label>商户号：</label>
            <input type="text" name="posMerId" id="posMerId" value="${channelForm.posMerId}"  size="15">&nbsp;
            <label>终端号：</label>
            <input type="text" name="posTermId" id="posTermId" value="${channelForm.posTermId}"  size="15">&nbsp;

            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a href="${pageContext.request.contextPath}/CM/add_channel_info_batch" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="420" data-id="dialog-normal-batch-key" data-title="导入">批量导入</a>&nbsp;

        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">网关号</th>
            <th align="center">商户号</th>
            <th align="center">终端号</th>
            <th align="center">批次号</th>
            <th align="center">机构号</th>
            <th align="center">机构名称</th>
            <%--<th align="center">主密钥</th>--%>
            <%--<th align="center">MAK KEY</th>--%>
            <%--<th align="center">PIN</th>--%>
            <%--<th align="center">TD</th>--%>
            <th align="center">操作</th>
            <th align="center"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${channelForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.gateId}"/>">
                <td align="center"><c:out value="${record.gateId}"/></td>
                <td align="center"><c:out value="${record.posMerId}"/></td>
                <td align="center"><c:out value="${record.posTermId}"/></td>
                <td align="center"><c:out value="${record.batchId}"/></td>
                <td align="center"><c:out value="${record.instId}"/></td>
                <td align="center"><c:out value="${record.instName}"/></td>
                <%--<td align="center"><c:out value="${record.mainKey}"/></td>--%>
                <%--<td align="center"><c:out value="${record.macKey}"/></td>--%>
                <%--<td align="center"><c:out value="${record.pinKey}"/></td>--%>
                <%--<td align="center"><c:out value="${record.tdKey}"/></td>--%>


                <td align="center">
                    <a href="${pageContext.request.contextPath}/CM/edit_inst_mer?id=<c:out value="${record.gateId}"/>&mId=<c:out value="${record.posMerId}"/>&tId=<c:out value="${record.posTermId}"/>" class="btn btn-green"data-toggle="doajax"  data-confirm-msg="确定签到吗？">
                        <c:if test="${record.mainKey==null}">点击签到</c:if>
                        <c:if test="${record.mainKey!=null}">重新签到</c:if>
                        </a>
                    <a href="${pageContext.request.contextPath}/CM/edit_channel_info?id=<c:out value="${record.gateId}"/>&mId=<c:out value="${record.posMerId}"/>&tId=<c:out value="${record.posTermId}"/>" class="btn btn-red" data-toggle="dialog" data-width="400" data-height="400" data-id="dialog-normal-modify"  data-title="修改渠道信息">修改</a>
                    <a href="${pageContext.request.contextPath}/CM/delete_channel?id=<c:out value="${record.gateId}"/>&mId=<c:out value="${record.posMerId}"/>&tId=<c:out value="${record.posTermId}"/>" class="btn btn-red" data-toggle="doajax"  data-confirm-msg="确定删除吗？">删除</a>
                </td>
                <td><font  color="blue" id="${record.instId}"><c:if test="${record.mainKey!=null}">已经签到</c:if></font></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter" id="pageFooter">
    <div class="pages">
        <span>每页 </span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="10">10</option>
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="100">100</option>
            </select>
        </div>
        <span> 条，共 <c:out value="${channelForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${channelForm.pagination.itemCount}"/>" data-page-size="<c:out value="${channelForm.pageSize}"/>" data-page-current="<c:out value="${channelForm.pageCurrent}"/>">
    </div>
</div>

</body>
</html>
