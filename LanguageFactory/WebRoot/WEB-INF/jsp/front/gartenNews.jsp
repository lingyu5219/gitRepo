<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			queryDataWithPage({
				length : 10,
				pageContainer : $("#news-leo-page"),
				url : basePath + "front/queryMessageList",
				data : {msgType : 1, isGroup : -1},
				success : function(dataTable) {
					$("#news-row").empty();
					var data = dataTable.data;
					
					for(var i = 0; i < data.length; i++){
						var news = data[i];
						/* <div class="col-md-6">
							<a href="#" class="info-box">
					            <span class="info-box-icon">
					            	<div class="day">25</div>
					            	<div class="yearMonth">2018-06</div>
					            </span>
					            <div class="info-box-content">
					              <span class="info-box-text">【人之初风采】 师德源于爱，爱源于学生</span>
					              <span class="info-box-number">幼儿园是还在踏入社会的第一步？近日...</span>
					            </div>
							</a>
						</div> */
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
			
			
		});
	</script>
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="news-garten">
				<div class="gap"></div>
				<div class="gap hidden-xs hidden-sm"></div>
				<div class="container">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 news-desc text-center text-large text-grey-green">${paramMap.systemDesc.paramName}</div>
					</div>
					<div class="gap"></div>
					<div class="gap hidden-xs hidden-sm"></div>
					<div id="news-row" class="row">
						<div class="col-md-6">
							<a href="#" class="info-box">
					            <span class="info-box-icon">
					            	<div class="day">25</div>
					            	<div class="yearMonth">2018-06</div>
					            </span>
					            <div class="info-box-content">
					              <span class="info-box-text">【人之初风采】 师德源于爱，爱源于学生</span>
					              <span class="info-box-number">幼儿园是还在踏入社会的第一步？近日...</span>
					            </div>
							</a>
						</div>
						<div class="col-md-6">
							<a href="#" class="info-box">
					            <span class="info-box-icon">
					            	<div class="day">25</div>
					            	<div class="yearMonth">2018-06</div>
					            </span>
					            <div class="info-box-content">
					              <span class="info-box-text">【人之初风采】 师德源于爱，爱源于学生</span>
					              <span class="info-box-number">幼儿园是还在踏入社会的第一步，幼儿园老师对孩子的影响可能伴随一生。你想象中的幼儿园老师是什么样子的？近日...</span>
					            </div>
							</a>
						</div>
						<div class="col-md-6">
							<a href="#" class="info-box">
					            <span class="info-box-icon">
					            	<div class="day">25</div>
					            	<div class="yearMonth">2018-06</div>
					            </span>
					            <div class="info-box-content">
					              <span class="info-box-text">【人之初风采】 师德源于爱，爱源于学生</span>
					              <span class="info-box-number">幼儿园是还在踏入社会的第一步，幼儿园老师对孩子的影响可能伴随一生。你想象中的幼儿园老师是什么样子的？近日...</span>
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