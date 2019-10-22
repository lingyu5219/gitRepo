$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "studymgt/queryProjectList",
		//ajax删除数据的http请求url
		delUrl : basePath + "studymgt/delProject",
		//增加页面
		addPageUrl : basePath + 'forward?page=studymgt/projectAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "studymgt/addProject",
		//修改页面
		updatePageUrl : basePath + 'forward?page=studymgt/projectModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "studymgt/updateProject",
		//查看页面
		detailPageUrl : basePath + 'forward?page=studymgt/projectDetail',
		//文件上传URL
		uploadAction : basePath + "upload/uploadFile",
		//文件删除URL
		delAction : basePath + "upload/delFile",
		//文件下载URL
		downloadAction:basePath + "upload/downloadFile",
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
	        {"data":'projectName'},
	        {"data":'projectUrl',
	        	"render":function(data,type,full,callback) {
	        		return "<a href=\"" + data + "\" target=\"_blank\">查看</a>";
	            }
	        },
	        {"data":'projectFile',
	        	"render":function(data,type,full,callback) {
	        		if (data){
	        			return "<a href=\"javascript:void(0);\" class=\"btn btn-warning btn-xs downloadFile\" target=\"_blank\">下载</a>";
	        		} else {
	        			return "<span class=\"label bg-red\">无</span>";
	        		}
	            }
	        },
	        {"data":'createTime',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		return full.createTime = data.substr(0,19);
	            }
	        },
	        {"data":null}
	    ],
		//操作按钮所在列
		operBtnColumn : 5
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
    
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw(true);//查询后不需要保持分页状态，回首页
    });
    
    function downloadFile(item){
		var leoUp = new LeoUpload({downloadAction:tableOption.downloadAction});
		leoUp.downloadFile({fileRelativePath:item.projectFile,fileName:item.projectFileName});
	}
    
    tableOption.table.on("click",".downloadFile",function() {
    	var item = dataTable.row($(this).closest('tr')).data();
    	downloadFile(item);
    });
    
    //增加
    $("#btn-add").on("click", function () {
    	var jcAdd = addData(tableOption,
    		//以下3个方法可以自定义
    		//发送增加请求之前，执行此方法，一般在此方法中用于准备向请求传递的参数
    		function(){
    			var addForm = $("#" +tableOption.addFormId);
    			if(addForm.attr("enctype") == "multipart/form-data"){
    				var formData = new FormData(addForm[0]);
    				return formData;
    			}
	    		//获取表单数据，此处可自行获取表单数据
	        	var param = formToJson(addForm);
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
	    		//初始化文件上传插件
	    		var leoUpload = new LeoUpload({
	    			fileMaxCount:1,
	    			fileMaxSize:52428800,//50MB
	    			fileLimitType:".zip|.rar",
	    			uploadPath : "static/upload/file/projects",
	    			uploadAction : tableOption.uploadAction,
	    			delAction : tableOption.delAction,
	    			picker : "projectFileBtn",
	    			fileList : "fileList",
	    			afterSuccess : function(data){
	    				$("input[name='projectFile']").val(data.fileRelativePath);
	    				$("input[name='projectFileName']").val(data.fileName);
	    				$("input[name='projectFileSize']").val(data.fileSize);
	    			},
	    			afterDelFile : function(data){
	    				$("input[name='projectFile']").val("");
	    			}
	    		});
	    	}
    	);
    });
    
    
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {projectId:item.projectId};
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
        var jcDetail = detailData(tableOption,item,function(detailForm,item,content){
        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
        	fillForm(detailForm,item);
        	//初始化文件
        	if(item.projectFile){
        		var fileBoxCol = $("<div class=\"col-sm-6\">");
        		var fileBox = $("<div class=\"info-box bg-aqua\">");
        		var fileIcon = $("<span class=\"info-box-icon\"><i class=\"fa fa-file-archive-o\"></i></span>");
        		var fileContent = $("<div class=\"info-box-content\">");
        		var fileName = $("<span class=\"info-box-text\" title=\"" + item.projectFileName + "\">" + item.projectFileName + "</span>");
        		var fileSize = $("<span class=\"info-box-text\">" + Math.ceil(item.projectFileSize / 1024) + "KB </span>");
        		var fileDownloadBtn = $("<span class=\"progress-description\"><a class=\"btn btn-warning btn-xs pull-right leoFileDownloadBtn\">下载</a></span>");
        		//设置下载
        		fileDownloadBtn.find(".leoFileDownloadBtn").click(function(){
        			var leoUp = new LeoUpload({downloadAction:tableOption.downloadAction});
        			leoUp.downloadFile({fileRelativePath:item.projectFile,fileName:item.projectFileName});
        		});
        		
        		fileContent.append(fileName).append(fileSize).append(fileDownloadBtn);
        		fileBox.append(fileIcon).append(fileContent);
        		fileBoxCol.append(fileBox);
        		content.find("#fileList").append(fileBoxCol);
        	} else {
        		var label = $("<span class=\"label bg-red\">无</span>");
        		content.find("#fileList").append(label);
        	}
        })
    });
    
    
});