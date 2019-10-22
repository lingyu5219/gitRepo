<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=nbZjIball3R0jjtTIiGYaKSU9RLfynjr&services=&t=20180629105706"></script>
	<script type="text/javascript">
		$(function(){
			var gartenId = "${garten.gartenId }";
			var pics = {};
			
			function getPic(id){
				for(var i = 0; i < pics.length; i++){
					if(id == pics[i].mediaId){
						return pics[i];
					}
				}
			}
			//初始化地图
			var map = new BMap.Map("gartenMap");
			var longtitude = "${garten.gartenLongitude }";
			var latitude = "${garten.gartenLatitude }";
			var point = new BMap.Point("116.403946", "39.913981");
			if(longtitude != "" && latitude != ""){
				point = new BMap.Point(longtitude, latitude);
			}
			map.centerAndZoom(point, 15);
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);               // 将标注添加到地图中
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			
			map.addEventListener("click", function(e){
				map.enableScrollWheelZoom();
			});
			
			//显示校园新闻活动
			queryDataWithPage({
				length : 4,
				pageContainer : $("#news-leo-page"),
				url : basePath + "front/queryMessageList",
				//根据校园ID，查询msgType!=3,也就是查询新闻1、活动2的
				data : {msgTypeIsNot : 3, msgGartenId: gartenId},
				success : function(dataTable) {
					$("#news-row").empty();
					var data = dataTable.data;
					
					for(var i = 0; i < data.length; i++){
						var news = data[i];
						var col = $("<div class=\"col-md-6\">");
						var a = $("<a class=\"info-box\">");
						a.attr("href",basePath + "front/msgDetail/" + news.msgId);
						var left = $("<span class=\"info-box-icon\">");
						var leftDay = $("<div class=\"day\">");
						leftDay.text(news.createTime.substr(8,2));
						var leftYearMonth = $("<div class=\"yearMonth\">");
						leftYearMonth.text(news.createTime.substr(0,7));
						left.append(leftDay).append(leftYearMonth);
						
						var right = $("<div class=\"info-box-content\">");
						var rightTitle = $("<span class=\"info-box-text\">");
						rightTitle.text(news.msgTitle);
						var rightDesc = $("<span class=\"info-box-number\">");
						rightDesc.text($(news.msgContent).text());
						right.append(rightTitle).append(rightDesc);
						
						a.append(left).append(right);
						col.append(a);
						$("#news-row").append(col);
						
					}
				}
			});
			//根据职位ID，校园ID获取园长头像positionIds
			queryData({
				url: basePath + "front/queryStaffs",
				data: {positionIds : 21, staffGartenId: gartenId},
				success: function(data){
					if(data.length > 0 && data[0].staffPic != null && data[0].staffPic.length > 0){
						$("#garten-leader").attr("src", basePath + data[0].staffPic);
					}
				}
			});
			//根据校园ID获取校园的课程
			queryData({
				url: basePath + "front/queryLessonByGartenId",
				data: {gartenId: gartenId},
				success: function(data){
					$("#garten-course").empty();
					var length = data.length;
					if(data.length > 4){
						length = 4;
					}
					
					for(var i = 0; i < length; i++){
						var course = data[i];
						var box = $("<div class=\"box box-green box-solid\">");
						if(i > 0){
							box.addClass("collapsed-box");
						}
						var boxHeader = $("<div class=\"box-header with-border\">");
						var h3 = $("<h3 class=\"box-title\">");
						h3.text(course.lessonName);
						var boxTools = $("<div class=\"box-tools pull-right\">");
						var button = $("<button type=\"button\" class=\"btn btn-box-tool\" data-widget=\"collapse\">");
						var btni = $("<i class=\"fa\">");
						if(i > 0){
							btni.addClass("fa-plus");
						} else {
							btni.addClass("fa-minus");
						}
						boxTools.append(button.append(btni));
						boxHeader.append(h3).append(boxTools);
						
						var boxBody = $("<div class=\"box-body\">");
						boxBody.text($("<div>").append(course.description).text());
						box.append(boxHeader).append(boxBody);
						
						$("#garten-course").append(box);
					}
				}
			});
			
			//根据校园ID，获取班级类型
			queryData({
				url: basePath + "front/queryClassTypeByGartenId",
				data: {gartenId: gartenId},
				success: function(data){
					$("#batch-type").empty();
					for(var i = 0; i < data.length; i++){
						var classType = data[i];
						/*
						<div class=\"col-sm-4\">
							<a href=\"javascript:void(0);\" class=\"garten-detail-batch\">
								<img class=\"img-responsive\" src=\"${basePath }static/front/images/garten-detail-batch.png\"/>
								<div class=\"garten-detail-batch-title\">小班：1.5岁-3岁混龄班</div>
							</a>
						</div>
						*/
						var typeItem = $("<div class=\"col-sm-4\">");
						var a = $("<a href=\"javascript:void(0);\" class=\"garten-detail-batch\">");
						var img = $("<img class=\"img-responsive\"/>");
						img.attr("src",basePath + "static/front/images/" + getRandom(0,23) + ".jpg");
						var typeName = $("<div class=\"garten-detail-batch-title\">");
						typeName.text(classType.classTypeName);
						typeItem.append(a.append(img).append(typeName));
						
						$("#batch-type").append(typeItem);
					}
				}
			});
			
			//根据校园ID获取可以前端展示的校园图片
			queryDataWithPage({
				length : 6,
				pageContainer : $("#garten-pic-page"),
				url : basePath + "front/queryMediaList",
				//根据校园ID，查询mediaPattern=1,也就是查询图片
				data :{mediaPattern : 1, mediaGartenId : gartenId, mediaIsFront : 1},
				success : function(dataTable) {
					$("#garten-pic").empty();
					var data = dataTable.data;
					pics = data;
					for(var i = 0; i < data.length; i++){
						var gartenPic = data[i];
						/*
						<div class=\"col-xs-6 col-md-4 padding-top padding-bottom\">
							<img class=\"img-responsive\" src=\"${basePath }static/front/images/garten-detail-pic.png\"/>
						</div>
						*/
						var picItem = $("<div class=\"col-xs-6 col-md-4 padding-top padding-bottom\">");
						var img = $("<img class=\"img-responsive img-fixed-height\"/>");
						img.attr("src", basePath + gartenPic.mediaUrl);
						img.attr("data-id",gartenPic.mediaId);
						img.css("cursor","pointer");
						img.click(function(){
							var media = getPic($(this).attr("data-id"));
							showPic(media.mediaTitle, media.mediaUrl.replace(/\\/g,"/"));
						});
						picItem.append(img);
						$("#garten-pic").append(picItem);
					}
				}
			});
		})
	</script>
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="kinder-content garten-detail">
				<div class="container">
					<h2 class="text-center">${garten.gartenName }</h2>
					<div class="garten-detail-desc">${garten.gartenDesc }</div>
					<div class="gap"></div>
					<div id="gartenMap" class="map"></div>
					<div class="gap"></div>
					<h2 class="text-center">新闻活动</h2>
					<div class="gap"></div>
					<div id="news-row" class="news-garten row"></div>
					<div class="gap"></div>
					<div class="row">
						<div id="news-leo-page"></div>
					</div>
					<div class="gap"></div>
					<!-- 园长致辞 -->
					<div class="row">
						<div class="col-sm-4">
							<img id="garten-leader" class="img-responsive" src="${basePath }static/front/images/staff-head.png"/>
						</div>
						<div class="col-sm-8 garten-speech">${garten.gartenSpeech }</div>
					</div>					
					<div class="gap"></div>
					<!-- 课程 -->
					<div class="row">
						<%-- <div class="col-md-6">
							<img class="img-responsive" src="${basePath }static/front/images/garten-detail-course.png"/>
						</div>
						<div class="gap visible-xs-block visible-sm-block"></div> --%>
						<div id="garten-course" class="col-md-12"></div>
					</div>	
					<div class="gap"></div>
					<!-- 班级类型 -->
					<div id="batch-type" class="row">
						<div class="col-sm-4">
							<a href="javascript:void(0);" class="garten-detail-batch">
								<img class="img-responsive" src="${basePath }static/front/images/garten-detail-batch.png"/>
								<div class="garten-detail-batch-title">小班：1.5岁-3岁混龄班</div>
							</a>
						</div>
						<div class="col-sm-4">
							<a href="javascript:void(0);" class="garten-detail-batch">
								<img class="img-responsive" src="${basePath }static/front/images/garten-detail-batch.png"/>
								<div class="garten-detail-batch-title">小班：1.5岁-3岁混龄班</div>
							</a>
						</div>
						<div class="col-sm-4">
							<a href="javascript:void(0);" class="garten-detail-batch">
								<img class="img-responsive" src="${basePath }static/front/images/garten-detail-batch.png"/>
								<div class="garten-detail-batch-title">小班：1.5岁-3岁混龄班</div>
							</a>
						</div>
					</div>
					<div class="gap"></div>
					<h2 class="text-center">校园照片</h2>
					<div class="gap"></div>
					<div id="garten-pic" class="row"></div>
					<div class="gap"></div>
					<div id="garten-pic-page"></div>
				</div>
				<div class="gap"></div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>