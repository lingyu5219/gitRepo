<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
	<jsp:include page="front/common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			function resize(){
				var wrapperHeight = $(".content-wrapper").height();
				var imgHeight = $("#img404").height();
				$(".content-wrapper").css("padding-top",(wrapperHeight-imgHeight)/2 + 60);
			}
			resize();
			$(window).resize(function(){
				resize();
			});
		})
	</script>	
</head>
<body class="hold-transition skin-custom layout-top-nav">

	<div class="wrapper">
		<jsp:include page="front/template/topNav.jsp"></jsp:include>
		<div class="content-wrapper">
			<img id="img404" style="margin:0 auto;" class="img-responsive" src="${basePath}static/images/404.png" alt="出错了"/>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>