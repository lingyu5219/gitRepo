<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			queryData({
				url: basePath + "front/queryContentById",
				data: {contentId : 21},
				success: function(data){
					$("#content").html(data.contentBody);
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
			<section class="parent-service">
				<div class="gap hidden-xs hidden-sm"></div>
				<div class="container">
					<div class="row">
						<div class="col-md-4">
							<jsp:include page="template/parentServiceSide.jsp"></jsp:include>
						</div>
						<div id="content" class="col-md-8">
						</div>
					</div>
				</div>
				<div class="gap hidden-xs hidden-sm"></div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>