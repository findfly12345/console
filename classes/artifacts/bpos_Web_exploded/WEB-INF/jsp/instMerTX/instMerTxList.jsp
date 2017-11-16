<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构提现</title>
    <!-- 机构提现 -->
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="outcomerecorddo" data-toggle="ajaxsearch"
          action="${pageContext.request.contextPath}/instMerTX/queryInstMerTxList" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${outcomerecorddo.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${outcomerecorddo.pageCurrent}">
        <div class="bjui-searchBar">

            <label>翰银商户：</label>
            <input type="text" name="channelReturnResv2" id="channelReturnResv2"
                   value="${outcomerecorddo.channelReturnResv2}" size="20">&nbsp;
            <label>商户号：</label>
            <input type="text" name="merId" id="merId" value="${outcomerecorddo.merId}" size="15">&nbsp;
            <label>订单号：</label>
            <input type="text" name="merTransId" id="merTransId" value="${outcomerecorddo.merTransId}" size="15">&nbsp;
            <label>交易日期：</label>
            <input name="merDate" data-toggle="datepicker" value="${outcomerecorddo.merDate}" class="form-control" size="15" data-pattern="yyyyMMdd">
            <label>提现状态：</label>
            <select path="channelReturnCode"  name="channelReturnCode"  data-toggle="selectpicker" >
                <option value="">请选择</option>
                <option <c:if test="${outcomerecorddo.channelReturnCode=='FF'}">selected="selected"</c:if>  value="FF">交易失败</option>
                <option <c:if test="${outcomerecorddo.channelReturnCode=='00'}">selected="selected"</c:if>  value="00">成功</option>
                <option <c:if test="${outcomerecorddo.channelReturnCode=='Z5'}">selected="selected"</c:if>  value="Z5">交易处理中</option>
                <option <c:if test="${outcomerecorddo.channelReturnCode=='R0'}">selected="selected"</c:if>  value="R0">风控受限</option>
            </select>
            <%-- <input type="text"  id="channelReturnCode" name="channelReturnCode" value="${outcomerecorddo.channelReturnCode}"  >--%>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%"
           data-nowrap="true">
        <thead>
        <tr>
            <th align="center">翰银商户</th>
            <th align="center">商户名</th>
            <th align="center">交易日期</th>
            <th align="center">提现时间</th>
            <th align="center">订单号</th>
            <th align="center">提现金额</th>
            <th align="center">提现到账金额</th>
            <th align="center">提现状态</th>
            <th align="center">提现状态描述</th>
            <th align="center"> 操作
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${outcomerecorddo.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.merId}"/>">
                <td align="center"><c:out value="${record.channelReturnResv2}"/></td>
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center"><c:out value="${record.merDate}"/></td>
                <td align="center"><c:out value="${record.recvTime}"/></td>
                <td align="center"><c:out value="${record.merTransId}"/></td>
                <td align="center">
                    <c:if test="${record.transAmt == null || record.transAmt == ''}"></c:if>
               <c:if test="${record.transAmt != '' && record.transAmt != null}">    <c:out value="${record.transAmt / 100}"/></c:if>
                </td>
                <td align="center"><c:out value="${record.channelReturnResv1 / 100}"/></td>
                <td align="center"><c:out value="${record.channelReturnCode}"/></td>
                <td align="center"><c:out value="${record.channelReturnMessage}"/></td>

                <td align="center">

                        <%--民生通道--%>
                    <input type="button"
                           href="${pageContext.request.contextPath}/instMerTX/hanYin_tx?txId=${record.id}"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.transStatus == 'S'}"> disabled=true; value="已经提现" </c:if> value="提现">
                    </input>
                </td>
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
        <span> 条，共 <c:out value="${outcomerecorddo.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination"
         data-total="<c:out value="${outcomerecorddo.pagination.itemCount}"/>"
         data-page-size="<c:out value="${outcomerecorddo.pageSize}"/>"
         data-page-current="<c:out value="${outcomerecorddo.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
