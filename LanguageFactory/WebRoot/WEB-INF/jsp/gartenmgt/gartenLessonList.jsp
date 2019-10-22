<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}static/common/js/gartenmgt/gartenLessonList.js"></script>
	<style type="text/css">
	.selectRow {
	background: transparent;
	padding: 0;
	}
	.selectRow i {
	font-size: 1.5em;
	color: grey;
	}
	.selectRow.selected i {
	color: red;
	}
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
									role="form" method="post">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-4 control-label" for="historyTitle">课程类型</label>
												<div class="col-md-8">
													<select class="form-control lessonTypeIdSelect2" name="lessonTypeId" style="width: 100%">
											    		<option selected="selected" value="0">请选择课程类型</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-4 control-label" for="historyTitle">课程名称</label>
												<div class="col-md-8">
													<input type="text" class="form-control" id="lessonName"
														name="lessonName" placeholder="请输入课程名称">
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="col-md-2 control-label">日期</label>
												<div class="col-md-10">
													<div class="input-daterange input-group" id="datepicker">
														<input type="text" readonly="" class="form-control" name="startTime" placeholder="请输入开始日期"> <span class="input-group-addon">to</span> <input type="text" readonly="" class="form-control" name="endTime" placeholder="请输入结束日期">
													</div>
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
									</div>
									<!-- <div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="description">描述</label>
												<div class="col-md-8">
													<input type="text" class="form-control" id="description" name="description" placeholder="请输入描述">
												</div>
											</div>
										</div>
										
										
									</div> -->
								</form>
							</div>
							<div class="box-body">

								<table id="dataListTable"
									class="table table-condensed table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>ID</th>
											<th>课程类型</th>
											<th>课程名称</th>
											<th>描述</th>
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