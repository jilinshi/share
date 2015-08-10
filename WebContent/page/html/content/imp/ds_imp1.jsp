<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String ftype = "RESINSURANCE";
%>
<title>吉林市信息共享平台</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="<%=basePath%>assets/mydefine/style.css" />
<!-- ajax layout which only needs content area -->
<!--<div class="page-header"></div> -->
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="resumable-drop" ondragenter="jQuery(this).addClass('resumable-dragover');" ondragend="jQuery(this).removeClass('resumable-dragover');" ondrop="jQuery(this).removeClass('resumable-dragover');">
        <a class="resumable-browse"><u>选取上传文件</u></a>
      </div>
      <div class="resumable-progress">
        <table>
          <tr>
            <td width="100%"><div class="progress-container"><div class="progress-bar"></div></div></td>
            <td class="progress-text" nowrap="nowrap"></td>
            <td class="progress-pause" nowrap="nowrap">
              <a href="#" onclick="r.upload(); return(false);" class="progress-resume-link"><img src="<%=basePath%>assets/mydefine/resume.png" title="Resume upload" /></a>
              <a href="#" onclick="r.pause(); return(false);" class="progress-pause-link"><img src="<%=basePath%>assets/mydefine/pause.png" title="Pause upload" /></a>
            </td>
          </tr>
        </table>
      </div>
      <ul class="resumable-list"></ul>

		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		 
		<table id="grid-table"></table>

		<div id="grid-pager"></div>


		<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
		</script>

		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
var changeGridOptions;
var _basepath='<%=basePath%>';
var _ftype='<%=ftype%>';
var scripts = [null,"<%=basePath%>assets/mydefine/resumable.js","<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>page/html/content/imp/myextra.js","<%=basePath%>assets/js/publicSetup.js", null];
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	  //inline scripts related to this page
		 jQuery(function($){
	try {
		var r = new Resumable({
            target:'<%=basePath%>page/BigUpload',
            chunkSize:2*1024*1024,
            simultaneousUploads:4,
            testChunks: true,
            throttleProgressCallbacks:1,
            method: "octet"
          });
        // Resumable.js isn't supported, fall back on a different method
        if(!r.support) {
          $('.resumable-error').show();
        } else {
          // Show a place for dropping/selecting files
          $('.resumable-drop').show();
          r.assignDrop($('.resumable-drop')[0]);
          r.assignBrowse($('.resumable-browse')[0]);

          // Handle file add event
          r.on('fileAdded', function(file){
              // Show progress pabr
              $('.resumable-progress, .resumable-list').show();
              // Show pause, hide resume
              $('.resumable-progress .progress-resume-link').hide();
              $('.resumable-progress .progress-pause-link').show();
              // Add the file to the list
              $('.resumable-list').append('<li class="resumable-file-'+file.uniqueIdentifier+'">正在上传... <span class="resumable-file-name"></span> <span class="resumable-file-progress"></span>');
              $('.resumable-file-'+file.uniqueIdentifier+' .resumable-file-name').html(file.fileName);
              // Actually start the upload
              r.upload();
            });
          r.on('pause', function(){
              // Show resume, hide pause
              $('.resumable-progress .progress-resume-link').show();
              $('.resumable-progress .progress-pause-link').hide();
            });
          r.on('complete', function(){
              // Hide pause/resume when the upload has completed
              $('.resumable-progress .progress-resume-link, .resumable-progress .progress-pause-link').hide();
            });
          r.on('fileSuccess', function(file,message){
        	  if(message=='upload'){
        		  
        	  }else{
        		  var bbs= message.split('~');
        		  addfile('-1',bbs[0],bbs[0],bbs[1],_ftype);
        	  }
              $('.resumable-file-'+file.uniqueIdentifier+' .resumable-file-progress').html('(completed)');
            });
          r.on('fileError', function(file, message){
              // Reflect that the file upload has resulted in error
              $('.resumable-file-'+file.uniqueIdentifier+' .resumable-file-progress').html('(file could not be uploaded: '+message+')');
            });
          r.on('fileProgress', function(file){
              // Handle progress for both the file and the overall upload
              $('.resumable-file-'+file.uniqueIdentifier+' .resumable-file-progress').html(Math.floor(file.progress()*100) + '%');
              $('.progress-bar').css({width:Math.floor(r.progress()*100) + '%'});
            });
        }
 
	  function remfile(fid,realname,realpath){
		  if(-1!=fid){
		  $.ajax({url: "<%=basePath%>page/html/content/imp/removedfile.action",
			  type:"GET",
			  dataType:"JSON",
			  data: {'fileid':fid, 'realname':realname, 'realpath':realpath},
              success: function(){
            	  alert("文件和文件所产生的记录已经删除");
		 }});}
	  }
	  
	  function addfile(fid,realname,realpath,displayname,ftype){
		  $.ajax({url: "<%=basePath%>page/html/content/imp/addfile.action",
												type : "GET",
												dataType : "JSON",
												data : {
													'fileDTO.fileid' : fid,
													'fileDTO.realname' : realname,
													'fileDTO.realpath' : realpath,
													'fileDTO.displayname' : displayname,
													'fileDTO.ftype' : ftype
												},
												success : function() {
													//alert("文件上传成功");
												}
											});
								}
	  
	  
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		
		//resize to fit page size
		$(window).on('resize.jqGrid', function () {
			$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
	    })
		//resize on sidebar collapse/expand
		var parent_column = $(grid_selector).closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
				setTimeout(function() {
					$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
				}, 0);
			}
	    })
		jQuery(grid_selector).jqGrid({
			autowidth:true,
			mtype:"POST",
			url:"<%=basePath%>page/html/content/imp/queryFiles.action",
			datatype: "json",
			postData:{'fileDTO.ftype':'<%=ftype%>'},
			height: 450,
			colNames:['','文档名称','文档路径','处理状态', '上传时间', '上传用户','最后操作时间'],
			colModel:[
				{name:'fileId',index:'fileId', width:80, fixed:true, sortable:false, resize:false},
				{ name: 'filename'},
						{ name: 'realpath' ,width:300},
						{ name: 'operstatval'},
						{ name: 'uptime' ,unformat: pickDate},
						{ name: 'uname' },
						{ name: 'opertime',unformat: pickDate}
			], 
			gridComplete: function(){
				                //在Grid的第一列（Actions）中添加按钮E、S、C，添加增、删、查、改按钮；
				                 var ids = jQuery(grid_selector).jqGrid('getDataIDs');
				                 for(var i=0;i < ids.length;i++){
				                    var cl = ids[i];
				                    //alert($(grid_selector).getCell(cl,'fileId'));
				                   var p1= $(grid_selector).getCell(cl,'fileId');
				                   var p2= $(grid_selector).getCell(cl,'realpath');
				                   var p3= '<%=ftype%>';
				                       be = "<input   type='button' value='导入' class=\"btn btn-sm btn-danger btn-white btn-round\"  onclick='impexcel("+ p1 +",\""+_basepath+"\",\""+_ftype+"\")'  />"+""+
				                       "<input   type='button' value='删除' class=\"btn btn-sm btn-danger btn-white btn-round\"  onclick='delexcel("+ p1 +",\""+_basepath+"\",\""+_ftype+"\")'  />";
				                      jQuery(grid_selector).jqGrid('setRowData',ids[i],{fileId:be});
				                }
				            },
			viewrecords : true,
			rowNum:10,
			rowList:[10,15,20],
			pager : pager_selector,
			altRows: true,
			rownumbers: true,
			multiselect: false,
	        multiboxonly: true,
			loadComplete : function() {
				var table = this;
				setTimeout(function(){
					styleCheckbox(table);
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
			},
			caption: "社保数据导入"
		});
		$(window).triggerHandler('resize.jqGrid');
		function aceSwitch( cellvalue, options, cell ) {
			setTimeout(function(){
				$(cell) .find('input[type=checkbox]')
					.addClass('ace ace-switch ace-switch-5')
					.after('<span class="lbl"></span>');
			}, 0);
		}
	
		function pickDate( cellvalue, options, cell ) {
			setTimeout(function(){
				$(cell) .find('input[type=text]')
						.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
			}, 0);
		}
	 
		function styleCheckbox(table) {
		}
		function updateActionIcons(table) {
		}		
		function updatePagerIcons(table) {
			var replacement = 
			{
				'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
				'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
				'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
				'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
		}
	
		function enableTooltips(table) {
			$('.navtable .ui-pg-button').tooltip({container:'body'});
			$(table).find('.ui-pg-div').tooltip({container:'body'});
		}
	  
								$(document).one('ajaxloadstart.page',
										function(e) {
											try {
												myDropzone.destroy();
											} catch (e) {
											}
										});

							} catch (e) {
								alert('不支持此浏览器!');
							}

						});
					});
	function returnLogin(){
		window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
		window.open('', '_self');
	};
</script>
