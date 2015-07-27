//全局ajax控制，用于session超时 无权限时 提示  
$.ajaxSetup({  
    cache: false, //close AJAX cache  
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    timeout : 1000, //超时时间设置，单位毫秒
    complete: function(XMLHttpRequest, textStatus) {
    	//"success","timeout", "error", "notmodified" 和 "parsererror"。
    	if(textStatus=="success"){
    		
    	}else if(textStatus=="parsererror"){
    		sessionTimeOut();
    		login();
    	}else if(textStatus=="notmodified"){
    		sessionTimeOut();
    		login();
    	}else if(textStatus=="error"){
    		noLimit();
    		login();
    	}
        
    }
});  
  
function sessionTimeOut(){  
    alert('用户登录会话已过期，请重新登录！');  
}  
  
function noLimit(){  
    alert('无相应操作权限，请联系系统管理员！');  
}

