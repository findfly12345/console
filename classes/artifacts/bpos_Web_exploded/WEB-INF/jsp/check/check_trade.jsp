<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 16/10/19
  Time: 上午9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>交易对账</title>
</head>
<body>
<div class="bjui-pageHeader">
  <form id="pagerForm" data-toggle="validate" action="${pageContext.request.contextPath}/check/check_trade" method="post">
    <div class="bjui-searchBar">
      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85">交易日期</label>
        <input type="text" data-toggle="datepicker" id="transDate" name="transDate" data-rule="交易日期:required;date">
      </div>
      <div class="form-group" style="margin: 20px 0 20px; ">
        <label class="control-label x85"> 渠道名称：</label>
        <div style="display: inline-block; vertical-align: middle;">
          <div style="display: inline-block; vertical-align: middle;">
            <select id="gateId" name="gateId" data-toggle="selectpicker" data-width="200" data-rule="渠道名称:required">
              <option value="">请选择</option>
                <option value="U1">钱宝</option>
            </select>
          </div>
        </div>
      </div>

      <div class="form-group" style="margin: 20px 0 20px; ">
        <button type="submit" class="btn-default" data-icon="save">交易对账</button>&nbsp;
      </div>
    </div>
  </form>
</div>
</body>
</html>
