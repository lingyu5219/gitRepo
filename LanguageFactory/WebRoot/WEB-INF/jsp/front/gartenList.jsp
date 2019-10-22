<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			initRegionSelect2({regionParentId:26},function(result){
				$("select.gartenDistrictIdSelect2").empty().select2({
            		data:result
            	});
				var option = $("<option selected=\"selected\" value=\"-1\">请选择所在区</option>");
				$("select.gartenDistrictIdSelect2").prepend(option).trigger("change");
			});
			
    		$("select.gartenDistrictIdSelect2").on('change', function (e) {
    			var regionId = $(this).val();
    			
    			initBasicSelect2({
    				url: basePath + 'orgmgt/queryGarten',
    				params: {gartenDistrictId:regionId},
    				callback: function(data){
    		        	var result = $.map(data, function (obj) {
    			    		obj.id = obj.gartenId;
    						obj.text = obj.gartenName;
    						return obj;
    		    		});
    		        	$("select.gartenIdSelect2").empty().select2({
    	            		data:result
    	            	});
    					var option = $("<option selected=\"selected\" value=\"0\">请选择校园</option>");
    					$("select.gartenIdSelect2").prepend(option).trigger("change");
    		        }
    			});
    		});
			
			$(".btn-search").click(function(){
   				queryDataWithPage({
   					length : 3,
					pageContainer : $("#garten-page"),
					url : basePath + "front/queryGartenList",
					data : formToJson($("#gartenForm")),
   					success:function(dataTable){
   						$("#garten-list").empty();
   						var data = dataTable.data;
   						for(var i = 0; i < data.length; i++){
   							var garten = data[i];
   							/*${basePath }front/gartenDetail/1  ${basePath }front/gartenDetail/1    src="${basePath }static/front/images/garten-item2.png"*/
   							var gartenItem = $("<div class=\"row garten-item\">");
   							var gartenItemContainer = $("<div class=\"col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 garten-item-container no-padding\">");
   							var gartenItemLeft = $("<div class=\"col-sm-5 garten-item-left no-padding\">");
   							var gartenItemImga = $("<a class=\"garten-item-imga\">");
   							var gartenItemImg = $("<img class=\"img-responsive\"/>");
   							var gartenItemRight = $("<div class=\"col-sm-7 garten-item-right\">");
   							var gartenItemRightH2 = $("<h2>和欣家园校区</h2>");
   							var gartenItemDesc = $("<div class=\"garten-item-desc\">");
   							var gartenItemDetail = $("<div class=\"garten-item-detail\">");
   							var gartenItemDetailA = $("<a href=\"#\">[查看详情]</a>");
   							
   							gartenItem.attr("id","garten" + garten.gartenId);
   							gartenItemImga.attr("href", basePath + "front/gartenDetail/" + garten.gartenId);
   							//gartenItemImg.attr("src", basePath + "static/front/images/garten-item2.png");
   							gartenItemImg.attr("src", basePath + "static/front/images/" + getRandom(0,23) + ".jpg");
   							gartenItemImg.css("height","100%");
   							gartenItemRightH2.text(garten.gartenName);
   							gartenItemDesc.html(garten.gartenDesc);
   							gartenItemDetailA.attr("href", basePath + "front/gartenDetail/" + garten.gartenId);
							
   							gartenItemLeft.append(gartenItemImga.append(gartenItemImg));
   							gartenItemDetail.append(gartenItemDetailA);
   							gartenItemRight.append(gartenItemRightH2).append(gartenItemDesc).append(gartenItemDetail);
   							gartenItem.append(gartenItemContainer.append(gartenItemLeft).append(gartenItemRight));
   							
   							$("#garten-list").append(gartenItem).append($("<div class=\"gap\">"));
   						}
   					}
   				});
			});
			$(".btn-search").click();
		});
	</script>
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="garten-list">
				<div class="container text-center">
					<h2 class="text-grey-green">查询校园</h2>
					<div class="gap hidden-xs hidden-sm"></div>
					<form id="gartenForm" class="form-horizontal" role="form" method="post">
						<div class="row">
							<div class="col-sm-3 col-sm-offset-1 padding">
								<select class="form-control gartenDistrictIdSelect2" id="gartenDistrictId" name="gartenDistrictId" style="width: 100%">
									<option selected="selected" value="0">请选择所在区</option>
								</select>
							</div>
							<div class="col-sm-3 padding">
								<select class="form-control gartenIdSelect2" id="gartenId" name="gartenId" style="width: 100%">
									<option selected="selected" value="0">请选择校园</option>
								</select>
							</div>
							<div class="col-sm-4 padding">
								<div class="input-group">
					                <input type="text" class="form-control" name="gartenName" value="${gartenName }" placeholder="搜索您附近的幼儿园">
					                <div class="input-group-btn">
					                  <button type="button" class="btn btn-default btn-search"><i class="glyphicon glyphicon-search"></i></button>
					                </div>
					            </div>
							</div>
						</div>
					</form>
					<div class="gap"></div>
					<div id="garten-list">
					
					</div>
					<div class="row">
						<div id="garten-page"></div>
					</div>
					<div class="gap"></div>
				</div>
			</section>
			
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>