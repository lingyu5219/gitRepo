$(function () {
	/***************初始化查询条件下拉框控件******************/
	$("select.stuSexSelect2").select2({
		minimumResultsForSearch: Infinity//隐藏搜索框Hiding the search box
	});
	//初始化出生日期
	$("#stuBirthday").datepicker("destroy");
	initDateWithOption({
		dateObj:$("#stuBirthday"),
		startView:2,
		minViewMode:0,
		maxViewMode:3
	});
	//初始化学年下拉框
	initBasicSelect2({
		url: basePath + 'edumgt/queryYear',
		callback: function(data){
        	var result = $.map(data, function (obj) {
	    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	    		obj.id = obj.yearId;
				obj.text = obj.yearTitle;
				
				return obj;
    		});
        	$("select.yearIdSelect2").select2({
        		data:result
        	});
        }
	});
	//初始化专业方向下拉框
	initBasicSelect2({
		url: basePath + 'edumgt/queryMajor',
		callback: function(data){
        	var result = $.map(data, function (obj) {
	    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
	    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
	    		obj.id = obj.majorId;
				obj.text = obj.majorName;
				
				return obj;
    		});
        	$("select.majorIdSelect2").select2({
        		data:result
        	});
        }
	});
	$("select.batchIdSelect2").select2();
	//学年下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	$("select.yearIdSelect2").on('change', function (e) {
		var yearId = $(this).val();
		//var yearTitle = $(this).find("option:selected").text();
		var majorId = $("select.majorIdSelect2").find("option:selected").val();
		initBasicSelect2({
			url: basePath + 'edumgt/queryBatch',
			params: {yearId:yearId,majorId:majorId},
			callback: function(data){
            	var result = $.map(data, function (obj) {
		    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
		    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
		    		obj.id = obj.batchId;
					obj.text = obj.batchName;
					
					return obj;
	    		});
            	$("select.batchIdSelect2").empty().select2({
            		data:result
            	});
            	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
            	$("select.batchIdSelect2").append(option).trigger("change");
            }
		});
	});
	//专业方向下拉框change后，先清空班级下拉框,然后根据学年和专业方向查询班级
	$("select.majorIdSelect2").on('change', function (e) {
		var yearId = $("select.yearIdSelect2").find("option:selected").val();
		var majorId = $(this).val();
		initBasicSelect2({
			url: basePath + 'edumgt/queryBatch',
			params: {yearId:yearId,majorId:majorId},
			callback: function(data){
            	var result = $.map(data, function (obj) {
		    		//select2控件option选项的value是id值，显示的文本是text值，所以需要将各功能实际的ID和显示的文本值，分别赋给id和text
		    		//本功能中是讲userId对应赋值给id, userName赋值给text，此处需要各自按实际情况修改
            		obj.id = obj.batchId;
					obj.text = obj.batchName;
					
					return obj;
	    		});
            	$("select.batchIdSelect2").empty().select2({
            		data:result
            	});
            	var option = $("<option selected=\"selected\" value=\"0\">请选择班级</option>");
            	$("select.batchIdSelect2").append(option).trigger("change");
            }
		});
	});
	
	/**********************************************************/
	//初始化复选框
	$('input[type="checkbox"].icheck').iCheck({
        checkboxClass: 'icheckbox_square-blue'
    });
	
    //注册
    function ajaxForm(form){
		var param = formToJson(form);
		//ajax请求插入数据
		$.ajax({
        	type: 'post',
            url: basePath + "stumgt/regStu",
            data: param,
            dataType: "json",
            cache: "false",
            success: function(data){
	    		//提示信息
            	if(data.status == 1){
            		tcAlert("注册成功,确定跳转到登录",function(){
                		window.location.href = basePath + "system/login";
                	});
            	} else {
            		tcAlert("注册失败",5000);
            	}
            	
            },
            error: function(err){
            	tcAlert(err.status + "——" + err.statusText);
            }
        });
    }
    
    
    $("#regForm").validate({
    	submitHandler:function(form) {
    		ajaxForm($(form));
    	},
		rules: {
			yearId: {
				selectRequired: true
			},
			majorId: {
				selectRequired: true
			},
			batchId: {
				selectRequired: true
			},
			stuNo: {
				required: true,
				digits: true,
				maxlength: 12,
				//远程校验唯一性
				stuNoUnique: true
			},
			stuName: {
				required: true,
				maxlength: 100
			},
			stuSex: {
				selectRequired: true
			},
			stuBirthday: {
				required: true
			},
			stuIdcard: {
				required: true,
				isIdCard: true,
				stuIdcardUnique: true
			},
			stuMobile: {
				required: true,
				isPhone: true,
				stuMobileUnique: true
			},
			stuEmail: {
				required: true,
				email:true,
				stuEmailUnique: true,
				maxlength: 100
			},
			readAndAgree: {
				required: true
			}
		},
		messages: {
			yearId: {
				selectRequired: "请选择学年"
			},
			majorId: {
				selectRequired: "请选择专业方向"
			},
			batchId: {
				selectRequired: "请选择班级"
			},
			stuNo: {
				remote: "该学号已存在"
			},
			stuSex: {
				selectRequired: "请选择性别"
			},
			stuBirthday: {
				selectRequired: "请选择出生日期"
			},
			stuIdcard: {
				selectRequired: "请输入身份证号"
			},
			stuMobile: {
				selectRequired: "请输入手机号码"
			},
			readAndAgree: {
				required: "请阅读并同意相关协议"
			}
		}
    });
});