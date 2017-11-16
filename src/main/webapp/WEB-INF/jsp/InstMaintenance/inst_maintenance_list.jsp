<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构维护</title>
</head>
<body>
<div class="bjui-pageHeader">
  <form id="pagerForm" name="institutionForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/IM/maintenance_the_inst" method="post">
    <input type="hidden" id="pageSize" name="pageSize" value="${institutionForm.pageSize}">
    <input type="hidden" id="pageCurrent" name="pageCurrent" value="${institutionForm.pageCurrent}">
    <div class="bjui-searchBar">
    <a href="${pageContext.request.contextPath}/IM/add_new_inst_page" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="350" data-id="dialog-normal-inst" data-title="新增机构">新增机构</a>&nbsp;
        <label>机构号：</label>
        <input type="text" name="instCode" id="instCode" value="${institutionForm.instCode}"  size="15">&nbsp;
        <label>机构名称：</label>
        <input type="text" name="instName" id="instName" value="${institutionForm.instName}"  size="15">&nbsp;
        <label>机构类别：</label>
        <select name="instType" id="instType" data-toggle="selectpicker">
          <option value="">请选择</option>
          <option <c:if test="${institutionForm.instType=='0'}">selected="selected"</c:if> value="0">收单机构</option>
          <option <c:if test="${institutionForm.instType=='1'}">selected="selected"</c:if> value="1">银行渠道</option>
        </select>&nbsp;
        <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
    </div>
  </form>
</div>
<div class="bjui-pageContent tableContent">
  <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">机构号</th>
      <th align="center">机构类型</th>
      <th align="center">机构名</th>
      <th align="center">机构邮箱</th>
      <th align="center">机构联系方式</th>
      <th align="center">注册时间</th>
      <th align="center">操作</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="record" items="${institutionForm.pagination.list}" varStatus="status">
<tr data-id="<c:out value="${record.instId}"/>">
      <td align="center"><c:out value="${record.instCode}"/></td>
      <td align="center"><c:out value="${record.instType}"/></td>
      <td align="center"><c:out value="${record.instName}"/></td>
      <td align="center"><c:out value="${record.instEmail}"/></td>
      <td align="center"><c:out value="${record.instTelphome}"/></td>
      <td align="center"><c:out value="${record.createTime}"/></td>
      <td align="center">
          <a href="${pageContext.request.contextPath}/IM/edit_inst_info?id=<c:out value="${record.instCode}"/>" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="350" data-id="dialog-normal-modify"  data-title="修改机构信息(${record.instCode})">修改</a>
          <a href="${pageContext.request.contextPath}/IM/delete_inst?id=<c:out value="${record.instCode}"/>" class="btn btn-red" data-toggle="doajax"  data-confirm-msg="确定删除吗？">删除</a>
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
        <span> 条，共 <c:out value="${institutionForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${institutionForm.pagination.itemCount}"/>" data-page-size="<c:out value="${institutionForm.pageSize}"/>" data-page-current="<c:out value="${institutionForm.pageCurrent}"/>">
    </div>
</div>
</body>
</html>
