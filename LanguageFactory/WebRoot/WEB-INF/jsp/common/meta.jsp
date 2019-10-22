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
<c:if test="${currentMenu != null}">
<title>${paramMap.systemTitle.paramName} ${currentMenu.menuName}</title>
</c:if>
<c:if test="${currentMenu == null}">
<title>${paramMap.systemTitle.paramName}</title>
</c:if>

<!-- 引入 Bootstrap -->
<link href="${basePath}static/adminLTE/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${basePath}static/adminLTE/dist/css/font-awesome.min.css" rel="stylesheet">
<!-- Ionicons -->
<link href="${basePath}static/adminLTE/dist/css/ionicons.min.css" rel="stylesheet">

<!-- **************************************plugins css*************************************** -->
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css"> -->
<!-- DataTables -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/datatables/dataTables.bootstrap.css">
<!-- jquery-confirm -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/jquery-confirm/dist/jquery-confirm.min.css">
<!-- select2 -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/select2-4.0.3/dist/css/select2.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/iCheck/all.css">
<!-- bootstrap-switch -->
<link rel="stylesheet" href="${basePath}static/adminLTE/plugins/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css">


<!-- AdminLTE Theme style -->
<link rel="stylesheet" href="${basePath}static/adminLTE/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
-->
<link rel="stylesheet" href="${basePath}static/adminLTE/dist/css/skins/skin-blue.min.css">
<!-- 本项目公用css -->
<link rel="stylesheet" href="${basePath}static/common/css/tcbase.css">


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

<!-- **************************************plugins js**************************************** -->
<!-- bootstrap datepicker -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script> -->
<script src="${basePath}static/adminLTE/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="${basePath}static/adminLTE/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<!-- Slimscroll -->
<script src="${basePath}static/adminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${basePath}static/adminLTE/plugins/fastclick/fastclick.js"></script>
<!-- DataTables -->
<script src="${basePath}static/adminLTE/plugins/datatables/jquery.dataTables.js"></script>
<script src="${basePath}static/adminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- jquery-confirm -->
<script src="${basePath}static/adminLTE/plugins/jquery-confirm/dist/jquery-confirm.min.js"></script>
<!-- jquery-form -->
<script src="${basePath}static/adminLTE/plugins/jquery-form/jquery.form.min.js"></script>
<!-- select2 -->
<script src="${basePath}static/adminLTE/plugins/select2-4.0.3/dist/js/select2.full.min.js"></script>
<script src="${basePath}static/adminLTE/plugins/select2-4.0.3/dist/js/i18n/zh-CN.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${basePath}static/adminLTE/plugins/iCheck/icheck.min.js"></script>
<!-- jquery validation 1.16.0-->
<script src="${basePath}static/adminLTE/plugins/jquery-validation-1.16.0/dist/jquery.validate.js"></script>
<script src="${basePath}static/adminLTE/plugins/jquery-validation-1.16.0/dist/localization/messages_zh.js"></script>
<!-- UEditor -->
<script type="text/javascript" src="${basePath}static/adminLTE/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${basePath}static/adminLTE/plugins/ueditor/ueditor.all.js"></script>
<!-- Bootstrap-switch -->
<script type="text/javascript" src="${basePath}static/adminLTE/plugins/bootstrap-switch-master/dist/js/bootstrap-switch_3.3.4.min.js"></script>
<!-- Bootstrap-treeview -->
<script type="text/javascript" src="${basePath}static/adminLTE/plugins/bootstrap-treeview-1.2.0/src/js/bootstrap-treeview.js"></script>
<!-- webSocket js文件 -->
<script src="${basePath}static/adminLTE/plugins/leopie-websocket/websocket.js"></script>
<!-- upload js文件 -->
<script src="${basePath}static/adminLTE/plugins/leopie-upload/leopie-upload.js"></script>


<!-- AdminLTE App -->
<script src="${basePath}static/adminLTE/dist/js/app.min.js"></script>
<!-- 全局js文件 -->
<script src="${basePath}static/common/js/tcbase.js"></script>
<script type="text/javascript">
	var rootPath = "${rootPath}";
	var basePath = "${basePath}";
	$("title").text($("title").text().replace(/<\/?[^>]*>/g, ''));
</script>
	
	
	