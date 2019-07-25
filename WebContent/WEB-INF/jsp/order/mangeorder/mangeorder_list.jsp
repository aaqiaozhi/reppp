<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
							
						<!-- 检索  -->
						<form action="mangeorder/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td>
								
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
								<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">订单编号</th>
									<th class="center">下单时间</th>
									<th class="center">支付时间</th>
								    <th class="center">收货详细地址</th>
									<th class="center">电话号码</th>
									<th class="center">订单总金额</th>
									<th class="center">已付金额</th>
									<th class="center">优惠金额</th>
									<th class="center">支付手续费</th>
									<th class="center">运费</th>
									<th class="center">附言</th>
									<th class="center">调整金额</th>
									<th class="center">订单状态</th>
									<th class="center">支付状态</th>
									<th class="center">配送状态</th>
									<th class="center">会员</th>
									<th class="center">操作人员</th>
									<th class="center">收货人</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.MANGEORDER_ID}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>
											<a onclick="orderdear('${var.TO_ID}')" style="cursor:pointer;">${var.TO_SN}</a>
											
											</td>
											<td class='center'>${var.TO_ADD_DATE}</td>
											<td class='center'>${var.TO_PAY_DATE}</td>
											
											<td class='center'>${var.TO_ADDRESS}</td>
											<td class='center'>${var.TO_MOBILE}</td>
											<td class='center'>${var.TO_AMOUNT}</td>
											<td class='center'>${var.TO_PAID_AMOUNT}</td>
											<td class='center'>${var.TO_DISCOUNT_AMOUNT}</td>
											<td class='center'>${var.TO_FEE}</td>
											<td class='center'>${var.TO_FREIGHT}</td>
											<td class='center'>${var.TO_MEMO}</td>
											<td class='center'>${var.TO_OFFSET_AMOUNT}</td>
											<td class='center'>
											
												<c:if test="${var.TO_ORDER_STATUS == '1' }"><span class="label label-important arrowed-in">未确定</span></c:if>
												<c:if test="${var.TO_ORDER_STATUS == '2' }"><span class="label label-success arrowed">已经确定</span></c:if>
												<c:if test="${var.TO_ORDER_STATUS == '4' }"><span class="label label-important arrowed-in">已经取消</span></c:if>
												<c:if test="${var.TO_ORDER_STATUS == '3' }"><span class="label label-success arrowed">已完成</span></c:if>
											    <c:if test="${var.TO_ORDER_STATUS == '5' }"><span class="label label-important arrowed-in">已过期</span></c:if>
					                            <c:if test="${var.TO_ORDER_STATUS == '6' }"><span class="label label-important arrowed-in">已退货</span></c:if>						
											   <c:if test="${var.TO_ORDER_STATUS == '7' }"><span class="label label-important arrowed-in">已经删除</span></c:if>
											
											</td>
											<td class='center'>
											<c:if test="${var.TO_PAYMENT_STATUS == '1' }"><span class="label label-important arrowed-in">未支付</span></c:if>
											<c:if test="${var.TO_PAYMENT_STATUS == '3' }"><span class="label label-success arrowed">已支付</span></c:if>
											<c:if test="${var.TO_PAYMENT_STATUS == '5' }"><span class="label label-important arrowed-in">已退款</span></c:if>
											
											</td>
											
					
											<td class='center'>
											
											<c:if test="${var.TO_SHIPPING_STATUS == '1' }"><span class="label label-important arrowed-in">未发货</span></c:if>
											<c:if test="${var.TO_SHIPPING_STATUS == '3' }"><span class="label label-success arrowed">已发货</span></c:if>
											<c:if test="${var.TO_SHIPPING_STATUS == '5' }"><span class="label label-important arrowed-in">已退货</span></c:if>
											<c:if test="${var.TO_SHIPPING_STATUS == '6' }"><span class="label label-success arrowed">已收货</span></c:if>
											
											</td>
											<td class='center'>${var.tm_name}</td>
											<td class='center'>${var.TO_OPERATOR}</td>
											<td class='center'>${var.TO_CONSIGNEE}</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.edit == 1 }">
													<c:if test="${var.TO_ORDER_STATUS == '1'}">
													<c:if test="${var.TO_PAYMENT_STATUS == '1' }">
													
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.TO_ID}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													</c:if>
													
													</c:if>
													
													<c:if test="${QX.edit == 1 }">
													<c:if test="${var.TO_PAYMENT_STATUS == '3'}">
													<c:if test="${var.TO_SHIPPING_STATUS == '1' }">
													
													<a id="offing" class="btn btn-info btn-xs" title="发货" onclick="onoff('${var.TO_ID}');">
														<i class="ace-icon glyphicon glyphicon-play" title="发货"></i>
													</a>
													</c:if>
													</c:if>
													
													</c:if>
													
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
			
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<c:if test="${var.TO_ORDER_STATUS == '1'}">
													<c:if test="${var.TO_PAYMENT_STATUS == '1' }">
													
													<li>
																<a style="cursor:pointer;" onclick="edit('${var.MANGEORDER_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
													</c:if>
													</c:if>
															
															</c:if>
															
														</ul>
													</div>
												</div>
											</td>
										</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
					<tr>
								
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
						</div>
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

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {
		
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
			
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
			
			
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>mangeorder/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 tosearch();
					 }else{
						 tosearch();
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		
		function orderdear(carid){
			top.mainFrame.tabAddHandler("orderdear",'订单详情',"<%=basePath%>mangeorder/list1.do?TOI_ORDER_ID="+carid);
			
		}
		
		
		
		
		
		//发货
		function onoff(Id){
			bootbox.confirm("确定要发货吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>mangeorder/send.do?TO_ID="+Id;
					$.get(url,function(data){
						tosearch();
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			
			
			bootbox.confirm("确定要调整金额?", function(result) {
				if(result){
					 top.jzts();
					 var diag = new top.Dialog();
					 diag.Drag=true;
					 diag.Title ="编辑";
					 diag.URL = '<%=basePath%>mangeorder/goEdit.do?TO_ID='+Id;
					 diag.Width = 500;
					 diag.Height = 600;
					 diag.Modal = true;				//有无遮罩窗口
					 diag. ShowMaxButton = true;	//最大化按钮
				     diag.ShowMinButton = true;		//最小化按钮 
					 diag.CancelEvent = function(){ //关闭事件
						 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
							 tosearch();
						}
						diag.close();
					 };
					 diag.show();
				}
			
				
			});
			
		
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>mangeorder/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											tosearch();
									 });
								}
							});
						}
					}
				}
			});
		};
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>mangeorder/excel.do';
		}
	</script>


</body>
</html>