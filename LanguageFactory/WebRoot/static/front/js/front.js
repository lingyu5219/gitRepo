$(function(){
	//关闭下拉菜单点击触发，改为鼠标经过触发
    $(document).off('click.bs.dropdown.data-api');
    //初始化导航菜单
    initColumn();
    
    
    $(window).scroll(function () {
        var tp = $(window).scrollTop();
        //将导航菜单固定在页面顶端
        if(tp > 60){
        	$(".navbar").removeClass("navbar-static-top").addClass("navbar-fixed-top");
        }else{
        	$(".navbar").removeClass("navbar-fixed-top").addClass("navbar-static-top");
        }
        
        if (tp > $("#carousel-example-generic").height()/2) {
            $(".leo-sidebar").addClass("seite");
        }else {
            $(".leo-sidebar").removeClass("seite");
        }
    })
    
    $(".img-item").mouseover(function() {
        $(this).find(".title").css('display',"block");
    }).mouseout(function() {
        $(this).find(".title").css('display',"none");
    });
    
    $(".form-control").focus(function(){
    	$(this).addClass("active");
    }).blur(function(){
    	$(this).removeClass("active");
    });
});

/**
 * 鼠标划过就展开子菜单，免得需要点击才能展开
 */
function dropdownOpen() {
 
    var $dropdownLi = $('ul.nav li.dropdown');
 
    $dropdownLi.mouseover(function() {
        $(this).addClass('open');
    }).mouseout(function() {
        $(this).removeClass('open');
    });
}

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

function initRegionSelect2(args, callback){
	initBasicSelect2({
		url: basePath + 'orgmgt/queryRegion',
		params: args,
		callback: function(data){
        	var result = $.map(data, function (obj) {
	    		obj.id = obj.regionId;
				obj.text = obj.regionName;
				return obj;
    		});
        	if (callback && typeof (callback) == "function") {
        		callback(data);
			}
        }
	});
}

function initColumn(){
	$.ajax({
		type : 'post',
		url : basePath + "front/queryColumn",
		data : {columnParentId:0, columnEnable:1},
		dataType : "json",
		cache : "false",
		success : function(data) {
			$("ul.nav").empty();
			for(var i = 0; i < data.length; i++){
				var column = data[i];
				var hasChildren = column.childColumnList != null && column.childColumnList.length > 0;
				var isCurrent = path == column.columnUrl;
				var isCurrentChild = false;
				var liStr = "<li " + (isCurrent ? "class=\"active\"" : "") + "><a href=\"" + basePath + column.columnUrl + "\">" + column.columnName + "</a></li>";
				var liaStr = "";
				var ulStr = "";
				if(hasChildren){
					liStr = "<li class=\"dropdown " + (isCurrent ? "active" : "") + "\">";
					liaStr = "<a href=\"" + basePath + column.columnUrl + "\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" + column.columnName + "</a>";
					ulStr = "<ul class=\"dropdown-menu\" role=\"menu\">";
					for(var j = 0; j < column.childColumnList.length; j++){
						var col = column.childColumnList[j];
						if(!isCurrentChild){
							isCurrentChild = path == col.columnUrl;
						}
						ulStr += "<li " + (path == col.columnUrl ? "class=\"active\"" : "") + "><a href=\"" + basePath + col.columnUrl + "\">" + col.columnName + "</a></li>";
					}
								
					ulStr += "</ul>";
				}
				if(isCurrentChild){
					liStr = "<li class=\"dropdown active\">";
				}
				$("ul.nav").append(liStr + liaStr + ulStr + "</li>");
			}
			dropdownOpen();
		},
		error : function(err) {
			
		}
	});
}

function toggleMore(target){
	target.toggleClass("hide-part");
}

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

function tcAlert2(info, content, msOrCallback) {
	var option = {
		title : info,
		content : content,
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

function ajaxErrorAlert(err){
	tcAlert(err.status);
}

function queryDataWithPage(option){
	if(!option.recordsTotal){
		option.recordsTotal = 0; //默认总记录数
	}
	if(!option.curPage){
		option.curPage = 1; //默认当前页
	}
	if(!option.length){
		option.length = 10; //默认每页显示的记录数
	}
	//将每页显示的记录数和当前页面的开始索引作为参数传递到服务器端
	if(!option.data){
		option.data = {};
	}
	option.data.length = option.length;
	option.data.start = (option.curPage-1) * option.length;
	
	$.ajax({
		type : 'post',
		url : option.url,
		data : option.data,
		dataType : "json",
		cache : "false",
		success : function(rsData) {
			//查询完数据后，更新总记录数，用于在后面生成分页控件的时候计算总页数
			option.recordsTotal = rsData.recordsTotal;
			
			if (option.success && typeof (option.success) == "function") {
				option.success(rsData);
			}
			//生成分页
			option.pageContainer.leoPagination({
				totalPage:Math.ceil(option.recordsTotal / option.length),
				curPage:option.curPage,
				goPage:function(num){
					option.curPage = num;
					queryDataWithPage(option);
				}
			});
		},
		error : function(err) {
			
		}
	});
}
function queryData(option){
	if(!option.data){
		option.data = {};
	}
	$.ajax({
		type : 'post',
		url : option.url,
		data : option.data,
		dataType : "json",
		cache : "false",
		success : function(rsData) {
			if (option.success && typeof (option.success) == "function") {
				option.success(rsData);
			}
		},
		error : function(err) {
			
		}
	});
}

function save(option){
	$.ajax({
		type : option.type ? option.type : 'post',
		url : option.url,
		data : option.data,
		dataType : option.dataType ? option.dataType : "json",
		cache : "false",
		success : function(rsData) {
			if(rsData.status == 0){
				tcAlert(rsData.info,3000);
				return;
			}
			if (option.success && typeof (option.success) == "function") {
				option.success(rsData);
			}
		},
		error : function(err) {
			
		}
	});
}

function createBox(){
	var box = $("<div class=\"box box-primary\">");
	
	var boxHeader = $("<div class=\"box-header with-border\">");
	var boxHeaderH3 = $("<h3 class=\"box-title\">");
	var boxHeaderBtnDiv = $("<div class=\"box-tools pull-right\">");
	var boxHeaderBtn = $("<button type=\"button\" class=\"btn btn-box-tool\" data-widget=\"collapse\"><i class=\"fa fa-minus\"></i></button>");
	
	boxHeaderBtnDiv.append(boxHeaderBtn);
	boxHeader.append(boxHeaderH3).append(boxHeaderBtnDiv);
	
	var boxBody = $("<div class=\"box-body\">");
	var boxFoot = $("<div class=\"box-footer\">");
	
	box.append(boxHeader).append(boxBody).append(boxFoot);
	
	return box;
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

function showVideo(option) {
	$.dialog({
		title : option.title,
		content : 'url:' + option.previewPageUrl,
		columnClass : 'col-md-8 col-md-offset-2',
		onContentReady : function() {
			var video = "<div class=\"embed-responsive embed-responsive-16by9\"><video src=\"" + basePath + option.mediaUrl + "\" controls></video></div>";
			this.$content.find("#media").html(video);
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
			param[e.name] = param[e.name] + "," + e.value;
		}
	});
	return param;
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) 
    	return unescape(r[2]); 
    return null; //返回参数值
}
//下拉框required
jQuery.validator.addMethod("selectRequired", function(value, element) {
	return value > 0;
}, "请选择相应选项");
//邮箱地址验证   
jQuery.validator.addMethod("isEmail", function(value, element) {
	var regEx = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	return this.optional(element) || (regEx.test(value));
}, "请正确填写邮箱地址例如 xxx@xxx.com");
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
	//var regEx = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	var regEx = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
	//如果通过该验证，说明身份证格式正确，但准确性还需计算
	return this.optional(element) || (regEx.test(value));
}, "请输入15位或18位身份证号");
//中文
jQuery.validator.addMethod("isChinese", function(value, element) {
	var regEx = /^[\u4E00-\u9FA5]+$/;
	return this.optional(element) || (regEx.test(value));
}, "请输入中文");
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

function getRandom(minNum,maxNum){
	return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10);
}
