<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 class="parent-service-header">入园流程</h2>
<div class="gap hidden-xs hidden-sm"></div>
<ul class="parent-service-menu">
	<li>
		<a href="${basePath }front/parentService" 
		<c:if test='${relPath == "front/parentService"}'>
		class="active"
		</c:if>
		>
			<i class="fa fa-minus pull-left"></i>
			<span>入园流程</span>
			<i class="fa fa-chevron-circle-right pull-right"></i>
		</a>
	</li>
	<li>
		<a href="${basePath }front/signup"
		<c:if test='${relPath == "front/signup"}'>
		class="active"
		</c:if>
		>
			<i class="fa fa-minus pull-left"></i>
			<span>在线报名</span>
			<i class="fa fa-chevron-circle-right pull-right"></i>
		</a>
	</li>
	<li>
		<a href="${basePath }front/questions"
		<c:if test='${relPath == "front/questions"}'>
		class="active"
		</c:if>
		>
			<i class="fa fa-minus pull-left"></i>
			<span>常见问题与解答</span>
			<i class="fa fa-chevron-circle-right pull-right"></i>
		</a>
	</li>
</ul>
<div class="gap hidden-xs hidden-sm"></div>
<img class="img-responsive hidden-xs hidden-sm" src="${basePath }static/front/images/parent-service-img1.png"/>
<div class="gap hidden-xs hidden-sm"></div>
<img class="img-responsive hidden-xs hidden-sm" src="${basePath }static/front/images/parent-service-img2.png"/>