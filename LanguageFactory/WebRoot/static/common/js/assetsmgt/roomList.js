$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "room/queryRoomList",
		//ajax删除数据的http请求url
		delUrl : basePath + "room/delRoom",
		//ajax增加数据的http请求url
		addUrl : basePath + "room/addRoom",
		
		addPageUrl : basePath + 'forward?page=assetsmgt/roomAdd',
		//修改页面
		updatePageUrl : basePath + 'forward?page=assetsmgt/roomModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "room/updateRoom",
		//查看页面
		detailPageUrl : basePath + 'forward?page=assetsmgt/roomDetail',
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
	        {"data":'roomId',visible:false},
	        {"data":'roomName'},
	        {"data":'centerId'},
	        {"data":'createBy'},
	        {"data":'createTime',
	        	"render":function(data,type,full,callback) {
	                return data.substr(0,19) 
	            }
	        },
	        {"data":'remark'},
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
    	
    	
    	var jcAdd = addData(tableOption,
    		//以下3个方法可以自定义
    		//发送请求之前，执行此方法，一般才此方法中用于准备向请求传递的参数
    		function(){
	    		//获取表单数据，此处可自行获取表单数据
	        	var param = formToJson($("#" +tableOption.addFormId));
	        	return param;
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
	    		//初始化日期控件，content是窗口的内容对象，通过find根据jquery的类选择器找到日期控件进行初始化
	    		initDate(content.find("input.datepicker"));
	    		/***************新增初始化下拉框控件******************/
	    		initSelect2({
	    			selected:false,//没有默认选中项
	    			selectObj: content.find("select.roleSelect2"),//通过class类选择器获取的下拉框对象
	    			placeholder: "请选区域",
	    			url: basePath + 'room/queryRoomList',//查询下拉框选项的url，具体参考UserController中的queryUser方法
	    			beforeRequest: function (params) {
	    				//发送查询请求之前，先将输入的关键词，赋值给对应的查询条件，次示例中是将关键词作为userName进行查询
						var query = {centerId: params.term};
						return query;
    			    },
    			    afterResponse: function (data) {
    			    	//查询结果返回后，在将数据插入到select下拉框之前执行此函数，此处可以对返回的数据做一些处理后，再显示到select中
    			    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.roomId;
							obj.text = obj.centerId;
							
							return obj;
			    		});
    			        return {results: result};
    			    }
	    		});
	    		/**********************************************************/
	    	}
    	);
    });
    
  //增加
  /**  $("#btn-add").on("click", function () {
    	var jcAdd = $.dialog({
    		title: '增加数据',
    	    content: 'url:' + basePath + '/forward?page=assetsmgt/roomAdd',
    	    columnClass: 'col-md-8 col-md-offset-2',
    	    onContentReady : function(){
    	        // NOTE: `this.$content` is the jquery object for content.
    	    	this.$content.find("button.btn-save").click(function(){
    	    		var param = formToJson($("#addForm"));
    	    		
    	    		$.ajax({
    	            	type: 'post',
    	                url: tableOption.addUrl,
    	                data: param,
    	                dataType: "json",
    	                cache: "false",
    	                success: function(data){
    	                	if(data.roomId != null && data.roomId != "" && data.roomId != "undefined"){
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
    **/
    
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {roomId:item.roomId};
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
