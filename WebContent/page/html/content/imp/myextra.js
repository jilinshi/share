function delexcel(p1, basepath, ftype) {
	jQuery.ajax({
		url : basepath + "page/html/content/imp/removeUpFiles.action",
		type : "GET",
		dataType : "JSON",
		data : {
			'fileDTO.fileId' : p1,
			'fileDTO.ftype' : ftype
		},
		 error : function() {
	          alert("异常！");     
	     },
		success : function(data) {
			alert("删除数据");
			$("#grid-table").jqGrid().trigger('reloadGrid'); 
		}
	});
}

function impexcel(p1, basepath, ftype) {
	jQuery.ajax({
		url : basepath + "page/html/content/imp/impInfos.action",
		type : "GET",
		dataType : "JSON",
		 cache:false,
		 async : true, //默认为true 异步
		data : {
			'fileDTO.fileId' : p1,
			'fileDTO.ftype' : ftype
		},
		 error : function() {
	          alert("异常:检查所上传的文档格式");     
	     },
		success : function(data) {
			alert(data.info);
			$("#grid-table").jqGrid().trigger('reloadGrid'); 
		}
	});
}