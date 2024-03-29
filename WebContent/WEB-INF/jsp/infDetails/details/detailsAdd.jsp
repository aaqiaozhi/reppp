﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
	
	<style type="text/css">
	#dialog-add,#dialog-message,#dialog-comment{width:100%; height:100%; position:fixed; top:0px; z-index:99999999; display:none;}
	.commitopacity{position:absolute; width:100%; height:10000px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
	.commitbox{width:100%; margin:0px auto; position:absolute; top:120px; z-index:99999;}
	.commitbox_inner{width:96%; height:255px;  margin:6px auto; background:#efefef; border-radius:5px;}
	.commitbox_top{width:100%; height:253px; margin-bottom:10px; padding-top:10px; background:#FFF; border-radius:5px; box-shadow:1px 1px 3px #e8e8e8;}
	.commitbox_top textarea{width:95%; height:195px; display:block; margin:0px auto; border:0px;}
	.commitbox_cen{width:95%; height:40px; padding-top:10px;}
	.commitbox_cen div.left{float:left;background-size:15px; background-position:0px 3px; padding-left:18px; color:#f77500; font-size:16px; line-height:27px;}
	.commitbox_cen div.left img{width:30px;}
	.commitbox_cen div.right{float:right; margin-top:7px;}
	.commitbox_cen div.right span{cursor:pointer;}
	.commitbox_cen div.right span.save{border:solid 1px #c7c7c7; background:#6FB3E0; border-radius:3px; color:#FFF; padding:5px 10px;}
	.commitbox_cen div.right span.quxiao{border:solid 1px #f77400; background:#f77400; border-radius:3px; color:#FFF; padding:4px 9px;}
	</style>
	
	</head> 
<body>

<!-- 编辑邮箱  -->
<div id="dialog-add">
	<div class="commitopacity"></div>
  	<div class="commitbox">
	  	<div class="commitbox_inner">
	        <div class="commitbox_top">
	        	<textarea name="EMAILs" id="EMAILs" placeholder="请选输入对方邮箱,多个请用(;)分号隔开" title="请选输入对方邮箱,多个请用(;)分号隔开"></textarea>
	            <div class="commitbox_cen">
	                <div class="left" id="cityname"></div>
	                <div class="right"><span class="save" onClick="saveEmail()">保存</span>&nbsp;&nbsp;<span class="quxiao" onClick="cancel_pl()">取消</span></div>
	            </div>
	        </div>
	  	</div>
  	</div>
</div>
	
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">		
				 	<div class="span12">
						<div class="widget-box">
							<div class="widget-header widget-header-blue widget-header-flat wi1dget-header-large">
								<h4 class="lighter">新增资讯</h4>
							</div>
							<div class="widget-body">
							 <div class="widget-main">
							 <div class="step-content row-fluid position-relative">
								<div id="zhongxin">
								<textarea name="CONTENT" id="CONTENT" style="display:none" ></textarea>
								<input type="hidden" name="TYPE" id="TYPE" value="2"/>
								<input type="hidden" name="TI_CATA_ID" id="TI_CATA_ID" value="${TIC_ID }"/>
								<table style="width:100%;" id="xtable">
									<tr>
										<td>
											 <input type="text" name="TITLE" id="TITLE" value="" placeholder="请选输入文章标题" style="width:95%"/>
										</td>
									</tr>
									<tr>
										<td id="nr" style="padding-top: 5px;">
											 <script id="editor" type="text/plain" style="width:96%;height:259px;"></script>
										</td>
									</tr>
									<tr>
										<td style="text-align: center;padding-top: 5px;">
											<a class="btn btn-mini btn-primary" onclick="sendEm();">保存</a>
										<!--  	<label style="float:left;padding-left: 32px;"><input name="form-field-radio" id="form-field-radio1" onclick="setType('1');" checked="checked" type="radio" value="icon-edit" class="ace"><span class="lbl">纯文本</span></label>
											-->
											<label style="float:left;padding-left: 5px;"><input name="form-field-radio" id="form-field-radio2" onclick="setType('2');" checked="checked" type="radio" value="icon-edit" class="ace" /><span class="lbl">带标签</span></label>
											
										</td>
									</tr>
								</table>
								</div>
								 
							 <div id="zhongxin2" class="center" style="display:none"><br/><img src="" id='msg' /><br/>
							 
							 </div> 
							 </div><!--/widget-main-->
							</div><!--/widget-body-->
						</div>
					</div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->

	<!-- 返回顶部 -->
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
</div>
<!-- /.main-container -->
<!-- basic scripts -->
<!-- 页面底部js¨ -->
<%@ include file="../../system/index/foot.jsp"%>
<!-- ace scripts -->
<script src="static/ace/js/ace/ace.js"></script>
<!-- 编辑框-->
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
<!-- 编辑框-->

<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<!--引入属于此页面的js -->
<script type="text/javascript" src="static/js/myjs/toolEmail.js"></script>	
<script type="text/javascript">
	
	
$(top.hangge());
	
	function sendEm(){
		    
		if($("#TYPE").val()=="1"){
			$("#CONTENT").val(getContentTxt());
		}else{
			$("#CONTENT").val(getContent());
		}
		
	
		if($("#TITLE").val()==""){
			$("#TITLE").tips({
				side:3,
	            msg:'请输入标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TITLE").focus();
			return false;
		}
		if($("#CONTENT").val()==""){
			$("#nr").tips({
				side:1,
	            msg:'请输入内容',
	            bg:'#AE81FF',
	            time:3
	        });
			return false;
		}
		
	//	$("#zhongxin").hide();
		$("#zhongxin2").show();
		
		var TI_CATA_ID  = $("#TI_CATA_ID").val();
		var TITLE = $("#TITLE").val();
		var CONTENT = $("#CONTENT").val();
	
		
		$.ajax({
			type: "POST",
			url: locat+'/details/Add.do?tm='+new Date().getTime(),
	    	data: {TI_CATA_ID:TI_CATA_ID,TITLE:TITLE,CONTENT:CONTENT},
			dataType:'json',
			cache: false,
			success: function(data){
				 $.each(data.list, function(i, list){
					 if(list.stutas == 'success'){
						
						 $("#msg").tips({
							side:3,
				            msg:'保存成功',
				            bg:'#68B500',
				            time:2
					      });
					 }else{
						 $("#msg").tips({
								side:3,
					            msg:'有误 ! 请联系管理员',
					            bg:'#FF0000',
					            time:4
						 });
					 }
					 setTimeout("close()",800);
					 //timer(5);
				 });
			}
		});
		
	}
	 

	
	
	
	//倒计时
	function timer(intDiff){
		window.setInterval(function(){
		$('#second_shows').html('<s></s>'+intDiff+'秒');
		intDiff--;
		}, 1000);
	} 
	
	function close(){
		top.Dialog.close();
	}
	
	
	//$(top.Dialog.close());
	//ueditor纯文本
	function getContentTxt() {
	    var arr = [];
	    arr.push(UE.getEditor('editor').getContentTxt());
	    return arr.join("");
	}
	//ueditor有标签文本
	function getContent() {
	    var arr = [];
	    arr.push(UE.getEditor('editor').getContent());
	    return arr.join("");
	}
	function setType(value){
		$("#TYPE").val(value);
	}
	

	
</script>
</body>
</html>