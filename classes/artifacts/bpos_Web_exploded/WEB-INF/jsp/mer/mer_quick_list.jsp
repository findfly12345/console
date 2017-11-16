<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无卡商户信息管理</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merInfoForm" data-toggle="ajaxsearch"
          action="${pageContext.request.contextPath}/mer/query_quick_mer_list" method="post">
        
        <input type="hidden" id="pageSize" name="pageSize" value="${merInfoForm.pageSize}">
        <input type="hidden" id="pageCurrent" name="pageCurrent" value="${merInfoForm.pageCurrent}">
        
        <div class="bjui-searchBar">
            <label>商户名：</label>
            <input type="text" name="merName" id="merName" value="${merInfoForm.merName}">&nbsp;
            <label>商户号：</label>
            <input type="text" name="merId" id="merId" value="${merInfoForm.merId}" size="15">&nbsp;
            <label>所属代理商：</label>
            <select name="agentId" id="agentId" data-toggle="selectpicker">
                <option value="">请选择</option>
                <c:forEach var="item" items="${merInfoForm.tblAgentInfoDos}" varStatus="status">
                    <option value="${item.memberId}"
                            <c:if test="${item.memberId == merInfoForm.agentId}">selected</c:if>>${item.agentName}</option>
                </c:forEach>
            </select>&nbsp;
            
            <label>所属机构：</label>
            <select name="instId" id="instId" data-toggle="selectpicker">
                <option value="">请选择</option>
                <c:forEach var="item" items="${merInfoForm.instDOList}" varStatus="status">
                    <option value="${item.instCode}"
                            <c:if test="${item.instCode == merInfoForm.instId}">selected</c:if>>${item.instName}</option>
                </c:forEach>
            </select>&nbsp;            
            
            <label>报备状态：</label>
            <select name="quickStatus" id="quickStatus" data-toggle="selectpicker"}>
                <option value=""  <c:if test="${merInfoForm.quickStatus==''}">selected</c:if>>请选择</option>
                <option value="Y"  <c:if test="${merInfoForm.quickStatus=='Y'}">selected</c:if>>已报备</option>
                <option value="N"   <c:if test="${merInfoForm.quickStatus=='N'}">selected</c:if>>未报备</option>
            </select>&nbsp;

            <label>无卡标记：</label>
            <input type="checkbox" name ="quickMark"  id="quickMark" ${merInfoForm.quickMark == 'on'?'checked':''}/>
            
            <button type="submit" class="btn-default" data-icon="search">查询</button>
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
            <th align="center">商户名称</th>
            <th align="center">商户状态</th>
            <th align="center">代理商号</th>
            <th align="center">机构号</th>
            <th align="center">渠道商户号</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${merInfoForm.pagination.list}" varStatus="status">
            <tr data-id="<c:out value="${record.merId}"/>">
                <td align="center"><c:out value="${record.merId}"/></td>
                <td align="center"><c:out value="${record.merName}"/></td>
                <td align="center">
                    <c:if test="${record.merStat=='3'}">未提交</c:if>
                    <c:if test="${record.merStat=='2'}">审核失败</c:if>
                    <c:if test="${record.merStat=='1'}">未审核</c:if>
                    <c:if test="${record.merStat=='0'}">审核通过</c:if>
                    <c:if test="${record.merStat=='4'}">审核中</c:if>
                </td>
                <td align="center"><c:out value="${record.agentId}"/></td>
                <td align="center"><c:out value="${record.instId}"/></td>
                <td align="center"><c:out value="${record.channelMerId}"/></td>
                <td align="center">
                    <input type="button"
                           href="${pageContext.request.contextPath}/mer/register_quick_mer?merId=${record.merId}&channel=HY"
                           class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定？"
                    <c:if test="${record.registedQuickHY == 'Y'}"> disabled</c:if> value="翰银">                    
                    </input>
                    <a href="${pageContext.request.contextPath}/mer/query_quick_mer_detail?id=<c:out value="${record.merId}"/>"
                       class="btn btn-green" data-toggle="navtab" data-id="navtab-detail"
                       data-title="商户详细信息(${record.merName})">编辑</a>
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

<script type="text/javascript">

    function agentMerExport() {
        merInfoForm.action =
            '${pageContext.request.contextPath}/mer/export_agent_mer_report?qMerName=${merInfoForm.merName}&qMerId=${merInfoForm.merId}&qMerStat=${merInfoForm.merStat}&qAgentId=${merInfoForm.agentId}';
        merInfoForm.submit();
        merInfoForm.action = '${pageContext.request.contextPath}/mer/query_agent_mer_list';
    }

</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.checkbox.js"></script>
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

</script>
</body>
</html>
