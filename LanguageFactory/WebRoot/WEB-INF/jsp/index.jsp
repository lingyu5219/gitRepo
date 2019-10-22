<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath}static/common/js/index.js"></script>
	<title>${sysParam}</title>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<div class="container">
				<section class="content-header">
			        <h1><span class="glyphicon glyphicon-education text-large"></span> NIIT优秀讲师</h1>
			        <ol class="breadcrumb">
				        <li class="active"><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
			        </ol>
			    </section>		
				<section class="content">
					<div class="row">
						<c:forEach var="i" begin="1" end="4" step="1">
						<div class="col-md-3 col-sm-6">
							<div class="info-box">
								<span class="info-box-icon bg-aqua text-zero">
									<img src="${basePath }/static/adminLTE/dist/img/user1.png" width="70px" class="img-circle" alt=""/>
								</span>
								<div class="info-box-content">
									<span class="info-box-text">CPU Traffic</span>
									<span class="info-box-number">90<small>%</small></span>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
					
					
					<div class="row">
						<c:forEach var="i" begin="1" end="8" step="1">
						<div class="col-md-4 col-sm-6 col-xs-12">
							<div class="box box-primary border-top-primary">
								<div class="box-header">
									<i class="fa fa-th"></i>
	              					<h3 class="box-title">xxx</h3>
								</div>
								<div class="box-body">
									<ul class="products-list product-list-in-box">
										<li class="item">
											<div class="product-img">
												<img src="${basePath}static/adminLTE/dist/img/default-50x50.gif" alt="Product Image">
											</div>
											<div class="product-info">
												<a href="javascript:void(0)" class="product-title">
													XXXXXXX<span class="label label-warning pull-right">1111</span>
												</a>
												<span class="product-description">xxxxxxxxxxxxxxxxxxxxx</span>
											</div>
										</li>
										
									</ul>
					            </div>
							</div>
						</div>
						</c:forEach>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>