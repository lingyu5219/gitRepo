$(function () {
	var tableOption = {
		queryUrl : basePath + "syllabus/getThisSyllabusWithJson",
		table : $('#dataListTable'),
		form : $("#queryForm"),
		columns : [
	        {"data":null,width:"10px"},
	        {"data":'monday.display'},
	        {"data":'tuesday.display'},
	        {"data":'wednesday.display'},
	        {"data":'thursday.display'},
	        {"data":'friday.display'},
	        {"data":'saturday.display'},
	        {"data":'sunday.display'}
	    ]
	};
	//初始化表格控件
	var dataTable = initDataTable(tableOption);
    
    //查询按钮
    $("#btn-query").on("click", function () {
    	$("#query").val("biwozhemexie");
    	var tempYear = $("#tempYear option:selected").text();
    	var tempSylQuarter = $("#tempSylQuarter option:selected").text();
    	var tempBatch = $("#tempBatch option:selected").text();
    	$("#syllabusHeader").text(tempYear+"年 "+tempSylQuarter+"季 "+tempBatch+"班");
        dataTable.draw(true);//查询后不需要保持分页状态，回首页
    });
    detailAndEditLoadOp("queryForm");
});
function detailAndEditLoadOp(formId){
	loadYear(formId);
	loadSemester(formId);
}
function loadYear(formId){
	var formObj = $("#" + formId);
	var yearSelect = formObj.find("select[name^=yearObj]");
	if(yearSelect.length==0){
		yearSelect = formObj.find("select[name=tempYear]");
	}
	var method = 'post';
	var url = basePath + "year/queryYearList";
	var datas = {"start":"0","length":"100"};
	var success = function(vdata,yearSelect){
    	var yearSelects = vdata.data;
    	for(var i=0;i<yearSelects.length;i++){
    		yearSelect.append("<option value='"+yearSelects[i].yearId+"'>"+yearSelects[i].description+"</option>");
    	}
	}
	var error = function(err){
    	tcAlert(err.status + "——" + err.statusText);
	}
	toAjax(method,url,datas,success,error,yearSelect);	
}
function loadSemester(formId){
	var formObj = $("#" + formId);
	var sylQuarter = formObj.find("select[name^=semester]");
	if(sylQuarter.length==0){
		sylQuarter = formObj.find("select[name=tempSylQuarter]");
	}
	var method = 'post';
	var url = basePath + "semester/querySemesterList";
	var datas = {"start":"0","length":"100"};
	var success = function(vdata,sylQuarter){
    	var sylQuarters = vdata.data;
    	for(var i=0;i<sylQuarters.length;i++){
    		sylQuarter.append("<option value='"+sylQuarters[i].semesterId+"'>"+sylQuarters[i].description+"</option>");
    	}
	}
	var error = function(err){
    	tcAlert(err.status + "——" + err.statusText);
	}
	toAjax(method,url,datas,success,error,sylQuarter);	
}
function toAjax(methed,url,datas,success,error,param){
	$.ajax({
    	type: methed,
        url: url,
        dataType: "json",
        data:datas,
        cache: "false",
        success: function(vdata){
        	success(vdata,param);
        },
        error: function(err){
        	error(err);
        }
    });
}