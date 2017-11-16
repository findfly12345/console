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
    <title>交易信息查询</title>
            <script language="javascript">
                  function prtReport() {
                    settlementInfoForm.action = '${pageContext.request.contextPath}/TC/export_settlement_info_report';
                    settlementInfoForm.submit();
                    settlementInfoForm.action = '${pageContext.request.contextPath}/TC/query_settlement_info_file_list';
                    }
            </script>
</head>
<body>
<div class="bjui-pageHeader">
 <form id="pagerForm" name="tradeListForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/TC/query_settlement_info_file_list" method="post">
    <input type="hidden" name="pageSize" value="${tradeListForm.pageSize}">
    <input type="hidden" name="pageCurrent" value="${tradeListForm.pageCurrent}">
    <div class="bjui-searchBar">
        <label>发送日期起：</label>
        <input type="text" name="sendDateStart" id="sendDate" value="${tradeListForm.sendDateStart}" data-toggle="datepicker" size="15">&nbsp;
      <label>发送日期至：</label>
      <input type="text" name="sendDateEnd" id="recvDate" value="${tradeListForm.sendDateEnd}" data-toggle="datepicker" size="15">&nbsp;
      <label>交易状态：</label>
      <select name="transStatus" id="transStatus" data-toggle="selectpicker">
        <option value="">请选择</option>
        <c:choose>
             <c:when test="${tradeListForm.transStatus=='S'}">
                <option selected="selected" value="S">成功（S）</option>
                <option value="F">失败（F）</option>
             </c:when>
             <c:when test="${settlementInfoForm.transStatus=='F'}">
                <option value="S">成功（S）</option>
                <option selected="selected" value="F">失败（F）</option>
             </c:when>
             <c:otherwise>
               <option value="S">成功（S）</option>
               <option value="F">失败（F）</option>
             </c:otherwise>
        </c:choose>
      </select>&nbsp;
      <label>卡号：</label>
      <input type="text" name="acctNo" id="acctNo" value="${tradeListForm.acctNo}"  size="15">&nbsp;
      <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="batchInfo"><i class="fa fa-angle-double-down"></i></button>
      <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
      <button type="button" class="btn-default" data-icon="search" onclick="prtReport();">导出</button>&nbsp;
     </div>
    <div class="bjui-moreSearch">
      <label>结算流水号：</label><input type="text" value="${tradeListForm.id}" name="id" size="15" />&nbsp;
      <label>宝付订单号：</label><input type="text" value="${tradeListForm.orderNumber}" name="orderNumber" size="15" />
    </div>
  </form>
</div>
<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="150%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center"><div class="fixedtableCol">支付订单号</div></th>
      <th align="center"><div class="fixedtableCol">交易类型</div></th>
      <th align="center"><div class="fixedtableCol">参考号</div></th>
      <th align="center"><div class="fixedtableCol">交易金额(元)</div></th>
      <th align="center"><div class="fixedtableCol">交易时间</div></th>
      <th align="center"><div class="fixedtableCol">商户名称</div></th>
      <th align="center"><div class="fixedtableCol">交易状态</div></th>
      <th align="center"><div class="fixedtableCol">卡号</div></th>
      <th align="center"><div class="fixedtableCol">手续费(元)</div></th>
      <th align="center"><div class="fixedtableCol">清算日期</div></th>
      <!-- <th align="center">操作</th> -->
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${settlementInfoForm.pagination.list}" varStatus="status">
<tr data-id="<c:out value="${record.outcomeId}"/>">
      <td align="center"><c:out value="${record.outcomeId}"/></td>
      <td align="center"><c:out value="${record.recvDate}"/></td>
      <td align="center"><c:out value="${record.recvTime}"/></td>
      <td align="center"><c:out value="${record.transAmt}"/></td>
      <td align="center"><c:out value="${record.transStatus}"/></td>
      <td align="center"><c:out value="${record.acctNo}"/></td>
      <td align="center"><c:out value="${record.acctName}"/></td>
      <td align="center"><c:out value="${record.sendDate}"/></td>
      <td align="center"><c:out value="${record.sendTime}"/></td>
      <td align="center"><c:out value="${record.channelReturnCode}"/></td>
      <td align="center"><c:out value="${record.channelReturnMsg}"/></td>
      <td align="center"><c:out value="${record.channelReturnSeq1}"/></td>
      <td align="center"><c:out value="${record.channelReturnSeq2}"/></td>
      <td align="center"><c:out value="${record.channelCheckFlag}"/></td>
      <td align="center"><c:out value="${record.channelReturnDate}"/></td>
      <td align="center"><c:out value="${record.channelReturnTime}"/></td>
      <td align="center"><c:out value="${record.channelBatchId}"/></td>
      <td align="center"><c:out value="${record.branchId}"/></td>
      <td align="center"><c:out value="${record.rate}"/></td>
      <td align="center"><c:out value="${record.poundage}"/></td>
     <!-- <td align="center">
        <a href="${pageContext.request.contextPath}/SM/settlement_file_detail?id=<c:out value="${record.outcomeId}"/>" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-normal" data-title="结算明细">明细</a>
      </td> -->
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
    <span> 条，共 <c:out value="${tradeListForm.pagination.itemCount}"/> 条</span>
  </div>
  <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${tradeListForm.pagination.itemCount}"/>" data-page-size="<c:out value="${tradeListForm.pageSize}"/>" data-page-current="<c:out value="${tradeListForm.pageCurrent}"/>">
  </div>
</div>

</div>

</body>
</html>
