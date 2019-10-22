$(function () {
	var map = null;
	//初始化表单校验函数，当打开增加/修改窗口时，调用该方法，初始化校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			onfocusout: false,
			ignore:"",//在校验时，框架不对对隐藏的元素进行验证，将ignore设置成""或者[],框架将不会对任何元素呼略校验，从而实现对隐藏元素的校验
			rules: {
				gartenName: {
					required: true
				},
				gartenPhone:{
					isTelOrPhone: true
				},
				gartenEmail:{
					isEmail: true
				},
				gartenZipCode:{
					isZipCode: true
				},
				gartenProvinceId:{
					selectRequired: true
				},
				gartenCityId:{
					selectRequired: true
				},
				gartenDistrictId:{
					selectRequired: true
				},
				gartenAddress:{
					required: true
				}
			}
		});
    }
	//初始化省份/城市/区县下拉框
	function initRegionSelect2(args, callback){
		initBasicSelect2({
			url: basePath + 'orgmgt/queryRegion',
			params: args,
			callback: function(data){
	        	var result = $.map(data, function (obj) {
		    		obj.id = obj.regionId;
					obj.text = obj.regionName;
					return obj;
	    		});
	        	if (callback && typeof (callback) == "function") {
	        		callback(data);
				}
	        }
		});
	}
	// 初始化百度地图，根据地址解析进行定位
	function initMap(mapOption){
		if(map == null){
			map = new BMap.Map(mapOption.mapContainerId);
			//设置地图点击事件
	    	if(mapOption.clickCallBack && typeof mapOption.clickCallBack == "function"){
	    		//单击获取点击的经纬度
	    		map.addEventListener("click",function(e){
	    			map.enableScrollWheelZoom(true);
	    			mapOption.clickCallBack(e);
	    		});
	    	}
		}
		var point = null;
		//如果有经纬度坐标，则根据坐标定位
		if(mapOption.data && mapOption.data.gartenLongitude != null && mapOption.data.gartenLongitude != "" &&
			mapOption.data.gartenLatitude != null && mapOption.data.gartenLatitude != ""){
			point = new BMap.Point(mapOption.data.gartenLongitude,mapOption.data.gartenLatitude);
			map.centerAndZoom(point,13);
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			map.panTo(point);
		} else if(mapOption.data && mapOption.data.gartenCityName != null && mapOption.data.gartenCityName != ""){
			/* 根据地址定位
			 */
			//map.centerAndZoom(item.gartenCityName,13);
	    	// 创建地址解析器实例
	    	var myGeo = new BMap.Geocoder();
	    	// 将地址解析结果显示在地图上,并调整地图视野
	    	myGeo.getPoint(mapOption.data.gartenCityName + mapOption.data.gartenDistrictName + mapOption.data.gartenAddress, function(point){
	    		if (point) {
	    			map.centerAndZoom(point, mapOption.zoomLevel ? mapOption.zoomLevel : 13);
	    			var marker = new BMap.Marker(point);
	    			map.addOverlay(marker);
	    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    		}else{
	    			tcAlert("您设置的地址没有解析到结果!");
	    		}
	    	}, mapOption.data.gartenCityName);
		} else {
			map.centerAndZoom(mapOption.center ? mapOption.center : "北京市",mapOption.zoomLevel ? mapOption.zoomLevel : 13);
		}
	}
	//获取当前用户所属校园的信息
	$.ajax({
		type : 'post',
		url : basePath + "orgmgt/queryGartenById",
		dataType : "json",
		cache : "false",
		success : function(data) {
			if(!ajaxAlert(data)){
				//没有成功，则直接return
				return;
			}
			//去掉加载遮罩
			$("div.overlay").remove();
			//获取校园数据
			var item = data.garten;
			
			UE.delEditor("gartenDesc");
    		//初始化ueditor控件
    		var updateUe1 = UE.getEditor('gartenDesc');
    		updateUe1.ready(function() {
    		    //设置编辑器的内容
    			var gartenDesc = item["gartenDesc"];
    			updateUe1.setContent(gartenDesc);
    			updateUe1.container.style.zIndex = 999;
    		});
    	
    		UE.delEditor("gartenSpeech");
    		//初始化ueditor控件
    		var updateUe2 = UE.getEditor('gartenSpeech');
    		updateUe2.ready(function() {
    		    //设置编辑器的内容
    			var gartenSpeech = item["gartenSpeech"];
    			updateUe2.setContent(gartenSpeech);
    			updateUe2.container.style.zIndex = 999;
    		});
			
			//初始化省份下拉框
    		initRegionSelect2({regionParentId:0},function(result){
    			$("select.gartenProvinceIdSelect2").select2({
	        		data:result
	        	});
    			$("select.gartenProvinceIdSelect2").val(item.gartenProvinceId).trigger('change');
    		});
    		//当省份下拉框变化时，初始化城市下拉框
    		$("select.gartenProvinceIdSelect2").on('change', function (e) {
    			var regionId = $(this).val();
    			initRegionSelect2({regionParentId:regionId},function(result){
    				$("select.gartenCityIdSelect2").empty().select2({
	            		data:result
	            	});
    				var option = $("<option selected=\"selected\" value=\"-1\">请选择城市</option>");
    				$("select.gartenCityIdSelect2").prepend(option);
    				if(regionId == item.gartenProvinceId){
    					$("select.gartenCityIdSelect2").val(item.gartenCityId);
    				}
    				$("select.gartenCityIdSelect2").trigger("change");
    			});
    		});
    		//当城市下拉框变化时，初始化区县下拉框
    		$("select.gartenCityIdSelect2").on('change', function (e) {
    			var regionId = $(this).val();
    			
    			initRegionSelect2({regionParentId:regionId},function(result){
    				$("select.gartenDistrictIdSelect2").empty().select2({
	            		data:result
	            	});
    				var option = $("<option selected=\"selected\" value=\"-1\">请选择区县</option>");
    				$("select.gartenDistrictIdSelect2").prepend(option);
    				if(regionId == item.gartenCityId){
    					$("select.gartenDistrictIdSelect2").val(item.gartenDistrictId);
    				}
    				$("select.gartenDistrictIdSelect2").trigger("change");
    			});
    		});
    		//当区县下拉框变化时，初始化地图
    		var mapCount = 1;
    		$("select.gartenDistrictIdSelect2").on("change",function(e){
    			if($("select.gartenCityIdSelect2").val() <= 0){
    				return;
    			}
    			var zoomLevel = 11;
    			var center = $("select.gartenCityIdSelect2 option:selected").text();
    			if($(this).val() > 0){
    				center += $("select.gartenDistrictIdSelect2 option:selected").text();
    				zoomLevel = 13;
    			}
    			//判断区县下拉框的值是否与原来值相同，如果不相同
    			
    			//根据地址显示地图定位
	        	initMap({
            		mapContainerId:"addressMap",
            		center:center,
        			zoomLevel:zoomLevel,
        			data:mapCount++ > 1 ? null : item,
        			clickCallBack:function(e){
        				var pt = e.point;
        				$("input[name='gartenLongitude']").val(pt.lng);
        				$("input[name='gartenLatitude']").val(pt.lat);
        			
        				var geoc = new BMap.Geocoder();
        				geoc.getLocation(pt, function(rs){
        					//先清除之前的定位marker
        					map.clearOverlays();
        					var marker = new BMap.Marker(pt);
        	    			map.addOverlay(marker);
        	    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        					var addComp = rs.addressComponents;
        					$("input[name='gartenAddress']").val(addComp.street + addComp.streetNumber);
        					//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
        					var curDistrictOption = $("select.gartenDistrictIdSelect2 option:contains('" + addComp.district + "')");
        					if(curDistrictOption.length > 0){
        						//如果当前地图定位的区与区县下拉框是不同的，则改变下拉框的值为地图定位的区县，否则不改变下拉框
        						if(curDistrictOption.val() != $("select.gartenDistrictIdSelect2").val()){
        							$("select.gartenDistrictIdSelect2 option").removeAttr("selected");
        							//districtSelect.attr("selected", true);
        							$("select.gartenDistrictIdSelect2").val(curDistrictOption.val()).trigger("change");
        						}
        					} else {
        						$("select.gartenDistrictIdSelect2").val(-1).trigger("change");
        					}
        				});
        			}
        		});
    		});
    		$("select").on("change", function(e) {
    			$(this).valid();
    		});
    		//解析校园信息，并回显
    		fillForm("updateForm", item);
    		//将校园名称设置为页面标题
    		$("h3.box-title").text(item.gartenName);
    		//初始化表单校验
    		initFormValidator("updateForm");
    		
    		//激活按钮
			$("button.btn-update").removeClass("disabled");
    		//点击保存按钮 提交修改
    		$("button.btn-update").click(function() {
				try {
					//提交表单数据之前，校验表单
	    			var validResult = $("#updateForm").valid();
	    			if(!validResult){
	    				//如果没有校验通过，则中断提交
	    				return;
	    			}
	    			//获取修改表单数据
	    			var param = formToJson($("#updateForm"));
					$.ajax({
						type : 'post',
						url : basePath + "orgmgt/updateGarten",
						data : param,
						dataType : "json",
						cache : "false",
						success : function(data) {
							if(!ajaxAlert(data)){
								//没有成功，则直接return
								return;
							}
				    		//提示信息
			            	tcAlert(data.info);
						},
						error : function(err) {
							ajaxErrorAlert(err);
						}
					});
				} catch (e) {
					tcAlert(e);
				}
			});
		},
		error : function(err) {
			ajaxErrorAlert(err);
		}
	});
	
});