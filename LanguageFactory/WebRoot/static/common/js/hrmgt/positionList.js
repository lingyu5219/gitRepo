$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "hrmgt/queryPositionList",
		//ajax删除数据的http请求url
		delUrl : basePath + "hrmgt/delPosition",	
		//增加页面
		addPageUrl : basePath + 'forward?page=hrmgt/positionAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "hrmgt/addPosition",
		//修改页面
		updatePageUrl : basePath + 'forward?page=hrmgt/positionModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "hrmgt/updatePosition",
		//查看页面
		detailPageUrl : basePath + 'forward?page=hrmgt/positionDetail',
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
	        {"data":'positionId',visible:false},
	        {"data":'positionName'},
	        {"data":'remark',className:'hidden-xs'},
	        {"data":'userName',className:'hidden-xs'},
	        {"data":'createTime',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	                return data.substr(0,19) 
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 6
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	//初始化表单校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				positionName: {
					required: true
				}
			}
		});
		
	}
    //查询按钮
    $("#btn-query").on("click", function () {
		dataTable.draw(true);
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
	    	//窗口打开后，初始化操作，可自定义初始化内容
	    	function(content){
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
        var params = {positionId:item.positionId};
        //调用删除方法，向服务器发送请求
        delDataTableItem(dataTable,tableOption,params,function(data){
        	if(data.status == 1){
            	//dataTable.ajax.reload();
        		//false重新查询后保持在当前页
            	dataTable.draw(false);
            }
            tcAlert(data.info, 6000);
        });
    });
    
    //详情
    tableOption.table.on("click",".detailRow",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        item.titleSuffix = "职位" + item.positionId;
        var jcDetail = detailData(tableOption,item,function(detailForm,item){
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        })
    });
    
    //修改
    tableOption.table.on("click",".editRow",function() {
    	//要修改哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();       
        var jcUpdate = updateData(tableOption,item,
        	//以下三个回掉函数可自定义
    		function(updateForm,item){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
        		fillForm(updateForm,item);
        		//初始化表单校验
        		initFormValidator(tableOption.updateFormId);
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
                	//刷新结果列表,false查询后保持在当前页
            		dataTable.draw(false);
                }
	    		//提示信息
            	tcConfirm(data.info,"是否返回？",function(){
            		jcUpdate.close();
            	});
        	}
        );
    });
});