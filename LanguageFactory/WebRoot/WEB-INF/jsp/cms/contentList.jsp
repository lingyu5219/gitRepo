<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/meta.jsp"></jsp:include>
<script src="${basePath}static/common/js/cms/contentList.js"></script>
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
					                <input type="hidden" name="contentColumnId" value="0"/>
					                <div class="row">
						                <div class="col-md-4">
						                	<div class="form-group">
												<label class="col-md-3 control-label" for="contentColumnName">所属栏目</label>
												<div class="col-md-9">
													<input type="text" readonly class="form-control tree-select" name="contentColumnName" placeholder="请选择所属栏目" />
													<div class="tree-menu tree-menu-hide">
								                   		<div id="columnTree"></div>
								                   	</div>
												</div>
											</div>
						                </div>
						                <div class="col-md-8">
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
					                </div>
					                <div class="row">
					                	<div class="col-md-4">
						                	<div class="form-group">
							                    <label class="col-md-3 control-label" for="contentTitle">标题</label>
							                    <div class="col-md-9">
							                    	<input type="text" class="form-control" id="contentTitle" name="contentTitle" placeholder="请输入标题">
							                    </div>
							                </div>
						                </div>
						                <div class="col-md-4">
											<div class="form-group">
												<label class="col-md-4 control-label">内容类型</label>
												<div class="col-md-8">
													<select class="form-control" name="contentType" style="width: 100%">
											    		<option value="0" selected="selected">请选择内容类型</option>
											    		<option value="1">本站内容</option>
											    		<option value="2">外部链接</option>
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
										<th>标题</th>
										<th>所属栏目</th>
										<th>类型</th>
										<th>状态</th>
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