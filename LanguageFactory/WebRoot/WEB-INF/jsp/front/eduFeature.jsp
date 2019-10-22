<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			var featureDesc = "${paramMap.featureDesc.paramName}";
			$("#gartenFeatureDesc").text(featureDesc.replace(/<\/?[^>]*>/g, ''));
			
			$("#toggleMoreBtn").click(function(){
				$(this).find("i").toggleClass("fa-minus");
				toggleMore($(this).prev());
				
			});
			
			queryData({
				url: basePath + "front/queryColumnById",
				data: {columnId : 44},
				success: function(data){
					$(".feature-content").html(data.remark);
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
			<section class="edu-feature">
				<div class="container text-center">
					<div class="feature-container">
						<div class="row feature-item">
							<div class="col-sm-12 feature-item-container no-padding">
								<div class="col-sm-5 feature-item-left no-padding">
										<img class="img-responsive" width="100%" src="${basePath }static/front/images/garten-item.png"/>
								</div>
								<div class="col-sm-7 feature-item-right no-padding">
									<div class="feature-item-head">教育特色</div>
									<div class="feature-item-desc">
										<h2>顶级的教育理念</h2>
										<div id="gartenFeatureDesc" style="padding:0px 10px;height:80px;overflow:hidden;"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="gap hidden-xs hidden-sm"></div>
						<div class="row">
							<div class="feature-content hide-part">
							</div>
							<button id="toggleMoreBtn" type="button" class="btn bg-orange margin">了解更多 <i class="fa fa-plus"></i></button>
						</div>
						<div class="gap hidden-xs hidden-sm"></div>
					</div>
				</div>
			</section>
			
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>