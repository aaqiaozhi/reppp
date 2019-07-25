<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
					
					<form action="agentlevel/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="TML_ID" id="TML_ID" value="${pd.TML_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">代理等级:</td>
								<!--  
								<td><input type="text" name="TML_LEVEL" id="TML_LEVEL" value="${pd.TML_TOTAL_NUMBER}" maxlength="32" placeholder="请输入等级" title="等级" style="width:98%;"/></td>
								-->
								
							  <td><input type="text" name="TML_LEVEL" id="TML_LEVEL" value="${pd.TML_LEVEL}" maxlength="50" placeholder="这里输入等级" title="等级" style="width:98%;"/></td>
								
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">总台数:</td>
								<td><input type="number" name="TML_TOTAL_NUMBER" id="TML_TOTAL_NUMBER" value="${pd.TML_TOTAL_NUMBER}" maxlength="32" placeholder="这里输入总台数" title="总台数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">直推台数:</td>
								<td><input type="number" name="TML_DIRECT_NUMBER" id="TML_DIRECT_NUMBER" value="${pd.TML_DIRECT_NUMBER}" maxlength="32" placeholder="这里输入直推台数" title="直推台数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">小片台数:</td>
								<td><input type="number" name="TML_SMALL_NUMBER" id="TML_SMALL_NUMBER" value="${pd.TML_SMALL_NUMBER}" maxlength="32" placeholder="这里输入小片台数" title="小片台数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">极差奖比例:</td>
								<td><input type="text" name="TML_RANGE_PRIZE_SCALE" id="TML_RANGE_PRIZE_SCALE" value="${pd.TML_RANGE_PRIZE_SCALE}" maxlength="22" placeholder="这里输入极差奖比例" title="极差奖比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">极差奖:</td>
								<td><input type="text" name="TML_RANGE_PRIZE" id="TML_RANGE_PRIZE" value="${pd.TML_RANGE_PRIZE}" maxlength="22" placeholder="这里输入极差奖" title="极差奖" style="width:98%;"/></td>
							</tr>
							<tr>
							<td style="width:79px;text-align: right;padding-top: 13px;">状态:</td>
									<td>
							<select name="TML_STATUS" title="状态">
										<option value="1" <c:if test="${pd.TML_STATUS == '1' }">selected</c:if> >正常</option>
										<option value="0" <c:if test="${pd.TML_STATUS == '0' }">selected</c:if> >删除</option>
										</select>
								</td>
							<!--  
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td><input type="number" name="TML_STATUS" id="TML_STATUS" value="${pd.TML_STATUS}" maxlength="32" placeholder="这里输入状态" title="状态" style="width:98%;"/></td>
								-->
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

<c:if test="${'edit' == msg }">
<!--
	<div>
		<iframe name="treeFrame" id="treeFrame" frameborder="0" src="<%=basePath%>/agentlevelmx/list.do?AGENTLEVEL_ID=${pd.TML_ID}" style="margin:0 auto;width:805px;height:368px;;"></iframe>
	</div>
	 -->
</c:if>

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
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#TML_LEVEL").val()==""){
				$("#TML_LEVEL").tips({
					side:3,
		            msg:'请输入等级',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TML_LEVEL").focus();
			return false;
			}
			if($("#TML_TOTAL_NUMBER").val()==""){
				$("#TML_TOTAL_NUMBER").tips({
					side:3,
		            msg:'请输入总台数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TML_TOTAL_NUMBER").focus();
			return false;
			}
			if($("#TML_DIRECT_NUMBER").val()==""){
				$("#TML_DIRECT_NUMBER").tips({
					side:3,
		            msg:'请输入直推台数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TML_DIRECT_NUMBER").focus();
			return false;
			}
			if($("#TML_SMALL_NUMBER").val()==""){
				$("#TML_SMALL_NUMBER").tips({
					side:3,
		            msg:'请输入小片台数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TML_SMALL_NUMBER").focus();
			return false;
			}
			if($("#TML_RANGE_PRIZE_SCALE").val()==""){
				$("#TML_RANGE_PRIZE_SCALE").tips({
					side:3,
		            msg:'请输入极差奖比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TML_RANGE_PRIZE_SCALE").focus();
			return false;
			}
			if($("#TML_RANGE_PRIZE").val()==""){
				$("#TML_RANGE_PRIZE").tips({
					side:3,
		            msg:'请输入极差奖',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TML_RANGE_PRIZE").focus();
			return false;
			}
			if($("#TML_STATUS").val()==""){
				$("#TML_STATUS").tips({
					side:3,
		            msg:'请输入状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TML_STATUS").focus();
			return false;
			}
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