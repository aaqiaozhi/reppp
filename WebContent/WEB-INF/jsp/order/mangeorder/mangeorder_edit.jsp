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
					
					<form action="mangeorder/${msg }.do" name="Form" id="Form" method="post">
					
					
					<input type="hidden" name="TO_AMOUNT" id="TO_AMOUNT" value="${pd.TO_AMOUNT}"/>
						<input type="hidden" name="TO_ID" id="TO_ID" value="${pd.TO_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收货详细地址:</td>
								<td><input type="text" name="TO_ADDRESS" id="TO_ADDRESS" value="${pd.TO_ADDRESS}" maxlength="255" placeholder="这里输入收货详细地址" title="收货详细地址" style="width:98%;"/></td>
							</tr>
							
							<!--  
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收货地——省份:</td>
								<td>
								<select class="chosen-select form-control" name="TFT_PROV_ID" onchange="change1(this.value)" id="TFT_PROV_ID" placeholder="请选择省" style="vertical-align:top;"  title="请选择省" style="width:98%;" >
								
									<c:forEach items="${levelListPro}" var="level">
										<option value="${level.PID }" <c:if test="${level.PID == pd.TO_PROV_ID }">selected</c:if>>${level.PNAME }</option>
									</c:forEach>
									</select>
								
								</td>
								-->
								
								<!--  <td><input type="number" name="TFT_PROV_ID" id="TFT_PROV_ID" value="${pd.TFT_PROV_ID}" maxlength="32" placeholder="这里输入发货地——省份" title="发货地——省份" style="width:98%;"/></td>
							
							
							</tr>-->
							<!-- 
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收货地——市:</td>
								
									<td>
								<select class="chosen-select form-control" name="TFT_CITY_ID" id="TFT_CITY_ID" onchange="change2(this.value)"  placeholder="请选择城市" style="vertical-align:top;"  title="请选择城市" style="width:98%;" >
								
								
										<option value="${pd.TO_CITY_ID }" >${pd.CNAME }</option>
									
									</select>
								
								</td>
								
								
							<td><input type="number" name="TFT_CITY_ID" id="TFT_CITY_ID" value="${pd.TFT_CITY_ID}" maxlength="32" placeholder="这里输入发货地——市" title="发货地——市" style="width:98%;"/></td>
						
							</tr> -->
							<!--  
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收货地——区县:</td>
								
								
									<td>
								<select class="chosen-select form-control" name="TFT_REGION_ID" id="TFT_REGION_ID" placeholder="请选择区" style="vertical-align:top;"  title="请选择区" style="width:98%;" >
								
								
										<option value="${pd.TO_REGION_ID }" >${pd.ONAME }</option>
									
									</select>
								
								</td>
								
								<!--  <td><input type="number" name="TFT_REGION_ID" id="TFT_REGION_ID" value="${pd.TFT_REGION_ID}" maxlength="32" placeholder="这里输入发货地——区县" title="发货地——区县" style="width:98%;"/></td>
							
							</tr>
							-->
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">调整金额:</td>
								<td><input type="number" name="TO_OFFSET_AMOUNT" id="TO_OFFSET_AMOUNT" value="${pd.TO_OFFSET_AMOUNT}" maxlength="22" placeholder="这里输入调整金额" title="调整金额" style="width:98%;"/></td>
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
		
		
		//第一级值改变事件(初始第二级)
		function change1(value){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>freighttpl/getCity.do?tm='+new Date().getTime(),
		    	data: {PID:value},
				dataType:'json',
				cache: false,
				success: function(data){
					$("#TFT_CITY_ID").html('<option>请选择</option>');
					$("#TFT_REGION_ID").html('<option>请选择</option>');
					
					
					 $.each(data.list, function(i, dvar){
							$("#TFT_CITY_ID").append("<option value="+dvar.CID+">"+dvar.CNAME+"</option>");
					 });
				}
			});
		}
		
		
		function change2(value){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>freighttpl/getCounty.do?tm='+new Date().getTime(),
		    	data: {CID:value},
				dataType:'json',
				cache: false,
				success: function(data){
					
					 $.each(data.list, function(i, dvar){
							$("#TFT_REGION_ID").append("<option value="+dvar.OID+">"+dvar.ONAME+"</option>");
					 });
				}
			});
		}
		
		
		function save(){
			if($("#TO_ADDRESS").val()==""){
				$("#TO_ADDRESS").tips({
					side:3,
		            msg:'请输入收货详细地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TO_ADDRESS").focus();
			return false;
			}
		
			
			if($("#TO_OFFSET_AMOUNT").val()==""){
				$("#TO_OFFSET_AMOUNT").tips({
					side:3,
		            msg:'请输入调整金额',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TO_OFFSET_AMOUNT").focus();
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