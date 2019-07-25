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
					
					<form action="prospdetails/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="PROSPDETAILS_ID" id="PROSPDETAILS_ID" value="${pd.PROSPDETAILS_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">市场价:</td>
								<td><input type="text" name="TPSR_MARKET_PRICE" id="TPSR_MARKET_PRICE" value="${pd.TPSR_MARKET_PRICE}" maxlength="22" placeholder="这里输入市场价" title="市场价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">成本价:</td>
								<td><input type="text" name="TPSR_COST" id="TPSR_COST" value="${pd.TPSR_COST}" maxlength="22" placeholder="这里输入成本价" title="成本价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">销售价:</td>
								<td><input type="text" name="TPSR_PRICE" id="TPSR_PRICE" value="${pd.TPSR_PRICE}" maxlength="22" placeholder="这里输入销售价" title="销售价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">库存:</td>
								<td><input type="number" name="TPSR_STOCK" id="TPSR_STOCK" value="${pd.TPSR_STOCK}" maxlength="32" placeholder="这里输入库存" title="库存" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">是否启用:</td>
								<td><input type="text" name="TPSR_IS_ENABLE" id="TPSR_IS_ENABLE" value="${pd.TPSR_IS_ENABLE}" maxlength="1" placeholder="这里输入是否启用" title="是否启用" style="width:98%;"/></td>
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
			if($("#TPSR_MARKET_PRICE").val()==""){
				$("#TPSR_MARKET_PRICE").tips({
					side:3,
		            msg:'请输入市场价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSR_MARKET_PRICE").focus();
			return false;
			}
			if($("#TPSR_COST").val()==""){
				$("#TPSR_COST").tips({
					side:3,
		            msg:'请输入成本价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSR_COST").focus();
			return false;
			}
			if($("#TPSR_PRICE").val()==""){
				$("#TPSR_PRICE").tips({
					side:3,
		            msg:'请输入销售价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSR_PRICE").focus();
			return false;
			}
			if($("#TPSR_STOCK").val()==""){
				$("#TPSR_STOCK").tips({
					side:3,
		            msg:'请输入库存',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSR_STOCK").focus();
			return false;
			}
			if($("#TPSR_IS_ENABLE").val()==""){
				$("#TPSR_IS_ENABLE").tips({
					side:3,
		            msg:'请输入是否启用',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSR_IS_ENABLE").focus();
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