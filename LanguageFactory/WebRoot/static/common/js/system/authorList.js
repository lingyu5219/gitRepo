$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "system/queryAuthorList",
		//ajax删除数据的http请求url
		//delUrl : basePath + "system/delAuthor",
		//增加页面
		//addPageUrl : basePath + 'forward?page=system/authorAdd',
		//ajax增加数据的http请求url
		//addUrl : basePath + "system/addAuthor",
		//修改页面
		updatePageUrl : basePath + 'forward?page=system/authorModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "system/updateAuthor",
		//查看页面
		detailPageUrl : basePath + 'forward?page=system/authorDetail',
		//Table对象
		table : $('#dataListTable'),
		//查询表单Form对象
		form : $("#queryForm"),
		//增加功能的表单formID
		//addFormId : "addForm",
		//修改功能的表单formID
		updateFormId : "updateForm",
		//查看功能的表单formID
		detailFormId : "detailForm",
		//表格列
		columns : [//对应thead里面的序列
	        {"data":null,width:"10px"},
	        {"data":'roleName'},
	        {"data":'menuNames',width:"360px",className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		var menuNames = data.split(",");
	        		var menuNameHtml = "<p class=\"text-justify\" style=\"line-height:2em;\">";
	        		for(var i in menuNames){
	        			if(i > 5){
	        				menuNameHtml += " <span class=\"label label-warning\">...... more</span>";
	        				break;
	        			}
	        			
	        			menuNameHtml += " <span class=\"label label-primary\">" + menuNames[i] + "</span>";
	        		}
	        		menuNameHtml += "</p>";
	        		return menuNameHtml;
	            }
	        },
	        {"data":'createByName',visible:false},
	        {"data":'createTime',visible:false,
	        	"render":function(data,type,full,callback) {
	        		return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 5,
		operBtns:[
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"配置菜单",btnClass:"btn-primary editRow",iconClass:"fa-edit"}
		]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
    
	//初始化表单校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			//debug:true,//只验证不提交
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				menuIds: {
					selectMultipleRequired: true
				}
			}
		});
		
	}
	
	/***************初始化查询条件角色下拉框控件******************/
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
        	$("select.roleIdSelect2").select2({data:result});
        }
	});
	
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw(true);//查询后不需要保持分页状态，回首页
    });
    
    //修改
    tableOption.table.on("click",".editRow",function() {
    	//要修改哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        tableOption.columnClass = 'col-md-12';
        var jcUpdate = updateData(tableOption,item,
        	//以下三个回掉函数可自定义
        	//此方法在窗口加载内容完成后调用，在此方法中，可执行一些初始化的操作
    		function(updateForm,item,content){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
        		fillForm(updateForm,item);
        		/***************初始化菜单下拉框控件******************/
	    		initBasicSelect2({
	    			url: basePath + 'system/queryMenuTree',
	    			callback: function(data){
	    	        	var result = $.map(data, function (module) {
	    		    		var optionArr = $.map(module.subMenuList, function (menu) {
	    		    			var option = {};
	    		    			option.id = menu.menuId;
	    		    			option.text = menu.menuName;
	    		    			return option;
	    		    		});
	    		    		var optgroup = {};
	    		    		optgroup.text = module.moduleName;
	    		    		optgroup.children = optionArr;
	    		    		return optgroup;
	    	    		});
	    	        	$("select.menuIdsSelect2").select2({
	    	        		placeholder: "请选择菜单，可多选",
	    	        		data:result
	    	        	});
	    	        	$("select.menuIdsSelect2").val(item.menuIds.split(",")).trigger('change');
	    	        }
	    		});
	    		//初始化表单校验
        		initFormValidator(tableOption.updateFormId);
        	},
        	function(){
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
        	/***************初始化菜单下拉框控件******************/
        	var optArr = new Array();
        	var optValArr = item.menuIds.split(",");
        	var optTextArr = item.menuNames.split(",");
        	for(var i = 0; i < optValArr.length; i++){
        		var option = {};
        		option.id = optValArr[i];
        		option.text = optTextArr[i];
        		optArr.push(option);
        	}
        	$("select.menuIdsSelect2").select2({
        		placeholder: "请选择菜单，可多选",
        		data:optArr
        	});
        	$("select.menuIdsSelect2").val(optValArr).trigger('change');
        })
    });
});
