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
    <title>选取对应大商户</title>
    <script type="text/javascript">
        function addAllSelect(){
//            $("tr").siblings(".merCheck").children()
//            var tableObject = $('.table');
//            var tbBody = tableObject.children('tbody'); //获取table对象下的tbody
//            var tbBodyTr = tbBody.find('tr'); //获取tbody下的tr
//
//            tbBodyTr.each(function (){
//
//            });
            var merIdList;


            $(".table tr:gt(0) input:checkbox:checked").each(function() {
                var merId = $(this).parent().children("#label1").text();
                merIdList =  merIdList + merId + ",";

            });
        }

        </script>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="merRouteForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/routeManage/select_big_mer" method="post">
        <input type="hidden" name="pageSize" value="${merRouteForm.pageSize}">
        <input type="hidden" name="pageCurrent" value="${merRouteForm.pageCurrent}">


        <%--<datalist id="refsBigMers" name="refsBigMers" value="${merRouteForm.refsBigMers}"></datalist>--%>
        <div class="bjui-searchBar">
            <%--<a href="${pageContext.request.contextPath}/IM/add_new_inst_page" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="350" data-id="dialog-normal-inst" data-title="新增机构">新增机构</a>&nbsp;--%>
            <label>商户号：</label>
            <input type="text" name="merId" id="merId" value="${merRouteForm.merId}"  size="15"/>&nbsp;
            <label>第</label>
             <input type="text" name="currentTradeCount" id="currentTradeCount" value="${merRouteForm.currentTradeCount}"  size="3">&nbsp;
            <label>笔</label>
            <label>机构名：</label>
                <select name="instCode" id="instCode" data-toggle="selectpicker">
                    <option value="">请选择</option>
                    <c:forEach var="record" items="${merRouteForm.instBOList}" varStatus="status">
                        <option value="${record.instCode}">${record.instName}</option>
                    </c:forEach>
                </select>&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
                <button type="button" class="btn-default" data-icon="save" onclick="addAllSelect()">save</button>&nbsp;
                <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
                    <thead>
                    <tr>
                        <th align="center">1</th>
                        <th align="center">2</th>
                        <th align="center">3</th>
                        <%--<th align="center">注册时间</th>--%>
                        <%--<th align="center">操作</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="record" items="${merRouteForm.refsBigMers}" varStatus="status">
                        <tr>
                            <td align="center"><c:out value="${record.merId}"/> <input type="checkbox" name="checked" id="" data-toggle="icheck" > </td>
                            <input type="hidden" name="refsBigMers.merId" value="${record.merId}">
                            <input type="hidden" name="refsBigMers.checked" value="${record.checked}">
                                <%--<td align="center"><c:out value="${record.createTime}"/></td>--%>
                                <%--<td align="center">--%>
                                <%--a--%>
                                <%--<a href="${pageContext.request.contextPath}/IM/edit_inst_info?id=<c:out value="${record.instCode}"/>" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="350" data-id="dialog-normal-modify"  data-title="修改机构信息(${record.instCode})">修改</a>--%>
                                <%--</td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">1</th>
            <th align="center">2</th>
            <th align="center">3</th>
            <%--<th align="center">注册时间</th>--%>
            <%--<th align="center">操作</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${merRouteForm.refsBigMers}" varStatus="status">
            <tr>
                <td class="merCheck" align="center"><label id="label1"><c:out value="${record.merId}"/></label> <input type="checkbox" name="checked" id="" data-toggle="icheck" > </td>
                <%--<td align="center"><c:out value="${record.createTime}"/></td>--%>
                <%--<td align="center">--%>
                    <%--a--%>
                    <%--<a href="${pageContext.request.contextPath}/IM/edit_inst_info?id=<c:out value="${record.instCode}"/>" class="btn btn-green" data-toggle="dialog" data-width="400" data-height="350" data-id="dialog-normal-modify"  data-title="修改机构信息(${record.instCode})">修改</a>--%>
                <%--</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<div class="bjui-pageFooter" id="pageFooter">
    <div class="pages">

    </div>

</div>

</body>
</html>
