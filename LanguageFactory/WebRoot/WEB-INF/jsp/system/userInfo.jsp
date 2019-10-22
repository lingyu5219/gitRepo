<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL:JSP标准标签库 -->
<!DOCTYPE html>
<html>
<head>
<!--将共有的css,jsp文件等封装在一个jsp页面中，然后可以在别的页面引入-->
<jsp:include page="../common/meta.jsp"></jsp:include>
<script src="${basePath}/static/common/js/system/userInfo.js"></script>
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
					<div class="col-sm-6">
						<div class="box">
							<div class="box-header with-border text-center">
								<h3 class="box-title">员工信息</h3>
							</div>
							<div class="box-body">
								<form id="staffUpdateForm" class="form-horizontal no-padding" role="form" method="post">
									<input type="hidden" name="staffId" value="${personalInfo.personal.staffId }"/>
									<input type="hidden" name="staffGartenId" value="${personalInfo.personal.staffGartenId }"/>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">姓名</label>
												<div class="col-md-9">
													<input type="text" class="form-control" id="staffName" name="staffName"
														placeholder="请输入姓名" value="${personalInfo.personal.staffName }"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">出生日期</label>
												<div class="col-md-9">
													<input type="text" readonly class="form-control datepicker"
														id="staffBirthday" name="staffBirthday" placeholder="请输入出生日期" value="${personalInfo.personal.staffBirthday }"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">电话号码</label>
												<div class="col-md-9">
													<input type="text" class="form-control" id="staffPhone"
														name="staffPhone" placeholder="请输入电话号码"  value="${personalInfo.personal.staffPhone }"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">Email</label>
												<div class="col-md-9">
													<input type="text" class="form-control" id="staffEmail"
														name="staffEmail" placeholder="请输入Email" value="${personalInfo.personal.staffEmail }"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">职位</label>
												<div class="col-md-9">
													<input type="text" class="form-control" id="positionName" disabled
														name="positionName" value="${personalInfo.personal.positionName }"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">头像</label>
												<div class="col-md-9">
													<input type="hidden" name="staffPicChanged" value="0"/>
													<input type="hidden" name="staffOriginalPic" value="${personalInfo.personal.staffPic }"/>
													<input type="hidden" name="staffPic" value="${personalInfo.personal.staffPic }"/>
													<input type="hidden" name="staffPicName" value="${personalInfo.personal.staffPicName }"/>
													<input type="hidden" name="staffPicSize" value="${personalInfo.personal.staffPicSize }"/>
													<div id="fileList" class="uploader-list"></div>
													<a id="headPhotoBtn" class="btn btn-primary">选择图片</a>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="col-md-9 col-md-offset-3">
												<button id="staffUpdate" type="button" class="btn btn-flat btn-block btn-primary btn-update">保存</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="col-sm-6">
						<div class="box">
							<div class="box-header with-border text-center">
								<h3 class="box-title">修改密码</h3>
							</div>
							<div class="box-body">
								<form id="passwordResetForm" class="form-horizontal no-padding" role="form" method="post">
									<input type="hidden" name="userId" value="${user.userId }"/>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">原密码</label>
												<div class="col-md-9">
													<input type="password" class="form-control" id="userPassword" name="userPassword"
														placeholder="请输入原密码"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">新密码</label>
												<div class="col-md-9">
													<input type="password" class="form-control" id="newPassword" name="newPassword"
														placeholder="请输入新密码"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">确认密码</label>
												<div class="col-md-9">
													<input type="password" class="form-control" id="newPassword2" name="newPassword2"
														placeholder="请再次输入新密码"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="form-group">
												<label class="col-md-3 control-label">验证码</label>
												<div class="col-md-5">
													<input type="text" class="form-control" name="verifyCode" placeholder="请输入验证码" />
												</div>
												<div class="col-md-4">
													<img src="${ctx}kaptcha" id="kaptchaImage" title="看不清，点击换一张">
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-11">
											<div class="col-md-9 col-md-offset-3">
												<button id="passwordReset" type="button" class="btn btn-flat btn-block btn-primary btn-update">保存</button>
											</div>
										</div>
									</div>
								</form>
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