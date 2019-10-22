<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel">
			<div class="pull-left image">
				<c:if test="${not empty personalInfo.personal.staffPic}">
				<img style="height:45px; object-fit: cover;" src="${basePath}${personalInfo.personal.staffPic}" class="img-circle" alt="User Image">
				</c:if>
				<c:if test="${empty personalInfo.personal.staffPic}">
				<img style="height:45px; object-fit: cover;" src="${basePath}static/images/man.png" class="img-circle" alt="User Image">
				</c:if>
			</div>
			<div class="pull-left info">
				<p>${personalInfo.personal.staffGartenName }</p>
				<!-- Status -->
				<a href="${basePath }forward?page=system/userInfo"><c:if test="${not empty personalInfo.personal.staffName}">${personalInfo.personal.staffName}</c:if></a>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu">
			<li class="header text-center"><span class="text-large text-light">系统菜单</span></li>
			
			<c:forEach items="${menuTreeList}" var="module">
			<c:if test="${not empty module.subMenuList}">
			<li class="treeview<c:if test="${currentMenu.moduleId == module.moduleId }"> active</c:if>">
				<a href="#">
					<c:if test="${empty module.moduleIcon}">
					<i class="fa fa-sitemap"></i>
					</c:if>
					<c:if test="${not empty module.moduleIcon}">
					<i class="fa ${module.moduleIcon}"></i>
					</c:if>
					<span><c:out value='${module.moduleName}'/></span>
					<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<c:forEach items="${module.subMenuList}" var="subMenu">
					<li<c:if test="${currentMenu.menuId == subMenu.menuId }"> class="active"</c:if>>
						<a href="<c:out value='${subMenu.location}'/>">
							<i class="fa fa-circle-o <c:if test="${currentMenu.menuId == subMenu.menuId }">text-aqua</c:if>"></i>
							<span <c:if test="${currentMenu.menuId == subMenu.menuId }">class="text-aqua"</c:if>><c:out value='${subMenu.menuName}'/></span>
						</a>
					</li>
					</c:forEach>
				</ul>
			</li>
			</c:if>
			</c:forEach>
			<!-- 
			<li class="treeview active">
				<a href="#">
					<i class="fa fa-sitemap"></i><span>模块名</span>
					<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<li class="active"><a href="#"><i class="fa fa-circle-o text-aqua"></i><span>菜单名</span></a></li>
					<li><a href="#"><i class="fa fa-circle-o"></i><span>菜单名</span></a></li>
				</ul>
			</li>
			 -->
		</ul>
	</section>
</aside>