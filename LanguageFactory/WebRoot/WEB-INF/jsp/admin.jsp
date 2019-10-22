<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<title>${sysParam}</title>
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">
		<jsp:include page="template/head.jsp"></jsp:include>
		<jsp:include page="template/menu.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<section class="content-header">
				<h4><b>管理中心</b> <small></small></h4>
				<ol class="breadcrumb">
					<li><a href="${basePath }forward?page=admin"><i class="glyphicon glyphicon-home"></i>首页</a></li>
				</ol>
			</section>
			
			<!-- Main content -->
			<section class="content">
			登录之后的管理页面
			</section>
			
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>