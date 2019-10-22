<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	<!-- <div class="navbg"></div> -->
    <ol class="carousel-indicators">
		<!-- <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li> -->
    </ol>
    <div class="carousel-inner">
        <%-- <div class="item active">
            <img style="width:100%;" src="${basePath }static/front/images/index-focus-5.png" alt="移动互联网开发">
        </div> --%>
    </div>
    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
        <span class="fa fa-angle-left"></span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
        <span class="fa fa-angle-right"></span>
    </a>
</div>

<script type="text/javascript">
	$(function(){
		var option = {
			url:basePath + "front/queryMedia",
			data:{mediaPattern : 1, mediaIsHome : 1},
			success:function(data){
				for(var i = 0; i < data.length; i++){
					//<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					/*
					<div class="item active">
			            <img style="width:100%;" src="${basePath }static/front/images/index-focus-5.png" alt="移动互联网开发">
			        </div>
					*/
					var li = $("<li data-target=\"#carousel-example-generic\"></li>");
					li.attr("data-slide-to",i);
					if(i == 0){
						li.addClass("active");
					}
					$(".carousel-indicators").append(li);
					
					var item = $("<div class=\"item\">");
					if(i == 0){
						item.addClass("active");
					}
					var img = $("<img style=\"width:100%;\"/>");
					img.attr("src",basePath + data[i].mediaUrl);
					item.append(img);
					$(".carousel-inner").append(item);
					
				}
			}
		};
		queryData(option);
	});
</script>
