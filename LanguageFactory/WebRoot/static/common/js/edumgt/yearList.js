$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "edumgt/queryYearList",
		//ajax删除数据的http请求url
		delUrl : basePath + "edumgt/delYear",
		//增加页面
		addPageUrl : basePath + 'forward?page=edumgt/yearAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "edumgt/addYear",
		//修改页面
		updatePageUrl : basePath + 'forward?page=edumgt/yearModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "edumgt/updateYear",
		//查看页面
		detailPageUrl : basePath + 'forward?page=edumgt/yearDetail',
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
	        {"data":'yearTitle'},
	        {"data":'createByName'},
	        {"data":'createTime',
	        	"render":function(data,type,full,callback) {
	                return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 4
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
    
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw(true);//查询后不需要保持分页状态，回首页
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
	    		initDateWithOption({
	    			dateObj:content.find("input.datepicker"),
	    			format: "yyyy",
	    			minViewMode: 2,
	    			maxViewMode: 2
	    		});
	    	}
    	);
    });
    
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {yearId:item.yearId};
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
        		//有日期控件，先设置日期值，回显日期
        		content.find("input.datepicker").val(item.yearTitle);
        		initDateWithOption({
	    			dateObj:content.find("input.datepicker"),
	    			format: "yyyy",
	    			minViewMode: 2,
	    			maxViewMode: 2
	    		});
        	},
        	//发送请求之前，执行此方法，一般才此方法中用于准备向请求传递的参数
        	function(){
        		//获取修改表单数据
        		var param = formToJson($("#" + tableOption.updateFormId));
        		return param;
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
        var jcDetail = detailData(tableOption,item,function(detailForm,item){
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        })
    });
});