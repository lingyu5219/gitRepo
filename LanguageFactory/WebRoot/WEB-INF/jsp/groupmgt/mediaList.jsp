<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/meta.jsp"></jsp:include>
	<script src="${basePath}/static/common/js/groupmgt/mediaList.js"></script>
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
							                    <label class="col-md-3 control-label" for="mediaTitle">资源名称</label>
							                    <div class="col-md-9">
							                    	<input type="text" class="form-control" id="mediaTitle" name="mediaTitle" placeholder="请输入资源名称">
							                    </div>
							                </div>
						                </div>
						                <div class="col-md-6">
						                	<div class="form-group">
							                    <label class="col-md-3 control-label">日期</label>
							                    <div class="col-md-9">
									                <div class="input-daterange input-group" id="datepicker">
													    	<input type="text" readonly class="form-control" name="createTimeBegin" placeholder="请输入开始日期" />
													    <span class="input-group-addon">to</span>
													    	<input type="text" readonly class="form-control" name="createTimeEnd" placeholder="请输入结束日期" />
													</div>
												</div>
											</div>
						                </div>
						                <div class="col-md-2">
											<div class="switch" data-on-label="是" data-off-label="否">
												<input type="checkbox" name="mediaIsHome" value="0"/>
											</div>
										</div>
					                </div>
					                <div class="row">
					                    <div class="col-md-4">
					                		<div class="form-group">
					                			<label class="col-xs-12 col-md-3 control-label">所属集团</label>
												<div class="col-xs-3 col-md-3">
													<div class="switch" data-on-label="是" data-off-label="否">
														<input type="checkbox" name="isGroupMedia"/>
													</div>
												</div>
												<div class="col-xs-9 col-md-6">
													<select class="form-control mediaGartenIdSelect2" name="mediaGartenId" style="width: 100%">
											    		<option selected="selected" value="-1">请选择所属校园</option>
													</select>
												</div>
											</div>
										</div>
					                    <div class="col-md-4">
					                		<div class="form-group">
						                		<label class="col-md-4 control-label" for="mediaPattern">资源格式</label>
							                    <div class="col-md-8">
									                <select class="form-control mediaPatternSelect2" name="mediaPattern" style="width: 100%">
											    		<option selected="selected" value="0">请选择资源格式</option>
											    		<option value="1">图片</option>
											    		<option value="2">视频</option>
													</select>
												</div>
											</div>
										</div>
						                <div class="col-xs-6 col-md-2">
				                			<button type="button" id="btn-query" class="btn btn-block btn-primary"><i class="fa fa-search"></i></button>
						                </div>
						                <div class="col-xs-6 col-md-2">
				                			<button type="button" id="btn-add" class="btn btn-block btn-primary"><i class="fa fa-plus"></i></button>
						                </div>
						            </div>
								</form>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="dataListTable" class="table table-condensed table-hover">
									<thead>
									<tr>
										<th>#</th>
										<th>资源名称</th>
										<th>缩略图</th>
										<th>首页轮播</th>
										<th>前端显示</th>
										<th>所属</th>
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