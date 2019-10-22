//GUID
function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

//将字符串转换成json对象
function parseStr2Json(str){
	try{    
		return eval("(" + str + ")");
	}
	catch(er){
		return false;
	}
}
//字符串长度（区分中英文）
String.prototype.gblen = function() {  
	var len = 0;  
	for (var i=0; i<this.length; i++) {
		if (this.charCodeAt(i)>127 || this.charCodeAt(i)==94) {  
	        len += 2;  
	    } else {  
	        len ++;  
	    }  
	}  
	return len;  
}

function subString(str, len, hasDot) {
    var newLength = 0;
    var newStr = "";
    var chineseRegex = /[^\x00-\xff]/g;
    var singleChar = "";
    var strLength = str.replace(chineseRegex, "**").length;
    for (var i = 0; i < strLength; i++) {
        singleChar = str.charAt(i).toString();
        if (singleChar.match(chineseRegex) != null) {
            newLength += 2;
        }
        else {
            newLength++;
        }
        if (newLength > len) {
            break;
        }
        newStr += singleChar;
    }

    if (hasDot && strLength > len) {
        newStr += "...";
    }
    return newStr;
}

//弹出窗口显示图片
function showPic(name, url){
	var img = new Image();  
	img.src = basePath + url;
	img.onload = function(){
		$.dialog({
			title : name,
			content : "<img width=\"" + img.width + "\" height=\"" + img.height + "\" src=\"" + basePath + url + "\"/>",
			//columnClass : 'col-md-8 col-md-offset-2',
			useBootstrap : false,
			boxWidth:"",
			backgroundDismiss: true,
			onOpenBefore: function () {
				this.$jconfirmBox.css("display","table");
	        }
		});
	}
}

//封装jquery-confirm的alert,指定相应的毫秒数，该窗口会在指定时间内自动关闭
function tcAlert(info, msOrCallback) {
	var option = {
		title : info,
		content : false,
		buttons : {
			"确定" : function() {
				if (msOrCallback && typeof (msOrCallback) == "function") {
					msOrCallback();
				}
			}
		}
	};
	if (msOrCallback != null && msOrCallback != "undefined" && $.isNumeric(msOrCallback)) {
		option.autoClose = '确定|' + msOrCallback;
	}
	$.alert(option);
}

function tcAlert2(title, info, msOrCallback) {
	var option = {
		title : title,
		content : info,
		buttons : {
			"确定" : function() {
				if (msOrCallback && typeof (msOrCallback) == "function") {
					msOrCallback();
				}
			}
		}
	};
	if (msOrCallback != null && msOrCallback != "undefined" && $.isNumeric(msOrCallback)) {
		option.autoClose = '确定|' + msOrCallback;
	}
	$.alert(option);
}
/*function tcAlert(info, callback) {
	var option = {
		title : info,
		content : false,
		buttons : {
			"确定" : function() {
				
			}
		}
	};
	$.alert(option);
}*/

//封装jquery-confirm的confirm
function tcConfirm(title, info, confirmCallBack) {
	$.confirm({
		title : title,
		content : info,
		buttons : {
			confirm : {
				text : "确定",
				//btnClass:"btn-red",
				action : function() {
					confirmCallBack();
				}
			},
			cancel : {
				text : "取消",
				action : function() {
				}
			}
		}
	});
}
//ajax成功回调函数
function ajaxAlert(data){
	//data.status: 0操作失败，1成功，2没有登录，3非法/没有操作权限
	if(data.status == 0){
		tcAlert(data.info,5000);
		return false;
    }
	if(data.status == 2){
		tcAlert(data.info,function(){
			window.location.href = basePath + "system/login";
		});
		return false;
    }
	if(data.status == 3){
		tcAlert2(data.url, data.info, 5000);
		return false;
	}
	return true;
}
//ajax错误回调函数
function ajaxErrorAlert(err){
	/*if(err.status == 2){
		tcAlert2(err.status, err.info, function(){
			window.location.href = basePath + "system/login";
		});
	} else {
		tcAlert(err.status);
	}*/
	tcAlert(err.status);
}

//加载JavaScript文件
function loadJs(url, success){
	$.getScript(url, function(response,status) {
		success(response,status);
	});
}
//签到打卡
function signIn(){
	$.ajax({
		type : 'post',
		url : basePath + "atndmgt/signIn",
		dataType : "json",
		cache : "false",
		success : function(data) {
			ajaxAlert(data);
		},
		error : function(err) {
			ajaxErrorAlert(err);
		}
	});
}

//将form表单序列化后得到一个json对象
function formToJson(form) {
	var param = {};
	var formData = form.serializeArray();//把form里面的数据序列化成数组
	formData.forEach(function(e) {
		if (param[e.name] == null || param[e.name] == ""
				|| param[e.name] == "undefined") {
			param[e.name] = e.value;
		} else {
			//针对checkbox有多个值的情况下，转换成 value1,value2,value3的格式
			param[e.name] = param[e.name] + "," + e.value;
		}
	});
	return param;

}

//初始化dataTable表格
function initDataTable(tableOption) {
	//初始化表格控件
	var table = tableOption.table
			.DataTable({
				ajax : {
					type : "post",
					url : tableOption.queryUrl,
					//dataSrc : "data",
					dataSrc : function (json) {
						if(json.data == null){
							ajaxAlert(json);
							json.draw = 1;
							json.recordsTotal = 0;
							json.recordsFiltered = 0;
							return [];
						}
						return json.data;
					},
					data : function(d) {
						var param = {};
						param.draw = d.draw;
						param.start = d.start;
						param.length = d.length;
						var formData = tableOption.form.serializeArray();//把form里面的数据序列化成数组
						formData.forEach(function(e) {
							param[e.name] = e.value;
						});
						return param;//自定义需要传递的参数。
					}
				},
				columns : tableOption.columns,
				//操作按钮
				columnDefs : [ {
					targets : tableOption.operBtnColumn,
					//defaultContent: 
					render : function(data, type, row) {
						//var btnAdd = "<button class='btn btn-success btn-sm detailRow' type='button'><i class='fa fa-bars'></i></button>&nbsp;&nbsp;";
						//var btnUpdate = "<button class='btn btn-primary btn-sm editRow' type='button'><i class='fa fa-edit'></i></button>&nbsp;&nbsp;";
						//var btnDel = "<button class='btn btn-danger btn-sm delRow' type='button'><i class='fa fa-trash-o'></i></button>";
						var btns = tableOption.operBtns;
						//默认的操作按钮：详情、修改、删除
						if (btns == null || typeof (btns) == "undefined" || btns.length == 0) {
							btns = [
								{title:"详情",btnClass:"btn-success detailRow",iconClass:"fa-bars"},
								{title:"修改",btnClass:"btn-primary editRow",iconClass:"fa-edit"},
								{title:"删除",btnClass:"btn-danger delRow",iconClass:"fa-trash-o"}
							];
						}
						//按钮组： 大屏幕下显示， 在超小屏幕(<768px)时隐藏
						var btnGroup = $("<div class=\"btn-group\">");
						if(btns.length > 1){
							btnGroup.addClass("hidden-xs");
						}
						for (var i = 0; i < btns.length; i++) {
							//根据附加条件显示按钮
							if(btns[i].condition && typeof (btns[i].condition) == "function" && !btns[i].condition(data, type, row)){
								continue;
							}
							
							var btn = $("<button type=\"button\" class=\"btn btn-sm\">");
							//设置按钮title
							btn.attr("title",btns[i].title ? btns[i].title : "");
							//设置按钮class类
							if(btns[i].btnClass){
								btn.addClass(btns[i].btnClass);
							}
							//设置按钮图标class类
							if (btns[i].iconClass) {
								var icon = $("<i>");
								icon.addClass("fa").addClass(btns[i].iconClass);
								//大屏幕下默认不显示按钮文字，如果设置显示
								if(btns[i].showText){
									icon.text(btns[i].title ? " " + btns[i].title : "");
								}
								btn.append(icon);
							}
							btnGroup.append(btn);
						}
						//按钮组： 大屏幕下隐藏， 在超小屏幕(<768px)时显示
						var btnGroupVxs = $("<div class=\"btn-group visible-xs-inline-block\">");
						var btnDropdown = $("<button type=\"button\" class=\"btn btn-warning dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
						var btnDropdownIcon = $("<span class=\"caret\"></span>");
						btnDropdown.append(btnDropdownIcon);
						
						var dropdownMenu = $("<ul class=\"dropdown-menu\">");
						for (var i = 0; i < btns.length; i++) {
							//根据附加条件显示按钮
							if(btns[i].condition && typeof (btns[i].condition) == "function" && !btns[i].condition(data, type, row)){
								continue;
							}
							var li = $("<li>");
							var btn = $("<a href=\"javascript:void(0);\">");
							//设置按钮title
							btn.attr("title",btns[i].title ? btns[i].title : "");
							//设置按钮class类
							if(btns[i].btnClass){
								btn.addClass(btns[i].btnClass.split(" ")[1]);
							}
							//设置按钮图标class类
							if (btns[i].iconClass) {
								var icon = $("<i>");
								icon.addClass("fa").addClass(btns[i].iconClass);
								icon.text(btns[i].title ? " " + btns[i].title : "");
								btn.append(icon);
							}
							li.append(btn);
							dropdownMenu.append(li);
						}
						
						btnGroupVxs.append(btnDropdown);
						btnGroupVxs.append(dropdownMenu);
						
						/*
						 <ul class="dropdown-menu">
						    <li><a href="#">Another action</a></li>
						    <li><a href="#">Something else here</a></li>
						    <li role="separator" class="divider"></li>
						    <li><a href="#">Separated link</a></li>
						  </ul>
						 * */
						if(btns.length > 1){
							return btnGroup[0].outerHTML + btnGroupVxs[0].outerHTML;
						}
						return btnGroup[0].outerHTML;
					}
				}]
				/*,
				fnDrawCallback : function() {
					if (tableOption.fnDrawCallback && typeof (tableOption.fnDrawCallback) == "function") {
						var api = this.api();
						tableOption.fnDrawCallback(api);
					}
				}*/
			});
	//选中行
	/*
	tableOption.table.on("click", "tr", function() {
		$(this).toggleClass("active").siblings().removeClass("active");
	});
	*/
	return table;
}

//增加功能封装
function addData(tableOption, getParam, success, readyCallBack, closeCallBack) {
	var jcAdd = $.dialog({
		title : '增加',
		content : 'url:' + tableOption.addPageUrl,
		columnClass : tableOption.columnClass ? tableOption.columnClass : 'col-md-8 col-md-offset-2',
		onContentReady : function() {
			// NOTE: `this.$content` is the jquery object for content.
			//如果没有权限返回json:{url: "forward?page=system/noticeAdd", status: "0", info: "您没有操作权限，请联系管理员"}
			var rsData = parseStr2Json(this.$content.text());
			//如果返回的内容被解析成json对象，则说明没有权限，即rsData!=false,而是以上的json对象
			if(rsData != false){
				//没有权限访问，则直接return
				this.$content.text(rsData.info);
				//return;
			}
			if(!ajaxAlert(rsData)){
				//没有成功，则直接return
				return;
			}
			if (readyCallBack && typeof (readyCallBack) == "function") {
				readyCallBack(this.$content);
			}

			this.$content.find("button.btn-save").click(function() {
				var param = getParam();
				//如果没有校验通过，则中断提交
				if(!param){
					return false;
				}
				//ajax请求插入数据
				$.ajax({
					type : 'post',
					url : tableOption.addUrl,
					data : param,
					contentType : $("#" +tableOption.addFormId).attr("enctype") == "multipart/form-data" ? false : "application/x-www-form-urlencoded",
					processData : $("#" +tableOption.addFormId).attr("enctype") == "multipart/form-data" ? false : true,		
					dataType : "json",
					cache : "false",
					success : function(data) {
						if(!ajaxAlert(data)){
							//没有成功，则直接return
							return;
						}
						success(data);
					},
					error : function(err) {
						ajaxErrorAlert(err);
					}
				});
			});
		},
		onClose: function(){
			if (closeCallBack && typeof (closeCallBack) == "function") {
				closeCallBack();
			}
	    }
	});
	return jcAdd;
}

//删除功能封装
function delDataTableItem(dataTable, tableOption, params, callBack) {
	tcConfirm('确定要删除数据吗？', '删除数据后讲无法恢复，请谨慎操作!', function() {
		$.ajax({
			type : 'post',
			url : tableOption.delUrl,
			data : params,
			dataType : "json",
			cache : "false",
			success : function(data) {
				//如果没有权限返回json:{url: "forward?page=system/noticeAdd", status: "0", info: "您没有操作权限，请联系管理员"}
				/*if(data.status == 0){
					//没有权限访问，则直接return
					tcAlert(data.info);
					return;
				}*/
				//data.status: 0操作失败，1成功，2没有登录，3非法/没有操作权限
				if(!ajaxAlert(data)){
					//没有成功，则直接return
					return;
				}
				callBack(data);
			},
			error : function(err) {
				ajaxErrorAlert(err);
			}
		});
	});
}

//修改功能封装
function updateData(tableOption, item, readyCallBack, getParamCallBack,
		successCallBack,closeCallBack) {
	var jcUpdate = $.dialog({
		title : '修改',
		content : 'url:' + tableOption.updatePageUrl,
		columnClass : tableOption.columnClass ? tableOption.columnClass : 'col-md-8 col-md-offset-2',
		onContentReady : function() {
			//如果没有权限返回json:{url: "forward?page=system/noticeAdd", status: "0", info: "您没有操作权限，请联系管理员"}
			var rsData = parseStr2Json(this.$content.text());
			//如果返回的内容被解析成json对象，则说明没有权限，即rsData!=false,而是以上的json对象
			if(rsData != false){
				//没有权限访问，则直接return
				this.$content.text(rsData.info);
				//return;
			}
			if(!ajaxAlert(rsData)){
				//没有成功，则直接return
				return;
			}
			//当窗口加载完成后，将数据回显
			//tableOption.updateForm.find("input[name=userName]").val(item.userName);
			readyCallBack(tableOption.updateFormId, item, this.$content);
			// NOTE: `this.$content` is the jquery object for content.
			this.$content.find("button.btn-update").click(function() {
				try {
					var param = getParamCallBack();
					//如果没有校验通过，则中断提交
					if(!param){
						return false;
					}
					$.ajax({
						type : 'post',
						url : tableOption.updateUrl,
						data : param,
						dataType : "json",
						cache : "false",
						success : function(data) {
							if(!ajaxAlert(data)){
								//没有成功，则直接return
								return;
							}
							successCallBack(data);
						},
						error : function(err) {
							ajaxErrorAlert(err);
						}
					});
				} catch (e) {
					tcAlert(e);
				}
			});
		},
		onClose: function(){
			if (closeCallBack && typeof (closeCallBack) == "function") {
				closeCallBack();
			}
	    }
	});
	return jcUpdate;
}

//查看功能封装
function detailData(tableOption, item, fillFormCallBack,closeCallBack) {
	$.dialog({
		title : '查看' + (item.titleSuffix ? item.titleSuffix : ""),
		content : 'url:' + tableOption.detailPageUrl,
		columnClass : 'col-md-8 col-md-offset-2',
		onContentReady : function() {
			//如果没有权限返回json:{url: "forward?page=system/noticeAdd", status: "0", info: "您没有操作权限，请联系管理员"}
			var rsData = parseStr2Json(this.$content.text());
			//如果返回的内容被解析成json对象，则说明没有权限，即rsData!=false,而是以上的json对象
			if(rsData != false){
				//没有权限访问，则直接return
				this.$content.text(rsData.info);
				//return;
			}
			
			if(!ajaxAlert(rsData)){
				//没有成功，则直接return
				return;
			}
			//将数据回显
			fillFormCallBack(tableOption.detailFormId, item, this.$content);
		},
		onClose: function(){
			if (closeCallBack && typeof (closeCallBack) == "function") {
				closeCallBack();
			}
	    }
	});
}

//图片或视频预览功能封装
function previewData(tableOption, item, fillFormCallBack) {
	$.dialog({
		title : item.title,
		content : 'url:' + tableOption.previewPageUrl,
		columnClass : 'col-md-8 col-md-offset-2',
		onContentReady : function() {
			//如果没有权限返回json:{url: "forward?page=system/noticeAdd", status: "0", info: "您没有操作权限，请联系管理员"}
			var rsData = parseStr2Json(this.$content.text());
			//如果返回的内容被解析成json对象，则说明没有权限，即rsData!=false,而是以上的json对象
			if(rsData != false){
				//没有权限访问，则直接return
				this.$content.text(rsData.info);
				return;
			}
			//将数据回显
			fillFormCallBack(tableOption.previewFormId, item, this.$content);
		}
	});
}

//将json数据自动填充到form表单，用于修改功能的数据回显
function fillForm(formId, item) {
	var formObj = $("#" + formId);
	var formArr = formToJson(formObj);
	//$("#updateForm").find("input[name=userName]")
	for (key in formArr) {
		var value = item[key];
		//alert(key);
		var formItem;
		if (formObj.find("input[name=" + key + "]").length > 0) {
			formItem = formObj.find("input[name=" + key + "]");
		} else if (formObj.find("textarea[name=" + key + "]").length > 0) {
			formItem = formObj.find("textarea[name=" + key + "]");
		} else {
			formItem = formObj.find("select[name=" + key + "]");
		}
		var tagLable = formItem.get(0).tagName;
		var type = formItem.attr('type');
		if (tagLable == 'INPUT') {
			if (type == 'radio') {
				formItem.attr('checked', formItem.val() == value);
			} else if (type == 'checkbox') {
				value = value + "";
				var arr = value.split(',');
				for (var i = 0; i < arr.length; i++) {
					if (formItem.val() == arr[i]) {
						formItem.attr('checked', true);
						break;
					}
				}
			} else {
				formItem.val(value);
			}
		} else if (tagLable == 'SELECT') {
			formItem.val(value);
			var options = formItem.find("option");
			options.each(function(){
				if(value == $(this).attr("value")){
					$(this).attr("selected", "selected");
				} else {
					$(this).removeAttr("selected");
				}
		    });
			
		} else if(tagLable == 'TEXTAREA'){
			formItem.val(value);
		}
	}
}

//通过jquery ajax初始化basic select2下拉框控件
function initBasicSelect2(option) {
	$.ajax({
		type : option.type ? option.type : 'post',
		url : option.url,
		data : option.params ? option.params : {},
		dataType : option.dataType ? option.dataType : "json",
		cache : option.cache ? option.cache : "false",
		success : function(data) {
			if (option.callback && typeof (option.callback) == "function") {
				option.callback(data);
			}
		},
		error : function(err) {
			ajaxErrorAlert(err);
		}
	});
}

//ajax初始化带搜索框的下拉框控件，只有搜索时才会显示相应的搜索结果选项
function initSelect2(selectOption) {
	if (selectOption.selected) {
		var option = $('<option selected>Loading...</option>');
		option.val(selectOption.selectedData.id).text(
				selectOption.selectedData.text);
		selectOption.selectObj.append(option).trigger('change');
	}
	selectOption.selectObj.select2({
		language : "zh-CN",
		placeholder : selectOption.placeholder,
		minimumInputLength : 1,
		ajax : {
			delay : 250,
			type : "post",
			cache : false,
			dataType : 'json',
			url : selectOption.url,
			data : function(params) {
				if (selectOption.beforeRequest
						&& typeof (selectOption.beforeRequest) == "function") {
					return selectOption.beforeRequest(params);
				} else {
					return params;
				}
			},
			processResults : function(data) {
				if (selectOption.afterResponse
						&& typeof (selectOption.afterResponse) == "function") {
					return selectOption.afterResponse(data);
				} else {
					return {
						results : data
					};
				}
			}
		}
	});
}

//初始化日期范围控件
function initDateRange(dateObj) {
	dateObj.datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		clearBtn : true,
		weekStart : 1,
		autoclose : true,
		todayHighlight : true
	});
}
//初始化单个日期控件
function initDate(dateObj) {
	dateObj.datepicker({
		format : "yyyy-mm-dd",
		weekStart : 1,
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true
	});
}
function initYear(dataObj) {
	dataObj.datepicker({
		format:'yyyy',
		language: 'zh-CN',
		autoclose: true,
		starView:'years',
		maxViewMode:'years',
		minViewMode:'years',
	});
}
//初始化单个日期控件
function initDateWithOption(option) {
	if (option != null && option.dateObj != null) {
		option.dateObj.datepicker({
			format : option.format ? option.format : "yyyy-mm-dd",
			minViewMode : option.minViewMode ? option.minViewMode : 0,
			maxViewMode : option.maxViewMode ? option.maxViewMode : 2,
			weekStart : option.weekStart ? option.weekStart : 1,
			startView : option.startView ? option.startView : 0,
			language : option.language ? option.language : "zh-CN",
			autoclose : option.autoclose ? option.autoclose : true,
			todayHighlight : option.todayHighlight ? option.todayHighlight
					: true
		});
	}
}
/*************************日期处理**************************/
//将毫秒日期格式化为可读
function covertDate(vdate) {
	var date = new Date(vdate.time);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var sencond = date.getSeconds();
	return year + '-' + getzf(month) + '-' + getzf(day);
}
//日期补0
function getzf(value) {
	if (parseInt(value) < 10) {
		value = '0' + value;
	}
	return value;
}



//设置jquery validate的默认值
$.validator.setDefaults({
	errorElement : "em",
	errorPlacement : function(error, element) {
		// Add the `help-block` class to the error element
		error.addClass("help-block");

		if (element.prop("type") === "checkbox") {
			var errorContainer = element.parents("label").length > 0 ? element.parents("label") : element.parents(".bootstrap-switch");
			error.insertAfter(errorContainer);
		} else if(element.parents(".input-group") > 0){
			error.insertAfter(element.parents(".input-group"));
		} else {
			var left = element.parent().prev().outerWidth(true) + 15;
			error.css("padding-left",left + "px");
			element.parents(".form-group").append(error);
			//element.attr("placeholder",error.text());
		}
	},
	highlight : function(element, errorClass, validClass) {
		$(element).parents(".form-group").addClass("has-error");
		$(element).next("span.select2").find("span.select2-selection").css(
				"border-color", "#dd4b39");
		//$(element).prev("span.input-group-addon").css("border-color", "#dd4b39");
	},
	unhighlight : function(element, errorClass, validClass) {
		$(element).parents(".form-group").removeClass("has-error");
		$(element).next("span.select2").find("span.select2-selection").css(
				"border-color", "#d2d6de");
		//$(element).prev("span.input-group-addon").css("border-color", "#d2d6de");
	}
});

//多选下拉框required
jQuery.validator.addMethod("treeSelectRequired", function(value, element) {
	return value != null && value.length > 0 && value != "请选择相应选项";
}, "请选择相应选项");
//多选下拉框required
jQuery.validator.addMethod("selectMultipleRequired", function(value, element) {
	return value != null && value.length > 0;
}, "请选择相应选项");
//下拉框required
jQuery.validator.addMethod("selectRequired", function(value, element) {
	return value > 0;
}, "请选择相应选项");
//邮政编码验证   
jQuery.validator.addMethod("isZipCode", function(value, element) {
	var regEx = /^[0-9]{6}$/;
	return this.optional(element) || (regEx.test(value));
}, "请正确填写邮政编码");

//邮箱地址验证   
jQuery.validator.addMethod("isEmail", function(value, element) {
	var regEx = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	return this.optional(element) || (regEx.test(value));
}, "请正确填写邮箱地址例如 xxx@xxx.com");
//中文
jQuery.validator.addMethod("isChinese", function(value, element) {
	var regEx = /^[\u4E00-\u9FA5]{1,6}$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入中文");
//字母或数字
jQuery.validator.addMethod("isCharOrDigit", function(value, element) {
	var regEx = /^[A-Za-z0-9]{5,12}$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入5~12位字母或数字");
//字母、数字、下划线
jQuery.validator.addMethod("isPassword", function(value, element) {
	var regEx = /^[a-zA-Z0-9]\w{5,19}$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入6~20位字母、数字和下划线");
//以字母开头的6~20位字母、数字和下划线
jQuery.validator.addMethod("isTextBeginChar", function(value, element) {
	var regEx = /^[a-zA-Z]\w{5,19}$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入以字母开头的6~20位字母、数字和下划线");
//手机号码
jQuery.validator.addMethod("isPhone",function(value, element) {
	var regEx = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入11位手机号码");
//固定电话
jQuery.validator.addMethod("isTelephone",function(value, element) {
	var regEx = /^0\d{2,3}-\d{7,8}$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入'区号-座机号'");
//手机号或固定电话
jQuery.validator.addMethod("isTelOrPhone",function(value, element) {
	var regEx = /^((0\d{2,3}-\d{7,8})|((13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}))$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入11位手机号 或者 '区号-座机号'");
//身份证号
jQuery.validator.addMethod("isIdCard",function(value, element) {
	//15位和18位身份证号码的正则表达式
	var regEx = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	//如果通过该验证，说明身份证格式正确，但准确性还需计算
	return this.optional(element) || (regEx.test(value));
}, "请输入15位或18位身份证号");
//Email
/*jQuery.validator.addMethod("isEmail", function(value, element) {   
 var regEx = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
 return this.optional(element) || (regEx.test(value));
 }, "请输入11位手机号码");*/
//检查学号是否已存在
jQuery.validator.addMethod("stuNoUnique", function(value, element) {
	var rs = false;
	$.ajax({
		url : basePath + "stumgt/checkStu",
		data : {
			stuNo : value
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = data.status == "true" ? true : false;
		}
	});
	return this.optional(element) || rs;
}, "该学号已存在");
//检查学生手机号是否已存在
jQuery.validator.addMethod("stuMobileUnique", function(value, element) {
	var rs = false;
	$.ajax({
		url : basePath + "stumgt/checkStu",
		data : {
			stuMobile : value
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = data.status == "true" ? true : false;
		}
	});
	return this.optional(element) || rs;
}, "该手机号已存在");
//检查学生身份证号是否已存在
jQuery.validator.addMethod("stuIdcardUnique", function(value, element) {
	var rs = false;
	$.ajax({
		url : basePath + "stumgt/checkStu",
		data : {
			stuIdcard : value
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = data.status == "true" ? true : false;
		}
	});
	return this.optional(element) || rs;
}, "该身份证号已存在");
//检查学生邮箱是否已存在
jQuery.validator.addMethod("stuEmailUnique", function(value, element) {
	var rs = false;
	$.ajax({
		url : basePath + "stumgt/checkStu",
		data : {
			stuEmail : value
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = data.status == "true" ? true : false;
		}
	});
	return this.optional(element) || rs;
}, "该邮箱已存在");

//检查该员工是否已经与系统账号关联
jQuery.validator.addMethod("staffIsRelatedByUser", function(value, element, params) {
	var rs = false;
	$.ajax({
		url : basePath + "system/staffIsRelatedByUser",
		data : {
			staffId : value
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = !data.status;
		}
	});
	return this.optional(element) || rs;
}, "该员工已关联其他账号");

jQuery.validator.addMethod("staffIsRelatedByUserNotSelf", function(value, element, params) {
	var rs = false;
	$.ajax({
		url : basePath + "system/staffIsRelatedByUserNotSelf",
		data : {
			staffId : value,
			userId : params.userId
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = !data.status;
		}
	});
	return this.optional(element) || rs;
}, "该员工已关联其他账号");

//检查系统用户名是否已经存在
jQuery.validator.addMethod("userNameExist", function(value, element) {
	var rs = false;
	$.ajax({
		url : basePath + "system/userNameExist",
		data : {
			userName : value
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = !data.status;
		}
	});
	return this.optional(element) || rs;
}, "用户名已存在");

//检查系统用户名是否已经存在,不检查用户自己
jQuery.validator.addMethod("userNameExistNotSelf", function(value, element, params) {
	var rs = false;
	$.ajax({
		url : basePath + "system/userNameExistNotSelf",
		data : {
			userName : value,
			userId : params.userId
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = !data.status;
		}
	});
	return this.optional(element) || rs;
}, "用户名已存在");
/*//检查员工电话号码是否已经存在
jQuery.validator.addMethod("staffPhoneExist", function(value, element, params) {
	var rs = false;
	$.ajax({
		url : basePath + "hrmgt/staffPhoneExist",
		data : {
			staffPhone : value,
			staffId : params.staffId
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = !data.status;
		}
	});
	return this.optional(element) || rs;
}, "此电话号码已被使用！");*/
//检查员工电话号码是否已经存在,不检查自己的电话号码
jQuery.validator.addMethod("staffPhoneExistNotSelf", function(value, element, params) {
	var rs = false;
	$.ajax({
		url : basePath + "hrmgt/staffPhoneExistNotSelf",
		data : {
			staffPhone : value,
			staffId : params.staffId
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = !data.status;
		}
	});
	return this.optional(element) || rs;
}, "此电话号码已被使用！");
//检查系统验证码
jQuery.validator.addMethod("kaptcha", function(value, element, params) {
	var rs = false;
	$.ajax({
		url : basePath + "kaptchaValid",
		data : {
			verifyCodeIn : value
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = data.status;
		}
	});
	return this.optional(element) || rs;
}, "验证码错误");

//再次输入新密码与前一次输入的新密码进行比较，判断两次输入的是否一致，确保新密码输入正确
jQuery.validator.addMethod("newPasswordAgain", function(value, element, params) {
	var pre = params.preValue();
	return value == pre;
}, "两次输入密码不一致！");

//唯一性验证
jQuery.validator.addMethod("isUnique", function(value, element, params) {
	var rs = false;
	$.ajax({
		url : basePath + "validate/isUnique",
		data : {
			tableName : params.tableName,
			queryFieldList : params.queryFieldList(value, element)
		},
		type : 'POST',
		async : false, // 改异步为同步  
		dataType : 'json',
		success : function(data) {
			rs = data.status;
		}
	});
	return this.optional(element) || rs;
}, "已被使用！");

$(function() {
	//初始化日期范围控件，
	initDateRange($('.input-daterange'));
	/*$('.input-daterange').datepicker({
		format: "yyyy-mm-dd",
	    language: "zh-CN",
	    clearBtn: true,
	    weekStart: 1,
	    autoclose: true,
	    todayHighlight: true
	});*/
	//初始化单个的日期控件
	initDate($("input.datepicker"));
	/*$("input.datepicker").datepicker({
		format: "yyyy-mm-dd",
	    weekStart: 1,
	    language: "zh-CN",
	    autoclose: true,
	    todayHighlight: true
	});*/

	//初始化dataTable默认参数
	$.extend($.fn.dataTable.defaults, {
		serverSide : true,//分页，取数据等等的都放到服务端去
		processing : true,//载入数据的时候是否显示“载入中”
		pageLength : 10, //首次加载的数据条数
		ordering : false, //排序操作在服务端进行，所以可以关了。
		pagingType : "full_numbers",//分页类型：simple(上一页、下一页)/simple_numbers(上一页、页数、下一页)/full(首页、上一页、下一页、末页)/full_numbers(首页、上一页、页数、下一页、末页)
		autoWidth : false,//自动计算并设置表格的宽度（表格会固定宽度）
		stateSave : false,//保持翻页状态，和comTable.fnDraw(false);结合使用
		searching : false,//禁用datatables搜索
		lengthChange : false,//是否允许用户改变表格每页显示的记录数
		deferRender : true,//延迟渲染，可以提高初始化的速度
		//scrollX : true,//水平滚动条
		//在每次table被draw完后回调函数
		fnDrawCallback : function() {
			var api = this.api();
			//获取到本页开始的条数
			var startIndex = api.context[0]._iDisplayStart;
			//如果没有设置那一列显示序号，默认第一列显示序号
			api.column(0).nodes().each(function(cell, i) {
				cell.innerHTML = startIndex + i + 1;
			});
		},
		language : {
			//lengthMenu: "",
			processing : "loading...",
			emptyTable : "没有符合条件的记录",
			zeroRecords : "没有符合条件的记录",
			info : "共  _TOTAL_ 条 | 共 _PAGES_ 页",
			infoEmpty : "",
			infoFiltered : "",
			paginate : {
				previous : "<",
				next : ">",
				first : "<<",
				last : ">>"
			}
		},
		render : { //常用render可以抽取出来，如日期时间、头像等
			ellipsis : function(data, type, row, meta) {
				data = data || "";
				return '<span title="' + data + '">' + data + '</span>';
			}
		}
	});
	//所有select2下拉框控件，选择关闭后触发jQuery validate进行校验
	//$("select").on("select2:close", function(e) {
	$("select").on("change", function(e) {
		$(this).valid();
	});
	//所有日期控件选择关闭后触发jQuery validate进行校验
	$('input.datepicker').datepicker().on("hide", function(e) {
		$(this).valid();
	});
	//icheck控件改变后触发jQuery validate进行校验
	$('input[type="checkbox"].icheck').on('ifChanged', function(event) {
		$(this).valid();
	});
	//通过js模拟form表单提交跳转
	$.extend({
	    standardPost:function(url,args){
	        var body = $(document.body),
	            form = $("<form method='post'></form>"),
	            input;
	        
	        form.attr("action",url + "?gi=" + guid());
	        $.each(args,function(key,value){
	            input = $("<input type='hidden'>");
	            input.attr({"name":key});
	            input.val(value);
	            form.append(input);
	        });

	        body.append(form);
	        form.submit();
	        form.remove();
	    }
	});
});