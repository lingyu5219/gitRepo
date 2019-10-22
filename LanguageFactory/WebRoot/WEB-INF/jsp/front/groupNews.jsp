<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<style>
		img.news-img{
			width:100%;
			height:260px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			queryDataWithPage({
				length : 12,
				pageContainer : $("#news-leo-page"),
				url : basePath + "front/queryMessageList",
				data : {msgType : 1, msgGartenId : -1},
				success : function(dataTable) {
					$("#news-row").empty();
					var data = dataTable.data;
					
					for(var i = 0; i < data.length; i++){
						var news = data[i];
						/*
						<div class="col-md-4">
							<a href="#" class="info-box no-padding">
								<img class="img-responsive" src="${basePath }static/front/images/group-news-item.png"/>
								<div class="foot"></div>
								<div class="news-text">
									<div class="news-title">幼儿班—帮助孩子适应幼儿园学习与生活</div>
									<div class="news-date"><i class="glyphicon glyphicon-calendar"></i> June 20 , 2018</div>
								</div>
							</a>
						</div> */
						var col = $("<div class=\"col-sm-6 col-md-4\">");
						var a = $("<a href=\"#\" class=\"info-box no-padding\">");
						a.attr("href",basePath + "front/msgDetail/" + news.msgId);
						var img = $("<img class=\"img-responsive news-img\" />");
						
						var contentImg = $(news.msgContent).find("img").first();
						var imgUrl = contentImg.length > 0 ? contentImg.attr("src") : "static/front/images/group-news-item2.png";
						if(news.msgPattern == 2){
							imgUrl = news.mediaCoverUrl;
						}
						
						img.attr("src", basePath + imgUrl);
						//img.attr("src", basePath + "static/front/images/group-news-item2.png");
						
						
						var foot = $("<div class=\"foot\"></div>");
						var newsText = $("<div class=\"news-text\"></div>");
						var newsTitle = $("<div class=\"news-title\"></div>");
						newsTitle.text(news.msgTitle);
						var newsDate = $("<div class=\"news-date\"></div>");
						var dateIcon = $("<i class=\"glyphicon glyphicon-calendar\"></i> ");
						newsDate.append(dateIcon).append(" " + news.createTime.substr(0,10));
						
						newsText.append(newsTitle).append(newsDate);
						a.append(img).append(foot).append(newsText);
						col.append(a);
						
						$("#news-row").append(col);
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
			<section class="news">
				<div class="gap"></div>
				<div class="gap hidden-xs hidden-sm"></div>
				<div class="container">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 news-desc text-center text-large text-grey-green">${paramMap.systemDesc.paramName}</div>
					</div>
					<div class="gap"></div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div id="news-row" class="row">
						<div class="col-md-4">
							<a href="#" class="info-box no-padding">
								<img class="img-responsive" src="${basePath }static/front/images/group-news-item.png"/>
								<div class="foot"></div>
								<div class="news-text">
									<div class="news-title">幼儿班—帮助孩子适应幼儿园学习与生活</div>
									<div class="news-date"><i class="glyphicon glyphicon-calendar"></i> June 20 , 2018</div>
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="#" class="info-box no-padding">
								<img class="img-responsive" src="${basePath }static/front/images/group-news-item.png"/>
								<div class="foot"></div>
								<div class="news-text">
									<div class="news-title">幼儿班—帮助孩子适应幼儿园学习与生活</div>
									<div class="news-date"><i class="glyphicon glyphicon-calendar"></i> June 20 , 2018</div>
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="#" class="info-box no-padding">
								<img class="img-responsive" src="${basePath }static/front/images/group-news-item.png"/>
								<div class="foot"></div>
								<div class="news-text">
									<div class="news-title">幼儿班—帮助孩子适应幼儿园学习与生活</div>
									<div class="news-date"><i class="glyphicon glyphicon-calendar"></i> June 20 , 2018</div>
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="#" class="info-box no-padding">
								<img class="img-responsive" src="${basePath }static/front/images/group-news-item.png"/>
								<div class="foot"></div>
								<div class="news-text">
									<div class="news-title">幼儿班—帮助孩子适应幼儿园学习与生活</div>
									<div class="news-date"><i class="glyphicon glyphicon-calendar"></i> June 20 , 2018</div>
								</div>
							</a>
						</div>
					</div>
					<div class="gap"></div>
					<div class="row">
						<div id="news-leo-page">
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