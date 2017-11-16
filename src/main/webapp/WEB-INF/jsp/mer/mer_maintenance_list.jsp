<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户信息</title>
    <!-- 机构商户查询 -->
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merInfoForm" data-toggle="ajaxsearch"
          action="${pageContext.request.contextPath}/mer/get_mer_info" method="post">
        <input type="hidden" id="pageSize" name="pageSize" value="${merInfoForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${merInfoForm.pageCurrent}">
        <div class="bjui-searchBar">

            <label>商户名：</label>
            <input type="text" name="merName" id="merName" value="${merInfoForm.merName}" size="15">&nbsp;
            <label>商户号：</label>
            <input type="text" name="merId" id="merId" value="${merInfoForm.merId}" size="15">&nbsp;
            <label>所属机构：</label>
            <select name="instId" id="instId" data-toggle="selectpicker">
                <option value="">请选择</option>
                <c:forEach var="item" items="${merInfoForm.instDOList}" varStatus="status">
                    <option value="${item.instCode}"
                            <c:if test="${item.instCode == merInfoForm.instId}">selected</c:if>>${item.instName}</option>
                </c:forEach>
            </select>&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            &nbsp;
            <button id="regBt" type="button" class="btn-default">批量注册</button>
            &nbsp;
            <button type="submit" id="exportButton" class="btn-default" data-icon="save" onclick="instMerExport()">导出
            </button>
            &nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%"
           data-nowrap="true">
        <thead>
        <tr>
            <th align="center">商户号</th>
            <th align="center">商户名</th>
            <th align="center">终端号</th>
            <th align="center">机构号</th>
            <th align="center">机构商户号</th>
            <th align="center">机构终端号</th>
            <th align="center">注册 <input id="checkAll_id" type='checkbox'/></th>
            <th align="center"> 操作
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${merInfoForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.merId}"/>">
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center"><c:out value="${record.merName}"/></td>
                <td align="center"><c:out value="${record.termId}"/></td>
                <td align="center"><c:out value="${record.instId}"/></td>
                <td align="center"><c:out value="${record.instMerId}"/></td>
                <td align="center"><c:out value="${record.instTermId}"/></td>
                <td align="center">

                        <%--民生通道--%>
                    <input type="button"
                           href="${pageContext.request.contextPath}/wechat/wechat_register?memberId=mswx${record.merId}"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.registedMinSheng == '1'}"> disabled=true; value="已经注册" </c:if> value="微信注册">
                    </input>
                    <input value="mswx${record.merId}" name="check_li" type='checkbox' <c:if
                            test="${record.registedMinSheng == '1'}"> disabled = true</c:if>></input>

                    <input type="button"
                           href="${pageContext.request.contextPath}/wechat/wechat_register?memberId=mszf${record.merId}"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.registedMinShengZf == '1'}"> disabled=true; value="已经注册" </c:if> value="支付注册">
                    </input>
                    <input value="mszf${record.merId}" name="check_li" type='checkbox' <c:if
                            test="${record.registedMinShengZf == '1'}"> disabled = true</c:if>></input>

                        <%--翰银通道--%>
                    <input type="button"
                           href="${pageContext.request.contextPath}/wechat/wechat_register?memberId=hy${record.merId}"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.registedHanYin == '1'}"> disabled=true; value="已经注册" </c:if> value="翰银注册">
                    
                        <%--上海银行--%>
                    <input type="button"
                           href="${pageContext.request.contextPath}/wechat/wechat_register?memberId=shbank${record.merId}&action=ADD"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.registedSHBank == '1'}"> value="上银修改" </c:if> value="上海银行">                    
                    </input>
                    <input value="shbank${record.merId}" name="check_li" type='checkbox' <c:if
                            test="${record.registedSHBank == '1'}"> disabled = true</c:if>></input>
                            
                    <%-- 恒丰银行 --%>
                    <input type="button"
                           href="${pageContext.request.contextPath}/wechat/wechat_register?memberId=egbank${record.merId}&action=EGBANK"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.registedEGBank == '1'}"> disabled=true; value="已经注册" </c:if> value="恒丰注册">
                </td>

                <td align="center">
                    <a href="${pageContext.request.contextPath}/mer/query_mer_detail?id=<c:out value="${record.merId}"/>"
                       class="btn btn-green" data-toggle="navtab" data-id="navtab-detail"
                       data-title="商户详细信息(${record.merName})">详细</a>
                       
                    <a href="${pageContext.request.contextPath}/mer/query_qrcode_info?merId=<c:out value="${record.merId}"/>"
                       class="btn btn-green" data-toggle="navtab" data-id="navtab-qrcode"
                       data-title="商户二维码">二维码</a>                       
                       
                <%--<a href="${pageContext.request.contextPath}/IM/delete_inst?id=<c:out value="${record.instCode}"/>" class="btn btn-red" data-toggle="doajax"  data-confirm-msg="确定删除吗？">删除</a>--%>
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
        <span> 条，共 <c:out value="${merInfoForm.pagination.itemCount}"/> 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination"
         data-total="<c:out value="${merInfoForm.pagination.itemCount}"/>"
         data-page-size="<c:out value="${merInfoForm.pageSize}"/>"
         data-page-current="<c:out value="${merInfoForm.pageCurrent}"/>">
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.checkbox.js"></script>
<script type="text/javascript">

    /*
     全选、反选
     */
    $(document.body).on("click", "#checkAll_id", function () {
        var _is_check = jQuery(this).get(0).checked;
        jQuery("input[name='check_li']").each(function (_i, _o) {
            if (!$(_o).prop("disabled")) {
                jQuery(_o).get(0).checked = _is_check;
            }
        });
    });

    /**
     * 批量注册
     */
    $(document.body).on("click", "#regBt", function () {
        var records = jQuery(":checkbox[name='check_li']:checked").checkbox().val();
        if (records.length == "") {
            $(this).alertmsg("warn", "未选中任何记录!");
            return;
        }

        $.post("${pageContext.request.contextPath}/wechat/wechat_register", {
            "memberId": records
        }, function (res) {
            if (res.statusCode == "200") {
                $(this).alertmsg("ok", "注册成功！");
            } else {
                $(this).alertmsg("warn", "注册失败！");
            }
        });

    });


    function instMerExport() {
        merInfoForm.action =
            '${pageContext.request.contextPath}/mer/export_inst_mer_report';
        merInfoForm.submit();
        merInfoForm.action = '${pageContext.request.contextPath}/mer/get_mer_info';
    }


</script>
</body>
</html>
