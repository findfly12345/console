<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构汇总展示</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/instsum/search" method="post"
			modelAttribute="rout">
			<input type="hidden" name="pageSize"
				value="${transRateCountForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${transRateCountForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>机构号：</label>
				<form:input path="instId" class="form-control" size="15" />
				<label>交易日期：</label>
				<form:input path="accountingDate" class="form-control" size="15" data-toggle="datepicker" data-pattern="yyyyMMdd"/>
				<button type="submit" class="btn-default" data-icon="search">查询</button>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">机构号</th>
      <th align="center">交易日期</th>
      <th align="center">交易金额（元）</th>
      <th align="center">手续费金额（元）</th>
      <th align="center">编辑</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${pageUser.list}" varStatus="status">
<tr data-id="<c:out value="${record.instId}"/>">
      <td align="center"><c:out value="${record.instId}"/></td>
      <td align="center"><c:out value="${record.accountingDate}"/></td>
      <td align="center"><c:out value="${record.transSumAmt}"/></td>
      <td align="center"><c:out value="${record.transFeeSumAmt}"/></td>
      <td align="center">
		<a class="btn btn-red" data-confirm-msg="确定重新汇总吗？" data-toggle="doajax" href="instsum/delete/${record.instId}/${record.accountingDate}">重新汇总</a></td>
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
