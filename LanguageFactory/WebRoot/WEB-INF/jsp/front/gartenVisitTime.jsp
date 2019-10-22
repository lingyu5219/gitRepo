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
			queryDataWithPage({
				length : 4,
				pageContainer : $("#visit-leo-page"),
				url : basePath + "front/queryGartenList",
				success : function(dataTable) {
					$("#garten-visit-time-row").empty();
					var data = dataTable.data;
					
					for(var i = 0; i < data.length; i++){
						var garten = data[i];
						
						var col = $("<div class=\"col-sm-6\">");
						var box = $("<div class=\"info-box padding\">");
						var content = $("<div class=\"garten-visit-time-content\">");
						
						var title = $("<div class=\"title\">");
						var time = $("<div class=\"time\">");
						var address = $("<div class=\"address\">");
						var map = $("<div class=\"map\">");
						map.attr("id","map" + garten.gartenId);
						
						title.html(garten.gartenName);
						time.html(garten.gartenVisitTime + " ");
						address.html("地址：" + garten.gartenAddress);
						
						content.append(title).append(time).append(address);
						box.append(content).append(map);
						col.append(box);
						$("#garten-visit-time-row").append(col);
						//初始化地图
						var map = new BMap.Map("map" + garten.gartenId);
						var point = new BMap.Point(garten.gartenLongitude, garten.gartenLatitude);
						map.centerAndZoom(point, 15);
						//map.enableScrollWheelZoom();
						var marker = new BMap.Marker(point);  // 创建标注
						map.addOverlay(marker);               // 将标注添加到地图中
						marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						
						map.addEventListener("click", function(e){
							this.enableScrollWheelZoom();
						});
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
			<section class="garten-visit-time">
				<div class="gap"></div>
				<div class="container">
					<h2 class="text-center text-grey-green">开放日时间及园区</h2>
					<div class="gap"></div>
					<div id="garten-visit-time-row" class="row">
						
					</div>
					<div class="gap"></div>
					<div class="row">
						<div id="visit-leo-page">
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