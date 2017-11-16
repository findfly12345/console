<%--
  Created by IntelliJ IDEA.
  User: LiuBin
  Date: 2017/1/19
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
    String context = request.getRemoteAddr() + request.getServerPort();
%>
<html>
<head>
    <title>商户图片附件</title>
    <style>
        .j_custom_span_pic {
            position: relative;
            display: inline-block;
            height: auto;
            overflow: hidden;
            border: 1px #eeeeee solid;
        }

        .j_custom_span_pic i {
            position: absolute;
            display: block;
            top: 8px;
            right: 8px;
            font-size: 17px;
            color: red;
            display: none;
            cursor: pointer
        }

        .j_custom_span_pic:hover {
            border: 1px #049cff solid
        }

        .j_custom_span_pic:hover i {
            display: block
        }

        .v-error {
            border: 1px solid red;
            color: red;
        }
    </style>
</head>

<body>
<div class="bjui-pageContent" id="addAgentMer">
    <form id="pagerForm" name="merFileForm" data-toggle="validate" novalidate="novalidate"
          action="${pageContext.request.contextPath}/mer/add_agent_mer_image" method="post">
        <div style="margin:15px auto 0; width:96%;">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">商户附件</h3></div>
                    <div class="panel-body">
                        <input name="merId" type="hidden" value="${merId}" id="merIdForImage"></input>
                        <input name="instId" type="hidden" value="${agentId}"></input>
                        <input name="instMerId" type="hidden" value="${merId}"></input>
                        <div class="row">
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress1Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress1 != null}">
                                        <div id="picAddress1" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="营业执照上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress1" value="${tblMerFileInfoDO.picAddress1}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress1}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_01" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-preview-img="true"
                                             data-button-text="营业执照上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress1" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress2Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress2 != null}">
                                        <div id="picAddress2" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="法人代表身份证正面上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress2" value="${tblMerFileInfoDO.picAddress2}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress2}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_02" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="法人代表身份证正面上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress2" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress3Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress3 != null}">
                                        <div id="picAddress3" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="法人代表身份证反面上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress3" value="${tblMerFileInfoDO.picAddress3}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress3}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_03" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="法人代表身份证反面上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress3" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress4Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress4 != null}">
                                        <div id="picAddress4" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="开户许可证复印件上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress4" value="${tblMerFileInfoDO.picAddress4}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress4}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_04" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="开户许可证复印件上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress4" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3" style="margin-bottom: 20px" id="picAddress5Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress5 != null}">
                                        <div id="picAddress5" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="组织机构代码证复印件上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress5" value="${tblMerFileInfoDO.picAddress5}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress5}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_05" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-preview-img="true"
                                             data-button-text="组织机构代码证复印件上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress5" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress6Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress6 != null}">
                                        <div id="picAddress6" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="税务登记证复印件上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress6" value="${tblMerFileInfoDO.picAddress6}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress6}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_06" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="税务登记证复印件上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress6" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress7Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress7 != null}">
                                        <div id="picAddress7" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="商户门头照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress7" value="${tblMerFileInfoDO.picAddress7}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress7}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_07" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="商户门头照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress7" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress8Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress8 != null}">
                                        <div id="picAddress8" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="商户内部照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress8" value="${tblMerFileInfoDO.picAddress8}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress8}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_08" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="商户内部照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress8" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3" style="margin-bottom: 20px" id="picAddress9Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress9 != null}">
                                        <div id="picAddress9" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="商户经营产品照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress9" value="${tblMerFileInfoDO.picAddress9}"
                                               class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress9}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_09" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-preview-img="true"
                                             data-button-text="商户经营产品照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress9" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress10Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress10 != null}">
                                        <div id="picAddress10" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="银行卡正面照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress10"
                                               value="${tblMerFileInfoDO.picAddress10}" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress10}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_10" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="银行卡正面照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress10" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress11Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress11 != null}">
                                        <div id="picAddress11" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="银行卡反面照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress11"
                                               value="${tblMerFileInfoDO.picAddress11}" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress11}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_11" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="银行卡反面照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress11" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress12Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress12 != null}">
                                        <div id="picAddress12" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="其他资料照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress12"
                                               value="${tblMerFileInfoDO.picAddress12}" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress12}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_12" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="商户信息调查表上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress12" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3" style="margin-bottom: 20px" id="picAddress13Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress13 != null}">
                                        <div id="picAddress13" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="其他资料照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress13"
                                               value="${tblMerFileInfoDO.picAddress13}" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress13}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_13" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-preview-img="true"
                                             data-button-text="合同协议照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress13" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-3" style=" margin-bottom: 20px" id="picAddress15Con">
                                <c:choose>
                                    <c:when test="${tblMerFileInfoDO.picAddress15 != null}">
                                        <div id="picAddress15" style="display: none" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="其他资料照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress15"
                                               value="${tblMerFileInfoDO.picAddress15}" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic">
                                            <i class="glyphicon glyphicon-remove-circle"
                                               onclick="click_event($(this));"></i>
                                            <img src="${pageContext.request.contextPath}/mer/showImageFile?path=${tblMerFileInfoDO.picAddress15}"
                                                 width="150" height="150">
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="j_div_pic_14" data-toggle="upload"
                                             data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
                                             data-file-size-limit="1024000000"
                                             data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                             data-multi="false"
                                             data-auto="false"
                                             data-previewImg="true"
                                             data-button-text="其他资料照片上传"
                                             data-on-upload-success="pic_upload_success"
                                             data-icon="cloud-upload"></div>
                                        <input type="hidden" name="picAddress15" value="" class="j_input_pic"></input>
                                        <span class="j_custom_span_pic"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <button type="submit" class="btn-default" data-icon="save">提交</button>
                &nbsp;
            </div>
        </div>
    </form>
</div>
</div>
<div style="display: none;" id="tempImageDivId">
    <div id="" data-toggle="upload" data-uploader="${pageContext.request.contextPath}/mer/uploadAgentImage"
         data-file-size-limit="1024000000"
         data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
         data-multi="false"
         data-auto="false"
         data-previewImg="true"
         data-button-text="其他资料照片上传"
         data-on-upload-success="pic_upload_success"
         data-icon="cloud-upload"></div>
    <input type="hidden" name="" value="" class="j_input_pic"></input>
    <span class="j_custom_span_pic"></span>
</div>

<script>

    var errorFields = '${tblMerAuditRecordDO.errorField}'.split(",");

    setTimeout(function () {
        for (var i = 0; i < errorFields.length; i++) {
            $.CurrentNavtab.find("#" + errorFields[i]+'Con').addClass("v-error");
        }
    }, 1000);

    function pic_upload_success(file, data, $element) {
        var json = $.parseJSON(data)
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            debugger
            $element.parent().find('.j_input_pic').val(json.filename);
            $element.parent().find('.j_custom_span_pic').append('<i class="glyphicon glyphicon-remove-circle" onclick="click_event($(this));"></i><img src="' + '${pageContext.request.contextPath}' + '/mer/showImageFile?path=' + json.filename + '" width="150" height="150" />')
        }
        var btn = $element.find('.bjui-upload-select');
        btn.attr('disabled', "true");
        btn.attr('tachhref', btn.attr('href'));
        btn.removeAttr('href');
    }
    //    $('.glyphicon').unbind('click').on('click',click_event($(this)));
    function click_event(that) {
        debugger
        var parentNode = that.parent().parent();
        var input = parentNode.find('.j_input_pic');
        var span = parentNode.find('.j_custom_span_pic');
        var div = parentNode.find('.bjui-upload');
        var colName = input.attr('name');
        var src = that.next().attr('src');
        var merId = $('#merIdForImage').val();
        $.post("${pageContext.request.contextPath}/mer/delete_image_page", {
            merId: merId,
            path: src,
            colName: colName
        }, function (result) {
            if (result.statusCode == 200) {
                input.val('');
                span.html('');
                if (div.css('display') == 'none') {
                    div.css('display', 'block');
                } else {
                    var btn = parentNode.find('.bjui-upload-select');
                    btn.attr('disabled', false);
                    btn.attr('href', btn.attr('tachhref'));
                }
//                parentNode.html('');
//                var tempImage = $('#tempImageDivId').clone(true);
//                var div = tempImage.find('.bjui-upload');
//                var input = tempImage.find('.j_input_pic');
//                var span = tempImage.find('.j_custom_span_pic');
//                div.attr('id','j_div_pic_'+colName);
//                input.attr('name',colName);
//                tempImage.css('display','block');
//                parentNode.append(div).append(input).append(span);
            }
        });
    }

</script>
</body>
</html>
