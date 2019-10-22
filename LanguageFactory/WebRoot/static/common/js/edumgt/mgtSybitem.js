var dataTable;
$(function () {
	var tableOption={
			queryUrl : basePath + "syllabus/loadSybitem?syllabusId="+syllabusId,
			addUrl   : basePath + "syllabus/addSybitem?syllabusId="+syllabusId,
			delUrl : basePath + "syllabus/delSybitem?syllabusId="+syllabusId,
			updateUrl : basePath + "syllabus/updateSybitem?syllabusId="+syllabusId,
			detailPageUrl : basePath + 'forward?page=edumgt/sybitemAdd',
			updatePageUrl : basePath + 'forward?page=edumgt/mgtSybitemModify',
			table : $('#dataListTable'),
			form : $("#queryForm"),
			updateFormId : "updateForm",
			detailFormId : "addSybitem",
			columns : [
		        {"data":null,width:"10px"},
		        {"data":'id',visible:false},
		        {"data":'major.majorName'},
		        {"data":'week'},
		        {"data":'date'},
		        {"data":'section'},
		        {"data":'room.roomName'},
		        {"data":'staff.staffName'},
		        {"data":null}
		    ],
			operBtnColumn : 8
	};
	dataTable = initDataTable(tableOption);
	$("#btn-query").on("click",function(){
		dataTable.draw();
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
			detailAndEditLoadOp(detailFormId,item,0);
		});
	});
	tableOption.table.on("click",".editRow",function(){
		var item = dataTable.row($(this).closest('tr')).data();
		var jcUpdate = updateData(tableOption,item,
				function (updateFormId,item){
					detailAndEditLoadOp(updateFormId,item);			
				},
				function (){
						var param = formToJson($("#"+tableOption.updateFormId));
						if(param.begin==null||param.begin==""||param.end==null||param.end==""){
							throw "请填写起始日期!";
						}else if(param.begin>param.end){
							throw "开始周次大于截止周次";
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
	$("#btn-add").on("click",function(){
		var jcAdd = $.dialog({
			title:'添加每日课程',
			content:'url:'+basePath+'/forward?page=edumgt/sybitemAdd',
			columnClass:'col-md-8 col-md-offset-2',
			onContentReady:function(){
				loadOption(this.$content);
				this.$content.find("input.btn-save").click(function(){
					var check = checkSyllabus(tableOption.detailFormId);
					if(check!='error'){
						var param = formToJson($("#addSybitem"));
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
								window.location.href= basePath+"syllabus/mgtSybitemList?syllabusId="+syllabusId;
							},
							error:function(err){
								tcAlert(err.status + "——" + err.statusText);
								$("#add").removeAttr("disabled");
							}
						});						
					}
				});
			}
		});
	});
});
function weekInput(event){
	var week = event.target.value;
	if(week==""||week==null){
		$("#hiddenWeek").html('必填字段!');
		$("#hiddenWeek").show();
	}else if(isNaN(week)){
		$("#hiddenWeek").html('请检查填写的是否是数字!');
		$("#hiddenWeek").show();
	}else{
		$("#hiddenWeek").hide();
	}
}
function checkSyllabus(){
	$("#add").attr("disabled","disabled");
	var date =$("#date").val();
	var coursesId =$("#coursesId").val();
	var begin = $("#begin").val();
	var end = $("#end").val();
	var section = $("#section").val();
	var classroomId = $("#classroomId").val();
	var teacherId = $("#teacherId").val();
	var checkResult;
	if(begin==""||begin==null||end==""||end==null){
		$("#hiddenWeek").html('必填字段!');
		$("#hiddenWeek").show();
		$("#add").removeAttr("disabled");
		checkResult ='error';
	}else if(isNaN(begin)||isNaN(end)){
		$("#hiddenWeek").html('请检查填写的是否是数字!');
		$("#hiddenWeek").show();
		$("#add").removeAttr("disabled");
		checkResult ='error';
	}
	return checkResult;
}
function sybitemSfillForm(formObj,item,isHide){
	var formArr = formToJson(formObj);
	var time = item["week"].split("-");
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
			if(mulValue=="begin"){
				value = time[0];
			}else if(mulValue=="end"){
				value = time[1];
			}else{
				value = item[key];							
			}
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
				formItem.val(value);
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
function detailAndEditLoadOp(formId,item,isHide){
	var formObj = $("#" + formId);
	loadStaff(formObj,item,isHide);	
}
function loadOption(content){
	loadStaff(content);
}
function loadStaff(content,item,isHide){
	var staff = content.find("select[name^=staff]");
	var method = 'post';
	var url = basePath + "hrmgt/queryStaffList";
	var datas = {"start":"0","length":"100"};
	var success = function(vdata,staff){
    	var staffs = vdata.data;
    	for(var i=0;i<staffs.length;i++){
    		staff.append("<option value='"+staffs[i].staffId+"'>"+staffs[i].staffName+"</option>");
    	}
    	loadRoom(content,item,isHide)
	}
	var error = function(err){
    	tcAlert(err.status + "——" + err.statusText);
	}
	toAjax(method,url,datas,success,error,staff);
}
function loadMajor(content,item,isHide){
	var major = content.find("select[name^=major]");
	var method = 'post';
	var url = basePath + "major/queryMajor";
	var datas = {"start":"0","length":"100"};
	var success = function(vdata,major){
    	var majors = vdata.data;
    	for(var i=0;i<majors.length;i++){
    		major.append("<option value='"+majors[i].majorId+"'>"+majors[i].majorName+"</option>");
    	}
    	loadRoom(content,item,isHide);
	}
	var error = function(err){
    	tcAlert(err.status + "——" + err.statusText);
	}
	toAjax(method,url,datas,success,error,major);	
}
function loadRoom(content,item,isHide){
	var room = content.find("select[name^=room]");
	var method = 'post';
	var url = basePath + "room/queryRoomList";
	var datas = {"start":"0","length":"100"};
	var success = function(vdata,room){
    	var rooms = vdata.data;
    	for(var i=0;i<rooms.length;i++){
    		room.append("<option value='"+rooms[i].roomId+"'>"+rooms[i].roomName+"</option>");
    	}
    	if(item!=null&&item!="undefined"){
    		sybitemSfillForm(content,item,isHide);
    	}
	}
	var error = function(err){
    	tcAlert(err.status + "——" + err.statusText);
	}
	toAjax(method,url,datas,success,error,room);		
}
//ajax方法封装
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