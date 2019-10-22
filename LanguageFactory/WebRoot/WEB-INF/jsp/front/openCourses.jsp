<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			var pics = {};
			
			function getPic(id){
				for(var i = 0; i < pics.length; i++){
					if(id == pics[i].mediaId){
						return pics[i];
					}
				}
			}
			queryData({
				url: basePath + "front/queryLessonTypeById",
				data: {lessonTypeId: 2},
				success: function(data){
					$(".open-course-desc").html(data.description);
				}
			});
			queryDataWithPage({
				length : 8,
				pageContainer : $("#open-course-page"),
				url : basePath + "front/queryMediaList",
				//根据校园ID，查询mediaPattern=1,也就是查询图片,mediaGartenId:-1查所有校园与集团的图片，mediaIsFront:1后台设置为允许前端显示的图片
				data :{mediaPattern : 1, mediaIsFront : 1,mediaGartenId : -1},
				success : function(dataTable) {
					$("#open-course-pic").empty();
					var data = dataTable.data;
					pics = data;
					for(var i = 0; i < data.length; i++){
						var gartenPic = data[i];
						/*
						<div class=\"col-xs-6 col-md-4 padding-top padding-bottom\">
							<img class=\"img-responsive\" src=\"${basePath }static/front/images/garten-detail-pic.png\"/>
						</div>
						*/
						var picItem = $("<div class=\"col-xs-6 col-md-3 padding-top padding-bottom\">");
						var img = $("<img class=\"img-responsive img-fixed-height\"/>");
						img.attr("src", basePath + gartenPic.mediaUrl);
						img.attr("data-id",gartenPic.mediaId);
						img.css("cursor","pointer");
						img.click(function(){
							var media = getPic($(this).attr("data-id"));
							showPic(media.mediaTitle, media.mediaUrl.replace(/\\/g,"/"));
						});
						picItem.append(img);
						$("#open-course-pic").append(picItem);
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
			<section class="open-course">
				<div class="container">
					<h2>开放课介绍</h2>
					<div class="gap"></div>
					<div class="open-course-desc">
					</div>
					<div class="gap"></div>
					<div id="open-course-pic" class="row">
					</div>
					<div class="gap"></div>
					<div class="row">
						<div id="open-course-page" class="leo-page">
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