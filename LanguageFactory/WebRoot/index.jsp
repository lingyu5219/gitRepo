<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path + "/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<script type="text/javascript">
			window.location.href="${basePath}front/index";
		</script>
	</head>
	<body>
	
	</body>
</html>