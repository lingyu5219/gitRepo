$(function () {
	var tableOption = {
		//ajax查询数据的http请求url 
		queryUrl : basePath + "/center/queryCenterList",
		//ajax删除数据的http请求url
		delUrl : basePath + "/center/deleteCenter",
		//ajax增加数据的http请求url
		addUrl : basePath + "/center/addCenter",
		//Table对象
		table : $('#dataListTable'),
		//查询表单Form对象
		form : $("#queryForm"),
		//表格列
		columns : [//对应thead里面的序列
	        {"data":null,width:"10px"},
	        {"data":'centerId',visible:false},
	        {"data":'centerName'},
	        {"data":'centerAddress'},
	        
	        {"data":'remark'},
	        {"data":'createBy'},
	        {"data":'createTime',
	        	"render":function(data,type,full,callback) {
	                return data.substr(0,19) 
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn :7
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
    		title: '增加中心',
    	    content: 'url:' + basePath + '/forward?page=org/centerAdd',
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
    	                	if(data.centerName != null && data.centerName != "" && data.centerName != "undefined"){
    	                    	//dataTable.ajax.reload();
    	                    	dataTable.draw(true);
    	                    }
    	                	tcConfirm("增加成功","是否返回管理页面？",function(){
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
        var params = {centerId:item.centerId};
        //调用删除方法，向服务器发送请求
        delDataTableItem(dataTable,tableOption,params,function(data){
        	if(data.status == 1){
            	//dataTable.ajax.reload();
            	dataTable.draw(true);
            }
            tcAlert(data.info, 6000);
        });
    }); 
    
});