<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}/static/common/js/user/userList.js"></script>
	<title>${menuName }</title>
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">
		<jsp:include page="../template/head.jsp"></jsp:include>
		<jsp:include page="../template/menu.jsp" flush="false"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<jsp:include page="../template/contentHeader.jsp"></jsp:include>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header with-border">
								<h1>创建新模块</h1>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<form action="${basePath}/model/createModel" method="POST">
									模块名<input type="text" name="modelName"/>
									<br/>
									模块描述<textarea name="modelDescription"></textarea>
									<br/>
									角色名<input type="text" name="roleId"/>
									<br/>
									映射地址<input type="text" name="localUrl"/> 
									<br/>
									<input type="submit" value="创建"/>
								</form>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<jsp:include page="../template/footer.jsp"></jsp:include>
	</div>
</body>
</html>