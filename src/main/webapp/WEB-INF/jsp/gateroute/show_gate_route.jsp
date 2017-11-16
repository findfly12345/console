<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/2/16
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="bjui-pageContent" id="bindGateRoute">
    <form id="pagerForm" name="pagerForm"  data-toggle="validate" novalidate="novalidate"
          action="${pageContext.request.contextPath}/gateroute/show_bind_route_detail" method="post">
        <div class="bjui-searchBar">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">路由信息</h3></div>
                    <div class="panel-body">
                        <input type="hidden" id="instMerId" value="${gateBankPosInfoForm.instMerId}" name="instMerId" class="form-control">
                        <input type="hidden" id="instMerTermId" value="${gateBankPosInfoForm.instMerTermId}" name="instMerTermId" class="form-control">
                        <div class="form-group" style="margin: 20px 0 20px; ">
                            <label >网关号:</label>
                            <input name="gateId" id="gateId"  data-toggle="selectpicker" data-width="200" disabled="true" 
                                <c:if test="${gateBankPosInfoForm.gateId == 'U1'}"> value = "U1-钱宝"</c:if>
                                <c:if test="${gateBankPosInfoForm.gateId == 'U2'}"> value = "U2-银嘉"</c:if>
                            </input>
                        </div>
                        <div class="form-group" style="margin: 20px 0 20px; ">
                                <label >商户号:</label>
                                <input  id="posMerId" name="posMerId" class="form-control input-nm" size="20" value="${gateBankPosInfoForm.posMerId}" disabled="true"/>
                        </div>
                        <div class="form-group" style="margin: 20px 0 20px; ">
                                <label >终端号:</label>
                                <input  id="posTermId" name="posTermId" class="form-control input-nm" size="20" value="${gateBankPosInfoForm.posTermId}" disabled="true"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" id="closeSelectA">关闭</button></li>
    </ul>
</div>
</body>
</html>
