<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">
		<jsp:include page="../template/head.jsp"></jsp:include>
		<jsp:include page="../template/menu.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<section class="content-header">
				<h4><b>消息详情</b> <small></small></h4>
				<ol class="breadcrumb">
					<li><a href="${basePath }forward?page=admin"><i class="glyphicon glyphicon-home"></i>首页</a></li>
					<li><a href="${basePath }msgmgt/msgList">系统消息</a></li>
					<li class="active">
					<c:choose>  
						<c:when test="${fn:length(notice.noticeTitle) > 20}">  
							<c:out value="${fn:substring(notice.noticeTitle, 0, 20)}......" />  
						</c:when>  
						<c:otherwise>  
							<c:out value="${notice.noticeTitle}" />  
						</c:otherwise>  
					</c:choose> 
					</li>
				</ol>
			</section>
			
			<!-- Main content -->
			<section class="content padding-lr-30">
				<div class="page-header text-center">
				  	<h3>${notice.noticeTitle }</h3>
				  	<h5 class="text-muted">发布于：
					  	<fmt:parseDate value="${notice.createTime }" var="createTime" pattern="yyyy-MM-dd HH:mm:ss"/>
					  	<fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm" />
				  	</h5>
				</div>
				<c:if test="${not empty notice.remark}">
				<blockquote><p class="text-normal">${notice.remark}</p></blockquote>
				</c:if>
				${notice.noticeContent }
				<!-- <p class="text-right">
					<b>阅读：<span class="text-red">100</span></b>
					
				</p> -->
				
			</section>
			
		</div>
		<jsp:include page="../template/footer.jsp"></jsp:include>
	</div>
</body>
</html>