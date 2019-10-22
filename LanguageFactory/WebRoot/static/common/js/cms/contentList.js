$(function () {
	var ue;
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "cms/queryContentList",
		//ajax删除数据的http请求url
		delUrl : basePath + "cms/delContent",
		//增加页面
		addPageUrl : basePath + 'forward?page=cms/contentAdd',
		//ajax增加数据的http请求url
		addUrl : basePath + "cms/addContent",
		//修改页面
		updatePageUrl : basePath + 'forward?page=cms/contentModify',
		//ajax修改数据的http请求url
		updateUrl : basePath + "cms/updateContent",
		//用于公告发布、撤销
		publishUrl : basePath + "cms/publishContent",
		//查看页面
		detailPageUrl : basePath + 'forward?page=cms/contentDetail',
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
	        {"data":'contentTitle',
	        	"render":function(data,type,full,callback) {
	        		var rs = '<span title="'+data +'">'+ (data.length > 20 ? data.substr(0,20) + "......" : data) +'</span>';
	        		
	        		if(full.contentType == 2 && full.contentUrl != null & full.contentUrl != ""){
	        			rs = " <a href=\"" + full.contentUrl + "\" target=\"_blank\">" + rs + "</a>";
	        		}
	        		return rs;
	        	}
	        },
	        {"data":'contentColumnName',className:'hidden-xs'},
	        {"data":'contentType',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		var rs = "";
	        		if(data == 1) {
	        			rs = "<i class=\"fa fa-file-text text-success\" title=\"本站内容\"></i>";
	        		} else {
	        			rs = "<i class=\"fa fa-link text-info\" title=\"外部链接\"></i>";
	        		}
	        		return rs;
	        	}
	        },
	        /*{"data":'contentUrl',className:'hidden-xs',
	        	"render":function(data,type,full,callback) {
	        		if(data != null & data != ""){
	        			return "<a href=\"" + data + "\" target=\"_blank\">" + data + "</a>";
	        		}
	        		return data;
	        	}
	        },*/
	        {"data":'contentState',
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
		operBtnColumn : 7,
		operBtns : [
			{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
			{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit",
				condition:function(data, type, row){
					//当公告的状态为已发布时，不显示修改按钮
					if(data.contentState == 2){
						return false;
					}
					return true;
				}
			},
			{
				title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o",
				condition:function(data, type, row){
					//当公告的状态为已发布时，不显示删除按钮
					if(data.contentState == 2){
						return false;
					}
					return true;
				}
			}
		]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	//栏目数据转换成bootstrap-treeview控件的数据格式
	function column2Tree(data,nodeId){
		var treeArray = new Array();
		for(var i in data){
			var node= {
				id : data[i].columnId,
				text : data[i].columnName,
				icon : "text-success fa fa-list",
				selectedIcon : "text-default fa fa-list",
				state : {
					checked:false,
				    expanded:false,
				    selected:data[i].columnId == nodeId ? true : false
				}
			};
			
			var childNodes = column2Tree(data[i].childColumnList,nodeId);
			if(childNodes.length > 0){
				node.nodes = childNodes;
			}
			treeArray[i] = node;
		}
		return treeArray;
	}
	//获取树节点
	function getTreeNode(data,nodeId){
		var tnode;
		for(var i in data){
			var node = data[i];
			if(node.id == nodeId){
				tnode = node;
				break;
			}
			if(node.nodes != null && node.nodes.length > 0){
				tnode = getTreeNode(node.nodes,nodeId);
				break;
			}
		}
		return tnode;
	}
	
	//初始化栏目树下拉菜单
	function initColumnTree(content,nodeId){
		$.ajax({
    		url : basePath + "cms/queryColumnTree",
    		data : {
    			columnParentId : 0
    		},
    		type : 'POST',
    		async : true, // 改异步为同步  
    		dataType : 'json',
    		success : function(data) {
    			var treeData = column2Tree(data,nodeId);
    			var firstNode = {
					id : 0,
					text : "请选择相应选项",
					state : {
						checked:false,
					    expanded:false,
					    selected:nodeId == 0 ? true : false
					}
				};
    			treeData.unshift(firstNode);
    			content.find("#columnTree").treeview({
    				data: treeData,
    				onNodeSelected:function(event, data) {
    					//console.log(data);
    					//将当前查询form的columnParentId设置为当前选择的栏目的Id
    					content.find("input[name='contentColumnId']").val(data.id);
    					content.find("input[name='contentColumnName']").val(data.text);
    					content.find("input[name='contentColumnName']").next().hide();
    				}
    			});
    			//var selectedNode = getTreeNode(treeData,content.find("input[name='contentColumnId']").val());
    			//content.find("#columnTree").treeview('selectNode', [selectedNode, { silent: true}]);
    		}
    	});
	}
	//初始化查询表单中的所属栏目下拉树
	initColumnTree(tableOption.form);
	tableOption.form.find("input[name='contentColumnName']").click(function() {
		$(this).next().toggle();
	});
	
	
	//初始化表单校验函数，当打开增加/修改窗口时，调用该方法，初始化校验
	function initFormValidator(formId){
		$("#" + formId).validate({
			onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
			ignore:"",//在校验时，框架不对对隐藏的元素进行验证，将ignore设置成""或者[],框架将不会对任何元素呼略校验，从而实现对隐藏元素的校验
			rules: {
				contentColumnName: {
					treeSelectRequired: true
				},
				contentTitle: {
					required: true
				},
				contentBody:{
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
			data : {contentId:item["contentId"], contentState:state},
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
		tableOption.columnClass = "col-md-12";
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
	    		UE.delEditor("contentBody");
	    		//初始化ueditor控件
	    		ue = UE.getEditor('contentBody');
	    		
	    		//初始化栏目树形菜单
	    		initColumnTree(content);
	    		//查询form中的栏目名称添加click事件，当点击的时候显示/隐藏栏目树下拉框
	    		content.find("input[name='contentColumnName']").click(function() {
	    			$(this).next().toggle();
	    		});
	    		
	    		//初始化内容类型下拉框change事件
	    		content.find("select[name='contentType']").change(function () {  
	                var selectedVal = $(this).children('option:selected').val();  
	                if (selectedVal == "2") {  
	                	var row = content.find("input[name='contentUrl']").closest(".row");
	                	row.removeClass("hidden");
	                	content.find("input[name='contentUrl']").rules("add",{required:true});
	                	content.find("#" +tableOption.addFormId).valid();
	                } else {
	                	content.find("input[name='contentUrl']").val("");
	                	var row = content.find("input[name='contentUrl']").closest(".row");
	    	    		content.find("input[name='contentUrl']").rules("remove","required");
	    	    		content.find("input[name='contentUrl']").closest(".form-group").removeClass("has-error");
	    	    		content.find("input[name='contentUrl']").parent().next("em").remove();
	    	    		row.addClass("hidden");
	                }  
	            });
	    		//初始化表单校验
	    		initFormValidator(tableOption.addFormId);
	    	}
	    	//,function(){
	    		//在窗口关闭的时候，销毁ueditor
	    		//if(typeof(UE.getEditor("contentContent")) !='undefined'){
	    	    //	UE.getEditor("contentContent").destroy();
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
        var params = {contentId:item.contentId};
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
        tableOption.columnClass = "col-md-12";
        var jcUpdate = updateData(tableOption,item,
        	//以下三个回掉函数可自定义
        	//此方法在窗口加载内容完成后调用，在此方法中，可执行一些初始化的操作
    		function(updateForm,item,content){
        		//此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
        		fillForm(updateForm,item);
        		/***************初始化ueditor******************/
        		//窗口打开后 先删除之前初始化的ueditor，再重新初始化，否则会渲染失败
	    		UE.delEditor("contentBody");
	    		//初始化ueditor控件
	    		var ue = UE.getEditor('contentBody');
	    		
	    		ue.ready(function() {
	    		    //设置编辑器的内容
	    			var contentBody = item["contentBody"];
	    		    ue.setContent(contentBody);
	    		});
	    		//初始化表单校验
	    		initFormValidator(tableOption.updateFormId);
	    		//如果是外部链接，则显示URL
	        	if(item.contentType == 2){
	        		content.find("input[name='contentUrl']").closest(".row").removeClass("hidden");
	        	}
	        	//初始化所属栏目下拉树
	        	initColumnTree(content,content.find("input[name='contentColumnId']").val());
	        	//查询form中的栏目名称添加click事件，当点击的时候显示/隐藏栏目树下拉框
	    		content.find("input[name='contentColumnName']").click(function() {
	    			$(this).next().toggle();
	    		});
	    		
	    		//初始化内容类型下拉框change事件
	    		content.find("select[name='contentType']").change(function () {  
	                var selectedVal = $(this).children('option:selected').val();  
	                if (selectedVal == "2") {  
	                	var row = content.find("input[name='contentUrl']").closest(".row");
	                	row.removeClass("hidden");
	                	content.find("input[name='contentUrl']").rules("add",{required:true});
	                	content.find("#" +tableOption.updateFormId).valid();
	                } else {
	                	content.find("input[name='contentUrl']").val("");
	                	var row = content.find("input[name='contentUrl']").closest(".row");
	    	    		content.find("input[name='contentUrl']").rules("remove","required");
	    	    		content.find("input[name='contentUrl']").closest(".form-group").removeClass("has-error");
	    	    		content.find("input[name='contentUrl']").parent().next("em").remove();
	    	    		row.addClass("hidden");
	                }  
	            });
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
        item.titleSuffix = "内容" + item.contentId;
        var jcDetail = detailData(tableOption,item
        		,function(detailForm,item,content){
		        	//此处可自定义，也可使用以下默认的fillForm函数自动回显数据
		        	fillForm(detailForm,item);
		        	content.find("#contentBody").append(item["contentBody"]);
		        	
		        	//如果是外部链接，则显示URL
		        	if(item.contentType == 2){
		        		content.find("#contentUrl").closest(".row").removeClass("hidden");
		        		content.find("#contentUrl").attr("href",item.contentUrl)
		        		content.find("#contentUrl").text(item.contentUrl);
		        	}
		        });
    });
});