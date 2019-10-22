<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="kinder-content">
				<div class="gap hidden-xs hidden-sm"></div>
				<div class="container">
					<h2 class="text-center">${message.msgTitle }</h2>
					<h4 class="text-center">
						<fmt:parseDate value="${message.createTime }" pattern="yyyy-MM-dd" var="createTime"/>
						<fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd" />
					</h4>
					<div class="kinder-content-body">
						<div class="embed-responsive embed-responsive-16by9">
							<video src="${basePath}${message.msgVideoUrl}" controls>
							</video>
						</div>
					</div>
				</div>
				<div class="gap"></div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>