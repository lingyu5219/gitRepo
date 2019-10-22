$(function() {
function draw(){
		
		setTimeout(function(){
			var signIn = 0; // 签到的和
			var late = 0; // 迟到的和
			var leave = 0; // 请假的和
			$('#attendListTable tr').each(function() {
				$(this).find('td:eq(5)').each(function() {
					signIn += parseFloat($(this).text());
				});
			});
			$('#attendListTable tr').each(function() {
				$(this).find('td:eq(6)').each(function() {
					late += parseFloat($(this).text());
				});
			});
			$('#attendListTable tr').each(function() {
				$(this).find('td:eq(7)').each(function() {
					leave += parseFloat($(this).text());
				});
			});
			var data = [ {
				label : "迟到",
				data : late,
				color : "#FF0000"
			}, {
				label : "签到",
				data : signIn,
				color : "#00CD66"
			}, {
				label : "请假",
				data : leave,
				color : "#FFA500"
			} ];
			$.plot($("#pie"), data, {
				series : {
					pie : {
						show : true
					}
				},
				legend : {
					show : false
				}
			});
		},1000);
		
	}
	
	var tableOption = {
		// ajax查询数据的http请求url
		queryUrl : basePath + "/perfmgt/queryTchAttend",
		// Table对象
		table : $('#attendListTable'),
		// 查询表单Form对象
		form : $("#queryForm"),
		// 表格列
		columns : [// 对应thead里面的序列
		{
			"data" : null,
			width : "10px"
		}, {
			"data" : 'userId',
			visible : false
		}, {
			"data" : 'yearName'
		}, {
			"data" : 'majorName'
		}, {
			"data" : 'batchName'
		}, {
			"data" : 'stuName'
		}, {
			"data" : 'signinDays'
		}, {
			"data" : 'lateDays'
		}, {
			"data" : 'leaveDays'
		}, {
			"data" : 'totalDays'
		}, ]
	};
	// 初始化表格控件
	var dataTable = initDataTable(tableOption);
	
	draw();

	// 查询按钮
	$("#btn-query").on("click", function() {
		dataTable.draw();// 查询后不需要保持分页状态，回首页

		draw();
	});
	
	

});
