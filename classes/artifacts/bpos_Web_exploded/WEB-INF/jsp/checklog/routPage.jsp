<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>对帐任务展示</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/checklog/search" method="post"
			modelAttribute="rout">
			<input type="hidden" name="pageSize"
				value="${transRateCountForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${transRateCountForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>网关号：</label>
				<form:input path="gateId" class="form-control" size="15" />
				<label>对帐日期：</label>
				<form:input path="bankCheckDate" class="form-control" size="15" data-toggle="datepicker" data-pattern="yyyyMMdd"/>
				<button type="submit" class="btn-default" data-icon="search">查询</button>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">网关号</th>
      <th align="center">对帐日期</th>
      <th align="center">对帐结果</th>
      <th align="center">对帐次数</th>
      <th align="center">最后一次对帐时间</th>
      <th align="center">编辑</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${pageUser.list}" varStatus="status">
<tr data-id="<c:out value="${record.gateId}"/>">
      <td align="center"><c:out value="${record.gateId}"/></td>
      <td align="center"><c:out value="${record.bankCheckDate}"/></td>
      <td align="center">${record.bankCheckResult == 'S'?'成功':''}
      ${record.bankCheckResult == 'F'?'失败':''}
      ${record.bankCheckResult == 'I'?'对帐中...':''}</td>
      <td align="center"><c:out value="${record.bankCheckTimes}"/></td>
      <td align="center"><fmt:parseDate value='${record.lastBankCheckTime}' var='lastcheckdatatime' pattern='yyyyMMddHHmmss'/><fmt:formatDate value='${lastcheckdatatime}'  pattern='yyyy-MM-dd HH:mm:ss' />
     </td>
      <td align="center">
		<a class="btn btn-red" data-confirm-msg="确定要重新对帐吗？" data-toggle="doajax" href="checklog/delete/${record.gateId}/${record.bankCheckDate}">重新对帐</a></td>
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
    <span> 条，共 <c:out value="${pageUser.itemCount}"/> 条</span>
  </div>
  <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${pageUser.itemCount}"/>" data-page-size="<c:out value="${pageUser.pageSize}"/>" data-page-current="<c:out value="${pageUser.pageIndex}"/>">
  </div>
</div>
</body>
</html>
