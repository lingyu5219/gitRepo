var LeoWs = function(url){
	//相对根目录的路径
	this.url = url;
	this.socket = null;
};

LeoWs.prototype.connect = function(path){
	if('WebSocket' in window){
		this.socket = new WebSocket(path);
	}else if('MozWebSocket' in window){
		this.socket = new MozWebSocket(path);
	}else{
		tcAlert('Error:WebSocket is not supported by this browser.');
		return;
	}
}

LeoWs.prototype.onOpen = function(onOpen){
	this.socket.onopen = function(e){
		if (onOpen && typeof (onOpen) == "function") {
			onOpen(e);
		}
	}
}

LeoWs.prototype.onError = function(onError){
	this.socket.onerror = function(e){
		if (onError && typeof (onError) == "function") {
			onError(e);
		} else {
			tcAlert("消息服务器链接错误",3000);
		}
	}
}

LeoWs.prototype.onClose = function(onClose){
	this.socket.onclose = function(e){
		if (onClose && typeof (onClose) == "function") {
			onClose(e);
		}
	}
}

LeoWs.prototype.onMessage = function(onMessage){
	this.socket.onmessage = function(e){
		if (onMessage && typeof (onMessage) == "function") {
			onMessage(e);
		}
	}
}

LeoWs.prototype.sendMessage = function(message){
	if(message != ''){
		this.socket.send(message);
	}
}

LeoWs.prototype.init = function(){
	if(window.location.protocol == 'http:'){
		this.connect('ws://' + window.location.host + rootPath + this.url);
	}else{
		this.connect('wss://' + window.location.host + rootPath + this.url)
	}
}