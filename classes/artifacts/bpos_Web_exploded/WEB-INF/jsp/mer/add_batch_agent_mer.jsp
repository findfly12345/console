<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/2/14
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>批量商户进件</title>
    <script type="text/javascript">
        function agent_file_upload_success(file, data) {
            var json = $.parseJSON(data)
            $(this).bjuiajax('ajaxDone', json)
            if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
                $('#uploadFile').val(json.filePath).trigger('validate')
                $('#uploadFileName').val(json.filename).trigger('validate')
                $('#uploadFileType').val(json.fileType).trigger('validate')
                $('#uploadFileSpan').html('<label>'+json.filename+'</label>')
            }
        }
    </script>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="addBatchAgentMerFileForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/mer/add_batch_agent_mer" method="post">
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85"> 代理商名称：</label>
                <div style="display: inline-block; vertical-align: middle;">
                    <div style="display: inline-block; vertical-align: middle;">
                        <select id="agentId" name="agentId" data-toggle="selectpicker" data-width="200" data-rule="代理商名称:required" >
                            <option value="">请选择</option>
                            <c:forEach items="${tblAgentInfoDos}" var="tblAgentInfoDo">
                                <option value="${tblAgentInfoDo.memberId}">${tblAgentInfoDo.agentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <%--<div class="form-group" style="margin: 20px 0 20px; ">--%>
                <%--<label class="control-label x85"> 文件类型：</label>--%>
                <%--<div style="display: inline-block; vertical-align: middle;">--%>
                    <%--<div style="display: inline-block; vertical-align: middle;">--%>
                        <%--<select id="fileType" name="fileType" data-toggle="selectpicker" data-width="200" data-rule="文件类型:required">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<option value="data">商户数据</option>--%>
                            <%--<option value="attachment">商户附件</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85"> 文件上传：</label>
                <div style="display: inline-block; vertical-align: middle;">
                    <div style="display: inline-block; vertical-align: middle;">
                        <div id="agentMerFile" data-toggle="upload" data-uploader="${pageContext.request.contextPath}/mer/upload_agent_mer_file"
                             data-file-size-limit="1024000000"
                             data-file-type-exts="*.xls;*.xlsx;"
                             data-multi="true"
                             data-auto = "true"
                             data-on-upload-success="agent_file_upload_success"
                             data-icon="cloud-upload">
                        </div>
                        <input type="hidden" name="uploadFile" id="uploadFile" value=""  data-rule="文件:required">
                        <input type="hidden" name="uploadFileName" id="uploadFileName" value="">
                        <input type="hidden" name="uploadFileType" id="uploadFileType" value="">
                        <span id="uploadFileSpan"></span>
                    </div>
                </div>
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85"></label>
                <button type="submit" class="btn-default" data-icon="save">导入</button>&nbsp;
            </div>
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <c:if test="${tblAgentMerAddDetailInfoDOList!=null}">
        <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
            <thead>
            <tr>
                <th align="center">批次号</th>
                <th align="center">文件商户号</th>
                <th align="center">批处理结果</th>
                <th align="center">批处理结果描述</th>
                <th align="center">操作员</th>
                <th align="center">更新时间</th>
                <th align="center">创建时间</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${tblAgentMerAddDetailInfoDOList}" varStatus="status">
                    <tr>
                        <td align="center">${record.batchNo}</td>
                        <td align="center">${record.merId}</td>
                        <td align="center">
                            <c:if test="${record.resultFlag=='I'}">初始化</c:if>
                            <c:if test="${record.resultFlag=='F'}">失败</c:if>
                            <c:if test="${record.resultFlag=='S'}">成功</c:if>
                        </td>
                        <td align="center">${record.resultDesc}</td>
                        <td align="center">${record.userName}</td>
                        <td align="center">${record.updateTime}</td>
                        <td align="center">${record.createTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<script type="text/javascript">

</script>
</body>
</html>
