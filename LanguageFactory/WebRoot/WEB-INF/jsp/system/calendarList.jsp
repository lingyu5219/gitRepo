<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${menuName}</title>
<!--meta.jsp-->
<jsp:include page="../common/meta.jsp"></jsp:include>

<script src="${basePath}/static/common/js/system/calendarList.js"></script>
<style>
.row {
	margin-top: 5px;
	margin-bottom: 5px;
}
</style>
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
								<form id="queryForm" class="form-horizontal no-padding" role="form" action="${basePath}/system/queryCalendarList" method="post">
					                 <div class="col-md-2"></div>
					                <div class="col-md-6">
					                	<div class="form-group">
						                    <label class="col-md-3 control-label">日期</label>
						                    <div class="col-md-9">
								                <div class="input-daterange input-group" id="datepicker">
												    	<input type="text" readonly class="form-control" name="calendarDate" placeholder="请输入日期" />												    
												</div>
											</div>
										</div>
					                </div>					                
					                <div class="col-xs-6 col-md-1">
			                			<button type="button" id="btn-query" class="btn btn-block btn-primary"><i class="fa fa-search"></i></button>
					                </div>
					                <div class="col-xs-6 col-md-1">
			                			<button type="button" id="btn-add" class="btn btn-block btn-primary"><i class="fa fa-plus"></i></button>
					                </div>
								</form>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="dataListTable" class="table table-condensed table-hover">
									<thead>
									<tr>
										<th>#</th>
										<th>日历表ID</th>
										<th>日期</th>
										<th>是否上课</th>
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
		<jsp:include page="../template/footer.jsp"></jsp:include>
	</div>
</body>
</html>