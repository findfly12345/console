<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户渠道报备</title>
    <script type="text/javascript">
        function goToMerChannelPre() {
            var merId = $("#merId").val();
            var merName = $("#merName").val();
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var instId = $("#instId").val();
            var instMerId = $("#instMerId").val();
            var instTermId = $("#instTermId").val();
            $('#merChannelPre').attr('href', '${pageContext.request.contextPath}/mer/mer_channel_pre?merId=' + merId + '&merName=' + merName +'&startTime='+startTime+'&endTime='+endTime+ '&instId=' + instId + '&instMerId=' + instMerId + '&instTermId=' + instTermId);
        }
    </script>
</head>
<body>
<div class="bjui-pageHeader">
    <form:form id="pagerForm" name="merInfoForm"
               data-toggle="ajaxsearch"
               action="${pageContext.request.contextPath}/mer/query_mer_info" method="post">
        <input type="hidden" name="pageSize"
               value="${merInfoForm.pageSize}"/>
        <input type="hidden" name="pageCurrent"
               value="${merInfoForm.pageCurrent}"/>

        <div class="bjui-searchBar">
            <table>
                <tr>
                    <td align="right">
                        <label>商户进件时间：</label>
                    </td>
                    <td align="left" colspan="3">
                        <input type="text" name="startTime" id="startTime" value="${merInfoForm.startTime}"
                               data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss">

                        <label>至：</label>
                        <input type="text" name="endTime" id="endTime" value="${merInfoForm.endTime}"
                               data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label>平台商户号：</label>
                    </td>
                    <td align="left">
                        <input type="text" id="merId" name="merId" value="${merInfoForm.merId}" class="form-control"
                               size="15"/>
                    </td>
                    <td align="right">
                        <label>平台商户名：</label>
                    </td>
                    <td align="left">
                        <input type="text" id="merName" name="merName" value="${merInfoForm.merName}"
                               class="form-control" size="15"/>
                    </td>
                    <td align="right">
                        <label>平台商户状态：</label>
                    </td>
                    <td align="left">
                        <select id="merStat" name="merStat" data-toggle="selectpicker">
                            <option value="">请选择</option>
                            <option <c:if test="${merInfoForm.status=='0'}">selected</c:if> value="0">冻结</option>
                            <option <c:if test="${merInfoForm.status=='1'}">selected</c:if> value="1">开通</option>
                            <option <c:if test="${merInfoForm.status=='2'}">selected</c:if> value="2">进件中</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label>所属机构：</label>
                    </td>
                    <td align="left">
                        <select id="instId" name="instId" data-toggle="selectpicker">
                            <option value="">请选择</option>
                            <c:forEach items="${tblBtsInstDOList}" var="tblBtsInstDO">
                                <option <c:if test="${merInfoForm.instId==tblBtsInstDO.instCode}">selected</c:if> value="${tblBtsInstDO.instCode}">${tblBtsInstDO.instName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">
                        <label>机构商户号：</label>
                    </td>
                    <td align="left">
                        <input type="text" id="instMerId" name="instMerId" value="${merInfoForm.instMerId}"
                               class="form-control" size="15"/>
                    </td>
                    <td align="right">
                        <label>机构终端号：</label>
                    </td>
                    <td align="left">
                        <input type="text" id="instTermId" name="instTermId" value="${merInfoForm.instTermId}"
                               class="form-control" size="15"/>
                    </td>
                </tr>
            </table>

            <button type="submit" class="btn-default" data-icon="search">查询</button>
            &nbsp; <a id="merChannelPre" name="merChannelPre" class="btn btn-green" data-title="商户渠道报备" data-id="form"
                      data-toggle="dialog" data-width="800" data-height="400" data-id="user-add" href=""
                      onclick="goToMerChannelPre()">商户渠道报备</a>
            &nbsp; <a class="btn btn-green" data-title="商户渠道报备结果维护" data-id="form"
                      data-toggle="dialog" data-width="800" data-height="400" data-id="user-add"
                      href="${pageContext.request.contextPath}/mer/mer_channel_pre_result">商户渠道报备结果维护</a>
        </div>
    </form:form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%"
           data-nowrap="true">
        <thead>
        <tr>
            <th align="center">平台商户号</th>
            <th align="center">平台商户名</th>
            <th align="center">平台商户状态</th>
            <th align="center">平台终端号</th>
            <th align="center">机构号</th>
            <th align="center">机构商户号</th>
            <th align="center">机构终端号</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${merInfoForm.pagination.list}" varStatus="status">
            <tr>
                <td align="center">${record.merId}</td>
                <td align="center">${record.merName}</td>
                <c:if test="${record.merStat=='0'}">
                    <td align="center">冻结</td>
                </c:if>
                <c:if test="${record.merStat=='1'}">
                    <td align="center">开通</td>
                </c:if>
                <c:if test="${record.merStat=='2'}">
                    <td align="center">进件中</td>
                </c:if>
                <td align="center">${record.termId}</td>
                <td align="center">${record.instId}</td>
                <td align="center">${record.instMerId}</td>
                <td align="center">${record.instTermId}</td>
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
</body>
</html>
