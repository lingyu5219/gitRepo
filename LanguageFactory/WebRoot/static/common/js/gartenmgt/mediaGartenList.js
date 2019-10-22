$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "groupmgt/queryMediaList",
		//ajax删除数据的http请求url
		delUrl : basePath + "groupmgt/delMedia",
		//增加页面
		addPageUrl : basePath + 'forward?page=gartenmgt/mediaGartenAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "groupmgt/addMedia",
		//修改页面
		updatePageUrl : basePath + 'forward?page=gartenmgt/mediaGartenModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "groupmgt/updateMedia",
		//查看页面
		detailPageUrl : basePath + 'forward?page=gartenmgt/mediaGartenDetail',
		//预览页面
		previewPageUrl : basePath + 'forward?page=groupmgt/mediaPreview',
		//文件上传URL
		uploadAction : basePath + "upload/uploadFile",
		//文件删除URL
		delAction : basePath + "upload/delFile",
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
		//预览功能的表单formID
		previewFormId : "previewForm",
		//表格列
		columns : [//对应thead里面的序列
	        {"data":null,width:"10px"},
	        {"data":'mediaTitle',
	        	"render":function(data,type,full,callback) {
	        		//return "<a href=\"" + basePath + full.mediaUrl + "\">" + data + "</a>";
	        		if(full.mediaPattern == 1){
	        			return "<i class=\"fa fa-file-image-o text-info\" title=\"图片\"></i> " + data;
	        		} else {
	        			return "<i class=\"fa fa-file-video-o text-success\" title=\"视频\"></i> " + data;
	        		}
	            }
	        },
	        {"data":'mediaPattern',
	        	"render":function(data,type,full,callback) {
	        		//根据格式显示相应的缩略图
	        		//媒体资源格式，1图片、2视频
	        		if(data == 1){
	        			return "<a href=\"javascript:showPic('" + full.mediaTitle + "','" + full.mediaUrl.replace(/\\/g,"/") + "')\"><img width=\"64\" class=\"img-thumbnail\" src=\"" + basePath + full.mediaUrl + "\"/></a>";
	        			
	        		} else {
	        			return "<a class=\"previewBtn\" href=\"javascript:void(0);\"><img width=\"64\" class=\"img-thumbnail\" src=\"" + basePath + "static/images/video_player_cover.png\"/></a>";
	        		}
	            }
	        },
	        {"data":'mediaIsFront',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		//是否前端显示，2否，1是
	        		return data == 1 ? 
	                		"<span class=\"label label-primary\"><i class=\"fa fa-check-square\"></i> 是</span>" 
	                		: "<span class=\"label label-default\"><i class=\"fa fa-square-o\"></i> 否</span>";
	            }
	        },
	        {"data":'createTime',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	                return full.createTime = data.substr(0,19); 
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 5,
		/*
		 * 如果操作按钮有修改、删除、查看这三个的，可不用此配置，默认会加入修改、删除、查看三个按钮
		 * 如果操作按钮不一定都是修改、删除、查看的，操作按钮可自定义，重点要指定按钮的类名，后面每个按钮通过类名绑定事件
		*/
		operBtns:[
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit"},
			{title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o"}
		]
	};
	
	
	//初始化资源集下拉框
	/*initBasicSelect2({
		url: basePath + 'groupmgt/queryAlbum',
		callback: function(data){
        	var result = $.map(data, function (obj) {
	    		obj.id = obj.albumId;
				obj.text = obj.albumName;
				
				return obj;
    		});
        	$("select.mediaAlbumIdSelect2").select2({data:result});
        	var option = $("<option selected=\"selected\" value=\"0\">请选择所属资源集</option>");
			$("select.mediaAlbumIdSelect2").prepend(option);
        }
	});*/
	
	//初始化表单校验函数，当打开增加/修改窗口时，调用该方法，初始化校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			ignore:"",
			rules: {
				mediaTitle: {
					required: true
				},
				mediaUrl: {
					required: true
				},
				mediaPattern: {
					selectRequired: true
				},
				mediaGartenId: {
					selectRequired: true
				}
			}
		});
    }
	
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw(true);//查询后不需要保持分页状态，回首页
    });
    
  //增加
    $("#btn-add").on("click", function () {
    	var jcAdd = addData(tableOption,
    		//以下3个方法可以自定义
    		//发送请求之前，执行此方法，一般在此方法中用于准备向请求传递的参数
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
	    	//窗口打开后，初始化操作，可自定义初始化内容，本功能中初始化了一个日期控件，下拉框控件
	    	function(content){
	    		//初始化资源集下拉框
        		/*initBasicSelect2({
        			url: basePath + 'groupmgt/queryAlbum',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		obj.id = obj.albumId;
							obj.text = obj.albumName;
							
							return obj;
			    		});
                    	content.find("select.mediaAlbumIdSelect2").select2({data:result});
                    	var option = $("<option selected=\"selected\" value=\"0\">请选择所属资源集</option>");
	    				content.find("select.mediaAlbumIdSelect2").prepend(option);
                    }
        		});
        		content.find("select.mediaAlbumIdSelect2").change(function(){
        			$(this).valid();
        		});*/
	    		
        		//初始化文件上传插件
	    		var leoUpload = new LeoUpload({
	    			disabled:true,//初始化上传插件，但是要设置为不可用的，只有当选择了资源格式之后再设置为可用
	    			fileMaxCount:1,
	    			fileMaxSize:52428800,//50MB
	    			fileLimitType:"",
	    			uploadPath : "static/upload/file/media/",
	    			uploadAction : tableOption.uploadAction,
	    			delAction : tableOption.delAction,
	    			picker : "mediaFileBtn",
	    			fileList : "fileList",
	    			afterSuccess : function(data){
	    				content.find("input[name='mediaUrl']").val(data.fileRelativePath);
	    				content.find("input[name='mediaFileName']").val(data.fileName);
	    				content.find("input[name='mediaFileSize']").val(data.fileSize);
	    				content.find("input[name='mediaUrl']").valid();
	    			},
	    			afterDelFile : function(data){
	    				content.find("input[name='mediaUrl']").val("");
	    				content.find("input[name='mediaFileName']").val("");
	    				content.find("input[name='mediaFileSize']").val("0");
	    				content.find("input[name='mediaUrl']").valid();
	    			}
	    		});
        		//当资源格式下拉框发生变化后，重置文件上传插件
        		content.find("select.mediaPatternSelect2").change(function(){
        			//先重置上传插件
        			leoUpload.reset();//会将之前上传的文件，删除，然后可以再重新选择文件上传
        			//如果没有选择资源格式，则直接返回
        			if($(this).val() == 0){
        				//关闭上传插件
        				leoUpload.disabled = true;
        				return;
        			}
        			var filePattern = "";
        			var uploadDir = "";
        			//1表示图片格式；2表示视频格式
        			if($(this).val() == 1){
        				filePattern = ".jpg|.png|.gif";
        				uploadDir = "pic";
        			} else if($(this).val() == 2){
        				filePattern = ".mp4";
        				uploadDir = "video";
        			}
        			leoUpload.fileLimitType = filePattern;
        			leoUpload.uploadPath = "static/upload/file/media/" + uploadDir;
        			//激活上传插件，设置为可用的
        			leoUpload.disabled = false;
        		});
        		
        		//初始化bootstrap-switch开关控件
	    		content.find("input[name='mediaIsFront']").bootstrapSwitch({
	    			state:false,
	    			onText:"是",  
	    	        offText:"否",
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	                $(this).val("1");  
	    	            }else{  
	    	                $(this).val("2");  
	    	            }
	    	            $(this).prop("checked",true);
	    	        }
	    		});
	    		content.find("input[name='mediaIsFront']").bootstrapSwitch('state', false);
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    	}
    	);
    });
    
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {mediaId:item.mediaId, mediaUrl:item.mediaUrl};
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
        var jcUpdate = updateData(tableOption,item,
        	//以下三个回掉函数可自定义
        	//此方法在窗口加载内容完成后调用，在此方法中，可执行一些初始化的操作
    		function(updateForm,item,content){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
        		fillForm(updateForm,item);
        		//初始化表单校验
	    		initFormValidator(tableOption.updateFormId);
        		//将资源文件的Url设置到mediaOriginalUrl，用来记录原来文件的url
        		content.find("input[name='mediaOriginalUrl']").val(item.mediaUrl);

        		//初始化bootstrap-switch开关控件
	    		content.find("input[name='mediaIsFront']").bootstrapSwitch({
	    			state:false,
	    			onText:"是",  
	    	        offText:"否",
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	                $(this).val("1");  
	    	            }else{  
	    	                $(this).val("2");  
	    	            }  
	    	            $(this).prop("checked",true);
	    	        }
	    		});
	    		//var switchEnableFlag = item.columnEnable == 1 ? true : false;
	    		if(item.mediaIsFront == 1){
	    			content.find("input[name='mediaIsFront']").bootstrapSwitch('state', true);
	    		}
	    		//初始化文件上传插件
	    		var leoUp = new LeoUpload({
	    			disabled:true,//初始化上传插件，但是要设置为不可用的，只有当选择了资源格式之后再设置为可用
	    			fileCount:1,//文件是必须要上传的，所以回显时，肯定有一个文件，所以将文件数量设置成1
	    			fileMaxCount:1,
	    			fileMaxSize:52428800,//50MB
	    			fileLimitType: item.mediaPattern == 1 ? ".jpg|.png|.gif" : ".mp4",
	    			uploadPath : "static/upload/file/media/" + (item.mediaPattern == 1 ? "pic" : "video"),
	    			uploadAction : tableOption.uploadAction,
	    			delAction : tableOption.delAction,
	    			picker : "mediaFileBtn",
	    			fileList : "fileList",
	    			afterSuccess : function(data){
	    				content.find("input[name='mediaUrl']").val(data.fileRelativePath);
	    				content.find("input[name='mediaFileName']").val(data.fileName);
	    				content.find("input[name='mediaFileSize']").val(data.fileSize);
	    				content.find("input[name='mediaUrl']").valid();
	    			},
	    			afterDelFile : function(data){
	    				content.find("input[name='mediaUrl']").val("");
	    				content.find("input[name='mediaFileName']").val("");
	    				content.find("input[name='mediaFileSize']").val("0");
	    				content.find("input[name='mediaUrl']").valid();
	    			}
	    		});
	    		//将上传按钮隐藏
	    		leoUp.picker.hide();
	    		//初始化文件item
	    		var file = {
	    			name:item.mediaFileName,
	    			size:item.mediaFileSize,
	    			fileRelativePath:item.mediaUrl
	    		};
	    		var fileItem = leoUp.createFileItem(file);
	    		fileItem.find(".leoFileProgressBar").css("width","100%");
	            fileItem.find(".leoFileProgressNum").text("100%");
	            //显示fileItem
				leoUp.fileList.append(fileItem);
				//绑定删除事件
	        	fileItem.find(".leoFileDelBtn").click(function(){
	        		//此处不会真的删除；避免真删除之后，如果没有点击保存更新数据库信息，当再次回显的时候，数据库中有文件记录数据，但是真实的文件已经删除，导致数据不一致
	        		fileItem.remove();
	        		content.find("input[name='mediaFileChanged']").val("1");
	        		leoUp.picker.show();
	        		leoUp.disabled = false;
	        		//移除fileItem后，清空mediaUrl的值
        			content.find("input[name='mediaUrl']").removeAttr("value");
        			content.find("input[name='mediaUrl']").valid();
	        		//leoUp.delFile(file,fileItem);
	        	});
	    		
	    		//当资源格式下拉框发生变化后，初始化文件上传插件
        		content.find("select.mediaPatternSelect2").change(function(){
        			//移除fileItem后，清空mediaUrl的值
        			content.find("input[name='mediaUrl']").removeAttr("value");
        			content.find("input[name='mediaUrl']").valid();
        			//先重置上传插件
        			var mediaFileChanged = content.find("input[name='mediaFileChanged']").val();
        			if(mediaFileChanged == "1"){
        				leoUp.reset();
        			}else{
        				fileItem.remove();
        				content.find("input[name='mediaFileChanged']").val("1");
    	        		leoUp.picker.show();
    	        		leoUp.disabled = false;
        			}
        			//如果没有选择资源格式，则直接返回
        			if($(this).val() == 0){
        				//关闭上传插件
        				leoUp.disabled = true;
        				return;
        			}
        			var filePattern = "";
        			var uploadDir = "";
        			//1表示图片格式；2表示视频格式
        			if($(this).val() == 1){
        				filePattern = ".jpg|.png|.gif";
        				uploadDir = "pic";
        			} else if($(this).val() == 2){
        				filePattern = ".mp4";
        				uploadDir = "video";
        			}
        			leoUp.fileLimitType = filePattern;
        			leoUp.uploadPath = "static/upload/file/media/" + uploadDir;
        			//激活上传插件，设置为可用的
        			leoUp.disabled = false;
        		});
        		
        	},
        	//发送请求之前，执行此方法，一般才此方法中用于准备向请求传递的参数
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
        	//请求响应后的回调方法
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
        	}
        );
    });
    
    //查看
    tableOption.table.on("click",".detailRow",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        var jcDetail = detailData(tableOption,item,function(detailForm,item,content){
			//初始化bootstrap-switch开关控件
    		content.find("input[name='mediaIsFront']").bootstrapSwitch({
    			onText:"是",  
    	        offText:"否"
    		});
    		//var switchEnableFlag = item.columnEnable == 1 ? true : false;
    		if(item.mediaIsFront == 1){
    			content.find("input[name='mediaIsFront']").bootstrapSwitch('state', true);
    		}
    		content.find("input[name='mediaIsFront']").bootstrapSwitch('disabled', true);
    		//如果是图片，则显示图片
    		if(item.mediaPattern == 1){
    			var img = "<img src=\"" + basePath + item.mediaUrl + "\"/>";
    			content.find("#media").html(img);
    		}
    		//如果是视频
    		if(item.mediaPattern == 2){
    			var video = "<div class=\"embed-responsive embed-responsive-16by9\"><video src=\"" + basePath + item.mediaUrl + "\" controls></video></div>";
    			content.find("#media").html(video);
    		}
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        	content.find("#mediaDesc").html(item.mediaDesc);
        })
    });
    
    //预览图片或视频
    tableOption.table.on("click","a.previewBtn",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        item.title = item.mediaTitle;
        var jcPreview = previewData(tableOption,item,function(previewForm,item,content){
    		//如果是图片，则显示图片
    		if(item.mediaPattern == 1){
    			var img = "<img src=\"" + basePath + item.mediaUrl + "\"/>";
    			content.find("#media").html(img);
    		}
    		//如果是视频
    		if(item.mediaPattern == 2){
    			var video = "<div class=\"embed-responsive embed-responsive-16by9\"><video src=\"" + basePath + item.mediaUrl + "\" controls></video></div>";
    			content.find("#media").html(video);
    		}
    		
        })
    });
});


