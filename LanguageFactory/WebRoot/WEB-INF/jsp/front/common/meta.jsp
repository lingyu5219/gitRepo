<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
request.setAttribute("rootPath", path);
request.setAttribute("basePath", basePath);
%>
<base href="${basePath}"/>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>${paramMap.systemTitle.paramName}</title>

<!-- 引入 Bootstrap -->
<link href="${basePath}static/adminLTE/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${basePath}static/adminLTE/dist/css/font-awesome.min.css" rel="stylesheet">
<!-- Ionicons -->
<link href="${basePath}static/adminLTE/dist/css/ionicons.min.css" rel="stylesheet">
<!-- AdminLTE Theme style -->
<link rel="stylesheet" href="${basePath}static/adminLTE/dist/css/AdminLTE.min.css">
<!-- select2 -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/select2-4.0.3/dist/css/select2.min.css">
<!-- bootstrap-switch -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css">
<!-- leo-pagination -->
<link rel="stylesheet" href="${basePath}static/front/plugins/leoPagination/css/leo-pagination.css">
<!-- jquery-confirm -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/jquery-confirm/dist/jquery-confirm.min.css">

<link rel="stylesheet" href="${basePath}static/adminLTE/dist/css/skins/skin-custom.css">
<link rel="stylesheet" href="${basePath}static/front/css/front.css">
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="${basePath}static/adminLTE/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="${basePath}static/adminLTE/bootstrap/js/bootstrap.min.js"></script>
<!-- select2 -->
<script src="${basePath}static/adminLTE/plugins/select2-4.0.3/dist/js/select2.full.min.js"></script>
<script src="${basePath}static/adminLTE/plugins/select2-4.0.3/dist/js/i18n/zh-CN.js"></script>
<!-- Bootstrap-switch -->
<script type="text/javascript" src="${basePath}static/adminLTE/plugins/bootstrap-switch-master/dist/js/bootstrap-switch_3.3.4.min.js"></script>
<!-- bootstrap datepicker -->
<script src="${basePath}static/adminLTE/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="${basePath}static/adminLTE/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<!-- leo-pagination -->
<script type="text/javascript" src="${basePath}static/front/plugins/leoPagination/js/leo-pagination.js"></script>
<!-- jquery-confirm -->
<script src="${basePath}static/adminLTE/plugins/jquery-confirm/dist/jquery-confirm.min.js"></script>
<!-- jquery validation 1.16.0-->
<script src="${basePath}static/adminLTE/plugins/jquery-validation-1.16.0/dist/jquery.validate.js"></script>
<script src="${basePath}static/adminLTE/plugins/jquery-validation-1.16.0/dist/localization/messages_zh.js"></script>
	
<!-- AdminLTE App -->
<script src="${basePath}static/adminLTE/dist/js/app.js"></script>
<script src="${basePath}static/front/js/front.js"></script>

<script type="text/javascript">
	var rootPath = "${rootPath}";
	var basePath = "${basePath}";
	var relPath = "${relPath}";
	var path = window.location.pathname.length > 0 ? window.location.pathname.substr(1) : "";
	
	$("title").text($("title").text().replace(/<\/?[^>]*>/g, ''));
</script>
