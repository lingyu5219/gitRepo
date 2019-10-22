<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//54
			
			queryDataWithPage({
				length : 4,
				pageContainer : $("#page-container"),
				url:basePath + "front/queryContentList",
				//查询当前栏目下的已发布的内容来分别对应安全79、运动80、饮食81对应类型的问题列表
				data:{contentColumnId:54, contentState:2},
				success:function(dataTable){
					$("#content-list").empty();
					var data = dataTable.data;
					for(var i = 0; i < data.length; i++){
						var content = data[i];
						var row = $("<div class=\"row bg-grey-green\">");
						var left = $("<div class=\"col-sm-6 no-padding\">");
						var leftBox = $("<div class=\"info-box\">");
						var right = $("<div class=\"col-sm-6 no-padding\">");
						var rightBox = $("<div class=\"info-box content\">");
						
						var img = $("<img class=\"img-responsive\"/>");
						//img.attr("src",basePath + "static/front/images/home-garten-item.png");
						img.attr("src",basePath + "static/front/images/" + getRandom(0,23) + ".jpg");
						
						var a = $("<a href=\"#\">");
						a.attr("href", basePath + "front/content/" + content.contentId);
						var p = $("<p>");
						a.text(content.contentTitle);
						var body = $("<div>");
						body.html(content.contentBody);
						p.text(body.text());
						
						
						if(i % 2 == 0){
							left.append(leftBox.append(img));
							right.append(rightBox.append(a).append(p))
						} else {
							leftBox.addClass("content");
							left.append(leftBox.append(a).append(p));
							rightBox.removeClass("content");
							right.append(rightBox.append(img));
						}
						row.append(left).append(right);
						$("#content-list").append(row).append($("<div class=\"gap visible-xs-block\">"));
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
			<section class="home-garten-edu">
				<div class="gap"></div>
				<div id="content-list" class="container">
					<div class="row bg-grey-green">
						<div class="col-sm-6 no-padding">
							<div class="info-box">
								<img class="img-responsive" src="${basePath }static/front/images/home-garten-item.png"/>
							</div>
						</div>
						<div class="col-sm-6 no-padding">
							<div class="info-box content">
								<a href="#">家长学校联合会</a>
								<p>
									家长学校联合会旨在为我们的孩子提供一个充满关爱、温馨的学校及家庭环境。家长学校联合会(PSA)定期由园长召开例会，商讨有关孩子的教育及学校活动计划方面的问题。我们将共同努力，力求达到更高的目标。在开学初的家长会上，每个班级选举1～2名家长参加此例会，请所有热心的家长踊跃参加。</p>
							</div>
						</div>
					</div>
					<div class="gap visible-xs-block"></div>
					<div class="row bg-grey-green">
						<div class="col-sm-6 no-padding">
							<div class="info-box content">
								<a href="#">家长与教师的交流</a>
								<p>
									家长与老师的交流，是孩子在校学习过程中必不可少并且至关重要的因素。在整个学年中，通过与老师之间的交流，您可以非常详细地了解到孩子的学习进程。
								</p>
							</div>
						</div>
						<div class="col-sm-6 no-padding">
							<div class="info-box">
								<img class="img-responsive" src="${basePath }static/front/images/home-garten-item.png"/>
							</div>
						</div>
					</div>
					<div class="gap visible-xs-block"></div>
					<div class="row bg-grey-green">
						<div class="col-sm-6 no-padding">
							<div class="info-box">
								<img class="img-responsive" src="${basePath }static/front/images/home-garten-item.png"/>
							</div>
						</div>
						<div class="col-sm-6 no-padding">
							<div class="info-box content">
								<a href="#">家长学校联合会</a>
								<p>
									为了更好地促进孩子的发展，提高为家长服务的质量，畅通家长与幼儿园之间的沟通、交流渠道至关重要。家长有任何意见和建议可以直接与班级主讲老师沟通，也可以通过以下几种途径与幼儿园保持沟通。Etonkids热诚欢迎您将宝贵的意见或建议留给我们！</p>
							</div>
						</div>
					</div>
					<div class="gap visible-xs-block"></div>
					<div class="row bg-grey-green">
						<div class="col-sm-6 no-padding">
							<div class="info-box content">
								<a href="#">家长与教师的交流</a>
								<p>
									家长与老师的交流，是孩子在校学习过程中必不可少并且至关重要的因素。在整个学年中，通过与老师之间的交流，您可以非常详细地了解到孩子的学习进程。
								</p>
							</div>
						</div>
						<div class="col-sm-6 no-padding">
							<div class="info-box">
								<img class="img-responsive" src="${basePath }static/front/images/home-garten-item.png"/>
							</div>
						</div>
					</div>
					<div class="gap"></div>
				</div>
				<div class="gap hidden-xs"></div>
				<div id="page-container"></div>
				<div class="gap"></div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>