<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="bjui-leftside">
    <div id="bjui-sidebar-s">
        <div class="collapse"></div>
    </div>
    <div id="bjui-sidebar">
        <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
        <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">
            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                	<shiro:hasRole name="超级管理员"> 
                    	<h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse4" class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;系统管理</a></h4>
                	</shiro:hasRole>
                </div>
                <div id="bjui-collapse4" class="panel-collapse panelContent collapse">
                    <div class="panel-body" >
                        <ul id="bjui-tree4" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                        	<shiro:hasPermission name="user:manage">
                        	<li data-id="user" data-pid="0">权限管理</li>
                            <li data-id="user_0" data-pid="user" data-url="<%=request.getContextPath()%>/user/page" data-tabid="user_0" data-fresh="true" data-reloadWarn="true">用户列表</li>
                            <li data-id="user_1" data-pid="user" data-url="<%=request.getContextPath()%>/user/index.do" data-tabid="user_1" data-fresh="true" data-reloadWarn="true">角色管理</li>
                        	<li data-id="user_2" data-pid="user" data-url="<%=request.getContextPath()%>/user/index.do" data-tabid="user_2" data-fresh="true" data-reloadWarn="true">节点管理</li>
                        	</shiro:hasPermission>
                        	<li data-id="system" data-pid="0">系统设置</li>
                            <li data-id="system_0" data-pid="system" data-url="<%=request.getContextPath()%>/Config/index.do" data-tabid="system_0" data-fresh="true" data-reloadWarn="true">基础配置</li>
                        	<li data-id="system_2" data-pid="system" data-url="<%=request.getContextPath()%>/user/index.do" data-tabid="system_2" data-fresh="true" data-reloadWarn="true">导航管理</li>
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
        </div>
    </div>
</div>
    
