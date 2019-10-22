<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			var courses = {};
			
			function getCourse(id){
				for(var i = 0; i < courses.length; i++){
					if(id == courses[i].lessonId){
						return courses[i];
					}
				}
			}
			
			//初始化特色课程
			queryData({
				url: basePath + "front/queryLesson",
				data: {lessonTypeId : 3},
				success: function(data){
					courses = data;
					$("#course-list").empty();
					for(var i = 0; i < data.length; i++){
						var course = data[i];
						//<li><a href="#">妙思宝贝·家庭早教套装</a></li>
						var li = $("<li>");
						var a = $("<a href=\"javascript:void(0);\">");
						a.text(course.lessonName);
						a.attr("data-id",course.lessonId);
						if(i == 0){
							a.addClass("active");
							$("#course-desc").html(course.description);
						}
						a.click(function(){
							$("#course-list>li>a").removeClass("active");
							$(this).addClass("active");
							var course = getCourse($(this).attr("data-id"));
							$("#course-desc").html(course.description);
						});
						
						$("#course-list").append(li.append(a));
					}
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
			<section class="interest-course">
				<div class="container">
					<div class="row">
						<div class="col-md-5">
							<div class="interest-course-menu">
								<div class="interest-course-menu-head">
									<div class="interest-course-menu-body">
										<h2>兴趣课</h2>
										<ul id="course-list">
											<li><a href="#">妙思宝贝·家庭早教套装</a></li>
											<li><a href="#">妙思宝贝·育儿阶梯课程</a></li>
											<li><a href="#">妙思宝贝·家庭早教套装</a></li>
											<li><a href="#">妙思宝贝·绘玩绘乐</a></li>
											<li><a href="#">妙思宝贝·创意时空</a></li>
											<li><a href="#">全脑开发亲子课程</a></li>
										</ul>
									</div>
									
								</div>
							</div>
						</div>
						<div class="clear visible-xs-block visible-sm-block"></div>
						<div class="gap visible-xs-block visible-sm-block"></div>
						<div id="course-desc" class="col-md-7">
						</div>
					</div>
				</div>
			</section>
			
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>