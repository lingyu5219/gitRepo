<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}/static/common/js/groupmgt/historyList.js"></script>
	<style type="text/css">
	.col-md-1,.col-md-2,.col-md-4,.col-md-8 {
	padding: 0px;
	
	}
	.col-md-8 {
	padding-left: 15px;}
	.col-md-1 {
	padding-left: 30px;}
	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">
		<jsp:include page="../template/head.jsp"></jsp:include>
		<jsp:include page="../template/menu.jsp"></jsp:include>
		<div class="content-wrapper">
			<jsp:include page="../template/contentHeader.jsp"></jsp:include>
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header with-border">
								<form id="queryForm" class="form-horizontal no-padding"
									role="form" action="${basePath}/groupmgt/queryHistoryList" method="post">
									<div class="col-md-5">
										<div class="form-group">
											<label class="col-md-3 control-label" for="historyTitle">历程标题</label>
											<div class="col-md-9">
												<input type="text" class="form-control" id="historyTitle"
													name="historyTitle" placeholder="请输入历程标题">
											</div>
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<label class="col-md-3 control-label">历程日期</label>
											<div class="col-md-9">
												<div class="input-daterange input-group" id="datepicker">
													<input type="text" readonly class="form-control"
														name="historyDateStart" placeholder="请输入开始日期" />
														<span class="input-group-addon">to</span>
														<input readonly class="form-control" name="historyDateEnd" placeholder="请输入结束日期" type="text">
												</div>
											</div>
										</div>
									</div>
									<!-- <div class="col-md-4">
										<div class="form-group">
											<label class="col-md-4 control-label">日期</label>
											<div class="col-md-8">
												<div class="input-daterange input-group" id="datepicker">
													<input type="text" readonly="" class="form-control" name="startTime" placeholder="请输入开始日期"> <span class="input-group-addon">to</span> <input type="text" readonly="" class="form-control" name="endTime" placeholder="请输入结束日期">
												</div>
											</div>
										</div>
									</div> -->
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
							<div class="box-body">

								<table id="dataListTable"
									class="table table-condensed table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>ID</th>
											<th>标题</th>
											<th>内容</th>
											<th>历程日期</th>
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
	</div>
</body>
</html>