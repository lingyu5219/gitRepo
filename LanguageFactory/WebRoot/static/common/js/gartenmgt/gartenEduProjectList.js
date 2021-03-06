$(function() {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "gartenmgt/querySelectedEduProject",
		//ajax删除数据的http请求EduProjectType
		delUrl : basePath + "gartenmgt/deleteSeletedEduroject",	
		//增加页面
		addPageUrl : basePath + 'forward?page=gartenmgt/gartenEduProjectAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "gartenmgt/addGartenEduProjectList",
		//修改页面
//		updatePageUrl : basePath + 'forward?page=gartenmgt/eduProjectModify',
		//ajax修改数据的http请求url
//		updateUrl : basePath + "gartenmgt/updateEduProject",
		//查看页面
		detailPageUrl : basePath + 'forward?page=gartenmgt/gartenEduProjectDetail',
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
	        {"data":'eduProjecteId',visible:false},
	        {"data":'eduProjectName'},
	        {"data":'description',className:'hidden-xs hidden-sm',
	        	"render":function(data,type,full,callback) {
	        		var noHtml = data.replace(/<\/?[^>]*>/g, ''); //去除HTML tag
	        		return '<span title="'+noHtml +'">'+ (noHtml.length > 20 ? noHtml.substr(0,20) + "......" : noHtml) +'</span>';
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
		operBtnColumn : 6,
		operBtns:[
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o"}
		]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);

	//初始化表单校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				gartenId: {
					selectRequired: true
				},
				eduProjectId: {
					required: true
				},
			}
		});
		
	}
	//查询按钮
    $("#btn-query").on("click", function () {
		dataTable.draw(true);//查询后不需要保持分页状态，回首页
    });
  //增加
    $("#btn-add").on("click", function () {
    	var idArr = new Array();
    	var jcAdd = addData(tableOption,
    		//以下2个方法可以自定义
    		function(){
	    		if(idArr.length !== 0) {
	    			var param = new Object();
	    			param.eduProjectList = idArr.toString()
	    			return param
	    		} else {
	    			return false
	    		}
	    	},
	    	//增加成功后的回调函数
	    	function(data){
	    		if(data.status == 1){
                	//刷新结果列表
            		dataTable.draw(true);
                }
	    		//提示信息
            	tcAlert(data.info,function(){
            		jcAdd.close();
            	});
	    	},
	    	//窗口打开后，初始化操作，可自定义初始化内容
	    	function(content){
	    		//init table data
        		var tbOption = {
    					//ajax查询数据的http请求url
    					queryUrl : basePath + "gartenmgt/queryGartenEduProjectNoSelected",
    					//预览页面
//    					previewPageUrl : basePath + 'forward?page=groupmgt/mediaPreview',
    					//Table对象
    					table : content.find('#dataListAddTable'),
    					//查询表单Form对象
    					form : content.find("#queryAddForm"),
    					//预览功能的表单formID
//    					previewFormId : "previewForm",
    					
    					//表格列
    					columns : [//对应thead里面的序列
    				        {"data":null,width:"10px"},
    				        {"data":'eduProjectId',visible:false},
//    				        {"data":'gartenName'},
    				        {"data":'eduProjectName'},
    				        {"data":'description',className:'hidden-xs hidden-sm',
    				        	"render":function(data,type,full,callback) {
    				        		var noHtml = data.replace(/<\/?[^>]*>/g, ''); //去除HTML tag
    				        		return '<span title="'+noHtml +'">'+ (noHtml.length > 20 ? noHtml.substr(0,20) + "......" : noHtml) +'</span>';
    				            }
    				        },
    				        /*{"data":'createByName',className:'hidden-xs hidden-sm'},
    				        {"data":'createTime',className:'hidden-xs hidden-sm',
    				        	"render":function(data,type,full,callback) {
    				        		return full.createTime = data.substr(0,19);
    				            }
    				        },*/
    				        {"data":null}
    				    ],
    					//操作按钮所在列
    					operBtnColumn : 4,
    					operBtns:[
    						{title:'选中',btnClass: 'btn selectRow',iconClass:'fa-square-o'}
    					]
    				};
    				//初始化表格控件
    				var dt = initDataTable(tbOption);
    				//增加
    				tbOption.table.on('click','.selectRow',function(){
    					var item = dt.row($(this).closest('tr')).data()
    					$(this).toggleClass('selected')
    					$(this).find('i').toggleClass('fa-square-o')
    					$(this).find('i').toggleClass('fa-check-square-o')
    					if($(this).hasClass('selected')) {
    						idArr.push(item.eduProjectId)
    					} else {
    						//删除用户取消勾选的数据
    						var tempId = item.eduProjectId
    						var index = idArr.indexOf(tempId)
    						if(index != -1)
    							idArr.splice(index, 1)
    					}
    				})
	    	}
    	);
    });
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {eduProjectId:item.eduProjectId};
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
        	content.find("#description").prepend(item["description"]);
        })
    });
    
    //修改
    tableOption.table.on("click",".editRow",function() {
    	//要修改哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();  
        var jcUpdate = updateData(tableOption,item,
        	//以下三个回掉函数可自定义
    		function(updateForm,item,content){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
        		fillForm(updateForm,item);
        		
        		//初始化校园下拉框
        		initBasicSelect2({
        			url: basePath + 'orgmgt/queryGarten',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		obj.id = obj.gartenId;
							obj.text = obj.gartenName;
							
							return obj;
			    		});
                    	content.find("select.staffGartenIdSelect2").select2({data:result});
                    	content.find("select.classTypeIdSelect2").val(item.classTypeId).trigger('change');
                    }
        		});
        		content.find("select.staffGartenIdSelect2").change(function(){
        			$(this).valid();
        		});
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
})