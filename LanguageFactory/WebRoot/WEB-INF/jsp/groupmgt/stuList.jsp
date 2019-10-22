<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}/static/common/js/groupmgt/stuList.js"></script>
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
								<form id="queryForm" class="form-horizontal no-padding" role="form" method="post">
					                <div class="row">
						                <div class="col-md-4">
						                	<div class="form-group">
							                    <label class="col-md-3 control-label" for="stuName">宝宝姓名</label>
							                    <div class="col-md-9">
							                    	<input type="text" class="form-control" id="stuName" name="stuName" placeholder="请输入宝宝姓名">
							                    </div>
											</div>
						                </div>
						                <div class="col-md-8">
											<div class="form-group">
							                    <label class="col-md-2 control-label">报名时间</label>
							                    <div class="col-md-10">
									                <div class="input-daterange input-group" id="datepicker">
													    <input type="text" readonly class="form-control" name="createTimeBegin" placeholder="请输入开始日期" />
													    <span class="input-group-addon">to</span>
													    <input type="text" readonly class="form-control" name="createTimeEnd" placeholder="请输入结束日期" />
													</div>
												</div>
							                </div>
						                </div>
					                </div>
					                <div class="row">
					                    <div class="col-md-4">
					                		<div class="form-group">
						                		<label class="col-md-3 control-label" for="stuFatherPhone">父亲电话</label>
							                    <div class="col-md-9">
									                <input type="text" class="form-control" id="stuFatherPhone" name="stuFatherPhone" placeholder="请输入父亲电话">
												</div>
											</div>
										</div>
					                    <div class="col-md-4">
					                		<div class="form-group">
					                			<label class="col-md-4 control-label" for="stuMatherPhone">母亲电话</label>
							                    <div class="col-md-8">
						                			<input type="text" class="form-control" id="stuMatherPhone" name="stuMatherPhone" placeholder="请输入母亲电话">
												</div>
											</div>
										</div>
						                <div class="col-xs-6 col-md-2">
				                			<button type="button" id="btn-query" class="btn btn-block btn-primary"><i class="fa fa-search"></i></button>
						                </div>
						               <!--  <div class="col-xs-6 col-md-2">
				                			<button type="button" id="btn-add" class="btn btn-block btn-primary"><i class="fa fa-plus"></i></button>
						                </div> -->
						            </div>
								</form>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="dataListTable" class="table table-condensed table-hover">
									<thead>
									<tr>
										<th>#</th>
										<th>宝宝姓名</th>
										<th>家庭地址</th>
										<th>父亲电话</th>
										<th>母亲电话</th>
										<th>报名时间</th>
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