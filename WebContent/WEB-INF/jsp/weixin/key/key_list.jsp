<%@ page language="java" contentType="text/html; charset=UTF-8"
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
						<form action="key/list.do" method="post" name="Form" id="Form">
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
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">公众号</th>
									<th class="center">用户名</th>
									<th class="center">备注</th>
									<th class="center">定时器描叙</th>
									<th class="center">定时器状态</th>
									<th class="center">定时器时间规则</th>
									<th class="center" style="width:200px;">操作</th>
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
											<td class='center'>${var.WXUSERNAME}</td>
											<td class='center'>${var.USERNAME}</td>
											<td class='center'>${var.BZ}</td>
											<td class='center'>${var.TIMEEXPLAIN}</td>
											<td style="width: 80px;" class="center">
												<c:if test="${var.STATUS == 'Y' }"><span class="label label-success arrowed">已开启</span></c:if>
												<c:if test="${var.STATUS == 'N' }"><span class="label label-important arrowed-in">已停止</span></c:if>
												<c:if test="${var.STATUS ==null }"><span class="label label-important arrowed-in">请填写定时器规则 然后点击开启</span></c:if>
											</td>
											<td class='center'>${var.FHTIME}</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<a class="btn btn-xs btn-purple" title="自定义菜单" onclick="weixinMenu('${var.WXUSERNAME}');">
														自定义菜单
													</a>
													<c:if test="${QX.edit == 1 }">
													<c:choose>
													<c:when test="${var.STATUS != null}">
													<a id="offing1${vs.index+1}" <c:if test="${var.STATUS == 'Y'}">style="display: none;"</c:if> class="btn btn-info btn-xs" title="启动" onclick="onoff('${var.KEY_ID}','Y',this.id,'${vs.index+1}');">
														<i class="ace-icon glyphicon glyphicon-play" title="启动"></i>
													</a>
													<a id="oning1${vs.index+1}" <c:if test="${var.STATUS == 'N'}">style="display: none;"</c:if> class="btn btn-info btn-xs" title="关闭" onclick="onoff('${var.KEY_ID}','N',this.id,'${vs.index+1}');">
														<i class="ace-icon glyphicon glyphicon-off" title="关闭"></i>
													</a>
													</c:when>
													<c:when test="${var.STATUS == null}">
													<a id="offing1${vs.index+1}" class="btn btn-info btn-xs" title="启动" onclick="onoff('${var.KEY_ID}','Y',this.id,'${vs.index+1}');">
														<i class="ace-icon glyphicon glyphicon-play" title="启动"></i>
													</a>
													</c:when>
													</c:choose>
													
                                                     <a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.KEY_ID}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${QX.del == 1 }">
													<a class="btn btn-xs btn-danger" onclick="del('${var.KEY_ID}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
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
																<a id="offing2${vs.index+1}" <c:if test="${var.STATUS == 'Y'}">style="display: none;cursor:pointer;"</c:if> onclick="onoff('${var.KEY_ID}','Y',this.id,'${vs.index+1}');" class="tooltip-info" data-rel="tooltip" title="启动">
																	<span class="blue">
																		<i class="ace-icon glyphicon glyphicon-play" title="启动"></i>
																	</span>
																</a>
															</li>
															<li>
																<a id="oning2${vs.index+1}" <c:if test="${var.STATUS == 'N'}">style="display: none;cursor:pointer;"</c:if>  onclick="onoff('${var.KEY_ID}','N',this.id,'${vs.index+1}');" class="tooltip-info" data-rel="tooltip" title="关闭">
																	<span class="blue">
																		<i class="ace-icon glyphicon glyphicon-off" title="关闭"></i>
																	</span>
																</a>
															</li>
															
															
															
															<li>
																<a style="cursor:pointer;" onclick="edit('${var.KEY_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="del('${var.KEY_ID}');" class="tooltip-error" data-rel="tooltip" title="删除">
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
								<td style="vertical-align:top;">
									<c:if test="${QX.add == 1 }">
									<a class="btn btn-mini btn-success" onclick="add();">新增</a>
									
									</c:if>
									
								</td>
								<td style="vertical-align:top;">
									<c:if test="${QX.add == 1}">
									<c:if test="${not empty varList}">
                   			<a class="btn btn-mini btn-success" onclick="goSetTime();">设置定时器</a>
									</c:if>
									</c:if>
									
								</td>
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
			
		});
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>key/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 325;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//设置定时器
			function goSetTime(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="设置定时器";
			 diag.URL = '<%=basePath%>key/goSetTime.do';
			 diag.Width = 600;
			 diag.Height = 380;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
			
			//启动 or 关闭
			function onoff(KEY_ID,STATUS,ofid,VSID){
				top.jzts();
				$.ajax({
					type: "POST",
					url: '<%=basePath%>key/changeStatus.do?tm='+new Date().getTime(),
			    	data: {KEY_ID:KEY_ID,STATUS:STATUS},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){
						 nextPage(${page.currentPage});
						 top.hangge();
					}
				});
			}
		
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>key/delete.do?KEY_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>key/goEdit.do?KEY_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 325;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		
		//去设置微信菜单页面
		function weixinMenu(WXUSERNAME){
			window.location.href='<%=basePath%>mymenu/goEditMenu.do?WXUSERNAME='+WXUSERNAME;
		}
		
	</script>


</body>
</html>