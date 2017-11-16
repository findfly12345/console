<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>商户二维码</title>
</head>
<body>
	<div class="bjui-pageHeader"></div>
	<div class="bjui-pageContent tableContent">
		<form id="pagerForm" name="merQrcodeForm" data-toggle="validate" data-options="{fresh:true}"
			action="${pageContext.request.contextPath}/mer/get_mer_qrcode"
			method="post">
			<div class="row" style="padding: 0 8px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">商户信息</h3>
						</div>
						<div class="panel-body">
							<dl class="detail-list">
								<dd class="row">
									<div class="form-group col-md-4">
										<label class="control-label x110">商户号:</label> <input
											id="merId" name="merId" class="form-control input-nm"
											value="${merQrcodeForm.merId}" size="20" readonly />
									</div>
									<div class="form-group col-md-4">
										<label class="control-label x110">商户名称:</label> <input
											id="merName" name="merName" class="form-control input-nm"
											value="${merQrcodeForm.merName}" size="20" readonly />
									</div>								
								</dd>
								<dd class="row">
									<div class="form-group col-md-4">
										<label class="control-label x110" style="display:none">机构号</label> 
										<input
											id="instId" name="instId" class="form-control input-nm"
											value="${merQrcodeForm.instId}" size="20" readonly style="display:none"/>
									</div>
									<div class="form-group col-md-4">
										<label class="control-label x110" style="display:none">代理商号:</label> 
										<input
											id="agentId" name="agentId" class="form-control input-nm"
											value="${merQrcodeForm.agentId}" size="20" readonly style="display:none"/>
									</div>
									<div class="form-group col-md-4">
										<label class="control-label x110">终端号:</label> <input
											id="termId" name="termId" class="form-control input-nm"
											value="${merQrcodeForm.termId}" size="20" readonly style="display:none"/>
									</div>	
								</dd>								
							</dl>
						</div>
					</div>
				</div>
			</div>

			<div class="row" style="padding: 0 8px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">二维码图片</h3>
						</div>
						<div class="panel-body">

							<div style="margin: 0 0 5px; padding: 10px;">
								<img width="135"
								    src="${merQrcodeForm.srcPath}" id="qrImage">  
							</div>
							<dd class="row">
									<div class="form-group col-md-4" style="padding: 0 50px;">
										<button type="submit" class="btn btn-primary" id="createQrcode" name="createQrcode">生成二维码</button> 
									</div>
							</dd>
						</div>
					</div>
				</div>
		</form>
	</div>
	<div class="bjui-pageFooter" id="pageFooter">
		<ul>
			<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		</ul>
		<div class="pages"></div>
	</div>
	</div>
</body>

<script type="text/javascript">

  function reloadQrcodePage() {

	 var srcpath = "${pageContext.request.contextPath}/mer/show_mer_qrcode?merId=${merQrcodeForm.merId}" + "?" + new Date();
	 document.getElementById("qrImage").src=srcpath;

	 if(document.getElementById("qrImage").complete){
		 alert("1");
	 }
	   
  }
</script>


</html>
