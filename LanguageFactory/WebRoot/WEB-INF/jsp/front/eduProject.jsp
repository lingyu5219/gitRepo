<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			var eduIdea = "${paramMap.eduIdea.paramName}";
			queryData({
				url : basePath + "front/queryEduProjects",
				success : function(data){
					$("#edu-project").empty();
					for(var i = 0; i < data.length; i++){
						var eduProject = data[i];
						var container = $("<div class=\"container text-center\">");
						var innerContainer = $("<div class=\"feature-container\">");
						var top = $("<div class=\"row feature-item\">");
						var topContainer = $("<div class=\"col-sm-12 feature-item-container no-padding\">");
						var topLeft = $("<div class=\"col-sm-5 feature-item-left no-padding\">");
						var img = $("<img class=\"img-responsive\" width=\"100%\"/>");
						//img.attr("src",basePath + "static/front/images/garten-item.png");
						img.attr("src",basePath + "static/front/images/" + getRandom(0,23) + ".jpg");
						topLeft.append(img);
						var topRight = $("<div class=\"col-sm-7 feature-item-right no-padding\">");
						var topRightHead = $("<div class=\"feature-item-head\">");
						topRightHead.text(eduProject.eduProjectName);
						var topRightDesc = $("<div class=\"feature-item-desc\">");
						topRightDesc.html("<h2>顶级的教育理念</h2>").append(eduIdea.replace(/<\/?[^>]*>/g, ''));
						topRight.append(topRightHead).append(topRightDesc);
						top.append(topContainer.append(topLeft).append(topRight));
						
						var gap = $("<div class=\"gap hidden-xs hidden-sm\"></div>");
						
						var contentContainer = $("<div class=\"row\">");
						var content = $("<div class=\"feature-content hide-part\">");
						content.html(eduProject.description);
						var button = $("<button type=\"button\" class=\"btn bg-orange margin toggleMoreBtn\">了解更多 <i class=\"fa fa-plus\"></i></button>");
						
						contentContainer.append(content).append(button);
						container.append(innerContainer.append(top).append(gap).append(contentContainer).append(gap));
						
						$("#edu-project").append(container).append($("<div class=\"gap\"></div>"));
					}
					$(".toggleMoreBtn").click(function(){
						$(this).find("i").toggleClass("fa-minus");
						toggleMore($(this).prev());
						
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
			<section id="edu-project" class="edu-feature">
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>