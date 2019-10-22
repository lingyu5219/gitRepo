<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<title>Insert title here</title>
	<style type="text/css">
		.input-border{
			width:200px;
			height:20px;
			line-height:20px;
			padding: 10px 10px;
			box-shadow: 0px 2px 6px 2px rgba(07, 20, 41, 0.2);
			border:1px solid transparent;
		    border-radius: 4px;
		    background-clip:padding-box,border-box;
		    background-origin:padding-box,border-box;
		    background-image:linear-gradient(90deg,#fff,#fff),linear-gradient(90deg,#000000,#9FBBFF);
			border-image: -webkit-linear-gradient(left, #4561FE,#9FBBFF) 1 3;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('.input-dateranges').datepicker({
			    format: "yyyy-mm",
			    startView: 2,
			    minViewMode: 1,
			    maxViewMode: 2,
			    clearBtn: true,
			    language: "zh-CN"
			});
			
			
			
		});
	</script>
</head>
<body>

	<div style="border:1px solid red; float:left;">
		<img src="${basePath}static/upload/file/media/pic/20180624/0fcb85650eb142f4849407c83396ee23.jpg"/>
	</div>
	<div class="yy-mm input-dateranges input-group" id="datepicker">
		<input type="text" readonly class="form-control" name="historyDateStart" placeholder="请输入查询开始日期" />
		<span class="input-group-addon">to</span>
		<input type="text" readonly class="form-control" name="historyDateEnd" placeholder="请输入查询结束日期"/>
	</div>
	<!-- <script type="text/javascript" src="http://counter.sina.com.cn/ip/" charset="gb2312"></script>       获取接口数据，注意charset  
	<script type="text/javascript">   
	document.writeln("IP地址："+ILData[0]+"<br />");             //输出接口数据中的IP地址   
	document.writeln("地址类型："+ILData[1]+"<br />");         //输出接口数据中的IP地址的类型   
	document.writeln("地址类型："+ILData[2]+"<br />");         //输出接口数据中的IP地址的省市  
	document.writeln("地址类型："+ILData[3]+"<br />");         //输出接口数据中的IP地址的  
	document.writeln("地址类型："+ILData[4]+"<br />");         //输出接口数据中的IP地址的运营商  
	</script> -->
	
	asdf<input type="text" class="input-border"/><br/><br/><br/><br/>
	asdf<input type="text" style="height:20px; line-height:20px; border: 1px solid #4561FE; border-image: -webkit-linear-gradient(left, #4561FE,#9FBBFF) 1 1;"/>
	
</body>
</html>