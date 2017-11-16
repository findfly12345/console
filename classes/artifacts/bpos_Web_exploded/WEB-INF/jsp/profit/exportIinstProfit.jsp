<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>增加规则</title>
</head>
<body>
<div class="bjui-pageContent">
    <form:form class="nice-validator n-red" data-alertmsg="false"
              action="profit/download" novalidate="novalidate"
               modelAttribute="pnrOrdLogFomr" id="pnrOrdLogFomr">
        <table class="table table-condensed table-hover" width="100%">
            <tr>
                <td>
                    <label>所属机构：</label>
                    <select name="instId" id="instId" data-toggle="selectpicker">
                        <option value="" style="width: 150px;">请选择</option>
                        <c:forEach var="item" items="${tblBtsInstDOList}" varStatus="status">
                            <option value="${item.instCode}">
                                   ${item.instName}</option>
                        </c:forEach>
                    </select>&nbsp;
                </td>
            </tr>
            <tr>
                <td>
                    <label>月 份：</label>
                    <select name="month" id="month" data-toggle="selectpicker" >
                        <option style="width: 150px;" value="">请选择</option>
                            <option value="1"> 一月</option>
                            <option value="2"> 二月</option>
                            <option value="3"> 三月</option>
                            <option value="4"> 四月</option>
                            <option value="5"> 五月</option>
                            <option value="6"> 六月</option>
                            <option value="7"> 七月</option>
                            <option value="8"> 八月</option>
                            <option value="9"> 九月</option>
                            <option value="10"> 十月</option>
                            <option value="11"> 十一月</option>
                            <option value="12"> 十二月</option>
                    </select>&nbsp;
                </td>
            </tr>

        </table>
    </form:form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button type="submit" class="btn-default">导出</button></li>
    </ul>
</div>
</body>
</html>