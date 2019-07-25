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
					
					<form action="costdetail/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="TLF_ID" id="TLF_ID" value="${pd.TLF_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">首重:</td>
								<td><input type="number" name="TLF_FIRST_WEIGHT" id="TLF_FIRST_WEIGHT" value="${pd.TLF_FIRST_WEIGHT}" maxlength="22" placeholder="这里输入首重" title="首重" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">续重:</td>
								<td><input type="number" name="TLF_CONTINUE_WEIGHT" id="TLF_CONTINUE_WEIGHT" value="${pd.TLF_CONTINUE_WEIGHT}" maxlength="22" placeholder="这里输入续重" title="续重" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">首重价格:</td>
								<td><input type="number" name="TLF_FIRST_PRICE" id="TLF_FIRST_PRICE" value="${pd.TLF_FIRST_PRICE}" maxlength="22" placeholder="这里输入首重价格" title="首重价格" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">续重价格:</td>
								<td><input type="number" name="TLF_CONTINUE_PRICE" id="TLF_CONTINUE_PRICE" value="${pd.TLF_CONTINUE_PRICE}" maxlength="22" placeholder="这里输入续重价格" title="续重价格" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">省:</td>
								
								<td>
								<select class="chosen-select form-control" name="TFT_PROV_ID"  id="TFT_PROV_ID" placeholder="请选择省" style="vertical-align:top;"  title="请选择省" style="width:98%;" >
								
									<c:forEach items="${levelListPro}" var="level">
										<option value="${level.PID }" <c:if test="${level.PID == pd.TLF_PROV_ID }">selected</c:if>>${level.PNAME }</option>
									</c:forEach>
									</select>
								
								</td>
								
								
								
								
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">运费模板:</td>
								
								<td>
								<select class="chosen-select form-control" name="TLF_FREIGHT_TEMPLATE_ID" id="TLF_FREIGHT_TEMPLATE_ID" placeholder="请选择运费模板" style="vertical-align:top;"  title="请选择运费模板" style="width:98%;" >
								
									<c:forEach items="${levelListTab}" var="level">
									 
										<option value="${level.TFT_ID }" <c:if test="${level.TFT_ID == pd.TP_FREIGHT_TEMPLATE_ID}">selected</c:if>>${level.TFT_NAME }</option>
									</c:forEach>
									</select>
								</td>
								
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
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
			if($("#TLF_FIRST_WEIGHT").val()==""){
				$("#TLF_FIRST_WEIGHT").tips({
					side:3,
		            msg:'请输入首重',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TLF_FIRST_WEIGHT").focus();
			return false;
			}
			if($("#TLF_CONTINUE_WEIGHT").val()==""){
				$("#TLF_CONTINUE_WEIGHT").tips({
					side:3,
		            msg:'请输入续重',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TLF_CONTINUE_WEIGHT").focus();
			return false;
			}
			if($("#TLF_FIRST_PRICE").val()==""){
				$("#TLF_FIRST_PRICE").tips({
					side:3,
		            msg:'请输入首重价格',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TLF_FIRST_PRICE").focus();
			return false;
			}
			if($("#TLF_CONTINUE_PRICE").val()==""){
				$("#TLF_CONTINUE_PRICE").tips({
					side:3,
		            msg:'请输入续重价格',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TLF_CONTINUE_PRICE").focus();
			return false;
			}
			if($("#TLF_PROV_ID").val()==""){
				$("#TLF_PROV_ID").tips({
					side:3,
		            msg:'请输入省',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TLF_PROV_ID").focus();
			return false;
			}
			
			
			if($("#TLF_FREIGHT_TEMPLATE_ID").val()==""){
				$("#TLF_FREIGHT_TEMPLATE_ID").tips({
					side:3,
		            msg:'请输入运费模板',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TLF_FREIGHT_TEMPLATE_ID").focus();
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