<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>吉林市信息共享平台</title>

<link rel="stylesheet" href="../../assets/css/dropzone.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>
		<small>
			<i class="ace-icon fa fa-angle-double-right"></i>
			拖拽文件到此处上传
		</small>
	</h1>
</div><!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div>
			<form method="post" action="${pageContext.request.contextPath}/page/html/content/imp/upload.action" class="dropzone" id="dropzone">
				<div class="fallback">
					<input name="single" type="file" multiple="" />
				</div>
			</form>
		</div>

		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
	var scripts = [null,"../../assets/js/dropzone.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	  //inline scripts related to this page
		 jQuery(function($){
	
	try {
	  Dropzone.autoDiscover = true;
	  var myDropzone = new Dropzone("#dropzone" , {
	    paramName: "single", // The name that will be used to transfer the file
	    //maxFilesize: 5.5, // MB
	
		addRemoveLinks : true,
		dictRemoveFile:'删除',
		dictDefaultMessage :
		'<span class="bigger-150 bolder"><i class="ace-icon fa fa-caret-right red"></i>拖动文件</span>到此处上传\
		<span class="smaller-80 grey">(或者点击此处)</span> <br /> \
		<i class="upload-icon ace-icon fa fa-cloud-upload blue fa-3x"></i>',
		dictResponseError: '错误 正在上传文件',
		
		//change the previewTemplate to use Bootstrap progress bars
		previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>"
		,
		  init: function () {
	            this.on("complete", function (data) {                
	                var res = eval('(' + data.xhr.responseText + ')');
	                alert(res.realname);
	            });
	        }

	  });
	  
	   $(document).one('ajaxloadstart.page', function(e) {
			try {
				myDropzone.destroy();
			} catch(e) {}
	   });
	
	} catch(e) {
	  alert('不支持此浏览器!');
	}
	
	});
	});
</script>
