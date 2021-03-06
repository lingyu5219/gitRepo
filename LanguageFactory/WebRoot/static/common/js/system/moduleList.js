$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "system/queryModuleList",
		//ajax删除数据的http请求url
		delUrl : basePath + "system/delModule",
		//增加页面
		addPageUrl : basePath + 'forward?page=system/moduleAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "system/addModule",
		//修改页面
		updatePageUrl : basePath + 'forward?page=system/moduleModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "system/updateModule",
		//查看页面
		detailPageUrl : basePath + 'forward?page=system/moduleDetail',
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
	        {"data":'moduleName'},
	        {"data":'moduleIcon',
	        	"render":function(data,type,full,callback) {
	        		return "<i class=\"fa " + data + " icon-default icon-small text-primary\"></i>";
	            }
	        },
	        {"data":'remark',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		return '<span title="'+data +'">'+ (data.length > 30 ? data.substr(0,30) + "......" : data) +'</span>'; 
	            }
	        },
	        {"data":'userName',className:'hidden-xs hidden-sm'},
	        {"data":'createTime',className:'hidden-xs hidden-sm',
	        	"render":function(data,type,full,callback) {
	                return data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 6,
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
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	//初始化表单校验函数，当打开增加/修改窗口时，调用该方法，初始化校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				moduleName: {
					required: true
				}
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
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    		content.find("#moduleIcon i").click(function(){
	    			if($(this).hasClass("text-primary")){
	    				$(this).removeClass("text-primary");
	    				content.find("input[name='moduleIcon']").val("");
	    			} else {
	    				content.find("#moduleIcon i").removeClass("text-primary");
	    				$(this).addClass("text-primary");
	    				content.find("input[name='moduleIcon']").val($(this).attr("data-class"));
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
        var params = {moduleId:item.moduleId};
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
	    		//回显图标
	    		if(item.moduleIcon != null && item.moduleIcon != ""){
	    			content.find("#moduleIcon i." + item.moduleIcon).addClass("text-primary");
	    		}
	    		content.find("#moduleIcon i").click(function(){
	    			if($(this).hasClass("text-primary")){
	    				$(this).removeClass("text-primary");
	    				content.find("input[name='moduleIcon']").val("");
	    			} else {
	    				content.find("#moduleIcon i").removeClass("text-primary");
	    				$(this).addClass("text-primary");
	    				content.find("input[name='moduleIcon']").val($(this).attr("data-class"));
	    			}
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
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        	//回显图标
        	if(item.moduleIcon != null && item.moduleIcon != ""){
        		content.find("#moduleIcon").html("<i class=\"fa " + item.moduleIcon + " icon-large text-primary\"></i>");
        	}
        })
    });
    
});


