<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>路由管理</title>
    <script type="text/javascript">
	 function checkStat(dom,id){
	    var checked = dom.checked;
	    if (checked){
	    	document.getElementById("chang_stat").href = "instrout/updatestat/"+id+"/"+ 'Y';
	    }else{
	    	document.getElementById("chang_stat").href = "instrout/updatestat/"+id+"/"+ 'N';
	    }
	}
	 
</script>
</head>
<body>
	<div class="bjui-pageHeader">
		<form:form id="pagerForm" name="transRateCountForm"
			data-toggle="ajaxsearch"
			action="${pageContext.request.contextPath}/instrout/search" method="post"
			modelAttribute="rout">
			<input type="hidden" name="pageSize"
				value="${transRateCountForm.pageSize}" />
			<input type="hidden" name="pageCurrent"
				value="${transRateCountForm.pageCurrent}" />
			<div class="bjui-searchBar">
				<label>机构号：</label>
				<form:input path="instCode" class="form-control" size="15" />
				<label>机构商户号：</label>
				<form:input path="instMerId" class="form-control" size="15" />
				<label>机构终端号：</label>
				<form:input path="instMerTermId" class="form-control" size="15" /><br>
				<label>网关号：</label>
				<form:input path="gateId" class="form-control" size="15" />
				<label>网关商户号：</label>
				<form:input path="bankTermId" class="form-control" size="15" />
				<label>网关终端号：</label>
				<form:input path="bankMerId" class="form-control" size="15" />
				
				<button type="submit" class="btn-default" data-icon="search">查询</button>
				&nbsp; <a class="btn btn-green" data-title="增加" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-add" href="instrout/addpage">增加</a>
				&nbsp; <a class="btn btn-green" data-title="批量增加" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-add" href="instrout/batchaddpage">批量增加</a>
			</div>
		</form:form>
	</div>
	<div class="bjui-pageContent tableContent">
 <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">机构号</th>
      <th align="center">机构商户号</th>
      <th align="center">机构终端号</th>
      <th align="center">网关号</th>
      <th align="center">网关商户号</th>
      <th align="center">网关终端号</th>
      <th align="center">状态</th>
      <th align="center">编辑</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${pageUser.list}" varStatus="status">
<tr data-id="<c:out value="${record.gateId}"/>">
      <td align="center"><c:out value="${record.instCode}"/></td>
      <td align="center"><c:out value="${record.instMerId}"/></td>
      <td align="center"><c:out value="${record.instMerTermId}"/></td>
      <td align="center"><c:out value="${record.gateId}"/></td>
      <td align="center"><c:out value="${record.bankMerId}"/></td>
      <td align="center"><c:out value="${record.bankTermId}"/></td>
      <td align="center">
      	<input type="checkbox" name="doc-stat" id="doc-stat"  ${record.stat == 'Y'?'checked':''} onclick="checkStat(this,${record.routeSeq})"> 可用
      	</td>
      <td align="center">
      	<a class="btn btn-green" data-title="修改" data-id="form"
					data-toggle="dialog" data-width="800" data-height="400" data-id="user-update" href="instrout/updatepage/${record.routeSeq}">修改</a>
		<a class="btn btn-red" data-confirm-msg="确定要删除该行信息吗？" data-toggle="doajax" href="instrout/delete/${record.routeSeq}">删除</a>
		<a id="chang_stat" class="btn btn-red" data-confirm-msg="确定要更改状态吗？" data-toggle="doajax" href="#">更改状态</a>
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
