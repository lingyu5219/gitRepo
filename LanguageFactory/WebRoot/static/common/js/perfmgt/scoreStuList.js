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
		}, {
			"data" : null
		} ],
		// 操作按钮所在列
		operBtnColumn : 10,
		
		operBtns:[
			{btnClass:"btn-success detailRow",iconClass:"fa-bars"}
		]
	};
	// 初始化表格控件
	var dataTable = initDataTable(tableOption);

	// 查询按钮
	$("#btn-query").on(
			"click",
			function() {
				dataTable.draw();
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
