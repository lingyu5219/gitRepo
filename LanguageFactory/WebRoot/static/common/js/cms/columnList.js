$(function () {
	//收缩侧边菜单栏
	//$("body").removeClass("fixed").addClass("sidebar-collapse");
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "cms/queryColumnList",
		//ajax删除数据的http请求url
		delUrl : basePath + "cms/delColumn",
		//增加页面
		addPageUrl : basePath + 'forward?page=cms/columnAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "cms/addColumn",
		//修改页面
		updatePageUrl : basePath + 'forward?page=cms/columnModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "cms/updateColumn",
		//查看页面
		detailPageUrl : basePath + 'forward?page=cms/columnDetail',
		//排序上移
		upSortUrl : basePath + "cms/upSortColumn",
		//排序下移
		downSortUrl : basePath + "cms/downSortColumn",
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
			/*{"data":"childColumnList",className:"child-collapse",
				"render":function(data,type,full,callback){
					if(data !=null && data.length > 0){
						alert(data.length);
					}
					return "<i class=\"fa fa-list\"></i>";
				}
			},*/
	        {"data":null,width:"10px"},
	        {"data":'columnId',visible:false},
	        {"data":'columnName',
	        	"render":function(data,type,full,callback) {
	        		var rs = data;
	        		if(full.columnChildNum > 0){
	        			rs = "<i class=\"fa fa-plus text-warning\"></i> " + data;
	        		}
	        		return rs;
	        	}
	        },
	        {"data":'columnUrl',className:'hidden-xs'},
	        {"data":'columnSort',
	        	"render":function(data,type,full,callback) {
	        		var btnGroupStart = " <div class=\"btn-group\">";
	        		var sortUpBtn = "";
	        		var sortDownBtn = "";
	        		if(data > full.columnSortMin){
	        			sortUpBtn = "<button type=\"button\" data-value=\"" + data + "\" class=\"btn btn-success btn-sm sortUp\"><i class=\"fa fa-arrow-up\"></i></button>";
	        		}
	        		if(data < full.columnSortMax){
	        			sortDownBtn = "<button type=\"button\" data-value=\"" + data + "\" class=\"btn btn-info btn-sm sortDown\"><i class=\"fa fa-arrow-down\"></i></button>";
	        		}
        			var btnGroupEnd = "</div>";
        			return btnGroupStart + sortUpBtn + sortDownBtn + btnGroupEnd;
	            }
	        },
	        {"data":'columnEnable',
	        	"render":function(data,type,full,callback) {
	                return data == 1 ? 
	                		"<span class=\"label label-primary\"><i class=\"fa fa-check-square\"></i> 是</span>" 
	                		: "<span class=\"label label-default\"><i class=\"fa fa-square-o\"></i> 否</span>";
	                
	            }
	        },
	        {"data":'createByName',className:'hidden-xs'},
	        {"data":'createTime',visible:false,className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	                return data.substr(0,19) 
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 8,
		/*
		 * 如果操作按钮有修改、删除、查看这三个的，可不用此配置，默认会加入修改、删除、查看三个按钮
		 * 如果操作按钮不一定都是修改、删除、查看的，操作按钮可自定义，重点要指定按钮的类名，后面每个按钮通过类名绑定事件
		*/
		operBtns:[
			//{title:"增加子栏目",btnClass:"btn-primary addChildRow",iconClass:"fa-plus"},
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit"},
			{title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o"}
		]
		//在每次table被draw完后回调函数
		/*fnDrawCallback:function(api){
			//获取到本页开始的条数
			var startIndex = api.context[0]._iDisplayStart;
			//如果没有设置那一列显示序号，默认第一列显示序号
			api.column(1).nodes().each(function(cell, i) {
				cell.innerHTML = startIndex + i + 1;
			});
		}*/
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	
	//栏目数据转换成bootstrap-treeview控件的数据格式
	function column2Tree(data){
		var treeArray = new Array();
		for(var i in data){
			var node= {
				id : data[i].columnId,
				text : data[i].columnName,
				icon : "text-success fa fa-list",
				selectedIcon : "text-default fa fa-list",
				state : {
				    expanded:false
				}
			};
			
			var childNodes = column2Tree(data[i].childColumnList);
			if(childNodes.length > 0){
				node.nodes = childNodes;
			}
			treeArray[i] = node;
		}
		return treeArray;
	}
	//初始化栏目树下拉菜单
	function initColumnTree(){
		$.ajax({
    		url : basePath + "cms/queryColumnTree",
    		data : {
    			columnParentId : 0
    		},
    		type : 'POST',
    		async : true, // 改异步为同步  
    		dataType : 'json',
    		success : function(data) {
    			var treeData = column2Tree(data);
    			var firstNode = {
					id : 0,
					text : "请选择相应选项"
				};
    			treeData.unshift(firstNode);
    			$("#columnTree").treeview({
    				data: treeData,
    				onNodeSelected:function(event, data) {
    					//console.log(data);
    					//将当前查询form的columnParentId设置为当前选择的栏目的Id
    					tableOption.form.find("input[name='columnParentId']").val(data.id);
    					tableOption.form.find("input[name='columnParentName']").val(data.text);
    					tableOption.form.find("input[name='columnParentName']").next().hide();
    				}
    			});
    		}
    	});
	}
	//查询form中的栏目名称添加click事件，当点击的时候初始化栏目树下拉框
	tableOption.form.find("input[name='columnParentName']").click(function() {
		//初始化栏目树形菜单
		initColumnTree();
		$(this).next().toggle();
	});
	
	//初始化表单校验函数，当打开增加/修改窗口时，调用该方法，初始化校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			rules: {
				columnName: {
					required: true
				}
			}
		});
    }
	
    //查询按钮
    $("#btn-query").on("click", function () {
    	tableOption.form.find("input[name='columnParentName']").next().hide();
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
	    			//恢复查询表单form中的columnParentId的值为0，默认查询所有一级栏目
	    			//tableOption.form.find("input[name='columnParentId']").val(0);
                	//刷新结果列表
            		dataTable.draw(true);
            		//刷新栏目树
            		initColumnTree();
                }
	    		//提示信息
            	tcConfirm(data.info,"是否返回？",function(){
            		jcAdd.close();
            	});
	    	},
	    	//窗口打开后，初始化操作，可自定义初始化内容，本功能中初始化了一个日期控件，下拉框控件
	    	function(content){
	    		//窗口打开后 先删除之前初始化的ueditor，再重新初始化，否则会渲染失败
	    		UE.delEditor("remark");
	    		//初始化ueditor控件
	    		ue = UE.getEditor("remark");
	    		
	    		//将增加form表单中columnParentId的值设置为当前父级栏目的Id
	    		content.find("input[name='columnParentId']").val(tableOption.form.find("input[name='columnParentId']").val());
	    		//初始化bootstrap-switch开关控件
	    		content.find("#columnEnable").bootstrapSwitch({
	    			onText:"是",  
	    	        offText:"否",
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	            	content.find("input[name='columnEnable']").val("1");  
	    	            }else{  
	    	            	content.find("input[name='columnEnable']").val("2");  
	    	            }  
	    	        }
	    		});
	    		//content.find("input[name='columnEnable']").bootstrapSwitch('state', true);
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    	}
    	);
    });
    
    //点击每一个栏目的添加按钮，在该栏目下增加子栏目
    /*
    tableOption.table.on("click",".addChildRow",function() {
    	var item = dataTable.row($(this).closest('tr')).data();
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
	    		//将form表单中columnParentId的值设置为当前栏目的Id
	    		content.find("input[name='columnParentId']").val(item.columnId);
	    		//初始化bootstrap-switch开关控件
	    		content.find("input[name='columnEnable']").bootstrapSwitch({
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	                $(this).val("1");  
	    	            }else{  
	    	                $(this).val("0");  
	    	            }  
	    	        }
	    		});
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    	}
    	);
    });
    */
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        if(item.columnChildNum > 0){
        	tcAlert2("无法删除!","该栏目含有子栏目，请先删除子栏目！", 6000);
        	return false;
        }
        //向服务器发送删除请求的参数，使用post方式
        var params = {columnId:item.columnId};
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
	        	//窗口打开后 先删除之前初始化的ueditor，再重新初始化，否则会渲染失败
	    		UE.delEditor("remark");
	    		//初始化ueditor控件
	    		var updateUe = UE.getEditor('remark');
	    		updateUe.ready(function() {
	    		    //设置编辑器的内容
	    			var remark = item["remark"];
	    			updateUe.setContent(remark);
	    		});
	        	//初始化bootstrap-switch开关控件
	        	content.find("#columnEnable").bootstrapSwitch({
	    			onText:"是",  
	    	        offText:"否",
	    			onSwitchChange:function(event,state){  
	    	            if(state==true){  
	    	            	content.find("input[name='columnEnable']").val("1");  
	    	            }else{  
	    	            	content.find("input[name='columnEnable']").val("2");  
	    	            }  
	    	        }
	    		});
	    		//var switchEnableFlag = item.columnEnable == 1 ? true : false;
	    		if(item.columnEnable == 1){
	    			content.find("#columnEnable").bootstrapSwitch('state', true);
	    			content.find("input[name='columnEnable']").val("1");
	    		}else{
	    			content.find("#columnEnable").bootstrapSwitch('state', false);
	    			content.find("input[name='columnEnable']").val("2"); 
	    		}
	    		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
	        	fillForm(updateForm,item);
	    		//初始化表单校验
	    		initFormValidator(tableOption.updateFormId);
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
        item.titleSuffix = "栏目" + item.columnId;
        var jcDetail = detailData(tableOption,item,function(detailForm,item,content){
        	//初始化bootstrap-switch开关控件
    		content.find("input[name='columnEnable']").bootstrapSwitch({
    			onText:"是",  
    	        offText:"否"
    		});
    		//var switchEnableFlag = item.columnEnable == 1 ? true : false;
    		if(item.columnEnable == 1){
    			content.find("input[name='columnEnable']").bootstrapSwitch('state', true);
    		}
    		content.find("input[name='columnEnable']").bootstrapSwitch('disabled', true);
    		
    		content.find("#remark").prepend(item["remark"]);
    		
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        })
    });
    
    //查询子栏目
    /*
    tableOption.table.on("click",".child-collapse",function() {
    	//获取当前行数据对象
    	var curTr = $(this).closest('tr');
        var item = dataTable.row(curTr).data();
        //根据columnId作为columnParentId的值为条件查询子栏目
        $.ajax({
    		url : basePath + "cms/queryColumn",
    		data : {
    			columnParentId : item.columnId
    		},
    		type : 'POST',
    		async : false, // 改异步为同步  
    		dataType : 'json',
    		success : function(data) {
    			//var table = $("<table>");
    			for(var i = 0; i < data.length; i++){
    			
    				var tr = $("<tr>");
    				var td = $("<td>");
    				tr.append(td);
    				
    				var td1 = $("<td>");
    				td1.text(data[i].columnName);
    				tr.append(td1);
    				
    				//table.append(tr);
    				curTr.after(tr);
    			}
    			//curTr.after(table);
    		}
    	});
    });
    */
    //排序上移
    tableOption.table.on("click",".sortUp",function() {
    	//获取当前行数据对象
    	var curTr = $(this).closest('tr');
        var item = dataTable.row(curTr).data();
        
    	$.ajax({
    		url : tableOption.upSortUrl,
    		data : {
    			columnId : item.columnId
    		},
    		type : 'POST',
    		async : true, // 改异步为同步  
    		dataType : 'json',
    		success : function(data) {
    			//修改成功后提示信息
        		if(data.status == 1){
                	//刷新结果列表
            		dataTable.draw(true);
                }else{
                	tcAlert(data.info, 6000);
                }
    			
    		}
    	});
    });
    //排序下移
    tableOption.table.on("click",".sortDown",function() {
    	//获取当前行数据对象
    	var curTr = $(this).closest('tr');
        var item = dataTable.row(curTr).data();
        
    	$.ajax({
    		url : tableOption.downSortUrl,
    		data : {
    			columnId : item.columnId
    		},
    		type : 'POST',
    		async : true, // 改异步为同步  
    		dataType : 'json',
    		success : function(data) {
    			//修改成功后提示信息
        		if(data.status == 1){
                	//刷新结果列表
            		dataTable.draw(true);
                }else{
                	tcAlert(data.info, 6000);
                }
    			
    		}
    	});
    });
});


