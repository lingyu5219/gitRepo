$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "userTest/queryUserList",
		//ajax删除数据的http请求url
		delUrl : basePath + "userTest/delUser",
		//增加页面
		addPageUrl : basePath + 'forward?page=user/userAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "userTest/addUser",
		//修改页面
		updatePageUrl : basePath + 'forward?page=user/userModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "userTest/updateUser",
		//查看页面
		detailPageUrl : basePath + 'forward?page=user/userDetail',
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
	        {"data":'userId',visible:false},
	        {"data":'userName'},
	        {"data":'userEmail'},
	        {"data":'createBy'},
	        {"data":'createTime',
	        	"render":function(data,type,full,callback) {
	        		return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 6,
		/*
		 * 如果操作按钮有修改、删除、查看这三个的，可不用此配置，默认会加入修改、删除、查看三个按钮
		 * 如果操作按钮不一定都是修改、删除、查看的，操作按钮可自定义，重点要指定按钮的类名，后面每个按钮通过类名绑定事件
		*/
		operBtns:[
			{btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{btnClass:"btn-primary editRow",iconClass:"fa-edit"},
			{btnClass:"btn-danger delRow",iconClass:"fa-trash-o"}
		]
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
	    		initDate(content.find("input.datepicker"));
	    		/***************新增初始化下拉框控件******************/
	    		initSelect2({
	    			selected:false,//没有默认选中项
	    			selectObj: content.find("select.roleSelect2"),//通过class类选择器获取的下拉框对象
	    			placeholder: "请选择创建人",
	    			url: basePath + 'userTest/queryUser',//查询下拉框选项的url，具体参考UserController中的queryUser方法
	    			beforeRequest: function (params) {
	    				//发送查询请求之前，先将输入的关键词，赋值给对应的查询条件，次示例中是将关键词作为userName进行查询
						var query = {userName: params.term};
						return query;
    			    },
    			    afterResponse: function (data) {
    			    	//查询结果返回后，在将数据插入到select下拉框之前执行此函数，此处可以对返回的数据做一些处理后，再显示到select中
    			    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.userId;
							obj.text = obj.userName;
							
							return obj;
			    		});
    			        return {results: result};
    			    }
	    		});
	    		/**********************************************************/
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
        		
        		//有日期控件，先设置日期值，回显日期
        		content.find("input.datepicker").val(item.createTime.substr(0,10));
        		//初始化日期控件
        		initDate(content.find("input.datepicker"));
        		
        		/***************新增初始化下拉框控件******************/
	    		initSelect2({
	    			selected:true,//在做修改操作时，需要回显数据，所以有默认选中项
	    			selectedData:{id: item.userId, text: item.userName},//默认选中项的value和text值
	    			selectObj: content.find("select.roleSelect2"),//下拉框对象
	    			placeholder: "",
	    			url: basePath + 'userTest/queryUser',
	    			beforeRequest: function (params) {
	    				//发送查询请求之前，先将输入的关键词，赋值给对应的查询条件，次示例中是将关键词作为userName进行查询
						var query = {userName: params.term};
						return query;
    			    },
    			    afterResponse: function (data) {
    			    	//查询结果返回后，在将数据插入到select下拉框之前执行此函数，此处可以对返回的数据做一些处理后，再显示到select中
    			    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.userId;
							obj.text = obj.userName;
							
							return obj;
			    		});
    			        return {results: result};
    			    }
	    		});
	    		/**********************************************************/
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
    
    $("#btn-testJson").bind("click",function(){
    	$.ajax({
    		type : 'get',
    		url : basePath + "userTest/userListJson",
    		dataType : "json",
    		cache : "false",
    		success : function(data) {
    			var userList = data.userList;
    			for(var i = 0; i < userList.length; i++){
    				var user = userList[i];
    				tcAlert(user.userAccount);
    				
    			}
    			
    		},
    		error : function(err) {
    			ajaxErrorAlert(err);
    		}
    	});
    });
    
});