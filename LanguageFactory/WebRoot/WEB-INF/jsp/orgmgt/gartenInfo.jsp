<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL:JSP标准标签库 -->
<!DOCTYPE html>
<html>
<head>
<!--将共有的css,jsp文件等封装在一个jsp页面中，然后可以在别的页面引入-->
<jsp:include page="../common/meta.jsp"></jsp:include>
<script src="${basePath}/static/common/js/orgmgt/gartenInfo.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=nbZjIball3R0jjtTIiGYaKSU9RLfynjr&services=&t=20180629105706"></script>
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
							<div class="box-header with-border text-center">
								<h3 class="box-title"></h3>
							</div>
							<div class="box-body">
								<form id="updateForm" class="col-xs-11 form-horizontal no-padding" role="form" method="post">
									<input type="hidden" name="gartenId" />
									<input type="hidden" name="gartenLongitude" />
									<input type="hidden" name="gartenLatitude" />
									<!-- <div class="row">
										<div class="col-sm-12">
											<div class="form-group">
												<label class="col-md-2 control-label">校园名称</label>
												<div class="col-md-10">
													<input type="text" class="form-control" name="gartenName" placeholder="请输入校园名称" />
												</div>
											</div>
										</div>
									</div> -->
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<label class="col-md-6 control-label">邮编</label>
												<div class="col-md-6">
													<input type="text" class="form-control" name="gartenZipCode" placeholder="请输入邮编" />
												</div>
												
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="col-md-3 control-label">邮箱</label>
												<div class="col-md-9">
													<input type="text" class="form-control" name="gartenEmail" placeholder="请输入邮箱" />
												</div>
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="col-md-3 control-label">电话</label>
												<div class="col-md-9">
													<input type="text" class="form-control" name="gartenPhone" placeholder="请输入校园联系电话" />
												</div>
											</div>
										</div>
									</div>
								
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-sm-4 control-label">地址</label>
												<div class="col-sm-8 input-group" style="padding-left:15px; padding-right:15px;">
													<span class="input-group-addon"><i class="glyphicon glyphicon-map-marker text-primary"></i></span>
													<select class="form-control gartenProvinceIdSelect2" id="gartenProvinceId" name="gartenProvinceId" style="width: 100%">
														<option selected="selected" value="-1">请选择省份</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<div class="col-sm-12 input-group" style="padding-left:15px; padding-right:15px;">
													<span class="input-group-addon"><i class="glyphicon glyphicon-tree-deciduous text-primary"></i></span>
													<select class="form-control gartenCityIdSelect2" id="gartenCityId" name="gartenCityId" style="width: 100%">
														<option selected="selected" value="-1">请选择城市</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<div class="col-sm-12 input-group" style="padding-left:15px; padding-right:15px;">
													<span class="input-group-addon"><i class="glyphicon glyphicon-grain text-primary"></i></span>
													<select class="form-control gartenDistrictIdSelect2" id="gartenDistrictId" name="gartenDistrictId" style="width: 100%">
														<option selected="selected" value="-1">请选择区县</option>
													</select>
												</div>
											</div>
										</div>
									</div>
								
									<div class="row">	
										<div class="col-sm-12">
											<div class="form-group">
												<label class="col-md-2 control-label">详细地址</label>
												<div class="col-md-10">
													<input type="text" class="form-control" name="gartenAddress" placeholder="请输入校园地址" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="form-group">
												<label class="col-md-2 control-label">地图</label>
												<div class="col-md-10">
													<div id="addressMap" style="width: 100%;height:300px;overflow: hidden;"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="form-group">
												<label class="col-md-2 control-label">校园简介</label>
												<div class="col-md-10">
													<!-- <textarea class="form-control" rows="4" name="gartenDesc" placeholder="请输入校园简介"></textarea> -->
													<textarea id="gartenDesc" name="gartenDesc">请输入校园简介</textarea>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="form-group">
												<label class="col-md-2 control-label">园长致辞</label>
												<div class="col-md-10">
													<!-- <textarea class="form-control" rows="4" name="gartenSpeech" placeholder="请输入园长致辞"></textarea> -->
													<textarea id="gartenSpeech" name="gartenSpeech">请输入园长致辞</textarea>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="form-group">
												<label class="col-md-2 control-label">参观时间</label>
												<div class="col-md-10">
													<input type="text" class="form-control" name="gartenVisitTime" placeholder="请输入参观时间" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="col-md-10 col-md-offset-2">
												<button type="button"
													class="btn btn-flat btn-block btn-primary disabled btn-update">保存</button>
											</div>
										</div>
									</div>
								</form>
							</div>
							<div class="overlay">
				                <i class="fa">
					                <img src="${basePath}static/images/loading.gif"/>
				                </i>
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