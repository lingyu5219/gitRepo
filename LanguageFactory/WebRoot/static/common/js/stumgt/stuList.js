$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "stumgt/queryStuList",
		//ajax删除数据的http请求url
		delUrl : basePath + "stumgt/delStu",
		//增加页面
		addPageUrl : basePath + 'forward?page=stumgt/stuAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "stumgt/addStu",
		//修改页面
		updatePageUrl : basePath + 'forward?page=stumgt/stuModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "stumgt/updateStu",
		//查看页面
		detailPageUrl : basePath + 'forward?page=stumgt/stuDetail',
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
	        {"data":'majorName'},
	        {"data":'batchName'},
	        {"data":'stuNo'},
	        {"data":'stuName'},
	        {"data":'stuSex',
	        	"render":function(data,type,full,callback) {
        			return data == 1 ? "男" : "女";
	            }
	        },
	        {"data":'stuMobile'},
	        {"data":'createByName',visible:false},
	        {"data":'createTime',visible:false,
	        	"render":function(data,type,full,callback) {
        			return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 10
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
    
	/***************初始化查询条件下拉框控件******************/
	//初始化学年下拉框
	initBasicSelect2({
		url: basePath + 'edumgt/queryYear',
		callback: function(data){
        	var result = $.map(data, function (obj) {
	    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	    		obj.id = obj.yearId;
				obj.text = obj.yearTitle;
				
				return obj;
    		});
        	$("select.yearIdSelect2").select2({
        		data:result
        	});
        }
	});
	//初始化专业方向下拉框
	initBasicSelect2({
		url: basePath + 'edumgt/queryMajor',
		callback: function(data){
        	var result = $.map(data, function (obj) {
	    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	    		obj.id = obj.majorId;
				obj.text = obj.majorName;
				
				return obj;
    		});
        	$("select.majorIdSelect2").select2({
        		data:result
        	});
        }
	});
	//学年下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	$("select.yearIdSelect2").on('change', function (e) {
		var yearId = $(this).val();
		//var yearTitle = $(this).find("option:selected").text();
		var majorId = $("select.majorIdSelect2").find("option:selected").val();
		initBasicSelect2({
			url: basePath + 'edumgt/queryBatch',
			params: {yearId:yearId,majorId:majorId},
			callback: function(data){
            	var result = $.map(data, function (obj) {
		    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
		    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
		    		obj.id = obj.batchId;
					obj.text = obj.batchName;
					
					return obj;
	    		});
            	$("select.batchIdSelect2").empty().select2({
            		data:result
            	});
            	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
            	$("select.batchIdSelect2").append(option).trigger("change");
            }
		});
	});
	//专业方向下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	$("select.majorIdSelect2").on('change', function (e) {
		var yearId = $("select.yearIdSelect2").find("option:selected").val();
		var majorId = $(this).val();
		initBasicSelect2({
			url: basePath + 'edumgt/queryBatch',
			params: {yearId:yearId,majorId:majorId},
			callback: function(data){
            	var result = $.map(data, function (obj) {
		    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
		    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
            		obj.id = obj.batchId;
					obj.text = obj.batchName;
					
					return obj;
	    		});
            	$("select.batchIdSelect2").empty().select2({
            		data:result
            	});
            	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
            	$("select.batchIdSelect2").append(option).trigger("change");
            }
		});
	});
	
	/**********************************************************/
	
	
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
	    		/***************初始化下拉框控件******************/
	    		//初始化学年下拉框
	    		initBasicSelect2({
        			url: basePath + 'edumgt/queryYear',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.yearId;
							obj.text = obj.yearTitle;
							
							return obj;
			    		});
                    	content.find("select.yearIdSelect2").select2({
                    		data:result
                    	});
                    }
        		});
	    		//初始化专业方向下拉框
	    		initBasicSelect2({
        			url: basePath + 'edumgt/queryMajor',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.majorId;
							obj.text = obj.majorName;
							
							return obj;
			    		});
                    	content.find("select.majorIdSelect2").select2({
                    		data:result
                    	});
                    }
        		});
	    		//学年下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	    		content.find("select.yearIdSelect2").on('change', function (e) {
	    			var yearId = $(this).val();
	    			//var yearTitle = $(this).find("option:selected").text();
	    			var majorId = content.find("select.majorIdSelect2").find("option:selected").val();
	    			initBasicSelect2({
	        			url: basePath + 'edumgt/queryBatch',
	        			params: {yearId:yearId,majorId:majorId},
	        			callback: function(data){
	                    	var result = $.map(data, function (obj) {
	    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	    			    		obj.id = obj.batchId;
								obj.text = obj.batchName;
								
								return obj;
				    		});
	                    	content.find("select.batchIdSelect2").empty().select2({
	                    		data:result
	                    	});
	                    	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
	                    	content.find("select.batchIdSelect2").append(option).trigger("change");
	                    }
	        		});
    			});
	    		//专业方向下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	    		content.find("select.majorIdSelect2").on('change', function (e) {
	    			var yearId = content.find("select.yearIdSelect2").find("option:selected").val();
	    			var majorId = $(this).val();
	    			initBasicSelect2({
	        			url: basePath + 'edumgt/queryBatch',
	        			params: {yearId:yearId,majorId:majorId},
	        			callback: function(data){
	                    	var result = $.map(data, function (obj) {
	    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	                    		obj.id = obj.batchId;
								obj.text = obj.batchName;
								
								return obj;
				    		});
	                    	content.find("select.batchIdSelect2").empty().select2({
	                    		data:result
	                    	});
	                    	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
	                    	content.find("select.batchIdSelect2").append(option).trigger("change");
	                    }
	        		});
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
        var params = {stuId:item.stuId, userId:item.userId};
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
        		content.find("input.datepicker").val(item.stuBirthday);
        		//初始化日期控件
        		initDate(content.find("input.datepicker"));
        		/***************初始化下拉框控件******************/
        		//初始化性别
        		
	    		//初始化学年下拉框
	    		initBasicSelect2({
        			url: basePath + 'edumgt/queryYear',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.yearId;
							obj.text = obj.yearTitle;
							
							return obj;
			    		});
                    	content.find("select.yearIdSelect2").select2({
                    		data:result
                    	});
                    	content.find("select.yearIdSelect2").val(item.yearId).trigger('change');
                    }
        		});
	    		//初始化专业方向下拉框
	    		initBasicSelect2({
        			url: basePath + 'edumgt/queryMajor',
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.majorId;
							obj.text = obj.majorName;
							
							return obj;
			    		});
                    	content.find("select.majorIdSelect2").select2({
                    		data:result
                    	});
                    	content.find("select.majorIdSelect2").val(item.majorId).trigger('change');
                    }
        		});
	    		//初始化班级下拉框 
	    		/*initBasicSelect2({
        			url: basePath + 'edumgt/queryBatch',
        			params: {yearId:item.yearId,majorId:item.majorId},
        			callback: function(data){
                    	var result = $.map(data, function (obj) {
    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
    			    		obj.id = obj.batchId;
							obj.text = obj.batchName;
							
							return obj;
			    		});
                    	content.find("select.batchIdSelect2").select2({
                    		data:result
                    	});
                    	
                    }
        		});
        		*/
	    		//学年下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	    		content.find("select.yearIdSelect2").on('change', function (e) {
	    			var yearId = $(this).val();
	    			//var yearTitle = $(this).find("option:selected").text();
	    			var majorId = content.find("select.majorIdSelect2").find("option:selected").val();
	    			initBasicSelect2({
	        			url: basePath + 'edumgt/queryBatch',
	        			params: {yearId:yearId,majorId:majorId},
	        			callback: function(data){
	                    	var result = $.map(data, function (obj) {
	    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	    			    		obj.id = obj.batchId;
								obj.text = obj.batchName;
								
								return obj;
				    		});
	                    	content.find("select.batchIdSelect2").empty().select2({
	                    		data:result
	                    	});
	                    	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
	                    	content.find("select.batchIdSelect2").append(option).trigger("change");
	                    	//如果当前班级option中有原来的班级，则回显，没有则显示“请选择班级”
	                    	for(var i in result){
	                    		if (result[i].id == item.batchId) {
	                    			content.find("select.batchIdSelect2").val(item.batchId).trigger('change');
	                    		}
	                    	}
	                    }
	        		});
    			});
	    		//专业方向下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	    		content.find("select.majorIdSelect2").on('change', function (e) {
	    			var yearId = content.find("select.yearIdSelect2").find("option:selected").val();
	    			var majorId = $(this).val();
	    			initBasicSelect2({
	        			url: basePath + 'edumgt/queryBatch',
	        			params: {yearId:yearId,majorId:majorId},
	        			callback: function(data){
	                    	var result = $.map(data, function (obj) {
	    			    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    			    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	                    		obj.id = obj.batchId;
								obj.text = obj.batchName;
								
								return obj;
				    		});
	                    	content.find("select.batchIdSelect2").empty().select2({
	                    		data:result
	                    	});
	                    	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
	                    	content.find("select.batchIdSelect2").append(option).trigger("change");
	                    	//如果当前班级option中有原来的班级，则回显，没有则显示“请选择班级”
	                    	for(var i in result){
	                    		if (result[i].id == item.batchId) {
	                    			content.find("select.batchIdSelect2").val(item.batchId).trigger('change');
	                    		}
	                    	}
	                    }
	        		});
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
});