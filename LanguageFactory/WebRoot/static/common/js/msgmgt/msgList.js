$(function () {
	var tableOption = {
		//ajax查询数据的http请求url
		queryUrl : basePath + "/msgmgt/queryMsgList",
		//查看页面
		detailPageUrl : basePath + 'msgmgt/msgDetail',
		//Table对象
		table : $('#dataListTable'),
		//查询表单Form对象
		form : $("#queryForm"),
		//查看功能的表单formID
		detailFormId : "detailForm",
		//表格列
		columns : [//对应thead里面的序列
	        {"data":null,width:"10px"},
	        {"data":'noticeTitle',
	        	"render":function(data,type,full,callback) {
	        		if(full.readStatus == 0){
                        return "<span class=\"label label-warning\"><i class=\"fa fa-envelope-o\"></i> 未读</span> <strong>" + data + "</strong>";
                    } else {
                    	return data;
                    }
	            }
	        },
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
		operBtnColumn : 3,
		operBtns : [{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"}]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
	
    //查询按钮
    $("#btn-query").on("click", function () {
        dataTable.draw();//查询后不需要保持分页状态，回首页
    });
    
    //查看
    tableOption.table.on("click",".detailRow",function() {
    	//要查看哪一行数据对象
        var item = dataTable.row($(this).closest('tr')).data();
        $.standardPost(tableOption.detailPageUrl,{"noticeId":item.noticeId, "readStatus":item.readStatus});
//        window.location.href = tableOption.detailPageUrl + "?noticeId=" + item.noticeId;
    });
});