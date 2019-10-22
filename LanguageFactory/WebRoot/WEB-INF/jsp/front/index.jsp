<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<link href="${basePath}static/front/plugins/mislider/css/mislider.css" rel="stylesheet">
	<link href="${basePath}static/front/plugins/mislider/css/mislider-skin-cameo.css" rel="stylesheet">
	<script src="${basePath}static/front/plugins/mislider/js/mislider.js"></script>
	<script type="text/javascript">
		$(function(){
			var groupName = "${paramMap.systemTitle.paramName}";
			var featureDesc = "${paramMap.featureDesc.paramName}";
			var newsDesc = "${paramMap.newsDesc.paramName}";
			var courseDesc = "${paramMap.courseDesc.paramName}";
			
			$("#welcome").text(groupName.replace(/<\/?[^>]*>/g, ''));
			$("#gartenFeatureTitle").text(groupName.replace(/<\/?[^>]*>/g, '') + "的特色");
			$("#gartenFeatureDesc").text(featureDesc.replace(/<\/?[^>]*>/g, ''));
			$("#gartenNewsDesc").text(newsDesc.replace(/<\/?[^>]*>/g, ''));
			$("#gartenCourseDesc").text(courseDesc.replace(/<\/?[^>]*>/g, ''));
			//初始化新闻活动
		    var option = {
				length : 2,
				pageContainer : $("#garten-news-page"),
				url : basePath + "front/queryMessageList",
				//查询新闻1、活动3的，因此是消息类型不是2的
				data : {msgTypeIsNot : 2, msgGartenId : -1},
				success : function(dataTable){
					$("#garten-news-container").empty();
					var data = dataTable.data;
					for(var i = 0; i < data.length; i++){
						var msg = data[i];
						var newsItem = $("<div class=\"col-xs-10 col-xs-offset-1 col-sm-12 col-sm-offset-0 news-item text-left\">");
						var newsItemBody = $("<div class=\"row news-item-body\">");
						var newsItemFoot = $("<div class=\"row news-item-foot\">");
						
						var imgDiv = $("<div class=\"col-sm-3\">");
						var img = $("<img class=\"img-responsive\"/>");
						var contentImg = $(msg.msgContent).find("img").first();
						var imgUrl = contentImg.length > 0 ? contentImg.attr("src") : msg.msgCoverUrl;
						if(msg.msgPattern == 2){
							imgUrl = msg.mediaCoverUrl;
						}
						
						img.attr("src", basePath + imgUrl);
						imgDiv.append(img);
						
						var newsItemContent = $("<div class=\"news-item-content col-sm-7\">");
						var h3 = $("<h3>");
						h3.text(msg.msgTitle);
						var newsItemDesc = $("<div class=\"news-item-desc\">");
						if(msg.msgPattern == 1){
							//如果是图文消息，则显示图文内容
							newsItemDesc.text($(msg.msgContent).text());
						} else if(msg.msgPattern == 2) {
							//视频消息，则显示视频备注
							newsItemDesc.text(msg.remark);
						}
						newsItemContent.append(h3).append(newsItemDesc);
						newsItemBody.append(imgDiv).append(newsItemContent);
						
						var newsItemFoot = $("<div class=\"row news-item-foot\">");
						var newsItemDate = $("<div class=\"col-xs-6 col-sm-7 col-sm-offset-3 news-item-date\">");
						newsItemDate.text(msg.createTime.substr(0,10));
						var newsItemBtnDiv = $("<div class=\"col-xs-6 col-sm-2 text-left\">");
						var newsItemBtn = $("<a href=\"#\" class=\"btn-detail\">查看详情 +</a>");
						newsItemBtn.attr("href", basePath + "front/msgDetail/" + msg.msgId);
						newsItemBtnDiv.append(newsItemBtn);
						
						newsItemFoot.append(newsItemDate).append(newsItemBtnDiv);
						
						newsItem.append(newsItemBody).append(newsItemFoot);
						
						$("#garten-news-container").append(newsItem);
					}
				}
			};
			
			queryDataWithPage(option);
			
			//初始化特色课程
			queryData({
				url: basePath + "front/queryLesson",
				data: {lessonTypeId : 1},
				success: function(data){
					/*
					<li class="mis-slide">
						<a href="#" class="mis-container">
							<figure>
								<figcaption>蒙特梭利课程</figcaption>
							</figure>
						</a>
					</li>
					*/
					$("#garten-course-slider").empty();
					for(var i = 0; i < data.length; i++){
						var li = $("<li class=\"mis-slide\">");
						var a = $("<a href=\"javascript:void(0);\" class=\"mis-container\">");
						var figure = $("<figure>");
						var figcaption = $("<figcaption>");
						figcaption.text(data[i].lessonName);
						li.append(a.append(figure.append(figcaption)));
						
						$("#garten-course-slider").append(li);
					}
					//初始化特色课程轮播插件
				    var slider = $('.mis-stage').miSlider({
				    	speed:500,
						stageHeight: 340,
						slidesOnStage: false,
						slidePosition: 'center',
						slideStart: '1',
						slideScaling: 130,
						offsetV: -5,
						centerV: true,
						navButtonsOpacity: 1
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
			<section class="garten-search">
				<div class="container text-center">
					<h2 class="text-white">欢迎来到<span id="welcome" class="text-orange">${paramMap.systemTitle.paramName}</span></h2>
					<form id="signupForm" class="form-horizontal no-padding" role="form" method="post" action="${basePath }front/gartenSearch">
						<div class="input-group input-group-lg col-md-8 col-md-offset-2" style="margin-top:20px;">
			                <input type="text" class="form-control" name="gartenName" placeholder="搜索您附近的幼儿园">
			                <div class="input-group-btn">
			                  <button type="submit" class="btn btn-warning"><i class="text-large glyphicon glyphicon-search"></i></button>
			                </div>
			            </div>
		            </form>
				</div>
			</section>
			
			<section class="garten-about">
				<div class="container">
					<div class="row">
						<div class="col-md-5 hidden-xs hidden-sm">
							<div class="gap"></div>
							<div class="gap"></div>
							<div class="gap"></div>
							<div class="gap"></div>
							<img src="${basePath}static/front/images/about1.png"/>
						</div>
						<div class="col-md-2 hidden-xs hidden-sm">
							<img style="margin-top:40px;" src="${basePath}static/front/images/about2.png"/>
							<img style="margin-top:350px;" src="${basePath}static/front/images/about3.png"/>
						</div>
						<div class="col-md-5 col-sm-12">
							<div class="gap hidden-xs hidden-sm"></div>
							<div class="gap hidden-xs hidden-sm"></div>
							<div class="gap hidden-xs hidden-sm"></div>
							<div class="gap hidden-xs hidden-sm"></div>
							<h2 class="text-orange">关于幼儿园</h2>
							<div class="gap hidden-xs hidden-sm"></div>
							<p style="font-size:16px; line-height:30px;">
								${paramMap.systemGroupDesc.paramName }
							</p>
							<div class="gap hidden-xs hidden-sm"></div>
							<div class="gap hidden-xs hidden-sm"></div>
							<div class="text-center">
								<a href="${basePath }front/content/17" class="btn btn-warning btn-lg">了解更多</a>
							</div>
						</div>
					</div>
					<div class="gap"></div>
				</div>
			</section>
			
			<section class="garten-feature">
				<div class="container text-center">
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<h2 id="gartenFeatureTitle" class="text-orange"></h2>
					</div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<p id="gartenFeatureDesc" class="text-orange" style="font-size:18px;"></h2>
					</div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<div class="col-xs-6 col-sm-3">
							<a href="${basePath }front/mediaList" class="img-item">
								<img class="img-responsive center-block" width="100%" src="${basePath}static/front/images/nice-time.png"/>
								<div class="title">欢乐时光</div>
							</a>
						</div>
						<div class="col-xs-6 col-sm-3">
							<a href="${basePath }front/mediaList?type=2" class="img-item">
								<img class="img-responsive" width="100%" src="${basePath}static/front/images/nice-video.png"/>
								<div class="title">精彩视频</div>
							</a>
						</div>
						<div class="col-xs-6 col-sm-3">
							<a href="${basePath }front/gartenList" class="img-item">
								<img class="img-responsive" width="100%" src="${basePath}static/front/images/garten-desc.png"/>
								<div class="title">园所介绍</div>
							</a>
						</div>
						<div class="col-xs-6 col-sm-3">
							<a href="${basePath }front/content/21" class="img-item">
								<img class="img-responsive" width="100%" src="${basePath}static/front/images/recruit.png"/>
								<div class="title">招生专栏</div>
							</a>
						</div>
					</div>
					<div class="gap"></div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<a href="${basePath}front/eduFeature" class="btn btn-warning btn-lg">了解更多</a>
					</div>
					<div class="gap"></div>
				</div>
			</section>
			
			<section class="garten-news">
				<div class="container text-center">
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<h2 class="text-orange">我们的新闻公告</h2>
					</div>
					<div class="gap"></div>
					<div class="row hidden-xs">
						<p id="gartenNewsDesc" class="text-orange" style="font-size:18px;"></h2>
					</div>
					<div class="gap hidden-xs"></div>
					<div id="garten-news-container" class="row">
			            
					</div>
					<div id="garten-news-page" class="row">
					</div>
					<div class="gap"></div>
				</div>
			</section>
			
			<section class="garten-course">
				<div class="container text-center">
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<h2 class="text-orange">我们的特色课程</h2>
					</div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<p id="gartenCourseDesc" class="text-orange" style="font-size:18px;"></h2>
					</div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row text-left">
						<div class="mis-stage">
							<ol id="garten-course-slider" class="mis-slider">
								<li class="mis-slide">
									<a href="#" class="mis-container">
										<figure>
											<figcaption>蒙特梭利课程</figcaption>
										</figure>
									</a>
								</li>
								<li class="mis-slide">
									<a href="#" class="mis-container">
										<figure>
											<figcaption>英文课程</figcaption>
										</figure>
									</a>
								</li>
								<li class="mis-slide">
									<a href="#" class="mis-container">
										<figure>
											<figcaption>中文课程</figcaption>
										</figure>
									</a>
								</li>
							</ol>
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