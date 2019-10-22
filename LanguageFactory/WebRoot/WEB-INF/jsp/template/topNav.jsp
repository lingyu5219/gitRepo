<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="main-header">
    <nav class="navbar navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a href="#" class="navbar-brand" style="height:50px; padding:0; line-height:46px;">
					<img width="50" height="50" class="pull-left" src="${basePath }static/front/images/logo.png"/>
					<b id="systemTitle"></b>
                </a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                	<i class="fa fa-bars"></i>
          		</button>
        	</div>
	        <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
	          	<ul class="nav navbar-nav">
		            <li><a href="${basePath}">网站首页</a></li>
		            <li><a href="${basePath}front/gartenList">园所分布</a></li>
		            <li><a href="${basePath}front/eduFeature">教育特色</a></li>
		            <li><a href="${basePath}front/parentService">家长服务</a></li>
		            <li><a href="${basePath}front/groupNews">新闻活动</a></li>
	          	</ul>
	        </div>
	        <div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="dropdown" style="display:none;">
						<!-- Menu toggle button -->
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span>客户端</span>
							<span class="caret"></span>
						</a>
		              	<ul class="dropdown-menu">
		                	<li class="text-center">
				                <img alt="APP下载" src="${basePath}static/images/download-app-qrcode.png"/>
		                    </li>
		                    <li class="text-center">
		                    	<span class="padding-large"><a href="${basePath}download/android"><span class="fa fa-android icon-large"></span></a></span>
			                    <span class="padding-large"><a href="${basePath}download/ios"><span class="fa fa-apple icon-large"></span></a></span>
		                    </li>
		              	</ul>
		            </li>
		            <c:if test="${user == null }">
		            <li>
		            	<a href="${basePath }system/login">登录</a>
		            </li>
		            </c:if>
					<c:if test="${user != null }">
					<li class="user user-menu">
						<a href="${basePath}admin">
							<c:if test="${not empty user.picPath}">
							<img src="${basePath }${user.picPath }" class="user-image" alt="User Image"/>
							</c:if>
							<c:if test="${empty user.picPath}">
							<img src="${basePath}static/images/man.png" class="user-image" alt="User Image">
							</c:if>
							<span class="hidden-xs">${user.userName }</span>
						</a>
					</li>
					<li>
						<a href="${basePath }system/logout">退出</a>
					</li>
					</c:if>
				</ul>
	        </div>
      	</div>
    </nav>
</header>
<script>
	$(function(){
		var stytemTitle = '${paramMap.systemTitle.paramName}';
		var noHtml = stytemTitle.replace(/<\/?[^>]*>/g, ''); 
		$("#systemTitle").append("&nbsp;&nbsp;&nbsp;" + noHtml);
	});
	
</script>