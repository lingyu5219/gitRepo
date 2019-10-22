<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			var type = getUrlParam("type");
			var medias = {};
			
			function getMedia(id){
				for(var i = 0; i < medias.length; i++){
					if(id == medias[i].mediaId){
						return medias[i];
					}
				}
			}
			
			var picSuccess = function(dataTable){
				$("#media-row").empty();
				var data = dataTable.data;
				medias = data;
				for(var i = 0; i < data.length; i++){
					var media = data[i];
					/*
					<div class="col-md-4">
						<a href="javascript:void(0);" class="info-box no-padding">
							<img class="img-responsive" src="${basePath }static/front/images/group-news-item.png"/>
						</a>
					</div>
					*/
					var col = $("<div class=\"col-xs-6 col-md-4\">");
					var a = $("<a href=\"javascript:void(0);\" class=\"info-box no-padding\">");
					a.attr("data-id",media.mediaId);
					a.click(function(){
						var media = getMedia($(this).attr("data-id"));
						showPic(media.mediaTitle, media.mediaUrl.replace(/\\/g,"/"));
					});
					
					var img = $("<img class=\"img-responsive img-fixed-height\"/>");
					img.attr("src", basePath + media.mediaUrl);
					col.append(a.append(img));
					
					$("#media-row").append(col);
				}
			}
			
			var VideoSuccess = function(dataTable){
				$("#media-row").empty();
				var data = dataTable.data;
				medias = data;
				for(var i = 0; i < data.length; i++){
					var media = data[i];
					/*
					<div class="col-md-4">
						<a href="javascript:void(0);" class="info-box no-padding video-box">
							<img class="img-responsive" src="${basePath }static/front/images/media-video.png"/>
							<div class="video-text">
								<div class="video-title">一起来跳舞</div>
								<div class="video-desc">小朋友们一起来上跳舞课程</div>
							</div>
						</a>
					</div>
					*/
					var col = $("<div class=\"col-xs-6 col-md-4\">");
					var a = $("<a href=\"javascript:void(0);\" class=\"info-box no-padding video-box\">");
					a.attr("data-id", media.mediaId);
					a.click(function(){
						var media = getMedia($(this).attr("data-id"));
						showVideo({
							title:media.mediaTitle,
							previewPageUrl:"front/mediaPreview",
							mediaUrl:media.mediaUrl.replace(/\\/g,"/")
						});
					});
					var img = $("<img class=\"img-responsive img-fixed-height\"/>");
					img.attr("src", basePath + media.mediaCoverUrl);
					var videoText = $("<div class=\"video-text\">");
					var videoTitle = $("<div class=\"video-title\">");
					videoTitle.text(media.mediaTitle);
					var videoDesc = $("<div class=\"video-desc\">");
					videoDesc.text(media.mediaDesc);
					videoText.append(videoTitle).append(videoDesc);
					
					col.append(a.append(img).append(videoText));
					
					$("#media-row").append(col);
				}
			}
			
			var option = {
				length : 9,
				pageContainer : $("#media-leo-page"),
				url : basePath + "front/queryMediaList",
				data : {mediaPattern : 1, mediaGartenId : -1, mediaIsHome : 2, mediaIsFront: 1},
				success : picSuccess
			};
			
			queryDataWithPage(option);
	
			$(".btn-toggle").click(function(){
				$(".btn-toggle").removeClass("active");
				$(this).addClass("active");
				
				if($(this).text() == "图片"){
					//
					option.data = {
						mediaPattern : 1, //格式为图片
						mediaGartenId : -1, //查询集团和校园的
						mediaIsHome : 2, //不是首页轮播图 1是 2否
						mediaIsFront: 1 //设置为前端显示的 1是 2否
					};
					option.success = picSuccess;
				}
				if($(this).text() == "视频"){
					option.data = {mediaPattern : 2, mediaGartenId : -1, mediaIsHome : 2, mediaIsFront: 1};
					option.success = VideoSuccess;
				}
				option.curPage = 1;//重新从第一页开始显示，否则会记忆上一次查询之后的翻页页码
				queryDataWithPage(option);
			});
			
			/* if(type != null && type == 1){
				$("#btn-pic").click();	
			} */
			
			if(type != null && type == 2){
				$("#btn-video").click();	
			}
		});
	</script>
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="news activity media">
				<div class="gap"></div>
				<div class="gap hidden-xs hidden-sm"></div>
				<div class="container">
					<div class="row">
						<div class="col-xs-10 col-xs-offset-1">
							<div class="row">
								<div class="col-xs-5 col-sm-4">
									<a id="btn-pic" href="javascript:void(0)" class="btn btn-block btn-grey-green btn-lg btn-toggle active">图片</a>
								</div>
								<div class="col-xs-5 col-xs-offset-1  col-sm-4 col-sm-offset-4">
									<a id="btn-video" href="javascript:void(0)" class="btn btn-block btn-grey-green btn-lg btn-toggle">视频</a>
								</div>
							</div>
						</div>
					</div>
					<div class="gap"></div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div id="media-row" class="row">
						<div class="col-md-4">
							<a href="javascript:void(0);" class="info-box no-padding">
								<img class="img-responsive" src="${basePath }static/front/images/group-news-item.png"/>
							</a>
						</div>
					</div>
					<div id="media-row2" class="row hide" >
						<div class="col-md-4">
							<a href="javascript:void(0);" class="info-box no-padding video-box">
								<img class="img-responsive" src="${basePath }static/front/images/media-video.png"/>
								<div class="video-text">
									<div class="video-title">一起来跳舞</div>
									<div class="video-desc">小朋友们一起来上跳舞课程</div>
								</div>
							</a>
						</div>
					</div>
					<div class="gap"></div>
					<div class="row">
						<div id="media-leo-page">
						</div>
					</div>
				</div>
				<div class="gap"></div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>