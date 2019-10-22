$(function () {
	var map = null;
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "orgmgt/queryGartenList",
		//ajax删除数据的http请求url
		delUrl : basePath + "orgmgt/delGarten",
		//增加页面
		addPageUrl : basePath + 'forward?page=orgmgt/gartenAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "orgmgt/addGarten",
		//修改页面
		updatePageUrl : basePath + 'forward?page=orgmgt/gartenModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "orgmgt/updateGarten",
		//查看页面
		detailPageUrl : basePath + 'forward?page=orgmgt/gartenDetail',
		//Table对象
		table : $('#dataListTable'),
		//查询表单Form对象
		form : $("#queryForm"),
		//增加功能的表单formID
		addFormId : "addForm",
		//修改功能的表单formID
		updateFormId : "updateForm",
		//查看功能的表单formID
		detailFormId : "detailForm",
		//表格列
		columns : [//对应thead里面的序列
	        {"data":null,width:"10px"},
	        {"data":'gartenName'},
	        {"data":'gartenEmail',className:'hidden-xs'},
	        {"data":'gartenPhone'},
	        {"data":'gartenZipCode',visible:false},
	        {"data":'gartenAddress',className:'hidden-xs'},
	        {"data":'createByName',className:'hidden-xs'},
	        {"data":'createTime',visible:false,
	        	"render":function(data,type,full,callback) {
	        		if(data != null && data != ""){
                        return data.substr(0,19);
                    }
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 8,
		operBtns : [
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit"},
			{title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o"}
		]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
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
	    			mapOption.clickCallBack(e);
	    		});
	    	}
	    	map.enableScrollWheelZoom(true);
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
	 //增加
	$("#btn-add").on("click", function () {
    	var jcAdd = addData(tableOption,
    		//以下2个方法可以自定义
    		function(){
	    		//获取表单数据之前，校验表单
				var validResult = $("#" +tableOption.addFormId).valid();
				if(validResult){
					//获取表单数据，此处可自行获取表单数据
					var param = formToJson($("#" +tableOption.addFormId));
					return param;
				} else {
					return false;
				}
	    	},
	    	//增加成功后的回调函数
	    	function(data){
	    		if(data.status == 1){
                	//刷新结果列表
            		dataTable.draw(true);
                }
	    		//提示信息
            	tcConfirm(data.info,"是否返回？",function(){
            		jcAdd.close();
            	});
	    	},
	    	//窗口打开后，初始化操作，可自定义初始化内容
	    	function(content){
	    		//窗口打开后 先删除之前初始化的ueditor，再重新初始化，否则会渲染失败
	    		UE.delEditor("gartenDesc");
	    		//初始化ueditor控件
	    		ue1 = UE.getEditor("gartenDesc");
	    		
	    		UE.delEditor("gartenSpeech");
	    		//初始化ueditor控件
	    		ue2 = UE.getEditor("gartenSpeech");
	    		
	    		//初始化省份下拉框
	    		initRegionSelect2({regionParentId:0},function(result){
	    			content.find("select.gartenProvinceIdSelect2").select2({
		        		data:result
		        	});
	    		});
	    		//当省份下拉框变化时，初始化城市下拉框
	    		content.find("select.gartenProvinceIdSelect2").on('change', function (e) {
	    			var regionId = $(this).val();
	    			
	    			initRegionSelect2({regionParentId:regionId},function(result){
	    				content.find("select.gartenCityIdSelect2").empty().select2({
    	            		data:result
    	            	});
	    				var option = $("<option selected=\"selected\" value=\"-1\">请选择城市</option>");
	    				content.find("select.gartenCityIdSelect2").prepend(option).trigger("change");
	    			});
	    		});
	    		//当城市下拉框变化时，初始化区县下拉框
	    		content.find("select.gartenCityIdSelect2").on('change', function (e) {
	    			var regionId = $(this).val();
	    			
	    			initRegionSelect2({regionParentId:regionId},function(result){
	    				content.find("select.gartenDistrictIdSelect2").empty().select2({
    	            		data:result
    	            	});
	    				var option = $("<option selected=\"selected\" value=\"-1\">请选择区县</option>");
	    				content.find("select.gartenDistrictIdSelect2").prepend(option).trigger("change");
	    			});
	    		});
	    		//当区县下拉框变化时，初始化地图
	    		content.find("select.gartenDistrictIdSelect2").on("change",function(e){
	    			if(content.find("select.gartenCityIdSelect2").val() <= 0){
	    				return;
	    			}
	    			var zoomLevel = 11;
	    			var center = content.find("select.gartenCityIdSelect2 option:selected").text();
	    			if($(this).val() > 0){
	    				center += content.find("select.gartenDistrictIdSelect2 option:selected").text();
	    				zoomLevel = 13;
	    			}
	    			
	    			//根据地址显示地图定位
		        	initMap({
	            		mapContainerId:"addressMap",
	            		center:center,
	        			zoomLevel:zoomLevel,
	        			clickCallBack:function(e){
	        				var pt = e.point;
	        				content.find("input[name='gartenLongitude']").val(pt.lng);
	        				content.find("input[name='gartenLatitude']").val(pt.lat);
	        			
	        				var geoc = new BMap.Geocoder();
	        				geoc.getLocation(pt, function(rs){
	        					//先清除之前的定位marker
	        					map.clearOverlays();
	        					var marker = new BMap.Marker(pt);
	        	    			map.addOverlay(marker);
	        	    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	        					var addComp = rs.addressComponents;
	        					content.find("input[name='gartenAddress']").val(addComp.street + addComp.streetNumber);
	        					//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
	        					var curDistrictOption = content.find("select.gartenDistrictIdSelect2 option:contains('" + addComp.district + "')");
	        					if(curDistrictOption.length > 0){
	        						//如果当前地图定位的区与区县下拉框是不同的，则改变下拉框的值为地图定位的区县，否则不改变下拉框
	        						if(curDistrictOption.val() != content.find("select.gartenDistrictIdSelect2").val()){
	        							content.find("select.gartenDistrictIdSelect2 option").removeAttr("selected");
	        							//districtSelect.attr("selected", true);
	        							content.find("select.gartenDistrictIdSelect2").val(curDistrictOption.val()).trigger("change");
	        						}
	        					} else {
	        						content.find("select.gartenDistrictIdSelect2").val(-1).trigger("change");
	        					}
	        				});
	        			}
	        		});
		        	
	    		});
	    		content.find("select").on("change", function(e) {
	    			$(this).valid();
	    		});
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    	},
	    	//当关闭窗口时，将map设置为null
	    	function(){
	    		map = null;
	    	}
    	);
    });
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw();//查询后不需要保持分页状态，回首页
    });
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {gartenId:item.gartenId};
        //调用删除方法，向服务器发送请求
        delDataTableItem(dataTable,tableOption,params,function(data){
        	if(data.status == 1){
            	//dataTable.ajax.reload();
            	dataTable.draw(true);
            }
            tcAlert(data.info, 6000);
        });
    });
    
    //修改
    tableOption.table.on("click",".editRow",function() {
    	//要修改哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        if(item.gartenZipCode == 0){
        	item.gartenZipCode = null;
        }
        
        var jcUpdate = updateData(tableOption,item,
        	//以下三个回掉函数可自定义
        	//此方法在窗口加载内容完成后调用，在此方法中，可执行一些初始化的操作
    		function(updateForm,item,content){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
	        	//窗口打开后 先删除之前初始化的ueditor，再重新初始化，否则会渲染失败
	    		UE.delEditor("gartenDesc");
	    		//初始化ueditor控件
	    		var updateUe1 = UE.getEditor('gartenDesc');
	    		updateUe1.ready(function() {
	    		    //设置编辑器的内容
	    			var gartenDesc = item["gartenDesc"];
	    			updateUe1.setContent(gartenDesc);
	    		});
        	
	    		UE.delEditor("gartenSpeech");
	    		//初始化ueditor控件
	    		var updateUe2 = UE.getEditor('gartenSpeech');
	    		updateUe2.ready(function() {
	    		    //设置编辑器的内容
	    			var gartenSpeech = item["gartenSpeech"];
	    			updateUe2.setContent(gartenSpeech);
	    		});
	    		
	        	//初始化省份下拉框
	    		initRegionSelect2({regionParentId:0},function(result){
	    			content.find("select.gartenProvinceIdSelect2").select2({
		        		data:result
		        	});
	    			content.find("select.gartenProvinceIdSelect2").val(item.gartenProvinceId).trigger('change');
	    		});
	    		/*//初始化城市下拉框
	    		initRegionSelect2({regionParentId:item.gartenProvinceId},function(result){
	    			content.find("select.gartenCityIdSelect2").select2({
		        		data:result
		        	});
	    			content.find("select.gartenCityIdSelect2").val(item.gartenCityId).trigger('change');
	    		});
	    		//初始化区县下拉框
	    		initRegionSelect2({regionParentId:item.gartenCityId},function(result){
	    			content.find("select.gartenDistrictIdSelect2").select2({
		        		data:result
		        	});
	    			content.find("select.gartenDistrictIdSelect2").val(item.gartenDistrictId).trigger('change');
	    		});*/
	    		//当省份下拉框变化时，初始化城市下拉框
	    		content.find("select.gartenProvinceIdSelect2").on('change', function (e) {
	    			var regionId = $(this).val();
	    			
	    			initRegionSelect2({regionParentId:regionId},function(result){
	    				content.find("select.gartenCityIdSelect2").empty().select2({
		            		data:result
		            	});
	    				var option = $("<option selected=\"selected\" value=\"-1\">请选择城市</option>");
	    				content.find("select.gartenCityIdSelect2").prepend(option);
	    				if(regionId == item.gartenProvinceId){
	    					content.find("select.gartenCityIdSelect2").val(item.gartenCityId);
	    				}
	    				content.find("select.gartenCityIdSelect2").trigger("change");
	    				//content.find("select.gartenCityIdSelect2").prev("span.input-group-addon").css("border-color", "#d2d6de");
	    				//content.find("select.gartenDistrictIdSelect2").prev("span.input-group-addon").css("border-color", "#d2d6de");
	    			});
	    		});
	    		//当城市下拉框变化时，初始化区县下拉框
	    		content.find("select.gartenCityIdSelect2").on('change', function (e) {
	    			var regionId = $(this).val();
	    			
	    			initRegionSelect2({regionParentId:regionId},function(result){
	    				content.find("select.gartenDistrictIdSelect2").empty().select2({
		            		data:result
		            	});
	    				var option = $("<option selected=\"selected\" value=\"-1\">请选择区县</option>");
	    				content.find("select.gartenDistrictIdSelect2").prepend(option);
	    				if(regionId == item.gartenCityId){
	    					content.find("select.gartenDistrictIdSelect2").val(item.gartenDistrictId);
	    				}
	    				content.find("select.gartenDistrictIdSelect2").trigger("change");
	    				//content.find("select.gartenDistrictIdSelect2").prev("span.input-group-addon").css("border-color", "#d2d6de");
	    			});
	    		});
	    		//当区县下拉框变化时，初始化地图
	    		var mapCount = 1;
	    		content.find("select.gartenDistrictIdSelect2").on("change",function(e){
	    			if(content.find("select.gartenCityIdSelect2").val() <= 0){
	    				return;
	    			}
	    			var zoomLevel = 11;
	    			var center = content.find("select.gartenCityIdSelect2 option:selected").text();
	    			if($(this).val() > 0){
	    				center += content.find("select.gartenDistrictIdSelect2 option:selected").text();
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
	        				content.find("input[name='gartenLongitude']").val(pt.lng);
	        				content.find("input[name='gartenLatitude']").val(pt.lat);
	        			
	        				var geoc = new BMap.Geocoder();
	        				geoc.getLocation(pt, function(rs){
	        					//先清除之前的定位marker
	        					map.clearOverlays();
	        					var marker = new BMap.Marker(pt);
	        	    			map.addOverlay(marker);
	        	    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	        					var addComp = rs.addressComponents;
	        					content.find("input[name='gartenAddress']").val(addComp.street + addComp.streetNumber);
	        					//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
	        					var curDistrictOption = content.find("select.gartenDistrictIdSelect2 option:contains('" + addComp.district + "')");
	        					if(curDistrictOption.length > 0){
	        						//如果当前地图定位的区与区县下拉框是不同的，则改变下拉框的值为地图定位的区县，否则不改变下拉框
	        						if(curDistrictOption.val() != content.find("select.gartenDistrictIdSelect2").val()){
	        							content.find("select.gartenDistrictIdSelect2 option").removeAttr("selected");
	        							//districtSelect.attr("selected", true);
	        							content.find("select.gartenDistrictIdSelect2").val(curDistrictOption.val()).trigger("change");
	        						}
	        					} else {
	        						content.find("select.gartenDistrictIdSelect2").val(-1).trigger("change");
	        					}
	        				});
	        			}
	        		});
	    		});
	    		
	    		content.find("select").on("change", function(e) {
	    			$(this).valid();
	    		});
        		fillForm(updateForm,item);
	    		//初始化表单校验
	    		initFormValidator(tableOption.updateFormId);
	    		//根据地址显示地图定位
	    		var mapOption = {
            		mapContainerId:"addressMap",
        			data:item,
        			clickCallBack:function(e){
        				var pt = e.point;
        				content.find("input[name='gartenLongitude']").val(pt.lng);
        				content.find("input[name='gartenLatitude']").val(pt.lat);
        			
        				var geoc = new BMap.Geocoder();
        				geoc.getLocation(pt, function(rs){
        					//先清除之前的定位marker
        					map.clearOverlays();
        					var marker = new BMap.Marker(pt);
        	    			map.addOverlay(marker);
        	    			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        					var addComp = rs.addressComponents;
        					content.find("input[name='gartenAddress']").val(addComp.street + addComp.streetNumber);
        					//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
        					var curDistrictOption = content.find("select.gartenDistrictIdSelect2 option:contains('" + addComp.district + "')");
        					if(curDistrictOption.length > 0){
        						content.find("select.gartenDistrictIdSelect2 option").removeAttr("selected");
        						//districtSelect.attr("selected", true);
        						content.find("select.gartenDistrictIdSelect2").val(curDistrictOption.val()).trigger("change");
        					} else {
        						content.find("select.gartenDistrictIdSelect2").val(-1).trigger("change");
        					}
        					//$("#id option:contains("值").attr("selected", true).trigger("change");
        				});
        			}
        		}
	        	//initMap(mapOption);
        	},
        	function(){
        		//获取表单数据之前，校验表单
    			var validResult = $("#" +tableOption.updateFormId).valid();
    			if(validResult){
	        		//获取修改表单数据
	        		var param = formToJson($("#" + tableOption.updateFormId));
	        		return param;
    			} else {
    				return false;
    			}
        	},
        	function(data){
        		//修改成功后提示信息
        		if(data.status == 1){
                	//刷新结果列表
            		dataTable.draw(true);
                }
	    		//提示信息
            	tcConfirm(data.info,"是否返回？",function(){
            		jcUpdate.close();
            	});
        	},
        	//当关闭窗口时，将map设置为null
        	function(){
        		map = null;
        	}
        );
    });
    
    //查看
    tableOption.table.on("click",".detailRow",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        if(item.gartenZipCode == 0){
        	item.gartenZipCode = null;
        }
        var jcDetail = detailData(tableOption,item,function(detailForm,item){
        	
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        	$("#gartenDesc").append(item["gartenDesc"]);
        	$("#gartenSpeech").append(item["gartenSpeech"]);
        	
        	//根据地址显示地图定位
        	var mapOption = {
        		mapContainerId:"addressMap",
    			data:item
    		}
        	initMap(mapOption);
        	/*
        	var script = document.createElement("script");  
    	    script.src = "http://api.map.baidu.com/api?v=2.0&ak=nbZjIball3R0jjtTIiGYaKSU9RLfynjr&callback=initMap";
    	    document.head.appendChild(script);
        	 */
        	//$.getScript("http://api.map.baidu.com/api?v=2.0&ak=nbZjIball3R0jjtTIiGYaKSU9RLfynjr&callback=initMap");
        	
        },
    	//当关闭窗口时，将map设置为null
    	function(){
    		map = null;
    	});
    });
});