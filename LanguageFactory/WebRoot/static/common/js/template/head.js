var queryNoticeList = basePath + "msgmgt/queryMsg";
var detailPageUrl = basePath + "msgmgt/msgDetail";
var noticeList = [];

function getNotice(noticeId){
	for(var i in noticeList){
		//ID相同说明已经存在该消息公告
		if(noticeId == noticeList[i].noticeId){
			return noticeList[i];
		}
	}
	return null;
}

function removeNotice(noticeId){
	for(var i in noticeList){
		//ID相同说明已经存在该消息公告
		if(noticeId == noticeList[i].noticeId){
			noticeList.splice(i,1);
			return true;
		}
	}
	return false;
}

function refreshNotice(notice){
	var oldNotice = getNotice(notice.noticeId);
	//如果消息列表中不存在该消息，则添加
	if (oldNotice == null && notice.noticeState == 2) {
		noticeList.unshift(notice);
		refreshNoticeList();
	} else if(notice.noticeState == 1){
		//如果存在该消息，需要从列表中删除该消息
		removeNotice(notice.noticeId);
		//noticeList.push(notice);
		refreshNoticeList();
	}
	
}

function refreshNoticeList(){
	var noticeUl = $("#noticeList").find(".menu");
	//先清空
	noticeUl.empty();
	for(var i in noticeList){
		if(i > 5){
			break;
		}
		var notice = noticeList[i];
		var noticeLi = $("<li>");
		var noticeA = $("<a>");
		noticeA.attr("href","javascript:$.standardPost(\"" + detailPageUrl + "\",{\"noticeId\":" + notice.noticeId + "});");
		var noticeIcon = $("<div class=\"pull-left\"><span class=\"text-xlarge text-primary fa fa-envelope-o\"></span></div>");
		
		var rs = notice.noticeTitle.gblen() > 22;
		var noticeTitle = $("<h4>" + (rs ? subString(notice.noticeTitle,22,false) + "..." : notice.noticeTitle) + "</h4>");	
		var noticeRemark = $("<p><i class=\"fa fa-clock-o\"></i> " + notice.createTime.substr(0,19) + "</p>");	
		noticeA.append(noticeIcon);
		noticeA.append(noticeTitle);
		noticeA.append(noticeRemark);
		noticeLi.append(noticeA);
		noticeUl.append(noticeLi);
	}
	if(noticeList.length > 0){
		$("#noticeList").find(".notice-count").text(noticeList.length);
		$("#noticeList").find("li.header>strong").text(noticeList.length);
		
	} else {
		$("#noticeList").find(".notice-count").text("");
		$("#noticeList").find("li.header>strong").text(0);
	}
}

function initNoticeList(){
	$.ajax({
		type : 'post',
		url : queryNoticeList,
		dataType : "json",
		cache : "false",
		success : function(data) {
			//如果没有获取消息的访问权限
			/*if(data.status && data.status == 3){
				tcAlert2("读取系统消息",data.info,5000);
				return false;
			}*/
			if(!ajaxAlert(data)){
				//没有成功，则直接return
				return;
			}
			noticeList = data.data;
			refreshNoticeList();
		},
		error : function(err) {
			ajaxErrorAlert(err);
		}
	});
}

function initNoticeWs(){
	var ws = new LeoWs("/notice/ws");
	ws.init();
	ws.onMessage(function(e){
		var notice = parseStr2Json(e.data);
		refreshNotice(notice);
	});
}

$(function () {
	//初始 获取已发布的系统公告
	initNoticeList();
	//初始化websocket
	initNoticeWs();
});