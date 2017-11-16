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
    <title>商户渠道报备结果维护</title>
</head>
<body>
<div class="bjui-pageHeader">
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
</div>
<div class="bjui-pageContent tableContent">
    <form id="pagerForm" name="merChannelPreResultForm" data-toggle="validate" novalidate="novalidate"
          action="${pageContext.request.contextPath}/mer/mer_channel_pre_result" method="post">
        <div class="bjui-searchBar">
            <div style="margin:15px auto 0; width:96%;">
                <div class="row" style="padding: 0 8px;">
                    <div class="col-md-12">
                        <div class="panel panel-default">

                            <div class="panel-body">

                                <div class="form-group" style="margin: 20px 0 20px; ">
                                    <label class="control-label x85">报备渠道：</label>
                                    <select name="channelId" id="channelId" data-rule="报备渠道:required;"
                                            data-toggle="selectpicker" size="14">
                                        <option value="">请选择</option>
                                        <option value="U1">钱宝</option>
                                    </select>&nbsp;
                                </div>

                                <div class="form-group" style="margin: 20px 0 20px; ">
                                    <label class="control-label x85"> 文件上传：</label>
                                    <div style="display: inline-block; vertical-align: middle;">
                                        <div style="display: inline-block; vertical-align: middle;">
                                            <div id="instMerFile" data-toggle="upload" data-uploader="${pageContext.request.contextPath}/mer/upload_mer_channel_pre_result_file"
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
                                </br>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </form>
</div>
<div class="bjui-pageFooter" id="pageFooter">
    <ul>
        <li>
            <button type="submit" class="btn-default">报备结果维护</button>
        </li>
    </ul>
    <div class="pages">
    </div>
</div>

</div>

</body>
</html>
