<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath}static/common/js/login.js"></script>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		<div class="content-wrapper login-bg">
			<div class="container">
				<section>
					<div class="col-xs-12 col-md-6 col-md-offset-3">
						<div class="register-box-body">
							<h3 class="text-center">用户登录</h3>
							
							<h4 id="error-info" class="text-red">&nbsp;${info }</h4>
				            <form id="loginForm" action="${basePath}system/login" class="form-horizontal padding-lr-30" role="form" method="post">
				            	<div class="row">
									<div class="form-group">
										<div class="col-md-12 input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
											<input type="text" id="userName" name="userName" class="form-control input-lg" placeholder="账号">
										</div>
									</div>
								</div>
								<br>
				            	<div class="row">
									<div class="form-group">
										<div class="col-md-12 input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
											<input type="password" id="userPassword" name="userPassword" class="form-control input-lg" placeholder="密码">
										</div>
									</div>
								</div>
								<br>
				                <!-- <div class="row">
									<div class="form-group">
										<div class="col-md-8">
											<div class="padding-large">
												<label>
													<input style="width:6px;" type="checkbox" class="icheck"> 记住密码
												</label>
												<a class="" href="">忘记密码?</a>
											</div>
										</div>
									</div>
								</div> -->
								<div class="row">
									<div class="form-group">
										<div class="col-md-12">
											<button type="submit"
												class="btn btn-lg btn-flat btn-block btn-primary btn-save">登录</button>
										</div>
									</div>
								</div>
				            </form>
						</div>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>