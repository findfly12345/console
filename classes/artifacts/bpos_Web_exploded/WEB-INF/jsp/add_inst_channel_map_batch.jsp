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
    <title>新增机构关联</title>
    <script type="text/javascript">
        function inst_channel_map_file_upload_success(file, data) {
            var json = $.parseJSON(data)
            $(this).bjuiajax('ajaxDone', json)
            if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
                $('#uploadMapFile').val(json.fileurl).trigger('validate')
                $('#uploadMapFileName').val(json.filename).trigger('validate')
                $('#uploadMapFileSpan').html('<label>'+json.filename+'</label>')
            }
        }
    </script>
</head>
<body>
<div class="bjui-pageHeader">

</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="instChannelMapForm"data-toggle="validate" novalidate="novalidate" action="${pageContext.request.contextPath}/IM/importInstChannelMap" method="post">
        <div class="bjui-searchBar">
            <div class="form-group" style="margin: 20px 0 20px; ">
            <label class="control-label x85"> 批量导入：</label>
            <div style="display: inline-block; vertical-align: middle;">
                <div style="display: inline-block; vertical-align: middle;">
                    <div id="instChannelMapFile" data-toggle="upload" data-uploader="${pageContext.request.contextPath}/IM/upload_inst_mer_key_file"
                         data-file-size-limit="1024000000"
                         data-file-type-exts="*.xlsx;*.xls"
                         data-multi="true"
                         data-auto = "true"
                         data-on-upload-success="inst_channel_map_file_upload_success"
                         data-icon="cloud-upload">
                    </div>
                    <input type="hidden" name="uploadMapFile" id="uploadMapFile" value="" >
                    <input type="hidden" name="uploadMapFileName" id="uploadMapFileName" value="" >
                    <span id="uploadMapFileSpan"></span>
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

</div>

</body>
</html>
