<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="leo-sidebar">
	<ul>
		<li>
			<a href="${basePath}front/mediaList">
				<img width="59" height="59" src="${basePath}static/front/images/media.png"/>
				<div class="title hidden-xs hidden-sm">视频或图片</div>
			</a>
		</li>
		<li>
			<a href="${basePath}front/questions">
				<img width="59" height="59" src="${basePath}static/front/images/health.png"/>
				<div class="title hidden-xs hidden-sm">儿童健康</div>
			</a>
		</li>
		<li>
			<a href="${basePath}front/msgBoard">
				<img width="59" height="59" src="${basePath}static/front/images/msgboard.png"/>
				<div class="title hidden-xs hidden-sm">留言板</div>
			</a>
		</li>
		<li>
			<a href="${basePath}front/signup">
				<img width="59" height="59" src="${basePath}static/front/images/signup.png"/>
				<div class="title hidden-xs hidden-sm">在线报名</div>
			</a>
		</li>
	</ul>
</div>