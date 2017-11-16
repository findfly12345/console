<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>对帐数据展示</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/checkdata/search" method="post"
			modelAttribute="rout">
			<input type="hidden" name="pageSize"
				value="${transRateCountForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${transRateCountForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>网关：</label>
				<form:input path="gateId" class="form-control" size="15" />
				<label>流水：</label>
				<form:input path="seqId" class="form-control" size="15" />
				<label>交易日期：</label>
				<form:input path="transDate" class="form-control" size="15" data-toggle="datepicker" data-pattern="yyyyMMdd"/>
				<label>交易状态：</label>
				<form:select  path="transStatus" data-toggle="selectpicker">
						<form:option value="">请选择</form:option>
						<form:option value="S">成功</form:option>
						<form:option value="F">失败</form:option>
						<form:option value="I">初始</form:option>
						</form:select>
				<label>对帐状态：</label>
				<form:select  path="checkFlag" data-toggle="selectpicker">
						<form:option value="">请选择</form:option>
						<form:option value="S">成功</form:option>
						<form:option value="M">可疑（银行找不到记录）</form:option>
						<form:option value="R">可疑（我方失败）</form:option>
						<form:option value="Q">可疑（银行失败）</form:option>
						<form:option value="A">金额不对</form:option>
						</form:select><br>
				<label>姓名        ：</label>
				<form:input path="acctName" class="form-control" size="15" />
				<label>卡号：</label>
				<form:input path="acctId" class="form-control" size="15" />
				<label>手机号：</label>
				<form:input path="phone" class="form-control" size="15" />
				<button type="submit" class="btn-default" data-icon="search">查询</button>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">网关号</th>
      <th align="center">流水号</th>
      <th align="center">交易日期</th>
      <th align="center">交易状态</th>
      <th align="center">交易金额</th>
      <th align="center">对帐状态</th>
      <th align="center">卡号</th>
      <th align="center">姓名</th>
      <th align="center">编辑</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${pageUser.list}" varStatus="status">
<tr data-id="<c:out value="${record.gateId}"/>">
      <td align="center"><c:out value="${record.gateId}"/></td>
      <td align="center"><c:out value="${record.seqId}"/></td>
      <td align="center"><c:out value="${record.transDate}"/></td>
      <td align="center">${record.transStatus == 'S'?'成功':''}
      ${record.transStatus == 'F'?'失败':''}
      ${record.transStatus == 'I'?'初始':''}</td>
      <td align="center"><c:out value="${record.transAmt}"/></td>
      <td align="center">${record.checkFlag == 'S'?'成功':''}
      ${record.checkFlag == 'M'?'可疑（银行找不到记录）':''}
      ${record.checkFlag == 'R'?'可疑（我方失败）':''}
      ${record.checkFlag == 'Q'?'可疑（银行失败）':''}
      ${record.checkFlag == 'A'?'金额不对':''}</td>
      <td align="center"><c:out value="${record.acctId}"/></td>
      <td align="center"><c:out value="${record.acctName}"/></td>
      <td align="center">
		<a class="btn btn-red" data-confirm-msg="确定要把对帐状态修改为成功吗？" data-toggle="doajax" href="checkdata/updatepage/${record.gateId}/${record.seqId}/${record.transDate}/S">成功</a></td>
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
