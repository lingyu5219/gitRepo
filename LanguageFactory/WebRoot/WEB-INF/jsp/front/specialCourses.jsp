<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<link href="${basePath}static/front/plugins/mislider/css/mislider.css" rel="stylesheet">
	<link href="${basePath}static/front/plugins/mislider/css/mislider-skin-cameo2.css" rel="stylesheet">
	<script src="${basePath}static/front/plugins/mislider/js/mislider.js"></script>
	<script type="text/javascript">
		$(function(){
		    //初始化特色课程
			queryData({
				url: basePath + "front/queryLesson",
				data: {lessonTypeId : 1},
				success: function(data){
					$("ol.mis-slider").empty();
					for(var i = 0; i < data.length; i++){
						var course = data[i];
						/*
						<li class="mis-slide">
							<a href="javascript:void(0)" class="mis-container">
								<img class="img-responsive" src="${basePath }static/front/images/course-item.png"/>
								<p>1让儿童养成独立自主的个性，在多样化的游戏中，让幼儿从不同角度认识统一事物，进而形成多变思维模式的教学理念。
								1让儿童养成独立自主的个性，在多样化的游戏中，让幼儿从不同角度认识统一事物，进而形成多变思维模式的教学理念。</p>
								<span>1体智能课程</span>
							</a>
						</li>
						*/
						if(i == 0){
							$(".feature-container .row").html(course.description);
						}
						var li = $("<li class=\"mis-slide\">");
						var a = $("<a href=\"javascript:void(0);\" class=\"mis-container\">");
						var img = $("<img class=\"img-responsive\"/>");
						//img.attr("src",course.lessonCoverUrl && course.lessonCoverUrl.length > 0 ? course.lessonCoverUrl : basePath + "static/front/images/course-item.png");
						img.attr("src",basePath + "static/front/images/" + getRandom(0,23) + ".jpg");
						var p = $("<div class=\"description\">");
						p.html(course.description);
						var span = $("<span class=\"title\">")
						span.text(data[i].lessonName);
						li.append(a.append(img).append(p).append(span));
						
						$("ol.mis-slider").append(li);
					}
					//初始化特色课程轮播插件
				    var slider = $('.mis-stage').miSlider({
						slidesOnStage: false,
						slidePosition: 'center',
						slideStart: '1',
						slideScaling: 120,
						offsetV: -5,
						centerV: true,
						navButtonsOpacity: 1,
						navList:false,
						afterTrans:function(){
							$(".feature-container .row").html(slider.find(".mis-current").find(".description").html());
						}
					}); 
				    slider.data( "miSlider").resetSlider();
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
				<div class="container">
					<div class="mis-stage">
						<ol class="mis-slider">
							
						</ol>
					</div>
					<div class="clear"></div>
					<div class="feature-container">
						<div class="row padding margin">
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