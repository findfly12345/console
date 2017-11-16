<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>渠道大商户管理</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/gatemer/search" method="post"
			modelAttribute="rout">
			<input type="hidden" name="pageSize"
				value="${transRateCountForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${transRateCountForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>网关号：</label>
				<form:input path="gateId" class="form-control" size="15" />
				<label>商户号：</label>
				<form:input path="posMerId" class="form-control" size="15" />
				<label>终端号：</label>
				<form:input path="posTermId" class="form-control" size="15" />
				<label>商户类型：</label>
				<form:select  path="posMerType" data-toggle="selectpicker">
						<form:option value="">请选择</form:option>
						<form:option value="1">餐娱类</form:option>
						<form:option value="2">一般类</form:option>
						<form:option value="3">民生类</form:option>
						<form:option value="4">批发类</form:option>
						<form:option value="5">房车类</form:option>
						<form:option value="6">公益类</form:option>
						</form:select>
				<label>商户状态：</label>
					<form:select  path="posMerStat" data-toggle="selectpicker">
						<form:option value="">请选择</form:option>
						<form:option value="Y">可用</form:option>
						<form:option value="N">不可用</form:option>
						</form:select>
				<button type="submit" class="btn-default" data-icon="search">查询</button>
				&nbsp; <a class="btn btn-green" data-title="增加" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-add" href="gatemer/addpage">增加</a>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">网关号</th>
      <th align="center">商户号</th>
      <th align="center">终端号</th>
      <th align="center">商户类型</th>
      <th align="center">商户描述</th>
      <th align="center">商户状态</th>
      <th align="center">省份</th>
      <th align="center">地区</th>
      <th align="center">备注</th>
      <th align="center">编辑</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${pageUser.list}" varStatus="status">
<tr data-id="<c:out value="${record.gateId}"/>">
      <td align="center"><c:out value="${record.gateId}"/></td>
      <td align="center"><c:out value="${record.posMerId}"/></td>
      <td align="center"><c:out value="${record.posTermId}"/></td>
      <td align="center">${record.posMerType == 1?'餐娱类':''}
	      ${record.posMerType == 2?'一般类':''}
	      ${record.posMerType == 3?'民生类':''}
	      ${record.posMerType == 4?'批发类':''}
	      ${record.posMerType == 5?'房车类':''}
	      ${record.posMerType == 6?'公益类':''}
	  </td>
      <td align="center"><c:out value="${record.posMerDesc}"/></td>
      <td align="center">${record.posMerStat == 'Y'?'可用':''}
	      ${record.posMerStat == 'N'?'不可用':''}</td>
      <td align="center"><c:out value="${record.posMerProv}"/></td>
      <td align="center"><c:out value="${record.posMerCity}"/></td>
      <td align="center"><c:out value="${record.posMerRemark}"/></td>
      <td align="center">
      	<a class="btn btn-green" data-title="修改" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-update" href="gatemer/updatepage/${record.gateId}/${record.posMerId}/${record.posTermId}">修改</a>
		<a class="btn btn-red" data-confirm-msg="确定要删除该行信息吗？" data-toggle="doajax" href="gatemer/delete/${record.gateId}/${record.posMerId}/${record.posTermId}">删除</a></td>
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
