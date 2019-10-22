<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			var markupStyle = ["bg-blue","bg-aqua","bg-yellow","bg-maroon","bg-purple"];
			
			var option = {
				url : basePath + "front/queryHistory",
				success:function(rsData){
					$(".timeline").empty();
					var year = 0;
					for(var i = 0; i < rsData.length; i++){
						var history = rsData[i];
						var curYear = history.historyDate.substr(0,4);
						if(curYear != year){
							//创建一个timeline time label
						    var yearLabel = $("<li class=\"time-label\">");
						    var yearLabelText = $("<span class=\"bg-grey-green text-white\">");
						    yearLabelText.text(curYear + " 年");
						    yearLabel.append(yearLabelText);
						    $(".timeline").append(yearLabel);
						    
						    year = curYear;
						}
						//timeline item
					        
						var item = $("<li>")
						var random = parseInt(Math.random() * 5);
						var markup = $("<i class=\"fa " + markupStyle[random] + "\">");
						markup.text(history.historyDate.substr(5,2) + " 月");
						var timeItem = $("<div class=\"timeline-item\">");
						
						var itemBox = createBox();
						itemBox.find(".box-title").text(history.historyTitle);
						itemBox.find(".box-body").text(history.historyContent);
						itemBox.find(".box-footer").html("<span class=\"pull-right\"><i class=\"fa fa-clock-o\"></i> " + history.historyDate.substr(0,10) + "</span>")
						
						
						timeItem.append(itemBox);
						
						item.append(markup).append(timeItem);
						
						$(".timeline").append(item);
					}
					//最后显示时间markup符号
					var timeEnd = $("<li><i class=\"fa fa-clock-o bg-gray text-large\"></i></li>");
					$(".timeline").append(timeEnd);
				}
			}
			
			queryData(option);
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
					<h2>发展历程</h2>
					<div class="gap"></div>
					<ul class="timeline">
					    
					</ul>
				</div>
			</section>
			
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>