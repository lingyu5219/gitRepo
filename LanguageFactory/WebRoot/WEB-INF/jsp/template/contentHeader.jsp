<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<c:if test="${currentMenu != null}">
	<h4><b>${currentMenu.menuName }</b> <small>${currentMenu.remark }</small></h4>
	</c:if>
	<c:if test="${currentMenu == null}">
	<h4><b>${paramMap.systemTitle.paramName}</b></h4>
	</c:if>
	
	<ol class="breadcrumb">
		<li><a href="${basePath }forward?page=admin"><i class="glyphicon glyphicon-home"></i>首页</a></li>
		<c:if test="${currentMenu != null}">
		<li class="active">${currentMenu.menuName }</li>
		</c:if>
	</ol>
</section>