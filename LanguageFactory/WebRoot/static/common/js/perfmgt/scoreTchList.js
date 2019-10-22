$(function() {
	var tableOption = {
		// ajax查询数据的http请求url
		queryUrl : basePath + "/perfmgt/queryScoreList",
		//ajax删除数据的http请求url
		delUrl : basePath + "perfmgt/delScore",
		// 查看页面
		detailPageUrl : basePath + 'forward?page=perfmgt/scoreDetail',
		// 修改页面
		updatePageUrl : basePath + 'forward?page=perfmgt/scoreModify',
		// ajax修改数据的http请求url
		updateUrl : basePath + "perfmgt/updateScore",
		// Table对象
		table : $('#scoreListTable'),
		// 查询表单Form对象
		form : $("#queryForm"),
		// 查看功能的表单formID
		detailFormId : "detailForm",
		// 修改功能的表单formID
		updateFormId : "updateForm",
		// 表格列
		columns : [// 对应thead里面的序列
		{
			"data" : null,
			width : "10px"
		}, {
			"data" : 'scoreId',
			visible : false
		}, {
			"data" : 'yearName'
		}, {
			"data" : 'termName'
		}, {
			"data" : 'majorName'
		}, {
			"data" : 'batchName'
		}, {
			"data" : 'scorestudentId'
		}, {
			"data" : 'stuName'
		},{
			"data" : 'courseName'
		}, {
			"data" : 'score'
		},{
			"data" : null
		} ],
		// 操作按钮所在列
		operBtnColumn : 10
	};
	// 初始化表格控件
	var dataTable = initDataTable(tableOption);

	// 查询按钮
	$("#btn-query").on(
			"click",
			function() {
				if ($("select[name='yearName']").val() === ""
						&& $("select[name='termName']").val() !== "") {
					tcAlert("请选择年级");
				} else if ($("select[name='termName']").val() === ""
						&& $("select[name='yearName']").val() !== "") {
					tcAlert("请选择学期");
				} else {
					// 查询后不需要保持分页状态，回首页
					dataTable.draw();
				}

			});

	// 修改
	tableOption.table.on("click", ".editRow", function() {
		// 要修改哪一行数据对象
		var item = dataTable.row($(this).closest('tr')).data();
		var jcUpdate = updateData(tableOption, item,
		// 以下三个回掉函数可自定义
		function(updateForm, item) {
			// 此处代码可自定义，自定义实现数据回显，也可使用以下fillForm函数自动回显数据
			fillForm(updateForm, item);
		}, function() {
			// 获取修改表单数据
			var param = formToJson($("#" + tableOption.updateFormId));
			return param;
		}, function(data) {
			// 修改成功后提示信息
			if (data.status == 1) {
				// 刷新结果列表
				dataTable.draw(true);
			}
			// 提示信息
			tcConfirm(data.info, "是否返回？", function() {
				jcUpdate.close();
			});
		});
	});
	
	
    //删除
    tableOption.table.on("click",".delRow",function() {
    	//要删除那一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        //向服务器发送删除请求的参数，使用post方式
        var params = {scoreId:item.scoreId};
        //调用删除方法，向服务器发送请求
        delDataTableItem(dataTable,tableOption,params,function(data){
        	if(data.status == 1){
            	dataTable.draw(true);
            }
            tcAlert(data.info, 6000);
        });
    });

	// 查看
	tableOption.table.on("click", ".detailRow", function() {
		// 要查看哪一行数据对象
		var item = dataTable.row($(this).closest('tr')).data();
		var jcDetail = detailData(tableOption, item,
				function(detailForm, item) {
					// 此处可自定义，也可使用以下默认的fillForm函数自动回显数据
					fillForm(detailForm, item);
				})
	});

});
