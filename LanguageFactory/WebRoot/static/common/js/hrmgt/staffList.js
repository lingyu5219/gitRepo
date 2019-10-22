$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "hrmgt/queryStaffList",
		//ajax删除数据的http请求url
		delUrl : basePath + "hrmgt/delStaff",	
		//增加页面
		addPageUrl : basePath + 'forward?page=hrmgt/staffAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "hrmgt/addStaff",
		//修改页面
		updatePageUrl : basePath + 'forward?page=hrmgt/staffModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "hrmgt/updateStaff",
		//查看页面
		detailPageUrl : basePath + 'forward?page=hrmgt/staffDetail',
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
	        {"data":'staffId',visible:false},
	        {"data":'staffName'},
	        {"data":'staffGartenId',
	        	"render":function(data,type,full,callback) {
	        		if(data > 0){
	        			//归属校园
	        			return "<span class=\"label label-warning\">" + full.staffGartenName + "</span>"
	        		} else {
	        			//归属集团
	        			return "<span class=\"label label-primary\">集团</span>"
	        		}
	            }
	        },
	        {"data":'staffBirthday',visible:false},
	        {"data":'staffPhone',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		return data.substr(0,3) + "****" + data.substr(7);
	            }
	        },
	        {"data":'staffEmail',visible:false},
	        {"data":'positionName'},
	        {"data":'createByName',className:'hidden-xs hidden-sm'},
	        {"data":'createTime',className:'hidden-xs hidden-sm',
	        	"render":function(data,type,full,callback) {
	        		return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 10,
		operBtns:[
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit",
				condition:function(data, type, row){
					//当角色是超级管理员时，不显示修改按钮
					/*if(data.roleId == 1){
						return false;
					}
					return true;*/
					return true;
				}
			},
			{title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o",
				condition:function(data, type, row){
					//当角色是超级管理员时，不显示删除按钮
					if(data.staffId == 1){
						return false;
					}
					return true;
				}
			}
		]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	//初始化表单校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				staffName: {
					required: true
				},
				staffBirthday: {
					required: true
				},
				staffPhone: {
					required: true,
					isPhone: true,
					isUnique: {
						tableName:"tb_hrmgt_staff", 
						queryFieldList:function(value, element){
							return [{fieldName:"staffPhone",operator:"=", fieldValue:"'" + value +"'"}];
						}
					}
				},
				staffEmail: {
					isEmail: true
				},
				positionId: {
					selectRequired: true
				},
				staffGartenId: {
					selectRequired: true
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
	    		initDate(content.find("input.datepicker"));
	    		/***************初始化职位下拉框控件******************/
        		initBasicSelect2({
        			url: basePath + 'hrmgt/queryPosition',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.positionId;
							obj.text = obj.positionName;
							
							return obj;
			    		});
                    	content.find("select.positionIdSelect2").select2({data:result});
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
        		//初始化bootstrap-switch开关控件
	    		content.find("input[name='isGroupStaff']").bootstrapSwitch({
	    			onText:"是",  
	    	        offText:"否",
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	            	content.find("select.staffGartenIdSelect2").val(0).trigger("change");
	    	            	content.find("select.staffGartenIdSelect2").prop("disabled", true);
	    	            	$("select.staffGartenIdSelect2").rules("remove");
	    	            	$("select.staffGartenIdSelect2").valid();
	    	            }else{  
	    	                //$(this).val("0");  
	    	            	content.find("select.staffGartenIdSelect2").prop("disabled", false);
	    	            	$("select.staffGartenIdSelect2").rules("add",{selectRequired:true});
	    	            	$("select.staffGartenIdSelect2").valid();
	    	            }  
	    	        }
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
        var params = {staffId:item.staffId};
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
    		if(item.staffGartenId <= 0){
    			//初始化bootstrap-switch开关控件
    			content.find("input[name='isGroupStaff']").bootstrapSwitch({
    				onText:"是",  
    				offText:"否"
    			});
    			content.find("input[name='isGroupStaff']").bootstrapSwitch('state', true);
    			content.find("input[name='staffGartenName']").hide();
    			content.find("input[name='isGroupStaff']").bootstrapSwitch('disabled', true);
    		} else {
    			content.find("input[name='staffGartenName']").parent().prev().prev().text("所属校园");
    			content.find("input[name='staffGartenName']").parent().prev().hide();
    			content.find("input[name='staffGartenName']").parent().removeClass("col-md-6").addClass("col-md-9");
    		}
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
        		//初始化出生日期控件
        		initDate(content.find("input.datepicker"));
        		/***************初始化职位下拉框控件******************/
        		initBasicSelect2({
        			url: basePath + 'hrmgt/queryPosition',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.positionId;
							obj.text = obj.positionName;
							
							return obj;
			    		});
                    	content.find("select.positionIdSelect2").select2({data:result});
                		content.find("select.positionIdSelect2").val(item.positionId).trigger('change');
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
	    				content.find("select.staffGartenIdSelect2").prepend(option).val(item.staffGartenId).trigger("change");
                    }
        		});
        		content.find("select.staffGartenIdSelect2").change(function(){
        			$(this).valid();
        		});
        		//初始化bootstrap-switch开关控件
	    		content.find("input[name='isGroupStaff']").bootstrapSwitch({
	    			onText:"是",  
	    	        offText:"否",
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	            	content.find("select.staffGartenIdSelect2").val(0).trigger("change");
	    	            	content.find("select.staffGartenIdSelect2").prop("disabled", true);
	    	            	$("select.staffGartenIdSelect2").rules("remove");
	    	            	$("select.staffGartenIdSelect2").valid();
	    	            }else{  
	    	                //$(this).val("0");  
	    	            	content.find("select.staffGartenIdSelect2").prop("disabled", false);
	    	            	$("select.staffGartenIdSelect2").rules("add",{selectRequired:true});
	    	            	$("select.staffGartenIdSelect2").valid();
	    	            }  
	    	        }
	    		});
	    		if(item.staffGartenId <= 0){
	    			content.find("input[name='isGroupStaff']").bootstrapSwitch('state', true);
	    		}
        		//初始化表单校验
        		initFormValidator(tableOption.updateFormId);
        		//修改时，删除电话号码字段isUnique（验证是否有重复数据）校验规则，添加新的isUnique规则（验证是否有重复数据，但是需要排除数据自身，因为是修改，与其自身数据相同的，不算重复）
        		content.find("input[name='staffPhone']").rules("remove","isUnique");
        		content.find("input[name='staffPhone']").rules("add",{
        			isUnique: {
						tableName:"tb_hrmgt_staff", 
						queryFieldList:function(value, element){
							return [
								{fieldName:"staffPhone",operator:"=", fieldValue:"'" + value +"'"},
								{fieldName:"staffId",operator:"!=", fieldValue:"'" + item.staffId +"'"}
							];
						}
					}
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
});