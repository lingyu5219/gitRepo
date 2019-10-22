<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}/static/common/js/hrmgt/staffGartenList.js"></script>
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
									role="form" action="${basePath}/staff/queryStaff" method="post">
									<input type="hidden" name="staffGartenId" value="${personalInfo.personal.staffGartenId }"/>
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
											<label class="col-md-4 control-label" for="staffName">员工姓名</label>
											<div class="col-md-8">
												<input type="text" class="form-control" id="staffName"
													name="staffName" placeholder="请输入职工姓名">
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

							<!-- 教职工信息表 -->

							<!-- /.box-body -->
							<div class="box-body">

								<table id="dataListTable"
									class="table table-condensed table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>ID</th>
											<th>姓名</th>
											<th>员工归属</th>
											<th>出生日期</th>
											<th>电话</th>
											<th>邮箱</th>
											<th>职位</th>
											<th>创建人</th>
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