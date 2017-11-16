<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/29
  Time: 下午3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link href="${pageContext.request.contextPath}/resources/BJUI/themes/css/AdminLTE.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/BJUI/themes/css/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/BJUI/themes/css/ionicons-master/css/ionicons.min.css" rel="stylesheet">
</head>
<body>
<section class="content">
    <div class="row">
        <div class="col-lg-6 col-xs-12">
            <!-- small box -->
            <div class="small-box bg-aqua">
                <div class="inner">
                    <h3>${posTradeAmt}<sup style="font-size: 20px">￥</sup></h3>
                    <p>当日POS交易总额（单位：元）</p>
                </div>
                <div class="icon">
                    <i class="ion ion-social-yen"></i>
                </div>
                <a href="trans/gopage" data-toggle="navtab" data-options="{id:'211', title:'当日POS交易详情'}" class="small-box-footer">详情 <i class="fa fa-arrow-circle-right"></i></a>
    </div>
    </div>

        <div class="col-lg-6 col-xs-12">
            <!-- small box -->
            <div class="small-box bg-green">
                <div class="inner">
                    <h3>${posHisTradeAmt}<sup style="font-size: 20px">￥</sup></h3>
                    <p>昨日POS交易总额（单位：元）</p>
                </div>
                <div class="icon">
                    <i class="ion ion-social-yen"></i>
                </div>
                <a href="trans/goPageYes" data-toggle="navtab" data-options="{id:'211', title:'昨日POS交易详情'}" class="small-box-footer">详情 <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6 col-xs-12">
            <!-- small box -->
            <div class="small-box bg-aqua">
                <div class="inner">
                    <h3>${weChatTradeAmt}<sup style="font-size: 20px">￥</sup></h3>
                    <p>当日二维码交易总额（单位：元）</p>
                </div>
                <div class="icon">
                    <i class="ion ion-social-yen"></i>
                </div>
                <a href="/wctrans/gopage" data-toggle="navtab" data-options="{id:'212', title:'当日二维码交易详情'}" class="small-box-footer">详情 <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>

        <div class="col-lg-6 col-xs-12">
            <!-- small box -->
            <div class="small-box bg-green">
                <div class="inner">
                    <h3>${weChatHisTradeAmt}<sup style="font-size: 20px">￥</sup></h3>
                    <p>昨日二维码交易总额（单位：元）</p>
                </div>
                <div class="icon">
                    <i class="ion ion-social-yen"></i>
                </div>
                <a href="/wctrans/goPageYes" data-toggle="navtab" data-options="{id:'212', title:'昨日二维码交易详情'}" class="small-box-footer">详情 <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
    </div>
  <div class="row">
      <div class="col-lg-6 col-xs-12">
          <!-- small box -->
          <div class="small-box bg-aqua">
              <div class="inner">
                  <h3>${noCardTradeAmt}<sup style="font-size: 20px">￥</sup></h3>

                  <p>当日无卡总额（单位：元）</p>
              </div>
              <div class="icon">
                  <i class="ion ion-social-yen"></i>
              </div>
              <a href="/outcome/gopage" data-toggle="navtab" data-options="{id:'213', title:'当日无卡详情'}" class="small-box-footer">详情 <i class="fa fa-arrow-circle-right"></i></a>
          </div>
      </div>

      <div class="col-lg-6 col-xs-12">
          <!-- small box -->
          <div class="small-box bg-green">
              <div class="inner">
                  <h3>${noCardHistTradeAmt}<sup style="font-size: 20px">￥</sup></h3>

                  <p>昨日无卡总额（单位：元）</p>
              </div>
              <div class="icon">
                  <i class="ion ion-social-yen"></i>
              </div>
              <a href="/outcome/goPageYes" data-toggle="navtab" data-options="{id:'213', title:'昨日无卡详情'}" class="small-box-footer">详情 <i class="fa fa-arrow-circle-right"></i></a>
          </div>
      </div>
  </div>
    </section>
</body>
</html>
