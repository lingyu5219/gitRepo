$(function() {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "groupmgt/queryClassList",
		//ajax删除数据的http请求Class
		delUrl : basePath + "groupmgt/delClass",	
		//增加页面
		addPageUrl : basePath + 'forward?page=groupmgt/classAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "groupmgt/addClass",
		//修改页面
		updatePageUrl : basePath + 'forward?page=groupmgt/classModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "groupmgt/updateClass",
		//查看页面
		detailPageUrl : basePath + 'forward?page=groupmgt/classDetail',
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
	        {"data":'classId',visible:false},
	        {"data":'gartenName'},
	        {"data":'classTypeName',className:'hidden-xs'},
	        {"data":'className'},
	        {"data":'year',className:'hidden-xs hidden-sm hidden-md'},
	        {"data":'capacity',className:'hidden-xs hidden-sm hidden-md'},
	        {"data":'currentQuantity',className:'hidden-xs hidden-sm hidden-md'},
	        {"data":'description',className:'hidden-xs hidden-sm hidden-md',
	        	"render":function(data,type,full,callback) {
	        		return '<span title="'+data +'">'+ (data.length > 20 ? data.substr(0,20) + "......" : data) +'</span>';
	            }
	        },
	        {"data":'createByName',className:'hidden-xs hidden-sm hidden-md'},
	        {"data":'createTime',className:'hidden-xs hidden-sm',
	        	"render":function(data,type,full,callback) {
	        		return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 11
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	//初始化出生日期的日期控件
	initYear($("input.datepicker"));
//	初始化班级下拉
	initBasicSelect2({
		url: basePath + 'groupmgt/classTypeSelectList',
		callback: function(data){
        	var result = $.map(data, function (obj) {
	    		obj.id = obj.classTypeId;
				obj.text = obj.classTypeName;
				return obj;
    		});
        	$("select.classTypeIdSelect2").select2({data:result});
        }
	});
//	初始化所属幼儿园类型下拉
	initBasicSelect2({
		url: basePath + 'orgmgt/queryGarten',
		callback: function(data){
			var result = $.map(data, function (obj) {
	    		obj.id = obj.gartenId;
				obj.text = obj.gartenName;
				return obj;
    		});
        	$("select.gartenIdSelect2").select2({data:result});
        }
	});
	
	//初始化表单校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				className: {
					required: true
				},
				year: {
					required: true
				},
				classTypeId: {
					selectRequired: true
				},
				/*classTypeId: {
					selectRequired: true
				},*/
				gartenId: {
					selectRequired: true
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
	    		//初始化出生日期的日期控件
	    		initYear(content.find("input.datepicker"));
	    		//初始化班级类型下拉框
        		initBasicSelect2({
        			url: basePath + 'groupmgt/classTypeSelectList',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		obj.id = obj.classTypeId;
							obj.text = obj.classTypeName;
							return obj;
			    		});
                    	content.find("select.classTypeIdSelect2").select2({data:result});
                    	var option = $("<option selected=\"selected\" value=\"0\">请选择班级类型</option>");
	    				content.find("select.classTypeIdSelect2").prepend(option);
                    }
        		});
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
                    	var option = $("<option selected=\"selected\" value=\"0\">请选择所属校园</option>");
	    				content.find("select.staffGartenIdSelect2").prepend(option);
                    }
        		});
        		content.find("select.staffGartenIdSelect2").change(function(){
        			$(this).valid();
        		});
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
        var params = {classId:item.classId};
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
    		function(updateForm,item,content){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
        		fillForm(updateForm,item);
        		//初始化日期控件
        		initYear(content.find("input.datepicker"));
        		content.find("input.datepicker").val(item.year).trigger('change');
        		//初始化班级类型下拉框
        		initBasicSelect2({
        			url: basePath + 'groupmgt/classTypeSelectList',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		obj.id = obj.classTypeId;
							obj.text = obj.classTypeName;
							return obj;
			    		});
                    	content.find("select.classTypeIdSelect2").select2({data:result});
                    	content.find("select.classTypeIdSelect2").val(item.classTypeId).trigger('change');
                    }
        		});
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
                    	content.find("select.staffGartenIdSelect2").val(item.gartenId).trigger('change');
                    }
        		});
        		content.find("select.staffGartenIdSelect2").change(function(){
        			$(this).valid();
        		});
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