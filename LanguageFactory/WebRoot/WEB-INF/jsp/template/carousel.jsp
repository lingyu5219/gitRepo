<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		<li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
		<li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
    </ol>
    <div class="carousel-inner">
        <div class="item active">
            <img style="width:100%;" src="${basePath }static/images/index_focus_1.jpg" alt="移动互联网开发">
            <div class="carousel-caption"></div>
        </div>
        <div class="item">
            <img style="width:100%;" src="${basePath }static/images/index_focus_2.jpg" alt="全栈开发">
            <div class="carousel-caption"></div>
        </div>
        <div class="item">
            <img style="width:100%;" src="${basePath }static/images/index_focus_3.jpg" alt="NIIT云课堂">
            <div class="carousel-caption"></div>
        </div>
        <div class="item">
            <img style="width:100%;" src="${basePath }static/images/index_focus_4.jpg" alt="大数据云计算">
            <div class="carousel-caption"></div>
        </div>
    </div>
    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
        <span class="fa fa-angle-left"></span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
        <span class="fa fa-angle-right"></span>
    </a>
</div>