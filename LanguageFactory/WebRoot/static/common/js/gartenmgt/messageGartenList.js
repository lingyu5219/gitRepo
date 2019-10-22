$(function () {
	var ue;
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "groupmgt/queryMessageList",
		//ajax删除数据的http请求url
		delUrl : basePath + "groupmgt/delMessage",	
		//增加页面
		addPageUrl : basePath + 'forward?page=gartenmgt/messageGartenAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "groupmgt/addMessage",
		//修改页面
		updatePageUrl : basePath + 'forward?page=gartenmgt/messageGartenModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "groupmgt/updateMessage",
		//查看页面
		detailPageUrl : basePath + 'forward?page=gartenmgt/messageGartenDetail',
		//视频选择页面
		mediaSearchPageUrl : basePath + 'forward?page=gartenmgt/mediaGartenSearch',
		//Table对象
		table : $('#dataListTable'),
		//查询表单Form对象
		form : $("#queryForm"),
		//增加功能的表单form对象
		addFormId : "addForm",
		//修改功能的表单formID
		updateFormId : "updateForm",
		//查看功能的表单formID
		detailFormId : "detailForm",
		//表格列
		columns : [//对应thead里面的序列
	        {"data":null,width:"10px"},
	        {"data":'msgTitle'},
	        {"data":'msgType',
	        	"render":function(data,type,full,callback) {
	        		var span = $("<span class=\"label\">");
	        		//消息类型 ：1新闻、2活动、3公告
	        		switch(data){
		        		case 1:
		        			span.text("新闻");
		        			span.addClass("label-primary");
		        			break;
		        		case 2:
		        			span.text("活动");
		        			span.addClass("label-info");
		        			break;
		        		case 3:
		        			span.text("公告");
		        			span.addClass("label-warning");
		        			break;
		        		default:
		        			span.text("未知");
			        		span.addClass("label-danger");
	        		}
	        		return $("<span>").append(span).html();
	            }
	        },
	        {"data":'msgPattern',
	        	"render":function(data,type,full,callback) {
	        		//消息格式，1图文、2视频
	        		if(data == 1){
	        			return "<i class=\"fa fa-file-word-o text-info\" title=\"图文\"></i>";
	        		} else {
	        			return "<i class=\"fa fa-file-video-o text-success\" title=\"视频\"></i>";
	        		}
	            }
	        },
	        {"data":'msgGartenId',className:'hidden-xs hidden-sm',
	        	"render":function(data,type,full,callback) {
	        		if(data > 0){
	        			//归属校园
	        			return "<span class=\"label label-info\">" + full.msgGartenName + "</span>"
	        		} else {
	        			//归属集团
	        			return "<span class=\"label label-primary\">集团</span>"
	        		}
	            }
	        },
	        {"data":'createByName',className:'hidden-xs hidden-sm'},
	        {"data":'createTime',className:'hidden-xs hidden-sm',
	        	"render":function(data,type,full,callback) {
	        		return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 7
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	//初始化表单校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			ignore:"",
			rules: {
				msgTitle: {
					required: true
				},
				msgType: {
					selectRequired: true
				},
				msgPattern: {
					selectRequired: true
				},
				msgGartenId: {
					selectRequired: true
				},
				msgContent:{
					required:true
				}
			}
		});
		
	}
	//弹出窗口选择视频
	function searchMedia(tableOption, parentContent){
		$.dialog({
			title : '选择视频',
			content : 'url:' + tableOption.mediaSearchPageUrl,
			columnClass : 'col-md-8 col-md-offset-2',
			onContentReady : function() {
				var dialog = this;
				var content = dialog.$content;
				//设置资源格式为2，只查询视频
				content.find("input[name='mediaPattern']").val(2);
				//初始化表格
				var tbOption = {
					//ajax查询数据的http请求url
					queryUrl : basePath + "groupmgt/queryMediaList",
					//预览页面
					previewPageUrl : basePath + 'forward?page=groupmgt/mediaPreview',
					//Table对象
					table : content.find('#searchListTable'),
					//查询表单Form对象
					form : content.find("#searchForm"),
					//预览功能的表单formID
					previewFormId : "previewForm",
					
					columns : [//对应thead里面的序列
						{"data":null},
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
				        {"data":null}
				    ],
			        //操作按钮所在列
					operBtnColumn : 3,
					operBtns:[
						{title:"选择",btnClass:"btn-success choseRow",iconClass:"fa-circle-o"}
					]
				};
				//初始化表格控件
				var dt = initDataTable(tbOption);
				//查询按钮
				tbOption.table.on("click",".choseRow",function() {
					var item = dt.row($(this).closest('tr')).data();
					/*var choseBtn = $(this).children("i");
					if(choseBtn.hasClass("fa-circle-o")){
						choseBtn.removeClass("fa-circle-o").addClass("fa-dot-circle-o");
					} else {
						choseBtn.removeClass("fa-dot-circle-o").addClass("fa-circle-o");
					}*/
					parentContent.find("input[name='msgVideoId']").val(item.mediaId);
					parentContent.find("#msgVideo").attr("src",basePath + item.mediaUrl);
					parentContent.find("#msgVideo").removeClass("hidden");
					//parentContent.find("#msgVideo source").get(0).play();
					dialog.close();
				});
				//预览图片或视频
				tbOption.table.on("click","a.previewBtn",function() {
			    	//要查看哪一行数据对象
			        var item = dt.row($(this).closest('tr')).data();
			        item.title = item.mediaTitle;
			        var jcPreview = previewData(tbOption,item,function(previewForm,item,content){
			    		//如果是视频
			    		if(item.mediaPattern == 2){
			    			var video = "<div class=\"embed-responsive embed-responsive-16by9\"><video src=\"" + basePath + item.mediaUrl + "\" controls></video></div>";
			    			content.find("#media").html(video);
			    		}
			    		
			        })
			    });
				
			    content.find("#btn-query").on("click", function () {
			        dt.draw(true);//查询后不需要保持分页状态，回首页
			    });
			}
		});
	}
	
    //查询按钮
    $("#btn-query").on("click", function () {
		dataTable.draw(true);//查询后不需要保持分页状态，回首页
    });
    
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
	    		UE.delEditor("msgContent");
	    		//初始化ueditor控件
	    		ue = UE.getEditor('msgContent');
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    		//消息格式如果是视频，则显示选择视频
	    		content.find("select.msgPatternSelect2").change(function(){
        			var pat = $(this).val();
        			if(pat == 2){
        				content.find("textarea[name='msgContent']").rules("remove");
        				//如果是视频，则显示选择视频的按钮
        				content.find("#msgVideoChosser").removeClass("hidden");
        				//视频选择按钮设置点击事件处理
        				content.find("#msgVideoChosser").find("input[type='button']").unbind("click");
        				content.find("#msgVideoChosser").find("input[type='button']").click(function(){
        					//打开搜索媒体资源窗口，选择视频
        					searchMedia(tableOption,content);
        				});
        				content.find("input[name='msgVideoId']").removeAttr("value");
        				//设置存储所选视频ID的input为必填
        				content.find("input[name='msgVideoId']").rules("add",{required:true});
        				//清空内容编辑器，并且隐藏内容编辑器
        				//content.find("label[for='msgContent']").text("视频");
        				ue.setContent("", false);//若传入true，不清空原来的内容，在最后插入内容，否则，清空内容再插入
        				//ue.setHide();
        				//隐藏图文编辑区域
        				content.find("div.msgContent").addClass("hidden");
        				content.find("div.msgVideo").removeClass("hidden");
        			} else {
        				content.find("textarea[name='msgContent']").rules("add",{required:true});
        				content.find("textarea[name='msgContent']").valid();
        				//删除选择视频按钮绑定的click事件
        				content.find("#msgVideoChosser").find("input[type='button']").unbind("click");
        				content.find("input[name='msgVideoId']").rules("remove");
        				//如果是图文，则隐藏视频选择按钮，并将存储选择的视频ID的文本框清空
        				content.find("#msgVideoChosser").addClass("hidden");
        				content.find("input[name='msgVideoId']").val("0");
        				//将视频路径清除
        				content.find("#msgVideo").attr("src","");
        				//显示内容编辑器
        				//content.find("label[for='msgContent']").text("内容");
        				content.find("div.msgVideo").addClass("hidden");
        				content.find("div.msgContent").removeClass("hidden");
        				//ue.setShow();
        			}
        		});
	    		
	    		
        		
	    	}
    	);
    });
    
    
    
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {messageId:item.msgId};
        //调用删除方法，向服务器发送请求
        delDataTableItem(dataTable,tableOption,params,function(data){
        	if(data.status == 1){
            	//dataTable.ajax.reload();
            	dataTable.draw(true);
            }
            tcAlert(data.info, 6000);
        });
    });
    
    //详情
    tableOption.table.on("click",".detailRow",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        var jcDetail = detailData(tableOption,item,function(detailForm,item,content){
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
    		fillForm(detailForm,item);
    		//如果是图文，则显示图文内容
    		if(item.msgPattern == 1){
    			content.find("#msgContent").append(item["msgContent"]);
    			content.find(".msgContent").removeClass("hidden");
    		}
    		//如果是视频，则显示视频
    		if(item.msgPattern == 2){
    			content.find("#msgVideo").attr("src",basePath + item.msgVideoUrl);
    			content.find(".msgVideo").removeClass("hidden");
    		}
        })
    });
    
    //修改
    tableOption.table.on("click",".editRow",function() {
    	//要修改哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();       
        var jcUpdate = updateData(tableOption,item,
        	//以下三个回掉函数可自定义
    		function(updateForm,item,content){
	        	//初始化表单校验
	        	initFormValidator(tableOption.updateFormId);
	        	//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
	        	fillForm(updateForm,item);
	        	/***************初始化ueditor******************/
	        	//窗口打开后 先删除之前初始化的ueditor，再重新初始化，否则会渲染失败
	    		UE.delEditor("msgContent");
	    		//初始化ueditor控件
	    		var updateUe = UE.getEditor('msgContent');
	    		//如果是图文格式，则在UE编辑器加载完成之后初始化图文内容
	    		if(item.msgPattern == 1){
	    			content.find("input[name='msgVideoId']").val(0);
	    			content.find("div.msgVideo").addClass("hidden");
	    			content.find("div.msgContent").removeClass("hidden");
	    			updateUe.ready(function() {
		    		    //设置编辑器的内容
		    			var msgContent = item["msgContent"];
		    			updateUe.setContent(msgContent);
		    		});
	    		}
	    		//如果是视频格式，则回显视频
	    		if(item.msgPattern == 2){
	    			content.find("textarea[name='msgContent']").rules("remove");
	    			updateUe.ready(function() {
		    		    //设置编辑器的内容
		    			updateUe.setContent("");
		    		});
	    			//如果是视频，则显示选择视频的按钮
    				content.find("#msgVideoChosser").removeClass("hidden");
    				//视频选择按钮设置点击事件处理
    				content.find("#msgVideoChosser").find("input[type='button']").unbind("click");
    				content.find("#msgVideoChosser").find("input[type='button']").click(function(){
    					//打开搜索媒体资源窗口，选择视频
    					searchMedia(tableOption,content);
    				});
    				//设置存储所选视频ID的input为必填
    				content.find("input[name='msgVideoId']").rules("add",{required:true});
	    			if(item.msgVideoId > 0){
	    				content.find("input[name='msgVideoId']").val(item.msgVideoId);
	    				content.find("#msgVideo").attr("src",basePath + item.msgVideoUrl);
	    			} else {
	    				content.find("input[name='msgVideoId']").removeAttr("value");
	    			}
	    			
	    			content.find("div.msgContent").addClass("hidden");
    				content.find("div.msgVideo").removeClass("hidden");
    				content.find("#msgVideo").removeClass("hidden");
	    		}
	    		
	    		
        		//初始化校园下拉框
        		/*initBasicSelect2({
        			url: basePath + 'orgmgt/queryGarten',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		obj.id = obj.gartenId;
							obj.text = obj.gartenName;
							
							return obj;
			    		});
                    	content.find("select.msgGartenIdSelect2").select2({data:result});
                    	var option = $("<option selected=\"selected\" value=\"0\">请选择所属校园</option>");
	    				content.find("select.msgGartenIdSelect2").prepend(option).val(item.msgGartenId).trigger("change");
                    }
        		});
        		content.find("select.msgGartenIdSelect2").change(function(){
        			$(this).valid();
        		});
        		//初始化bootstrap-switch开关控件
	    		content.find("input[name='isGroupMsg']").bootstrapSwitch({
	    			onText:"是",  
	    	        offText:"否",
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	            	content.find("select.msgGartenIdSelect2").val(0).trigger("change");
	    	            	content.find("select.msgGartenIdSelect2").prop("disabled", true);
	    	            	content.find("select.msgGartenIdSelect2").rules("remove");
	    	            	content.find("select.msgGartenIdSelect2").valid();
	    	            }else{  
	    	                //$(this).val("0");  
	    	            	content.find("select.msgGartenIdSelect2").prop("disabled", false);
	    	            	content.find("select.msgGartenIdSelect2").rules("add",{selectRequired:true});
	    	            	content.find("select.msgGartenIdSelect2").valid();
	    	            }  
	    	        }
	    		});
	    		if(item.msgGartenId <= 0){
	    			content.find("input[name='isGroupMsg']").bootstrapSwitch('state', true);
	    		}*/
	    		
	    		//消息格式如果是视频，则显示选择视频
	    		content.find("select.msgPatternSelect2").change(function(){
	    			var pat = $(this).val();
        			if(pat == 2){
        				//去掉图文内容的必填校验
        				content.find("textarea[name='msgContent']").rules("remove");
        				//清空内容编辑器，并且隐藏内容编辑器
        				updateUe.setContent("", false);//若传入true，不清空原来的内容，在最后插入内容，否则，清空内容再插入
        				//如果是视频，则显示选择视频的按钮
        				content.find("#msgVideoChosser").removeClass("hidden");
        				//视频选择按钮设置点击事件处理
        				content.find("#msgVideoChosser").find("input[type='button']").unbind("click");
        				content.find("#msgVideoChosser").find("input[type='button']").click(function(){
        					//打开搜索媒体资源窗口，选择视频
        					searchMedia(tableOption,content);
        				});
        				//设置存储所选视频ID的input为必填
        				content.find("input[name='msgVideoId']").rules("add",{required:true});
        				content.find("input[name='msgVideoId']").removeAttr("value");
        				content.find("input[name='msgVideoId']").valid();
        				//ue.setHide();
        				//隐藏图文编辑区域
        				content.find("div.msgContent").addClass("hidden");
        				content.find("div.msgVideo").removeClass("hidden");
        				
        			} else {
        				content.find("textarea[name='msgContent']").rules("add",{required:true});
        				content.find("textarea[name='msgContent']").valid();
        				//删除选择视频按钮绑定的click事件
        				content.find("#msgVideoChosser").find("input[type='button']").unbind("click");
        				content.find("input[name='msgVideoId']").rules("remove");
        				//如果是图文，则隐藏视频选择按钮，并将存储选择的视频ID的文本框清空
        				content.find("#msgVideoChosser").addClass("hidden");
        				content.find("input[name='msgVideoId']").val("0");
        				//将视频路径清除
        				content.find("#msgVideo").attr("src","");
        				//显示内容编辑器
        				//content.find("label[for='msgContent']").text("内容");
        				
        				content.find("div.msgVideo").addClass("hidden");
        				content.find("div.msgContent").removeClass("hidden");
        				//ue.setShow();
        			}
        		});
	    		
	    		//数据回显之后，触发消息格式下拉框的change事件，从而显示或者隐藏视频/内容
	    		//content.find("select.msgPatternSelect2").trigger("change");
        		
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
        	}
        );
    });
});