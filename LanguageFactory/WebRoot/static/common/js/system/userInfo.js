$(function () {
	//初始化员工信息表单校验
	$("#staffUpdateForm").validate({
		onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
		ignore:"",
		rules: {
			staffId:{
				required: true
			},
			staffName: {
				required: true
			},
			staffPhone: {
				required: true,
				isPhone: true,
				staffPhoneExistNotSelf: {staffId:$("input[name='staffId']").val()}
			}
		}
	});
	//初始化修改密码表单校验
	$("#passwordResetForm").validate({
		onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
		ignore:"",
		rules: {
			userId: {
				required: true
			},
			userPassword: {
				required: true,
				isPassword: true
			},
			newPassword: {
				required: true,
				isPassword: true
			},
			newPassword2: {
				required: true,
				isPassword: true,
				//与newPassword进行对比，确认两次是否输入的新密码是一样的
				newPasswordAgain:{
					preValue:function(){
						return $("input[name='newPassword']").val();
					}
				}
			},
			verifyCode:{
				required: true,
				kaptcha: true
			}
		}
	});
	
	$("#kaptchaImage").click(function(){
		var time = new Date().getTime();
        $(this).attr("src",basePath + "kaptcha?d=" + time);
	});
	
	//上传头像初始化
	//初始化文件上传插件
	var leoUp = new LeoUpload({
		fileMaxCount:1,
		fileMaxSize:1048576,//1MB
		fileLimitType:".jpg|.png|.gif",
		uploadPath : "static/upload/pic/userHead/",
		uploadAction : basePath + "upload/uploadFile",
		delAction : basePath + "upload/delFile",
		picker : "headPhotoBtn",
		fileList : "fileList",
		afterSuccess : function(data){
			$("input[name='staffPic']").val(data.fileRelativePath);
			$("input[name='staffPicName']").val(data.fileName);
			$("input[name='staffPicSize']").val(data.fileSize);
			$(".info-box-icon").html("<img class=\"img-responsive img-circle\" src=\"" + basePath + data.fileRelativePath + "\"/>");
	        
		},
		afterDelFile : function(data){
			$("input[name='staffPic']").val("");
			$("input[name='staffPicName']").val("");
			$("input[name='staffPicSize']").val("0");
		}
	});
	//如果员工已经有头像了，则显示头像，隐藏上传按钮
	var staffPic = $("input[name='staffPic']").val();
	if(staffPic != null && staffPic != ""){
		//将上传按钮隐藏
		leoUp.picker.hide();
		//初始化文件item
		var picName = $("input[name='staffPicName']").val();
		var picSize = $("input[name='staffPicSize']").val();
		var file = {
			name:picName == null || picName == "" ? "userHead" : picName,
			size:picSize == null || picSize == "" ? " " : picSize,
			fileRelativePath:staffPic
		};
		var fileItem = leoUp.createFileItem(file);
		fileItem.find(".leoFileProgressBar").css("width","100%");
        fileItem.find(".leoFileProgressNum").text("100%");
        fileItem.find(".info-box-icon").html("<img class=\"img-responsive img-circle\" src=\"" + basePath + staffPic + "\"/>");
        //显示fileItem
		leoUp.fileList.append(fileItem);
		//绑定删除事件
    	fileItem.find(".leoFileDelBtn").click(function(){
    		//此处不会真的删除；避免真删除之后，如果没有点击保存更新数据库信息，当再次回显的时候，数据库中有文件记录数据，但是真实的文件已经删除，导致数据不一致
    		fileItem.remove();
    		$("input[name='staffPicChanged']").val("1");
    		//leoUp.disabled = false;
    		$("input[name='staffPic']").val("");
    		$("input[name='staffPicName']").val("");
    		$("input[name='staffPicSize']").val(0);
    		leoUp.picker.show();
    	});
	}
	//点击保存按钮 提交修改
	$("#staffUpdate").click(function() {
		try {
			//提交表单数据之前，校验表单
			var validResult = $("#staffUpdateForm").valid();
			if(!validResult){
				//如果没有校验通过，则中断提交
				return;
			}
			//获取修改表单数据
			var param = formToJson($("#staffUpdateForm"));
			$.ajax({
				type : 'post',
				url : basePath + "hrmgt/updateStaffInfo",
				data : param,
				dataType : "json",
				cache : "false",
				success : function(data) {
					if(!ajaxAlert(data)){
						//没有成功，则直接return
						return;
					}
		    		//提示信息
	            	tcAlert(data.info,function(){
	            		window.location.reload();
	            	});
				},
				error : function(err) {
					ajaxErrorAlert(err);
				}
			});
		} catch (e) {
			tcAlert(e);
		}
	});
	
	$("#passwordReset").click(function() {
		try {
			//提交表单数据之前，校验表单
			var validResult = $("#passwordResetForm").valid();
			if(!validResult){
				//如果没有校验通过，则中断提交
				return;
			}
			//获取修改表单数据
			var param = formToJson($("#passwordResetForm"));
			$.ajax({
				type : 'post',
				url : basePath + "system/updatePassword",
				data : param,
				dataType : "json",
				cache : "false",
				success : function(data) {
					if(!ajaxAlert(data)){
						//刷新验证码
						$("#kaptchaImage").click();
						//没有成功，则直接return
						return;
					}
		    		//提示信息
	            	tcAlert(data.info + "，请退出重新登录",function(){
	            		window.location.href = basePath + "system/logout";
	            	});
				},
				error : function(err) {
					ajaxErrorAlert(err);
				}
			});
		} catch (e) {
			tcAlert(e);
		}
	});
});