<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}/static/common/js/hrmgt/positionList.js"></script>
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
									role="form" action="${basePath}/position/queryPosition"
									method="post">

									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-3 control-label">日期</label>
											<div class="col-md-9">
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
											<label class="col-md-3 control-label" for="positionName">职位名</label>
											<div class="col-md-9">
												<input type="text" class="form-control" id="positionName"
													name="positionName" placeholder="请输入职位名">
											</div>
										</div>
									</div>
									<!-- 搜索 -->
									<div class="col-xs-6 col-md-1">
										<button type="button" id="btn-query"
											class="btn btn-block btn-primary">
											<i class="fa fa-search"></i>
										</button>
									</div>
									<!-- 添加 -->
									<div class="col-xs-6 col-md-1">
										<button type="button" id="btn-add"
											class="btn btn-block btn-primary">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</form>
							</div>
							<!-- box-header -->

							<!-- 职位信息表 -->
							<!-- /.box-body -->
							<div class="box-body">
								<table id="dataListTable"
									class="table table-condensed table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>职位ID</th>
											<th>职位名</th>
											<th>备注</th>
											<th>创建者</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>

							</div>
							<!-- box-body -->
						</div>
						<!-- box -->
					</div>
					<!-- xs-12 -->
				</div>
				<!-- row -->
			</section>
		</div>
		<!-- <div class="content-wrapper"> -->
		<jsp:include page="../template/footer.jsp"></jsp:include>
	</div>
	<!-- <div class="wrapper"> -->

</body>
</html>