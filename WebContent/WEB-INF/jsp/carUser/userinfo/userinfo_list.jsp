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
<link  rel="stylesheet" type="text/css" id="skin" prePath="plugins/tab/" />
	<script type="text/javascript" charset="utf-8" src="plugins/tab/js/tab.js"></script>
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
						<form action="userinfo/list.do" method="post" name="Form" id="Form">
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
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入手机号码" class="nav-search-input" id="nav-search-input" autocomplete="off" name="TM_MOBILE" value="${pd.TM_MOBILE}" placeholder="这里输入手机号码"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								  
								<td style="padding-left:2px;"><input class="span10 date-picker" name="dateStart" id="dateStart"  value="${pd.dateStart }" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="dateEnd" id="dateEnd"  value="${pd.dateEnd }" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td>
								
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="TM_LEVEL_ID" id="tm_level_id" data-placeholder="代理等级" style="vertical-align:top;width: 120px;">
									<option value=""></option>
									<option value="">全部</option>
									<c:forEach items="${levelList}" var="level">
										<option value="${level.TML_ID }" <c:if test="${pd.TM_LEVEL_ID==level.TML_ID}">selected</c:if>>${level.TML_LEVEL }</option>
									</c:forEach>
								  	</select>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="caruser" id="id" data-placeholder="会员类型" style="vertical-align:top;width: 120px;">
									<option value=""></option>
									<option value="">全部</option>
									<option value="1">普通会员</option>
									<option value="2">有效会员</option>
								  	</select>
								</td>
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
									<th class="center" style="width:50px;">序号</th>
									<th class="center">呢称</th>
									<th class="center">用户名</th>
									<th class="center">姓名</th>
									<th class="center">会员类型</th>
									<th class="center">会员等级</th>
									<!-- <th class="center">地址</th> -->
									<th class="center">电话号码</th>
									<!-- <th class="center">积分</th> -->
									<th class="center">佣金余额</th>
									<th class="center">已提现佣金</th>
									<th class="center">总佣金</th>
									<th class="center">自买台数</th>
									<th class="center">直推台数</th>
									<th class="center">小片台数</th>
									<th class="center">总推销台数</th>
									<th class="center">直推人数</th>
									<th class="center">总推荐人数</th>
									<th class="center">推荐会员</th>
									<!-- <th class="center">手动设置会员等级</th> -->
									<th class="center">状态</th>
									<th class="center">注册日期</th>
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
											<td class='center' style="width: 30px;">${vs.index+1}</td>
										   	<td class='center'>${var.TM_NICKNAME}</td>
											<td class='center'>${var.TM_USERNAME}</td>
											<td class='center'><c:out value="${var.TM_NAME}" default="-" /></td>
											<td style="width: 60px;" class="center">
												<c:if test="${var.TM_TYPE == '1' }"><span class="label label-important arrowed-in">普通会员</span></c:if>
												<c:if test="${var.TM_TYPE == '2' }"><span class="label label-success arrowed">有效会员</span></c:if>
											</td>
											
										   	<td class='center'><c:out value="${var.TML_LEVEL}" default="-" /></td>
										   	
											<%-- <td class='center'>${var.TM_ADDRESS}</td> --%>
											<td class='center'><c:out value="${var.TM_MOBILE}" default="-" /></td>
											<%-- <td class='center'>${var.TM_SCORE}</td> --%>
											<td class='center'><fmt:formatNumber type="number" value="${var.TM_YONGJIN}" pattern="0.00"/></td>
											<td class='center'><fmt:formatNumber type="number" value="${var.TM_IS_YONGJIN}" pattern="0.00"/></td>
											<td class='center'><fmt:formatNumber type="number" value="${var.TM_TOTAL_YONGJIN }" pattern="0.00"/></td>
											<td class='center'><c:out value="${var.TMP_SELF_NUMBER}" default="-" /></td>
											<td class='center'><c:out value="${var.TMP_DIRECT_NUMBER}" default="-" /></td>
											<td class='center'><c:out value="${var.TMP_SMALL_NUMBER}" default="-" /></td>
											<td class='center'><c:out value="${var.TMP_TOTAL_NUMBER}" default="-" /></td>
											<td class='center'><c:out value="${var.TMP_RECOMMEND_NUMBER}" default="-" /></td>
											<td class='center'><c:out value="${var.TMP_TOTAL_RECOMMEND_NUMBER}" default="-" /></td>
											<td class="center">
												<c:choose> 
								                     <c:when test="${var.TMP_RECOMMEND_NUMBER > 0 }"> 
														<a onclick="carUser('${var.TM_ID}')" style="cursor:pointer;"><span class="label label-important arrowed-in">点击查看</span></a>
								                     </c:when> 
								                     <c:otherwise> 
								                     	-
								                     </c:otherwise> 
								               </c:choose>
											</td>
											<%-- <td class='center'>
												<c:if test="${var.TM_IS_SET == '0' }"><span class="label label-important arrowed-in">否</span></c:if>
												<c:if test="${var.TM_IS_SET == '1' }"><span class="label label-success arrowed">是</span></c:if>
											</td> --%>
											<td style="width: 60px;" class="center">
											    <c:if test="${var.TM_STATUS == '0' }"><span class="label label-important arrowed-in">删除</span></c:if>
												<c:if test="${var.TM_STATUS == '1' }"><span class="label label-success arrowed">正常</span></c:if>
												<c:if test="${var.TM_STATUS == '2' }"><span class="label label-important arrowed-in">冻结</span></c:if>
											</td>
											<td class='center'><fmt:formatDate value="${var.TM_ADD_DATE}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${var.TM_LEVEL_ID ==null && var.TM_TYPE == '2' }">
													<c:if test="${QX.edit == 1 }">
													
													<a class="btn btn-xs btn-success" title="升级" onclick="edit('${var.TM_ID}','${var.TM_TYPE}','${var.TM_IS_SET}');">
														<i class="ace-icon glyphicon glyphicon-off" title="升级"></i>
													</a>
													</c:if>
													</c:if>
													
													<c:if test="${QX.edit == 1 }">
														<a id="oning1${vs.index+1}" <c:if test="${var.TM_STATUS == '2'}">style="display: none;"</c:if> class="btn btn-info btn-xs" title="冻结" onclick="onoff('${var.TM_ID}','2');">
															<i class="ace-icon glyphicon glyphicon-lock" title="冻结"></i>
														</a>
														<a id="offing1${vs.index+1}" <c:if test="${var.TM_STATUS == '1'}">style="display: none;"</c:if> class="btn btn-info btn-xs" title="恢复" onclick="onoff('${var.TM_ID}','1');">
															<i class="ace-icon glyphicon glyphicon-ok" title="恢复"></i>
														</a>
														<a class="btn btn-xs btn-success" title="编辑" onclick="editUser('${var.TM_ID}');">
															<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
														</a>
													</c:if>
														
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
			
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="edit('${var.USERINFO_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="del('${var.USERINFO_ID}');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
															</li>
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
			 diag.URL = '<%=basePath%>userinfo/goAdd.do';
			 diag.Width = 500;
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
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>userinfo/delete.do?USERINFO_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if("success" == data.result){
							tosearch();
						}else if("false" == data.result){
							top.hangge();
							bootbox.dialog({
								message: "<span class='bigger-110'>删除失败,请先删除明细数据!</span>",
								buttons: 			
								{
									"button" :
									{
										"label" : "确定",
										"className" : "btn-sm btn-success"
									}
								}
							});
						}
					});
				}
			});
		}
		
		function carUser(carid){
			top.mainFrame.tabAddHandler("caruserid",'所属成员',"<%=basePath%>userinfo/list1.do?TM_ID="+carid);
			
		}
		
		
		//修改
		function edit(Id,str,st){
			bootbox.confirm("确定要升级代理吗？", function(result) {
				if(result){
					if(str=='1'){
						bootbox.alert("还不是有效会员，不能升级！");
					}else{
						if(st=='1'){
							bootbox.alert("已经升级了，不能再次升级");
						}else{
							 top.jzts();
							 var diag = new top.Dialog();
							 diag.Drag=true;
							 diag.Title ="编辑";
							 diag.URL = '<%=basePath%>userinfo/goEdit.do?TM_ID='+Id;
							 diag.Width = 300;
							 diag.Height = 300;
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
						
					}
				}
			
		
			
			})
			
		}
		//改变状态
		function onoff(Id,status){
			var str = "冻结";
			if(status==1){
				str = "恢复";
			}
			bootbox.confirm("确定要"+str+"该会员吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>userinfo/status.do?TM_ID="+Id+"&TM_STATUS="+status+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if("success" == data.result){
							tosearch();
						}else if("false" == data.result){
							top.hangge();
							bootbox.dialog({
								message: "<span class='bigger-110'>"+str+"失败!</span>",
								buttons: 			
								{
									"button" :
									{
										"label" : "确定",
										"className" : "btn-sm btn-success"
									}
								}
							});
						}
					});
				}
			});
		}
		
		//修改
		function editUser(id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>userinfo/goEditInfo.do?TM_ID='+id;
			 diag.Width = 469;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>userinfo/excel.do';
		}
	</script>


</body>
</html>