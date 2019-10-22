var LeoUpload = function(option){
	//每个文件最大体积10MB，单位byte
	this.fileMaxSize = option.fileMaxSize ? option.fileMaxSize : 10485760;
	//最大上传文件数
	this.fileMaxCount = option.fileMaxCount ? option.fileMaxCount : 5;
	//限制的文件类型，以竖线分隔的字符串，如：".jpg|.png"
	this.fileLimitType = option.fileLimitType ? option.fileLimitType : null;
	//文件上传后保存的目录，相对工程目录
	this.uploadPath = option.uploadPath;
	//上传文件的url
	this.uploadAction = option.uploadAction;
	//删除文件的url
	this.delAction = option.delAction;
	//下载文件的url
	this.downloadAction = option.downloadAction;
	//选择文件的按钮
	this.picker = $("#" + option.picker);
	//文件显示列表区域的ID
	this.fileList = $("#" + option.fileList);
	//上传成功后执行的回调函数
	this.afterSuccess = option.afterSuccess &&  typeof (option.afterSuccess) == "function" ? option.afterSuccess : null;
	//删除文件成功后执行的回调函数
	this.afterDelFile = option.afterDelFile &&  typeof (option.afterDelFile) == "function" ? option.afterDelFile : null;
//	//初始化上传表单
//	this.form = this.createForm(option);
//	//初始化文件域
//	this.fileInput = this.createFileInput();
//	this.form.append(this.fileInput);
	this.disabled = option.disabled ? option.disabled : false;//默认是false,表示可用的
	this.fileCount = 0; //上传文件计数
	this.fileArr = new Array();
	//绑定事件
	this.bindPicker();
	return this;
}

/*LeoUpload.prototype.bindPicker = function(){
	var leoUpload = this;
	leoUpload.fileInput.change(function(e){
		var file = $(this)[0].files[0];
		leoUpload.upload(file);
		
	});
	leoUpload.picker.click(function(){
		if(leoUpload.disabled){
			tcAlert("上传插件不可用！");
			return;
		}
		//判断上传文件数是否已经超过最大限制
		if(leoUpload.fileCount >= leoUpload.fileMaxCount){
			tcAlert("最多允许上传" + leoUpload.fileMaxCount + "个文件！");
			return false;
		}
		//上传文件后，刷新文本域，避免再次选择相同文件时，不会触发文本域的change事件
		leoUpload.refreshFileInput();
		leoUpload.fileInput.click();
	});
}*/
LeoUpload.prototype.bindPicker = function(){
	var leoUpload = this;
	/*leoUpload.fileInput.change(function(e){
		var file = $(this)[0].files[0];
		leoUpload.upload(file);
		
	});*/
	leoUpload.picker.click(function(){
		if(leoUpload.disabled){
			tcAlert("上传插件不可用！");
			return;
		}
		//判断上传文件数是否已经超过最大限制
		if(leoUpload.fileCount >= leoUpload.fileMaxCount){
			tcAlert("最多允许上传" + leoUpload.fileMaxCount + "个文件！");
			return false;
		}
		//上传文件后，重置form表单，避免再次选择相同文件时，不会触发文本域的change事件
		leoUpload.removeForm();
		leoUpload.createForm();
		leoUpload.fileInput.click();
	});
}

/*LeoUpload.prototype.createForm = function(option){
	var newForm = $("<form method=\"post\" enctype=\"multipart/form-data\">");
	var fileMaxSizeInput = $("<input type=\"text\" name=\"fileMaxSize\"/>").val(option.fileMaxSize);
	var fileMaxCountInput = $("<input type=\"text\" name=\"fileMaxCount\"/>").val(option.fileMaxCount);
	var fileLimitTypeInput = $("<input type=\"text\" name=\"fileLimitType\"/>").val(option.fileLimitType);
	var uploadPathInput = $("<input type=\"text\" name=\"uploadPath\"/>").val(option.uploadPath);
	newForm.append(fileMaxSizeInput).append(fileMaxCountInput).append(fileLimitTypeInput).append(uploadPathInput);
	return newForm; 
}*/
LeoUpload.prototype.createForm = function(){
	var leoUp = this;
	var newForm = $("<form method=\"post\" enctype=\"multipart/form-data\">");
	var fileMaxSizeInput = $("<input type=\"text\" name=\"fileMaxSize\"/>").val(leoUp.fileMaxSize);
	var fileMaxCountInput = $("<input type=\"text\" name=\"fileMaxCount\"/>").val(leoUp.fileMaxCount);
	var fileLimitTypeInput = $("<input type=\"text\" name=\"fileLimitType\"/>").val(leoUp.fileLimitType);
	var uploadPathInput = $("<input type=\"text\" name=\"uploadPath\"/>").val(leoUp.uploadPath);
	newForm.append(fileMaxSizeInput).append(fileMaxCountInput).append(fileLimitTypeInput).append(uploadPathInput);
	leoUp.fileInput = $("<input type=\"file\" name=\"file\"/>");
	//绑定change事件
	leoUp.fileInput.change(function(e){
		var file = $(this)[0].files[0];
		leoUp.upload(file);
		
	});
	newForm.append(leoUp.fileInput);
	
	leoUp.form = newForm;
	return leoUp;
}

/*LeoUpload.prototype.createFileInput = function(){
	var fileInput = $("<input type=\"file\" name=\"file\"/>");
	return fileInput;
}*/

LeoUpload.prototype.removeForm = function(){
	if(this.form){
		this.form.remove();
	}
	return this;
}


//创建文件项item
LeoUpload.prototype.createFileItem = function(file){
	//var fileBoxCol = $("<div class=\"col-sm-6\">");
	var fileBoxCol = $("<div style=\"width:100%; max-width:250px;\">");
	var fileBox = $("<div class=\"info-box bg-aqua\">");
	var fileIcon = $("<span class=\"info-box-icon\"><i class=\"fa fa-file-o\"></i></span>");
	var fileContent = $("<div class=\"info-box-content\">");
	var fileName = $("<span class=\"info-box-text\" title=\"" + file.name + "\">" + file.name + "</span>");
	var fileSize = $("<span class=\"info-box-text\">" + Math.ceil(file.size / 1024) + "KB </span>");
	var fileProgressBar = $("<div class=\"progress\"><div class=\"leoFileProgressBar progress-bar\" style=\"width:5%;\"></div></div>");
	var fileProgressNum = $("<span class=\"progress-description\"><span class=\"leoFileProgressNum\">0</span><a class=\"btn btn-danger btn-xs pull-right leoFileDelBtn\">删除</a></span>");
	
	fileContent.append(fileName).append(fileSize).append(fileProgressBar).append(fileProgressNum);
	fileBox.append(fileIcon).append(fileContent);
	fileBoxCol.append(fileBox);
	return fileBoxCol;
}

LeoUpload.prototype.removeFileItem = function(fileItem){
	
}
//校验文件类型
LeoUpload.prototype.validateType = function(file){
	if(this.fileLimitType == null){
		return true;
	}
	
	var strRegex = "(" + this.fileLimitType + ")$"; //用于验证图片扩展名的正则表达式
	var re = new RegExp(strRegex);
	if (re.test(file.name.toLowerCase())){
	 return true;
	}
	return false;
}
//校验文件大小
LeoUpload.prototype.validateSize = function(file){
	if(file.size > this.fileMaxSize){
		return false;
	}
	return true;
}
//校验文件大小
LeoUpload.prototype.validateCount = function(){
	if(this.fileCount > this.fileMaxCount){
		return false;
	}
	return true;
}
/* LeoUpload.prototype.beforeUpload = function(callback){
	if (callback && typeof (callback) == "function") {
		callback();
	}
} */

LeoUpload.prototype.reset = function(){
	for(var i = 0; i < this.fileArr.length; i++){
		var file = this.fileArr[i];
		this.delFile(file.file, file.fileItem);
	}
}


LeoUpload.prototype.delFile = function(file,fileItem){
	var leoUpload = this;
	$.ajax({
		type : 'post',
		url : leoUpload.delAction,
		data : file,
		dataType : "json",
		cache : "false",
		success : function(data) {
			if(data.status == 1){
				//移除该文件item
				fileItem.remove();
				if(--leoUpload.fileCount < leoUpload.fileMaxCount){
					leoUpload.picker.show();
					//leoUpload.disabled = false;
				}
				//将对应的fileArr中的文件信息删除
				for(var i = 0; i < leoUpload.fileArr.length; i++){
					var file = leoUpload.fileArr[i];
					if(file.fileItem == fileItem){
						leoUpload.fileArr.splice(i,1);
					}
				}
				if(leoUpload.afterDelFile != null){
					leoUpload.afterDelFile(data);
        		}
			} else {
				tcAlert("删除失败！");
			};
		},
		error : function(err) {
			ajaxErrorAlert(err);
		}
	});
}

LeoUpload.prototype.upload = function(file){
	var leoUp = this;
	//初始化文件item
	var fileItem = leoUp.createFileItem(file);
	leoUp.form.ajaxSubmit({
		type: "post",
		url: leoUp.uploadAction,
		beforeSubmit: function() {
			
			//上传之前进行文件校验
			//检查文件类型
			if(!leoUp.validateType(file)){
				tcAlert("请选择" + leoUp.fileLimitType + "类型的文件！");
				return false;
			}
			//检查文件大小
			if(!leoUp.validateSize(file)){
				var fileMaxSize = (leoUp.fileMaxSize / 1024) / 1024;
				tcAlert("请选择" + fileMaxSize + "MB 以内的文件！");
				return false;
			}
            //显示fileItem
			leoUp.fileList.append(fileItem);
        },
        uploadProgress: function(event, position, total, percentComplete) {
            //更新进度
            var percentVal = percentComplete + '%';
            fileItem.find(".leoFileProgressBar").css("width",percentVal);
            fileItem.find(".leoFileProgressNum").text(percentVal);
        },
        success: function(data) {
        	//如果上传失败,删除fileItem
        	if(data == null || typeof(data) == "undefined"){
        		fileItem.remove();
        		return false;
        	}
        	//上传成功后，计数
        	if(++leoUp.fileCount >= leoUp.fileMaxCount){
        		//leoUp.picker.unbind("click");
        		leoUp.picker.hide();
        	}
        	//将当前上传成功的文件信息存储在fileArr中
        	leoUp.fileArr.push({
        		file:data,
        		fileItem:fileItem
        	});
        	//绑定删除事件
        	fileItem.find(".leoFileDelBtn").click(function(){
        		leoUp.delFile(data,fileItem);
        	});
        	//成功后返回文件信息，显示文件
        	if(leoUp.afterSuccess != null){
    			leoUp.afterSuccess(data);
    		}
        }
	});
}

LeoUpload.prototype.downloadFile = function(file){
	var newForm = $("<form method=\"post\" action=\"" + this.downloadAction + "\">");
	var fileRelativePath = $("<input type=\"text\" name=\"fileRelativePath\"/>").val(file.fileRelativePath);
	var fileName = $("<input type=\"text\" name=\"fileName\"/>").val(file.fileName);
	newForm.append(fileRelativePath).append(fileName);
	newForm.css("display","none");
	$("body").append(newForm);
	newForm.submit();
}