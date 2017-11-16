<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网关管理</title>
</head>
<body>
	<div class="bjui-pageHeader" >
		<form:form id="pagerForm" name="route"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/gateroute/search" method="post"
			modelAttribute="route">
			<input type="hidden" name="pageSize"
				value="${transRateCountForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${transRateCountForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>网关号：</label>
				<form:input path="gateId" class="form-control" size="15" />
				
				<label>网关描述：</label>
				<form:input path="gateDesp" class="form-control" size="15" />
				
				<label>网关类型：</label>
                <form:select path="gateType" data-toggle="selectpicker">
                    <form:option value="">请选择</form:option>
                    <form:option value="0001">POS</form:option>
                    <form:option value="0002">二维码</form:option>
                </form:select>
				
				<button type="submit" class="btn-default" data-icon="search">查询</button>
				&nbsp; <a class="btn btn-green" data-title="增加" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-add" href="gateroute/addpage">增加</a>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent" >
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">网关号</th>
      <th align="center">网关类型</th>
      <th align="center">网关描述</th>
      <th align="center">网关费率</th>
      <th align="center">网关状态</th>
      <th align="center">备注</th>
      <th align="center">操作</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${pageUser.list}" varStatus="status">
<tr data-id="<c:out value="${record.gateId}"/>">
      <td align="center"><c:out value="${record.gateId}"/></td>
      <td align="center">
	  	<c:if test="${record.gateType == '0001'}">POS</c:if>	
		<c:if test="${record.gateType == '0002'}">二维码</c:if>		
	  </td>
      
      <td align="center"><c:out value="${record.gateDesp}"/></td>
      <td align="center"><c:out value="${record.gateFee}"/></td>
      <td align="center">
      		<c:if test="${record.gateStat == 'Y'}">打开</c:if>	
	  		<c:if test="${record.gateStat == 'N'}">关闭</c:if>		
      </td>
      <td align="center"><c:out value="${record.remark}"/></td>     
      
      <td align="center"><a class="btn btn-green" data-title="修改" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-update" href="gateroute/updatepage/${record.gateId}">修改</a>
		<a class="btn btn-red" data-confirm-msg="确定要删除该行信息吗？" data-toggle="doajax" href="gateroute/delete/${record.gateId}">删除</a></td>
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
