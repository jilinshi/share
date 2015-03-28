function delexcel(p1, basepath, ftype) {
	$.ajax({
		url : basepath + "page/html/content/imp/removeUpFiles.action",
		type : "GET",
		dataType : "JSON",
		data : {
			'fileDTO.fileId' : p1,
			'fileDTO.ftype' : ftype
		},
		success : function() {
			alert("数据删除成功");
			 
			$("#grid-table").jqGrid().trigger('reloadGrid'); 
		}
	});
}

function impexcel(p1, basepath, ftype) {
	$.ajax({
		url : basepath + "page/html/content/imp/impInfos.action",
		type : "GET",
		dataType : "JSON",
		data : {
			'fileDTO.fileId' : p1,
			'fileDTO.ftype' : ftype
		},
		success : function() {
			alert("数据导入成功");
			$("#grid-table").jqGrid().trigger('reloadGrid'); 
		}
	});
}