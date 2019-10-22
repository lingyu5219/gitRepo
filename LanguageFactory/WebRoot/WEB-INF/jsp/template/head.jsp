<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="main-header">
	<!-- Logo -->
	<a href="${basePath }admin" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels 系统名称缩写 -->
		<span class="logo-mini" style="font-size:14px;"><b> ${paramMap.systemTitleMini.paramName}</b></span> <!-- logo for regular state and mobile devices 系统名称-->
		<span class="logo-lg" style="font-size:16px;">
			<img width="50" height="50" class="pull-left" src="${basePath }static/front/images/logo.png"/>
			<b> ${paramMap.systemTitle.paramName}</b>
		</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only"></span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<%-- <li><a href="${basePath }static/adminLTE/index.html" target="_blank">API</a></li> --%>
				<li><a href="${basePath }front/index" target="_blank">网站首页</a></li>
				<!-- Messages: style can be found in dropdown.less-->
				<li id="noticeList" class="dropdown messages-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-envelope-o"></i><span class="label label-danger notice-count"></span>
					</a>
					<ul class="dropdown-menu">
						<li class="header">你有<strong class="text-red">0</strong>条消息</li>
						<li>
							<ul class="menu">
								
							</ul>
						</li>
						<li class="footer"><a href="${basePath }msgmgt/msgList">查看更多</a></li>
					</ul>
				</li>
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding-bottom:12px;">
						<c:if test="${not empty personalInfo.personal.staffPic}">
						<img style="object-fit: cover;" src="${basePath }${personalInfo.personal.staffPic}" class="user-image" alt="User Image"/>
						</c:if>
						<c:if test="${empty personalInfo.personal.staffPic}">
						<img style="object-fit: cover;" src="${basePath}static/images/man.png" class="user-image" alt="User Image">
						</c:if>
						<span class="hidden-xs">${user.userName }</span>
					</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header">
							<c:if test="${not empty personalInfo.personal.staffPic}">
							<img style="object-fit: cover;" src="${basePath }${personalInfo.personal.staffPic}" class="img-circle" alt="User Image"/>
							</c:if>
							<c:if test="${empty personalInfo.personal.staffPic}">
							<img style="object-fit: cover;" src="${basePath}static/images/man.png" class="img-circle" alt="User Image">
							</c:if>
							<p><b>
							<c:if test="${not empty personalInfo}">
								<c:if test='${personalInfo["flag"] == "1"}'>
									${personalInfo["personal"].batchName} - ${personalInfo["personal"].stuName}
								</c:if>
								<c:if test='${personalInfo["flag"] == "2"}'>
									${personalInfo["personal"].positionName} - ${personalInfo["personal"].staffName}
								</c:if>
							</c:if>
							</b></p>
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="${basePath }forward?page=system/userInfo" class="btn btn-default btn-flat">个人信息</a>
							</div>
							<div class="pull-right">
								<a href="${basePath }system/logout" class="btn btn-default btn-flat">退出</a>
							</div>
							<!-- <div class="pull-right">
								<a href="#" class="btn btn-default btn-flat">修改密码</a>
							</div> -->
						</li>
					</ul>
				</li>
				<!-- Control right Sidebar Toggle Button (settings) -->
				<%-- <li>
					<a href="${basePath }system/logout">退出</a>
				</li> --%>
			</ul>
		</div>
	</nav>
</header>
<script src="${basePath}static/common/js/template/head.js"></script>