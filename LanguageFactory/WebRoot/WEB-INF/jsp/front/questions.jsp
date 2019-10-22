<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$(".question-item").click(function(){
				$(".question-item .info-box span").removeClass("show");
				$(this).find(".info-box span").addClass("show");
				
				queryDataWithPage({
					length : 8,
					pageContainer : $("#question-list-page"),
					url:basePath + "front/queryContentList",
					//查询当前栏目下的已发布的内容来分别对应安全79、运动80、饮食81对应类型的问题列表
					data:{contentColumnId:$(this).attr("data-id"), contentState:2},
					success:function(dataTable){
						$("#question-list").empty();
						var data = dataTable.data;
						for(var i = 0; i < data.length; i++){
							var question = data[i];
							var questionItem = $("<div class=\"row padding-left padding-right\">");
							var box = $("<div class=\"box box-green box-solid\">");
							if(i > 0){
								box.addClass("collapsed-box");
							}
							var boxHeader = $("<div class=\"box-header with-border\">");
							var h3 = $("<h3 class=\"box-title\">");
							h3.text(question.contentTitle);
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
							boxBody.html(question.contentBody);
							questionItem.append(box.append(boxHeader).append(boxBody));
							$("#question-list").append(questionItem);
						}
					}
				});
			});
			$(".question-item:first").click();
		});
	</script>
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="parent-service">
				<div class="gap hidden-xs hidden-sm"></div>
				<div class="container">
					<div class="row">
						<div class="col-md-4">
							<jsp:include page="template/parentServiceSide.jsp"></jsp:include>
						</div>
						<div class="col-md-8">
							<div class="row">
								<div class="col-xs-4 question-item" data-id="79">
									<div class="info-box">
										<img class="img-responsive" src="${basePath }static/front/images/question-item1.png"/>
										<span class="show">安全</span>
									</div>
								</div>
								<div class="col-xs-4 question-item" data-id="80">
									<div class="info-box">
										<img class="img-responsive" src="${basePath }static/front/images/question-item2.png"/>
										<span>运动</span>
									</div>
								</div>
								<div class="col-xs-4 question-item" data-id="81">
									<div class="info-box">
										<img class="img-responsive" src="${basePath }static/front/images/question-item3.png"/>
										<span>饮食</span>
									</div>
								</div>
							</div>
							<div id="question-list">
								
							</div>
							<div class="row">
								<div id="question-list-page" class="leo-page">
								</div>
							</div>
							<div class="gap"></div>
						</div>
					</div>
					
				</div>
				<div class="gap hidden-xs hidden-sm"></div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>