var dataTable;
$(function () {
	var tableOption={
			queryUrl : basePath + "syllabus/loadSyllabus",
			addUrl   : basePath + "syllabus/addSyllabus",
			delUrl : basePath + "syllabus/deleteSyllabus",
			detailPageUrl : basePath + 'forward?page=edumgt/syllabusAdd',
			updatePageUrl : basePath + 'forward?page=edumgt/mgtSyllabusModify',
			updateUrl : basePath + "syllabus/updateSyllabus",
			table : $('#dataListTable'),
			form : $("#queryForm"),
			updateFormId : "updateForm",
			detailFormId : "addSylabus",
			columns : [
		        {"data":null,width:"10px"},
		        {"data":'id',visible:false},
		        {"data":'yearObj.description'},
		        {"data":'semester.description'},
		        {"data":'batch.batchName',
		        	"render":function(data,type,full,callback){
		        		return "<a href=\"#\" class=\"toSylItem\" data-toggle=\"modal\">"+data+"</a>";
		        	}
		        },
		        {"data":'beginTime' ,
		        	"render":function(data,type,full,callback){
		        		return covertDate(data); 
		        	} 	
		        },
		        {"data":'endTime' ,
		     		"render":function(data,type,full,callback){
		     			return covertDate(data);
		     		} 
		        },
		        {"data":null}
		    ],
			operBtnColumn : 7
	};
	dataTable = initDataTable(tableOption);
	$("#btn-query").on("click",function(){
		$("#query").val("biwozhemexie");
		dataTable.draw(true);
	});
	tableOption.table.on("click",".delRow",function(){
		var item = dataTable.row($(this).closest('tr')).data();
		var params = {id:item.id};
		delDataTableItem(dataTable,tableOption,params,function(data){
			if(data.status==1){
				dataTable.draw(true);
			}
			tcAlert(data.info,2000);
		});
	});
	tableOption.table.on("click",".detailRow",function(){
		var item = dataTable.row($(this).closest('tr')).data();
		var jcDetail = detailData(tableOption,item,function(detailFormId,item){
			detailAndEditLoadOp(detailFormId,item,0)
		});
	});
	tableOption.table.on("click",".editRow",function(){
		var item = dataTable.row($(this).closest('tr')).data();
		var jcUpdate = updateData(tableOption,item,
				function (updateFormId,item){
					detailAndEditLoadOp(updateFormId,item)
				},
				function (){
						var param = formToJson($("#"+tableOption.updateFormId));
						if(param.beginTime==null||param.beginTime==""||param.endTime==null||param.endTime==""){
							throw "请选择日期";
						}else if(param.beginTime>=param.endTime){
							throw "开始日期大于结束日期!";
						}
						return param;												
				},
				function(data){
					if(data.name=="error"){
						tcAlert(data.value);
					}else{
						dataTable.draw(true);
		            	tcConfirm(data.value,"是否返回？",function(){
		            		jcUpdate.close();
		            	});
					}

				}
		);
	});
	tableOption.table.on("click",".toSylItem",function(){
		var item = dataTable.row($(this).closest('tr')).data();
		var id = item.id;
		if(id==""||id==null||id=="undefined"){
			tcAlert("错误操作!",2000);
		}else{
			window.location.href=basePath+"syllabus/mgtSybitemList?syllabusId="+id;
		}
	});	 
	$("#btn-add").on("click",function(){
		var jcAdd = $.dialog({
			title:'添加课程表',
			content:'url:'+basePath+'/forward?page=edumgt/syllabusAdd',
			columnClass:'col-md-8 col-md-offset-2',
			onContentReady:function(){
				loadYear(tableOption.detailFormId)
				 $('.datepicker').datepicker({
					 format: 'yyyy-mm-dd',
					 language: "zh-CN",
				       weekStart: 1,
				       todayHighlight: true,
				       autoclose: true
				 }).on('changeDate',function(e) {
					 $("#hiddenDate").hide();
						var beginTime = $("#beginTime").val();
						var endTime = $("#endTime").val();
						if(beginTime==null||beginTime==''||endTime==null||endTime==''){
							return;
						}
						if(beginTime>endTime){
							$("#hiddenDate").html('开始时间大于结束时间!');
							$("#hiddenDate").show();
						}
				 } );
				this.$content.find("input.btn-save").click(function(){
					var check = checkSyllabus();
					if(check!='error'){
						var param = formToJson($("#addSylabus"));
						$.ajax({
							type:'post',
							url:tableOption.addUrl,
							data:param,
							dataType:'json',
							cache:'false',
							success:function(data){
								if(data.name=='error'){
									tcAlert(data.value)
									$("#add").removeAttr("disabled");
									return;
								}
								window.location.href= basePath+"/syllabus/mgtSyllabusList";
							},
							error:function(err){
								tcAlert(err.status + "——" + err.statusText);
								$("#add").removeAttr("disabled");
							}
						});						
					}else{
						tcAlert("填写信息有误，请重新填写!");
						$("#add").removeAttr("disabled");
					}
				});
			}
		});
	});
	detailAndEditLoadOp("queryForm");
});
function syllabuSfillForm(formId,item,isHide){
	var formObj = $("#" + formId);
	var formArr = formToJson(formObj);
	//$("#updateForm").find("input[name=userName]")
	for(key in formArr) {
		var value;
		var mulValue = key.split(".");
		if(mulValue!=null&&mulValue.length!=1){
			for (var i = 1; i <=mulValue.length; i++) {
				if(i==1){
					value = item[mulValue[i-1]];
				}else{
					value = value[mulValue[i-1]];
				}
			}
		}else{
			value = item[key];			
		}
		//alert(key);
		var formItem;
		if(mulValue!=null&&mulValue.length!=1){
			if (formObj.find("select[name^="+mulValue[0]+"]").length > 0&&formObj.find("select[name$="+mulValue[mulValue.length-1]+"]").length > 0){
				formItem = formObj.find("select[name^="+mulValue[0]+"]");
			} else if(formObj.find("textarea[name^="+mulValue[0]+"]").length > 0&&formObj.find("textarea[name$="+mulValue[mulValue.length-1]+"]").length > 0){
				formItem = formObj.find("textarea[name^="+mulValue[0]+"]");
			} else {
				formItem = formObj.find("input[name^="+mulValue[0]+"]");
			}
		}else{
			if (formObj.find("input[name="+key+"]").length > 0){
				formItem = formObj.find("input[name="+key+"]");
			} else if(formObj.find("textarea[name="+key+"]").length > 0){
				formItem = formObj.find("textarea[name="+key+"]");
			} else {
				formItem = formObj.find("select[name="+key+"]");
			}			
		}

		var tagLable = formItem.get(0).tagName;
		var type = formItem.attr('type');
		if(tagLable=='INPUT'){
			if(type=='radio'){
				formItem.attr('checked',formItem.val()==value);
			}else if(type=='checkbox'){
				var arr = value.split(',');
				for(var i =0;i<arr.length;i++){
					if(formItem.val()==arr[i]){
						formItem.attr('checked',true);
						break;
					}
				}
			}else{
				var clazz = formItem.attr("class");
				if(clazz=="datepicker"){
					formItem.datepicker({
						 format: 'yyyy-mm-dd',
						 language: "zh-CN",
					       weekStart: 1,
					       todayHighlight: true,
					       autoclose: true
					 }).on('changeDate',function(e) {
						 $("#hiddenDate").hide();
							var beginTime = $("#beginTime").val();
							var endTime = $("#endTime").val();
							if(beginTime==null||beginTime==''||endTime==null||endTime==''){
								return;
							}
							if(beginTime>endTime){
								$("#hiddenDate").html('开始时间大于结束时间!');
								$("#hiddenDate").show();
							}
					 } );
					formItem.datepicker("setDate",new Date(value.time));
				}else{
					formItem.val(value);					
				}
			}
		}else if(tagLable=='SELECT' || tagLable=='TEXTAREA'){
			formItem.val(value);
		}
		if(isHide==0){
			formItem.attr("disabled",true);
		}
	}
	if(isHide==0){
		$("#save").hide();		
	}
}

function checkSyllabus(){
	$("#hiddenBeginTime").hide();
	$("#hiddenEndTime").hide();
	$("#add").attr("disabled","disabled");
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var checkResult;
	if(beginTime==""||beginTime==null||endTime==""||endTime==null){
		$("#hiddenDate").html('必填字段!');
		$("#hiddenDate").show();
		checkResult ='error';
	}
	if(beginTime>endTime){
		$("#hiddenDate").html('开始时间大于结束时间!');
		$("#hiddenDate").show();
		$("#add").removeAttr("disabled");
		checkResult ='error';
	}
	return checkResult;
}
function detailAndEditLoadOp(formId,item,isHide){
	loadYear(formId,item,isHide);
}
function loadYear(formId,item,isHide){
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
    	loadSemester(formId,item,isHide);
	}
	var error = function(err){
    	tcAlert(err.status + "——" + err.statusText);
	}
	toAjax(method,url,datas,success,error,yearSelect);	
}
function loadSemester(formId,item,isHide){
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
    	if(item!=null&&item!="undefined"){
    		syllabuSfillForm(formId,item,isHide);
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