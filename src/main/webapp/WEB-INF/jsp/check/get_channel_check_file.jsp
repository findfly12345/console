<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 16/10/19
  Time: 上午9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>渠道对账文件获取</title>
</head>
<body>
<div class="bjui-pageHeader">
  <form id="pagerForm" name="addInstMerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/check/get_channel_check_file" method="post">
    <div class="bjui-searchBar">
      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85">交易日期</label>
        <input type="text" data-toggle="datepicker" id="transDate" name="transDate" data-rule="交易日期:required;date">
      </div>
      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85"> 渠道名称：</label>
        <div style="display: inline-block; vertical-align: middle;">
          <div style="display: inline-block; vertical-align: middle;">
            <select id="gateId" name="gateId" data-toggle="selectpicker" data-width="200" data-rule="渠道名称:required">
              <option value="">请选择</option>
                <option value="U1">钱宝</option>
            </select>
          </div>
        </div>
      </div>

      <div class="form-group" style="margin: 20px 0 20px; ">
        <button type="submit" class="btn-default" data-icon="save">获取文件</button>&nbsp;
      </div>
    </div>
  </form>

</div>
<div class="bjui-pageContent tableContent">
<c:if test="${tblChannelCheckFileInfoDOList!=null}">
  <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">交易日期</th>
      <th align="center">渠道</th>
      <th align="center">文件类型</th>
      <th align="center">文件编号</th>
      <th align="center">文件</th>
      <th align="center">操作员</th>
      <th align="center">更新时间</th>
      <th align="center">创建时间</th>
      <th align="center">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tblChannelCheckFileInfoDO" items="${tblChannelCheckFileInfoDOList}">
      <tr>
        <td align="center">${tblChannelCheckFileInfoDO.transDate}</td>
        <c:if test="${tblChannelCheckFileInfoDO.gateId=='U1'}">
          <td align="center">钱宝</td>
        </c:if>
        <c:if test="${tblChannelCheckFileInfoDO.fileType=='0'}">
          <td align="center">交易对账文件</td>
        </c:if>
        <c:if test="${tblChannelCheckFileInfoDO.fileType=='1'}">
          <td align="center">结算对账文件</td>
        </c:if>
        <c:if test="${tblChannelCheckFileInfoDO.fileType=='2'}">
          <td align="center">对账文件(全部类型)</td>
        </c:if>
        <td align="center">${tblChannelCheckFileInfoDO.fileNo}</td>
        <td align="center"><a href="${pageContext.request.contextPath}/mer/get_mer_batch_file?batchFile=${tblChannelCheckFileInfoDO.filePath}">下载</a></td>
        <td align="center">${tblChannelCheckFileInfoDO.userName}</td>
        <td align="center">${tblChannelCheckFileInfoDO.updateTime}</td>
        <td align="center">${tblChannelCheckFileInfoDO.createTime}</td>
        <td align="center"><a href="${pageContext.request.contextPath}/check/get_channel_check_file_detail?fileNo=${tblChannelCheckFileInfoDO.fileNo}&gateId=${tblChannelCheckFileInfoDO.gateId}" data-toggle="dialog" data-id="getChannelCheckFileDetail" data-max="true" data-title="文件详情">查看详情</a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
</div>
</body>
</html>
