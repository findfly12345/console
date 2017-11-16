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
    <title>机构密钥维护</title>

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
    <script language="javascript">
        function importRecord() {
            instChannelMapForm.action = '${pageContext.request.contextPath}/IM/importInstChannelMap';
//            instChannelMapForm.
//            instChannelMapForm.method = 'get';
            instChannelMapForm.submit();
//            instChannelMapForm.method = 'post';
            instChannelMapForm.action = '${pageContext.request.contextPath}/IM/inst_channel_map_query';
        }
    </script>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="instChannelMapForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/IM/inst_channel_map_query" method="post">
        <input type="hidden" name="pageSize" value="${instChannelMapForm.pageSize}">
        <input type="hidden" name="pageCurrent" value="${instChannelMapForm.pageCurrent}">
        <div class="bjui-searchBar">
            <a href="${pageContext.request.contextPath}/IM/add_map_for_inst_channel" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="420" data-id="dialog-normalmap" data-title="新增关联机构">新增</a>&nbsp;
            <a href="${pageContext.request.contextPath}/IM/add_map_for_inst_channel_batch" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="420" data-id="dialog-normal-batchmap" data-title="导入关联机构">批量导入</a>&nbsp;
            <label>机构号：</label>
            <input type="text" name="instId" id="instId" value="${instChannelMapForm.instId}"  size="15">&nbsp;
            <label>渠道号：</label>
            <input type="text" name="channelId" id="channelId" value="${instChannelMapForm.channelId}"  size="15">&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <%--<label class="control-label x85"> 批量导入：</label>--%>
            <%--<div style="display: inline-block; vertical-align: middle;">--%>
                <%--<div style="display: inline-block; vertical-align: middle;">--%>
                    <%--<div id="instChannelMapFile" data-toggle="upload" data-uploader="${pageContext.request.contextPath}/IM/upload_inst_mer_key_file"--%>
                         <%--data-file-size-limit="1024000000"--%>
                         <%--data-file-type-exts="*.xlsx;*.xls"--%>
                         <%--data-multi="true"--%>
                         <%--data-auto = "true"--%>
                         <%--data-on-upload-success="inst_channel_map_file_upload_success"--%>
                         <%--data-icon="cloud-upload">--%>
                    <%--</div>--%>
                    <%--<input type="hidden" name="uploadMapFile" id="uploadMapFile" value="" >--%>
                    <%--<input type="hidden" name="uploadMapFileName" id="uploadMapFileName" value="" >--%>
                    <%--<span id="uploadMapFileSpan"></span>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<a href="javascript:void(0)" class="btn btn-green" data-toggle="doajax" onclick="importRecord()">导入</a>--%>
            <%--<a href="'${pageContext.request.contextPath}/IM/importInstChannelMap?" class="btn btn-green" data-toggle="doajax" >导入</a>--%>
            <%--<button type="button" class="btn-default" data-icon="save" onclick="importRecord();">导入</button>&nbsp;--%>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">机构号</th>
            <th align="center">机构商户号</th>
            <th align="center">商户终端号</th>
            <th align="center">渠道号</th>
            <th align="center">渠道商户号</th>
            <th align="center">商户终端号</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${instChannelMapForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.instId}"/>">
                <td align="center"><c:out value="${record.instId}"/></td>
                <td align="center"><c:out value="${record.instMerId}"/></td>
                <td align="center"><c:out value="${record.instMerTermId}"/></td>
                <td align="center"><c:out value="${record.channelId}"/></td>
                <td align="center"><c:out value="${record.channelMerId}"/></td>
                <td align="center"><c:out value="${record.channelMerTermId}"/></td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/IM/toggleMapStatus?id=<c:out value="${record.instId}"/>&merId=<c:out value="${record.instMerId}"/>&termId=<c:out value="${record.instMerTermId}"/>" class="btn btn-red" data-toggle="doajax" >
                        <c:out value="${record.statusFlag}"/></a>
                    <a href="${pageContext.request.contextPath}/IM/delete_inst_channnel_map?id=<c:out value="${record.instId}"/>&merId=<c:out value="${record.instMerId}"/>&termId=<c:out value="${record.instMerTermId}"/>" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定删除吗？" >
                        删除</a>
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
        <span> 条，共 <c:out value="${instChannelMapForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<c:out value="${instChannelMapForm.pagination.itemCount}"/>" data-page-size="<c:out value="${instChannelMapForm.pageSize}"/>" data-page-current="<c:out value="${instChannelMapForm.pageCurrent}"/>">
    </div>

</div>

</body>
</html>
