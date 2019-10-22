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
			var staffs = [];
			
			function getStaff(staffId){
				for(var i = 0; i < staffs.length; i++){
					if(staffId == staffs[i].staffId){
						return staffs[i];
					}
				}
			}
			
			function initSlider(){
				//初始化特色课程轮播插件
			    var slider = $('.mis-stage').miSlider({
			    	speed:500,
			    	stageHeight: 310,
					slidesOnStage: false,
					slidePosition: 'center',
					slideStart: '1',
					slideScaling: 120,
					offsetV: -5,
					centerV: true,
					navButtonsOpacity: 1,
					navList:false,
					afterTrans:function(){
						var staffId = slider.find(".mis-current").find("img").attr("data-id");
						var staff = getStaff(staffId);
						
						$(".manager-info").find("img").attr("src", basePath + staff.staffPic);
						
						var title = staff.staffName;
						if(staff.staffGartenId > 0){
							title += "——" + staff.staffGartenName;
						}
						$(".manager-info").find(".manager-info-title").text( title + "——" + staff.positionName);
						
						$(".manager-info").find(".manager-info-body").text(staff.remark);
					}
				}); 
				//重置mislider,解决有时初始化失败的问题
				slider.data( "miSlider").resetSlider();
			}
			//initSlider();
			
			queryData({
				url: basePath + "front/queryStaffs",
				data: {positionIds : "${paramMap.managerPositions.paramName}"},
				success: function(rsData){
					staffs = rsData;
					
					$("ol.mis-slider").empty();
					for(var i = 0; i < rsData.length; i++){
						var staff = rsData[i];
						if(i == 0){
							$(".manager-info").find("img").attr("src", basePath + staff.staffPic);
							
							var title = staff.staffName;
							if(staff.staffGartenId > 0){
								title += "——" + staff.staffGartenName;
							}
							$(".manager-info").find(".manager-info-title").text( title + "——" + staff.positionName);
							
							$(".manager-info").find(".manager-info-body").text(staff.remark);
						}
						
						/*
						<li class="mis-slide">
							<a href="javascript:void(0)" class="mis-container">
								<img class="img-responsive" src="${basePath }static/front/images/staff-head.png"/>
							</a>
						</li>
						*/
						var li = $("<li class=\"mis-slide\">");
						var a =  $("<a href=\"javascript:void(0)\" class=\"mis-container\">");
						var img = $("<img width=\"100%\" height=\"100%\"/>");
						img.attr("src", basePath + staff.staffPic);
						img.attr("data-id", staff.staffId);
						li.append(a.append(img));
						$("ol.mis-slider").append(li);
					}

					//setTimeout(initSlider(),2000);
					initSlider();
					$(".mis-stage").css("opacity",1);
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
			<section class="team-manager">
				<div class="container">
					<div class="manager-info">
						<div class="row">
							<div class="col-md-4 padding-lg">
								<img class="img-responsive" src="${basePath }static/front/images/staff-head.png"/>
							</div>
							<div class="col-md-8">
								<div class="manager-info-title">集团董事长</div>
								<div class="manager-info-body">集团董事长</div>
							</div>
						</div>
					</div>
					<div class="gap"></div>
					<div class="mis-stage">
						<ol class="mis-slider">
							<li class="mis-slide">
								<a href="javascript:void(0)" class="mis-container">
									<img class="img-responsive" src="${basePath }static/front/images/staff-head.png"/>
								</a>
							</li>
							<li class="mis-slide">
								<a href="javascript:void(0)" class="mis-container">
									<img class="img-responsive" src="${basePath }static/front/images/staff-head.png"/>
								</a>
							</li>
							<li class="mis-slide">
								<a href="javascript:void(0)" class="mis-container">
									<img class="img-responsive" src="${basePath }static/front/images/staff-head.png"/>
								</a>
							</li>
							<li class="mis-slide">
								<a href="javascript:void(0)" class="mis-container">
									<img class="img-responsive" src="${basePath }static/front/images/staff-head.png"/>
								</a>
							</li>
						</ol>
					</div>
				</div>
			</section>
			
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>