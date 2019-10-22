<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	
	<script type="text/javascript">
		$(function(){
			function initFormValidator(){
				$("#commentsForm").validate({
					errorClass:"has-error",
					onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
					rules: {
						commentsTitle: {
							required: true
						},
						commentsContent:{
							required: true
						},
						commentsBy:{
							required: true
						},
						commentsEmail:{
							required: true,
							isEmail: true
						},
						commentsPhone:{
							required: true,
							isTelOrPhone: true
						},
						verifyCode:{
							required: true,
							kaptcha: true
						}
					},
					errorPlacement: function(error, element) {  
						
						error.appendTo(element.parent());
					}
				});
		    }
			//初始化form表单校验
			initFormValidator();
			
			$("#kaptchaImage").click(function(){
    			var time = new Date().getTime();
                $(this).attr("src",basePath + "kaptcha?d=" + time);
    		});
			
			$("#kaptchaImage").tooltip();
			
			$(".btn-save").click(function(){
				var validResult = $("#commentsForm").valid();
    			if(validResult){
    				//获取表单数据，此处可自行获取表单数据
    				var param = formToJson($("#commentsForm"));
    				save({
    					data:param,
    					url: basePath + "front/addComments",
    					success:function(result){
    						tcAlert(result.info,function(){
    							window.location.reload();
    						});
    					}
    				});
    			}
			});
		});
	</script>
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="garten-visit-time">
				<div class="gap"></div>
				<div class="container">
					<h3 class="text-grey-green" style="line-height:34px;">我们用心倾听您的建议，如果您在预约时遇到问题，或者对我们有任何意见建议，欢迎在此留言，我们将关注您的问题。</h3>
					<div class="gap"></div>
					<div class="gap"></div>
					<div class="row">
						<div class="col-sm-6 col-md-4">
							<h1 class="text-grey-green text-xlarge">Your  Feedback</h1>
							<h1 class="text-orange">意见建议</h1>
						</div>
					</div>
					<form id="commentsForm" class="form-horizontal whiteForm" role="form" method="post">
						<div class="row">
							<div class="col-md-5 padding">
								<input type="text" class="form-control input-lg" name="commentsTitle" placeholder="请输入标题" />
							</div>
							<div class="col-md-5 col-md-offset-2 padding">
								<input type="text" class="form-control input-lg" name="commentsBy" placeholder="留言人姓名" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-5 padding">
								<input type="text" class="form-control input-lg" name="commentsEmail" placeholder="电子邮箱" />
							</div>
							<div class="col-md-5 col-md-offset-2 padding">
								<input type="text" class="form-control input-lg" name="commentsPhone" placeholder="联系电话" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-5 padding">
								<input type="text" class="form-control input-lg" name="commentsAddress" placeholder="联系地址" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 padding">
								<textarea rows="10" class="form-control text-large" name="commentsContent" placeholder="留言内容"></textarea>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5 padding">
								<div class="form-group">
									<label class="col-xs-12 col-md-3 text-large text-left">验证码:</label>
									<div class="col-xs-7 col-md-6">
										<input type="text" class="form-control input-lg" name="verifyCode" placeholder="请输入验证码" />
									</div>
									<div class="col-xs-5 col-md-3">
										<img src="${basePath}kaptcha2" id="kaptchaImage" class="kaptcha-img" data-toggle="tooltip" data-placement="top" title="看不清点击更换">
									</div>
								</div>
							</div>
							<div class="col-md-5 col-md-offset-2 padding">
								<input type="button" class="col-xs-4 btn btn-success btn-lg btn-save" value="提交"/>
							</div>
						</div>
					</form>
				</div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>