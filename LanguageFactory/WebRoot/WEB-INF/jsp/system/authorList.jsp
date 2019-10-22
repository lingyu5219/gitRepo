<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<!--将共有的css,jsp文件等封装在一个jsp页面中，然后可以在别的页面引入-->
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}/static/common/js/system/authorList.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">
		<jsp:include page="../template/head.jsp"></jsp:include>
		<jsp:include page="../template/menu.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<jsp:include page="../template/contentHeader.jsp"></jsp:include>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header with-border">
								<form id="queryForm" class="form-horizontal no-padding"
									role="form" action="${basePath}/user/queryUserList"
									method="post">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-2 control-label">日期</label>
											<div class="col-md-10">
												<div class="input-daterange input-group" id="datepicker">
													<input type="text" readonly class="form-control"
														name="createTimeBegin" placeholder="请输入开始日期" /> <span
														class="input-group-addon">to</span> <input type="text"
														readonly class="form-control" name="createTimeEnd"
														placeholder="请输入结束日期" />
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-md-3 control-label" for="roleId">角色</label>
											<div class="col-md-9">
												<select class="form-control roleIdSelect2" name="roleId" style="width: 100%">
													<option selected="selected" value="0">请选择角色</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-1">
										<button type="button" id="btn-query"
											class="btn btn-block btn-primary">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</form>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="dataListTable"
									class="table table-condensed table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>角色</th>
											<th>菜单</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
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