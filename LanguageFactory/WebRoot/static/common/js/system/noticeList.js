$(function () {
	var ue;
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "system/queryNoticeList",
		//ajax删除数据的http请求url
		delUrl : basePath + "system/delNotice",
		//增加页面
		addPageUrl : basePath + 'forward?page=system/noticeAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "system/addNotice",
		//修改页面
		updatePageUrl : basePath + 'forward?page=system/noticeModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "system/updateNotice",
		//用于公告发布、撤销
		publishUrl : basePath + "system/publishNotice",
		//查看页面
		detailPageUrl : basePath + 'forward?page=system/noticeDetail',
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
	        {"data":'noticeTitle'},
	        {"data":'noticeState',
	        	"render":function(data,type,full,callback) {
	        		if(data == 2){
	        			//return "<button type=\"button\" class=\"btn btn-warning btn-sm cancelPublish\"><span class=\"badge label-success\">已发布</span> 撤销</button>";
	        			return "<button type=\"button\" class=\"btn btn-success btn-sm cancelPublish\">已发布</button>";
                    } else {
                    	//return "<button type=\"button\" class=\"btn btn-success btn-sm publish\"><span class=\"badge label-warning\">未发布</span> 发布</button>";
                    	return "<button type=\"button\" class=\"btn btn-warning btn-sm publish\">未发布</button>";
                    }
	            }
	        },
	        {"data":'createByName',className:'hidden-xs'},
	        {"data":'createTime',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		if(data != null && data != ""){
                        return data.substr(0,19)
                    }
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 5,
		operBtns : [
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit",
				condition:function(data, type, row){
					//当公告的状态为已发布时，不显示修改按钮
					if(data.noticeState == 2){
						return false;
					}
					return true;
				}
			},
			{
				title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o",
				condition:function(data, type, row){
					//当公告的状态为已发布时，不显示删除按钮
					if(data.noticeState == 2){
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
			ignore:"",//在校验时，框架不对对隐藏的元素进行验证，将ignore设置成""或者[],框架将不会对任何元素呼略校验，从而实现对隐藏元素的校验
			rules: {
				noticeTitle: {
					required: true
				},
				noticeContent:{
					required: true
				}
			}
		});
    }
	//state 1:未发布  2:发布
	function publishOrNot(item,state){
		$.ajax({
			type : 'post',
			url : tableOption.publishUrl,
			data : {noticeId:item["noticeId"], noticeState:state},
			dataType : "json",
			cache : "false",
			success : function(data) {
				if(data.status == 0){
					tcAlert(data.info);
					return false;
				}
				//刷新结果列表
				dataTable.draw(true);
			},
			error : function(err) {
				ajaxErrorAlert(err);
			}
		});
	}
	//发布
	tableOption.table.on("click",".publish", function () {
		//要修改哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        publishOrNot(item,2);
		
	});
	
	//撤销发布
	tableOption.table.on("click",".cancelPublish", function () {
		//要修改哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        publishOrNot(item,1);
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
	    		UE.delEditor("noticeContent");
	    		//初始化ueditor控件
	    		ue = UE.getEditor('noticeContent');
	    		
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    	}
	    	//,function(){
	    		//在窗口关闭的时候，销毁ueditor
	    		//if(typeof(UE.getEditor("noticeContent")) !='undefined'){
	    	    //	UE.getEditor("noticeContent").destroy();
	    		//}
	    	//}
    	);
    });
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw();//查询后不需要保持分页状态，回首页
    });
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {noticeId:item.noticeId};
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
        		/***************初始化ueditor******************/
        		//窗口打开后 先删除之前初始化的ueditor，再重新初始化，否则会渲染失败
	    		UE.delEditor("noticeContent");
	    		//初始化ueditor控件
	    		var ue = UE.getEditor('noticeContent');
	    		
	    		ue.ready(function() {
	    		    //设置编辑器的内容
	    			var noticeContent = item["noticeContent"];
	    		    ue.setContent(noticeContent);
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
    
    //查看
    tableOption.table.on("click",".detailRow",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        var jcDetail = detailData(tableOption,item,function(detailForm,item){
        	
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        	$("#noticeContent").append(item["noticeContent"]);
        })
    });
});