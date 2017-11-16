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
    <title>机构商户进件</title>
  <script type="text/javascript">
    function file_upload_success(file, data) {
      var json = $.parseJSON(data)
      $(this).bjuiajax('ajaxDone', json)
      if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#uploadFile').val(json.filePath).trigger('validate')
        $('#uploadFileName').val(json.filename).trigger('validate')
        $('#uploadFileSpan').html('<label>'+json.filename+'</label>')
      }
    }
  </script>
</head>
<body>
<div class="bjui-pageHeader">
  <form id="pagerForm" name="addInstMerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/mer/add_inst_mer" method="post">
    <div class="bjui-searchBar">
      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85"> 机构名称：</label>
        <div style="display: inline-block; vertical-align: middle;">
          <div style="display: inline-block; vertical-align: middle;">
            <select id="instId" name="instId" data-toggle="selectpicker" data-width="200" data-rule="机构名称:required">
              <option value="">请选择</option>
              <c:forEach items="${tblBtsInstDOList}" var="tblBtsInstDO">
                <option value="${tblBtsInstDO.instCode}">${tblBtsInstDO.instName}</option>
              </c:forEach>
            </select>
          </div>
        </div>
      </div>

      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85"> 文件类型：</label>
        <div style="display: inline-block; vertical-align: middle;">
          <div style="display: inline-block; vertical-align: middle;">
            <select id="fileType" name="fileType" data-toggle="selectpicker" data-width="200" data-rule="文件类型:required">
              <option value="">请选择</option>
              <option value="data">商户数据</option>
              <option value="attachment">商户附件</option>
            </select>
          </div>
        </div>
      </div>

      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85"> 文件上传：</label>
        <div style="display: inline-block; vertical-align: middle;">
          <div style="display: inline-block; vertical-align: middle;">
            <div id="instMerFile" data-toggle="upload" data-uploader="${pageContext.request.contextPath}/mer/upload_inst_mer_file"
                 data-file-size-limit="1024000000"
                 data-file-type-exts="*.xls;*.zip"
                 data-multi="true"
                 data-auto = "true"
                 data-on-upload-success="file_upload_success"
                 data-icon="cloud-upload">
            </div>
            <input type="hidden" name="uploadFile" id="uploadFile" value=""  data-rule="文件:required">
            <input type="hidden" name="uploadFileName" id="uploadFileName" value="">
            <span id="uploadFileSpan"></span>
          </div>
        </div>
      </div>
      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85"></label>
        <button type="submit" class="btn-default" data-icon="save">导入</button>&nbsp;
        <button type="button" data-toggle="navtab"data-id="navtab-detail"  data-title="进件历史" href="${pageContext.request.contextPath}/mer/forward_batch_history" id="historyButton" class="btn btn-primary" data-icon="history">进件历史</button>&nbsp;      </div>
    </div>
  </form>

</div>
<div class="bjui-pageContent tableContent">
<c:if test="${tblInstMerAddBatchInfoDO!=null}">
  <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
    <thead>
    <tr>
      <th align="center">批次号</th>
      <th align="center">机构号</th>
      <th align="center">上传文件类型</th>
      <th align="center">上传文件</th>
      <th align="center">批处理结果</th>
      <th align="center">批处理结果描述</th>
      <th align="center">结果文件</th>
      <th align="center">操作员</th>
      <th align="center">更新时间</th>
      <th align="center">创建时间</th>
      <th align="center">操作</th>
    </tr>
    </thead>
    <tbody>
      <tr>
        <td align="center">${tblInstMerAddBatchInfoDO.batchNo}</td>
        <td align="center">${tblInstMerAddBatchInfoDO.instId}</td>
        <c:if test="${tblInstMerAddBatchInfoDO.fileType=='0'}">
          <td align="center">商户数据</td>
        </c:if>
        <c:if test="${tblInstMerAddBatchInfoDO.fileType=='1'}">
          <td align="center">商户附件</td>
        </c:if>
        <td align="center"><a href="${pageContext.request.contextPath}/mer/get_mer_batch_file?batchFile=${tblInstMerAddBatchInfoDO.filePath}">下载</a></td>
        <td align="center">${tblInstMerAddBatchInfoDO.resultFlag}</td>
        <td align="center">${tblInstMerAddBatchInfoDO.resultDesc}</td>
        <td align="center"><a href="${pageContext.request.contextPath}/mer/import_mer_result_file?resultFilePath=${tblInstMerAddBatchInfoDO.resultFilePath}">下载</a></td>
        <td align="center">${tblInstMerAddBatchInfoDO.userName}</td>
        <td align="center">${tblInstMerAddBatchInfoDO.updateTime}</td>
        <td align="center">${tblInstMerAddBatchInfoDO.createTime}</td>
        <td align="center"><a href="${pageContext.request.contextPath}/mer/import_mer_result_detail?batchNo=${tblInstMerAddBatchInfoDO.batchNo}" data-toggle="dialog" data-id="importMerResultDetailDialog" data-max="true" data-title="批量导入详情">查看详情</a></td>
      </tr>
    </tbody>
  </table>
</c:if>
</div>
</script>

</body>
</html>
