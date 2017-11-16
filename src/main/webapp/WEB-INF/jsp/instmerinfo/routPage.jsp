<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构大商户管理</title>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="merChannelPreInfoForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/instmerinfo/search" method="post"
			modelAttribute="channelpreiinfo">
			<input type="hidden" name="pageSize"
				value="${merChannelPreInfoForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${merChannelPreInfoForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>机构号：</label>
				<form:input path="instId" class="form-control" size="15" value="${merChannelPreInfoForm.instId}"/>
				<label>平台商户号：</label>
				<form:input path="merId" class="form-control" size="15" value="${merChannelPreInfoForm.merId}"/>
				<label>机构商户号：</label>
				<form:input path="instMerId" class="form-control" size="15" value="${merChannelPreInfoForm.instMerId}"/>
				<label>渠道商户号：</label>
				<form:input path="channelMerId" class="form-control" size="15" value="${merChannelPreInfoForm.channelMerId}"/>
				<label>渠道报备状态：</label>
				<select id="channelStat" name="channelStat" data-toggle="selectpicker">
					<option value="">请选择</option>
					<option <c:if test="${merChannelPreInfoForm.channelStat == '0'}">selected</c:if> value="0">未报备</option>
					<option <c:if test="${merChannelPreInfoForm.channelStat == '1'}">selected</c:if> value="1">报备中</option>
					<option <c:if test="${merChannelPreInfoForm.channelStat == '2'}">selected</c:if> value="2">报备失败</option>
					<option <c:if test="${merChannelPreInfoForm.channelStat == '3'}">selected</c:if> value="3">报备成功</option>
					<option <c:if test="${merChannelPreInfoForm.channelStat == '4'}">selected</c:if> value="4">通道关闭</option>
				</select>

				<button type="submit" class="btn-default" data-icon="search">查询</button>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th width="30" align="center">序号</th>
      <th align="center">机构号</th>
      <th align="center">平台商户号</th>
      <th align="center">平台终端号</th>
      <th align="center">机构商户号</th>
      <th align="center">机构终端号</th>
      <th align="center">渠道网关</th>
      <th align="center">渠道商户号</th>
      <th align="center">渠道终端号</th>
      <th align="center">报备状态</th>
      <th align="center">操作</th>
    </tr>
    </thead>
    <tbody>
	<c:forEach var="record" items="${pageUser.list}" varStatus="status">
		  <td>${status.index+1}</td>
	      <td align="center"><c:out value="${record.instId}"/></td>
	      <td align="center"><c:out value="${record.merId}"/></td>
	      <td align="center"><c:out value="${record.termId}"/></td>
	      <td align="center"><c:out value="${record.instMerId}"/></td>
	      <td align="center"><c:out value="${record.instTermId}"/></td>
	      <td align="center"><c:out value="${record.gateId}"/></td>
	      <td align="center"><c:out value="${record.channelMerId}"/></td>
	      <td align="center"><c:out value="${record.channelTermId}"/></td>
	      	      <td align="center">
	      	            <c:if test="${record.channelStat == '0'}">未报备</c:if>
						<c:if test="${record.channelStat == '1'}">报备中</c:if>
	      	      		<c:if test="${record.channelStat == '2'}">报备失败</c:if>
	      	      		<c:if test="${record.channelStat == '3'}">报备成功</c:if>
	      	      		<c:if test="${record.channelStat == '4'}">通道关闭</c:if>
	      	      
	      	      </td>
	      	</td>
	      <td align="center">
			<a class="btn btn-red" data-confirm-msg="确定要删除该行信息吗？" data-toggle="doajax" href="instmerinfo/delete/${record.merId}">删除</a>
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
