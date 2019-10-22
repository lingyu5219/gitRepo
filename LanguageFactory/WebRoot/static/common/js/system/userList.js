$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "system/queryUserList",
		//ajax删除数据的http请求url
		delUrl : basePath + "system/delUser",
		//增加页面
		addPageUrl : basePath + 'forward?page=system/userAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "system/addUser",
		//修改页面
		updatePageUrl : basePath + 'forward?page=system/userModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "system/updateUser",
		//查看页面
		detailPageUrl : basePath + 'forward?page=system/userDetail',
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
		columns : [
            {"data":null},
            {"data":'userName'},
	        {"data":'roleName'},
	        {"data":'staffName'},
	        {"data":'createByName',className:'hidden-xs'},
	        {"data":'createTime',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	                return data.substr(0,19) 
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 6,
		operBtns:[
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit"},
			{title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o",
				condition:function(data, type, row){
					//当用户是系统默认超级管理员admin时，不显示删除按钮
					if(data.userId == 1){
						return false;
					}
					return true;
				}
			}
		]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	//初始化表单校验函数，当打开增加/修改窗口时，调用该方法，初始化校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				userName: {
					required: true,
					isCharOrDigit: true,
					userNameExist: true
				},
				userPassword: {
					required: true,
					isPassword: true
				},
				roleId: {
					selectRequired: true
				},
				staffId:{
					selectRequired: true,
					staffIsRelatedByUser: true
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
	    		//query_all_role(content);
	    		/***************初始化角色下拉框******************/
        		initBasicSelect2({
        			url: basePath + 'system/queryRole',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.roleId;
							obj.text = obj.roleName;
							
							return obj;
			    		});
                    	content.find("select.roleIdSelect2").select2({data:result});
                    }
        		});
        		//初始化关联员工下拉框，该下拉框只有输入关键词查询才能显示选项
        		initSelect2({
	    			selected:true,//在做修改操作时，需要回显数据，所以有默认选中项
	    			selectedData:{id: 0, text: "请选择员工"},//默认选中项的value和text值
	    			selectObj: content.find("select.staffIdSelect2"),//下拉框对象
	    			placeholder: "",
	    			url: basePath + 'hrmgt/queryStaffByPhone',
	    			beforeRequest: function (params) {
	    				//发送查询请求之前，先将输入的关键词，赋值给对应的查询条件，次示例中是将关键词作为员工电话进行查询
						var query = {staffPhone: params.term};
						return query;
    			    },
    			    afterResponse: function (data) {
    			    	//查询结果返回后，在将数据插入到select下拉框之前执行此函数，此处可以对返回的数据做一些处理后，再显示到select中
    			    	var result = $.map(data, function (obj) {
    			    		obj.id = obj.staffId;
							obj.text = obj.staffName;
							
							return obj;
			    		});
    			        return {results: result};
    			    }
	    		});
        		content.find("select.staffIdSelect2").change(function(){
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
        var params = {userId:item.userId};
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
        		/***************初始化角色下拉框******************/
        		initBasicSelect2({
        			url: basePath + 'system/queryRole',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.roleId;
							obj.text = obj.roleName;
							
							return obj;
			    		});
                    	content.find("select.roleIdSelect2").select2({data:result});
                		content.find("select.roleIdSelect2").val(item.roleId).trigger('change');
                    }
        		});
        		//初始化关联员工下拉框，该下拉框只有输入关键词查询才能显示选项
        		var selectedId = 0;
        		var selectedText = "请选择员工";
        		if(item.staffId > 0 && item.staffName != null && item.staffName.length > 0){
        			selectedId = item.staffId;
        			selectedText = item.staffName;
        		}
        		initSelect2({
	    			selected:true,//在做修改操作时，需要回显数据，所以有默认选中项
	    			selectedData:{id: selectedId, text: selectedText},//默认选中项的value和text值
	    			selectObj: content.find("select.staffIdSelect2"),//下拉框对象
	    			placeholder: "",
	    			url: basePath + 'hrmgt/queryStaffByPhone',
	    			beforeRequest: function (params) {
	    				//发送查询请求之前，先将输入的关键词，赋值给对应的查询条件，次示例中是将关键词作为userName进行查询
						var query = {staffPhone: params.term};
						return query;
    			    },
    			    afterResponse: function (data) {
    			    	//查询结果返回后，在将数据插入到select下拉框之前执行此函数，此处可以对返回的数据做一些处理后，再显示到select中
    			    	var result = $.map(data, function (obj) {
    			    		obj.id = obj.staffId;
							obj.text = obj.staffName;
							
							return obj;
			    		});
    			        return {results: result};
    			    }
	    		});
        		content.find("select.staffIdSelect2").change(function(){
        			$(this).valid();
        		});
        		
	    		//更新时关联员工的校验，使用staffIsRelatedByUserNotSelf方法，检查员工是否被关联时，排除自身，应该检查员工是否被其他用户关联
	    		content.find("select.staffIdSelect2").rules("remove");
	    		content.find("select.staffIdSelect2").rules("add",{selectRequired: true,staffIsRelatedByUserNotSelf:{userId:item.userId}});
	    		content.find("input[name='userName']").rules("remove");
	    		content.find("input[name='userName']").rules("add",{
	    			required: true,
					isCharOrDigit: true,
					userNameExistNotSelf:{userId:item.userId}
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
    
    //查看
    tableOption.table.on("click",".detailRow",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        var jcDetail = detailData(tableOption,item,function(detailForm,item){
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        })
    });
    
});

