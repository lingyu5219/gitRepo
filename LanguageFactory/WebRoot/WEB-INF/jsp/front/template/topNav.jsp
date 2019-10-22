<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="main-header">
	<%-- <div class="top-bar">
		<div class="container">
			<i style="font-size:14px; color:#A8A8A8;" class="glyphicon glyphicon-earphone"></i>
			联系电话：${paramMap.systemPhone.paramName}
		</div>
	</div> --%>
    <nav class="navbar navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">
					<img width="60" height="60" class="pull-left" src="${basePath }static/front/images/logo.png"/>
					<b id="systemTitle">&nbsp;&nbsp;&nbsp;&nbsp;</b>
                </a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                	<i class="fa fa-bars"></i>
          		</button>
        	</div>
	        <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
	          	<ul class="nav navbar-nav">
		            
	          	</ul>
	        </div>
      	</div>
	    <div class="navbg"></div>
    </nav>
</header>
<script>
	$(function(){
		var stytemTitle = '${paramMap.systemTitle.paramName}';
		var noHtml = stytemTitle.replace(/<\/?[^>]*>/g, ''); 
		$("#systemTitle").append(noHtml);
	});
	
</script>