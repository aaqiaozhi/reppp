<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String filePath = request.getScheme() + "://"+ request.getServerName();
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
	<link href="plugins/uploadify/uploadify.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	var jsessionid = "<%=session.getId()%>";  //勿删，uploadify兼容火狐用到
	</script>
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="userinfo/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="TM_ID" id="TM_ID" value="${pd.TM_ID}"/>
						
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">姓名:</td>
								<td><input type="text" name="TM_NAME" id="TM_NAME" value="${pd.TM_NAME }" maxlength="32" placeholder="这里输入姓名" title="姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">昵称:</td>
								<td><input type="text" name="TM_NICKNAME" id="TM_NICKNAME" value="${pd.TM_NICKNAME }" maxlength="32" placeholder="这里输入昵称" title="昵称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">手机号码:</td>
								<td><input type="text" name="TM_MOBILE" id="TM_MOBILE" value="${pd.TM_MOBILE }" maxlength="32" placeholder="这里输入手机号码" title="手机号码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">身份证号码:</td>
								<td><input type="text" name="TM_ID_CARD" id="TM_ID_CARD" value="${pd.TM_ID_CARD }" maxlength="32" placeholder="这里输入身份证号码" title="身份证号码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">身份证正面照:</td>
								<td>
									<input type="file" name="File_name" id="uploadify1" keepDefaultStyle = "true" width="60px"/>
									<input type="hidden" name="TM_SFZZMZ_FILE" id="TM_SFZZMZ_FILE" value="${pd.TM_SFZZMZ_FILE }" />
									<span class="label label-important arrowed-in" style="display: none;" id="show-msg1">上传成功</span>
								</td>
							</tr>
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">身份证反面照:</td>
								<td>
									<input type="file" name="File_name" id="uploadify2" keepDefaultStyle = "true" width="60px"/>
									<input type="hidden" name="TM_SFZFMZ_FILE" id="TM_SFZFMZ_FILE" value="${pd.TM_SFZFMZ_FILE }" />
									<span class="label label-important arrowed-in" style="display: none;" id="show-msg2">上传成功</span>
								</td>
							</tr>
							
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->



<footer>
<div style="width: 100%;padding-bottom: 2px;" class="center">
	<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
	<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
</div>
</footer>

	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="plugins/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="plugins/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	
		<script type="text/javascript">
		var str;
		$("#uploadify1").uploadify({
			'buttonImg'	: 	"<%=basePath%>static/images/fileup.png",
			'uploader'	:	"<%=basePath%>plugins/uploadify/uploadify.swf",
			'script'    :	"<%=basePath%>plugins/uploadify/uploadFile.jsp;jsessionid="+jsessionid,
			'cancelImg' :	"<%=basePath%>plugins/uploadify/cancel.png",
			'folder'	:	"<%=filePath%>/upload/uploadImg/member",//上传文件存放的路径,请保持与uploadFile.jsp中PATH的值相同
			'queueId'	:	"fileQueue",
			'queueSizeLimit'	:	1,//限制上传文件的数量
			//'fileExt'	:	"*.rar,*.zip",
			//'fileDesc'	:	"RAR *.rar",//限制文件类型
			'fileExt'     : '*.*;*.*;*.*',
			'fileDesc'    : 'Please choose(.*, .*, .*)',
			'auto'		:	true,
			'multi'		:	true,//是否允许多文件上传
			'simUploadLimit':	2,//同时运行上传的进程数量
			'buttonText':	"files",
	        'scriptData':	{'uploadPath':'/upload/uploadImg/member/','stus':''},//这个参数用于传递用户自己的参数，此时'method' 必须设置为GET, 后台可以用request.getParameter('name')获取名字的值
			'method'	:	"GET",
			'onComplete':function(event,queueId,fileObj,response,data){
				str = response.trim();//单个上传完毕执行
			},
			'onAllComplete' : function(event,data) {
				$("#TM_SFZZMZ_FILE").val('/upload/uploadImg/member/'+str);
	    		$("#show-msg1").show();
	    	},
	    	'onSelect' : function(event, queueId, fileObj){
	    		//$("#hasTp1").val("ok");
	    	}
		});
		
		var str1;
		$("#uploadify2").uploadify({
			'buttonImg'	: 	"<%=basePath%>static/images/fileup.png",
			'uploader'	:	"<%=basePath%>plugins/uploadify/uploadify.swf",
			'script'    :	"<%=basePath%>plugins/uploadify/uploadFile.jsp;jsessionid="+jsessionid,
			'cancelImg' :	"<%=basePath%>plugins/uploadify/cancel.png",
			'folder'	:	"<%=filePath%>/upload/uploadImg/member",//上传文件存放的路径,请保持与uploadFile.jsp中PATH的值相同
			'queueId'	:	"fileQueue",
			'queueSizeLimit'	:	1,//限制上传文件的数量
			//'fileExt'	:	"*.rar,*.zip",
			//'fileDesc'	:	"RAR *.rar",//限制文件类型
			'fileExt'     : '*.*;*.*;*.*',
			'fileDesc'    : 'Please choose(.*, .*, .*)',
			'auto'		:	true,
			'multi'		:	true,//是否允许多文件上传
			'simUploadLimit':	2,//同时运行上传的进程数量
			'buttonText':	"files",
	        'scriptData':	{'uploadPath':'/upload/uploadImg/member/','stus':''},//这个参数用于传递用户自己的参数，此时'method' 必须设置为GET, 后台可以用request.getParameter('name')获取名字的值
			'method'	:	"GET",
			'onComplete':function(event,queueId,fileObj,response,data){
				str1 = response.trim();//单个上传完毕执行
			},
			'onAllComplete' : function(event,data) {
				$("#TM_SFZFMZ_FILE").val('/upload/uploadImg/member/'+str1);						
	    		$("#show-msg2").show();
	    	},
	    	'onSelect' : function(event, queueId, fileObj){
	    		//$("#hasTp1").val("ok");
	    	}
		});
		
		$(top.hangge());
		//保存
		function save(){
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>