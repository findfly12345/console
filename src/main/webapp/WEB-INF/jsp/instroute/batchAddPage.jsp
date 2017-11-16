<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>批量增加路由</title>
</head>
<body>
	<div class="bjui-pageContent">
		<table class="table table-condensed table-hover" width="100%">
			<tr>
				<td>
					<form action="instrout/downloadtemp">
						<button class="btn-blue" type="submit">下载模板</button>
					</form>
				</td>
				<td>
					<div style="display: inline-block; vertical-align: middle;">
						<div id="doc_pic_up" data-toggle="upload"
							data-uploader="instrout/uploader"
							data-file-size-limit="1024000000" data-file-type-exts="*.xlsx"
							data-multi="true" data-on-upload-success="doc_upload_success"
							data-icon="cloud-upload"></div>
						<input type="hidden" name="doc.pic" value="" id="doc_pic">
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="bjui-pageFooter">
		<ul>
			<li><button type="button" class="btn-close">关闭</button></li>
			<li><button type="submit" class="btn-default">保存</button></li>
		</ul>
	</div>
	<script type="text/javascript">
		function doc_upload_success(file, data) {
			var json = $.parseJSON(data)
			$(this).bjuiajax('ajaxDone', json)
		}
	</script>

</body>
</html>
