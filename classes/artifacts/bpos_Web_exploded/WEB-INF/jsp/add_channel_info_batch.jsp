<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/18
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量导入渠道信息</title>
    <script type="text/javascript">
        function file_upload_success(file, data) {
            var json = $.parseJSON(data)
            $(this).bjuiajax('ajaxDone', json)
            if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
                $('#uploadFile').val(json.fileurl).trigger('validate')
                $('#uploadFileName').val(json.filename).trigger('validate')
                $('#uploadFileSpan').html('<label>'+json.filename+'</label>')
            }
        }
    </script>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="channelFileUploadForm" data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/CM/import_channel_info" method="post">
        <div class="bjui-searchBar">

            <div class="form-group" style="margin: 20px 0 20px; ">
                <label class="control-label x85"> 批量导入：</label>
                <div style="display: inline-block; vertical-align: middle;">
                    <div style="display: inline-block; vertical-align: middle;">
                        <div id="instMerKeyFile" data-toggle="upload" data-uploader="${pageContext.request.contextPath}/CM/upload_file"
                             data-file-size-limit="1024000000"
                             data-file-type-exts="*.xlsx;*.xls"
                             data-multi="true"
                             data-auto = "true"
                             data-on-upload-success="file_upload_success"
                             data-icon="cloud-upload">
                        </div>
                        <input type="hidden" name="uploadFile" id="uploadFile" value=""  >
                        <input type="hidden" name="uploadFileName" id="uploadFileName" value="" >
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
<div class="bjui-pageFooter" id="pageFooter">
    <div class="pages">
    </div>
</div>

</body>
</html>
