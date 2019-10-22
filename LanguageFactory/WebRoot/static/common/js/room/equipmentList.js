$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "/equipment/queryEquipmentList",
		//ajax删除数据的http请求url
		delUrl : basePath + "/equipment/delEquipment",
		//ajax增加数据的http请求url
		addUrl : basePath + "/equipment/addEquipment",
		//修改页面
		updatePageUrl : basePath + 'forward?page=assetsmgt/equipmentModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "equipment/updateEquipment",
		//查看页面
		detailPageUrl : basePath + 'forward?page=assetsmgt/equipmentDetail',
		
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
	        {"data":'equipmentId',visible:false},
	        {"data":'equipmentName'},
	        {"data":'equipmentType'},
	        {"data":'equipmentState'},
	        {"data":'createBy'},
	        {"data":'createTime',
	        	"render":function(data,type,full,callback) {
	                return data.substr(0,19) 
	            }
	        },
	       
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 7
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
    
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw();//查询后不需要保持分页状态，回首页
    });
    
    
    //增加
    $("#btn-add").on("click", function () {
    	var jcAdd = $.dialog({
    		title: '增加数据',
    	    content: 'url:' + basePath + '/forward?page=assetsmgt/equipmentAdd',
    	    columnClass: 'col-md-8 col-md-offset-2',
    	    onContentReady : function(){
    	        // NOTE: `this.$content` is the jquery object for content.
    	    	this.$content.find("button.btn-save").click(function(){
    	    		var param = formToJson($("#addForm1"));
    	    		
    	    		$.ajax({
    	            	type: 'post',
    	                url: tableOption.addUrl,
    	                data: param,
    	                dataType: "json",
    	                cache: "false",
    	                success: function(data){
    	                	if(data.equipmentId != null && data.equipmentId != "" && data.equipmentId != "undefined"){
    	                    	//dataTable.ajax.reload();
    	                    	dataTable.draw(true);
    	                    }
    	                	tcConfirm("增加成功","是否返回用户管理页面？",function(){
    	                		jcAdd.close();
    	                	});
    	                },
    	                error: function(err){
    	                	tcAlert(err.status + "——" + err.statusText);
    	                }
    	            });
    	        });
    	    }
    	});
    });
    
    
    
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {equipmentId:item.equipmentId};
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
    		function(updateForm,item){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
        		fillForm(updateForm,item);
        	},
        	function(){
        		//获取修改表单数据
        		var param = formToJson($("#" + tableOption.updateFormId));
        		return param;
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