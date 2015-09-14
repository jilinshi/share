//全局ajax控制，用于session超时 无权限时 提示  
$.ajaxSetup({  
    //cache: false, //close AJAX cache  
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    timeout : 60000, //超时时间设置，单位毫秒
    complete: function(XMLHttpRequest, textStatus) {
    	//"success","timeout", "error", "notmodified" 和 "parsererror"。
    	//alert(textStatus);
    	if(textStatus=="success"){
    	}else if(textStatus=="parsererror"){
    		sessionTimeOut();
    		returnLogin();
    	}else if(textStatus=="notmodified"){
    		sessionTimeOut();
    		returnLogin();
    	}else if(textStatus=="error"){
    		noLimit();
    		returnLogin();
    	}
        
    }
});  
  
function sessionTimeOut(){  
    alert('用户登录会话已过期，请重新登录！');  
}  
  
function noLimit(){  
    alert('无相应操作权限，请联系系统管理员！');  
}

