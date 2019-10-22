$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "/resume/queryResumeList",
		//ajax删除数据的http请求url
		delUrl : basePath + "/resume/delResume",
		//ajax增加数据的http请求url
		addUrl : basePath + "/resume/addResume",
		//Table对象
		table : $('#dataListTable'),
		//查询表单Form对象
		form : $("#queryForm"),
		//表格列
		columns : [//对应thead里面的序列
	        {"data":null,width:"10px"},
	        {"data":'resumeId',visible:false},
	        {"data":'stuId'},
	        {"data":'resumeDescribe'},
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
    	var jcAdd = $.dialog({
    		title: '增加用户',
    	    content: 'url:' + basePath + '/forward?page=user/userAdd',
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
    	                	if(data.userId != null && data.userId != "" && data.userId != "undefined"){
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
});